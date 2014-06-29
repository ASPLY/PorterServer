package org.a_sply.porter.domain.product;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public class MultipartImageFile {
	
	private MultipartFile listImage;
	private List<MultipartFile> nomalImages;
	private List<MultipartFile> zoomInImages;
	
	public MultipartFile getListImage() {
		return listImage;
	}
	public void setListImage(MultipartFile listImage) {
		this.listImage = listImage;
	}
	public List<MultipartFile> getNomalImages() {
		return nomalImages;
	}
	public void setNomalImages(List<MultipartFile> nomalImages) {
		this.nomalImages = nomalImages;
	}
	public List<MultipartFile> getZoomInImages() {
		return zoomInImages;
	}
	public void setZoomInImages(List<MultipartFile> zoomInImages) {
		this.zoomInImages = zoomInImages;
	}
}
