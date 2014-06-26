package org.a_sply.porter.domain;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.SocketException;

import org.apache.commons.net.ftp.FTPClient;

public class CDNServer {
	
	private String host;
	private FTPClient ftpClient;
	private String accessUrl;
	
	public CDNServer() {
	}
	
	public CDNServer(String host, String accessUrl) {
		super();
		this.host = host;
		this.accessUrl = accessUrl;
	}

	public void login(String id, String pw){
		ftpClient = new FTPClient(); 
		ftpClient.setControlEncoding("UTF-8"); 
		try {
			ftpClient.connect(host, 21);
			ftpClient.enterLocalPassiveMode(); 
			ftpClient.setFileType(org.apache.commons.net.ftp.FTP.BINARY_FILE_TYPE); 
			ftpClient.login(id, pw);
		} catch (SocketException e) {
			throw new RuntimeException(e);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	public String upload(File file, String uploadDir){
		String url;
		
		try {
			if(!exist(uploadDir)){
				ftpClient.makeDirectory(uploadDir);
				ftpClient.changeWorkingDirectory(uploadDir);
			}
			
			ftpClient.storeFile(file.getName(), new FileInputStream(file));
			url = accessUrl+ "/" + uploadDir +"/"+ file.getName();
			ftpClient.changeToParentDirectory();
			
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		
		return url;
	}

	private boolean exist(String uploadDir) throws IOException {
		return ftpClient.changeWorkingDirectory(uploadDir);
	}
	
	public String getHost() {
		return host;
	}
	public void setHost(String host) {
		this.host = host;
	}
	public String getAccessUrl() {
		return accessUrl;
	}
	public void setAccessUrl(String accessUrl) {
		this.accessUrl = accessUrl;
	}
	
}
