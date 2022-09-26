package com.grupo01.spring.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.grupo01.spring.dao.GameDAO;
import com.grupo01.spring.model.Game;
import com.grupo01.spring.service.GameService;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

@Service("script")
public class ScriptBBDD {
	
	private static final Logger log = LoggerFactory.getLogger(ScriptBBDD.class);
	
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
        String csvFilePath = "minivgsalesyeah.csv";
        File file = buscar(csvFilePath, Paths.get(".").toFile());

 
        Connection connection = null;
        try {
        	 
            connection = DriverManager.getConnection(jdbcURL, username, password);
            connection.setAutoCommit(false);
            
            //Aqui inicializaremos BBDD
            Statement statement = connection.createStatement();
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS `minigametable` (     `game_id` int(11) NOT NULL AUTO_INCREMENT, `Name` varchar(300) NOT NULL,    `Platform` varchar(45) NOT NULL,    `Year` int(11) NOT NULL,    `Genre` varchar(45) NOT NULL,    `Publisher` varchar(45) NOT NULL,    `NA_Sales` double,    `EU_Sales` double,    `JP_Sales` double,    `Other_Sales` double,    `Global_Sales` double,    PRIMARY KEY (`Rank`) ) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;");
            
            BufferedReader lineReader = new BufferedReader(new FileReader(file));
            String lineText = null;
 
 
            lineReader.readLine(); // skip header line
 
            while ((lineText = lineReader.readLine()) != null) {
            	
                String[] data = lineText.split("}");
                String Rank = data[0];
                String Name = data[1];
                String Platform = data[2];
                String Year = data[3];
                String Genre = data[4];
                String Publisher = data[5];
                String NA_Sales = data[6];
                String EU_Sales = data[7];
                String JP_Sales = data[8];
                String Other_Sales = data[9];
                String Global_Sales = data[10];
                
                int RankI = Integer.parseInt(Rank);
                int YearI=1;
                try {
                	YearI = Integer.parseInt(Year);
                }catch(Exception e) {
                	YearI = 1;
                }
                Double fNA = 0.0;
                try {
                	fNA = Double.parseDouble(NA_Sales);
                }catch(Exception e) {
                	fNA = 0.0;
                }
                Double fEU = Double.parseDouble(EU_Sales);
                Double fJP = Double.parseDouble(JP_Sales);
                Double fOther = Double.parseDouble(Other_Sales);
                Double fGlobal = Double.parseDouble(Global_Sales);

                String sql = "INSERT INTO minigametable VALUES ("+
                		RankI+", '"+Name+"', '"+Platform+"', "+YearI+", '"+Genre+"', '"+Publisher+"', "+fNA+", "+fEU+", "+fJP+", "+fOther+", "+fGlobal+");";
                
                log.info("Introducimos: "+Name);
                
                statement = connection.createStatement();
     
                statement.executeUpdate(sql);
                
            }
            
            connection.commit();
            connection.close();
            lineReader.close();
            
 
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
