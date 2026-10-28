package group_5_attendease;

import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class AttendanceMethod {

    private final Node_subject subject;
    private final Scanner input = new Scanner(System.in);

    public AttendanceMethod(Node_subject subject) {
        this.subject = subject;
    }

    // Add attendance record
    // Add attendance record
    // Add attendance record
    // Add attendance record
    // Add attendance record
    public void addAttendance() {
        System.out.println("\n===== ADD ATTENDANCE =====");

        System.out.print("Enter student ID: ");
        String id = input.nextLine().trim();

        if (id.isEmpty()) {
            System.out.println("Student ID cannot be empty.");
            return;
        }

        if (findById(id) != null) {
            System.out.println("This student already has an attendance record.");
            return;
        }

        System.out.print("Enter student name: ");
        String name = input.nextLine().trim();

        if (name.isEmpty()) {
            System.out.println("Student name cannot be empty.");
            return;
        }

        // Automatically get the current date and time
        java.time.LocalDateTime now = java.time.LocalDateTime.now();

        // Use the selected subject's lecture schedule
        java.time.LocalTime start
                = java.time.LocalTime.parse(subject.lectureStart);

        java.time.LocalTime end
                = java.time.LocalTime.parse(subject.lectureEnd);

        java.time.LocalTime currentTime = now.toLocalTime();

        // Determine attendance status automatically
        String status;

        if (currentTime.isBefore(start)) {
            status = "Present";
        } else if (!currentTime.isAfter(start.plusMinutes(15))
                && !currentTime.isAfter(end)) {
            status = "Present";
        } else if (currentTime.isBefore(end)) {
            status = "Late";
        } else {
            status = "Late";
        }

        // Automatically store time and status
        Node_attendance newNode
                = new Node_attendance(id, name, now, status);

        if (subject.attendanceHead == null) {
            subject.attendanceHead = subject.attendanceTail = newNode;
        } else {
            subject.attendanceTail.next = newNode;
            newNode.prev = subject.attendanceTail;
            subject.attendanceTail = newNode;
        }

        System.out.println("\nAttendance recorded successfully!");
        System.out.println("Student ID: " + id);
        System.out.println("Student Name: " + name);
        System.out.println("Attendance Time: "
                + now.format(
                        java.time.format.DateTimeFormatter
                                .ofPattern("yyyy-MM-dd HH:mm:ss")));
        System.out.println("Status: " + status);
    }

    // Update attendance record using student ID
    // Update attendance record using student ID
    // Update attendance record using student ID
    // Update attendance record using student ID
    // Update attendance record using student ID
    public void updateAttendance() {
        Node_attendance current = findRecordByPrompt();

        if (current == null) {
            return;
        }

        System.out.println("Press Enter to keep the current value.");

        System.out.print("New student name (" + current.studentName + "): ");
        String name = input.nextLine().trim();

        if (!name.isEmpty()) {
            current.studentName = name;
        }

        System.out.print("New attendance time (HH:mm) (" + current.attendanceTime.toLocalTime() + "): ");
        String time = input.nextLine().trim();

        if (!time.isEmpty()) {
            if (!validTime(time)) {
                System.out.println("Invalid time. Update cancelled for time.");
            } else {
                // Parse the string into LocalTime and combine it with the current record's date
                java.time.LocalTime newLocalTime = java.time.LocalTime.parse(time);
                current.attendanceTime = java.time.LocalDateTime.of(current.attendanceTime.toLocalDate(), newLocalTime);
                System.out.println("Attendance time updated.");
            }
        }

        System.out.println("Attendance record updated.");
    }

    // Delete attendance record using student ID
    // Delete attendance record using student ID
    // Delete attendance record using student ID
    // Delete attendance record using student ID
    // Delete attendance record using student ID
    public void deleteAttendance() {
        Node_attendance current = findRecordByPrompt();

        if (current == null) {
            return;
        }

        if (current.prev == null) {
            subject.attendanceHead = current.next;
        } else {
            current.prev.next = current.next;
        }

        if (current.next == null) {
            subject.attendanceTail = current.prev;
        } else {
            current.next.prev = current.prev;
        }

        System.out.println("Attendance record deleted.");
    }

    // Search by student ID or name
    // Search by student ID or name
    // Search by student ID or name
    // Search by student ID or name
    // Search by student ID or name
    public void searchAttendance() {
        System.out.print("Enter student ID or name to search: ");
        String search = input.nextLine().trim();

        if (search.isEmpty()) {
            System.out.println("Search cannot be empty.");
            return;
        }

        Node_attendance current = subject.attendanceHead;
        boolean found = false;

        while (current != null) {
            if (current.studentId.equalsIgnoreCase(search)
                    || current.studentName.toLowerCase()
                            .contains(search.toLowerCase())) {
                printRecord(current);
                found = true;
            }

            current = current.next;
        }

        if (!found) {
            System.out.println("No matching attendance record found.");
        }
    }

    // Display records from head to tail
    // Display records from head to tail
    // Display records from head to tail
    // Display records from head to tail
    // Display records from head to tail
    public void displayForward() {
        if (subject.attendanceHead == null) {
            System.out.println("No attendance records available.");
            return;
        }

        printHeader();

        Node_attendance current = subject.attendanceHead;
        int count = 1;

        while (current != null) {
            printRow(count++, current);
            current = current.next;
        }
    }

    // Display records from tail to head
    // Display records from tail to head
    // Display records from tail to head
    // Display records from tail to head
    // Display records from tail to head
    public void displayBackward() {
        if (subject.attendanceTail == null) {
            System.out.println("No attendance records available.");
            return;
        }

        printHeader();

        Node_attendance current = subject.attendanceTail;
        int count = 1;

        while (current != null) {
            printRow(count++, current);
            current = current.prev;
        }
    }

    // Sort records alphabetically by student name
    // Sort records alphabetically by student name
    // Sort records alphabetically by student name
    // Sort records alphabetically by student name
    // Sort records alphabetically by student name
    public void sortByStudentName() {
        sortRecords(true);
    }

    // Sort records by attendance time, earliest first
    // Sort records by attendance time, earliest first
    // Sort records by attendance time, earliest first
    // Sort records by attendance time, earliest first
    // Sort records by attendance time, earliest first
    public void sortByAttendanceTime() {
        sortRecords(false);
    }

    // Bubble sort by swapping record data, preserving node links
    // Bubble sort by swapping record data, preserving node links
    // Bubble sort by swapping record data, preserving node links
    // Bubble sort by swapping record data, preserving node links
    // Bubble sort by swapping record data, preserving node links
    private void sortRecords(boolean byName) {
        if (subject.attendanceHead == null
                || subject.attendanceHead.next == null) {
            System.out.println("Not enough records to sort.");
            return;
        }

        boolean swapped;

        do {
            swapped = false;
            Node_attendance current = subject.attendanceHead;

            while (current.next != null) {
                Node_attendance next = current.next;

                int comparison;

                if (byName) {
                    comparison = current.studentName.compareToIgnoreCase(
                            next.studentName);
                } else {
                    comparison = current.attendanceTime.compareTo(
                            next.attendanceTime);
                }

                if (comparison > 0) {
                    String tempId = current.studentId;
                    String tempName = current.studentName;
                    java.time.LocalDateTime tempTime = current.attendanceTime;
                    String tempStatus = current.status;

                    current.studentId = next.studentId;
                    current.studentName = next.studentName;
                    current.attendanceTime = next.attendanceTime;
                    current.status = next.status;

                    next.studentId = tempId;
                    next.studentName = tempName;
                    next.attendanceTime = tempTime;
                    next.status = tempStatus;

                    swapped = true;
                }

                current = current.next;
            }
        } while (swapped);

        System.out.println(byName
                ? "Sorted alphabetically by student name."
                : "Sorted by attendance time.");

        displayForward();
    }

    // Count attendance records
    // Count attendance records
    // Count attendance records
    // Count attendance records
    // Count attendance records
    public void countAttendance() {
        int count = 0;
        Node_attendance current = subject.attendanceHead;

        while (current != null) {
            count++;
            current = current.next;
        }

        System.out.println("Total attendance records: " + count);
    }

    // Find a record by student ID
    // Find a record by student ID
    // Find a record by student ID
    // Find a record by student ID
    // Find a record by student ID
    private Node_attendance findById(String id) {
        Node_attendance current = subject.attendanceHead;

        while (current != null) {
            if (current.studentId.equalsIgnoreCase(id)) {
                return current;
            }

            current = current.next;
        }

        return null;
    }

    private Node_attendance findRecordByPrompt() {
        System.out.print("Enter student ID: ");
        String id = input.nextLine().trim();

        Node_attendance current = findById(id);

        if (current == null) {
            System.out.println("Attendance record not found.");
        }

        return current;
    }

    private boolean validTime(String time) {
        try {
            LocalTime.parse(time);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    private void printHeader() {
        System.out.println("\n====================== ATTENDANCE RECORDS ======================");
        System.out.printf("%-5s %-15s %-28s %-12s %-10s%n",
                "No.", "Student ID", "Student Name", "Time", "Status");
        System.out.println("----------------------------------------------------------------");
    }

    private void printRow(int number, Node_attendance record) {
        // Format time to show just HH:mm:ss or whatever format you prefer
        String formattedTime = record.attendanceTime.format(
                java.time.format.DateTimeFormatter.ofPattern("HH:mm:ss")
        );

        System.out.printf("%-5d %-15s %-28s %-12s %-10s%n",
                number, record.studentId,
                record.studentName, formattedTime, record.status);
    }

    private void printRecord(Node_attendance record) {
        String formattedTime = record.attendanceTime.format(
                java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
        );

        System.out.println("-----------------------------------");
        System.out.println("Student ID: " + record.studentId);
        System.out.println("Student Name: " + record.studentName);
        System.out.println("Attendance Time: " + formattedTime);
        System.out.println("Status: " + record.status);
        System.out.println("-----------------------------------");
    }
}
