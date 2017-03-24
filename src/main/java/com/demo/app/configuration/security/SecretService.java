package com.demo.app.configuration.security;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.crypto.SecretKey;

import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwsHeader;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SigningKeyResolver;
import io.jsonwebtoken.SigningKeyResolverAdapter;
import io.jsonwebtoken.impl.TextCodec;
import io.jsonwebtoken.impl.crypto.MacProvider;
import io.jsonwebtoken.lang.Assert;

@Service
public class SecretService {

	private Map<String, String> secrets = new HashMap<>();

	private SigningKeyResolver signingKeyResolver = new SigningKeyResolverAdapter() {
		@SuppressWarnings("rawtypes")
		@Override
		public byte[] resolveSigningKeyBytes(JwsHeader header, Claims claims) {
			return TextCodec.BASE64.decode(secrets.get(header.getAlgorithm()));
		}
	};

	@PostConstruct
	public void setup() {
		refreshSecrets();
	}

	public SigningKeyResolver getSigningKeyResolver() {
		return signingKeyResolver;
	}

	public Map<String, String> getSecrets() {
		return secrets;
	}

	public void setSecrets(Map<String, String> secrets) {
		Assert.notNull(secrets);
		Assert.hasText(secrets.get(SignatureAlgorithm.HS512.getValue()));
		this.secrets = secrets;
	}

	public byte[] getHS512SecretBytes() {
		return TextCodec.BASE64.decode(secrets.get(SignatureAlgorithm.HS512.getValue()));
	}


	public Map<String, String> refreshSecrets() {
		SecretKey key = MacProvider.generateKey(SignatureAlgorithm.HS512);
		secrets.put(SignatureAlgorithm.HS512.getValue(), TextCodec.BASE64.encode(key.getEncoded()));
		return secrets;
	}
}
