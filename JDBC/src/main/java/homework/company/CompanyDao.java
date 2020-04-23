package homework.company;

import homework.company.consoleHelper.ConsoleHelper;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.sql.*;

public class CompanyDao {
    private static final String URL = "jdbc:h2:mem:";
    private static final String SQL_CREATE_DEPARTMENTS = "CREATE TABLE Departments(id TINYINT AUTO_INCREMENT NOT NULL PRIMARY KEY," +
            "department_name NVARCHAR(50) NOT NULL CHECK(department_name != '')," +
            "manager_id BIGINT DEFAULT NULL" + ")";
    private static final String SQL_CREATE_EMPLOYEES = "CREATE TABLE Employees(id SERIAL PRIMARY KEY," +
            "full_name NVARCHAR(50) NOT NULL CHECK(full_name !='')," +
            "employee_position NVARCHAR(30) NOT NULL CHECK(employee_position != '')," +
            "salary DECIMAL(10,3) CHECK (salary > 0)," +
            "department_id TINYINT NOT NULL CHECK (department_id > 0)," +
            "CONSTRAINT fk_departments FOREIGN KEY (department_id) REFERENCES Departments(id)" + ")";
    private static final String SQL_POPULATE_EMPLOYEES = "INSERT INTO Employees(full_name, employee_position, salary, department_id) VALUES (?,?,?,?)";
    private static final String SQL_POPULATE_DEPARTMENTS = "INSERT INTO Departments(department_name, manager_id) VALUES (?,?)";
    private static final String SQL_SELECT_DEPARTMENTS = "SELECT * FROM Departments";
    private static final String SQL_SELECT_ALL_EMPLOYEES = "SELECT Employees.id, Employees.full_name, Employees.employee_position, Employees.salary, Departments.department_name FROM Employees JOIN Departments ON department_id=Departments.id";
    private static final String SQL_SELECT_DEPARTMENT_EMPLOYEES_SORTED_BY_FULLNAME = "SELECT * FROM Employees WHERE department_id=? ORDER BY full_name";
    private static final String SQL_GET_DEPARTMENT_NAME_BY_ID = "SELECT (department_name) FROM Departments WHERE id=?";
    private static final String SQL_SELECT_DEPARTMENT_EMPLOYEES_SORTED_BY_SALARY = "SELECT * FROM Employees WHERE department_id=? ORDER BY salary";
    private static final String SQL_GET_DEPARTMENT_BY_MANAGER_ID = "SELECT * FROM Departments WHERE manager_id=?";
    private static final String SQL_GET_EMPLOYEE_NAME_BY_ID = "SELECT (full_name) FROM Employees WHERE id=?";
    private static final String SQL_SELECT_EMPLOYEE_BY_ID = "SELECT Employees.id, Employees.full_name, Employees.employee_position, Employees.salary, Departments.department_name FROM Employees JOIN Departments ON department_id=Departments.id WHERE Employees.id=?";
    private static final String SQL_SELECT_EMPLOYEES_COMPARED_BY_SALARY = "SELECT Employees.id, Employees.full_name, Employees.employee_position, Employees.salary, Departments.department_name FROM Employees JOIN Departments ON department_id=Departments.id WHERE salary>(SELECT salary FROM Employees WHERE id=?) ORDER BY salary DESC";

    private static Connection connection;

    public CompanyDao() {
        init();
        connection = getConnection();
    }

    private void init() {
        try {
            Class.forName("org.h2.Driver");
        } catch (ClassNotFoundException e) {
            ConsoleHelper.writeMessage("Connection was unsuccessful");
        }
    }

