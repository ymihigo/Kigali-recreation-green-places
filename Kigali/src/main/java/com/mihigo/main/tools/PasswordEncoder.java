package com.mihigo.main.tools;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Component;

@Component
public class PasswordEncoder {
public String hashPassword(String password) {
	return DigestUtils.sha256Hex(password);
}
}
