package me.islend.advent;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Day2 {
	static List<String> input = new ArrayList<String>();
	
	public static void main(String[] args) throws IOException {
		
		getInput();
		System.out.println("First exercise: " + solveFirst());
		System.out.println("Second exercise: " + solveSecond());

	}
	
	private static void getInput() throws NumberFormatException, IOException {
		BufferedReader reader = new BufferedReader(new FileReader(new File(".", "Day2.txt")));
		
		String s;
		while((s = reader.readLine()) != null) {
			input.add(s);
		}
	}
	
	private static Integer solveFirst() {
		
		int contains2 = 0, contains3 = 0;
		for(String str : input) {
			List<String> chars = new ArrayList<String>(Arrays.asList(str.split("(?!^)")));
			
			Boolean found2 = false;
			Boolean found3 = false;
			
			for(String c : chars) {
				
				int freq = Collections.frequency(chars, c);
				if(freq == 2 && !found2) {
					contains2++;
					found2 = true;
				}
				if(freq == 3 && !found3) {
					contains3++;
					found3 = true;
				}
			}
		}
		
		return contains2 * contains3;
	}
	
	private static String solveSecond() {
		
		for(String str : input) {
			ArrayList<String> chars = new ArrayList<String>(Arrays.asList(str.split("(?!^)")));
			
			for(String check : input) {
				ArrayList<String> charscheck = new ArrayList<String>(Arrays.asList(check.split("(?!^)")));
				
				int same = 0;
				String fin = "";
				for(int x = 0; x < chars.size(); x++) {
					if(chars.get(x).equals(charscheck.get(x))) {
						same++;
						fin += chars.get(x);
					}
				}
				
				if(same == (chars.size() - 1)) {
					return fin;
				}
			}
		}
		
		return "";
	}
}
