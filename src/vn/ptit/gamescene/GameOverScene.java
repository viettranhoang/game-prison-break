package vn.ptit.gamescene;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import vn.ptit.common.GameConfig;
import vn.ptit.model.Score;

/**
 * Created by nguye on 9/29/2016.
 */
public class GameOverScene extends GameScene {
    private Image background;

    public GameOverScene() {
        try {
            background = ImageIO.read(new File("resources/game_over.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {

    }

    @Override
    public void paint(Graphics g) {
        g.drawImage(background, 0, 0,
                GameConfig.getSingleton().getScreenWidth(),
                GameConfig.getSingleton().getScreenHeight(),
                null);
        g.setFont(new Font("TimesRoman", Font.PLAIN, 65));
        g.drawString(Score.scorePlayer + "", 570, 480);
    }

    @Override
    public void onKeyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_ENTER) {
            changeGameScene(EGameSceneType.MENU);
        }
    }

    @Override
    public void onKeyReleased(KeyEvent e) {

    }


	
}
