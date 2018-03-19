package vn.ptit.controller.around;

import vn.ptit.common.GameConfig;
import vn.ptit.common.RandomInt;
import vn.ptit.controller.ControllerManager;
import vn.ptit.controller.HumanController;
import vn.ptit.model.GameVector;
import vn.ptit.model.Human;
import vn.ptit.model.Score;
import vn.ptit.view.ImageDrawer;

public class AroundControllerManager extends ControllerManager{
	private int time = 0;
	private boolean isRun = true;
	private static AroundControllerManager aroundControllerManager;

	public static AroundControllerManager getSingleton() {
		if (aroundControllerManager == null) {
			aroundControllerManager = new AroundControllerManager();
		}
		return aroundControllerManager;
	}

	public void setNull() {
		AroundControllerManager.aroundControllerManager = null;
	}

	@Override
	public void run() {
		time++;
		if (GameConfig.getSingleton().durationInSeconds(time) >= 0 && isRun) {
			isRun = false;
			int heartX = 20;
			for (int i = 0; i < Human.DEFAULT_HP; i++) {
				this.controllerVector.add(HPController.create(heartX, 30));
				heartX += 25;
			}
			
			Score point = new Score(980, 30, Score.WIDTH, Score.HEIGHT);
	        ImageDrawer imageDrawer = new ImageDrawer("resources/score.png");
	        AroundController scoreController = new AroundController(point, imageDrawer, new GameVector());
	        this.controllerVector.add(scoreController);
	        
	        int randX, randY;
	        randX = RandomInt.random(100, GameConfig.getSingleton().getScreenWidth() - 200);
			randY = RandomInt.random(300, GameConfig.getSingleton().getScreenHeight() - 150);
			this.controllerVector.add(RiverController.create(randX, randY));
			
			randX = RandomInt.random(100, GameConfig.getSingleton().getScreenWidth() - 150);
			randY = RandomInt.random(300, GameConfig.getSingleton().getScreenHeight() - 120);
			this.controllerVector.add(SwampController.create(randX, randY));
			
			this.controllerVector.add(FlagController.create(520, 40));
			
	        for (int x = 0; x < 5; x++) {
	        	randX = RandomInt.random(100, GameConfig.getSingleton().getScreenWidth() - 70);
				randY = RandomInt.random(300, GameConfig.getSingleton().getScreenHeight() - 150);
				this.controllerVector.add(TreeController.create(randX, randY));
			}
		}
		
		if (GameConfig.getSingleton().durationInSeconds(time) >= 0) {
			if (HumanController.fullHP) {
				int heartX = 20;
				for (int i = 0; i < Human.DEFAULT_HP; i++) {
					this.controllerVector.add(HPController.create(heartX, 30));
					heartX += 25;
				}
				HumanController.fullHP = false;
			}

		}
		super.run();
	}
	
	
}
