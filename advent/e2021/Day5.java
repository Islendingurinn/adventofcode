package advent.e2021;

import advent.Advent;

import java.io.IOException;

public class Day5 extends Advent {

    private int[][] plot;

    public static void main(String[] args) throws IOException {
        new Day5();
    }

    protected Day5() throws IOException {
        super(5);
    }

    @Override
    protected void setup() {
        plot = new int[1000][1000];

        for (int i = 0; i < plot.length; i++)
            for (int j = 0; j < plot.length; j++)
                plot[i][j] = 0;
    }

    @Override
    protected Object solveFirst() {
        for(String in : getInput()){
            String[] coords = in.split(" -> ");
            String[] c1 = coords[0].split(",");
            String[] c2 = coords[1].split(",");

            int x1 = Integer.parseInt(c1[0]);
            int x2 = Integer.parseInt(c2[0]);
            int y1 = Integer.parseInt(c1[1]);
            int y2 = Integer.parseInt(c2[1]);

            if(!(x1 == x2) && !(y1 == y2))
                continue;

            if(y1 == y2) {
                for (int i = x1; i <= x2; i++) {
                    plot[y1][i] += 1;
                }

                for (int i = x1; i >= x2; i--) {
                    plot[y1][i] += 1;
                }
            }else{
                for (int i = y1; i <= y2; i++) {
                    plot[i][x1] += 1;
                }

                for (int i = y1; i >= y2; i--) {
                    plot[i][x1] += 1;
                }
            }
        }

        return count(plot);
    }

    @Override
    protected Object solveSecond() {
        setup();

        for(String in : getInput()){
            String[] coords = in.split(" -> ");
            String[] c1 = coords[0].split(",");
            String[] c2 = coords[1].split(",");

            int x1 = Integer.parseInt(c1[0]);
            int x2 = Integer.parseInt(c2[0]);
            int y1 = Integer.parseInt(c1[1]);
            int y2 = Integer.parseInt(c2[1]);

            if(x1 == x2){
                for(int j = y1; j<=y2; j++){
                    plot[x1][j] += 1;
                }

                for(int j = y1; j>=y2; j--){
                    plot[x1][j] += 1;
                }
            }
            else if(y1 == y2){
                for(int j = x1; j<=x2; j++){
                    plot[j][y1] += 1;
                }

                for(int j = x1; j>=x2; j--){
                    plot[j][y1] += 1;
                }
            }
            else{
                if(x1 > x2 && y1 > y2){
                    for(int j = x2; j < x1; j++){
                        for(int k = y2; k <= y1; k++){
                            plot[j][k] += 1;
                            j++;
                        }
                    }
                }
                else if(x1 > x2){
                    for(int j = x2; j < x1; j++){
                        for(int k = y2; k >= y1; k--){
                            plot[j][k] += 1;
                            j++;
                        }
                    }
                }
                else if(y1 > y2){
                    for(int j = x2; j >= x1; j--){
                        for(int k = y2; k <= y1; k++){
                            plot[j][k] += 1;
                            j--;
                        }
                    }
                }
                else {
                    for(int j = x2; j >= x1; j--){
                        for(int k = y2; k >= y1; k--){
                            plot[j][k] += 1;
                            j--;
                        }
                    }
                }
            }
        }

        return count(plot);
    }

    private int count(int[][] plot){

        int amount = 0;
        for (int[] ints : plot) {
            for (int j = 0; j < plot.length; j++) {
                int freq = ints[j];
                if (freq > 1)
                    amount++;
            }
        }
        return amount;

    }
}
