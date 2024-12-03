package commLogic;

public class Message {
	private String recipUser;
	private String sendUser;
	private String content;
	private MessageCats category;
	
	public Message(String sendUser, String recipUser, String content, MessageCats category) {
		this.recipUser = recipUser;
		this.sendUser = sendUser;
		this.content = content;
		this.category = category;
	}

	public String getSender() {
        return sendUser;
    }
    public String getRecipient() {
        return recipUser;
    }

    public String getContent() {
        return content;
    }
    public MessageCats getCategory() {
        return category; // Getter for the category
    }

 // Convert Message to CSV format

    public String toCSV() {

        return String.join(",", sendUser, recipUser, content, category.toString());

    }


    // Static method to create a Message from CSV line
    public static Message fromCSV(String csvLine) {
        String[] parts = csvLine.split(",");
        if (parts.length != 4) {
            throw new IllegalArgumentException("Invalid CSV line for Message: " + csvLine);
        }
        String sender = parts[0];
        String recipient = parts[1];
        String content = parts[2];
        MessageCats category = MessageCats.valueOf(parts[3]);
        return new Message(sender, recipient, content, category);
    }
    
    @Override
    public String toString() {
        return sendUser + " [" + category + "]: " + content; // Include category in the string representation
    }
}
