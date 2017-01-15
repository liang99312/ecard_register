/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.luopan.client.util;

import java.util.prefs.*;

/**
 *
 * @author liangxr01
 */
public class RegisteryUtil {
	String[] keys = {"start_date", "end_date", "dq_date"};
	String[] values = {"2016-03-03", "2", "3"};

	//把相应的值储存到变量中去   

	public void writeValue() {
		// HKEY_LOCAL_MACHINE/Software/JavaSoft/prefs下写入注册表值.   
		Preferences pre = Preferences.systemRoot().node("/javaplayer");
		for (int i = 0; i < keys.length; i++) {
//			pre.put(keys[i], values[i]);
			System.out.println(pre.get(keys[i], null));
		}
	}

	public static void main(String[] args) {
		RegisteryUtil reg = new RegisteryUtil();
		reg.writeValue();
	}
}
