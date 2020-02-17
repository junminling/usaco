//package dec2019.silver;

import java.io.*;
import java.util.Scanner;

public class MooBuzz {
	int[] orig = {1, 2, 4, 7, 8, 11, 13, 14};
	public int findValue(int count){
		int num = (count-1)/8;
		int mod = (count-1)%8;
		return orig[mod]+num*15;
	}

	public static void main(String[] args){
		try {
//			Scanner scanner = new Scanner(new File("/Users/jling/git/usaco/src/dec2019/silver/moobuzz.in"));
			Scanner scanner = new Scanner(new File("moobuzz.in"));
			int count = scanner.nextInt();
			MooBuzz moobuzz = new MooBuzz();

			int value = moobuzz.findValue(count);
			System.out.println(value);

//			BufferedWriter bw = new BufferedWriter(new FileWriter("/Users/jling/git/usaco/src/dec2019/silver/moobuzz.out"));
			BufferedWriter bw = new BufferedWriter(new FileWriter("moobuzz.out"));
			bw.write(String.valueOf(value));
			bw.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
