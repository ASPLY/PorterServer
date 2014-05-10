package org.a_sply.porter.util;

import java.io.File;
import java.io.IOException;

import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.name.Rename;

public class ThumbnailMakerImpl implements ThumbnailMaker {

	@Override
	public File[] make(String storageDir, File[] files, int size) {
		File dir = new File(storageDir);

		try {
			return Thumbnails.of(files).size(size, size)
					.asFiles(dir, Rename.NO_CHANGE).toArray(new File[0]);
		} catch (IOException e) {
		}
		;

		return null;
	}

}
