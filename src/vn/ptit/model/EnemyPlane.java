package vn.ptit.model;


public class EnemyPlane extends GameObjectWithHP {
    public final static int WIDTH = 60;
    public final static int HEIGHT = 60;
    public static final int DEFAULT_HP = 2;

    public EnemyPlane(int x, int y, int width, int height) {
        super(x, y, width, height, DEFAULT_HP);
    }
}
