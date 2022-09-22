
package com.grupo01.spring.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * 
 * @author Grupo 01
 *
 */

@Entity
public class Game {
	
	@Id
	@Column(name="Rank")
	private int Rank;
	private String Name;
	private String Platform;
	private int Year;
	private String Genre;
	private String Publisher;
	private double NA_Sales;
	private double EU_Sales;
	private double JP_Sales;
	private double Other_Sales;
	private double Global_Sales;
	
	public int getRank() {
		return Rank;
	}

	public void setRank(int Rank) {
		this.Rank = Rank;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public String getPlatform() {
		return Platform;
	}

	public void setPlatform(String platform) {
		Platform = platform;
	}

	public int getYear() {
		return Year;
	}

	public void setYear(int year) {
		Year = year;
	}

	public String getGenre() {
		return Genre;
	}

	public void setGenre(String genre) {
		Genre = genre;
	}

	public String getPublisher() {
		return Publisher;
	}

	public void setPublisher(String publisher) {
		Publisher = publisher;
	}

	public double getNA_Sales() {
		return NA_Sales;
	}

	public void setNA_Sales(double nA_Sales) {
		NA_Sales = nA_Sales;
	}

	public double getEU_Sales() {
		return EU_Sales;
	}

	public void setEU_Sales(double eU_Sales) {
		EU_Sales = eU_Sales;
	}

	public double getJP_Sales() {
		return JP_Sales;
	}

	public void setJP_Sales(double jP_Sales) {
		JP_Sales = jP_Sales;
	}

	public double getOther_Sales() {
		return Other_Sales;
	}

	public void setOther_Sales(double other_Sales) {
		Other_Sales = other_Sales;
	}

	public double getGlobal_Sales() {
		return Global_Sales;
	}

	public void setGlobal_Sales(double global_Sales) {
		Global_Sales = global_Sales;
	}

	public Game() {
		super();
	}

	@Override
	public String toString() {
		return "Game [Rank=" + Rank + ", Name=" + Name + ", Platform=" + Platform + ", Year=" + Year + ", Genre="
				+ Genre + ", Publisher=" + Publisher + ", NA_Sales=" + NA_Sales + ", EU_Sales=" + EU_Sales
				+ ", JP_Sales=" + JP_Sales + ", Other_Sales=" + Other_Sales + ", Global_Sales=" + Global_Sales + "]";
	}
	
	

}
