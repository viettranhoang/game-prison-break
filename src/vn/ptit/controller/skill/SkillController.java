package vn.ptit.controller.skill;

import vn.ptit.controller.CollisionPool;
import vn.ptit.controller.HumanController;
import vn.ptit.controller.IColliable;
import vn.ptit.controller.SingleController;
import vn.ptit.model.GameObject;
import vn.ptit.model.GameObjectWithHP;
import vn.ptit.model.GameVector;
import vn.ptit.view.IGameDrawer;

public class SkillController  extends SingleController implements IColliable{

	public SkillController(GameObject gameObject, IGameDrawer gameDrawer, GameVector gameVector) {
		super(gameObject, gameDrawer, gameVector);
		 CollisionPool.getSingleton().add(this);
	}

	@Override
	public void onCollide(IColliable c) {
		GameObjectWithHP g = (GameObjectWithHP) this.gameObject;
        if(c instanceof HumanController) {
            g.decreaseHP(g.getHp());
            
        }
        if(g.getHp() <= 0) {
            this.gameObject.setAlive(false);
        }
	}

	@Override
	public GameObject getGameObject() {
		return this.gameObject;
	}
}

