package org.a_sply.porter.domain;

import static org.junit.Assert.*;

import org.a_sply.porter.controller.UnitTestUtil;
import org.a_sply.porter.domain.product.ImageFile;
import org.junit.Before;
import org.junit.Test;

public class CDNServerTest {
	
	private CDNServer cdnServer;

	@Before
	public void setUp() {
		cdnServer = new CDNServer();
		cdnServer.setAccessUrl("http://sply.cdn3.cafe24.com");
		cdnServer.setHost("iup.cdn3.cafe24.com");
		cdnServer.login("sply", "digital02");
	}
	
	@Test
	public void upload_성공() {
		ImageFile imageFile = new ImageFile(UnitTestUtil.multipartFileA());
		String url = cdnServer.upload(imageFile.getFile(), "test");
		assertTrue(url.startsWith("http://"));
	}
}
