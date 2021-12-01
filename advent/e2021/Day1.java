package advent.e2021;

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
    protected Object solveFirst() {
        int increments = 0;

        for (int i = 1; i < parsed.size(); i++)
            if (parsed.get(i) > parsed.get(i-1))
                increments++;

        return increments;
    }

    @Override
    protected Object solveSecond() {
        int increments = 0;
        int previousGroup = -1;

        for (int i = 0; i < (parsed.size() - 2); i++) {
            int currentGroup = parsed.get(i) + parsed.get(i+1) + parsed.get(i+2);

            if (previousGroup != -1)
                if (currentGroup > previousGroup)
                    increments++;

            previousGroup = currentGroup;
        }

        return increments;
    }
}
