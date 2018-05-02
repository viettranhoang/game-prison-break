package vn.ptit.controller.enemy;

import vn.ptit.common.RandomInt;
import vn.ptit.model.EnemyBullet;
import vn.ptit.model.EnemyPlane;
import vn.ptit.model.GameObject;
import vn.ptit.model.GameVector;
import vn.ptit.view.IGameDrawer;
import vn.ptit.view.ImageDrawer;

public class TankController extends EnemyController{

	public TankController(GameObject gameObject, IGameDrawer gameDrawer, GameVector gameVector) {
		super(gameObject, gameDrawer, gameVector);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void shot() {
        int x = RandomInt.random(0, 3);
        if(x > 0) {
            EnemyBullet enemyBullet = new EnemyBullet(
                    this.gameObject.getX(),
                    this.gameObject.getY() + (EnemyPlane.HEIGHT - EnemyBullet.HEIGHT - 20) / 2,
                    EnemyBullet.WIDTH,
                    EnemyBullet.HEIGHT
            );
            ImageDrawer enemyBulletDrawer = new ImageDrawer("resources/enemy_bullet.png");
            EnemyBulletController enemyBulletController = new EnemyBulletController(enemyBullet, enemyBulletDrawer, new GameVector(-7, 0));
            EnemyBulletControllerManager.getSingleton().add(enemyBulletController);
        }
	}
	
	public static TankController create(int x, int y) {
		EnemyPlane tank = new EnemyPlane(x, y, 80, 50);
		ImageDrawer tankDrawer = new ImageDrawer("resources/tank.png");
		TankController tankController = new TankController(tank, tankDrawer, new GameVector(-2, 0));
		return tankController;
	}
}
