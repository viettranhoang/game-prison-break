package vn.ptit.controller;

import java.awt.Graphics;
import java.awt.Rectangle;

import vn.ptit.controller.BulletController;
import vn.ptit.controller.SingleController;
import vn.ptit.common.GameConfig;
import vn.ptit.controller.around.FlagController;
import vn.ptit.controller.around.RiverController;
import vn.ptit.controller.around.SwampController;
import vn.ptit.controller.enemy.EnemyBulletController;
import vn.ptit.controller.enemy.MineController;
import vn.ptit.controller.enemy.TankController;
import vn.ptit.controller.enemy.TowerController;
import vn.ptit.controller.skill.SkillController;
import vn.ptit.controller.skill.SkillHPController;
import vn.ptit.model.Bullet;
import vn.ptit.model.GameObject;
import vn.ptit.model.GameVector;
import vn.ptit.model.Human;
import vn.ptit.view.IGameDrawer;
import vn.ptit.view.ImageDrawer;

public class HumanController extends SingleController implements IColliable{
    public static int SPEED;
    public static final int MAX_BULLET = 10;
    public static boolean fullHP = false;
    public static boolean isThreeBullet = false; 
    public static boolean clearHeart = false; 
    public int time = 0;
    Human plane = (Human) this.gameObject;
    private BulletControllerManager bulletControllerManager;

    public HumanController(GameObject gameObject, IGameDrawer gameDrawer, GameVector gameVector) {
        super(gameObject, gameDrawer, gameVector);
        this.bulletControllerManager = new BulletControllerManager();
        CollisionPool.getSingleton().add(this);
        
        
    }

    public void move(EHumanDirection planeDirection) {
        switch (planeDirection) {
            case NONE:
                break;
            case UP:
                this.gameVector.dy = -SPEED;
                
                break;
            case DOWN:
                this.gameVector.dy = SPEED;
                break;
            case LEFT:
                this.gameVector.dx = -SPEED;
                break;
            case RIGHT:
                this.gameVector.dx = SPEED;
                break;
            case STOP_X:
                this.gameVector.dx = 0;
                break;
            case STOP_Y:
                this.gameVector.dy = 0;
                break;
        }
        
    }

    public void shot() {
        if(isThreeBullet) for(int i = -2; i < 3; i+=2) {
        	if (this.bulletControllerManager.size() < MAX_BULLET) {
                Bullet bullet = new Bullet(
                        this.gameObject.getX() + (Human.WIDTH - Bullet.WIDTH) / 2,
                        this.gameObject.getY(),
                        Bullet.WIDTH,
                        Bullet.HEIGHT);
                ImageDrawer bulletDrawer = new ImageDrawer("resources/bullet.png");
                BulletController bulletController = new BulletController(bullet, bulletDrawer, new GameVector(i, 2));
                this.bulletControllerManager.add(bulletController);
            }
        }
        else {
        	if (this.bulletControllerManager.size() < MAX_BULLET) {
                Bullet bullet = new Bullet(
                        this.gameObject.getX() + (Human.WIDTH - Bullet.WIDTH) / 2,
                        this.gameObject.getY(),
                        Bullet.WIDTH,
                        Bullet.HEIGHT);
                ImageDrawer bulletDrawer = new ImageDrawer("resources/bullet.png");
                BulletController bulletController = new BulletController(bullet, bulletDrawer, new GameVector());
                this.bulletControllerManager.add(bulletController);
        	}
        }
    }

    private static HumanController planeController;

    public static HumanController getSingleton() {
        if (planeController == null) {
            Human plane = new Human(100, 500, Human.WIDTH, Human.HEIGHT);
            ImageDrawer imageDrawer = new ImageDrawer("resources/human.png");
            planeController = new HumanController(plane, imageDrawer, new GameVector());
        }
        return planeController;
    }

    @Override
    public void run() {
        if (this.gameObject.isAlive()) {
            Rectangle rectangle=this.gameObject.getNextRect(this.gameVector);
            if(GameConfig.getSingleton().isInScreen(rectangle)) {
                super.run();
            }
            bulletControllerManager.run();
        }
        
        if(isThreeBullet) {
        	time++;
        	if(GameConfig.getSingleton().durationInSeconds(time) >= 10) {
        		time = 0;
        		isThreeBullet = false;
        	}
        	
        }
       
    }

    @Override
    public void paint(Graphics g) {
        if(this.gameObject.isAlive()) {
            super.paint(g);
            this.bulletControllerManager.paint(g);
        }
    }

    @Override
    public void onCollide(IColliable c) {
        if(c instanceof EnemyBulletController) {
            plane.decreaseHP();
            clearHeart = true;
            
        }
        else if(c instanceof TowerController || c instanceof SwampController) {
        	if(this.gameVector.dx == SPEED) this.gameVector.dx = -2;
        	if(this.gameVector.dx == -SPEED) this.gameVector.dx = 2;
        	if(this.gameVector.dy == SPEED) this.gameVector.dy = -2;
        	if(this.gameVector.dy == -SPEED) this.gameVector.dy = 2;

        }
        else if(c instanceof RiverController) {
        	if(this.gameVector.dx == SPEED) this.gameVector.dx = 1;
        	if(this.gameVector.dx == -SPEED) this.gameVector.dx = -1;
        	if(this.gameVector.dy == SPEED) this.gameVector.dy = 1;
        	if(this.gameVector.dy == -SPEED) this.gameVector.dy = -1;

        }

        else if(c instanceof MineController || c instanceof TankController) {
          plane.decreaseHP(plane.getHp());
        }
        else if(c instanceof SkillHPController) {
        	plane.increaseHP(Human.DEFAULT_HP - plane.getHp());
        	fullHP = true;
        }
        else if(c instanceof SkillController) {
        	isThreeBullet = true;
        }
        else if(c instanceof FlagController) {
        	FlagController.win = true;
        	plane.decreaseHP(plane.getHp());
        	
        }
        
        if(plane.getHp() <= 0) {
            this.gameObject.setAlive(false);
        }
        
    }

    @Override
    public GameObject getGameObject() {
        return this.gameObject;
    }

    public void setNull() {
        HumanController.planeController = null;
    }
}
