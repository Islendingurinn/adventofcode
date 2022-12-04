package advent.e2022;

import advent.Advent;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;

public class Day3 extends Advent {

    public static void main(String[] args) throws IOException {
        new Day3();
    }

    protected Day3() throws IOException {
        super(3);
    }

    protected int getPriority(char c){
        if (Character.isUpperCase(c)) return getPriorityUppercase(c);
        else return getPriorityLowercase(c);
    }

    protected int getPriorityUppercase(char c){
        return c - 38;
    }

    protected int getPriorityLowercase(char c){
        return c - 96;
    }

    @Override
    protected void setup() {
    }

    @Override
    protected Object solveFirst() {
        int score = 0;
        for (String input : getInput()){
            int center = input.length() / 2;
            List<Character> left = input.substring(0, center)
                    .chars()
                    .mapToObj(e -> (char) e)
                    .toList();

            char[] right = input.substring(center).toCharArray();
            for (char c : right){
                if (left.contains(c)){
                    score += getPriority(c);
                    break;
                }
            }
        }
        return score;
    }

    @Override
    protected Object solveSecond() {
        int score = 0;
        List<String> input = getInput();
        for (int i = 0; i < input.size(); i += 3) {
            String first = input.get(i);
            String second = input.get(i + 1);

            HashSet<Character> duplicates = new HashSet<>();
            List<Character> list1 = first
                    .chars()
                    .mapToObj(e -> (char) e)
                    .toList();

            char[] list2 = second.toCharArray();
            for (char c : list2){
                if (list1.contains(c)){
                    duplicates.add(c);
                }
            }
            if (duplicates.isEmpty()) continue;

            String third = input.get(i + 2);
            char[] list3 = third.toCharArray();
            for (char c : list3){
                if (duplicates.contains(c)){
                    score += getPriority(c);
                    break;
                }
            }
        }
        return score;
    }
}
