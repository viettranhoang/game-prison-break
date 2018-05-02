package vn.ptit.model;

public class Wall extends GameObjectWithHP{
	public static final int WIDTH = 150;
	public static final int HEIGHT = 75;
	public static final int DEFAULT_HP = 1000;
	
	
	public Wall(int x, int y, int width, int height) {
		super(x, y, width, height, DEFAULT_HP);
	}
	
}
