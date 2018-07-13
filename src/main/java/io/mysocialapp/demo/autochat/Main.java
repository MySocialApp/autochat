package io.mysocialapp.demo.autochat;

import io.mysocialapp.client.MySocialApp;
import io.mysocialapp.client.models.User;

import java.io.File;

/**
 * Created by evoxmusic on 12/07/2018.
 */
public class Main {

    final static String APP_ID = "u470584465854a194805";
    final static String JOHN_EMAIL = "john.test.acc@mysocialapp.io";
    final static String ALICE_EMAIL = "alice.test.acc@mysocialapp.io";
    final static String JACK_EMAIL = "jack.test.acc@mysocialapp.io";
    final static String ACCOUNT_PASSWORD = "mySecretPassword";

    final static MySocialApp msa = new MySocialApp.Builder().setAppId(APP_ID).build();

    public static void main(String[] args) {
        initJohn();
        initAlice();
        initJack();

        // launch all
        JackChat.main(args);
        AliceChat.main(args);
        JohnChat.main(args);
    }

    private static void initJohn() {
        try {
            User john = msa.blockingCreateAccount(JOHN_EMAIL, ACCOUNT_PASSWORD, "John").getAccount().blockingGet();
            john.blockingChangeProfilePhoto(new File(System.class.getResource("/john.png").getFile()));
            john.setExternalId("john");
            john.blockingSave();
        } catch (Exception e) {
            // account already exists
        }
    }

    private static void initAlice() {
        try {
            User john = msa.blockingCreateAccount(ALICE_EMAIL, ACCOUNT_PASSWORD, "Alice").getAccount().blockingGet();
            john.blockingChangeProfilePhoto(new File(System.class.getResource("/alice.png").getFile()));
            john.setExternalId("alice");
            john.blockingSave();
        } catch (Exception e) {
            // account already exists
        }
    }

    private static void initJack() {
        try {
            User john = msa.blockingCreateAccount(JACK_EMAIL, ACCOUNT_PASSWORD, "Jack").getAccount().blockingGet();
            john.blockingChangeProfilePhoto(new File(System.class.getResource("/jack.png").getFile()));
            john.setExternalId("jack");
            john.blockingSave();
        } catch (Exception e) {
            // account already exists
        }
    }

    static void simulateUserTypingDelay() {
        try {
            Thread.sleep(randomTime());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static long randomTime() {
        long leftLimit = 3200L;
        long rightLimit = 5200L;
        return leftLimit + (long) (Math.random() * (rightLimit - leftLimit));
    }

}
