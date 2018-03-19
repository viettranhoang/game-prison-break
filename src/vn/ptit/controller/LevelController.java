package vn.ptit.controller;


import vn.ptit.controller.enemy.EnemyControllerManager;
import vn.ptit.controller.enemy.TowerController;

public class LevelController {
	public static void easy() {
		EnemyControllerManager.timeEnemyPlane = 8;
		HumanController.SPEED = 6;
		BulletController.SPEED = 7;
		TowerController.numberOfTowerBullet = 1;
	}
	
	public static void hard() {
		EnemyControllerManager.timeEnemyPlane = 2;
		HumanController.SPEED = 3;
		BulletController.SPEED = 2;
		TowerController.numberOfTowerBullet = 5;
	}
}
