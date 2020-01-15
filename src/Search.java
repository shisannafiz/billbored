import java.net.*;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.imageio.ImageIO;

public class Search {
		
	public static int id = 1;

	
	public Search(Storage ht) { }	
	
	//searches both articles and media
	//adds obtained information into hashtable
	public static void startBoth(String query, Storage ht) throws IOException {
		ArrayList<String> list = new ArrayList<String>(); 
		String site = both(query);
		URLConnection conn = connect(site);
//		readLine(conn);
		ArrayList<String> results = initial(conn);
		
		int count = 0;
		for(String result : results) {
			Date date = new Date();
			SimpleDateFormat formatter= new SimpleDateFormat("MM-dd-yyyy HH:mm:ss");  
			URLConnection url = connect(result);
			list = ht.lister(Integer.toString(id), "USER", formatter.format(date), query, title(url));
			ht.insert(Storage.inventory, query + Integer.toString(count), list);
			count++;
			id++;
		}
	} //startBoth

	//searches articles 
	//adds obtained information into hashtable
	public static void startArticle(String query, Storage ht) throws IOException {
		ArrayList<String> list = new ArrayList<String>(); 
		String site = article(query);
		URLConnection conn = connect(site);
//		readLine(conn);
		ArrayList<String> results = initial(conn);
		
		int count = 0;
		for(String i : results) {
			Date date = new Date();
			SimpleDateFormat formatter= new SimpleDateFormat("MM-dd-yyyy HH:mm:ss"); 
			URLConnection url = connect(i);
			list = ht.lister(Integer.toString(id), "USER", formatter.format(date), query, title(url));
			ht.insert(ht.inventory, query + Integer.toString(count), list);
			count++;
			id++;
		}
	} //startArticle
	
	//searches media
	//adds obtained information into hashtable
	public static void startMedia(String query, Storage ht) throws IOException {
		ArrayList<String> list = new ArrayList<String>(); 
		String site = media(query);
		URLConnection conn = connect(site);
//		readLine(conn);
		ArrayList<String> results = initial(conn);
		
		int count = 0;
		for(String i : results) {
			Date date = new Date();
			SimpleDateFormat formatter= new SimpleDateFormat("MM-dd-yyyy HH:mm:ss"); 
			URLConnection url = connect(i);
			list = ht.lister(Integer.toString(id), "USER", formatter.format(date), query, title(url));
			ht.insert(ht.inventory, query + Integer.toString(count), list);
			count++;
			id++;
		}
	} //startMedia

	public static String both(String query) {
		String path = "https://www.billboard.com/search/" + query;
		return path;
	} //search
	
	public static String article(String query) {
		String path = "https://www.billboard.com/search/" + query + "?type=article";
		return path;
	} //article
	
	public static String media(String query) {
		String path = "https://www.billboard.com/search/" + query + "?type=media";
		return path;
	} //media
	
	//connects to URL 
	public static URLConnection connect(String site) throws IOException {
		URL url = new URL(site);
		URLConnection connection = url.openConnection();
		connection.addRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.0)");
		return connection;
	} //connect
	
	//reads website's HTML code line by line and prints it to output file
	public static void readLine(URLConnection uc) throws IOException {
	    String line = " ";
		DataInputStream in = new DataInputStream(uc.getInputStream());
		PrintStream out = new PrintStream(new File("website.txt"));
	    System.setOut(out);
	    
	    for(int i = 0; i < 10000; i++) {
	    	  line = in.readLine();
	          if (line == null) break;
	          System.out.println(line);
	    } 
	} //readLine
	
	//creates a single string of all the HTML code 
	public static StringBuilder HTMLstring(URLConnection uc) throws IOException {
		String encoding = uc.getContentEncoding();
		if (encoding == null) encoding = "ISO-8859-1";
		
		BufferedReader br = new BufferedReader(new InputStreamReader(uc.getInputStream(), encoding));
		StringBuilder sb = new StringBuilder(16384);
		
		try {
			String line;
			while ((line = br.readLine()) != null) {
				sb.append(line);
				sb.append('\n');
			}
		} finally {
			br.close();
		}
		
		return sb;
	} //HTMLstring
	
	//parses out the article links by searching for the href tags and adds each link to array list
	public static ArrayList<String> initial(URLConnection uc) throws IOException {
		String result = " ";
		ArrayList<String> results = new ArrayList<String>(); 
		StringBuilder sb = HTMLstring(uc);
	    Pattern p = Pattern.compile("href=\\\"https://www.billboard.com/articles/([^\\\"]*)\\\"");
		Matcher m = p.matcher(sb);
		
	    while (m.find() == true) {
	    	result = "https://www.billboard.com/articles/" + m.group(1);
	    	results.add(result);
	    }
	    
	    return results;
	} //initial 
	
	//parses out the title tag 
	public static String title(URLConnection uc) throws IOException {
		String title = " ";
		StringBuilder sb = HTMLstring(uc);
	    Pattern p = Pattern.compile("<title>(.*?)</title>");
	    Matcher m = p.matcher(sb);
	    	    
	    if (m.find() == true) title = m.group(1);
	   
	    return title;
	} //title
	
} //search



















