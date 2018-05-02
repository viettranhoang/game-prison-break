package vn.ptit.model;

public class Tower extends GameObjectWithHP{
	public static final int WIDTH = 80;
	public static final int HEIGHT = 144;
	public static final int DEFAULT_HP = 40;
	
	
	public Tower(int x, int y, int width, int height) {
		super(x, y, width, height, DEFAULT_HP);
	}
	
}
