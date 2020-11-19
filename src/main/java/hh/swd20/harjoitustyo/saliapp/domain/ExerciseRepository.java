package hh.swd20.harjoitustyo.saliapp.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface ExerciseRepository extends CrudRepository<Exercise, Long> {

    List<Exercise> findByName(String name);
    
}