package org.a_sply.porter.model;

public interface ApiKeyManager {

	String generate(String email);

	boolean valid(String apiKey);

	String parse(String apiKey);

}
