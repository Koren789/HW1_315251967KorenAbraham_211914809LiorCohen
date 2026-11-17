import java.util.Date;

public class SmsMessage extends Message implements IDigital {
    private String phoneNumber;

    public SmsMessage(String sender, String content, Date sendDate, String phoneNumber)
            throws IllegalArgumentException, InvalidPhoneNumberException {
        super(sender, content, sendDate);
        setPhoneNumber(phoneNumber);
    }

    public SmsMessage(String sender, String content, String phoneNumber)
            throws IllegalArgumentException, InvalidPhoneNumberException {
        this(sender, content, new Date(), phoneNumber);
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) throws InvalidPhoneNumberException {
        // בדיקת תקינות: 10 ספרות המתחילות ב-05
        if (phoneNumber == null || !phoneNumber.matches("^05\\d{8}$")) {
            throw new InvalidPhoneNumberException("Invalid phone number: " + phoneNumber + ". Must be 10 digits starting with 05.");
        }
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String printCommunicationMethod() {
        return "Sent via Cellular Network (SMS)";
    }

    @Override
    public String generatePreview() {
        return "[SMS] To: " + phoneNumber + " | From: " + sender;
    }

    @Override
    public String toString() {
        return "[SMS Message] " + super.toString() + " | Recipient Phone: " + phoneNumber;
    }
}
