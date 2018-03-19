package vn.ptit.controller.enemy;

import vn.ptit.common.GameConfig;
import vn.ptit.controller.BulletController;
import vn.ptit.controller.CollisionPool;
import vn.ptit.controller.HumanController;
import vn.ptit.controller.IColliable;
import vn.ptit.controller.PlaneController;
import vn.ptit.controller.SingleController;
import vn.ptit.controller.around.TreeController;
import vn.ptit.model.GameObject;
import vn.ptit.model.GameVector;
import vn.ptit.view.IGameDrawer;

public class EnemyBulletController extends SingleController implements IColliable{
//    public static final int SPEED = 7;

    public EnemyBulletController(GameObject gameObject, IGameDrawer gameDrawer, GameVector gameVector) {
        super(gameObject, gameDrawer, gameVector);
//        this.gameVector.dy = SPEED;
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
        if(c instanceof HumanController || c instanceof PlaneController) {
            this.gameObject.setAlive(false);
        }
        if(c instanceof BulletController) {
            this.gameObject.setAlive(false);
        }
        if(c instanceof TreeController) {
            this.gameObject.setAlive(false);
        }
    }

    @Override
    public GameObject getGameObject() {
        return this.gameObject;
    }
}
