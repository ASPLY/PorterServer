package org.a_sply.porter.model;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.multipart.MultipartFile;

public class FileUploaderImpl implements FileUploader {

	private FileOutputStream out;

	@Override
	public File[] upload(String uploadDir, MultipartFile[] multipartFiles) {
		List<File> files = new ArrayList<File>();
		try {
			for (MultipartFile multipartFile : multipartFiles) {
				String uniqueFileName = getUniqueFileName(multipartFile.getOriginalFilename());
				String pathname = uploadDir + uniqueFileName;
				out = new FileOutputStream(pathname);
				BufferedInputStream bis = new BufferedInputStream(
						multipartFile.getInputStream());
				byte[] buffer = new byte[8106];
				int read;

				while ((read = bis.read(buffer)) > 0) {
					out.write(buffer, 0, read);
				}
				IOUtils.closeQuietly(out);
				files.add(new File(pathname));
			}
		} catch (IOException ioe) {
			throw new RuntimeException(ioe);
		} finally {
			IOUtils.closeQuietly(out);
		}
		return files.toArray(new File[0]);
	}

	private String getUniqueFileName(String fileName) {
		String uniqueFilename = "";
		String uuid = StringUtils.remove(UUID.randomUUID().toString(), '-')
				.toUpperCase();

		int fileIndex = StringUtils.lastIndexOf(fileName, '.');

		// 파일명과 확장자를 분리
		if (fileIndex != -1) {
			String extension = StringUtils.substring(fileName, fileIndex + 1);
			uniqueFilename = uuid + "." + extension.toLowerCase();
		} else {
			uniqueFilename = uuid;
		}
		return uniqueFilename;
	}

}
