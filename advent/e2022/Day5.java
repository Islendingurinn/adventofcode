package advent.e2022;

import advent.Advent;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Stack;

public class Day5 extends Advent {

    public static void main(String[] args) throws IOException {
        new Day5();
    }

    protected Day5() throws IOException {
        super(5);
    }

    private static class Instruction {
        int amount, fromIndex, toIndex;

        public Instruction(int amount, int fromIndex, int toIndex){
            this.amount = amount;
            this.fromIndex = fromIndex;
            this.toIndex = toIndex;
        }
    }

    Stack<String>[] origin;
    ArrayList<Instruction> instructions;

    @Override
    protected void setup() {
        int dividesAt = 0;
        for (int i = 0; i < getInput().size(); i++) {
            if (getInput().get(i).equals("")){
                dividesAt = i;
                break;
            }
        }

        origin = new Stack[10];
        for (int i = dividesAt - 2; i >= 0; i--) {
            int index = 0;
            String line = getInput().get(i);
            for (int j = 0; j <= line.length(); j += 4) {
                String crate = line.substring(j + 1, j + 2);
                if (crate.isBlank()) {
                    index++;
                    continue;
                }
                Stack<String> stack = origin[index];
                if (stack != null){
                    stack.push(crate);
                } else {
                    Stack<String> newStack = new Stack<>();
                    newStack.push(crate);
                    origin[index] = newStack;
                }
                index++;
            }
        }

        instructions = new ArrayList<>();
        for (int i = dividesAt + 1; i < getInput().size(); i++) {
            String line = getInput().get(i);
            String[] split = line.split(" ");
            int amount = Integer.parseInt(split[1]);
            int fromIndex = Integer.parseInt(split[3]) - 1;
            int toIndex = Integer.parseInt(split[5]) - 1;
            Instruction instruction = new Instruction(amount, fromIndex, toIndex);
            instructions.add(instruction);
        }
    }

    private String Top(Stack<String>[] stacks){
        StringBuilder top = new StringBuilder();
        for (Stack<String> stack : stacks)
            if (stack != null)
                top.append(stack.peek());
        return top.toString();
    }

    @Override
    protected Object solveFirst() {
        Stack<String>[] stacks = origin.clone();
        for (Instruction instruction : instructions){
            Stack<String> from = stacks[instruction.fromIndex];
            Stack<String> to = stacks[instruction.toIndex];

            for (int i = 0; i < instruction.amount; i++) {
                String container = from.pop();
                to.push(container);
            }
        }
        return Top(stacks);
    }

    @Override
    protected Object solveSecond() {
        Stack<String>[] stacks = origin.clone();
        for (Instruction instruction : instructions){
            Stack<String> from = stacks[instruction.fromIndex];
            Stack<String> to = stacks[instruction.toIndex];

            StringBuilder builder = new StringBuilder();
            for (int i = 0; i < instruction.amount; i++) builder.append(from.pop());
            for (int i = builder.length(); i > 0; i--) to.push(builder.substring(i - 1, i));
        }
        return Top(stacks);
    }
}
