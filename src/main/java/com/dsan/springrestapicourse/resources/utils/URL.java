package com.dsan.springrestapicourse.resources.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

public class URL {

	public static List<Integer> covertToList(String str) {

		String[] ids = str.split(",");
		List<Integer> list = new ArrayList<>();

		for (int i = 0; i < ids.length; i++) {
			list.add(Integer.parseInt(ids[i]));
		}

		return list;
	}

	public static String decodeParam(String param) {
		try {
			return URLDecoder.decode(param, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			return "";
		}
	}
}
