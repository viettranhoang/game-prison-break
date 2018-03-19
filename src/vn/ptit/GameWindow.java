package vn.ptit;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;
import javax.swing.SwingWorker;

import vn.ptit.common.GameConfig;
import vn.ptit.gamescene.EGameSceneType;
import vn.ptit.gamescene.GameOverScene;
import vn.ptit.gamescene.GameScene;
import vn.ptit.gamescene.GameWinScene;
import vn.ptit.gamescene.IGameSceneListener;
import vn.ptit.gamescene.MenuGameScene;
import vn.ptit.gamescene.PlayGameScene;

@SuppressWarnings("serial")
public class GameWindow extends JFrame implements IGameSceneListener, Runnable {
    GameConfig gameConfig;
    Thread thread;
    Image backbufferImage;
    GameScene gameScene;
    
    public GameWindow() {
//        Utils.playSound("resources/Bg.wav", true);
        this.gameConfig = GameConfig.getSingleton();
        gameScene = new MenuGameScene(this);
        gameScene.setGameSceneListener(this);
        
        this.setVisible(true);
        this.setSize(gameConfig.getScreenWidth(), gameConfig.getScreenHeight());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      
        this.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                gameScene.onKeyPressed(e);
                if(e.getKeyCode() == KeyEvent.VK_R) {
                	resume();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                gameScene.onKeyReleased(e);
            }
        });
        
        this.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				gameScene.onMouseClick(e);
			}
        	
		});

        thread = new Thread(this);
        thread.start();

        
//        SwingWorker<Boolean, Void> sw = new SwingWorker<Boolean, Void>() {
//
//    		@Override
//    		protected Boolean doInBackground() throws Exception {
//    			while (true) {
//    	            try {
//    	                gameScene.run();
//    	                repaint();
//    	                Thread.sleep(gameConfig.getThreadDelay());
//    	                synchronized(this) {
//    	                	while(PlayGameScene.isSuspended) {
//    		                    try {
//    		         				wait();
//    		         			} catch (InterruptedException e) {}
//    	                	}
//    	                }
//    	            } catch (InterruptedException e) {
//    	                e.printStackTrace();
//    	            }
//    	        }
////    			return null;
//    		}
//        	
//        };
//       sw.execute();
        
    }

    @Override
    public void paint(Graphics g) {
        if (backbufferImage == null) {
            backbufferImage = new BufferedImage(this.gameConfig.getScreenWidth(),
                    this.gameConfig.getScreenHeight(), 1);
        }
        Graphics backbufferGraphics = backbufferImage.getGraphics();
        gameScene.paint(backbufferGraphics);
        g.drawImage(backbufferImage, 0, 0, null);
    }

    @Override
    public void run() {
        while (true) {
            try {
                gameScene.run();
                repaint();
                Thread.sleep(this.gameConfig.getThreadDelay());
                synchronized(this) {
	            	while(PlayGameScene.isSuspended) {
	                    try {
	         				wait();
	         			} catch (InterruptedException e) {}
	            	}
                }
                
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    
    

    @Override
    public void changeGameScene(EGameSceneType gameSceneType) {
        switch (gameSceneType) {
            case MENU:
                gameScene = new MenuGameScene(this);
                gameScene.setGameSceneListener(this);
                break;
            case PLAY:
                gameScene = new PlayGameScene();
                gameScene.setGameSceneListener(this);
                break;
            case GAMEOVER:
                gameScene = new GameOverScene();
                gameScene.setGameSceneListener(this);
                break;
            case WIN:
                gameScene = new GameWinScene();
                gameScene.setGameSceneListener(this);
                break;
        }
    }
    
    synchronized void resume() {
	      PlayGameScene.isSuspended = false;
	      notify();
	}
}
