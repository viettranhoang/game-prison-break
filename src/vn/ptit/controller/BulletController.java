package vn.ptit.controller;

import vn.ptit.controller.SingleController;
import vn.ptit.common.GameConfig;
import vn.ptit.controller.enemy.EnemyBulletController;
import vn.ptit.controller.enemy.EnemyBulletControllerManager;
import vn.ptit.controller.enemy.EnemyController;
import vn.ptit.controller.skill.SkillHPController;
import vn.ptit.model.EnemyBullet;
import vn.ptit.model.GameObject;
import vn.ptit.model.GameVector;
import vn.ptit.view.IGameDrawer;
import vn.ptit.view.ImageDrawer;

public class BulletController extends SingleController implements IColliable{
    public static int SPEED;

    public BulletController(GameObject gameObject, IGameDrawer gameDrawer, GameVector gameVector) {
        super(gameObject, gameDrawer, gameVector);
        this.gameVector.dy = -SPEED;
        CollisionPool.getSingleton().add(this);
    }

    @Override
    public void run() {
        if(!GameConfig.getSingleton().isInScreen(this.gameObject)) {
            this.gameObject.setAlive(false);
        }
        super.run();
    }

    @Override
    public void onCollide(IColliable c) {
    	if(c instanceof SkillHPController) {
        }
    	else if(c instanceof EnemyController) {
            this.gameObject.setAlive(false);
        }
        if(c instanceof EnemyBulletController) {
            this.gameObject.setAlive(false);
        }

    }

    @Override
    public GameObject getGameObject() {
        return this.gameObject;
    }
    
    public static void create(int x, int y) {
    	EnemyBullet enemyBullet = new EnemyBullet(x, y, EnemyBullet.WIDTH, EnemyBullet.HEIGHT);
        ImageDrawer enemyBulletDrawer = new ImageDrawer("resources/enemy_bullet.png");
        EnemyBulletController enemyBulletController = new EnemyBulletController(enemyBullet, enemyBulletDrawer, new GameVector(0, 7));
        EnemyBulletControllerManager.getSingleton().add(enemyBulletController);
    }
}
