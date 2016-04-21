package main;

import algorithm.*;

public class Main {

	public static void main(String[] args) {
		Mergesort merge = new TwoPhaseMultiway("Data/data.txt", 8);
		merge.sort();

	}

}
