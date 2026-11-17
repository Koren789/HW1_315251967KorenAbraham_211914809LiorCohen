import java.util.Date;

public class BoardMessage extends Message {
    private PriorityType priority;
    private String category;

    public BoardMessage(String sender, String content, Date sendDate, PriorityType priority, String category) throws IllegalArgumentException {
        super(sender, content, sendDate);
        setPriority(priority);
        setCategory(category);
    }

    public BoardMessage(String sender, String content, String category) throws IllegalArgumentException {
        this(sender, content, new Date(), PriorityType.REGULAR, category);
    }

    public PriorityType getPriority() {
        return priority;
    }

    public void setPriority(PriorityType priority) throws IllegalArgumentException {
        if (priority == null) {
            throw new IllegalArgumentException("Priority cannot be null.");
        }
        this.priority = priority;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) throws IllegalArgumentException {
        if (category == null || category.trim().isEmpty()) {
            throw new IllegalArgumentException("Category cannot be empty.");
        }
        this.category = category;
    }

    public boolean isUrgent() {
        return this.priority == PriorityType.URGENT;
    }

    @Override
    public String generatePreview() {
        String shortContent = content.length() > 15 ? content.substring(0, 15) + "..." : content;
        return "[Board] " + sender + ": " + shortContent;
    }

    @Override
    public String toString() {
        return "[Board Message] " + super.toString() + " | Priority: " + priority + " | Category: " + category;
    }
}
