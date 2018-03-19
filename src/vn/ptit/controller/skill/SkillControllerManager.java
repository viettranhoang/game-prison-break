package vn.ptit.controller.skill;

import vn.ptit.common.GameConfig;
import vn.ptit.common.RandomInt;
import vn.ptit.controller.ControllerManager;
import vn.ptit.model.EnemyPlane;
import vn.ptit.model.GameVector;
import vn.ptit.view.ImageDrawer;

public class SkillControllerManager extends ControllerManager{
	private int time = 0;
	
	@Override
	public void run() {
		time++;
		if (GameConfig.getSingleton().durationInSeconds(time) >= 20) {
			time =0;
			
            int randX = RandomInt.random(100, GameConfig.getSingleton().getScreenWidth() - 60);
         	int randY = RandomInt.random(300, GameConfig.getSingleton().getScreenHeight() - 60);
           	EnemyPlane skill = new EnemyPlane(randX, randY, 30, 30);
    	    ImageDrawer skillDrawer = new ImageDrawer("resources/three_bullet.png");
    	    SkillController skillController = new SkillController(skill, skillDrawer, new GameVector());
    	    this.controllerVector.add(skillController);
    	    
    	    randX = RandomInt.random(100, GameConfig.getSingleton().getScreenWidth() - 60);
    	    randY = RandomInt.random(300, GameConfig.getSingleton().getScreenHeight() - 60);
    	    this.controllerVector.add(SkillHPController.create(randX, randY));
        }
		super.run();
	}
	
	
	private static SkillControllerManager skillControllerManager;

    public static SkillControllerManager getSingleton() {
        if (skillControllerManager == null) {
            skillControllerManager = new SkillControllerManager();
        }
        return skillControllerManager;
    }



    public void setNull() {
        SkillControllerManager.skillControllerManager = null;
    }
	
}
