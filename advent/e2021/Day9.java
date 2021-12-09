package advent.e2021;

import advent.Advent;

import java.io.IOException;
import java.util.*;

public class Day9 extends Advent {

    private int[][] heightmap;

    public static void main(String[] args) throws IOException {
        new Day9();
    }

    protected Day9() throws IOException {
        super(9);
    }

    @Override
    protected void setup() {
        heightmap = new int[getInput().size()][getInput().get(0).length()];

        int x = 0;
        int y = 0;

        for(String in : getInput()){
            for(char c : in.toCharArray()){
                heightmap[x][y] = parse(c);
                y++;
            }

            y = 0;
            x++;
        }
    }

    private List<Integer> getNeighbours(int[][] input, int x, int y){
        List<Integer> result = new ArrayList<>();

        if(y+1 < input[0].length) result.add(heightmap[x][y+1]);
        if(x-1 > -1) result.add(heightmap[x-1][y]);
        if(x+1 < input.length) result.add(heightmap[x+1][y]);
        if(y-1 > -1) result.add(heightmap[x][y-1]);

        return result;
    }

    @Override
    protected Object solveFirst() {
        int sum = 0;

        for (int x = 0; x < heightmap.length; x++) {
            for (int y = 0; y < heightmap[0].length; y++) {

                int current = heightmap[x][y];
                List<Integer> neighbours = getNeighbours(heightmap, x, y);

                int higher = 0;
                for(int n : neighbours)
                    if(n > current)
                        higher++;

                if(higher == neighbours.size())
                    sum += current+1;
            }
        }
        return sum;
    }

    private int getBasin(int x, int y) {
        if(x < 0 || y < 0) return 0;
        if(x >= heightmap.length || y >= heightmap[0].length) return 0;
        if(heightmap[x][y] == 9) return 0;
        if(heightmap[x][y] == -1) return 0;

        heightmap[x][y] = -1;
        return 1 + getBasin(x - 1, y) + getBasin(x + 1, y) + getBasin(x, y - 1) + getBasin(x, y + 1);
    }

    @Override
    protected Object solveSecond() {

        List<Integer> basins = new ArrayList<>();
        for (int x = 0; x < heightmap.length; x++) {
            for (int y = 0; y < heightmap[0].length; y++) {
                int current = heightmap[x][y];
                List<Integer> neighbours = getNeighbours(heightmap, x, y);

                int higher = 0;
                for(int n : neighbours)
                    if(n > current)
                        higher++;

                if(higher == neighbours.size())
                    basins.add(getBasin(x, y));
            }
        }

        basins.sort(Collections.reverseOrder());
        return basins.get(0) * basins.get(1) * basins.get(2);
    }

    private int parse(char c){
        return Integer.parseInt(String.valueOf(c));
    }
}
