package advent.e2020;

import advent.Advent;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class Day12 extends Advent {

    public static void main(String[] args) throws IOException {
        new Day12();
    }

    protected Day12() throws IOException {
        super(12);
    }

    ArrayList<Character> directions;

    @Override
    protected void setup() {
        directions = new ArrayList<>(Arrays.asList('N', 'E', 'S', 'W'));
    }

    //wrong 1277
    @Override
    protected Object solveFirst() {

        int northsouth = 0;
        int eastwest = 0;
        char facing = 'E';
        for(String in : getInput()){

            char instruction = in.charAt(0);
            int value = Integer.parseInt(in.substring(1));

            switch(instruction){
                case 'N':
                    northsouth += value;
                    break;
                case 'S':
                    northsouth -= value;
                    break;
                case 'E':
                    eastwest += value;
                    break;
                case 'W':
                    eastwest -= value;
                    break;
                case 'F':
                    switch(facing){
                        case 'N':
                            northsouth += value;
                            break;
                        case 'S':
                            northsouth -= value;
                            break;
                        case 'E':
                            eastwest += value;
                            break;
                        case 'W':
                            eastwest -= value;
                            break;
                    }
                    break;
                case 'R':
                case 'L':

                    int iterations = value / 90;
                    int start = directions.indexOf(facing);

                    while (iterations != 0){
                        if(instruction == 'R')
                            if(start != 3)
                                start++;
                            else
                                start = 0;
                        else
                            if(start != 0)
                                start--;
                            else
                                start = 3;
                        iterations--;
                    }

                    facing = directions.get(start);
                    break;
            }
        }

        return Math.abs(northsouth) + Math.abs(eastwest);
    }

    @Override
    protected Object solveSecond() {
        int northsouthWP = 1;
        int eastwestWP = 10;

        int northsouth = 0;
        int eastwest = 0;

        for(String in : getInput()){

            char instruction = in.charAt(0);
            int value = Integer.parseInt(in.substring(1));

            switch(instruction){
                case 'N':
                    northsouthWP += value;
                    break;
                case 'S':
                    northsouthWP -= value;
                    break;
                case 'E':
                    eastwestWP += value;
                    break;
                case 'W':
                    eastwestWP -= value;
                    break;
                case 'F':
                    northsouth += (northsouthWP*value);
                    eastwest += (eastwestWP*value);
                    break;
                case 'R':
                case 'L':
                    int iterations = value / 90;

                    char initNS = northsouthWP > 0 ? 'N' : 'S';
                    char initEW = eastwestWP > 0 ? 'E' : 'W';

                    int ns = directions.indexOf(initNS);
                    int ew = directions.indexOf(initEW);

                    while (iterations != 0){
                        if(instruction == 'R'){

                            if(ns != 3)
                                ns++;
                            else
                                ns = 0;


                            if(ew != 3)
                                ew++;
                            else
                                ew = 0;

                        }else{

                            if(ns != 0)
                                ns--;
                            else
                                ns = 3;


                            if(ew != 0)
                                ew--;
                            else
                                ew = 3;

                        }

                        iterations--;
                    }

                    int newNS = 0;
                    int newEW = 0;
                    if(initNS == 'N'){
                        switch(directions.get(ns)){
                            case 'N':
                                newNS = northsouthWP;
                                break;
                            case 'S':
                                newNS = -northsouthWP;
                                break;
                            case 'E':
                                newEW = northsouthWP;
                                break;
                            case 'W':
                                newEW = -northsouthWP;
                                break;
                        }
                    }else{
                        switch(directions.get(ns)){
                            case 'S':
                                newNS = northsouthWP;
                                break;
                            case 'N':
                                newNS = -northsouthWP;
                                break;
                            case 'W':
                                newEW = northsouthWP;
                                break;
                            case 'E':
                                newEW = -northsouthWP;
                                break;
                        }
                    }

                    if(initEW == 'E'){
                        switch(directions.get(ew)){
                            case 'N':
                                newNS = eastwestWP;
                                break;
                            case 'S':
                                newNS = -eastwestWP;
                                break;
                            case 'E':
                                newEW = eastwestWP;
                                break;
                            case 'W':
                                newEW = -eastwestWP;
                                break;
                        }
                    }else{
                        switch(directions.get(ew)){
                            case 'S':
                                newNS = eastwestWP;
                                break;
                            case 'N':
                                newNS = -eastwestWP;
                                break;
                            case 'W':
                                newEW = eastwestWP;
                                break;
                            case 'E':
                                newEW = -eastwestWP;
                                break;
                        }
                    }

                    northsouthWP = newNS;
                    eastwestWP = newEW;
                    break;
            }
        }

        return Math.abs(northsouth) + Math.abs(eastwest);
    }
}
