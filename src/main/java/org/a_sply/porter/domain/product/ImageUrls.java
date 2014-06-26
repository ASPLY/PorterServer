package org.a_sply.porter.domain.product;

import java.util.List;

public class ImageUrls {
	
	private String listImageUrl;
	private List<String> normalImageUrls;
	private List<String> zoomInImageUrls;
	
	public ImageUrls(String listImageUrl, List<String> normalImageUrls, List<String> zoomInImageUrls) {
		super();
		this.listImageUrl = listImageUrl;
		this.normalImageUrls = normalImageUrls;
		this.zoomInImageUrls = zoomInImageUrls;
	}
	
	public String getListImageUrl() {
		return listImageUrl;
	}
	public void setListImageUrl(String listImageUrl) {
		this.listImageUrl = listImageUrl;
	}
	public List<String> getNormalImageUrls() {
		return normalImageUrls;
	}
	public void setNormalImageUrls(List<String> normalImageUrls) {
		this.normalImageUrls = normalImageUrls;
	}
	public List<String> getZoomInImageUrls() {
		return zoomInImageUrls;
	}
	public void setZoomInImageUrls(List<String> zoomInImageUrls) {
		this.zoomInImageUrls = zoomInImageUrls;
	}
}
