package advent.e2021;

import advent.Advent;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Day3 extends Advent {

    public static void main(String[] args) throws IOException {
        new Day3();
    }

    protected Day3() throws IOException {
        super(3);
    }

    @Override
    protected void setup() { }

    private HashMap<Integer, Integer> countIndices(List<String> input){
        HashMap<Integer, Integer> result = new HashMap<>();

        for(String in : input){
            // Regex magic to split "asdf" to ["a", "s", "d", "f"]
            String[] split = in.split("(?!^)");

            for (int i = 0; i < split.length; i++) {
                switch(split[i]){
                    case "1":
                        if(result.containsKey(i)) result.put(i, result.get(i) + 1);
                        else result.put(i, 1);
                        break;
                    case "0":
                        if(result.containsKey(i)) result.put(i, result.get(i)-1);
                        else result.put(i, -1);
                        break;
                }
            }
        }

        return result;
    }

    @Override
    protected Object solveFirst() {
        HashMap<Integer, Integer> frequencyCount = countIndices(getInput());

        StringBuilder gamma = new StringBuilder();
        StringBuilder epsilon = new StringBuilder();

        for(int index = 0; index < frequencyCount.size(); index++){
            if (frequencyCount.get(index) >= 0) {
                gamma.append("1");
                epsilon.append("0");
            }else{
                gamma.append("0");
                epsilon.append("1");
            }
        }

        return Integer.parseInt(String.valueOf(gamma), 2)
                * Integer.parseInt(String.valueOf(epsilon), 2);
    }

    private String reduce(List<String> fullInput, boolean inverted){
        List<String> input = new ArrayList<>(fullInput);

        int index = 0;
        while(input.size() != 1){
            HashMap<Integer, Integer> count = countIndices(input);
            List<String> newInput = new ArrayList<>();

            for(String in : input){
                String atIndex = in.substring(index, index+1);

                if(inverted){
                    if (count.get(index) >= 0) {
                        if (atIndex.equalsIgnoreCase("0")) {
                            newInput.add(in);
                        }
                    } else {
                        if (atIndex.equalsIgnoreCase("1")) {
                            newInput.add(in);
                        }
                    }
                }else {
                    if (count.get(index) >= 0) {
                        if (atIndex.equalsIgnoreCase("1")) {
                            newInput.add(in);
                        }
                    } else {
                        if (atIndex.equalsIgnoreCase("0")) {
                            newInput.add(in);
                        }
                    }
                }
            }

            input = newInput;
            index++;
        }

        return input.get(0);
    }

    @Override
    protected Object solveSecond() {
        return Integer.parseInt(reduce(getInput(), false), 2) * Integer.parseInt(reduce(getInput(), true), 2);
    }
}
