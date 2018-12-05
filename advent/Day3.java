package me.islend.advent;

import java.awt.Point;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Day3 extends Advent{

	private Set<Fabric> fabriclist;
	private int[][] size;
	
	public static void main(String[] args) throws IOException {
		new Day3();
	}
	
	protected Day3() throws IOException {
		super(3);
	}

	@Override
	protected void setup() {
		
		fabriclist = new HashSet<>();
		for(String str : getInput()) {
			int id = Integer.parseInt(str.split(" ")[0].replace("#", ""));
			
			int xpoint = Integer.parseInt(str.split(" ")[2].split(",")[0]);
			int ypoint = (Integer.parseInt(str.split(" ")[2].split(",")[1].replace(":", "")));
			
			int width = Integer.parseInt(str.split(" ")[3].split("x")[0]);
			int height = Integer.parseInt(str.split(" ")[3].split("x")[1]);
			
			fabriclist.add(new Fabric(id, xpoint, ypoint, width, height));
		}
		
		size = new int[1000][1000];
	}

	@Override
	protected Object solveFirst() {
		int fabriccount = 0;
		
		for(Fabric fabric : fabriclist) {
			for(int y = fabric.ypoint; y < (fabric.ypoint + fabric.ydim); y++) {
				for(int x = fabric.xpoint; x < (fabric.xpoint + fabric.xdim); x++) {
					
					if(size[x][y]++ == 1) {
						fabriccount++;
					}
				}
			}
		}
	
		return fabriccount;
	}

	@Override
	protected Object solveSecond() {
		HashMap<Integer, List<Point>> candidate = new HashMap<>();
		
		loop: for(Fabric fabric : fabriclist) {
			for(int y = fabric.ypoint; y < (fabric.ypoint + fabric.ydim); y++) {
				for(int x = fabric.xpoint; x < (fabric.xpoint + fabric.xdim); x++) {
					if(size[x][y] == 1) {
						continue loop;
					}
				}
			}
			
			return fabric.id;
		}
		
		return candidate.get(0);
	}
	
	private class Fabric {
		int id, xpoint, ypoint, xdim, ydim;
		
		Fabric(int id, int xpoint, int ypoint, int xdim, int ydim){
			this.id = id;
			this.xpoint = xpoint;
			this.ypoint = ypoint;
			this.xdim = xdim;
			this.ydim = ydim;
		}
	}
}

	
