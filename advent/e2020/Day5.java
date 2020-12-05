package advent.e2020;

import advent.Advent;

import java.io.IOException;
import java.util.HashSet;

public class Day5 extends Advent {

    public static void main(String[] args) throws IOException {
        new Day5();
    }

    protected Day5() throws IOException {
        super(5);
    }

    HashSet<Seat> seats;

    @Override
    protected void setup() {
        seats = new HashSet<>();

        for(String input : getInput()) {
            seats.add(getSeat(input));
        }
    }

    @Override
    protected Object solveFirst() {

        int maxId = 0;

        for(Seat seat : seats)
            if(seat.id > maxId)
                maxId = seat.id;

        return maxId;
    }

    @Override
    protected Object solveSecond() {

        HashSet<Integer> emptySeats = new HashSet<>();
        for (int i = 0; i <= 127; i++) {
            for (int j = 0; j <= 7; j++) {

                Seat seat = new Seat();
                seat.row = i;
                seat.col = j;

                int id = seat.row * 8 + seat.col;
                if(!seats.contains(seat)){
                    emptySeats.add(id);

                    if(!emptySeats.contains(id-1) && emptySeats.size() > 1)
                        return id;
                }
            }
        }

        return null;
    }

    public Seat getSeat(String input){
        int maxRow = 127;
        int minRow = 0;
        int minCol = 0;
        int maxCol = 7;

        for(char c : input.toCharArray()){

            int placeholderRows = -Math.floorDiv(-(maxRow - minRow), 2);
            int placeholderCols = -Math.floorDiv(-(maxCol - minCol), 2);
            if(c == 'F')
                maxRow -= placeholderRows;
            else if(c == 'B')
                minRow += placeholderRows;
            else if(c == 'L')
                maxCol -= placeholderCols;
            else if(c == 'R')
                minCol += placeholderCols;
        }

        Seat seat = new Seat();
        seat.row = maxRow;
        seat.col = maxCol;
        seat.id = maxRow * 8 + maxCol;
        return seat;
    }

    public class Seat{
        int row;
        int col;
        int id;

        @Override
        public int hashCode() {
            return 53 * row + col;
        }

        @Override
        public boolean equals(Object obj) {
            if(obj instanceof Seat){
                Seat seat = (Seat) obj;
                if(seat.col == col && seat.row == row)
                    return true;
            }

            return false;
        }
    }
}
