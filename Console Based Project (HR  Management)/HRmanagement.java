import java.util.*;

class Employee {
    public String ID;
    public String name;
    public String department;
    public String email;
    public String password;
    public int attendanceCount = 0;

    public Employee(String ID, String name, String department, String email, String password) {
        this.ID = ID;
        this.name = name;
        this.department = department;
        this.email = email;
        this.password = password;
    }
}

public class HRmanagement {
    static private String AdminName = "HRMANAGER";
    static private String AdminPassword = "123456";

    static List<Employee> employeeList = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n==== Welcome to Management System ====");
            System.out.println("1. Admin Login");
            System.out.println("2. Employee Sign Up");
            System.out.println("3. Employee Sign In");
            System.out.println("4. Exit");
            System.out.print("Enter your option: ");

            int choice = sc.nextInt();
            sc.nextLine(); 

            switch (choice) {
                case 1:
                    adminLogin();
                    break;
                case 2:
                    employeeSignUp();
                    break;
                case 3:
                    employeeSignIn();
                    break;
                case 4:
                    System.out.println("Exiting system...");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    static void adminLogin() {
        System.out.print("Enter the Admin Username: ");
        String name = sc.nextLine();
        System.out.print("Enter the Admin Password: ");
        String pass = sc.nextLine();

        if (AdminName.equals(name) && AdminPassword.equals(pass)) {
            System.out.println("Logged in successfully");
            adminDashboard();
        } else {
            System.out.println("Invalid Admin credentials");
        }
    }

    static void adminDashboard() {
        while (true) {
            System.out.println("---- Admin Dashboard -----");
            System.out.println("1. Add Employee");
            System.out.println("2. Delete Employee");
            System.out.println("3. View Employees");
            System.out.println("4. Generate Payroll");
            System.out.println("5. Logout");
            System.out.print("Enter your option: ");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    addEmployee();
                    break;
                case 2:
                    System.out.println("Enter Employee id to delete : ");
                    String id = sc.nextLine();
                    deleteEmployee(id);
                    break;
                case 3:
                    viewEmployees();
                    break;
                case 4:
                    generatePayroll();
                    break;
                case 5:
                    System.out.println("Logged out from Admin dashboard.");
                    return;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    static void addEmployee() {
        System.out.print("Enter Employee ID: ");
        String ID = sc.nextLine();
        System.out.print("Enter Employee Name: ");
        String name = sc.nextLine();
        System.out.print("Enter Employee Department: ");
        String department = sc.nextLine();
        System.out.print("Enter Employee Email: ");
        String email = sc.nextLine();
        System.out.print("Enter Employee Password: ");
        String password = sc.nextLine();

        Employee e = new Employee(ID, name, department, email, password);
        employeeList.add(e);
        System.out.println("Employee added successfully.");
    }

    static void viewEmployees() {
        if (employeeList.isEmpty()) {
            System.out.println("No employees found.");
            return;
        }

        System.out.println("Employee List:");
        for (Employee e : employeeList) {
            System.out.println(e.ID + " " + e.name + " " + e.department + " " + e.email + " " + e.attendanceCount);
        }
    }

    static void generatePayroll() {
        System.out.println("Payroll:");
        for (Employee e : employeeList) {
            double salary = e.attendanceCount * 1000;
            System.out.println("Employee: " + e.name + " | Salary: ₹" + salary);
        }
    }

    static void employeeSignUp() {
        System.out.print("Enter your ID: ");
        String ID = sc.nextLine();
        System.out.print("Enter your name: ");
        String name = sc.nextLine();
        System.out.print("Enter your department: ");
        String dept = sc.nextLine();
        System.out.print("Enter your email: ");
        String email = sc.nextLine();
        System.out.print("Set your password: ");
        String password = sc.nextLine();

        for (Employee e : employeeList) {
            if (e.email.equals(email)) {
                System.out.println("Email already registered.");
                return;
            }
        }

        Employee emp = new Employee(ID, name, dept, email, password);
        employeeList.add(emp);
        System.out.println("Sign up successful.");
    }

    static void employeeSignIn() {
        System.out.print("Enter email: ");
        String email = sc.nextLine();
        System.out.print("Enter password: ");
        String password = sc.nextLine();

        for (Employee e : employeeList) {
            if (e.email.equals(email) && e.password.equals(password)) {
                System.out.println("Login successful.");
                employeeDashboard(e);
                return;
            }
        }
        System.out.println("Invalid credentials.");
    }

    static void employeeDashboard(Employee emp) {
        while (true) {
            System.out.println("------Employee Dashboard -----");
            System.out.println("Welcome, " + emp.name);
            System.out.println("1. Mark Attendance");
            System.out.println("2. View My Profile");
            System.out.println("3. View Salary");
            System.out.println("4. Logout");
            System.out.print("Enter your choice: ");
            int ch = sc.nextInt();
            sc.nextLine();

            switch (ch) {
                case 1:
                    emp.attendanceCount++;
                    System.out.println("Attendance marked. Total: " + emp.attendanceCount);
                    break;
                case 2:
                    System.out.println("ID: " + emp.ID);
                    System.out.println("Name: " + emp.name);
                    System.out.println("Department: " + emp.department);
                    System.out.println("Email: " + emp.email);
                    System.out.println("Attendance: " + emp.attendanceCount);
                    break;
                case 3:
                    System.out.println("Your salary: Rs." + (emp.attendanceCount * 1000));
                    break;
                case 4:
                    System.out.println("Logged out.");
                    return;
                default:
                    System.out.println("Invalid option.");
            }
        }
    }

    static void deleteEmployee(String id) {
        for (int i = 0; i < employeeList.size(); i++) {
            if (employeeList.get(i).ID.equals(id)) {
                employeeList.remove(i);
                System.out.println("Employee with ID " + id + " removed successfully.");
                return;
            }
        }
        System.out.println("Employee with ID " + id + " not found.");
    }
}
