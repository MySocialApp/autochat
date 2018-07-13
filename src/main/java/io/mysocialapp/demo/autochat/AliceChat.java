package io.mysocialapp.demo.autochat;

import io.mysocialapp.client.AbstractNotificationCallback;
import io.mysocialapp.client.MySocialApp;
import io.mysocialapp.client.Session;
import io.mysocialapp.client.models.ConversationMessage;
import io.mysocialapp.client.models.ConversationMessagePost;
import org.jetbrains.annotations.NotNull;

import static io.mysocialapp.demo.autochat.Main.*;

/**
 * Created by evoxmusic on 09/07/2018.
 */
public class AliceChat {

    public static void main(String[] args) {
        // get Alice session
        Session aliceSession = new MySocialApp.Builder()
                .setAppId(APP_ID)
                .build()
                .blockingConnect(ALICE_EMAIL, ACCOUNT_PASSWORD);

        // listen for private messages from John and Jack
        aliceSession.getNotification().addNotificationListener(new AbstractNotificationCallback() {

            @Override
            public void onConversationMessage(@NotNull ConversationMessage conversationMessage) {
                // called when Alice receive private message

                // add artificial delay to reply
                simulateUserTypingDelay();

                ConversationMessagePost message = new ConversationMessagePost.Builder()
                        .setMessage("Look at https://www.nytimes.com/2018/07/04/sports/world-cup/benjamin-pavard-france.html " +
                                "[[user:" + conversationMessage.getOwner().getId() + "]] #france #worldcup")
                        .build();

                conversationMessage.blockingReplyBack(message);

                System.out.println("Alice replied to " + conversationMessage.getOwner().getDisplayedName());
            }

        });

        System.out.println("Alice is ready to chat");
    }
}