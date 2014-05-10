package org.a_sply.porter.util;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.name.Rename;

public class ThumbnailMakerImpl implements ThumbnailMaker {
	

	@Override
	public File[] makeAndSave(String storageDir, File[] files, int size) {
		File dir = new File(storageDir);

		try {
			return Thumbnails.of(files).size(size, size).asFiles(dir, Rename.NO_CHANGE).toArray(new File[0]);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public File[] make(File[] files, int size) {
		try {
			return Thumbnails.of(files).size(size, size).asFiles(Rename.NO_CHANGE).toArray(new File[0]);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public List<InputStream> make(List<InputStream> inputStreams, int size) {
		try {
			List<BufferedImage> bufferedImages = Thumbnails.of(inputStreams.toArray(new InputStream[0])).size(size, size).asBufferedImages();
			List<InputStream> byteArrayInputStreams = new ArrayList<InputStream>();
			
			for (BufferedImage bufferedImage : bufferedImages){
				ByteArrayOutputStream byteArrayOutputStream=new ByteArrayOutputStream();
				ImageIO.write(bufferedImage, "jpg", byteArrayOutputStream);
				byte[] imageBytes=byteArrayOutputStream.toByteArray();
				byteArrayInputStreams.add(new ByteArrayInputStream(imageBytes));
			}
			
			return byteArrayInputStreams;	
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}
