package commLogic;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class MessageStorage {
	private String filePath;
	
	public MessageStorage(String filePath) {
		this.filePath = filePath;
	}
	
	public ArrayList<Message> loadMessages() {
        ArrayList<Message> messages = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                messages.add(Message.fromCSV(line));
            }
        } catch (IOException e) {
            System.err.println("Error reading messages from file: " + e.getMessage());
        }
        return messages;
    }

    // Save messages to CSV file
	public void saveMessages(List<Message> newMessages) {
	    ArrayList<Message> existingMessages = loadMessages(); // Load existing messages
	    for (Message newMessage : newMessages) {
	        boolean isDuplicate = existingMessages.stream().anyMatch(existingMessage ->
	            existingMessage.getRecipient().equals(newMessage.getRecipient()) &&
	            existingMessage.getContent().equals(newMessage.getContent()) &&
	            existingMessage.getCategory() == newMessage.getCategory()
	        );
	        if (!isDuplicate) {
	            try (FileWriter writer = new FileWriter(filePath, true)) {
	                writer.write(newMessage.toCSV());
	                writer.write("\n");
	            } catch (IOException e) {
	                System.err.println("Error writing to CSV file: " + e.getMessage());
	            }
	        }
	   }
	}
}
