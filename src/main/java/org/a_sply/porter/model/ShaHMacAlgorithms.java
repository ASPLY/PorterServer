package org.a_sply.porter.model;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.security.crypto.codec.Base64;
import org.springframework.util.StringUtils;

public class ShaHMacAlgorithms implements HMacAlgorithms {

	private static final int DEFAULT_ENCRYPTION_STRENGTH = 256;
	private static final String key = "u0Jp!83L7ecBv@33urljyeo!qg6aG4#P";
	private static final String ENCODING_FOR_ENCRYPTION = "UTF-8";

	private boolean encodeHashAsBas64 = false;
	private String algorithm;

	public ShaHMacAlgorithms() {
		this(DEFAULT_ENCRYPTION_STRENGTH);
	}

	/**
	 * Initialize the ShaPasswordEncoder with a given SHA stength as supported
	 * by the JVM EX:
	 * <code>HMacShaPasswordEncoder encoder = new HMacShaPasswordEncoder(256);</code>
	 * initializes with SHA-256
	 * 
	 * @param strength
	 *            EX: 1, 256, 384, 512
	 */
	public ShaHMacAlgorithms(int strength) {
		this(strength, false);
	}

	public ShaHMacAlgorithms(int strength, boolean encodeHashAsBase64) {
		this("HmacSHA" + String.valueOf(strength), encodeHashAsBase64);
	}

	public ShaHMacAlgorithms(String algorithm, boolean encodeHashAsBase64) {
		this.algorithm = algorithm;
		this.encodeHashAsBas64 = encodeHashAsBase64;
		// validity Check
		getMac();

	}

	protected final Mac getMac() throws IllegalArgumentException {
		try {
			return Mac.getInstance(algorithm);
		} catch (NoSuchAlgorithmException e) {
			throw new IllegalArgumentException("No such algorithm ["
					+ algorithm + "]");
		}
	}

	public String encrypt(String rawDataToBeEncrypted) {
		byte[] hmacData = null;
		if (rawDataToBeEncrypted != null) {
			try {
				SecretKeySpec secretKey = new SecretKeySpec(
						key.getBytes(ENCODING_FOR_ENCRYPTION), this.algorithm);
				Mac mac = getMac();
				mac.init(secretKey);
				hmacData = mac.doFinal(rawDataToBeEncrypted.toString()
						.getBytes(ENCODING_FOR_ENCRYPTION));

				if (encodeHashAsBas64) {
					return new String(Base64.encode(hmacData),
							ENCODING_FOR_ENCRYPTION);
				} else {
					return new String(hmacData, ENCODING_FOR_ENCRYPTION);
				}

			} catch (InvalidKeyException ike) {
				throw new RuntimeException("Invalid Key while encrypting.", ike);
			} catch (UnsupportedEncodingException e) {
				throw new RuntimeException(
						"Unsupported Encoding while encrypting.", e);
			}
		}
		return "";
	}

	public boolean isValid(String enc, String raw) {
		if (!StringUtils.hasText(enc) || !StringUtils.hasText(raw)) {
			return false;
		}
		String pass1 = enc;
		String pass2 = encrypt(raw);

		return equals(pass1, pass2);
	}

	public boolean equals(String expected, String actual) {
		byte[] expectedBytes = null;
		byte[] actualBytes = null;
		try {
			expectedBytes = expected.getBytes(ENCODING_FOR_ENCRYPTION);
			actualBytes = actual.getBytes(ENCODING_FOR_ENCRYPTION);
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(
					"Unsupported Encoding while encrypting.", e);
		}

		int expectedLength = expectedBytes == null ? -1 : expectedBytes.length;
		int actualLength = actualBytes == null ? -1 : actualBytes.length;
		if (expectedLength != actualLength) {
			return false;
		}

		int result = 0;
		for (int i = 0; i < expectedLength; i++) {
			result |= expectedBytes[i] ^ actualBytes[i];
		}
		return result == 0;
	}

}
