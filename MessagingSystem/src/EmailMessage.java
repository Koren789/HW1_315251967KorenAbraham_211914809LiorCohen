import java.util.ArrayList;
import java.util.Date;

public class EmailMessage extends Message implements IDigital {
    private String subject;
    private ArrayList<File> attachments;

    // בנאי מלא
    public EmailMessage(String sender, String content, Date sendDate, String subject, ArrayList<File> attachments) throws IllegalArgumentException {
        super(sender, content, sendDate);
        setSubject(subject);
        if (attachments != null) {
            this.attachments = new ArrayList<>(attachments);
        } else {
            this.attachments = new ArrayList<>();
        }
    }

    // בנאי חלקי ללא תאריך וללא מערך קבצים
    public EmailMessage(String sender, String content, String subject) throws IllegalArgumentException {
        this(sender, content, new Date(), subject, new ArrayList<>());
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) throws IllegalArgumentException {
        if (subject == null || subject.trim().isEmpty()) {
            throw new IllegalArgumentException("Subject cannot be empty.");
        }
        this.subject = subject;
    }

    public ArrayList<File> getAttachments() {
        return attachments;
    }

    public void addAttachment(File file) throws IllegalArgumentException {
        if (file == null) {
            throw new IllegalArgumentException("Cannot add a null file.");
        }
        attachments.add(file);
    }

    public void removeAttachment(File file) throws AttachmentException {
        if (file == null || !attachments.contains(file)) {
            throw new AttachmentException("Attachment '" + (file != null ? file.toString() : "null") + "' was not found in the email.");
        }
        // מחיקת כל הקבצים התואמים לקובץ שהתקבל
        while (attachments.contains(file)) {
            attachments.remove(file);
        }
    }

    @Override
    public String printCommunicationMethod() {
        return "Sent via Email Server";
    }

    @Override
    public String generatePreview() {
        return "[Email] Subject: " + subject + " | From: " + sender;
    }

    @Override
    public String toString() {
        return "[Email Message] " + super.toString() + " | Subject: " + subject + " | Attachments: " + attachments;
    }
}
