package vn.ptit.controller.enemy;

import vn.ptit.controller.ControllerManager;

public class EnemyBulletControllerManager extends ControllerManager {

    private static EnemyBulletControllerManager enemyBulletControllerManager;
    public static EnemyBulletControllerManager getSingleton() {
        if(enemyBulletControllerManager == null) {
            enemyBulletControllerManager = new EnemyBulletControllerManager();
        }
        return enemyBulletControllerManager;
    }



    public void setNull() {
        EnemyBulletControllerManager.enemyBulletControllerManager = null;
    }
}
