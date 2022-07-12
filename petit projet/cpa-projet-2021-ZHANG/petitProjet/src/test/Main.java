package test;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import algorithms.Naif;
import algorithms.Welzl;

public class Main {
	protected static String samplesDirName = "../samples";
	protected static File result = new File("result.txt");
	protected static String path = ".points";

	public static void main(String[] args) {
		Naif naif = new Naif();
		Welzl welzl = new Welzl();
		long t1, t2;
		long total1 = 0, total2 = 0;
		double moyen1, moyen2;

		for (int i = 2; i <= 1664; i++) {
			ArrayList<Point> points = new ArrayList<Point>();
			try {
				BufferedReader in = new BufferedReader(new FileReader("./samples/test-" + i + ".points"));
				String line;
				while ((line = in.readLine()) != null) {
					String[] coordinates = line.split("\\s+");
					points.add(new Point(Integer.parseInt(coordinates[0]), Integer.parseInt(coordinates[1])));
				}
				t1 = System.currentTimeMillis();
				naif.calculCercleMin(points);
				t1 = System.currentTimeMillis() - t1;

				t2 = System.currentTimeMillis();
				welzl.calculCercleMin(points);
				t2 = System.currentTimeMillis() - t2;

				total1 += t1;
				total2 += t2;

				String contents = "test" + i + " " + t1 + " " + t2 + "\n";
				writeFile(result, contents);
				in.close();
			} catch (IOException e) {
			}
		}
		moyen1 = (double)total1 / 1663;
		moyen2 = (double)total2 / 1663;
		String contents = "result moyen " + moyen1 + " " + moyen2 + "\n";
		writeFile(result, contents);
	}

	public static ArrayList<Point> readFile(File file) {
		ArrayList<Point> points = new ArrayList<>();
		try {
			BufferedReader input = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
			try {
				String line;
				while ((line = input.readLine()) != null) {
					String[] coordinates = line.split("\\s+");
					points.add(new Point(Integer.parseInt(coordinates[0]), Integer.parseInt(coordinates[1])));
				}
			} catch (IOException e) {
				System.err.println("Exception: interrupted I/O.");
			} finally {
				try {
					input.close();
				} catch (IOException e) {
					System.err.println("I/O exception: unable to close " + file.getName());
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		return points;
	}

	public static void writeFile(File file, String contents) {
		try {
			FileWriter writer = new FileWriter(file, true);
			writer.write(contents);
			writer.flush();
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}