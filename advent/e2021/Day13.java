package advent.e2021;

import advent.Advent;

import java.io.IOException;
import java.util.ArrayList;

public class Day13 extends Advent {

    boolean[][] points;
    ArrayList<String> instructions;

    public static void main(String[] args) throws IOException {
        new Day13();
    }

    protected Day13() throws IOException {
        super(13);
    }

    @Override
    protected void setup() {
        int maxDown = 0;
        int maxRight = 0;
        for(String in : getInput()){
            if(in.equals(""))
                break;

            int d = 1 + Integer.parseInt(in.split(",")[1]);
            int r = 1 + Integer.parseInt(in.split(",")[0]);
            maxDown = Math.max(d, maxDown);
            maxRight = Math.max(r, maxRight);
        }

        points = new boolean[maxDown][maxRight];
        instructions = new ArrayList<>();
        for(String in : getInput()){
            if(in.equals(""))
                continue;
            if(in.startsWith("fold along")){
                instructions.add(in.split(" ")[2]);
            }else{
                int r = Integer.parseInt(in.split(",")[0]);
                int d = Integer.parseInt(in.split(",")[1]);
                points[d][r] = true;
            }
        }
    }

    private boolean[][] fold(boolean[][] input, String instruction){
        String dir = instruction.split("=")[0];
        int value = Integer.parseInt(instruction.split("=")[1]);

        boolean[][] result;
        switch(dir){
            case "y":
                result = new boolean[value][input[0].length];
                for (int d = 0; d < value; d++)
                    for (int r = 0; r < input[0].length; r++)
                        if(input[d][r])
                            result[d][r] = true;

                for (int d = value+1; d < input.length; d++)
                    for (int r = 0; r < input[0].length; r++)
                        if(input[d][r])
                            result[value-(d-value)][r] = true;

                return result;
            case "x":
                result = new boolean[input.length][value];
                for (int d = 0; d < input.length; d++)
                    for (int r = 0; r < value; r++)
                        if(input[d][r])
                            result[d][r] = true;

                for (int d = 0; d < input.length; d++) {
                    for (int r = value+1; r < input[0].length; r++) {
                        if(input[d][r])
                            result[d][value-(r-value)] = true;
                    }
                }
                return result;
        }

        return null;
    }

    @Override
    protected Object solveFirst() {
        String first = instructions.get(0);
        boolean[][] result = fold(points, first);

        int count = 0;
        for (int d = 0; d < result.length; d++) {
            for (int r = 0; r < result[0].length; r++) {
                if(result[d][r])
                    count++;
            }
        }

        return count;
    }

    @Override
    protected Object solveSecond() {

        boolean[][] result = points;
        for(String instruction : instructions)
            result = fold(result, instruction);

        for (int d = 0; d < result.length; d++) {
            for (int r = 0; r < result[0].length; r++) {
                if(result[d][r]) System.out.print("#");
                else System.out.print(".");
            }

            System.out.println();
        }
        return "result presented above";
    }
}
