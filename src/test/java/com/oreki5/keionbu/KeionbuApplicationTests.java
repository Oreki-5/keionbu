package com.oreki5.keionbu;

import javax.crypto.SecretKey;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import io.jsonwebtoken.Jwts;
import jakarta.xml.bind.DatatypeConverter;

@SpringBootTest

class KeionbuApplicationTests {

	@Test
	void contextLoads() {
		
	}

	@Test
	void genKey() {
		SecretKey key = Jwts.SIG.HS512.key().build();
		String h = key.getEncoded().toString();
		String secret = DatatypeConverter.printHexBinary(key.getEncoded());
		System.out.println(h);
		System.out.println(secret);
	}

}
