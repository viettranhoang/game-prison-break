package vn.ptit.controller;

import java.awt.Graphics;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Rectangle;

import vn.ptit.common.GameConfig;
import vn.ptit.controller.enemy.EnemyBulletController;
import vn.ptit.controller.enemy.EnemyController;
import vn.ptit.controller.enemy.MineController;
import vn.ptit.controller.enemy.TankController;
import vn.ptit.model.Bullet;
import vn.ptit.model.GameObject;
import vn.ptit.model.GameVector;
import vn.ptit.model.Plane;
import vn.ptit.view.IGameDrawer;
import vn.ptit.view.ImageDrawer;

public class PlaneController extends SingleController implements IColliable{
	public static boolean isTwoPlayer = false;
	private static final int MAX_BULLET = 10;
	private BulletControllerManager bulletControllerManager;
	Plane plane = (Plane) this.gameObject;
	public PlaneController(GameObject gameObject, IGameDrawer gameDrawer, GameVector gameVector) {
		super(gameObject, gameDrawer, gameVector);
		this.bulletControllerManager = new BulletControllerManager();
		CollisionPool.getSingleton().add(this);
		
	
	}

	private static PlaneController planeController;

	public static PlaneController getSingleton() {
		if (planeController == null) {
			Plane plane = new Plane(500, 500, Plane.HEIGHT, Plane.HEIGHT);
			ImageDrawer imageDrawer = new ImageDrawer("resources/plane2.png");
			planeController = new PlaneController(plane, imageDrawer, new GameVector());
		}
		return planeController;
	}

	public void move() {
		Point mousePoint = MouseInfo.getPointerInfo().getLocation();

        if(mousePoint.x - 40 > this.gameObject.getX()) {
            this.gameVector.dx = 5;
        } else if(mousePoint.x -30 < this.gameObject.getX()) {
        	this.gameVector.dx = -5;
        } else {
        	this.gameVector.dx = 0;
        }

        if(mousePoint.y - 35 > this.gameObject.getY()) {
        	this.gameVector.dy = 5;
        } else if(mousePoint.y - 25 < this.gameObject.getY()) {
        	this.gameVector.dy = -5;
        } else {
        	this.gameVector.dy = 0;
        }
	}
	
	public void shot() {
        	for(int i = 0; i <= 40; i+=40) {
        		if (this.bulletControllerManager.size() < MAX_BULLET) {
                    Bullet bullet = new Bullet(
                            this.gameObject.getX() + (Plane.WIDTH - Bullet.WIDTH) / 2  - 25 + i,
                            this.gameObject.getY(),
                            Bullet.WIDTH,
                            Bullet.HEIGHT);
                    ImageDrawer bulletDrawer = new ImageDrawer("resources/rocket.png");
                    BulletController bulletController = new BulletController(bullet, bulletDrawer, new GameVector());
                    this.bulletControllerManager.add(bulletController);
            	}
        	}
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
		move();
		
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
        }
		else if(c instanceof MineController || c instanceof TankController ) {
	    }
		else if(c instanceof EnemyController ) {
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
		PlaneController.planeController = null;
	}

}
