package advent.e2021;

import advent.Advent;

import java.io.IOException;
import java.util.*;

public class Day10 extends Advent {

    private HashMap<Character, Character> mapping;

    public static void main(String[] args) throws IOException {
        new Day10();
    }

    protected Day10() throws IOException {
        super(10);
    }

    @Override
    protected void setup() {
        mapping = new HashMap<>();
        mapping.put('{', '}');
        mapping.put('[', ']');
        mapping.put('<', '>');
        mapping.put('(', ')');
    }

    private char[] trim(String input){
        String result = input;
        while(true){
            String toReplace = result;
            toReplace = toReplace.replaceAll("\\[]", "");
            toReplace = toReplace.replaceAll("\\(\\)", "");
            toReplace = toReplace.replaceAll("\\{}", "");
            toReplace = toReplace.replaceAll("<>", "");

            if(toReplace.equals(result))
                break;

            result = toReplace;

        }

        return result.toCharArray();
    }

    @Override
    protected Object solveFirst() {
        long sum = 0;

        for(String in : getInput()){
            char[] chars = trim(in);

            for(int i = 0; i < chars.length; i++){
                if(mapping.containsValue(chars[i]) && mapping.containsKey(chars[i-1])){
                    char invalid = chars[i];
                    switch(invalid){
                        case ')':
                            sum += 3;
                            break;
                        case ']':
                            sum += 57;
                            break;
                        case '}':
                            sum += 1197;
                            break;
                        case '>':
                            sum += 25137;
                            break;
                    }

                    break;
                }
            }
        }

        return sum;
    }

    @Override
    protected Object solveSecond() {
        List<Long> scores = new ArrayList<>();

        for(String in : getInput()){
            char[] chars = trim(in);
            boolean valid = true;
            for(int i = 0; i < chars.length; i++){
                if(mapping.containsValue(chars[i]) && mapping.containsKey(chars[i-1])){
                    valid = false;
                    break;
                }
            }

            if(valid){
                long sum = 0;

                for (int i = chars.length-1; i >= 0; i--) {
                    char c = mapping.get(chars[i]);
                    switch(c){
                        case ')':
                            sum *= 5;
                            sum += 1;
                            break;
                        case ']':
                            sum *= 5;
                            sum += 2;
                            break;
                        case '}':
                            sum *= 5;
                            sum += 3;
                            break;
                        case '>':
                            sum *= 5;
                            sum += 4;
                            break;
                    }
                }

                scores.add(sum);
            }
        }

        Collections.sort(scores);
        return scores.get(scores.size() / 2);
    }
}
