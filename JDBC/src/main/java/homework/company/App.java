package homework.company;

import homework.company.consoleHelper.ConsoleHelper;
import homework.company.exceptions.ExitException;

public class App {


    public static void main(String[] args) {
        CompanyService service = new CompanyService();
        while (true) {
            try {
                ConsoleHelper.writeMessage("1 - Get Department info\r\n2 - Get Employee Info\r\n0 - Exit");
                int actionAnswer = ConsoleHelper.readNumber("action");
                switch (actionAnswer) {
                    case 1:
                        getDepartmentInfo(service);
                        break;
                    case 2:
                        getEmployeeInfo(service);
                        break;
                    default:
                    ConsoleHelper.writeMessage("There is no such option. Please try again");
                }
            } catch (ExitException e) {
                ConsoleHelper.writeMessage(e.getMessage());
                break;
            }
        }
        close(service);
    }

    private static void getEmployeeInfo(CompanyService service) throws ExitException {
        while (true) {
            int employeesCount = service.getAllEmployees();
            if (employeesCount == 0) throw new ExitException("There is no any employees. Exiting...");
            int employeeNumberAnswer = ConsoleHelper.readNumber("employee");
            if (employeeNumberAnswer <= employeesCount) {
                while (true) {
                    ConsoleHelper.writeMessage("1 - Get Employee info\r\n2 - Compare employee by salary\r\n0 - Exit");
                    int actionAnswer = ConsoleHelper.readNumber("action");
                    switch (actionAnswer) {
                        case 1:
                            service.getEmployeeInfo(employeeNumberAnswer);
                            break;
                        case 2:
                            service.getComparedEmployeesBySalary(employeeNumberAnswer);
                            break;
                        default:
                            ConsoleHelper.writeMessage("There is no such option. Please try again");
                            continue;
                    }
                    break;
                }
                break;
            } else {
                ConsoleHelper.writeMessage("There is no such employee. Please try again");
            }
        }
    }

    private static void getDepartmentInfo(CompanyService service) throws ExitException {
        while (true) {
            int departmentsCount = service.getDepartments();
            if (departmentsCount == 0) throw new ExitException("There are no any departments. Exiting...");
            int departmentNumberAnswer = ConsoleHelper.readNumber("department");
            if (departmentNumberAnswer <= departmentsCount) {
                while (true) {
                    ConsoleHelper.writeMessage("1 - Show employees sorted by full name\r\n2 - Show employees sorted by salary\r\n0 - Exit");
                    int sortEmployeesNumber = ConsoleHelper.readNumber("action");
                    service.getDepartmentSortedEmployees(departmentNumberAnswer, sortEmployeesNumber);
                }
            } else {
                ConsoleHelper.writeMessage("There is no such department. Please try again");
            }
        }
    }

    private static void close(CompanyService service) {
        service.close();
        ConsoleHelper.close();
    }
}
