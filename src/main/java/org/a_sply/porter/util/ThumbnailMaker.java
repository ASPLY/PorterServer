package org.a_sply.porter.util;

import java.io.File;

/**
 * ThumbnailMaker interface. 
 * @author LCH
 */


public interface ThumbnailMaker {
	
	/**
	 * Make thumbnail images.
	 * @param storageDir directory to save.
	 * @param files files original image files.
	 * @param size size to make thumbnail.
	 * @return thumbnail images in {@link File} class.
	 * @author LCH
	 */

	File[] make(String storageDir, File[] files, int size);

}
