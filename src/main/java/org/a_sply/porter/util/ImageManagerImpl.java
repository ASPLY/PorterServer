package org.a_sply.porter.util;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.a_sply.porter.domain.Image;
import org.springframework.web.multipart.MultipartFile;

public class ImageManagerImpl implements ImageManager {

	private static final String ORIGINAL_DIR_NAME = "original/";
	private static final String ARTICLE_LIST_THUMBNAIL_DIR_NAME = "article_list_thumbnail/";
	private static final String ARTICLE_THUMBNAIL_DIR_NAME = "article_thumbnail/";

	public static final String STORAGE_IMAGES_DIR = "/storage/images/";
	public static final String STORAGE_IMAGES_ORIGINAL_DIR = STORAGE_IMAGES_DIR
			+ ORIGINAL_DIR_NAME;
	public static final String STORAGE_IMAGES_ARTICLE_THUMBNAIL_DIR = STORAGE_IMAGES_DIR
			+ ARTICLE_THUMBNAIL_DIR_NAME;
	public static final String STORAGE_IMAGES_ARTICLE_LIST_THUMBNAIL_DIR = STORAGE_IMAGES_DIR
			+ ARTICLE_LIST_THUMBNAIL_DIR_NAME;

	private FileUploader fileUploader = new FileUploaderImpl();
	private ThumbnailMaker thumbnailMaker = new ThumbnailMakerImpl();

	@Override
	public List<Image> uploadImageAndMakeThumbnails(
			MultipartFile[] multipartFiles) {
		File[] files = fileUploader.upload(STORAGE_IMAGES_ORIGINAL_DIR,
				multipartFiles);
		File[] articleListThumbnails = thumbnailMaker.make(
				STORAGE_IMAGES_ARTICLE_LIST_THUMBNAIL_DIR, files, 300);
		File[] articleThumbnails = thumbnailMaker.make(
				STORAGE_IMAGES_ARTICLE_THUMBNAIL_DIR, files, 600);

		List<Image> images = new ArrayList<Image>();
		for (int i = 0; i < files.length; i++) {
			Image e = new Image();
			e.setOriginal(files[i].getName());
			e.setArticleThumbnail(articleThumbnails[i].getName());
			e.setArticleListThumbnail(articleListThumbnails[i].getName());
			images.add(e);
		}
		return images;
	}
}
