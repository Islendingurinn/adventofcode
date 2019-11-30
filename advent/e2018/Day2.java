package advent.e2018;

import advent.Advent;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

public class Day2 extends Advent {
	
	public static void main(String[] args) throws IOException {
		new Day2();
	}
	
	protected Day2() throws IOException {
		super(2);
	}
	
	@Override
	protected void setup() {
		
	}
	
	@Override
	protected Object solveFirst() {
		
		int contains2 = 0, contains3 = 0;
		for(String str : getInput()) {
			HashSet<String> chars = new HashSet<>(Arrays.asList(str.split("(?!^)")));
			
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
	
	@Override
	protected Object solveSecond() {
	
		for(String str : getInput()) {
			List<String> chars = new ArrayList<>(Arrays.asList(str.split("(?!^)")));
			
			for(String check : getInput()) {
				List<String> charscheck = new ArrayList<>(Arrays.asList(check.split("(?!^)")));
				
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
