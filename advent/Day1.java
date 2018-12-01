package me.islend.advent;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Day1 {
	static List<Integer> input = new ArrayList<Integer>();
	
	public static void main(String[] args) throws IOException {
		
		getInput();
		System.out.println("First exercise: " + solveFirst());
		System.out.println("Second exercise: " + solveSecond());

	}
	
	private static void getInput() throws NumberFormatException, IOException {
		BufferedReader reader = new BufferedReader(new FileReader(new File(".", "Day1.txt")));
		
		String s;
		while((s = reader.readLine()) != null) {
			input.add(Integer.parseInt(s));
		}
	}
	
	private static Integer solveFirst() {
		
		int freq = 0;
		for(Integer value : input) {
			freq += value;
		}
		
		return freq;
	}
	
	private static Integer solveSecond() {
		List<Integer> passedfreq = new ArrayList<Integer>();
		
		Boolean fin = false;
		int freq = 0;
		passedfreq.add(0);
		while(!fin) {
			for(Integer value : input) {
				freq += value;
				if(passedfreq.contains(freq)) {
					return freq;
				}
				
				passedfreq.add(freq);
			}
		}
		
		return null;
	}
}