    private Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public void createDepartmentsTable() {
        try (Statement statement = connection.createStatement();) {
            statement.execute(SQL_CREATE_DEPARTMENTS);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createEmployeesTable() {
        try (Statement statement = connection.createStatement();) {
            statement.execute(SQL_CREATE_EMPLOYEES);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void populateDepartmentsTableFromJson() {
        JSONParser jsonParser = new JSONParser();
        PreparedStatement preparedStatement = null;
        Statement statement = null;
        try {
            statement = connection.createStatement();
            preparedStatement = connection.prepareStatement(SQL_POPULATE_DEPARTMENTS);
            URL url = Thread.currentThread().getContextClassLoader().getResource("defaultDepartments.json");
            JSONArray jsonArray = (JSONArray) jsonParser.parse(new FileReader(url.getPath()));
            for (int i = 0; i < jsonArray.size(); i++) {
                JSONObject jsonObjectRow = (JSONObject) jsonArray.get(i);
                String fullName = (String) jsonObjectRow.get("departmentName");
                Long managerId = (Long) jsonObjectRow.get("managerId");
                preparedStatement.setString(1, fullName);
                preparedStatement.setLong(2, managerId);
                preparedStatement.addBatch();
            }
            preparedStatement.executeBatch();
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
            rollBackAndClose(preparedStatement, statement);
        } finally {
            close(preparedStatement, statement);
        }
    }

    public void populateEmployeesTableFromJson() {
        JSONParser jsonParser = new JSONParser();
        PreparedStatement preparedStatement = null;
        Statement statement = null;
        try {
            statement = connection.createStatement();
            preparedStatement = connection.prepareStatement(SQL_POPULATE_EMPLOYEES);
            connection.setAutoCommit(false);
            URL url = Thread.currentThread().getContextClassLoader().getResource("defaultEmployees.json");
            JSONArray jsonArray = (JSONArray) jsonParser.parse(new FileReader(url.getPath()));
            for (int i = 0; i < jsonArray.size(); i++) {
                JSONObject jsonObjectRow = (JSONObject) jsonArray.get(i);
                String fullName = (String) jsonObjectRow.get("fullName");
                String position = (String) jsonObjectRow.get("position");
                Double salary = (Double) jsonObjectRow.get("salary");
                Long departmentId = (Long) jsonObjectRow.get("departmentId");
                preparedStatement.setString(1, fullName);
                preparedStatement.setString(2, position);
                preparedStatement.setDouble(3, salary);
                preparedStatement.setLong(4, departmentId);
                preparedStatement.addBatch();
            }
            preparedStatement.executeBatch();
            connection.commit();
            connection.setAutoCommit(true);
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
            rollBackAndClose(preparedStatement, statement);
        }
    }

    public int getCompanyDepartments() {
        Statement statement = null;
        ResultSet resultSet = null;
        int count = 0;
        try {
            statement= connection.createStatement();
            resultSet = statement.executeQuery(SQL_SELECT_DEPARTMENTS);
            String departmentName = null;
            ConsoleHelper.writeMessage("Foobar Company Departments: ");
            ConsoleHelper.writeMessage("id     |        department_name");
            ConsoleHelper.writeMessage("_____________________________________________________");
            while (resultSet.next()) {
                count++;
                int id = resultSet.getInt("id");
                departmentName = resultSet.getNString("department_name");
                departmentName = formatWithSpaces(departmentName, "department_name");
                ConsoleHelper.writeMessage(String.format("%d      |        %s", id, departmentName));
            }
         } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(statement, resultSet);
        }
        return count;
    }

    private String formatWithSpaces(String column, String columnHeading) {
        String departmentNameHeading = columnHeading;
        int headLength = departmentNameHeading.length();
        int columnLength = column.length();
        int numberOfSpaces = 17 + (headLength - columnLength);
        StringBuilder builder = new StringBuilder(column);
        for (int i = 0; i < numberOfSpaces; i++) {
            builder.append(" ");
        }
        return builder.toString();
    }

    private void close(Statement statement, ResultSet resultSet) {
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    private void rollBackAndClose(PreparedStatement preparedStatement, Statement...statements) {
        if (connection != null) {
            try {
                connection.rollback();
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        for (Statement statement: statements) {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

    private void close(Statement...statements) {
        for (Statement statement: statements) {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

    public void getDepartmentEmployeesSortedByFullName(int departmentId) {
        String departmentName = getDepartmentNameById(departmentId);
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement(SQL_SELECT_DEPARTMENT_EMPLOYEES_SORTED_BY_FULLNAME);
            preparedStatement.setInt(1,departmentId);
            resultSet = preparedStatement.executeQuery();
            String fullName = null;
            String position = null;
            Double salary = null;
            ConsoleHelper.writeMessage(departmentName + " employees: ");
            ConsoleHelper.writeMessage("id     |        full_name                 |       position                           |     salary");
            ConsoleHelper.writeMessage("_________________________________________________________________________________________________________");
            while (resultSet.next()) {
                long id = resultSet.getLong("id");
                fullName = resultSet.getNString("full_name");
                fullName = formatWithSpaces(fullName, "full_name");
                position = resultSet.getNString("employee_position");
                position = formatWithSpaces(position, "employee_position");
                salary = resultSet.getDouble("salary");
                ConsoleHelper.writeMessage(String.format("%d      |        %s|        %s|       %.3f", id, fullName, position, salary));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(preparedStatement, resultSet);
        }
    }

    private String getDepartmentNameById(int departmentId) {
        String departmentName = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement(SQL_GET_DEPARTMENT_NAME_BY_ID);
            preparedStatement.setInt(1,departmentId);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                departmentName = resultSet.getNString("department_name");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(preparedStatement, resultSet);
        }
        return departmentName;
    }

    public void getDepartmentEmployeesSortedBySalary(int departmentId) {
        String departmentName = getDepartmentNameById(departmentId);
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement(SQL_SELECT_DEPARTMENT_EMPLOYEES_SORTED_BY_SALARY);
            preparedStatement.setInt(1,departmentId);
            resultSet = preparedStatement.executeQuery();
            String fullName = null;
            String position = null;
            Double salary = null;
            ConsoleHelper.writeMessage(departmentName + " employees: ");
            ConsoleHelper.writeMessage("id     |        full_name                 |       position                           |     salary");
            ConsoleHelper.writeMessage("_________________________________________________________________________________________________________");
            while (resultSet.next()) {
                long id = resultSet.getLong("id");
                fullName = resultSet.getNString("full_name");
                fullName = formatWithSpaces(fullName, "full_name");
                position = resultSet.getNString("employee_position");
                position = formatWithSpaces(position, "employee_position");
                salary = resultSet.getDouble("salary");
                ConsoleHelper.writeMessage(String.format("%d      |        %s|        %s|       %.3f", id, fullName, position, salary));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(preparedStatement, resultSet);
        }
    }

    public int getAllEmployees() {
        Statement statement = null;
        ResultSet resultSet = null;
        int count = 0;
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(SQL_SELECT_ALL_EMPLOYEES);
            String fullName = null;
            String position = null;
            Double salary = null;
            String departmentName = null;
            ConsoleHelper.writeMessage("id     |        full_name                 |       position                           |     salary                   |    department_name");
            ConsoleHelper.writeMessage("___________________________________________________________________________________________________________________________________________________");
            while (resultSet.next()) {
                count++;
                long id = resultSet.getLong("id");
                fullName = resultSet.getNString("full_name");
                fullName = formatWithSpaces(fullName, "full_name");
                position = resultSet.getNString("employee_position");
                position = formatWithSpaces(position, "employee_position");
                salary = resultSet.getDouble("salary");
                String formattedSalary = String.format("%.3f", salary);
                formattedSalary = formatWithSpaces(formattedSalary, "salary");
                departmentName = resultSet.getNString("department_name");
                departmentName = formatWithSpaces(departmentName, "department_name");
                ConsoleHelper.writeMessage(String.format("%d      |        %s|        %s|       %s|       %s", id, fullName, position, formattedSalary, departmentName));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(statement, resultSet);
        }
        return count;
    }

    public void getEmployeeInfo(int employeeId) {
        int departmentId = getDepartmentIdByManagerId(employeeId);
        if (departmentId > 0) {
            getDepartmentEmployeesWithoutManager(employeeId, departmentId);
        } else {
            getSingleEmployeeInfo(employeeId);
        }
    }

    public void getSingleEmployeeInfo(int employeeId) {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement(SQL_SELECT_EMPLOYEE_BY_ID);
            preparedStatement.setLong(1,employeeId);
            resultSet = preparedStatement.executeQuery();
            String fullName = null;
            String position = null;
            String departmentName = null;
            Double salary = null;
            ConsoleHelper.writeMessage("id     |        full_name                 |       position                           |     salary                   |     department_name");
            ConsoleHelper.writeMessage("___________________________________________________________________________________________________________________________________________________");
            while (resultSet.next()) {
                long id = resultSet.getLong("id");
                fullName = resultSet.getNString("full_name");
                fullName = formatWithSpaces(fullName, "full_name");
                position = resultSet.getNString("employee_position");
                position = formatWithSpaces(position, "employee_position");
                salary = resultSet.getDouble("salary");
                String formattedSalary = String.format("%.3f", salary);
                departmentName = resultSet.getNString("department_name");
                formattedSalary = formatWithSpaces(formattedSalary, "salary");
                ConsoleHelper.writeMessage(String.format("%d      |        %s|        %s|       %s|       %s", id, fullName, position, formattedSalary, departmentName));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(preparedStatement, resultSet);
        }
    }

    private void getDepartmentEmployeesWithoutManager(int managerId, int departmentId) {
        String managerName = getEmployeeNameById(managerId);
        String departmentName = getDepartmentNameById(departmentId);
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement(SQL_SELECT_DEPARTMENT_EMPLOYEES_SORTED_BY_FULLNAME);
            preparedStatement.setInt(1,departmentId);
            resultSet = preparedStatement.executeQuery();
            String fullName = null;
            String position = null;
            Double salary = null;
            ConsoleHelper.writeMessage(departmentName + " manager: " + managerName + "\r\n");
            ConsoleHelper.writeMessage("Other employees: \r\n");
            ConsoleHelper.writeMessage("id     |        full_name                 |       position                           |     salary");
            ConsoleHelper.writeMessage("_________________________________________________________________________________________________________");
            while (resultSet.next()) {
                long id = resultSet.getLong("id");
                if (id == managerId) continue;
                fullName = resultSet.getNString("full_name");
                fullName = formatWithSpaces(fullName, "full_name");
                position = resultSet.getNString("employee_position");
                position = formatWithSpaces(position, "employee_position");
                salary = resultSet.getDouble("salary");
                ConsoleHelper.writeMessage(String.format("%d      |        %s|        %s|       %.3f", id, fullName, position, salary));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(preparedStatement, resultSet);
        }
    }

    private String getEmployeeNameById(int employeeId) {
        String managerName = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement(SQL_GET_EMPLOYEE_NAME_BY_ID);
            preparedStatement.setLong(1,employeeId);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                managerName = resultSet.getNString("full_name");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(preparedStatement, resultSet);
        }
        return managerName;
    }

    private int getDepartmentIdByManagerId(int managerId) {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement(SQL_GET_DEPARTMENT_BY_MANAGER_ID);
            preparedStatement.setLong(1,managerId);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt("id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(preparedStatement, resultSet);
        }
        return 0;
    }

    public void getComparedEmployeesBySalary(int employeeId) {
        String employeeName = getEmployeeNameById(employeeId);
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement(SQL_SELECT_EMPLOYEES_COMPARED_BY_SALARY);
            preparedStatement.setLong(1,employeeId);
            resultSet = preparedStatement.executeQuery();
            String fullName = null;
            String position = null;
            String departmentName = null;
            Double salary = null;
            ConsoleHelper.writeMessage("id     |        full_name                 |       position                           |     salary                   |     department_name");
            ConsoleHelper.writeMessage("___________________________________________________________________________________________________________________________________________________");
            while (resultSet.next()) {
                long id = resultSet.getLong("id");
                fullName = resultSet.getNString("full_name");
                fullName = formatWithSpaces(fullName, "full_name");
                position = resultSet.getNString("employee_position");
                position = formatWithSpaces(position, "employee_position");
                salary = resultSet.getDouble("salary");
                String formattedSalary = String.format("%.3f", salary);
                departmentName = resultSet.getNString("department_name");
                formattedSalary = formatWithSpaces(formattedSalary, "salary");
                ConsoleHelper.writeMessage(String.format("%d      |        %s|        %s|       %s|       %s", id, fullName, position, formattedSalary, departmentName));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(preparedStatement, resultSet);
        }
    }

    public void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                //DO NOTHING
            }
        }
    }
}
