package vn.ptit.controller.around;

import vn.ptit.model.GameObject;
import vn.ptit.model.GameVector;
import vn.ptit.model.Wall;
import vn.ptit.view.IGameDrawer;
import vn.ptit.view.ImageDrawer;

public class FlagController  extends AroundController{
	public static boolean win = false;
	
	public FlagController(GameObject gameObject, IGameDrawer gameDrawer, GameVector gameVector) {
		super(gameObject, gameDrawer, gameVector);
	}
	
	public static FlagController create (int x, int y) {
		Wall wall = new Wall(x, y, 40, 60);
		ImageDrawer flagDrawer = new ImageDrawer("resources/flag.png");
		FlagController flagController = new FlagController(wall, flagDrawer, new GameVector());
		return flagController;
	}
	
}
