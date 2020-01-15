import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Set;

public class Master {

	public static void main(String[] args) throws IOException {
		Storage storage = new Storage();
		String[] files = new String[2];
		
		//command line flags and no GUIs
		//reads input file line by line 
		//prints results into output file 
		if(args.length > 0) {
			for(int i = 0; i < args.length; i++) {
				if(args[i].equals("-i")) { files[0] = args[i+1]; }
				if(args[i].equals("-o")) { files[1] = args[i+1]; }
				if(args[i].equals("-p")) { 
					ArrayList<String> lines = new ArrayList<String>(); 
					ArrayList<String> list = new ArrayList<String>(); 
					ArrayList<String> results = new ArrayList<String>(); 
					
					lines = read(files[0]);
					for(String line : lines) {
						Search.startArticle(line, storage);
					}
					
				    Set<String> keys = Storage.inventory.keySet();
					PrintStream out = new PrintStream(new File(files[1]));					
				    System.setOut(out);

				    for(String key: keys) {
				    	list = Storage.inventory.get(key);
				    	results.add(list.get(4));
				    }
				    for(String result: results) {
				    	System.out.println(result);
				    }
				}
			}
		} 
		else {
//			searchGUI t = new searchGUI(storage);
			Search s = new Search(storage);
//			storageGUI st = new storageGUI(storage);
//			viewGUI v = new viewGUI(storage);
			masterGUI m = new masterGUI(storage);
		}	
	}
	
	//reads input file using buffered reader
	//adds each line from file to array list
	public static ArrayList<String> read(String file) {
    	String line = "";
		BufferedReader br = null;
		ArrayList<String> list = new ArrayList<String>(); 
		
	    try {
            br = new BufferedReader(new FileReader(file));
            while ((line = br.readLine()) != null) {
            	list.add(line);           	
            }
        } catch (FileNotFoundException e) {
             e.printStackTrace();
        } catch (IOException e) {
             e.printStackTrace();
        } finally {
             if(br != null) {
                 try {
                     br.close();
                 } catch (IOException e) {
                     e.printStackTrace();
                 }
             }
       	}
	    return list;
	 } //read

}

