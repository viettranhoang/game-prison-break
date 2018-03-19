package vn.ptit;

import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {  
//        GameWindow gameWindow = new GameWindow();
        SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				try {
					new GameWindow();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
    }
}
