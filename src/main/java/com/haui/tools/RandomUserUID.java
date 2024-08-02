package com.haui.tools;

import java.util.UUID;

public class RandomUserUID {
	public static String getRandomID() {
		UUID uuid = UUID.randomUUID();
		return uuid.toString();
	}
}
