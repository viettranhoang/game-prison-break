package vn.ptit.model;


public class Plane extends GameObjectWithHP {
    public final static int WIDTH = 70;
    public final static int HEIGHT = 56;
    public static final int DEFAULT_HP = 30;

    public Plane(int x, int y, int width, int height) {
        super(x, y, width, height, DEFAULT_HP);
    }
}
