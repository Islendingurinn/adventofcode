package advent.e2020;

import advent.Advent;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class Day9 extends Advent {

    public static void main(String[] args) throws IOException {
        new Day9();
    }

    protected Day9() throws IOException {
        super(9);
    }

    List<Long> parsed;
    long invalidNumber;

    @Override
    protected void setup() {
        parsed = getInput().stream().map(Long::parseLong).collect(Collectors.toList());
    }

    @Override
    protected Long solveFirst() {

        SizedStack<Long> stack = new SizedStack<>(25);
        for (int i = 0; i < 25; i++) {
            stack.push(parsed.get(i));
        }

        for (int i = 25; i < getInput().size(); i++) {

            boolean valid = false;

            for(long x : stack){

                for(long y : stack){

                    if(x + y == parsed.get(i)){

                        valid = true;
                        break;

                    }

                    if(valid)
                        break;
                }
            }

            if(valid)
                stack.push(parsed.get(i));

            else{

                invalidNumber = parsed.get(i);
                return invalidNumber;

            }
        }

        return null;
    }

    @Override
    protected Object solveSecond() {

        List<Long> possibilities = parsed.subList(0, parsed.indexOf(invalidNumber));

        List<Long> result = null;
        for (int i = 0; i < possibilities.size(); i++) {

            List<Long> check = new ArrayList<>(possibilities.subList(i, possibilities.size()));

            Long sum = 0L;
            for (int j = 0; j < check.size(); j++) {

                sum += check.get(j);

                if(sum.equals(invalidNumber))
                    result = new ArrayList<>(check.subList(0, j));

            }
        }

        if(!(result == null)){

            Collections.sort(result);
            return result.get(0) + result.get(result.size()-1);

        }

        return null;
    }

    public class SizedStack<T> extends Stack<T> {
        private int maxSize;

        public SizedStack(int size) {
            super();
            this.maxSize = size;
        }

        @Override
        public T push(T object) {
            //If the stack is too big, remove elements until it's the right size.
            while (this.size() >= maxSize) {
                this.remove(0);
            }
            return super.push(object);
        }
    }
}
