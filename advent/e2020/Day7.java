package advent.e2020;

import advent.Advent;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;

public class Day7 extends Advent {

    public static void main(String[] args) throws IOException {
        new Day7();
    }

    protected Day7() throws IOException {
        super(7);
    }

    HashMap<String, Bag> rules;

    @Override
    protected void setup() {

        rules = new HashMap<>();

        for(String in : getInput()){

            String name = in.split("contain")[0].trim();

            if(!rules.containsKey(name)) {
                Bag rule = new Bag();
                rule.name = name;
                rules.put(rule.name, rule);
            }
            Bag rule = rules.get(name);

            String bags = in.split("contain")[1].replace(".", "").trim();
            for(String bag : bags.split(",")){

                if(bag.startsWith("no other bags"))
                    continue;

                int amount = Integer.parseInt(bag.trim().split(" ")[0]);
                String containsName = bag.trim().substring(1).trim();

                if(amount == 1)
                    containsName = containsName.concat("s");

                if(!rules.containsKey(containsName)) {
                    Bag contains = new Bag();
                    contains.name = containsName;
                    rules.put(containsName, contains);
                }

                Bag contains = rules.get(containsName);
                contains.containedBy.add(rule);
                rule.contains.put(contains, amount);

            }
        }
    }

    private HashSet<String> recursion(HashSet<Bag> bags, HashSet<String> input){

        for(Bag b : bags){
            if(!input.contains(b.name)){
                input.add(b.name);
                recursion(b.containedBy, input);
            }
        }

        return input;
    }

    private int recursion(HashMap<Bag, Integer> path, int input){

        for(Bag b : path.keySet()){
            input += path.get(b) + (path.get(b) * recursion(b.contains, 0));
        }

        return input;
    }

    @Override
    protected Integer solveFirst() {

        Bag bag = rules.get("shiny gold bags");
        return recursion(bag.containedBy, new HashSet<>()).size();

    }

    @Override
    protected Object solveSecond() {

        Bag bag = rules.get("shiny gold bags");
        return recursion(bag.contains, 0);

    }

    public class Bag{
        String name;
        HashMap<Bag, Integer> contains = new HashMap<>();
        HashSet<Bag> containedBy = new HashSet<>();
    }
}
