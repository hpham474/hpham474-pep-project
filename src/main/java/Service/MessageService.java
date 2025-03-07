package Service;

import Model.Message;
import DAO.MessageDAO;

import java.util.List;

public class MessageService {
    public MessageDAO messageDAO;

    public MessageService() {
        messageDAO = new MessageDAO();
    }

    public MessageService(MessageDAO messageDAO) {
        this.messageDAO = messageDAO;
    }

    public List<Message> getAllMessages() {
        return messageDAO.getAllMessages();
    }

    public List<Message> getMessageByUser(int userID) {
        return messageDAO.getMessagesByUser(userID);
    }

    public Message getMessageByID(int id) {
        return messageDAO.getMessageById(id);
    }

    public Message insertMessage(Message message) {
        if (message.getMessage_text().length() <= 255 &&
            message.getMessage_text() != "") {
                return messageDAO.insertMessage(message);
            }

        return null;
    }

    public Message deleteMessage(int id, Message message) {
        return messageDAO.deleteMessage(id, message);
    }

    public Message updateMessage(int id, Message message) {
        return messageDAO.updateMessage(id, message);
    }
}