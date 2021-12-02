package advent.e2021;

import advent.Advent;

import java.io.IOException;

public class Day2 extends Advent {

    public static void main(String[] args) throws IOException {
        new Day2();
    }

    protected Day2() throws IOException {
        super(2);
    }

    @Override
    protected void setup() {

    }

    @Override
    protected Object solveFirst() {
        int depth = 0;
        int horizontal = 0;

        for(String in : getInput()){
            String[] split = in.trim().split(" ");
            String direction = split[0];
            int magnitude = Integer.parseInt(split[1]);

            switch(direction) {
                case "forward":
                    horizontal += magnitude;
                    break;
                case "down":
                    depth += magnitude;
                    break;
                case "up":
                    depth -= magnitude;
                    break;
            }
        }

        return depth*horizontal;
    }

    @Override
    protected Object solveSecond() {
        int depth = 0;
        int horizontal = 0;
        int aim = 0;

        for(String in : getInput()){
            String[] split = in.trim().split(" ");
            String direction = split[0];
            int magnitude = Integer.parseInt(split[1]);

            switch(direction) {
                case "forward":
                    horizontal += magnitude;
                    depth += aim * magnitude;
                    break;
                case "down":
                    aim += magnitude;
                    break;
                case "up":
                    aim -= magnitude;
                    break;
            }
        }

        return depth*horizontal;
    }
}
