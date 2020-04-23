package homework.company;


import homework.company.consoleHelper.ConsoleHelper;

public class CompanyService {
    private  static CompanyDao companyDao = new CompanyDao();

    public CompanyService() {
        companyDao.createDepartmentsTable();
        companyDao.createEmployeesTable();
        companyDao.populateDepartmentsTableFromJson();
        companyDao.populateEmployeesTableFromJson();
    }

    public int getDepartments() {
        return companyDao.getCompanyDepartments();
    }

    public void getDepartmentSortedEmployees(int departmentId, int sortEmployeesOption) {
        switch (sortEmployeesOption) {
            case 1:
                companyDao.getDepartmentEmployeesSortedByFullName(departmentId);
                break;
            case 2:
                companyDao.getDepartmentEmployeesSortedBySalary(departmentId);
                break;
        }
    }

    public int getAllEmployees() {
        return companyDao.getAllEmployees();
    }

    public void getEmployeeInfo(int employeeId) {
        companyDao.getEmployeeInfo(employeeId);
    }

    public void getComparedEmployeesBySalary(int employeeId) {
        companyDao.getSingleEmployeeInfo(employeeId);
        ConsoleHelper.writeMessage("");
        companyDao.getComparedEmployeesBySalary(employeeId);
    }

    public void close() {
        companyDao.closeConnection();
    }
}
