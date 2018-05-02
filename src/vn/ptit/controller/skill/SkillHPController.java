package vn.ptit.controller.skill;

import vn.ptit.model.EnemyPlane;
import vn.ptit.model.GameObject;
import vn.ptit.model.GameVector;
import vn.ptit.view.IGameDrawer;
import vn.ptit.view.ImageDrawer;

public class SkillHPController extends SkillController{

	public SkillHPController(GameObject gameObject, IGameDrawer gameDrawer, GameVector gameVector) {
		super(gameObject, gameDrawer, gameVector);
		// TODO Auto-generated constructor stub
	}

	public static SkillHPController create(int x, int y) {
		EnemyPlane hp = new EnemyPlane(x, y, 30, 30);
	    ImageDrawer hpDrawer = new ImageDrawer("resources/hp.png");
	    SkillHPController hpController = new SkillHPController(hp, hpDrawer, new GameVector());
	    return hpController;
	}
	
}