package hh.swd20.harjoitustyo.saliapp.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
public class Exercise {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	// attributes
	public Long id;
	
	// validating min and max size of String name
	@Size(min=5, max=30)
	private String name;
	private double weight;
	private int setAmount;
	private int repAmount;
	private String date;
	private String comment;
	
	// relationships & Category constructor
	@ManyToOne
	// @JsonIgnoreProperties - one way to avoid infinite loop during JSON serialization/deserialization with bidirectional relationships
	@JsonIgnoreProperties("categories")
	@JoinColumn(name = "categoryId")
	private Category category;
	
	// constructor to hold attributes from setters
	public Exercise() {}
	
	// constructors
	public Exercise(String name, double weight, int setAmount, int repAmount, String date, String comment, Category category) {
		super();
		this.name = name;
		this.weight = weight;
		this.setAmount = setAmount;
		this.repAmount = repAmount;
		this.date = date;
		this.comment = comment;
		this.category = category;
	}

	// getters & setters
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	public int getSetAmount() {
		return setAmount;
	}

	public void setSetAmount(int setAmount) {
		this.setAmount = setAmount;
	}

	public int getRepAmount() {
		return repAmount;
	}

	public void setRepAmount(int repAmount) {
		this.repAmount = repAmount;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}
	
	// toString method
	@Override
	public String toString() {
		return "Exercise [id= " + id + " + name=" + name + ", weight=" + weight + ", setAmount=" + setAmount + ", repAmount=" + repAmount + 
				", date=" + date + ", comment=" + comment + ", category =" + this.getCategory() + "]";
	}
	
	
}