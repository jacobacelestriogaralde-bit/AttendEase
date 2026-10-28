package group_5_attendease;

import java.util.Scanner;

public class Group_5_AttendEase {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
            int choice = 0;
            SubjectMethod subjects = new SubjectMethod();
            do {
                System.out.println("\n===== AttendEase =====");
                System.out.println("1. View all subjects");
                System.out.println("2. Create a subject attendance list");
                System.out.println("3. Delete a subject attendance list");
                System.out.println("4. Update a subject attendance list");
                System.out.println("5. Select a subject attendance list");
                System.out.println("6. Exit");
                System.out.print("Enter your choice: ");
                
                if (!input.hasNextInt()) {
                    System.out.println("Please enter a valid number.");
                    input.nextLine();
                    continue;
                }
                
                choice = input.nextInt();
                input.nextLine();
                
                switch(choice) {
                    case 1 -> {System.out.println("View all subjects selected.");
                    subjects.viewAllSubjects();}
                    // Call viewAllSubjects()
                        
                    case 2 -> subjects.addSubject();
                    // Call createSubject()
                        
                    case 3 -> subjects.deleteSubject();
                    // Call deleteSubject()
                    
                    case 4 -> subjects.updateSubject();
                    // Call selectSubject()
                    
                    case 5 -> subjects.selectSubject();
                    // Call selectSubject()
                    
                    case 6 -> System.out.println("Exiting AttendEase...");
                        
                    default -> System.out.println("Invalid choice. Try again.");
                }
                
            } while (choice != 6);
        
    }
}
