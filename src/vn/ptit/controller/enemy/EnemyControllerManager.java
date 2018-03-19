package vn.ptit.controller.enemy;

import vn.ptit.common.GameConfig;
import vn.ptit.common.RandomInt;
import vn.ptit.controller.ControllerManager;
import vn.ptit.model.EnemyPlane;
import vn.ptit.model.GameVector;
import vn.ptit.model.Gate;
import vn.ptit.model.Wall;
import vn.ptit.view.ImageDrawer;

public class EnemyControllerManager extends ControllerManager {
	private int time = 0;
	private boolean isRun = true;
	private int[] wallX = { 0, 220, 700, 920 };
	private int[] towerX = { 145, 365, 630, 845 };
	public static int timeEnemyPlane;
	@Override
	public void run() {
		time++;
		if (GameConfig.getSingleton().durationInSeconds(time) >= 0 && isRun) {
			isRun = false;
			for (int quantity = 0; quantity < 9; quantity++) {
				int mineX = RandomInt.random(30, GameConfig.getSingleton().getScreenWidth() - 30);
				int mineY = RandomInt.random(200, GameConfig.getSingleton().getScreenHeight() - 25);
				this.controllerVector.add(MineController.create(mineX, mineY));
			}

			for (int x = 0; x < towerX.length; x++) {
				this.controllerVector.add(TowerController.create(towerX[x], 100));
			}

			for (int x = 0; x < wallX.length; x++) {
				Wall wall = new Wall(wallX[x], 145, Wall.WIDTH, Wall.HEIGHT);
				ImageDrawer towerDrawer = new ImageDrawer("resources/wall.png");
				TowerController towerController = new TowerController(wall, towerDrawer, new GameVector());
				towerController.isShot = false;
				this.controllerVector.add(towerController);
			}

			Gate gate = new Gate(435, 145, Gate.WIDTH, Gate.HEIGHT);
			ImageDrawer gateDrawer = new ImageDrawer("resources/gate.png");
			TowerController towerController = new TowerController(gate, gateDrawer, new GameVector());
			towerController.isShot = false;
			this.controllerVector.add(towerController);

		}

		if (GameConfig.getSingleton().durationInSeconds(time) >= timeEnemyPlane) {
			time = 0;
			for (int x = 20; x <= GameConfig.getSingleton().getScreenWidth() - EnemyPlane.WIDTH; x += 5
					* EnemyPlane.WIDTH) {
				int dx = RandomInt.random(0, GameConfig.getSingleton().getScreenWidth() - EnemyPlane.WIDTH);
				this.controllerVector.add(EnemyController.create(dx, 0));
			}

			this.controllerVector.add(TankController.create(1000, 300));

		}

		super.run();
	}

	private static EnemyControllerManager enemyPlaneControllerManager;

	public static EnemyControllerManager getSingleton() {
		if (enemyPlaneControllerManager == null) {
			enemyPlaneControllerManager = new EnemyControllerManager();
		}
		return enemyPlaneControllerManager;
	}

	public void setNull() {
		EnemyControllerManager.enemyPlaneControllerManager = null;
	}
}
