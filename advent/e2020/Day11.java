package advent.e2020;

import advent.Advent;
import java.io.IOException;
import java.util.Arrays;

public class Day11 extends Advent {

    public static void main(String[] args) throws IOException {
        new Day11();
    }

    protected Day11() throws IOException {
        super(11);
    }

    String[][] seats;
    @Override
    protected void setup() {

        seats = new String[getInput().size()][getInput().get(0).length()];

        int x = 0;
        int y = 0;

        for (String in : getInput()){

            for(char c : in.toCharArray()) {
                if (c == 'L') seats[x][y] = "L";
                if (c == '.') seats[x][y] = ".";

                y++;
            }

            y = 0;
            x++;
        }
    }


    @Override
    protected Object solveFirst() {
        return occupied(getFinalArrangement(false));
    }

    @Override
    protected Object solveSecond() {
        return occupied(getFinalArrangement(true));
    }

    private String[][] getFinalArrangement(boolean los){

        String[][] copy = deepCopy(seats);
        boolean changed = true;

        while(changed) {

            String[][] placeholder = deepCopy(copy);
            changed = false;

            for (int x = 0; x < copy.length; x++) {
                for (int y = 0; y < copy[0].length; y++) {

                    String seat = copy[x][y];
                    if(seat.equals(".")) continue;

                    int emptyNeighbours = emptyNeighbours(copy, x, y, los);

                    if(seat.equals("L") && emptyNeighbours == 8){
                        placeholder[x][y] = "#";
                        changed = true;
                    }
                    if(seat.equals("#") && emptyNeighbours <= (los ? 3 : 4)){
                        placeholder[x][y] = "L";
                        changed = true;
                    }
                }
            }

            copy = placeholder;
        }

        return copy;
    }

    private Integer emptyNeighbours(String[][] input, int x, int y, boolean los){
        int MIN_X = -1;
        int MIN_Y = -1;
        int MAX_X = input.length;
        int MAX_Y = input[0].length;

        int startPosX = (x - 1 < MIN_X) ? x : x-1;
        int startPosY = (y - 1 < MIN_Y) ? y : y-1;
        int endPosX =   (x + 1 > MAX_X) ? x : x+1;
        int endPosY =   (y + 1 > MAX_Y) ? y : y+1;

        int empty = 0;
        for (int rowNum=startPosX; rowNum<=endPosX; rowNum++) {
            for (int colNum=startPosY; colNum<=endPosY; colNum++) {

                if(rowNum == x && colNum == y) continue;

                if(los){

                    if(emptyLOS(input, x, y, rowNum-x, colNum-y))
                        empty++;

                }else {

                    try {
                        if (!input[rowNum][colNum].equals("#")) empty++;
                    } catch (ArrayIndexOutOfBoundsException e) {
                        empty++;
                    }

                }
            }
        }

        return empty;
    }


    private boolean emptyLOS(String[][] input, int initX, int initY, int changeX, int changeY){

        int counter = 1;
        while(true){

            int xToCheck = initX + (counter*changeX);
            int yToCheck = initY + (counter*changeY);

            try{
                String seat = input[xToCheck][yToCheck];
                switch (seat) {
                    case ".":
                        counter++;
                        continue;
                    case "#":
                        return false;
                    case "L":
                        return true;
                }
            }catch(ArrayIndexOutOfBoundsException e){
                return true;
            }
        }
    }

    private String[][] deepCopy(String[][] toCopy){

        String[][] placeholder = new String[toCopy.length][toCopy[0].length];
        for (int i = 0; i < placeholder.length; i++)
            placeholder[i] = Arrays.copyOf(toCopy[i], toCopy[i].length);

        return placeholder;
    }

    private Integer occupied(String[][] copy) {

        int occupied = 0;

        for (int i = 0; i < copy.length; i++)
            for (int j = 0; j < copy[0].length; j++)
                if(copy[i][j].equals("#")) occupied++;

        return occupied;
    }
}
