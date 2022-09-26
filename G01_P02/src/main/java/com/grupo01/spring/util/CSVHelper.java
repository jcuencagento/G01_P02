package com.grupo01.spring.util;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.csv.QuoteMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.grupo01.spring.model.Game;
import com.grupo01.spring.service.GameService;

@Service("csv")
public class CSVHelper {
  public static String TYPE = "text/csv";
  static String[] HEADERs = { "id", "Name", "Platform", "Year", "Genre", "Publisher", "NA_Sales", "EU_Sales", "JP_Sales", "Other_Sales", "Global_Sales" };
  
  @Autowired
  GameService service;
  
  public static boolean hasCSVFormat(MultipartFile file) {
    if (TYPE.equals(file.getContentType())
    		|| file.getContentType().equals("application/vnd.ms-excel")) {
      return true;
    }

    return false;
  }

  public void csvToGames(File file) {
    try (BufferedReader fileReader = new BufferedReader(new FileReader(file));
        CSVParser csvParser = new CSVParser(fileReader,
            CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim().withDelimiter('}'));) {


      Iterable<CSVRecord> csvRecords = csvParser.getRecords();
      
      for (CSVRecord csvRecord : csvRecords) {
    	  int year = 0;
    	  try {
    		  year = Integer.parseInt(csvRecord.get(3));
    	  }catch(Exception e){
    	  }
    	  Game game = new Game(
              Integer.parseInt(csvRecord.get(0)), //id
              csvRecord.get(1),
              csvRecord.get(2),
              year,
              csvRecord.get(4),
              csvRecord.get(5),
              Double.parseDouble(csvRecord.get(6)),
              Double.parseDouble(csvRecord.get(7)),
              Double.parseDouble(csvRecord.get(8)),
              Double.parseDouble(csvRecord.get(9)),
              Double.parseDouble(csvRecord.get(10))
              
            );

    	  service.save(game);
      }

    } catch (IOException e) {
      throw new RuntimeException("fail to parse CSV file: " + e.getMessage());
    }
  }

  public static ByteArrayInputStream gamesToCSV(List<Game> gameList) {
    //final CSVFormat format = CSVFormat.DEFAULT.withQuoteMode(QuoteMode.MINIMAL);
    final CSVFormat format = CSVFormat.DEFAULT.withDelimiter('}');

    try (ByteArrayOutputStream out = new ByteArrayOutputStream();
        CSVPrinter csvPrinter = new CSVPrinter(new PrintWriter(out), format);) {
    	for (Game g : gameList) {
    		List<String> data = Arrays.asList(
    			String.valueOf(g.getId()),
    			g.getName(),
    			g.getPlatform(),
    			String.valueOf(g.getYear()),
    			g.getGenre(),
    			g.getPublisher(),
    			String.valueOf(g.getNa_Sales()),
    			String.valueOf(g.getEu_Sales()),
    			String.valueOf(g.getJp_Sales()),
    			String.valueOf(g.getOther_Sales()),
    			String.valueOf(g.getGlobal_Sales())
            );

        csvPrinter.printRecord(data);
      }

      csvPrinter.flush();
      return new ByteArrayInputStream(out.toByteArray());
    } catch (IOException e) {
      throw new RuntimeException("fail to import data to CSV file: " + e.getMessage());
    }
  }
}