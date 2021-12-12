package advent.e2021;

import advent.Advent;

import java.io.IOException;
import java.util.*;

public class Day12 extends Advent {

    private HashMap<String, HashSet<String>> caves;

    public static void main(String[] args) throws IOException {
        new Day12();
    }

    protected Day12() throws IOException {
        super(12);
    }

    @Override
    protected void setup() {
        caves = new HashMap<>();

        for(String in : getInput()){
            String origin = in.split("-")[0];
            String end = in.split("-")[1];

            if(caves.containsKey(origin))
                caves.get(origin).add(end);
            else
                caves.put(origin, new HashSet<>(Collections.singleton(end)));

            if(caves.containsKey(end))
                caves.get(end).add(origin);
            else
                caves.put(end, new HashSet<>(Collections.singleton(origin)));
        }
    }

    private static boolean isStringLowerCase(String str){
        //convert String to char array
        char[] charArray = str.toCharArray();

        for(int i=0; i < charArray.length; i++){

            //if any character is not in lower case, return false
            if( !Character.isLowerCase( charArray[i] ))
                return false;
        }

        return true;
    }

    private int traverse(String start, ArrayList<String> history){
        if(start.equals("end"))
            return 1;

        ArrayList<String> copy = new ArrayList<>(history);
        copy.add(start);

        int ways = 0;
        HashSet<String> directions = caves.get(start);
        for(String direction : directions){
            if(copy.contains(direction) && isStringLowerCase(direction))
                continue;

            ways += traverse(direction, copy);
        }

        return ways;
    }

    @Override
    protected Object solveFirst() {
        return traverse("start", new ArrayList<>());
    }

    private boolean duplicate(ArrayList<String> history){
        for(String path : history){
            if(isStringLowerCase(path) && Collections.frequency(history, path) > 1){
                return true;
            }
        }

        return false;
    }

    private int traverse2(String start, ArrayList<String> history){
        if(start.equals("end"))
            return 1;

        ArrayList<String> copy = new ArrayList<>(history);
        copy.add(start);

        int ways = 0;
        HashSet<String> directions = caves.get(start);
        for(String direction : directions){
            if(direction.equals("start"))
                continue;

            if(copy.contains(direction) && isStringLowerCase(direction) && duplicate(copy))
                continue;

            ways += traverse2(direction, copy);
        }

        return ways;
    }

    @Override
    protected Object solveSecond() {
        return traverse2("start", new ArrayList<>());
    }
}
