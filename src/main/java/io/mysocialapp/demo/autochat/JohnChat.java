package io.mysocialapp.demo.autochat;

import io.mysocialapp.client.AbstractNotificationCallback;
import io.mysocialapp.client.MySocialApp;
import io.mysocialapp.client.Session;
import io.mysocialapp.client.models.Conversation;
import io.mysocialapp.client.models.ConversationMessage;
import io.mysocialapp.client.models.ConversationMessagePost;
import org.jetbrains.annotations.NotNull;

import static io.mysocialapp.demo.autochat.Main.*;

/**
 * Created by evoxmusic on 09/07/2018.
 */
public class JohnChat {

    public static void main(String[] args) {
        Session johnSession = new MySocialApp.Builder()
                .setAppId(APP_ID)
                .build()
                .blockingConnect(JOHN_EMAIL, ACCOUNT_PASSWORD);

        // john create private conversation with Alice and Jack
        Conversation conv = new Conversation.Builder()
                .setName("Chat between Alice, Jack and John")
                .addMember(johnSession.getUser().blockingGetByExternalId("alice")) // add Alice
                .addMember(johnSession.getUser().blockingGetByExternalId("jack")) // add Jack
                .addMember(johnSession.getUser().blockingGetByExternalId("0453ecc8-75b2-4c94-ae81-26694578f555")) // add Romaric
                .build();

        Conversation conversation = johnSession.getConversation().blockingCreate(conv);

        // listen for private messages from Alice and Jack
        johnSession.getNotification().addNotificationListener(new AbstractNotificationCallback() {

            @Override
            public void onConversationMessage(@NotNull ConversationMessage conversationMessage) {
                // called when John receive private message

                // add artificial delay to reply
                simulateUserTypingDelay();

                ConversationMessagePost message = new ConversationMessagePost.Builder()
                        .setMessage("Hey [[user:" + conversationMessage.getOwner().getId() + "]] 👋")
                        .build();

                conversationMessage.blockingReplyBack(message);

                System.out.println("John replied to " + conversationMessage.getOwner().getDisplayedName());
            }

        });

        // john start chatting with the first message
        conversation.blockingSendMessage(new ConversationMessagePost.Builder().setMessage("Hello here").build());

        System.out.println("John is ready to chat");
    }

}