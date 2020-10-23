package com.ti2cc.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;

public class BodyParser {
	
	public static HashMap<String, String> parseBody(String body){
		
		HashMap<String, String> obj = new HashMap<String, String>();
		
		for(String pair : body.split("&")) {
			String[] keyValue = pair.split("=");

			try {
				obj.put(URLDecoder.decode(keyValue[0], "UTF-8"), URLDecoder.decode(keyValue[1], "UTF-8"));
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return obj;
	}

}
