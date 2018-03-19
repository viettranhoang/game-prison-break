package vn.ptit.common;

import java.awt.Rectangle;

import vn.ptit.model.GameObject;

public class GameConfig {
    public static final int SCREEN_WIDTH = 1080;
    public static final int SCREEN_HEIGHT = 720;
    public static final int THREAD_DELAY = 17;

    private int screenWidth;
    private int screenHeight;
    private int threadDelay;
    
    public boolean isInScreen(GameObject gameObject) {
        if (gameObject.getX() >= 0
                && gameObject.getX() <= SCREEN_WIDTH - gameObject.getWidth()
                && gameObject.getY() >= 0
                && gameObject.getY() <= SCREEN_HEIGHT) return true;
        return false;
    }

    public boolean isInScreen(Rectangle rect) {
        return rect.getX() > 0 && rect.getX() + rect.getWidth() < this.screenWidth
                && rect.getY() > 25 && rect.getY() + rect.getHeight() < this.screenHeight;
    }

    public int durationInSeconds(int time) {
        return (time * threadDelay / 1000);
    }

    private static GameConfig gameConfig;

    public static GameConfig getSingleton() {
        if (gameConfig == null) {
            gameConfig = new GameConfig(SCREEN_WIDTH, SCREEN_HEIGHT, THREAD_DELAY);
        }
        return gameConfig;
    }
    
    
    
    
    //------------------------------------------------------
    
    
    
    public GameConfig(int screenWidth, int screenHeight, int threadDelay) {
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
        this.threadDelay = threadDelay;
    }

    public int getScreenWidth() {
        return screenWidth;
    }

    public void setScreenWidth(int screenWidth) {
        this.screenWidth = screenWidth;
    }

    public int getScreenHeight() {
        return screenHeight;
    }

    public void setScreenHeight(int screenHeight) {
        this.screenHeight = screenHeight;
    }

    public int getThreadDelay() {
        return threadDelay;
    }

    public void setThreadDelay(int threadDelay) {
        this.threadDelay = threadDelay;
    }

    
}
