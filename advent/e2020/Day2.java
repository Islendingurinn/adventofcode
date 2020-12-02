package advent.e2020;

import advent.Advent;

import java.io.IOException;
import java.util.HashSet;

public class Day2 extends Advent {

    HashSet<PuzzleObject> parsed;

    public static void main(String[] args) throws IOException {
        new Day2();
    }

    protected Day2() throws IOException {
        super(2);
    }

    @Override
    protected void setup() {
        parsed = new HashSet<>();

        for(String in : getInput()){
            PuzzleObject object = new PuzzleObject();

            String[] parts = in.split(" ");
            object.lower = Integer.parseInt(parts[0].split("-")[0]);
            object.upper = Integer.parseInt(parts[0].split("-")[1]);
            object.character = parts[1].charAt(0);
            object.password = parts[2];

            parsed.add(object);
        }
    }

    @Override
    protected Integer solveFirst() {

        int valid = 0;
        for(PuzzleObject in : parsed){

            int occurrences = 0;
            char[] chars = in.password.toCharArray();

            for(char c : chars){
                if(c == in.character) occurrences++;
            }

            if(in.lower <= occurrences && occurrences <= in.upper ) valid++;
        }

        return valid;
    }

    @Override
    protected Object solveSecond() {

        int valid = 0;
        for(PuzzleObject in : parsed){

            if((in.password.charAt(in.lower - 1) == in.character
            || in.password.charAt(in.upper - 1) == in.character))
                if(!(in.password.charAt(in.lower - 1) == in.password.charAt(in.upper - 1)))
                    valid++;

        }

        return valid;
    }

    private class PuzzleObject{
        public int lower;
        public int upper;
        public char character;
        public String password;
    }
}
