package org.a_sply.porter.util;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public class MultipartFileUtil {
	
	public static File toFile(MultipartFile multipart) {
		try {
			File tmpFile = new File(System.getProperty("java.io.tmpdir") + System.getProperty("file.separator") + multipart.getOriginalFilename());
			multipart.transferTo(tmpFile);
			return tmpFile;
		} catch (IllegalStateException e) {
			throw new RuntimeException(e);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public static List<File> toFiles(List<MultipartFile> multipartFiles) {
		List<File> files = new ArrayList<File>();
		for (MultipartFile multipartFile : multipartFiles)
			files.add(toFile(multipartFile));
		return files;
	}
}
