package org.a_sply.porter.util;

import java.io.IOException;
import java.io.InputStream;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.List;

import org.a_sply.porter.controller.UnitTestUtil;
import org.a_sply.porter.util.file_uploader.FTPFileUploader;
import org.a_sply.porter.util.file_uploader.FileUploader;
import org.apache.commons.net.ftp.FTPClient;
import org.junit.Before;
import org.junit.Test;
import org.springframework.web.multipart.MultipartFile;

public class FileUploaderTest {

	private FileUploader fileUploader = new FTPFileUploader();

	@Test
	public void upload_성공() throws IOException {
		
		MultipartFile[] multipartFiles = new MultipartFile[] {UnitTestUtil.multipartFile(UnitTestUtil.IMAGES_A_TEST_JPG), UnitTestUtil.multipartFile(UnitTestUtil.IMAGES_B_TEST_JPG) };
		List<InputStream> inputStreams = new ArrayList<InputStream>();
		List<String> names = new ArrayList<String>();
		
		for (MultipartFile multipartFile : multipartFiles) {
			inputStreams.add(multipartFile.getInputStream());
			names.add(multipartFile.getOriginalFilename());
		}
		
		//fileUploader.upload(names, inputStreams ,"aa");
	}

}
