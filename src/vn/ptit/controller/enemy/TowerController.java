package vn.ptit.controller.enemy;

import vn.ptit.common.RandomInt;
import vn.ptit.model.EnemyBullet;
import vn.ptit.model.EnemyPlane;
import vn.ptit.model.GameObject;
import vn.ptit.model.GameVector;
import vn.ptit.model.Tower;
import vn.ptit.view.IGameDrawer;
import vn.ptit.view.ImageDrawer;

public class TowerController extends EnemyController{
	public static int numberOfTowerBullet;

	public TowerController(GameObject gameObject, IGameDrawer gameDrawer, GameVector gameVector) {
		super(gameObject, gameDrawer, gameVector);
	}

	@Override
	public void shot() {
		int x = RandomInt.random(0, 3);
        if(x > 0) {
            for(int i = 0; i < numberOfTowerBullet; i++) {

            	int r = RandomInt.random(-3, 3);
            	EnemyBullet towerBullet = new EnemyBullet(
                        this.gameObject.getX() + (EnemyPlane.WIDTH - EnemyBullet.WIDTH) / 2,
                        this.gameObject.getY() + (EnemyPlane.HEIGHT - EnemyBullet.HEIGHT),
                        EnemyBullet.WIDTH,
                        EnemyBullet.HEIGHT
                );
                ImageDrawer towerBulletDrawer = new ImageDrawer("resources/tower_bullet.png");
                EnemyBulletController towerBulletController = new EnemyBulletController(towerBullet, towerBulletDrawer, new GameVector(r, r + 5));
                EnemyBulletControllerManager.getSingleton().add(towerBulletController);
            }
        }		
	}

	public static TowerController create(int x, int y) {
		Tower tower = new Tower(x, y, Tower.WIDTH, Tower.HEIGHT);
		ImageDrawer towerDrawer = new ImageDrawer("resources/tower.png");
		TowerController towerController = new TowerController(tower, towerDrawer, new GameVector());
		return towerController;
	}

}
