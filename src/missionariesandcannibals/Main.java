package missionariesandcannibals;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {

	public static void main(String[] args) {
		System.out.println("==== Missionaries and Cannibals Problem ====");
		System.out.println("Choose the search method: ");
		System.out.println("\t 1. Breadth-first search");
		System.out.println("\t 2. Depth-limited search");
		System.out.print("\nType your choice and press ENTER: ");

		String optionStr = null;
		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
			optionStr = in.readLine();
		} catch (IOException e) {
			System.out.println("[ERROR] Fail to read the typed option.");
			e.printStackTrace();
		}

		int option = Integer.parseInt(optionStr);
		State initialState = new State (3, 3, Position.LEFT, 0, 0);
		switch(option) {
		case 1:
			executeBFS(initialState);
			break;
		case 2:
			executeDLS(initialState);
			break;
		default:
			System.out.println("[ERROR] Invalid search option.");
		}
	}

	private static void executeBFS(State initialState) {
		BreadthFirstSearch search = new BreadthFirstSearch();
		State solution = search.exec(initialState);
		printSolution(solution);
	}

	private static void executeDLS(State initialState) {
		DepthLimitedSearch search = new DepthLimitedSearch();
		State solution = search.exec(initialState);
		printSolution(solution);
	}

	private static void printSolution(State solution) {
		if (null == solution) {
			System.out.print("\nNo solution found.");
		} else {
			System.out.println("\nSolution (cannibalLeft,missionaryLeft,boat,cannibalRight,missionaryRight): ");
			List<State> path = new ArrayList<State>();
			State state = solution;
			while(null!=state) {
				path.add(state);
				state = state.getParentState();
			}

			int depth = path.size() - 1;
			for (int i = depth; i >= 0; i--) {
				state = path.get(i);
				if (state.isGoal()) {
					System.out.print(state.toString());
				} else {
					System.out.print(state.toString() + " -> ");
				}
			}
			System.out.println("\nDepth: " + depth);
		}
	}
}
/*
Output :
Test BFS : 
==== Missionaries and Cannibals Problem ====
Choose the search method: 
	 1. Breadth-first search
	 2. Depth-limited search

Type your choice and press ENTER: 1

Solution (cannibalLeft,missionaryLeft,boat,cannibalRight,missionaryRight): 
(3,3,L,0,0) -> (1,3,R,2,0) -> (2,3,L,1,0) -> (0,3,R,3,0) -> (1,3,L,2,0) -> (1,1,R,2,2) -> (2,2,L,1,1) -> (2,0,R,1,3) -> (3,0,L,0,3) -> (1,0,R,2,3) -> (1,1,L,2,2) -> (0,0,R,3,3)
Depth: 11
BUILD SUCCESSFUL (total time: 7 seconds)

Test Depth Limited Search : 
==== Missionaries and Cannibals Problem ====
Choose the search method: 
	 1. Breadth-first search
	 2. Depth-limited search

Type your choice and press ENTER: 2

Solution (cannibalLeft,missionaryLeft,boat,cannibalRight,missionaryRight): 
(3,3,L,0,0) -> (1,3,R,2,0) -> (3,3,L,0,0) -> (1,3,R,2,0) -> (3,3,L,0,0) -> (1,3,R,2,0) -> (3,3,L,0,0) -> (1,3,R,2,0) -> (3,3,L,0,0) -> (1,3,R,2,0) -> (2,3,L,1,0) -> (0,3,R,3,0) -> (1,3,L,2,0) -> (1,1,R,2,2) -> (2,2,L,1,1) -> (2,0,R,1,3) -> (3,0,L,0,3) -> (1,0,R,2,3) -> (1,1,L,2,2) -> (0,0,R,3,3)
Depth: 19
BUILD SUCCESSFUL (total time: 2 seconds)

*/
