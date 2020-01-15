import java.awt.image.*;
import javax.imageio.*;
import java.io.*;
import java.net.URL;
import java.net.MalformedURLException;

public class Image {
	URL url = null;
	File outputImageFile = null;
	public static BufferedImage image = null;
	
	//gets image from a URL
	public static void fetchImageFromURL (URL url) {
		try {
		image = ImageIO.read(url);
		} catch (IOException e) {
		} 
	} // fetchImageFromURL
	
	//connects to URL and sets image
    public static void start() throws MalformedURLException, IOException {
    	URL url = new URL("https://i1.wp.com/news.belmont.edu/wp-content/uploads/2018/12/billboard.jpg");
    	File outputImageFile = new File("logo.bmp");
    	fetchImageFromURL(url);
    	ImageIO.write(image, "jpg", outputImageFile);
    } // start
    
}//Image
