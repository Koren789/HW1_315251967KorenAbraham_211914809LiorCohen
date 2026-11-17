import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ArrayList<Message> messages = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        // הוספת לפחות 2 אובייקטים מכל סוג ידנית
        try {
            messages.add(new BoardMessage("Dana", "Important update regarding tomorrow's Java lecture.", new Date(), PriorityType.URGENT, "Studies"));
            messages.add(new BoardMessage("Admin", "Library closing hours for the holiday.", "General"));

            ArrayList<File> files1 = new ArrayList<>();
            files1.add(new File("HW1_Solution", "pdf"));
            files1.add(new File("Diagram", "png"));
            messages.add(new EmailMessage("Noa", "Attached is our homework submission.", new Date(), "Project Submission", files1));
            messages.add(new EmailMessage("Rina", "Welcome to Object Oriented Programming in Java!", "Course Welcome"));

            messages.add(new SmsMessage("Maayan", "Don't forget to push your code to GitHub!", new Date(), "0501234567"));
            messages.add(new SmsMessage("Shay", "Class moved to room 204.", "0529876543"));
        } catch (IllegalArgumentException | InvalidPhoneNumberException e) {
            System.out.println("Initialization Error: " + e.getMessage());
        }

        boolean running = true;
        while (running) {
            System.out.println("\n=== Messaging System Menu ===");
            System.out.println("1. Add a new message");
            System.out.println("2. Delete a message (by ID)");
            System.out.println("3. Print all messages");
            System.out.println("4. Count messages containing specific words");
            System.out.println("5. Print digital messages only");
            System.out.println("6. Print previews of all messages"); // אופציה נוספת לבחירתנו
            System.out.println("0. Exit");
            System.out.print("Choose an option: ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    addNewMessage(scanner, messages);
                    break;
                case "2":
                    deleteMessage(scanner, messages);
                    break;
                case "3":
                    System.out.println("\n--- All Messages ---");
                    if (messages.isEmpty()) {
                        System.out.println("No messages in the system.");
                    } else {
                        for (Message m : messages) {
                            System.out.println(m);
                        }
                    }
                    break;
                case "4":
                    countMessagesWithWords(scanner, messages);
                    break;
                case "5":
                    System.out.println("\n--- Digital Messages Only ---");
                    for (Message m : messages) {
                        if (m instanceof IDigital) {
                            System.out.println(m);
                            System.out.println("   -> Method: " + ((IDigital) m).printCommunicationMethod());
                        }
                    }
                    break;
                case "6":
                    System.out.println("\n--- Message Previews ---");
                    for (Message m : messages) {
                        System.out.println(m.generatePreview());
                    }
                    break;
                case "0":
                    running = false;
                    System.out.println("Exiting system. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
        scanner.close();
    }

    private static void addNewMessage(Scanner scanner, ArrayList<Message> messages) {
        System.out.println("Select message type: 1. BoardMessage | 2. EmailMessage | 3. SmsMessage");
        String type = scanner.nextLine();

        try {
            System.out.print("Enter sender name: ");
            String sender = scanner.nextLine();
            System.out.print("Enter message content: ");
            String content = scanner.nextLine();

            if (type.equals("1")) {
                System.out.print("Enter category: ");
                String category = scanner.nextLine();
                System.out.print("Enter priority (REGULAR / URGENT / LOW): ");
                PriorityType priority = PriorityType.valueOf(scanner.nextLine().trim().toUpperCase());
                messages.add(new BoardMessage(sender, content, new Date(), priority, category));
                System.out.println("Board message added successfully!");

            } else if (type.equals("2")) {
                System.out.print("Enter email subject: ");
                String subject = scanner.nextLine();
                EmailMessage email = new EmailMessage(sender, content, subject);

                System.out.print("Do you want to add an attachment? (yes/no): ");
                if (scanner.nextLine().trim().equalsIgnoreCase("yes")) {
                    System.out.print("Enter file name: ");
                    String fName = scanner.nextLine();
                    System.out.print("Enter file type (e.g., pdf, txt): ");
                    String fType = scanner.nextLine();
                    email.addAttachment(new File(fName, fType));
                }
                messages.add(email);
                System.out.println("Email message added successfully!");

            } else if (type.equals("3")) {
                System.out.print("Enter recipient phone number (10 digits starting with 05): ");
                String phone = scanner.nextLine();
                messages.add(new SmsMessage(sender, content, phone));
                System.out.println("SMS message added successfully!");

            } else {
                System.out.println("Unknown message type.");
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Input Error: " + e.getMessage());
        } catch (InvalidPhoneNumberException e) {
            System.out.println("Phone Validation Error: " + e.getMessage());
        }
    }

    private static void deleteMessage(Scanner scanner, ArrayList<Message> messages) {
        System.out.print("Enter Message ID to delete: ");
        try {
            int id = Integer.parseInt(scanner.nextLine());
            boolean removed = false;
            for (int i = 0; i < messages.size(); i++) {
                if (messages.get(i).getMessageId() == id) {
                    messages.remove(i);
                    removed = true;
                    System.out.println("Message ID " + id + " deleted successfully.");
                    break;
                }
            }
            if (!removed) {
                System.out.println("Message with ID " + id + " not found.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid ID format. Please enter a number.");
        }
    }

    private static void countMessagesWithWords(Scanner scanner, ArrayList<Message> messages) {
        System.out.print("Enter words to search separated by spaces: ");
        String line = scanner.nextLine();
        String[] parts = line.trim().split("\\s+");
        ArrayList<String> words = new ArrayList<>();
        for (String w : parts) {
            if (!w.isEmpty()) {
                words.add(w);
            }
        }

        int count = 0;
        for (Message m : messages) {
            if (m.find(words)) {
                count++;
            }
        }
        System.out.println("Number of messages containing at least one of the words: " + count);
    }
}
