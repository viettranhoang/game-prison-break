package vn.ptit.gamescene;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import vn.ptit.common.GameConfig;
import vn.ptit.controller.LevelController;
import vn.ptit.controller.PlaneController;
import vn.ptit.model.Score;


public class MenuGameScene extends GameScene {  //Màn hình vào game
    private Image background;  //Màn hình này chỉ có mỗi background thôi
	private JPanel contentPane;
	private Image imgTwoPlayer;
	private Image imgQuit;
	private Image imgEasy;
	private Image imgHard;
	private Image imgHighScore;
	
	private JLabel lbTwoPlayer;
	private JLabel lbQuit;
	private JLabel lbEasy;
	private JLabel lbHard;
	private JLabel lbPlay;
	private JLabel lbPause;
	
    public MenuGameScene(JFrame frame) { //Khoi tao cho cái hình nền
    	
        try {
            this.background = ImageIO.read(new File("resources/menu.jpg"));
            this.imgQuit = ImageIO.read(new File("resources/quit.png"));
            this.imgTwoPlayer = ImageIO.read(new File("resources/two_player.png"));
            this.imgEasy = ImageIO.read(new File("resources/easy.png"));
            this.imgHard = ImageIO.read(new File("resources/hard.png"));
            this.imgHighScore = ImageIO.read(new File("resources/high_score.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		frame.setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lbEasy = new JLabel("");
		lbEasy.setBounds(800, 380, 220, 50);
		contentPane.add(lbEasy);
		lbEasy.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				LevelController.easy();
				changeGameScene(EGameSceneType.PLAY);
			}
		});
		
		lbHard = new JLabel("");
		lbHard.setBounds(800, 450, 220, 50);
		contentPane.add(lbHard);
		lbHard.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				LevelController.hard();
				changeGameScene(EGameSceneType.PLAY);
			}
		});
		
		lbTwoPlayer = new JLabel("");
		lbTwoPlayer.setBounds(800, 520, 220, 50);
		contentPane.add(lbTwoPlayer);
		lbTwoPlayer.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				LevelController.hard();
				PlaneController.isTwoPlayer = true;
				changeGameScene(EGameSceneType.PLAY);
				PlaneController.isTwoPlayer = false;
			}
		});
		
		lbQuit = new JLabel("");
		lbQuit.setBounds(800, 590, 220, 50);
		contentPane.add(lbQuit);
		lbQuit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				frame.dispose();
			}
		});
		
		lbPlay = new JLabel("");
		lbPlay.setBounds(3, 27, 45, 45);
		contentPane.add(lbPlay);
		lbPlay.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
			}

		});
		
		lbPause = new JLabel("");
		lbPause.setBounds(50, 27, 45, 45);
		contentPane.add(lbPause);
		lbPause.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
//				PlayGameScene.isSuspended = true;
			}
		});
		
		frame.revalidate();
    }

 
	@Override
    public void run() {
    
    }

    @Override
    public void paint(Graphics g) { //In mỗi hình nền là được
        g.drawImage(background, 0, 0, GameConfig.getSingleton().getScreenWidth(), GameConfig.getSingleton().getScreenHeight(), null);
		g.drawImage(imgEasy, 800, 400, 250, 77, null);
		g.drawImage(imgHard, 800, 470, 250, 77, null);
		g.drawImage(imgTwoPlayer, 800, 540, 250, 77, null);
		g.drawImage(imgQuit, 800, 610, 250, 77, null);
		g.drawImage(imgHighScore, 920, 30, 70, 70, null);
		g.setFont(new Font("TimesRoman", Font.PLAIN, 35));
        g.drawString(Score.HIGH_SCORE + "", 1000, 80);
    }

    @Override
    public void onKeyPressed(KeyEvent e) { //Ấn enter thì chuyển sang màn hình Play
        if(e.getKeyCode() == KeyEvent.VK_ENTER) {
        	LevelController.easy();
            changeGameScene(EGameSceneType.PLAY);
        }
    }

    @Override
    public void onKeyReleased(KeyEvent e) {
    }

	

	
}
