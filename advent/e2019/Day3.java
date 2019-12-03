package advent.e2019;

import advent.Advent;

import java.awt.*;
import java.io.IOException;
import java.util.*;

public class Day3 extends Advent {

    public static void main(String[] args) throws IOException {
        new Day3();
    }

    protected Day3() throws IOException {
        super(3);
    }

    Map<Point, Integer> firstWirePoints = new HashMap<>();
    Map<Point, Integer> intersects = new HashMap<>();

    int x = 0;
    int y = 0;
    int steps = 0;

    @Override
    protected void setup() {

        for(String w1 : getInput().get(0).split(",")){
            for (int i = 0; i < Integer.parseInt(w1.substring(1, w1.length())); i++) {
                handleDir(w1.substring(0, 1));
                steps++;
                firstWirePoints.put(new Point(x, y), steps);
            }
        }

        x = 0;
        y = 0;
        steps = 0;
        for(String w2 : getInput().get(1).split(",")){
            for (int i = 0; i < Integer.parseInt(w2.substring(1, w2.length())); i++) {
                handleDir(w2.substring(0, 1));
                steps++;

                Point p = new Point(x, y);
                if(firstWirePoints.containsKey(p))
                    intersects.put(p, steps + firstWirePoints.get(p));
            }
        }
    }

    @Override
    protected Integer solveFirst() {
        double delta = Integer.MAX_VALUE;

        for(Point p : intersects.keySet()){

            double distance = Math.abs(0-p.getX()) + Math.abs(0-p.getY());
            if(distance < delta) delta = distance;

        }

        return (int) delta;
    }

    @Override
    protected Object solveSecond() {

        int max = Integer.MAX_VALUE;
        for(Integer steps : intersects.values()){
            if(steps < max) max = steps;
        }

        return max;
    }

    private void handleDir(String dir){
        switch(dir){
            case "U":
                y++;
                break;
            case "D":
                y--;
                break;
            case "L":
                x--;
                break;
            case "R":
                x++;
                break;
        }
    }
}


