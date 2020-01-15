import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Hashtable;
import java.util.Set;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;

public class Storage {
	static Hashtable<String, ArrayList<String>> inventory = new Hashtable<String, ArrayList<String>>(10, 10);
	
	//constructor 
	public Storage() { }
		
	//adds an entry into hashtable 
	public void insert (Hashtable<String, ArrayList<String>> ht, String key, ArrayList<String> value) {
		ht.put(key, value);
	}
	
	//removes a specified entry from hashtable
	public void delete(Hashtable<String, ArrayList<String>> ht, String key) {
		ht.remove(key);
	}	
		
	//creates array list of all the information
	public ArrayList<String> lister(String id, String user, String time, String query, String data) {
		ArrayList<String> list = new ArrayList<String>(); 
		list.add(id);
		list.add(user);
		list.add(time);
		list.add(query);
		list.add(data);
		return list;
	}
	
	//returns all the data in the hashtable 
	public void getData() {
		ArrayList<String> list = new ArrayList<String>(); 
        Set<String> keys = inventory.keySet();
        for(String key: keys){
            list = inventory.get(key);
            System.out.println(key + "  " + list.get(3));
        } 
	}
	
	//returns all data in the hashtable 
	public static String[][] listArray() {
		String[][] arrays = new String[inventory.size()][];
		ArrayList<String> list = new ArrayList<String>(); 
	    Set<String> keys = inventory.keySet();
	    int count = 0;
        for(String key: keys) {
            list = inventory.get(key);
            String[] array = list.toArray(new String[list.size()]);
            arrays[count] = array;
            count++;
        } 
        return arrays;
	}

	//searches data in hashtable for specific query and prints out results to external file
	public static void find(String query) throws FileNotFoundException {
		ArrayList<String> list = new ArrayList<String>(); 
		ArrayList<String> results = new ArrayList<String>(); 
	    Set<String> keys = inventory.keySet();
		PrintStream out = new PrintStream(new File("results.txt"));
	    System.setOut(out);

		for(String key: keys) {
			if(query.equals(key.substring(0, key.length() - 1))) {
				list = inventory.get(key);
				results.add(list.get(4));
			}
	    } 
		for(String result: results) {
			System.out.println(result);
		}
	 }
	
}


