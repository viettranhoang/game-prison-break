package vn.ptit.controller.around;

import vn.ptit.controller.CollisionPool;
import vn.ptit.controller.IColliable;
import vn.ptit.controller.SingleController;
import vn.ptit.model.GameObject;
import vn.ptit.model.GameVector;
import vn.ptit.view.IGameDrawer;

public class AroundController extends SingleController implements IColliable{

	public AroundController(GameObject gameObject, IGameDrawer gameDrawer, GameVector gameVector) {
		super(gameObject, gameDrawer, gameVector);
		 CollisionPool.getSingleton().add(this);
	}
	
	@Override
	public void onCollide(IColliable c) {
		
	}

	@Override
	public GameObject getGameObject() {
		return this.gameObject;
	}

}
