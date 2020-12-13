package advent.e2020;

import advent.Advent;

import java.io.IOException;
import java.util.*;

import static java.lang.Long.parseLong;

public class Day13 extends Advent {

    public static void main(String[] args) throws IOException {
        new Day13();
    }

    protected Day13() throws IOException {
        super(13);
    }

    long timestamp;
    String[] pattern;
    ArrayList<Integer> busses;
    @Override
    protected void setup() {
        busses = new ArrayList<>();
        pattern = getInput().get(1).split(",");
        timestamp = parseLong(getInput().get(0));

        for(String s : getInput().get(1).split(",")){
            if(!s.equals("x"))
                busses.add(Integer.parseInt(s));
        }
    }



    @Override
    protected Object solveFirst() {
        long earliest = -1;
        long shortest = Integer.MAX_VALUE;

        for(Integer bus : busses){
            long delta = (long) Math.ceil((double) timestamp / bus);
            long minutes = delta * bus;
            long waiting = minutes - timestamp;

            if(waiting < shortest) {
                earliest = bus;
                shortest = waiting;
            }
        }

        return earliest * shortest;
    }

    @Override
    protected Object solveSecond() {

        long[][] schedule = new long[busses.size()][];

        int index = 0;
        for (int i = 0; i < pattern.length; i++) {
            if(pattern[i].equals("x")) continue;

            schedule[index] = new long[]{parseLong(pattern[i]), i};
            index++;
        }

        long products = 1;
        for(long[] arr : schedule)
            products *= arr[0];
        final long product = products;

        index = 0;
        long[] partialProduct = new long[schedule.length];
        for(long[] arr : schedule) {
            partialProduct[index] = product / arr[0];
            index++;
        }

        index = 0;
        long[] inverse = new long[schedule.length];
        for(long[] arr : schedule){
            inverse[index] = computeInverse(partialProduct[index], arr[0]);
            index++;
        }

        long sum = 0;
        index = 0;
        for(long[] arr : schedule){
            sum += partialProduct[index] * inverse[index] * arr[1];
            index++;
        }

        return product - sum % product;
    }

    public long computeInverse(long a, long b){

        long m = b, t, q;
        long x = 0, y = 1;
        if (b == 1)
            return 0;

        // Apply extended Euclid Algorithm
        while (a > 1){

            // q is quotient
            q = a / b;
            t = b;

            // now proceed same as Euclid's algorithm
            b = a % b;
            a = t;
            t = x;
            x = y - q * x;
            y = t;
        }

        // Make x1 positive
        if (y < 0)
            y += m;

        return y;
    }
}


