package org.a_sply.porter.util.file_uploader;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.net.ftp.FTPClient;

public class FTPFileUploader implements FileUploader {

	private static final String HOST = "iup.cdn3.cafe24.com";
	private static final String ACCESS_URL = "http://sply.cdn3.cafe24.com";
	
	private FTPClient ftpClient;
	
	public FTPFileUploader() {
		ftpClient = new FTPClient(); 
		ftpClient.setControlEncoding("UTF-8"); 
		try {
			ftpClient.connect(HOST, 21);
			ftpClient.enterLocalPassiveMode(); 
			ftpClient.setFileType(org.apache.commons.net.ftp.FTP.BINARY_FILE_TYPE); 
			ftpClient.login("sply", "digital02");
		} catch (SocketException e) {
			throw new RuntimeException(e);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
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

	@Override
	public List<UploadResult> upload(List<String> fileNames, List<InputStream> inputStreames, String uploadDir) {
		List<UploadResult> uploadResults = new ArrayList<UploadResult>();
		
		try {
			if(!exist(uploadDir)){
				ftpClient.makeDirectory(uploadDir);
				ftpClient.changeWorkingDirectory(uploadDir);
			}
			
			for(int i=0; i<inputStreames.size(); i++){
				String uniqueFileName = getUniqueFileName(fileNames.get(i));
				ftpClient.storeFile(uniqueFileName, inputStreames.get(i)); // File 업로드
				uploadResults.add(new UploadResult(ACCESS_URL + "/" + uploadDir +"/"+ uniqueFileName, uniqueFileName));
			}
			
			ftpClient.changeToParentDirectory();
			
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		
		return uploadResults;
	}

	private boolean exist(String uploadDir) throws IOException {
		return ftpClient.changeWorkingDirectory(uploadDir);
	}

	@Override
	public List<UploadResult> upload2(List<String> fileNames, List<File> files, String uploadDir) {
		List<UploadResult> uploadResults = new ArrayList<UploadResult>();
		
		try {
			if(!exist(uploadDir)){
				ftpClient.makeDirectory(uploadDir);
				ftpClient.changeWorkingDirectory(uploadDir);
			}
			
			for(int i=0; i<files.size(); i++){
				String uniqueFileName = getUniqueFileName(fileNames.get(i));
				ftpClient.storeFile(uniqueFileName, new FileInputStream(files.get(i))); // File 업로드
				uploadResults.add(new UploadResult(HOST + "/" + uploadDir +"/"+ uniqueFileName, uniqueFileName));
			}
			
			ftpClient.changeToParentDirectory();
			
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		
		return uploadResults;
	}

}
