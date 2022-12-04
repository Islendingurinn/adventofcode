package advent.e2022;

import advent.Advent;

import java.io.IOException;
import java.util.HashSet;

public class Day4 extends Advent {

    public static void main(String[] args) throws IOException {
        new Day4();
    }

    protected Day4() throws IOException {
        super(4);
    }

    private static class Pair {
        int firstElfMin,firstElfMax, secondElfMin, secondElfMax;

        public Pair (int firstElfMin, int firstElfMax, int secondElfMin, int secondElfMax){
            this.firstElfMin = firstElfMin;
            this.firstElfMax = firstElfMax;
            this.secondElfMin = secondElfMin;
            this.secondElfMax = secondElfMax;
        }
    }

    private HashSet<Pair> pairs;

    @Override
    protected void setup() {
        pairs = new HashSet<>();
        for (String input : getInput()){
            String[] split = input.split(",");
            String[] firstElf = split[0].split("-");
            String[] secondElf = split[1].split("-");
            Pair pair = new Pair(
                    Integer.parseInt(firstElf[0]),
                    Integer.parseInt(firstElf[1]),
                    Integer.parseInt(secondElf[0]),
                    Integer.parseInt(secondElf[1]));
            pairs.add(pair);
        }
    }

    @Override
    protected Object solveFirst() {
        int count = 0;
        for (Pair pair : pairs){
            // CASE 1: First elf is within second elf set
            if (pair.firstElfMin >= pair.secondElfMin && pair.firstElfMax <= pair.secondElfMax)
                count++;
            // CASE 2: Second elf is within first elf set
            else if (pair.secondElfMin >= pair.firstElfMin && pair.secondElfMax <= pair.firstElfMax)
                count++;
        }
        return count;
    }

    @Override
    protected Object solveSecond() {
        int count = 0;
        for (Pair pair : pairs)
            if (pair.firstElfMax >= pair.secondElfMin && pair.firstElfMin <= pair.secondElfMax)
                count++;

        return count;
    }
}
