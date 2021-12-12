package advent;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public abstract class Advent {
	
	private List<String> input = new ArrayList<>();
	
	protected Advent(int day) throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader(new File(".", "Day" + day + ".txt")));
		
		String s;
		while((s = reader.readLine()) != null) {
			input.add(s);
		}
		
		setup();
		long currentTime = System.currentTimeMillis();
		System.out.print("Part 1: " + solveFirst());
		System.out.println(" (" + (System.currentTimeMillis() - currentTime) + " ms)");

		setup();
		currentTime = System.currentTimeMillis();
		System.out.print("Part 2: " + solveSecond());
		System.out.println(" (" + (System.currentTimeMillis() - currentTime) + " ms)");
	}
	
	public List<String> getInput() {
		return input;
	}
	
	protected abstract void setup();
	
	protected abstract Object solveFirst();
	
	protected abstract Object solveSecond();
}
