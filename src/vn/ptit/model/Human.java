package vn.ptit.model;


public class Human extends GameObjectWithHP {
    public final static int WIDTH = 47;
    public final static int HEIGHT = 60;
    public static final int DEFAULT_HP = 20;

    public Human(int x, int y, int width, int height) {
        super(x, y, width, height, DEFAULT_HP);
    }
}
