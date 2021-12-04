package advent.e2021;

import advent.Advent;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Day4 extends Advent {

    private HashSet<BingoCard> cards;
    private String[] draws;

    public static void main(String[] args) throws IOException {
        new Day4();
    }

    protected Day4() throws IOException {
        super(4);
    }

    @Override
    protected void setup() {
        draws = getInput().get(0).split(",");
        cards = new HashSet<>();

        BingoCard card = new BingoCard();
        int line = 0;
        for (int i = 2; i < getInput().size(); i++) {
            if(getInput().get(i).equalsIgnoreCase("")){
                cards.add(card);
                card = new BingoCard();
                line = 0;
            }else{
                String[] row = getInput().get(i).trim().replaceAll(" +", " ").split(" ");
                System.arraycopy(row, 0, card.card[line], 0, row.length);
                line++;
            }
        }
    }

    @Override
    protected Object solveFirst() {
        for(String number : draws){
            for(BingoCard c : cards){
                if(c.bingo(number)){
                    return c.sum() * Integer.parseInt(number);
                }

            }
        }
        return null;
    }

    @Override
    protected Object solveSecond() {
        List<BingoCard> winners = new ArrayList<>();
        int lastWinner = 0;

        for(String number : draws)
            for(BingoCard c : cards)
                if(c.bingo(number))
                    if(!winners.contains(c)){
                        winners.add(c);
                        lastWinner = c.sum() * Integer.parseInt(number);
                    }

        return lastWinner;
    }

    protected class BingoCard{
        protected String[][] card;
        protected Boolean[][] bingo;

        protected BingoCard(){
            card = new String[5][5];
            bingo = new Boolean[5][5];

            for (int i = 0; i < bingo.length; i++) {
                for (int j = 0; j < bingo.length; j++) {
                    bingo[i][j] = false;
                }
            }
        }

        protected boolean bingo(String number){
            for (int i = 0; i < card.length; i++)
                for (int j = 0; j < card.length; j++)
                    if(card[i][j].equals(number))
                        bingo[i][j] = true;

            return check();
        }

        protected boolean check(){
            for (int i = 0; i < bingo.length; i++) {
                boolean rowBingo = true;
                boolean columnBingo = true;

                for (int j = 0; j < bingo.length; j++) {
                    rowBingo = bingo[i][j] && rowBingo;
                    columnBingo = bingo[j][i] && columnBingo;
                }

                if(rowBingo || columnBingo)
                    return true;
            }

            return false;
        }

        protected int sum(){
            int sum = 0;

            for (int i = 0; i < bingo.length; i++) {
                for (int j = 0; j < bingo.length; j++) {
                    if(!bingo[i][j]){
                        sum += Integer.parseInt(card[i][j]);
                    }
                }
            }

            return sum;
        }
    }
}
