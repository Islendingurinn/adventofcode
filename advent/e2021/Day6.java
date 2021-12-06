package advent.e2021;

import advent.Advent;

import java.io.IOException;
import java.math.BigInteger;
import java.util.HashMap;

public class Day6 extends Advent {

    private HashMap<Integer, BigInteger> fish;

    public static void main(String[] args) throws IOException {
        new Day6();
    }

    protected Day6() throws IOException {
        super(6);
    }

    private HashMap<Integer, BigInteger> preLoad(){
        HashMap<Integer, BigInteger> preload = new HashMap<>();
        preload.put(0, new BigInteger("0"));
        preload.put(1, new BigInteger("0"));
        preload.put(2, new BigInteger("0"));
        preload.put(3, new BigInteger("0"));
        preload.put(4, new BigInteger("0"));
        preload.put(5, new BigInteger("0"));
        preload.put(6, new BigInteger("0"));
        preload.put(7, new BigInteger("0"));
        preload.put(8, new BigInteger("0"));

        return preload;
    }

    @Override
    protected void setup() {
        fish = preLoad();

        for(String in : getInput().get(0).split(",")){
            int number = Integer.parseInt(in);
            BigInteger prev = fish.get(number);
            fish.put(number, prev.add(new BigInteger("1")));
        }
    }

    private void run(int day){
        int ran = 0;
        while(ran != day) {
            HashMap<Integer, BigInteger> refreshed = preLoad();

            for (int key : fish.keySet()) {
                if (key == 0) {
                    BigInteger amount = fish.get(key);
                    refreshed.put(6, refreshed.get(6).add(amount));
                    refreshed.put(8, refreshed.get(8).add(amount));
                } else {
                    refreshed.put(key - 1, refreshed.get(key - 1).add(fish.get(key)));
                }
            }

            fish = refreshed;
            ran++;
        }
    }

    private BigInteger count(){
        BigInteger count = new BigInteger("0");
        for(BigInteger value : fish.values()){
            count = count.add(value);
        }

        return count;
    }

    @Override
    protected Object solveFirst() {
        run(80);
        return count();
    }

    @Override
    protected Object solveSecond() {
        setup();
        run(256);
        return count();
    }
}
