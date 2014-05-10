package org.a_sply.porter.model;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;

import org.a_sply.porter.controller.UnitTestUtil;
import org.a_sply.porter.util.FileUploader;
import org.a_sply.porter.util.FileUploaderImpl;
import org.apache.commons.fileupload.disk.DiskFileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.junit.Test;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

public class FileUploaderTest {

	private FileUploader fileUploader = new FileUploaderImpl();

	@Test
	public void upload_성공() {
		MultipartFile[] multipartFiles = new MultipartFile[] {
				UnitTestUtil.multipartFile(UnitTestUtil.IMAGES_A_TEST_JPG),
				UnitTestUtil.multipartFile(UnitTestUtil.IMAGES_B_TEST_JPG) };
		File[] files = fileUploader.upload(UnitTestUtil.TEST_STORAGE,
				multipartFiles);
		assertThat(files.length, is(multipartFiles.length));
	}

}
