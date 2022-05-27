package kiwi.mgr;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.swing.ImageIcon;

public class ResourceMgr {
	private static ResourceMgr instance = new ResourceMgr();
	
	public static ResourceMgr getInstance() {
		return instance;
	}
	
	public BufferedImage resizeImage(BufferedImage originImg, int width, int height) throws IOException {
		Image resizedPoster = originImg.getScaledInstance(width, height, Image.SCALE_SMOOTH);
		BufferedImage outputPoster = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		outputPoster.getGraphics().drawImage(resizedPoster, 0, 0, null);
		return outputPoster;
	}
	
	public ImageIcon resizeImageIcon(String path, int width, int height) {
		ImageIcon iconLogo = new ImageIcon(path);
		Image img = iconLogo.getImage();
		Image changeImg = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
		ImageIcon iconChangedLogo = new ImageIcon(changeImg);
		return iconChangedLogo;
	}
}