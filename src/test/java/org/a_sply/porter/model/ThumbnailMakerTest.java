package org.a_sply.porter.model;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.io.File;

import org.a_sply.porter.controller.UnitTestUtil;
import org.a_sply.porter.util.ThumbnailMaker;
import org.a_sply.porter.util.ThumbnailMakerImpl;
import org.junit.Test;

public class ThumbnailMakerTest {

	private ThumbnailMaker thumbnailMaker = new ThumbnailMakerImpl();

	@Test
	public void make_성공() {
		// when
		File files[] = new File[] { new File(UnitTestUtil.IMAGES_A_TEST_JPG),
				new File(UnitTestUtil.IMAGES_B_TEST_JPG) };

		// given
		File result[] = thumbnailMaker.make(
				UnitTestUtil.TEST_STORAGE_ARTICLE_THUMBNAIL_DIR, files, 300);

		// then
		assertThat(files.length, is(result.length));
	}

}
