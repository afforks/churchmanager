package br.com.churchmanager.seguranca;

import org.springframework.security.authentication.encoding.Md5PasswordEncoder;

public class MyEncoder extends Md5PasswordEncoder {
	public String encodePassword(String rawPass, Object salt) {
		return super.encodePassword(rawPass, salt);
	}
}