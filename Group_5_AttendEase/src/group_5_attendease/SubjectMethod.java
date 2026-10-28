package group_5_attendease;

import java.util.Scanner;

public final class SubjectMethod {

    Node_subject head = null;
    Node_subject tail = null;

    Scanner input = new Scanner(System.in);

    // Initialize 5 existing subjects
    public SubjectMethod() {
        addSubject("IT204", "Data Structures and Algorithms",
                "Monday", "08:00", "10:00",
                true, "13:00", "16:00");

        addSubject("ENT101", "Technoprenuership",
                "Tuesday", "09:00", "11:00",
                false, "", "");

        addSubject("IM201", "Information Management",
                "Wednesday", "08:00", "10:00",
                true, "13:00", "15:00");

        addSubject("MATH201", "Discrete Mathematics",
                "Thursday", "10:00", "12:00",
                false, "", "");
        addSubject("NET201", "Networking",
                "Friday", "08:00", "10:00",
                true, "13:00", "16:00");
    }

    // Add a subject attendance list record
    // Add a subject attendance list record
    // Add a subject attendance list record
    // Add a subject attendance list record
    // Add a subject attendance list record
    public void addSubject(String code, String subject, String day,
            String lectureStart, String lectureEnd,
            boolean hasLab, String labStart, String labEnd) {
        Node_subject newNode = new Node_subject(
                code, subject, day, lectureStart, lectureEnd,
                hasLab, labStart, labEnd
        );
        if (head == null) {
            head = tail = newNode;
        } else {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        }
    }

