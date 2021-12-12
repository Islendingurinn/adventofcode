package advent.e2021;

import advent.Advent;

import java.io.IOException;

public class Day11 extends Advent {

    int[][] squids;
    boolean[][] flashed;

    public static void main(String[] args) throws IOException {
        new Day11();
    }

    protected Day11() throws IOException {
        super(11);
    }

    @Override
    protected void setup() {
        squids = new int[10][10];
        int x = 0;
        for(String in : getInput()){
            int y = 0;
            for(char c : in.toCharArray()){
                squids[x][y] = parse(c);
                y++;
            }
            x++;
        }
    }

    private void check(int x, int y){
        int energy = squids[x][y];
        if(energy > 9 && !flashed[x][y]) {
            flashed[x][y] = true;

            if (x-1 >= 0) {
                squids[x-1][y]++;
                check(x-1, y);

                if (y-1 >= 0) {
                    squids[x-1][y-1]++;
                    check(x-1, y-1);
                }

                if (y + 1 < squids[0].length) {
                    squids[x-1][y+1]++;
                    check(x-1, y+1);
                }
            }

            if (y-1 >= 0) {
                squids[x][y-1]++;
                check(x, y-1);
            }

            if (y+1 < squids[0].length) {
                squids[x][y+1]++;
                check(x, y+1);
            }

            if (x+1 < squids.length) {
                squids[x+1][y]++;
                check(x+1, y);

                if (y - 1 >= 0) {
                    squids[x+1][y-1]++;
                    check(x+1, y-1);
                }

                if (y+1 < squids[0].length) {
                    squids[x+1][y+1]++;
                    check(x+1, y+1);
                }
            }
        }
    }

    private int executeSteps(){
        int done = 0;
        int flashes = 0;

        while (done != 100){

            // Step 1 increment
            for (int x = 0; x < 10; x++) {
                for (int y = 0; y < 10; y++) {
                    flashed = new boolean[10][10];
                    squids[x][y]++;
                }
            }

            // Step 2 execute / check flashes
            for (int x = 0; x < 10; x++) {
                for (int y = 0; y < 10; y++) {
                    check(x, y);
                }
            }

            // Step 3 count flashes and reset
            for (int x = 0; x < 10; x++) {
                for (int y = 0; y < 10; y++) {
                    if(squids[x][y] > 9){
                        squids[x][y] = 0;
                        flashes++;
                    }
                }
            }

            done++;
        }
        return flashes;
    }

    private int executeSteps2(){
        int done = 0;

        outer:
        while (true){

            // Step 1 increment
            for (int x = 0; x < 10; x++) {
                for (int y = 0; y < 10; y++) {

                    // Check if everything flashed at once
                    boolean fin = true;
                    check:
                    for (int i = 0; i < flashed.length; i++) {
                        for (int j = 0; j < flashed[0].length; j++) {
                            if(!flashed[i][j]){
                                fin = false;
                                break check;
                            }
                        }
                    }

                    // If it did, exit loop
                    if(fin){
                        break outer;
                    }

                    // If not, continue as usual
                    flashed = new boolean[10][10];
                    squids[x][y]++;
                }
            }

            // Step 2 execute / check flashes
            for (int x = 0; x < 10; x++) {
                for (int y = 0; y < 10; y++) {
                    check(x, y);
                }
            }

            // Step 3 reset
            for (int x = 0; x < 10; x++) {
                for (int y = 0; y < 10; y++) {
                    if(squids[x][y] > 9){
                        squids[x][y] = 0;
                    }
                }
            }

            done++;
        }

        return done;
    }

    @Override
    protected Object solveFirst() {
        return executeSteps();
    }

    @Override
    protected Object solveSecond() {
        return executeSteps2();
    }

    private int parse(char c){
        return Integer.parseInt(String.valueOf(c));
    }
}
