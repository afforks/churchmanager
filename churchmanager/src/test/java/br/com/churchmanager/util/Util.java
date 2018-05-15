package br.com.churchmanager.util;

public class Util {

	public static void aguardar(int segundos) {
		try {
			Thread.sleep(segundos * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
