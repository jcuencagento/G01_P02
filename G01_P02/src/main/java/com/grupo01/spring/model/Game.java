
package com.grupo01.spring.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 
 * @author Grupo 01
 *
 */

@Entity
@Table(name="gametable")
public class Game {
	
	@Id
	@Column(name="Rank")
	private int id;
	@Column(name="Name")
	private String name;
	@Column(name="Platform")
	private String platform;
	@Column(name="Year")
	private int year;
	@Column(name="Genre")
	private String genre;
	@Column(name="Publisher")
	private String publisher;
	@Column(name="NA_Sales")
	private double na_Sales;
	@Column(name="EU_Sales")
	private double eu_Sales;
	@Column(name="JP_Sales")
	private double jp_Sales;
	@Column(name="Other_Sales")
	private double other_Sales;
	@Column(name="Global_Sales")
	private double global_Sales;
	
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

	public double getNA_Sales() {
		return na_Sales;
	}

	public void setNA_Sales(double nA_Sales) {
		this.na_Sales = nA_Sales;
	}

	public double getEU_Sales() {
		return eu_Sales;
	}

	public void setEU_Sales(double eU_Sales) {
		this.eu_Sales = eU_Sales;
	}

	public double getJP_Sales() {
		return jp_Sales;
	}

	public void setJP_Sales(double jP_Sales) {
		this.jp_Sales = jP_Sales;
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

	public Game() {
		super();
	}

	@Override
	public String toString() {
		return "Game [Rank=" + id + ", Name=" + name + ", Platform=" + platform + ", Year=" + year + ", Genre="
				+ genre + ", Publisher=" + publisher + ", NA_Sales=" + na_Sales + ", EU_Sales=" + eu_Sales
				+ ", JP_Sales=" + jp_Sales + ", Other_Sales=" + other_Sales + ", Global_Sales=" + global_Sales + "]";
	}
	
	

}
