package vn.ptit.controller.around;

import vn.ptit.model.GameObject;
import vn.ptit.model.GameVector;
import vn.ptit.model.Wall;
import vn.ptit.view.IGameDrawer;
import vn.ptit.view.ImageDrawer;

public class SwampController  extends AroundController{

	public SwampController(GameObject gameObject, IGameDrawer gameDrawer, GameVector gameVector) {
		super(gameObject, gameDrawer, gameVector);
	}
	
	public static SwampController create (int x, int y) {
		Wall wall = new Wall(x, y, 150, 120);
		ImageDrawer drawer = new ImageDrawer("resources/swamp.png");
		SwampController swampController = new SwampController(wall, drawer, new GameVector());
		return swampController;
	}

}
