package org.a_sply.porter.util;

import java.io.File;
import java.io.InputStream;
import java.util.List;

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

	File[] makeAndSave(String storageDir, File[] files, int size);
	File[] make(File[] files, int size);
	List<InputStream> make(List<InputStream> inputStreams, int size);

}
