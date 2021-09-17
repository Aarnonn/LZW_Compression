import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.*;

public class Decoder {
	
	//private static double maxTableSize;
	private static String decodedOutputFileName = "decoded.txt";
	
	public static String decode (String inputFile, int maxDictSize) throws IOException{
		//maxTableSize = Math.pow(2, bit_Length);
		
		String[] compressedValuesStr = null;
		List<Integer> compressedValues = new ArrayList<Integer>();
		int dictSize = 255;
		
		String inputIntCodes = "";
		BufferedReader br = null;
		br = new BufferedReader(new FileReader(inputFile));
		inputIntCodes = br.readLine();
        br.close();
        compressedValuesStr = inputIntCodes.split(" ");
        for (String str: compressedValuesStr) {
        	int num = Integer.parseInt(str);
        	compressedValues.add(num);
        }
        
        Map<Integer, String> dict = new HashMap<Integer, String>();
 		for (int i = 0; i < 255; i++)
 			dict.put(i, "" + (char) i);
 		
 		String oldStr = "" + compressedValues.get(0);
 		StringBuffer decodedValues = new StringBuffer();
 		
 		String newStr = null;
 		for (int key : compressedValues) {
 			if (dict.containsKey(key))
 				newStr = dict.get(key);
 			else 
 				newStr = oldStr + oldStr.charAt(0);
 			decodedValues.append(newStr);
 			
 			if(dictSize<maxDictSize)
 				dict.put(dictSize++, oldStr + newStr.charAt(0));
 			
 			oldStr = newStr;
 		}
        createFile(decodedValues.toString());
 		return(decodedValues.toString());
	}
	
	private static void createFile(String decodedValues) throws IOException{
		FileWriter writer = new FileWriter(decodedOutputFileName);
		BufferedWriter bw = new BufferedWriter(writer);
		
		try {
			bw.write(decodedValues);
		}catch (IOException e) {
			e.printStackTrace();
		}
		bw.flush();
		bw.close();
	}
	
	public static void main(String[] args) throws IOException {
		System.out.println(decode("intcodesOutput.txt", 512));
	}
		
}
