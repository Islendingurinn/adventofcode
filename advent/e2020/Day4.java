package advent.e2020;

import advent.Advent;

import java.io.IOException;
import java.util.HashSet;

public class Day4 extends Advent {

    public static void main(String[] args) throws IOException {
        new Day4();
    }

    protected Day4() throws IOException {
        super(4);
    }

    private HashSet<Passport> passports;

    @Override
    protected void setup() {
        passports = new HashSet<>();

        Passport passport = new Passport();
        for(String in : getInput()){
            if(in.equals("")){
                passports.add(passport);
                passport = new Passport();
                continue;
            }

            String[] inputs = in.split(" ");
            for(String keyvalue : inputs){
                passport.set(keyvalue.split(":")[0], keyvalue.split(":")[1]);
            }
        }
    }

    @Override
    protected Integer solveFirst() {

        int valid = 0;
        for(Passport passport : passports){
            if(!(passport.byr == null
            || passport.iyr == null
            || passport.eyr == null
            || passport.hgt == null
            || passport.hcl == null
            || passport.ecl == null
            || passport.pid == null))
                valid++;
        }

        return valid;
    }

    @Override
    protected Integer solveSecond() {

        int valid = 0;
        for(Passport passport : passports){

            if(passport.byr == null || !passport.byr.matches("^([1][9][2-9][0-9]|[2][0][0][0-2])$"))
                continue;

            if(passport.iyr == null || !passport.iyr.matches("^([2][0][1][0-9]|[2][0][2][0])$"))
                continue;

            if(passport.eyr == null || !passport.eyr.matches("^([2][0][2][0-9]|[2][0][3][0])$"))
                continue;

            if(passport.hgt == null){
                continue;
            }else{
                if(passport.hgt.endsWith("cm")){
                    int height = Integer.parseInt(passport.hgt.substring(0, passport.hgt.length()-2));
                    if(height < 150 || height > 193)
                        continue;
                }else if(passport.hgt.endsWith("in")) {
                    int height = Integer.parseInt(passport.hgt.substring(0, passport.hgt.length() - 2));
                    if (height < 59 || height > 76)
                        continue;
                }else
                    continue;
            }

            if(passport.hcl == null || !passport.hcl.matches("^#[0-9a-f]{6}$"))
                continue;

            if(passport.ecl == null || !passport.ecl.matches("\\bamb\\b|\\bblu\\b|\\bbrn\\b|\\bgry\\b|\\bgrn\\b|\\bhzl\\b|\\both\\b"))
                continue;

            if(passport.pid == null || !passport.pid.matches("^[0-9]{9}$"))
                continue;

            valid++;
        }

        return valid;
    }

    public class Passport{
        String byr;
        String iyr;
        String eyr;
        String hgt;
        String hcl;
        String ecl;
        String pid;
        String cid;

        public void set(String key, String value){

            if(key.equals("byr"))
                byr = value;

            if(key.equals("iyr"))
                iyr = value;

            if(key.equals("eyr"))
                eyr = value;

            if(key.equals("hgt"))
                hgt = value;

            if(key.equals("hcl"))
                hcl = value;

            if(key.equals("ecl"))
                ecl = value;

            if(key.equals("pid"))
                pid = value;

            if(key.equals("cid"))
                cid = value;
        }
    }
}
