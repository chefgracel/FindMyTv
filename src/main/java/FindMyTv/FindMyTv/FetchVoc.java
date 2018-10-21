package FindMyTv.FindMyTv;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


public class FetchVoc {
	public static void createVocFile(File in, String fn) throws IOException {
		FileWriter writer = new FileWriter("/Users/graceli/subs/" + fn + ".csv");
		for(Element item : parseFromFile(in)) {
//		for(Element item : parseFromWeb("http://www.youdao.com/")) {
			writer.write(item.text() + "\n");
		}
		writer.close();
	}
	
	public static Elements parseFromFile(File in) throws IOException {
		Document doc = Jsoup.parse(in, null);
		Elements items = doc.getElementsByClass("item");
		return items;
	}
	
	public static Elements parseFromWeb(String url) throws IOException {
		Document doc = Jsoup.connect(url).get();
		System.out.println(doc.toString());
		Elements items = doc.select("div.pm");
		System.out.println("hello " + items.size());
		return items;
	}
	
	public static void main(String[] args) throws IOException {
//		File in = new File("/Users/graceli/subs/fun.htm");
//		FetchVoc.createVocFile(in);
	}
}
