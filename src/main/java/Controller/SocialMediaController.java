package Controller;

import Model.Account;
import Model.Message;
import Service.AccountService;
import Service.MessageService;

import io.javalin.Javalin;
import io.javalin.http.Context;
import java.util.List;

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
        app.post("register", this::registerUser);
        app.post("login", this::loginUser);
        app.post("messages", this::createMessage);
        app.get("messages", this::getAllMessages);
        app.get("messages/{message_id}", this::getMessageByID);
        app.delete("messages/{message_id}", this::deleteMessageByID);
        app.patch("messages/{message_id}", this::updateMessageByID);
        app.get("accounts/{account_id}/messages", this::getAllUserMessages);

        return app;
    }

    private void registerUser(Context context) {
        context.json("sample text");
    }

    private void loginUser(Context context) {
        context.json("sample text");
    }

    private void createMessage(Context context) {
        context.json("sample text");
    }

    private void getAllMessages(Context context) {
        context.json("sample text");
    }

    private void getMessageByID(Context context) {
        context.json("sample text");
    }

    private void deleteMessageByID(Context context) {
        context.json("sample text");
    }

    private void updateMessageByID(Context context) {
        context.json("sample text");
    }

    private void getAllUserMessages(Context context) {
        context.json("sample text");
    }

    /**
     * This is an example handler for an example endpoint.
     * @param context The Javalin Context object manages information about both the HTTP request and response.
     */
    // private void exampleHandler(Context context) {
    //     context.json("sample text");
    // }


}