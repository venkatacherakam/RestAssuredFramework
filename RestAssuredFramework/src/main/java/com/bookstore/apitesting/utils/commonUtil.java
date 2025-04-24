package com.bookstore.apitesting.utils;

import java.util.Random;

public class commonUtil {
	  public static int generateRandomInt(int min, int max) {
	        Random random = new Random();
	        return random.nextInt((max - min) + 1) + min;
	    }

}