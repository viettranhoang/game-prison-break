package vn.ptit.common;

import java.util.Random;

public class RandomInt {
	static Random rand = new Random();
	
	
	
	public static int random(int min, int max) {
		return rand.nextInt(max - min + 1) + min;
	}
}
