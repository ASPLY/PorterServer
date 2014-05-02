package org.a_sply.porter.model;

import java.io.File;

public interface ThumbnailMaker {

	File[] make(String storageDir, File[] files, int size);

}
