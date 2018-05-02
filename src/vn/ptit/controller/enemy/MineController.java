package vn.ptit.controller.enemy;

import vn.ptit.model.EnemyPlane;
import vn.ptit.model.GameObject;
import vn.ptit.model.GameVector;
import vn.ptit.view.IGameDrawer;
import vn.ptit.view.ImageDrawer;

public class MineController extends EnemyController{
	public MineController(GameObject gameObject, IGameDrawer gameDrawer, GameVector gameVector) {
		super(gameObject, gameDrawer, gameVector);
		// TODO Auto-generated constructor stub
	}
	
	public static MineController create(int x, int y) {
		EnemyPlane enemyPlane = new EnemyPlane(x, y, 30, 25);
		ImageDrawer enemyDrawer = new ImageDrawer("resources/mine.png");
		MineController mineController = new MineController(enemyPlane, enemyDrawer, new GameVector(0, 0));
		mineController.isShot = false;
		return mineController;
	}

}
