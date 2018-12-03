package me.islend.advent;

import java.awt.Point;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class Day3 extends Advent{

	public static void main(String[] args) throws IOException {
		new Day3();
	}
	
	protected Day3() throws IOException {
		super(3);
	}

	@Override
	protected void setup() {
		
	}

	@Override
	protected Object solveFirst() {
		List<Point> takenpoints = new ArrayList<Point>();
		List<Point> duplicates = new ArrayList<Point>();
		int fabric = 0;
		
		for(String str : getInput()) {
			int xpoint = Integer.parseInt(str.split(" ")[2].split(",")[0]) + 1;
			int ypoint = -(Integer.parseInt(str.split(" ")[2].split(",")[1].replace(":", "")) + 1);
			
			int width = Integer.parseInt(str.split(" ")[3].split("x")[0]);
			int height = Integer.parseInt(str.split(" ")[3].split("x")[1]);
			
			//System.out.println("xpoint: " + xpoint + ", ypoint: " + ypoint + ", width: " + width + ", height: " + height);
			
			for(int y = 0; y < height; y++) {
				
				for(int x = 0; x < width; x++) {
					
					Point point = new Point(xpoint + x, ypoint - y);
					if(takenpoints.contains(point) && !duplicates.contains(point)) {
						fabric++;
						duplicates.add(point);
					}else {
						takenpoints.add(point);
					}
					
				}
			}
		}
		
		return fabric;
	}

	@Override
	protected Object solveSecond() {
		HashMap<String, List<Point>> candidate = new HashMap<String, List<Point>>();
		List<Point> allpoints = new ArrayList<Point>();
		
		for(String str : getInput()) {
			String id = str.split(" ")[0];
			
			int xpoint = Integer.parseInt(str.split(" ")[2].split(",")[0]) + 1;
			int ypoint = -(Integer.parseInt(str.split(" ")[2].split(",")[1].replace(":", "")) + 1);
			
			int width = Integer.parseInt(str.split(" ")[3].split("x")[0]);
			int height = Integer.parseInt(str.split(" ")[3].split("x")[1]);
			
			List<Point> candidatepoints = new ArrayList<Point>();
			for(int y = 0; y < height; y++) {
				
				for(int x = 0; x < width; x++) {

					Point point = new Point(xpoint + x, ypoint - y);
					allpoints.add(point);
					candidatepoints.add(point);
					
				}
			}
			
			candidate.put(id, candidatepoints);
		}
		
		System.out.println("Candidate amount: " + candidate.size());
		List<String> idstoremove = new ArrayList<String>();
		for(String id : candidate.keySet()) {
			for(Point p : candidate.get(id)) {
				System.out.println(Collections.frequency(allpoints, p));
					if(!(Collections.frequency(allpoints, p) == 1)) {
						idstoremove.add(id);
						break;
					}
				}
			}
		
		
		System.out.println("Remove: " + candidate.size());
		for(String removeid : idstoremove) {	
			candidate.remove(removeid);
		}
		
		return candidate.toString();
	}

}
