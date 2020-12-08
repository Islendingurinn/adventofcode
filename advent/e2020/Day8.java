package advent.e2020;

import advent.Advent;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Day8 extends Advent {

    public static void main(String[] args) throws IOException {
        new Day8();
    }

    protected Day8() throws IOException {
        super(8);
    }

    @Override
    protected void setup() {
    }

    @Override
    protected Object solveFirst() {

        return runScenario(-1);

    }

    @Override
    protected Object solveSecond() {

        for(int i = 0; i < getInput().size(); i++){

            if(getInput().get(i).startsWith("nop") || getInput().get(i).startsWith("jmp")){

                int result = runScenario(i);
                if(result > 0)
                    return result;

            }
        }

        return null;
    }

    //Returns a negative accumulator if "unexpected" exit
    //Returns a positive accumulator if "expected" exit
    private int runScenario(int changedIndex){

        List<String> modifiedInput = new ArrayList<>(getInput());

        if(changedIndex != -1) {

            String atIndex = modifiedInput.get(changedIndex);

            atIndex = atIndex.startsWith("nop")?
            atIndex.replace("nop", "jmp")
            : atIndex.replace("jmp", "nop");

            modifiedInput.set(changedIndex, atIndex);

        }

        HashSet<Integer> instructions = new HashSet<>();

        int index = 0;
        int acc = 0;

        while(true){

            if(instructions.contains(index))
                return acc > 0 ? -acc : acc;

            if(modifiedInput.size() <= index)
                return acc;

            String command = modifiedInput.get(index).split(" ")[0];
            int parameter = Integer.parseInt(modifiedInput.get(index).split(" ")[1]);

            instructions.add(index);

            switch(command){
                case "jmp":
                    index += parameter;
                    break;
                case "nop":
                    index++;
                    break;
                case "acc":
                    acc += parameter;
                    index++;
                    break;
            }
        }
    }
}
