package org.a_sply.porter.util.file_uploader;

import java.io.File;
import java.io.InputStream;
import java.util.List;

/**
 * FileUploader interface. 
 * @author LCH
 */

public interface FileUploader {

	/**
	 * Upload image array.
	 * @param uploadDir directory to upload.
	 * @param files imagefiles on File class.
	 * @author LCH
	 * @return 
	 */
	
	public List<UploadResult> upload(List<String> fileNames, List<InputStream> inputStreames, String uploadDir);
	public List<UploadResult> upload2(List<String> fileNames, List<File> files, String uploadDir);
}
