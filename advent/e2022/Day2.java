package advent.e2022;

import advent.Advent;

import java.io.IOException;
import java.util.HashMap;

public class Day2 extends Advent {

    public static void main(String[] args) throws IOException {
        new Day2();
    }

    protected Day2() throws IOException {
        super(2);
    }

    static class Action {
        String winsAgainst;
        String drawsAgainst;
        String losesAgainst;

        public Action(String winsAgainst, String drawsAgainst, String losesAgainst){
            this.winsAgainst = winsAgainst;
            this.drawsAgainst = drawsAgainst;
            this.losesAgainst = losesAgainst;
        }
    }

    private HashMap<String, Integer> worth;
    private HashMap<String, Action> actions;

    @Override
    protected void setup() {
        actions = new HashMap<>() {{
            put("ROCK", new Action("SCISSORS", "ROCK", "PAPER"));
            put("PAPER", new Action("ROCK", "PAPER", "SCISSORS"));
            put("SCISSORS", new Action("PAPER", "SCISSORS", "ROCK"));
        }};
        worth = new HashMap<>() {{
            put("ROCK", 1); // Rock
            put("PAPER", 2); // Paper
            put("SCISSORS", 3); // Scissors
        }};
    }

    private String ToReadable(String input){
        if (input.equals("A")) return "ROCK";
        if (input.equals("B")) return "PAPER";
        if (input.equals("C")) return "SCISSORS";
        if (input.equals("X")) return "ROCK";
        if (input.equals("Y")) return "PAPER";
        if (input.equals("Z")) return "SCISSORS";
        return "";
    }

    @Override
    protected Object solveFirst() {
        int score = 0;
        for (String input : getInput()){
            String[] split = input.split(" ");
            String opponent = ToReadable(split[0]);
            String played = ToReadable(split[1]);
            Action player = actions.get(played);

            score += worth.get(played);
            if (player.winsAgainst.equals(opponent)) score += 6;
            if (player.drawsAgainst.equals(opponent)) score += 3;
        }

        return score;
    }

    @Override
    protected Object solveSecond() {
        int score = 0;
        for (String input : getInput()){
            String[] split = input.split(" ");
            Action opponent = actions.get(ToReadable(split[0]));
            String result = split[1];

            if (result.equals("X")) // LOSE
                score += worth.get(opponent.winsAgainst);
            if (result.equals("Y")) // DRAW
                score += worth.get(opponent.drawsAgainst) + 3;
            if (result.equals("Z")) // WIN
                score += worth.get(opponent.losesAgainst) + 6;
        }

        return score;
    }
}
