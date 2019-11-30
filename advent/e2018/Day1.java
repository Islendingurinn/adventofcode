package advent.e2018;

import advent.Advent;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

public class Day1 extends Advent {

	private List<Integer> parsed;
	
	public static void main(String[] args) throws IOException {
		new Day1();
	}
	
	protected Day1() throws IOException {
		super(1);
	}

	@Override
	protected void setup() {
		parsed = getInput().stream().map(Integer::parseInt).collect(Collectors.toList());
	}
	
	@Override
	protected Object solveFirst() {
		
		int freq = 0;
		for(Integer value : parsed) {
			freq += value;
		}
		
		return freq;
	}
	
	@Override
	protected Integer solveSecond() {
		HashSet<Integer> passedfreq = new HashSet<>();
		
		Boolean fin = false;
		int freq = 0;
		passedfreq.add(0);
		while(!fin) {
			for(Integer value : parsed) {
				freq += value;
				if(passedfreq.contains(freq)) {
					return freq;
				}
				
				passedfreq.add(freq);
			}
		}
		
		return freq;
	}
}

