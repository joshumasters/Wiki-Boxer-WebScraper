
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Scanner;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class WikiScraper {
    public static void main(String[] args) {
        ArrayList<FightEntry> entries = new ArrayList<>();
        Scanner scan = new Scanner(System.in);
        System.out.println("Input your Boxer's Wikipedia URL");
        final String url = scan.nextLine();
        try {
            final Document doc = Jsoup.connect(url).get();
            final Elements rows = doc.select("table.wikitable tr");
            for (Element row : rows) {
                Elements items = row.select("td");
                if(items.size() == 9){
                    FightEntry entry = new FightEntry(items.get(0).text(), items.get(1).text(), items.get(2).text(), items.get(3).text(), items.get(4).text(), items.get(5).text(), items.get(6).text(), items.get(7).text(), items.get(8).text());
                    System.out.println(entry.toString());
                    entries.add(entry);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
			FileOutputStream fileOut = new FileOutputStream(
					"/Users/joshuamasters724/Documents/Programming/Test.xlsx");
			ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
			objectOut.writeObject(entries);
			objectOut.close();
		} catch (Exception e) {
            e.printStackTrace();
			return;
		}
    }
}
