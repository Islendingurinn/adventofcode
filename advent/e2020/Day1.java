package advent.e2020;

import advent.Advent;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class Day1 extends Advent {

    private List<Integer> parsed;

    public static void main(String[] args) throws IOException {
        new Day1();
    }

    protected Day1() throws IOException {
        super(1);
    }

    @Override
    protected void setup() {
        parsed = getInput().stream().map(Integer::parseInt).collect(Collectors.toList());
    }

    @Override
    protected Integer solveFirst() {

        for(int x : parsed){
            for (int i = 0; i < parsed.size(); i++) {
                if(x + parsed.get(i) == 2020) return x * parsed.get(i);
            }
        }

        return -1;
    }

    @Override
    protected Integer solveSecond() {
        for(int x : parsed){
            for (int i = 0; i < parsed.size(); i++) {
                for (int j = parsed.size()-1; j > 0; j--) {
                    if(x + parsed.get(i) + parsed.get(j) == 2020) return x * parsed.get(i) * parsed.get(j);
                }
            }
        }

        return -1;
    }
}
