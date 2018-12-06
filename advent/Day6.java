package me.islend.advent;

import java.awt.Point;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Day6 extends Advent{

	private Map<Integer, Point> points;
	private int[][] plot;
	private int maxx = 0, maxy = 0;
	
	public static void main(String[] args) throws IOException {
		new Day6();
	}
	
	protected Day6() throws IOException {
		super(6);
	}

	@Override
	protected void setup() {
		points = new HashMap<Integer, Point>();
		
		int iteration = 0;
		for(String inp : getInput()) {
			String[] pointset = inp.split(", ");
			points.put(iteration, new Point(parse(pointset[0]), parse(pointset[1])));
			if(parse(pointset[0]) > maxx){
				maxx = parse(pointset[0]);
			}
			if(parse(pointset[1]) > maxy){
				maxy = parse(pointset[1]);
			}
			
			iteration++;
		}
		
		plot = new int[maxx+1][maxy+1];
	}

	@Override
	protected Object solveFirst() {
		Map<Integer, Integer> taken = new HashMap<>();
		
		for(int x = 0; x <= maxx; x++) {
			for(int y = 0; y <= maxy; y++) {
				
				int bestdist = Integer.MAX_VALUE;
				int bestid = 0;
				
				for(int i = 0; i < points.size(); i++) {
					Point p = points.get(i);
					
					int dist = Math.abs(x - p.x) + Math.abs(y - p.y);
					if(dist < bestdist) {
						bestdist = dist;
						bestid = i;
					}else if(dist == bestdist) {
						bestid = 0;
					}
				}
				
				plot[x][y] = bestid;
				Integer total = taken.get(bestid);
				if(total == null) total = 1;
				else total = total + 1;
				
				taken.put(bestid, total);		
			}
		}
		
		for (int x = 0; x <= maxx; x++) {
            taken.remove(plot[x][0]);
            taken.remove(plot[x][maxy]);
        }
        for (int y = 0; y <= maxy; y++) {
            taken.remove(plot[0][y]);
            taken.remove(plot[maxx][y]);
        }
		
		int highest = 0;
		for(int am : taken.values()) {
			if(am > highest) {
				highest = am;
			}
		}
		
		return highest;
	}

	@Override
	protected Object solveSecond() {
		
		int totalpoints = 0;
		for(int x = 0; x <= maxx; x++) {
			for(int y = 0; y <= maxy; y++) {
				
				int totaldistance = 0;
                for (int i = 0; i < points.size(); i++) {
                    Point p = points.get(i);
                    int dist = Math.abs(x - p.x) + Math.abs(y - p.y);
                    totaldistance += dist;
                }

                if (totaldistance < 10000) {
                	totalpoints++;
                }
                
			}
		}
		
		return totalpoints;
	}
	
	private Integer parse(String string) {
		return Integer.parseInt(string);
	}
}
