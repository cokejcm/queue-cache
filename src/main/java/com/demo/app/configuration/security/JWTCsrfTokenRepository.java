package com.demo.app.configuration.security;

import java.util.Date;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.csrf.DefaultCsrfToken;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class JWTCsrfTokenRepository implements CsrfTokenRepository {

	//private static final String DEFAULT_CSRF_TOKEN_ATTR_NAME = CSRFConfig.class.getName().concat(".CSRF_TOKEN");

	//private static final Logger LOG = LoggerFactory.getLogger(JWTCsrfTokenRepository.class);
	private byte[] secret;

	public JWTCsrfTokenRepository(byte[] secret) {
		this.secret = secret;
	}

	@Override
	public CsrfToken generateToken(HttpServletRequest request) {
		String id = UUID.randomUUID().toString().replace("-", "");

		Date now = new Date();
		Date exp = new Date(System.currentTimeMillis() + 1000*30); // 30 seconds

		String token = Jwts.builder()
				.setId(id)
				.setIssuedAt(now)
				.setNotBefore(now)
				.setExpiration(exp)
				.signWith(SignatureAlgorithm.HS256, secret)
				.compact();

		return new DefaultCsrfToken("X-CSRF-TOKEN", "_csrf", token);
	}

	@Override
	public CsrfToken loadToken(HttpServletRequest arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveToken(CsrfToken arg0, HttpServletRequest arg1, HttpServletResponse arg2) {
		// TODO Auto-generated method stub

	}


}
