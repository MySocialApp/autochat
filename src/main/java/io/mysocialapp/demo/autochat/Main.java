package io.mysocialapp.demo.autochat;

/**
 * Created by evoxmusic on 12/07/2018.
 */
public class Main {

    final static String APP_ID = "u470584465854a194805";

    public static void main(String[] args) {
        // launch all
        JackChat.main(args);
        AliceChat.main(args);
        JohnChat.main(args);
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
