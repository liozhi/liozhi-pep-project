package Controller;

import Model.Account;
import Model.Message;
import Service.SocialMediaService;

import io.javalin.Javalin;
import io.javalin.http.Context;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

/**
 * TODO: You will need to write your own endpoints and handlers for your controller. The endpoints you will need can be
 * found in readme.md as well as the test cases. You should
 * refer to prior mini-project labs and lecture materials for guidance on how a controller may be built.
 */
public class SocialMediaController {
    
    SocialMediaService smService;

    public SocialMediaController(){
        this.smService = new SocialMediaService();
    }

    /**
     * In order for the test cases to work, you will need to write the endpoints in the startAPI() method, as the test
     * suite must receive a Javalin object from this method.
     * @return a Javalin app object which defines the behavior of the Javalin controller.
     */
    public Javalin startAPI() {
        Javalin app = Javalin.create();
        app.post("/register", this::registerHandler);
        app.post("/login", this::loginHandler);
        app.post("/messages", this::createMessageHandler);
        app.get("/messages", this::getAllMessagesHandler);
        app.get("/messages/{message_id}", this::getMessageHandler);
        app.delete("/messages/{message_id}", this::deleteMessageHandler);
        app.patch("/messages/{message_id}", this::updateMessageHandler);
        app.get("/accounts/{account_id}/messages", this::getUserMessagesHandler);

        return app;
    }

    private void registerHandler(Context ctx) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        Account newAcc = mapper.readValue(ctx.body(), Account.class);
        Account acc = smService.register(newAcc);
        if (acc != null) {
            ctx.json(acc);
        } else {
            ctx.status(400);
        }
    }

    private void loginHandler(Context ctx) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        Account userAcc = mapper.readValue(ctx.body(), Account.class);
        Account acc = smService.login(userAcc);
        if (acc != null) {
            ctx.json(acc);
        } else {
            ctx.status(401);
        }
    }

    private void createMessageHandler(Context ctx) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();

        Message incMessage = mapper.readValue(ctx.body(), Message.class);
        Message message = smService.createMessage(incMessage);
        if (message != null) {
            ctx.json(message);
        } else {
            ctx.status(400);
        }
    }

    private void getAllMessagesHandler(Context ctx) throws JsonProcessingException {
        List<Message> messages = smService.getAllMessages();
        ctx.json(messages);
    }

    private void getMessageHandler(Context ctx) throws JsonProcessingException {
        Integer mid = Integer.parseInt(ctx.pathParam("message_id"));
        Message message = smService.getMessage(mid);
        if (message != null) {
            ctx.json(message);
        } else {
            ctx.json("");
        }
    }

    private void deleteMessageHandler(Context ctx) throws JsonProcessingException {
        Integer mid = Integer.parseInt(ctx.pathParam("message_id"));
        Message message = smService.deleteMessage(mid);
        if (message != null) {
            ctx.json(message);
        } else {
            ctx.json("");
        }
    }

    private void updateMessageHandler(Context ctx) throws JsonProcessingException {
        Integer mid = Integer.parseInt(ctx.pathParam("message_id"));
        ObjectMapper mapper = new ObjectMapper();

        Message incMessage = mapper.readValue(ctx.body(), Message.class);
        Message message = smService.updateMessage(mid, incMessage.message_text);
        if (message != null) {
            ctx.json(message);
        } else {
            ctx.status(400);
        }
    }

    private void getUserMessagesHandler(Context ctx) throws JsonProcessingException {
        Integer uid = Integer.parseInt(ctx.pathParam("account_id"));
        List<Message> messages = smService.getUserMessages(uid);
        ctx.json(messages);
    }
}