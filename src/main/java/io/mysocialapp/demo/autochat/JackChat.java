package io.mysocialapp.demo.autochat;

import io.mysocialapp.client.AbstractNotificationCallback;
import io.mysocialapp.client.MySocialApp;
import io.mysocialapp.client.Session;
import io.mysocialapp.client.models.ConversationMessage;
import io.mysocialapp.client.models.ConversationMessagePost;
import org.jetbrains.annotations.NotNull;

import java.io.File;

import static io.mysocialapp.demo.autochat.Main.APP_ID;
import static io.mysocialapp.demo.autochat.Main.simulateUserTypingDelay;

/**
 * Created by evoxmusic on 09/07/2018.
 */
public class JackChat {

    private final static File image = new File(System.class.getResource("/stadium.jpg").getFile());

    public static void main(String[] args) {
        // get Jack session
        Session jackSession = new MySocialApp.Builder()
                .setAppId(APP_ID)
                .build()
                .blockingConnect("jack.test@mysocialapp.io", "mySecretPassword");

        // listen for private messages from Alice and John
        jackSession.getNotification().addNotificationListener(new AbstractNotificationCallback() {

            @Override
            public void onConversationMessage(@NotNull ConversationMessage conversationMessage) {
                // called when Jack receive private message

                // add artificial delay to reply
                simulateUserTypingDelay();

                ConversationMessagePost message = new ConversationMessagePost.Builder()
                        .setMessage("Incredible moscow stadium #wonderful #soccer ! [[user:" + conversationMessage.getOwner().getId() + "]] " +
                                "look at this")
                        .setImage(image)
                        .build();

                conversationMessage.blockingReplyBack(message);

                System.out.println("Jack replied to " + conversationMessage.getOwner().getDisplayedName());
            }

        });
    }
}