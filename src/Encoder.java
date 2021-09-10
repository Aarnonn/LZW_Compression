import java.util.*;
import java.io.*;

public class Encoder {
    private HashMap<String, Integer> dictionary;
    BufferedReader in;
    String output;
    int tracker;
    int binary;

    public Encoder (String filename) throws IOException {
        in = new BufferedReader (new FileReader(new File (filename)));
        dictionary = new HashMap<String, Integer>();
        output = "";
        tracker = 256;
        binary = 0;
    }

    public String encode () throws IOException {
        while (true) {
            StringBuilder cur = new StringBuilder (in.read());
            if (in.read() >= 0) {
                String next = in.read();
                if (dictionary.containsKey(cur + next)) {
                    cur.append(next);
                }
                else {
                    output += cur;
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
            binary = (binary * 100000000) + binaryInteger;
        }
    }

}
