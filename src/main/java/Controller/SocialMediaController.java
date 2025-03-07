package Controller;

import Model.Account;
import Model.Message;
import Service.AccountService;
import Service.MessageService;

import io.javalin.Javalin;
import io.javalin.http.Context;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import java.util.Map;

/**
 * TODO: You will need to write your own endpoints and handlers for your controller. The endpoints you will need can be
 * found in readme.md as well as the test cases. You should
 * refer to prior mini-project labs and lecture materials for guidance on how a controller may be built.
 */
public class SocialMediaController {
    MessageService messageService;
    AccountService accountService;

    public SocialMediaController() {
        this.messageService = new MessageService();
        this.accountService = new AccountService();
    }

    /**
     * In order for the test cases to work, you will need to write the endpoints in the startAPI() method, as the test
     * suite must receive a Javalin object from this method.
     * @return a Javalin app object which defines the behavior of the Javalin controller.
     */
    public Javalin startAPI() {
        Javalin app = Javalin.create();
        app.post("/register", this::registerUser);
        app.post("/login", this::loginUser);
        app.post("/messages", this::createMessage);
        app.get("/messages", this::getAllMessages);
        app.get("/messages/{message_id}", this::getMessageByID);
        app.delete("/messages/{message_id}", this::deleteMessageByID);
        app.patch("/messages/{message_id}", this::updateMessageByID);
        app.get("/accounts/{account_id}/messages", this::getAllUserMessages);

        return app;
    }

    private void registerUser(Context context) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        Account account = mapper.readValue(context.body(), Account.class);
        Account addedAccount = accountService.registerUser(account);
        if(addedAccount != null){
            context.json(mapper.writeValueAsString(addedAccount));
        }else{
            context.status(400);
        }
    }

    private void loginUser(Context context) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        Account account = mapper.readValue(context.body(), Account.class);
        Account loggedInAccount = accountService.loginUser(account);
        if(loggedInAccount != null){
            context.json(mapper.writeValueAsString(loggedInAccount));
        }else{
            context.status(401);
        }
    }

    private void createMessage(Context context) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        Message message = mapper.readValue(context.body(), Message.class);
        Message messageAttempt = messageService.insertMessage(message);
        if(messageAttempt != null){
            context.json(mapper.writeValueAsString(messageAttempt));
        }else{
            context.status(400);
        }
    }

    private void getAllMessages(Context context) {
        List<Message> messages = messageService.getAllMessages();
        context.json(messages);
    }

    private void getMessageByID(Context context) {
        Message message = messageService.getMessageByID(Integer.parseInt(context.pathParam("message_id")));
        if (message != null) {
            context.json(message);
        } else {
            context.status(200);
        }
    }

    private void deleteMessageByID(Context context) {
        Message message = messageService.deleteMessage(Integer.parseInt(context.pathParam("message_id")));
        if (message != null) {
            context.json(message);
        } else {
            context.status(200);
        }
    }

    private void updateMessageByID(Context context) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        Map<String, String> requestBody = mapper.readValue(context.body(), Map.class);
        String messageText = requestBody.get("message_text");
        Message updatedMessage = messageService.updateMessage(Integer.parseInt(context.pathParam("message_id")), messageText);
        if (updatedMessage != null) {
            context.json(updatedMessage);
        } else {
            context.status(400);
        }
    }

    private void getAllUserMessages(Context context) {
        int accountID = Integer.parseInt(context.pathParam("account_id"));
        List<Message> messages = messageService.getMessageByUser(accountID);
        context.json(messages);
    }

    /**
     * This is an example handler for an example endpoint.
     * @param context The Javalin Context object manages information about both the HTTP request and response.
     */
    // private void exampleHandler(Context context) {
    //     context.json("sample text");
    // }


}