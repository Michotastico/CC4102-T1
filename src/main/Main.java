package main;

import java.io.File;
import java.net.URL;

import algorithm.TwoWay;

public class Main {

	public static void main(String[] args) {
		TwoWay merge = new TwoWay("Data/data.txt");
		merge.sort();

	}

}
