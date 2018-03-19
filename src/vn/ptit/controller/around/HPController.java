package vn.ptit.controller.around;

import vn.ptit.controller.HumanController;
import vn.ptit.model.EnemyPlane;
import vn.ptit.model.GameObject;
import vn.ptit.model.GameVector;
import vn.ptit.view.IGameDrawer;
import vn.ptit.view.ImageDrawer;

public class HPController extends AroundController{

	public HPController(GameObject gameObject, IGameDrawer gameDrawer, GameVector gameVector) {
		super(gameObject, gameDrawer, gameVector);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void run() {
		if(HumanController.clearHeart) {
			this.gameObject.setAlive(false);
			HumanController.clearHeart = false;
		}
		super.run();
	}

	public static HPController create(int x, int y) {
		EnemyPlane wall = new EnemyPlane(x, y, 20, 20);
		ImageDrawer drawer = new ImageDrawer("resources/heart.png");
		HPController hpController = new HPController(wall, drawer, new GameVector());
		return hpController;
	}
	
	

}
