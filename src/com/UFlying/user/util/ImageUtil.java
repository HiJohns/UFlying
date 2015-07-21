package com.UFlying.user.util;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

public class ImageUtil {

	private static final int MAX_WIDTH = 500;

	public static void resize(File originImgFile, File targetImgFile) throws Exception {
		BufferedImage image = ImageIO.read(originImgFile);
		int width = image.getWidth(), height = image.getHeight();
		int newWidth = width, newHeight = height;
		if (width > MAX_WIDTH) {
			newWidth = MAX_WIDTH;
			newHeight = height * MAX_WIDTH / width;
		}
		Image scaledImage = image.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
		BufferedImage newImage = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_RGB);
		newImage.getGraphics().drawImage(scaledImage, 0, 0, null);
		ImageIO.write(newImage, "JPEG", targetImgFile);
	}

}
