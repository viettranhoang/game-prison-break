package vn.ptit.controller.around;

import vn.ptit.model.GameObject;
import vn.ptit.model.GameVector;
import vn.ptit.model.Wall;
import vn.ptit.view.IGameDrawer;
import vn.ptit.view.ImageDrawer;

public class RiverController  extends AroundController{

	public RiverController(GameObject gameObject, IGameDrawer gameDrawer, GameVector gameVector) {
		super(gameObject, gameDrawer, gameVector);
		// TODO Auto-generated constructor stub
	}
	
	public static RiverController create (int x, int y) {
		Wall wall = new Wall(x, y, 200, 150);
		ImageDrawer drawer = new ImageDrawer("resources/river.png");
		RiverController treeController = new RiverController(wall, drawer, new GameVector());
		return treeController;
	}

}
