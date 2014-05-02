package org.a_sply.porter.model;

public interface HMacAlgorithms {

	public String encrypt(String rawDataToBeEncrypted);

	public boolean isValid(String enc, String raw);

	boolean equals(String expected, String actual);

}
