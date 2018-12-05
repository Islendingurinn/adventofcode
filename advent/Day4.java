package me.islend.advent;

import java.io.IOException;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class Day4 extends Advent{

	private TreeSet<Log> logs;
	private Set<Integer> allids;
	
	public static void main(String[] args) throws IOException {
		new Day4();
	}
	
	protected Day4() throws IOException {
		super(4);
	}

	@Override
	protected void setup() {
		allids = new HashSet<>();
		logs = new TreeSet<Log>(new Comparator<Log>() {
			@Override
			public int compare(Log o1, Log o2) {
				return o1.date.compareTo(o2.date);
			}
		});
		
		for(String inputs : getInput()) {	
			String[] data = inputs.split(" ");
			String[] date = data[0].split("-");
			String[] time = data[1].split(":");
			
			Date findate = new Date(parse(date[0]), parse(date[1]), parse(date[2]), parse(time[0]), parse(time[1]));
			
			String event = "";
			for(int x = 2; x < data.length; x++) {
				event += data[x] + " ";
			}
			
			logs.add(new Log(findate, event));
		}
	}

	@Override
	protected Object solveFirst() {
		
		int[] guards = new int[5000];
		int cid = 0;
		Date start = null;
		
		for(Log l : logs) {
			String[] event = l.event.split(" ");
			if(event[0].equals("Guard")) {
				cid = parse(event[1].replace("#", ""));
				allids.add(cid);
		
			}else {
				if(event[0].equals("falls")) {
					start = l.date;
				}else if(event[0].equals("wakes")) {
					int length = l.date.getMinutes() - start.getMinutes();
					
					guards[cid] += length;
				}
			}
		}
		
		int mid = 0, sleep = 0;
		for(int x = 0; x < guards.length; x++) {
			if(guards[x] > sleep) {
				mid = x;
				sleep = guards[x];
			}
		}
		
		Boolean active = false;
		int[] minutes = new int[60];
		for(Log l : logs) {
			String[] event = l.event.split(" ");
			if(event[0].equals("Guard")) {
				if(parse(event[1].replace("#", "")).equals(mid)) {
					active = true;
				}else {
					active = false;
				}
			}else if(active) {
				if(event[0].equals("falls")) {
					start = l.date;
				}
				if(event[0].equals("wakes")) {
					for(int x = start.getMinutes(); x < (l.date.getMinutes()); x++) {
						minutes[x]++;
					}
				}
			}
			
		}
		
		int highestmin = 0, highestfreq = 0;
		for(int x = 0; x < minutes.length; x++) {
			if(minutes[x] > highestfreq) {
				highestfreq = minutes[x];
				highestmin = x;
			}
		}
		return mid * highestmin;
	}

	@Override
	protected Object solveSecond() {
		Map<Integer, Integer> guards = new HashMap<Integer, Integer>();
		for(int id : allids) {
			Boolean active = false;
			Date start = null;
			int[] minutes = new int[60];
			for(Log l : logs) {
				String[] event = l.event.split(" ");
				if(event[0].equals("Guard")) {
					if(parse(event[1].replace("#", "")).equals(id)) {
						active = true;
					}else {
						active = false;
					}
				}else if(active) {
					if(event[0].equals("falls")) {
						start = l.date;
					}
					if(event[0].equals("wakes")) {
						for(int x = start.getMinutes(); x < (l.date.getMinutes()); x++) {
							minutes[x]++;
						}
					}
				}
				
			}
			
			int highestmin = 0, highestfreq = 0;
			for(int x = 0; x < minutes.length; x++) {
				if(minutes[x] > highestfreq) {
					highestfreq = minutes[x];
					highestmin = x;
				}
			}
			
			guards.put(highestmin, id);
		}
		
		int highestmin = 0;
		for(int values : guards.keySet()) {
			if(values > highestmin) {
				highestmin = values;
			}
		}
		return guards.get(highestmin) * highestmin;
	}

	private Integer parse(String string) {
		return Integer.parseInt(string.replace("[", "").replace("]", ""));
	}
	
	private class Log{
		Date date;
		String event;
		
		public Log(Date date, String event) {
			this.date = date;
			this.event = event;
		}
	}
}