package Service;

import Model.Account;
import Model.Message;
import DAO.SocialMediaDAO;

import java.util.List;

public class SocialMediaService {
    private SocialMediaDAO smDAO;
    
    public SocialMediaService(){
        smDAO = new SocialMediaDAO();
    }

    public Account register(Account acc) {
        return smDAO.register(acc);
    }

    public Account login(Account acc) {
        return smDAO.login(acc);
    }

    public Message createMessage(Message msg) {
        return smDAO.createMessage(msg);
    }

    public List<Message> getAllMessages() {
        return smDAO.getAllMessages();
    }

    public Message getMessage(int id) {
        return smDAO.getMessage(id);
    }

    public Message deleteMessage(int id) {
        return smDAO.deleteMessage(id);
    }

    public Message updateMessage(int id, String text) {
        return smDAO.updateMessage(id, text);
    }

    public List<Message> getUserMessages(int uid) {
        return smDAO.getUserMessages(uid);
    }
}
