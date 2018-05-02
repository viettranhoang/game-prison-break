package vn.ptit.model;


public class Score extends GameObject{
	public final static int WIDTH = 50;
    public final static int HEIGHT = 50;
	public static int score = 0;
	public static int scorePlayer = 0;
	public static int HIGH_SCORE = 0;
	
	public Score(int x, int y, int width, int height) {
		super(x, y, width, height);
	}

	public static void setHighScore() {
		if(Score.score > Score.HIGH_SCORE) Score.HIGH_SCORE = Score.score; 
		Score.scorePlayer = Score.score;
        Score.score = 0;
	}
}
