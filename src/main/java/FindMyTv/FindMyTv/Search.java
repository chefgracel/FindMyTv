package FindMyTv.FindMyTv;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Search {
	public static void readFile(File dir, String item, FileWriter writer) {
		if (dir.exists()) // Directory exists then proceed.
		{
			Pattern p = Pattern.compile(" " + item + " "); // keyword = keyword to search in files.
			Scanner in;
			try {
				for (File f : dir.listFiles()) {
					if (f.isFile()) {
						in = new Scanner(f);
						String contents = in.nextLine();

						while (in.hasNextLine()) {
							contents = in.nextLine();
							Matcher m = p.matcher(contents);
							if (m.find()) {
								System.out.println(contents);
								writer.write(contents + "\n");
							}
						}
					} else if (f.isDirectory()) {
						readFile(f, item, writer);
					}
				}
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		} // IF directory exists then only process.
		else {
			System.out.print("\n Directory doesn't exist.");
		}
	}

	public static void main(String[] args) {
		File dir = new File("/Users/graceli/subs/How");
		try {
			Scanner sc = new Scanner(System.in);
			String htmFileName = sc.nextLine();
			File in = new File("/Users/graceli/subs/" + htmFileName + ".htm");
			FileWriter writer = new FileWriter("/Users/graceli/subs/" + htmFileName + "_result.csv");
			FetchVoc.createVocFile(in, htmFileName);
			BufferedReader br = new BufferedReader(new FileReader("/Users/graceli/subs/" + htmFileName + ".csv"));
			for (String line; (line = br.readLine()) != null;) {
				System.out.println(line);
				Search.readFile(dir, line, writer);
			}
			br.close();
			writer.close();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

}
