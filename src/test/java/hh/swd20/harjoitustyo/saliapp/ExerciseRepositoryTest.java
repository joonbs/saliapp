package hh.swd20.harjoitustyo.saliapp;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import hh.swd20.harjoitustyo.saliapp.domain.Category;
import hh.swd20.harjoitustyo.saliapp.domain.Exercise;
import hh.swd20.harjoitustyo.saliapp.domain.ExerciseRepository;

	//@RunWith(SpringRunner.class)  //JUnit4
	@ExtendWith(SpringExtension.class)  // JUnit5 aka Jupiter
	@DataJpaTest
	public class ExerciseRepositoryTest {

		// inject repositories
	    @Autowired
	    private ExerciseRepository erepository;

	    @Test  // testing ExerciseRepository's findByName() functionality (search)
	    public void findByNameShouldReturnName() {
	        List<Exercise> exercises = erepository.findByName("Maastaveto");
	        
	        // specifications to find wanted test data
	        assertThat(exercises).hasSize(1);
	        assertThat(exercises.get(0).getName()).isEqualTo("Maastaveto");
	    }
	    
	    @Test // testing ExerciseRepository's save() functionality (create)
	    public void createNewExercise() {
	    	Exercise exercise = new Exercise("Kyykyt", 50.0, 3, 8, "12.12.2012", "hyvä", new Category("Jalat"));
	    	erepository.save(exercise);
	    	assertThat(exercise.getId()).isNotNull();
	    }

	}