    public void addSubject() {
        System.out.println("\n===== ADD SUBJECT =====");

        System.out.print("Enter subject code: ");
        String code = input.nextLine().trim();

        if (code.isEmpty()) {
            System.out.println("Subject code cannot be empty.");
            return;
        }

        Node_subject current = head;
        while (current != null) {
            if (current.subjectCode != null
                    && current.subjectCode.equalsIgnoreCase(code)) {
                System.out.println("Subject code already exists.");
                return;
            }
            current = current.next;
        }

        System.out.print("Enter subject name: ");
        String name = input.nextLine().trim();

        if (name.isEmpty()) {
            System.out.println("Subject name cannot be empty.");
            return;
        }

        String[] days = {
            "Monday", "Tuesday", "Wednesday", "Thursday",
            "Friday", "Saturday", "Sunday"
        };

        for (int i = 0; i < days.length; i++) {
            System.out.println((i + 1) + ". " + days[i]);
        }

        System.out.print("Choose lecture day: ");
        int dayChoice;

        try {
            dayChoice = Integer.parseInt(input.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("Invalid day choice.");
            return;
        }

        if (dayChoice < 1 || dayChoice > 7) {
            System.out.println("Invalid day choice.");
            return;
        }

        String day = days[dayChoice - 1];

        System.out.print("Lecture start time (HH:mm): ");
        String lecStart = input.nextLine().trim();

        System.out.print("Lecture end time (HH:mm): ");
        String lecEnd = input.nextLine().trim();

        if (!validTime(lecStart) || !validTime(lecEnd)
                || lecStart.compareTo(lecEnd) >= 0) {
            System.out.println("Invalid lecture time range.");
            return;
        }

        System.out.print("Does this subject have a laboratory? (Y/N): ");
        String answer = input.nextLine().trim();

        boolean hasLab;
        if (answer.equalsIgnoreCase("Y")) {
            hasLab = true;
        } else if (answer.equalsIgnoreCase("N")) {
            hasLab = false;
        } else {
            System.out.println("Please enter Y or N.");
            return;
        }

        String labStart = "";
        String labEnd = "";

        if (hasLab) {
            System.out.println("Laboratory day: " + day);

            System.out.print("Laboratory start time (HH:mm): ");
            labStart = input.nextLine().trim();

            System.out.print("Laboratory end time (HH:mm): ");
            labEnd = input.nextLine().trim();

            if (!validTime(labStart) || !validTime(labEnd)
                    || labStart.compareTo(labEnd) >= 0) {
                System.out.println("Invalid laboratory time range.");
                return;
            }
        }

        Node_subject newNode = new Node_subject(
                code, name, day, lecStart, lecEnd,
                hasLab, labStart, labEnd
        );

        insertSubject(newNode);

        System.out.println("\nSubject added successfully!");
        System.out.println("Code: " + code);
        System.out.println("Subject: " + name);
        System.out.println("Lecture: " + day + " "
                + lecStart + " - " + lecEnd);

        if (hasLab) {
            System.out.println("Laboratory: " + day + " "
                    + labStart + " - " + labEnd);
        } else {
            System.out.println("Laboratory: None");
        }
    }

    // Insert at the end of your existing doubly linked list
    private void insertSubject(Node_subject newNode) {
        if (head == null) {
            head = tail = newNode;
        } else {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        }
    }

    // Validate 24-hour HH:mm format
    private boolean validTime(String time) {
        if (!time.matches("\\d{2}:\\d{2}")) {
            return false;
        }

        try {
            java.time.LocalTime.parse(time);
            return true;
        } catch (java.time.format.DateTimeParseException e) {
            return false;
        }
    }

    // View all existing subjects
    // View all existing subjects
    // View all existing subjects
    // View all existing subjects
    // View all existing subjects
    public void viewAllSubjects() {
        if (head == null) {
            System.out.println("No subjects available.");
            return;
        }

        System.out.println("\n======================= SUBJECT LIST =======================");
        System.out.printf("%-4s %-10s %-28s %-12s %-15s %-15s%n",
                "No.", "Code", "Subject", "Day",
                "Lecture", "Laboratory");
        System.out.println("------------------------------------------------------------"
                + "----------------");

        Node_subject current = head;
        int count = 1;

        while (current != null) {
            String code = current.subjectCode == null
                    ? "-" : current.subjectCode;

            String day = current.day == null ? "-" : current.day;

            String lecture = current.lectureStart == null
                    ? "-" : current.lectureStart + "-" + current.lectureEnd;

            String lab = current.hasLab
                    ? current.labStart + "-" + current.labEnd
                    : "None";

            System.out.printf("%-4d %-10s %-28s %-12s %-15s %-15s%n",
                    count, code, current.subject, day, lecture, lab);

            current = current.next;
            count++;
        }

        System.out.println("============================================================"
                + "================");
    }

    // Delete a subject by selecting its number
    // Delete a subject by selecting its number
    // Delete a subject by selecting its number
    // Delete a subject by selecting its number
    // Delete a subject by selecting its number
    public void deleteSubject() {
        if (head == null) {
            System.out.println("\nNo subjects available to delete.");
            return;
        }

        viewAllSubjects();

        System.out.print("\nEnter the subject number(not the code) to delete: ");

        if (!input.hasNextInt()) {
            System.out.println("Invalid input. Please enter a number.");
            input.nextLine();
            return;
        }

        int choice = input.nextInt();
        input.nextLine();

        Node_subject current = head;
        int count = 1;

        while (current != null && count < choice) {
            current = current.next;
            count++;
        }

        if (current == null || choice < 1) {
            System.out.println("Invalid subject number.");
            return;
        }

        // Update previous node or head
        if (current.prev == null) {
            head = current.next;
        } else {
            current.prev.next = current.next;
        }

        // Update next node or tail
        if (current.next == null) {
            tail = current.prev;
        } else {
            current.next.prev = current.prev;
        }

        System.out.println(
                "Subject \"" + current.subject + "\" deleted successfully."
        );
    }

    //  Select a subject by selecting its number
    //  Select a subject by selecting its number
    //  Select a subject by selecting its number
    //  Select a subject by selecting its number
    //  Select a subject by selecting its number
    public void selectSubject() {
        if (head == null) {
            System.out.println("\nNo subjects available.");
            return;
        }

        viewAllSubjects();

        System.out.print("\nEnter the subject number to select: ");

        if (!input.hasNextInt()) {
            System.out.println("Invalid input. Please enter a number.");
            input.nextLine();
            return;
        }

        int choice = input.nextInt();
        input.nextLine(); // Consume newline

        Node_subject current = head;
        int count = 1;

        while (current != null && count < choice) {
            current = current.next;
            count++;
        }

        if (current == null || choice < 1) {
            System.out.println("Invalid subject number.");
            return;
        }

        // Default target list type is Lecture
        String attendanceType = "Lecture";

        // Check if the subject has a lab
        if (current.hasLab) {
            System.out.println("\n--- Choose Attendance List ---");
            System.out.println("1. Lecture (" + current.lectureStart + " - " + current.lectureEnd + ")");
            System.out.println("2. Laboratory (" + current.labStart + " - " + current.labEnd + ")");
            System.out.print("Enter choice (1 or 2): ");

            int listChoice;
            try {
                listChoice = Integer.parseInt(input.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Invalid choice. Defaulting to Lecture.");
                listChoice = 1;
            }

            if (listChoice == 2) {
                attendanceType = "Laboratory";
            }
        } else {
            // If it doesn't have a lab, it automatically skips and uses Lecture
            System.out.println("\nThis subject has no laboratory. Opening Lecture attendance.");
        }

        // Display the selected subject and open its attendance menu
        System.out.println("\n===== SELECTED SUBJECT =====");
        System.out.println("Subject: " + current.subject
                + " (" + current.subjectCode + ")");
        System.out.println("Attendance List: " + attendanceType);

        AttendanceMethod attendanceMethod
                = new AttendanceMethod(current);

        AttendanceMenu menu
                = new AttendanceMenu(attendanceMethod);

        menu.showMenu();
    }

    // Update an existing subject and its schedule
    // Update an existing subject and its schedule
    // Update an existing subject and its schedule
    // Update an existing subject and its schedule
    // Update an existing subject and its schedule
    // Update an existing subject and its schedule
    public void updateSubject() {
        if (head == null) {
            System.out.println("\nNo subjects available to update.");
            return;
        }

        viewAllSubjects();

        System.out.print("\nEnter the subject number to update: ");

        if (!input.hasNextInt()) {
            System.out.println("Invalid input. Please enter a number.");
            input.nextLine();
            return;
        }

        int choice = input.nextInt();
        input.nextLine();

        Node_subject current = head;
        int count = 1;

        while (current != null && count < choice) {
            current = current.next;
            count++;
        }

        if (current == null || choice < 1) {
            System.out.println("Invalid subject number.");
            return;
        }

        System.out.println("\n===== UPDATE SUBJECT =====");
        System.out.println("Current subject: " + current.subject);

        System.out.print("Enter new subject name (Enter to keep): ");
        String name = input.nextLine().trim();

        if (!name.isEmpty()) {
            current.subject = name;
        }

        System.out.println("\nCurrent day: "
                + (current.day == null ? "Not assigned" : current.day));
        System.out.print("Change lecture day? (Y/N): ");
        String changeDay = input.nextLine().trim();

        if (changeDay.equalsIgnoreCase("Y")) {
            String[] days = {
                "Monday", "Tuesday", "Wednesday", "Thursday",
                "Friday", "Saturday", "Sunday"
            };

            for (int i = 0; i < days.length; i++) {
                System.out.println((i + 1) + ". " + days[i]);
            }

            System.out.print("Choose new day: ");

            if (!input.hasNextInt()) {
                System.out.println("Invalid day choice.");
                input.nextLine();
                return;
            }

            int dayChoice = input.nextInt();
            input.nextLine();

            if (dayChoice < 1 || dayChoice > 7) {
                System.out.println("Invalid day choice.");
                return;
            }

            current.day = days[dayChoice - 1];
        }

        System.out.print("Change lecture time? (Y/N): ");
        String changeLecture = input.nextLine().trim();

        if (changeLecture.equalsIgnoreCase("Y")) {
            System.out.print("New lecture start (HH:mm): ");
            String start = input.nextLine().trim();

            System.out.print("New lecture end (HH:mm): ");
            String end = input.nextLine().trim();

            if (!validTime(start) || !validTime(end)
                    || start.compareTo(end) >= 0) {
                System.out.println("Invalid lecture time range.");
                return;
            }

            current.lectureStart = start;
            current.lectureEnd = end;
        }

        System.out.print("Change laboratory details? (Y/N): ");
        String changeLab = input.nextLine().trim();

        if (changeLab.equalsIgnoreCase("Y")) {
            System.out.print("Does this subject have a lab? (Y/N): ");
            String labAnswer = input.nextLine().trim();

            if (labAnswer.equalsIgnoreCase("Y")) {
                System.out.print("Laboratory start (HH:mm): ");
                String start = input.nextLine().trim();

                System.out.print("Laboratory end (HH:mm): ");
                String end = input.nextLine().trim();

                if (!validTime(start) || !validTime(end)
                        || start.compareTo(end) >= 0) {
                    System.out.println("Invalid laboratory time range.");
                    return;
                }

                current.hasLab = true;
                current.labStart = start;
                current.labEnd = end;

            } else if (labAnswer.equalsIgnoreCase("N")) {
                current.hasLab = false;
                current.labStart = "";
                current.labEnd = "";

            } else {
                System.out.println("Please enter Y or N.");
                return;
            }
        }

        System.out.println("\nSubject updated successfully!");
    }

}
