import java.util.ArrayList;
import java.util.Date;

public abstract class Message {
    private static int nextId = 1;
    protected int messageId; // שדה נוסף משותף לכל ההודעות (ללא set כי מזהה נקבע פעם אחת)
    protected String sender;
    protected String content;
    protected Date sendDate;

    // בנאי מלא
    public Message(String sender, String content, Date sendDate) throws IllegalArgumentException {
        setSender(sender);
        setContent(content);
        setSendDate(sendDate);
        this.messageId = nextId++;
    }

    public Message(String sender, String content) throws IllegalArgumentException {
        this(sender, content, new Date());
    }

    public int getMessageId() {
        return messageId;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) throws IllegalArgumentException {
        if (sender == null || sender.trim().isEmpty()) {
            throw new IllegalArgumentException("Sender cannot be empty.");
        }
        this.sender = sender;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) throws IllegalArgumentException {
        if (content == null || content.trim().isEmpty()) {
            throw new IllegalArgumentException("Content cannot be empty.");
        }
        this.content = content;
    }

    public Date getSendDate() {
        return sendDate;
    }

    public void setSendDate(Date sendDate) throws IllegalArgumentException {
        if (sendDate == null) {
            throw new IllegalArgumentException("Send date cannot be null.");
        }
        this.sendDate = sendDate;
    }

    public boolean find(ArrayList<String> words) {
        if (words == null || words.isEmpty()) {
            return false;
        }
        for (String word : words) {
            if (word != null && !word.isEmpty() && content.toLowerCase().contains(word.toLowerCase())) {
                return true;
            }
        }
        return false;
    }

   
    public int getWordCount() {
        return content.trim().split("\\s+").length;
    }

    public abstract String generatePreview();

    @Override
    public String toString() {
        return "ID: " + messageId + " | From: " + sender + " | Date: " + sendDate + " | Content: " + content;
    }
}
