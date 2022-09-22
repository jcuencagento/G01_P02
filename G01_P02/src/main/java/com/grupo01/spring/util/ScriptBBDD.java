package com.grupo01.spring.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.supercsv.cellprocessor.ParseDouble;
import org.supercsv.cellprocessor.ParseInt;
import org.supercsv.cellprocessor.constraint.NotNull;
import org.supercsv.cellprocessor.ift.CellProcessor;
import org.supercsv.io.CsvBeanReader;
//import org.supercsv.io.ICsvBeanReader;
import org.supercsv.prefs.CsvPreference;

import com.grupo01.spring.model.Game;

public class ScriptBBDD {
	
	/*
	 * Metodo complementario para devolver un archivo introduciendo un String con el nombre
	 */
	public static File buscar(String archivoABuscar, File directorio) {
	    File[] archivos = directorio.listFiles(); //Suele ser Paths.get(".").toFile()
	    for (File archivo : archivos) {
	        if (archivo.getName().equals(archivoABuscar)) {
	            return archivo;
	        }
	        if (archivo.isDirectory()) {
	            File resultadoRecursion = buscar(archivoABuscar, archivo);
	            if (resultadoRecursion != null) {
	                return resultadoRecursion;
	            }
	        }
	    }
	    return null;
	}
	
	
	 public static void deCSVaMySQL() {
        String jdbcURL = "jdbc:mysql://localhost:3306/game?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
        String username = "root";
        String password = "Ihpo8Jin";
        String csvFilePath = "vgsalesF.csv";
        File file = buscar(csvFilePath, Paths.get(".").toFile());

        int batchSize = 20;
 
        Connection connection = null;
 
        CsvBeanReader beanReader = null;
        CellProcessor[] processors = new CellProcessor[] {
                new ParseInt(), //  name
                new NotNull(), //  name
                new NotNull(), //  name
                new ParseInt(), // rating
                new NotNull(), //  name
                new NotNull(), //  name
                new ParseDouble(), // rating
                new ParseDouble(), // rating
                new ParseDouble(), // rating
                new ParseDouble(), // rating
                new ParseDouble(), // rating
        };
 
        try {
 
            connection = DriverManager.getConnection(jdbcURL, username, password);
            connection.setAutoCommit(false);
 
            String sql = "INSERT INTO gametable(Rank, Name, Platform, Year, Genre, Publisher, NA_Sales, EU_Sales, JP_Sales, Other_Sales, Global_Sales) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
           
 
            beanReader = new CsvBeanReader(new FileReader(file),
                    CsvPreference.EXCEL_NORTH_EUROPE_PREFERENCE);
 
            beanReader.getHeader(true); // skip header line
 
            String[] header = {"Rank", "Name", "Platform", "Year", "Genre", "Publisher", "NA_Sales", "EU_Sales",
            			"JP_Sales", "Other_Sales", "Global_Sales"};
            
            Game bean = null;
 
            int count = 0;
 
            while ((bean = beanReader.read(Game.class, header, processors)) != null) {
            	
                int Rank = bean.getRank();
                String Name = bean.getName();
                String Platform = bean.getPlatform();
                int Year = bean.getYear();
                String Genre = bean.getGenre();
                String Publisher = bean.getPublisher();
                double NA_Sales = bean.getNA_Sales();
                double EU_Sales = bean.getEU_Sales();
                double JP_Sales = bean.getJP_Sales();
                double Other_Sales = bean.getOther_Sales();
                double Global_Sales = bean.getGlobal_Sales();
 
                statement.setInt(1, Rank);
                statement.setString(2, Name);
                statement.setString(3, Platform);
                statement.setInt(4, Year);
                statement.setString(5, Genre);
                statement.setString(6, Publisher);
                statement.setDouble(7, NA_Sales);
                statement.setDouble(8, EU_Sales);
                statement.setDouble(9, JP_Sales);
                statement.setDouble(10, Other_Sales);
                statement.setDouble(11, Global_Sales);
 
                statement.addBatch();
 
                if (count % batchSize == 0) {
                    statement.executeBatch();
                }
            }
 
            beanReader.close();
 
            // execute the remaining queries
            statement.executeBatch();
 
            connection.commit();
            connection.close();
 
        } catch (IOException ex) {
            System.err.println(ex);
        } catch (SQLException ex) {
            ex.printStackTrace();
 
            try {
                connection.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
 
 
	}
}
