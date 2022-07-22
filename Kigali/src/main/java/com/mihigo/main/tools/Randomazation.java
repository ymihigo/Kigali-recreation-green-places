package com.mihigo.main.tools;

import org.springframework.stereotype.Component;

@Component
public class Randomazation {

	
	public String random(int n) {
		String alpha = "ABCDEFGHIJKLMNOPQRSTUVWXYZ" + "1234567890" + "abcdefghijklmnopqrstuvwxyz";
		StringBuilder sb = new StringBuilder(n);

		for (int i = 0; i < n; i++) {
			int index = (int) (alpha.length() * Math.random());
			sb.append(alpha.charAt(index));
		}
		
		return sb.toString();
	}

}
