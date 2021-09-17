import java.util.*;
import java.io.*;

public class Encoder {
    private HashMap<String, Integer> dictionary;
    BufferedReader in;
    int dictTracker;

    public Encoder (String filename) throws IOException {
        in = new BufferedReader (new FileReader(new File (filename)));
        dictionary = new HashMap<String, Integer>();
        dictTracker = 256;
    }

    public String encode () throws IOException {
    	for (int i = 0; i < 256; i++)//load ascii table 
            dictionary.put("" + (char)i, i);
    	StringBuilder sb = new StringBuilder ();
    	String cur = "";
        while (in.ready()) {
              String next = ""+(char)in.read();
              String combined = cur + next;
              if (dictionary.containsKey(combined)) {
                    cur = combined;
              }else {
            	  sb.append(dictionary.get(cur) + " ");
                  dictionary.put(combined, dictTracker++);
                  cur = next;
              }
        }
        
        if (!cur.equals("")) {
        	sb.append(dictionary.get(cur));
        }
        return sb.toString();
    }

    public static void main (String [] args) throws IOException {
    	FileWriter outputFile;
    	BufferedWriter writer = null;
    	Encoder coder = new Encoder("lzw-file2.txt");
    	try {
    		outputFile = new FileWriter("intcodesOutput.txt");
    		writer = new BufferedWriter(outputFile);
    		writer.write(coder.encode());
    	}finally {
    		writer.close();
    	}
    	
    }
    
}
