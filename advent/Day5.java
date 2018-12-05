package me.islend.advent;

import java.io.IOException;

public class Day5 extends Advent{
	
	private StringBuilder sequence;
	
	public static void main(String[] args) throws IOException {
		new Day5();
	}
	
	protected Day5() throws IOException {
		super(5);
	}

	@Override
	protected void setup() {
	
		sequence = new StringBuilder(getInput().get(0));
	}

	@Override
	protected Object solveFirst() {
		
		return reaction(sequence);
	}

	@Override
	protected Object solveSecond() {
		String alphabet = "abcdefghijklmnopqrstuvwxyz";
		int lowestamount = Integer.MAX_VALUE;
		

		for(int x = 0; x < alphabet.length(); x++) {
			StringBuilder newseq = new StringBuilder(sequence.toString().replaceAll(String.valueOf(alphabet.charAt(x)), "").replaceAll(String.valueOf(alphabet.charAt(x)).toUpperCase(), ""));
			lowestamount = Math.min(lowestamount, reaction(newseq));
		}

		return lowestamount;
	}	
	
	private int reaction(StringBuilder seq) {
		boolean complete = false;
		while(!complete) {
			for(int x = 0; x < seq.length() - 1; x++) {
				if((seq.charAt(x) ^ seq.charAt(x + 1)) == 32) {
					seq.delete(x, x + 2);
					complete = false;
					break;
				}
				
				complete = true;
			}
		}
		
		return seq.length();
	}
}
