package group_5_attendease;

import java.util.Scanner;

public class AttendanceMenu {

    private final Scanner input = new Scanner(System.in);
    private final AttendanceMethod attendanceMethod;

    public AttendanceMenu(AttendanceMethod attendanceMethod) {
        this.attendanceMethod = attendanceMethod;
    }

    public void showMenu() {
        int choice = 0;

        do {
            System.out.println("\n===== ATTENDANCE MENU =====");
            System.out.println("1. Add attendance record");
            System.out.println("2. Update attendance record");
            System.out.println("3. Delete attendance record");
            System.out.println("4. Search attendance record");
            System.out.println("5. Display records forward");
            System.out.println("6. Display records backward");
            System.out.println("7. Sort by student name");
            System.out.println("8. Sort by attendance time");
            System.out.println("9. Count attendance records");
            System.out.println("10. Return to main menu");
            System.out.println("11. Exit");
            System.out.print("Enter your choice: ");

            if (!input.hasNextInt()) {
                System.out.println("Please enter a valid number.");
                input.nextLine();
                continue;
            }

            choice = input.nextInt();
            input.nextLine();

            switch (choice) {
                case 1 ->
                    attendanceMethod.addAttendance();
                case 2 ->
                    attendanceMethod.updateAttendance();
                case 3 ->
                    attendanceMethod.deleteAttendance();
                case 4 ->
                    attendanceMethod.searchAttendance();
                case 5 ->
                    attendanceMethod.displayForward();
                case 6 ->
                    attendanceMethod.displayBackward();
                case 7 ->
                    attendanceMethod.sortByStudentName();
                case 8 ->
                    attendanceMethod.sortByAttendanceTime();
                case 9 ->
                    attendanceMethod.countAttendance();
                case 10 ->
                    System.out.println("Returning to main menu...");
                case 11 ->
                    System.out.println("Exiting AttendEase...");
                default ->
                    System.out.println("Invalid choice. Try again.");
            }

        } while (choice != 10 && choice != 11);
    }
}
