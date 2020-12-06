package advent.e2020;

import advent.Advent;

import java.io.IOException;
import java.util.HashSet;

public class Day6 extends Advent {

    public static void main(String[] args) throws IOException {
        new Day6();
    }

    protected Day6() throws IOException {
        super(6);
    }

    @Override
    protected void setup() {

    }

    @Override
    protected Integer solveFirst() {

        HashSet<Character> duplicates = new HashSet<>();
        int questions = 0;

        for(String in : getInput()){

            if(in.equals("")){
                questions += duplicates.size();
                duplicates.clear();
            }

            for(char c : in.toCharArray()){
                if(!duplicates.contains(c))
                    duplicates.add(c);
            }
        }

        return questions;
    }

    @Override
    protected Integer solveSecond() {

        int noAnswers = 0;
        int yesQuestions = 0;
        int[] frequency = new int[26];

        for(String in : getInput()){

            if(in.equals("")){

                for(int i = 0; i < 26; i++)
                    if(frequency[i] == noAnswers)
                        yesQuestions++;

                noAnswers = 0;
                frequency = new int[26];
            }else{

                for (char c : in.toCharArray())
                    frequency[c - 'a']++;

                noAnswers++;
            }
        }

        return yesQuestions;
    }
}
