
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Scanner;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class WikiScraper {
    public static void main(String[] args) {
        //Creates main entry list, takes in user input
        ArrayList<FightEntry> entries = new ArrayList<>();
        Scanner scan = new Scanner(System.in);
        System.out.println("Input your Boxer's Wikipedia URL");
        final String url = scan.nextLine();
        scan.close();
        //Connects Jsoup to url and parses HTML
        try {
            final Document doc = Jsoup.connect(url).get();
            final Elements rows = doc.select("table.wikitable tr");
            final String title = doc.select("title").text();
            //Saves table elements to entry list.
            for (Element row : rows) {
                Elements items = row.select("td");
                if(items.size() == 9){
                    FightEntry entry = new FightEntry(items.get(0).text(), items.get(1).text(), items.get(2).text(), items.get(3).text(), items.get(4).text(), items.get(5).text(), items.get(6).text(), items.get(7).text(), items.get(8).text());
                    entries.add(entry);
                }
            }
            //Creates workbook and formats to save data
            Workbook book = new XSSFWorkbook();
            Sheet sheet = book.createSheet("FightEntries");
            String[] headings = {"Fight Number", "Fight Result", "Current Record", "Opponent", "Result Type", "Round/Time", "Date", "Location", "Notes"};
            Row header = sheet.createRow(0);
            for (int i = 0; i < headings.length; i++) {
                Cell cell = header.createCell(i);
                cell.setCellValue(headings[i]);
            }
            int rowNum = 1;
            //Adds each entry field to a workbook cell
            for(FightEntry entry : entries){
                Row row = sheet.createRow(rowNum++);
                row.createCell(0).setCellValue(entry.getFightNum());
                row.createCell(1).setCellValue(entry.getRecord());
                row.createCell(2).setCellValue(entry.getResult());
                row.createCell(3).setCellValue(entry.getOpponent());
                row.createCell(4).setCellValue(entry.getResultType());
                row.createCell(5).setCellValue(entry.getRoundTime());
                row.createCell(6).setCellValue(entry.getDate());
                row.createCell(7).setCellValue(entry.getLocation());
                row.createCell(8).setCellValue(entry.getNotes());
            }
            //Adds dynamic workbook formatting
            sheet.createFreezePane(0, 1);
            for (int i = 0; i < headings.length; i++) {
                sheet.autoSizeColumn(i);
            }
            //Writes to spreadsheet file
            FileOutputStream fileOut = new FileOutputStream("/Users/joshuamasters724/Documents/Programming/Spreadsheets/" + title + ".xlsx");
            book.write(fileOut);
            fileOut.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
