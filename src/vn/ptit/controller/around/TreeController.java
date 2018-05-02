package vn.ptit.controller.around;

import vn.ptit.model.GameObject;
import vn.ptit.model.GameVector;
import vn.ptit.model.Wall;
import vn.ptit.view.IGameDrawer;
import vn.ptit.view.ImageDrawer;

public class TreeController extends AroundController{

	public TreeController(GameObject gameObject, IGameDrawer gameDrawer, GameVector gameVector) {
		super(gameObject, gameDrawer, gameVector);
		
	}
	
	public static TreeController create (int x, int y) {
		Wall wall = new Wall(x, y, 70, 150);
		ImageDrawer wallDrawer = new ImageDrawer("resources/tree.png");
		TreeController treeController = new TreeController(wall, wallDrawer, new GameVector());
		return treeController;
	}

}
