package org.a_sply.porter.model;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import java.util.List;

import org.a_sply.porter.controller.UnitTestUtil;
import org.a_sply.porter.domain.Image;
import org.junit.Test;
import org.springframework.web.multipart.MultipartFile;

public class ImageManagerTest {

	private ImageManager imageManager = new ImageManagerImpl();

	@Test
	public void uploadAndThumbnail_성공() {
		MultipartFile[] multipartFiles = new MultipartFile[] {
				UnitTestUtil.multipartFile(UnitTestUtil.IMAGES_A_TEST_JPG),
				UnitTestUtil.multipartFile(UnitTestUtil.IMAGES_B_TEST_JPG) };
		List<Image> images = imageManager
				.uploadImageAndMakeThumbnails(multipartFiles);
		assertThat(images.size(), is(multipartFiles.length));
		for (Image image : images) {
			System.out.println(image.getOriginal());
			System.out.println(image.getArticleListThumbnail());
			System.out.println(image.getArticleThumbnail());
		}
	}

}
