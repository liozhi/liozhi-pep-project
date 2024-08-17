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

    public List<Message> getAllMessages() {
        return smDAO.getAllMessages();
    }

    public Message getMessage(int id) {
        return smDAO.getMessage(id);
    }
}
