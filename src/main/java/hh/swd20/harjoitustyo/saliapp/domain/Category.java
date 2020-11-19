package hh.swd20.harjoitustyo.saliapp.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
public class Category {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	// attributes
	public Long categoryId;		
	public String name;
		
		// relationship
		@OneToMany(cascade = CascadeType.ALL, mappedBy = "category")
		// use JsonIgnore(+Properties) to avoid infinite loops during JSON serialization
		@JsonIgnoreProperties("category") 
		public List<Exercise> exercises;
		
		// constructor without attributes
		public Category() {}
		
		// constructors
		public Category(String name) {
			super();
			this.name = name;
		}
		
		// getters and setters
		public Long getCategoryId() {
			return categoryId;
		}
		
		public void setCategoryId(Long categoryId) {
			this.categoryId = categoryId;
		}
		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}
		public List<Exercise> getExercises() {
			return exercises;
		}
		public void setExercises(List<Exercise> exercises) {
			this.exercises = exercises;
		}

		// toString method
		@Override
		public String toString() {
			return "Category [categoryId=" + categoryId + ", name=" + name + "]";
		}
			
}