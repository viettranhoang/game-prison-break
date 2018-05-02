package vn.ptit.model;

public class Gate extends GameObjectWithHP{
	public static final int WIDTH = 208;
	public static final int HEIGHT = 75;
	public static final int DEFAULT_HP = 20;
	
	
	public Gate(int x, int y, int width, int height) {
		super(x, y, width, height, DEFAULT_HP);
	}
	
}
