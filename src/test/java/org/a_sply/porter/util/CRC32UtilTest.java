package org.a_sply.porter.util;

import static org.junit.Assert.*;

import org.junit.Test;

public class CRC32UtilTest {

	@Test
	public void test() {
		System.out.println(CRC32Util.crcValue("1111@naver.com"));
	}

}
