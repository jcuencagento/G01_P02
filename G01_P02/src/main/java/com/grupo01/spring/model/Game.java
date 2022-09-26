
package com.grupo01.spring.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.opencsv.bean.CsvBindByName;

/**
 * 
 * @author Grupo01
 * 
 * Clase Juego(Game). 
 * Esta es una clase POJO (Plain Old Java Object). Permite guardar en nuestro programa objetos traídos de la Base de Datos.
 * Mediante la etiqueta @Entity, especificamos a Spring que el objeto es una Entidad (que proviene de la BD).
 * Mediante la etiqueta @Table(="nombreTabla") podemos relacionar nuestra tabla de juegos con nuestro objeto.
 * 
 * La definicion de los atributos incluyen las etiquetas @Column, para referirse al nombre del registro en la tabla; y @Id para definir en el objeto Java la Clave Primaria.
 */

@Entity
@Table(name="completetable")
public class Game {
	
	@Id
	@CsvBindByName(column="id")
	private int id;
	@CsvBindByName(column="Name")
	private String name;
	@CsvBindByName(column="Platform")
	private String platform;
	@CsvBindByName(column="Year")
	private int year;
	@CsvBindByName(column="Genre")
	private String genre;
	@CsvBindByName(column="Publisher")
	private String publisher;
	@CsvBindByName(column="NA_Sales")
	private double na_Sales;
	@CsvBindByName(column="EU_Sales")
	private double eu_Sales;
	@CsvBindByName(column="JP_Sales")
	private double jp_Sales;
	@CsvBindByName(column="Other_Sales")
	private double other_Sales;
	@CsvBindByName(column="Global_Sales")
	private double global_Sales;
	
	public Game() {
		super();
	}
	
	public Game(int id, String name, String platform, int year, String genre, String publisher, double na_Sales,
			double eu_Sales, double jp_Sales, double other_Sales, double global_Sales) {
		super();
		this.id = id;
		this.name = name;
		this.platform = platform;
		this.year = year;
		this.genre = genre;
		this.publisher = publisher;
		this.na_Sales = na_Sales;
		this.eu_Sales = eu_Sales;
		this.jp_Sales = jp_Sales;
		this.other_Sales = other_Sales;
		this.global_Sales = global_Sales;
	}


	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPlatform() {
		return platform;
	}
	public void setPlatform(String platform) {
		this.platform = platform;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public String getGenre() {
		return genre;
	}
	public void setGenre(String genre) {
		this.genre = genre;
	}
	public String getPublisher() {
		return publisher;
	}
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	public double getNa_Sales() {
		return na_Sales;
	}
	public void setNa_Sales(double na_Sales) {
		this.na_Sales = na_Sales;
	}
	public double getEu_Sales() {
		return eu_Sales;
	}
	public void setEu_Sales(double eu_Sales) {
		this.eu_Sales = eu_Sales;
	}
	public double getJp_Sales() {
		return jp_Sales;
	}
	public void setJp_Sales(double jp_Sales) {
		this.jp_Sales = jp_Sales;
	}
	public double getOther_Sales() {
		return other_Sales;
	}
	public void setOther_Sales(double other_Sales) {
		this.other_Sales = other_Sales;
	}
	public double getGlobal_Sales() {
		return global_Sales;
	}
	public void setGlobal_Sales(double global_Sales) {
		this.global_Sales = global_Sales;
	}
	@Override
	public String toString() {
		return "Game [id=" + id + ", name=" + name + ", platform=" + platform + ", year=" + year + ", genre=" + genre
				+ ", publisher=" + publisher + ", na_Sales=" + na_Sales + ", eu_Sales=" + eu_Sales + ", jp_Sales="
				+ jp_Sales + ", other_Sales=" + other_Sales + ", global_Sales=" + global_Sales + "]";
	}
	
	
	
	

}
