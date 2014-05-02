package org.a_sply.porter.model;

public class ApiKeyManagerImpl implements ApiKeyManager {

	private static final String DIMITER = "-";
	private HMacAlgorithms hMacAlgorithms = new ShaHMacAlgorithms(256, true);

	@Override
	public String generate(String email) {
		return email + DIMITER + hMacAlgorithms.encrypt(email);
	}

	@Override
	public boolean valid(String apiKey) {
		String raw = apiKey.split(DIMITER)[0];
		String encrypt = apiKey.split(DIMITER)[1];
		return hMacAlgorithms.isValid(encrypt, raw);
	}

	@Override
	public String parse(String apiKey) {
		return apiKey.split(DIMITER)[0];
	}

}
