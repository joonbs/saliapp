package hh.swd20.harjoitustyo.saliapp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import hh.swd20.harjoitustyo.saliapp.domain.Exercise;
import hh.swd20.harjoitustyo.saliapp.domain.ExerciseRepository;
import hh.swd20.harjoitustyo.saliapp.domain.Category;
import hh.swd20.harjoitustyo.saliapp.domain.CategoryRepository;
import hh.swd20.harjoitustyo.saliapp.domain.User;
import hh.swd20.harjoitustyo.saliapp.domain.UserRepository;

@SpringBootApplication
public class SaliAppApplication {
	private static final Logger log = LoggerFactory.getLogger(SaliAppApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(SaliAppApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner demo(ExerciseRepository erepository, CategoryRepository crepository, UserRepository urepository) {
		return (args) -> {
			log.info("Creating categories...");
			// Create categories
			crepository.save(new Category("Veto"));
			crepository.save(new Category("Työntö"));
			crepository.save(new Category("Muu"));
			
			// Save some exercise data
			erepository.save(new Exercise("Maastaveto", 50.0, 1, 10, "9.9.2020", "Lisää painoa", crepository.findByName("Veto").get(0)));
			erepository.save(new Exercise("Penkkipunnerrus", 20.0, 5, 5, "10.9.2020", "Huono tekniikka", crepository.findByName("Työntö").get(0)));
			erepository.save(new Exercise("Lankku", 0.0, 3, 30, "9.9.2020", "Ei moitittavaa", crepository.findByName("Muu").get(0)));
			
			// Create users
			User user1 = new User("user", "$2a$10$ygrX9.DuLjOHihmHSyHhc.S4cZQhaTCYXZAGksML1rEZww6nvtoaG", "USER");
			User user2 = new User("admin", "$2a$10$QcNwujpuCwUJSrrbnrnz2O27Hf.HcUXx7nGq1apgARRDIbsIWehsK", "ADMIN");
			urepository.save(user1);
			urepository.save(user2);
			
			log.info("Fetching all exercises");
			for (Exercise exercise : erepository.findAll()) {
				log.info(exercise.toString());
			}
			
		};
		
	}
	
}