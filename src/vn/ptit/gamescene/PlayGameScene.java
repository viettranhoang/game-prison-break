package vn.ptit.gamescene;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.util.Vector;

import javax.imageio.ImageIO;

import vn.ptit.common.GameConfig;
import vn.ptit.common.Utils;
import vn.ptit.controller.CollisionPool;
import vn.ptit.controller.EHumanDirection;
import vn.ptit.controller.HumanController;
import vn.ptit.controller.IController;
import vn.ptit.controller.PlaneController;
import vn.ptit.controller.around.AroundControllerManager;
import vn.ptit.controller.around.FlagController;
import vn.ptit.controller.enemy.EnemyBulletControllerManager;
import vn.ptit.controller.enemy.EnemyControllerManager;
import vn.ptit.controller.skill.SkillControllerManager;
import vn.ptit.model.Score;


public class PlayGameScene extends GameScene { //Màn hình chơi game
    private Image backgroundImage;
    private HumanController human;
    private PlaneController plane;
    private GameConfig gameConfig;
    private Vector<IController> controllerVector; //Chứa tất cả mấy cái controllerManager
    public static boolean isSuspended = false;
    
    private Image imgPlay;
	private Image imgPause;
	
    public PlayGameScene() { //Khởi tạo tất cả mấy cái khai báo ở trên
        gameConfig = GameConfig.getSingleton();
        controllerVector = new Vector<IController>();
        
        controllerVector.add(AroundControllerManager.getSingleton());
        controllerVector.add(HumanController.getSingleton());
        controllerVector.add(EnemyControllerManager.getSingleton());
        controllerVector.add(EnemyBulletControllerManager.getSingleton());
        controllerVector.add(SkillControllerManager.getSingleton());
        if(PlaneController.isTwoPlayer) controllerVector.add(PlaneController.getSingleton());
        human = HumanController.getSingleton();
        plane = PlaneController.getSingleton();
        try {
            backgroundImage = ImageIO.read(new File("resources/background.png"));
            this.imgPlay = ImageIO.read(new File("resources/play2.png"));
            this.imgPause = ImageIO.read(new File("resources/pause.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {  //Bắt va chạm trước, rồi cho từng thằng run
        CollisionPool.getSingleton().run();
        for (IController controller : controllerVector) {
            controller.run();
        }
        if(human.getGameObject().isAlive() == false) {
            CollisionPool.getSingleton().setNull();
            HumanController.getSingleton().setNull();
            PlaneController.getSingleton().setNull();
            EnemyBulletControllerManager.getSingleton().setNull();
            EnemyControllerManager.getSingleton().setNull();
            SkillControllerManager.getSingleton().setNull();
            AroundControllerManager.getSingleton().setNull();
            Score.setHighScore();
            if(FlagController.win) {
            	changeGameScene(EGameSceneType.WIN);
            	FlagController.win = false;
            }
            else changeGameScene(EGameSceneType.GAMEOVER);
        }
    }

    @Override
    public void paint(Graphics backbufferGraphics) {
        backbufferGraphics.drawImage(backgroundImage, 0, 0, gameConfig.getScreenWidth(), gameConfig.getScreenHeight(), null);
        backbufferGraphics.drawImage(imgPlay, 10, 55, 45, 45, null);
		backbufferGraphics.drawImage(imgPause, 60, 55, 45, 45, null);
        backbufferGraphics.setFont(new Font("TimesRoman", Font.PLAIN, 35));
        backbufferGraphics.drawString(Score.score + "", 1030, 70);
        for (IController controller : controllerVector) {
            controller.paint(backbufferGraphics);
        }
    }
    
    
    
    @Override
    public void onKeyPressed(KeyEvent e) { //Bắt phím như trước làm bên GameWindow
        EHumanDirection humanDirection = EHumanDirection.NONE;
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
                humanDirection = EHumanDirection.UP;
                // repaint();
                break;
            case KeyEvent.VK_DOWN:
                humanDirection = EHumanDirection.DOWN;
                // repaint();
                break;
            case KeyEvent.VK_LEFT:
                humanDirection = EHumanDirection.LEFT;
                // repaint();
                break;
            case KeyEvent.VK_RIGHT:
                humanDirection = EHumanDirection.RIGHT;
                // repaint();
                break;
            case KeyEvent.VK_SPACE:
                human.shot();
                Utils.playSound("resources/Shot.wav", false);
                break;
            case KeyEvent.VK_S:
                this.isSuspended = true;
                break;
        }
        human.move(humanDirection);
    }

    @Override
    public void onKeyReleased(KeyEvent e) { //Nhả phím
        EHumanDirection humanDirection = EHumanDirection.NONE;
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
            case KeyEvent.VK_DOWN:
                humanDirection = EHumanDirection.STOP_Y;
                // repaint();
                break;
            case KeyEvent.VK_LEFT:
            case KeyEvent.VK_RIGHT:
                humanDirection = EHumanDirection.STOP_X;
                // repaint();
                break;
        }
        human.move(humanDirection);
    }

	@Override
	public void onMouseClick(java.awt.event.MouseEvent e) {
		plane.shot();
		Utils.playSound("resources/plane_shot.wav", false);
	}
	
}
