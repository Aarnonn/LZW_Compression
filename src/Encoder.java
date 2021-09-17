import java.util.*;
import java.io.*;

/**
 * Here are the private instance variables. Dictionary is the dictionary,
 * in is the input object
 * output is the StringBuilder of the encoded message in string form.
 * tracker is how I tracked the new dictionary values.
 * binary is the binary integer of the encoded message.
 */

public class Encoder {
    private HashMap<String, Integer> dictionary;
    private BufferedReader in;
    private StringBuilder output;
    private int tracker;
    private StringBuilder binary;
    private FileOutputStream out;


    /**
     * Constructor.
     * @param filename
     * @throws IOException
     */
    public Encoder (String filename) throws IOException {
        in = new BufferedReader (new FileReader(new File (filename)));
        dictionary = new HashMap<String, Integer>();
        output.append("");
        tracker = 256;
        binary.append(0);
    }


    /**
     * The encoding algorithm. I believe this works.
     * @return
     * @throws IOException
     */
    public void encode () throws IOException {
        while (true) {
            String cur = "" + (char) in.read();
            if (in.read() >= 0) {
                String next = "" + (char) in.read();
                if (dictionary.containsKey(cur + next)) {
                    cur = next;
                }
                else {
                    output.append(cur);
                    dictionary.put(cur + next, tracker++);
                    cur = next;
                }
            }
            else
                break;
        }
    }

    /**
     * variable naming is very poor here but they are simple placeholders to go from char to a big binary int.
     */
    public void toBinary () {
        for (int i = 0; i<output.length(); i++) {
            char cur = output.charAt(i);

            //The following three lines is not my code
            Integer a = Character.getNumericValue(cur);
            String b = Integer.toBinaryString(a);
            Integer binaryInteger = Integer.valueOf(b);

            //back to my code :p
            binary.append(binaryInteger);
        }
    }

    public void fileWriter (FileOutputStream out) throws IOException{

        ArrayList <String> bytes = new ArrayList<String>();

        for (int i = 0; i < binary.length(); i = i+8) {

            String str = binary.substring(i, i+8);
            bytes.add (str);

        }

        for (int i = 0; i < bytes.size(); i++) {
            int num = binaryToInt(bytes, i);
            this.out.write ((char)num);
        }

        out.close();

    }

    public int binaryToInt (ArrayList <String> input, int index) {
        int sum = 0;
        String str = input.get(index);
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt (i) == 1) {
                sum += Math.pow (2, i);
            }
            else
                sum += 0;
        }

        return sum;
    }

    public static void main (String [] args) {
        Encoder compressed = new Encoder ("")
    }

}
