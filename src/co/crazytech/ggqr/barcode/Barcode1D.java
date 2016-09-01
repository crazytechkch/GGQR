package co.crazytech.ggqr.barcode;

import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsEnvironment;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class Barcode1D extends JPanel{
	private static final String QR_WEB_DIR = "https://phpmysql-crazytechco.rhcloud.com/barcode/barcode.html";
	
	private void getBarcode(String key, String type, String...options){
		URL url;
		try {
			url = barcodeUrl(key, type, options);
			BufferedImage image = ImageIO.read(url);
			//GraphicsConfiguration config = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDefaultConfiguration();
			//BufferedImage altered = config.createCompatibleImage(image.getWidth()+80, image.getHeight());
			//Graphics2D rect = altered.createGraphics();
			String qrDir = new File("tempqr").getAbsolutePath();
			ImageIO.write(image, "jpeg", new File(qrDir+"/barcode/barcode.jpg"));
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private static URL barcodeUrl(String key, String type, String... options) throws MalformedURLException{
		return new URL("https://phpmysql-crazytechco.rhcloud.com/barcode/barcode.html?"
				+"key="+key
				+"type="+type);
	}

}
