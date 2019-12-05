package advent.e2019;

import advent.Advent;

import java.io.IOException;

public class Day5 extends Advent {

    private int[] parsed;

    public static void main(String[]args) throws IOException {
        new Day5();
    }

    protected Day5() throws IOException {
        super(5);
    }

    @Override
    protected void setup() {
        String[] split = getInput().get(0).split(",");

        parsed = new int[split.length];
        for (int i = 0; i < split.length; i++) {
            parsed[i] = Integer.parseInt(split[i]);
        }
    }

    private int run(int force){
        int pos = 0;
        boolean keepgoing = true;

        while(keepgoing){

            int code = parsed[pos];
            int opcode = code % 100;
            code /= 100;

            int[] modes = new int[]{0, 0};
            int count = 0;
            while(code > 0){
                modes[count] = code % 10;
                code /= 10;
                count++;
            }

            if(opcode == 99){
                break;
            }

            if(opcode == 3) {
                parsed[parsed[pos + 1]] = force;
                pos += 2;
            }

            if(opcode == 4) {
                int output = parsed[parsed[pos + 1]];
                pos += 2;

                if(output > 0 && output != 3) return output;
            }

            int parameter1 = modes[0] == 0 ? parsed[parsed[pos+1]] : parsed[pos+1];
            int parameter2 = modes[1] == 0 ? parsed[parsed[pos+2]] : parsed[pos+2];

            switch(opcode){
                case 1:
                    parsed[parsed[pos+3]] = parameter1 + parameter2;
                    pos += 4;
                    break;
                case 2:
                    parsed[parsed[pos+3]] = parameter1 * parameter2;
                    pos += 4;
                    break;
                case 5:
                    if(parameter1 > 0) pos = parameter2;
                    else pos += 3;
                    break;
                case 6:
                    if(parameter1 == 0) pos = parameter2;
                    else pos += 3;
                    break;
                case 7:
                    if(parameter1 < parameter2) parsed[parsed[pos + 3]] = 1;
                    else parsed[parsed[pos + 3]] = 0;
                    pos+=4;
                    break;
                case 8:
                    if(parameter1 == parameter2) parsed[parsed[pos + 3]] = 1;
                    else parsed[parsed[pos + 3]] = 0;
                    pos+=4;
                    break;
            }
        }

        return 0;
    }

    @Override
    protected Object solveFirst() {
        return run(1);
    }

    @Override
    protected Object solveSecond() {
        setup();
        return run(5);
    }
}
