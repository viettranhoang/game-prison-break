package vn.ptit.gamescene;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

/**
 * Created by nguye on 9/12/2016.
 */
public abstract class GameScene {  //Class chung cho các màn hình

    private IGameSceneListener gameSceneListener;

    public void setGameSceneListener(IGameSceneListener gameSceneListener) {
        this.gameSceneListener = gameSceneListener;
    }

    protected void changeGameScene(EGameSceneType gameSceneType) {
        if(gameSceneListener != null) {
            gameSceneListener.changeGameScene(gameSceneType);
        }
    }


    //1 màn hình cần 5 hàm ở dưới
    public abstract void run();

    public abstract void paint(Graphics g);

    public abstract void onKeyPressed(KeyEvent e);

    public abstract void onKeyReleased(KeyEvent e);

	public void onMouseClick(MouseEvent e) {
	}

}
