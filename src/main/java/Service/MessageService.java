package Service;

import Model.Message;
import DAO.MessageDAO;

import java.util.List;

public class MessageService {
    private MessageDAO messageDAO;

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
        AccountService accountService = new AccountService();

        if (message.getMessage_text().length() <= 255 &&
            !message.getMessage_text().equals("") &&
            accountService.getAccountByUserID(message.getPosted_by()) != null) {
                return messageDAO.insertMessage(message);
            }

        return null;
    }

    public Message deleteMessage(int id) {
        Message message = messageDAO.getMessageById(id);

        if (message == null) {
            return null;
        }

        return messageDAO.deleteMessage(message);
    }

    public Message updateMessage(int id, String messageText) {
        Message message = messageDAO.getMessageById(id);

        if (message == null || messageText.equals("")) {
            return null;
        }

        return messageDAO.updateMessage(id, messageText);
    }
}