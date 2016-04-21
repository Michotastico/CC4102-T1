package main;

import algorithm.*;

public class Main {

	public static void main(String[] args) {
		Mergesort merge = new Adaptive("Data/data.txt");
		merge.sort();

	}

}
