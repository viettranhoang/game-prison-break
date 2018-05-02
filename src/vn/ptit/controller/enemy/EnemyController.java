package vn.ptit.controller.enemy;

import java.awt.Graphics;
import java.util.Random;

import vn.ptit.common.GameConfig;
import vn.ptit.common.Utils;
import vn.ptit.controller.BulletController;
import vn.ptit.controller.CollisionPool;
import vn.ptit.controller.IColliable;
import vn.ptit.controller.SingleController;
import vn.ptit.model.EnemyBullet;
import vn.ptit.model.EnemyPlane;
import vn.ptit.model.GameObject;
import vn.ptit.model.GameObjectWithHP;
import vn.ptit.model.GameVector;
import vn.ptit.model.Score;
import vn.ptit.view.IGameDrawer;
import vn.ptit.view.ImageDrawer;

public class EnemyController extends SingleController implements IColliable{
//    public static final int SPEED = 3;
    EnemyBulletControllerManager enemyBulletControllerManager;
    private int count = 0;
    public boolean isShot = true;

    public EnemyController(GameObject gameObject, IGameDrawer gameDrawer, GameVector gameVector) {
        super(gameObject, gameDrawer, gameVector);
//        this.gameVector.dy = SPEED;
        this.enemyBulletControllerManager = new EnemyBulletControllerManager();
         shot();
        CollisionPool.getSingleton().add(this);
    }

    @Override
    public void run() {
        super.run();

        count++;
        if(GameConfig.getSingleton().durationInSeconds(count) >= 1) {
            count = 0;
            if(isShot) shot();
        }


        if(!GameConfig.getSingleton().isInScreen(this.gameObject)) {
            this.gameObject.setAlive(false);
        }
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        this.enemyBulletControllerManager.paint(g);
    }

    @Override
    public void onCollide(IColliable c) {
        GameObjectWithHP g = (GameObjectWithHP) this.gameObject;
        if(c instanceof BulletController) {
            g.decreaseHP();
            
        }
            
        if(g.getHp() <= 0) {
            Utils.playSound("resources/EnemyDie.wav", false);
            this.gameObject.setAlive(false);
            Score.score++;
        }
    }

    @Override
    public GameObject getGameObject() {
        return this.gameObject;
    }

    public void shot() {
        Random rand = new Random();
        int x = rand.nextInt(3);
        if(x > 0) {
            BulletController.create(this.gameObject.getX() + (EnemyPlane.WIDTH - EnemyBullet.WIDTH) / 2,
                    				this.gameObject.getY() + (EnemyPlane.HEIGHT - EnemyBullet.HEIGHT));
        }
    }
    
    public static EnemyController create(int x, int y) {
    	EnemyPlane enemyPlane = new EnemyPlane(x, y, EnemyPlane.WIDTH, EnemyPlane.HEIGHT);
		ImageDrawer enemyDrawer = new ImageDrawer("resources/plane1.png");
		EnemyController enemyPlaneController = new EnemyController(enemyPlane, enemyDrawer, new GameVector(0, 3));
    	return enemyPlaneController;
    }
}
