package org.a_sply.porter.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import org.a_sply.porter.domain.Image;
import org.a_sply.porter.util.file_uploader.FTPFileUploader;
import org.a_sply.porter.util.file_uploader.FileUploader;
import org.a_sply.porter.util.file_uploader.UploadResult;
import org.springframework.web.multipart.MultipartFile;


public class ImageManagerImpl implements ImageManager {

	private static final String ORIGINAL_DIR_NAME = "original";
	private static final String ARTICLE_LIST_THUMBNAIL_DIR_NAME = "article_list_thumbnail";
	private static final String ARTICLE_THUMBNAIL_DIR_NAME = "article_thumbnail";

	private FileUploader fileUploader = new FTPFileUploader();
	private ThumbnailMaker thumbnailMaker = new ThumbnailMakerImpl();

	@Override
	public List<Image> upload(MultipartFile[] multipartFiles) {
		List<String> names = getFileNames(multipartFiles);
		
		List<InputStream> inputStreams = getInputStreams(multipartFiles);
		List<ByteArrayOutputStream> outputs = getOutStreams(inputStreams);
		
		List<InputStream> articleThumbnails = thumbnailMaker.make(copy(outputs), 300);
		List<InputStream> articleListThumbnails = thumbnailMaker.make(copy(outputs), 200);
		
		List<UploadResult> originalUploadResults = fileUploader.upload(names, copy(outputs), ORIGINAL_DIR_NAME);
		List<UploadResult> articleThumbnailUploadResults = fileUploader.upload(names, articleThumbnails, ARTICLE_THUMBNAIL_DIR_NAME);
		List<UploadResult> articleListThumbnailUploadResults = fileUploader.upload(names, articleListThumbnails, ARTICLE_LIST_THUMBNAIL_DIR_NAME);
		
		List<Image> images = new ArrayList<Image>();
		
		for(int i=0; i<originalUploadResults.size(); i++)
			images.add(new Image(originalUploadResults.get(i).getUrl(),
					articleThumbnailUploadResults.get(i).getUrl(), 
					articleListThumbnailUploadResults.get(i).getUrl()));
		
		return images;
	}

	private List<ByteArrayOutputStream> getOutStreams(List<InputStream> inputStreams) {
		List<ByteArrayOutputStream> outputs = new ArrayList<ByteArrayOutputStream>();
		for (InputStream inputStream : inputStreams) {
			ByteArrayOutputStream output = new ByteArrayOutputStream();
			try {
				copy(inputStream, output);
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
			outputs.add(output);
		}
		return outputs;
	}

	private List<InputStream> copy(List<ByteArrayOutputStream> outputs) {
		List<InputStream> result = new ArrayList<InputStream>();
		for (ByteArrayOutputStream byteArrayOutputStream : outputs) 
			result.add(new ByteArrayInputStream(byteArrayOutputStream.toByteArray()));
		return result;
	}


	public static int copy(InputStream input, OutputStream output) throws IOException{
	    byte[] buffer = new byte[256];
	    int count = 0;
	    int n = 0;
	    while (-1 != (n = input.read(buffer))) {
	        output.write(buffer, 0, n);
	        count += n;
	    }
	    return count;
	 }

	private List<InputStream> getInputStreams(MultipartFile[] multipartFiles){
		List<InputStream> inputStreams = new ArrayList<InputStream>();
		for (MultipartFile multipartFile : multipartFiles) {
			try {
				inputStreams.add(multipartFile.getInputStream());
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}
		return inputStreams;
	}
	
	private List<String> getFileNames(MultipartFile[] multipartFiles) {
		List<String> fileNames = new ArrayList<String>();
		for (MultipartFile multipartFile : multipartFiles) 
			fileNames.add(multipartFile.getOriginalFilename());
		return fileNames;
	}
}

