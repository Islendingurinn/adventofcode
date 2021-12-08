package advent.e2021;

import advent.Advent;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Day8 extends Advent {

    public static void main(String[] args) throws IOException {
        new Day8();
    }

    protected Day8() throws IOException {
        super(8);
    }

    @Override
    protected void setup() {

    }

    @Override
    protected Object solveFirst() {
        /*
            1 = 2,
            4 = 4,
            7 = 3,
            8 = 7
         */
        List<Integer> unique = new ArrayList<>(Arrays.asList(2, 4, 3, 7));

        int count = 0;
        for(String in : getInput()){
            String actual = in.split(" \\| ")[1];
            for(String split : actual.split(" ")){
                if(unique.contains(split.length())){
                    count++;
                }
            }
        }

        return count;
    }

    private HashMap<Character, Integer> createMap(){
        HashMap<Character, Integer> map = new HashMap<>();
        map.put('a', 0);
        map.put('b', 0);
        map.put('c', 0);
        map.put('d', 0);
        map.put('e', 0);
        map.put('f', 0);
        map.put('g', 0);
        return map;
    }

    @Override
    protected Object solveSecond() {
        char[] chars = {'a', 'b', 'c', 'd', 'e', 'f', 'g'};

        long total = 0;
        for (String in : getInput()){
            HashMap<Character, Character> mapping = new HashMap<>();
            String[] signals = in.split(" \\| ")[0].split(" ");

            // To use in filling the blanks
            String one = "";
            String seven = "";
            for(String s : signals){
                if(s.length() == 2)
                    one = s;
                else if(s.length() == 3)
                    seven = s;
            }

            // We know that the one character in seven that's not in one is in position 'a'
            for(char c : seven.toCharArray())
                if(!one.contains("" + c))
                    mapping.put(c, 'a');

            // How often does each character pop up in length 5 and 6 strings
            /*
                length 5: 2, 3, 5
                length 6: 0, 6, 9

                length five has two interesting traits:
                2 has position 'e' unique to 3, 5
                5 has position 'b' unique to 3, 5

                length six has three interesting traits
                0 has position 'd' missing
                6 has position 'c' missing
                9 has position 'e' missing
             */
            HashMap<Character, Integer> occurrences5 = createMap();
            HashMap<Character, Integer> occurrences6 = createMap();

            for(String s : signals)
                if(s.length() == 5)
                    for(char c : s.toCharArray())
                        occurrences5.put(c, occurrences5.get(c)+1);

            for(String s : signals)
                if(s.length() == 6)
                    for(char c : s.toCharArray())
                        occurrences6.put(c, occurrences6.get(c)+1);

            // Knowing our interesting facts... evaluate the length 5 strings
            for(char c : occurrences5.keySet()){
                // Not interested in anything but unique chars....
                if(occurrences5.get(c) > 1)
                    continue;

                /*
                    If the current char is unique in this length 5,
                    AND is not unique at all in length 6
                    it has to be in position 'b', as the other
                    unique which is 'e' is missing from 9.
                 */
                if(occurrences6.get(c) > 2) {
                    mapping.put(c, 'b');
                    continue;
                }

                // Knowing that, we know 'e'
                mapping.put(c, 'e');
            }

            // Doing the same for length 6
            for(char c : occurrences6.keySet()){
                /*
                    This time we're only interested in three positions;
                    the three missing from each of 0, 6, 9
                 */
                if(occurrences6.get(c) > 2)
                    continue;

                /*
                    We already know for position 'e' from
                    our previous length 5 checks, so ignore
                 */
                if(mapping.containsKey(c))
                    continue;

                /*
                    The two positions left are 'd' and 'e'.
                    The position 'd' is filled in all three
                    of the length 5 numbers, so...
                 */
                if(occurrences5.get(c) > 2) {
                    mapping.put(c, 'd');
                    continue;
                }

                // Lastly, the only position left is 'c'
                mapping.put(c, 'c');
            }

            /*
                Going back to the number 1.
                Now we know which of the two characters
                is in position 'c' so we can figure the other
                is in position 'f'
             */
            for(char c : one.toCharArray()){
                if(mapping.containsKey(c))
                    continue;

                mapping.put(c, 'f');
            }

            /*
             * Finally, there's only one position left to fill so
             * we can just find the remaining character
             */
            for(char c : chars){
                if(mapping.containsKey(c))
                    continue;

                mapping.put(c, 'g');
            }

            // Converting output strings to actual numbers...
            String[] outputs = in.split(" \\| ")[1].split(" ");
            StringBuilder output = new StringBuilder();
            for(String out : outputs){
                StringBuilder converted = new StringBuilder();

                for(char c : out.toCharArray())
                    converted.append(mapping.get(c));

                output.append(getNumber(converted.toString()));
            }

            total += Integer.parseInt(output.toString());
        }

        return total;
    }

    private int getNumber(String s){

        if(s.length() == 2)
            return 1;
        else if(s.length() == 4)
            return 4;
        else if(s.length() == 3)
            return 7;
        else if(s.length() == 7)
            return 8;
        else if(s.length() == 5){
            if(s.contains("e"))
                return 2;
            else if(s.contains("b"))
                return 5;
            else
                return 3;
        }else if(s.length() == 6){
            if(!s.contains("e"))
                return 9;
            if(!s.contains("c"))
                return 6;
            else
                return 0;
        }

        return -1;
    }
}
