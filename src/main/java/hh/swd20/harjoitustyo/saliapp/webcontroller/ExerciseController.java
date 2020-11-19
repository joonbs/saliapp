package hh.swd20.harjoitustyo.saliapp.webcontroller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import hh.swd20.harjoitustyo.saliapp.domain.Exercise;
import hh.swd20.harjoitustyo.saliapp.domain.ExerciseRepository;
import hh.swd20.harjoitustyo.saliapp.domain.CategoryRepository;

@Controller
public class ExerciseController {
	
	// inject repositories
	@Autowired
	private ExerciseRepository erepository;
	
	@Autowired
	private CategoryRepository crepository;
	

	
	// login page, delete login.html to use spring's default login page
	@RequestMapping(value="/login")
	public String login() {
		return "login";
	}
	
	// RESTful service to get all exercises
    @RequestMapping(value="/exercises", method = RequestMethod.GET)
    public @ResponseBody List<Exercise> exerciseListRest() {	
        return (List<Exercise>) erepository.findAll();
    }    

	// RESTful service to get exercise by id
    @RequestMapping(value="/exercises/{id}", method = RequestMethod.GET)
    public @ResponseBody Optional<Exercise> findExerciseRest(@PathVariable("id") Long exerciseId) {	
    	return erepository.findById(exerciseId);
    }      
    
	
	// list all exercises
	@RequestMapping("/exerciselist")
	public String exerciseList(Model model) {
		model.addAttribute("exercises", erepository.findAll());
		return "exerciselist";
	}
	
	// delete exercise (admin only)
	@PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public String deleteExercise(@PathVariable("id") Long exerciseId, Model model) {
		erepository.deleteById(exerciseId);
		return "redirect:../exerciselist";
	}
	
	// add exercise (user/admin only)
	@PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
	@RequestMapping(value = "/add")
	public String addExercise(Model model) {
		model.addAttribute("exercise", new Exercise());
		model.addAttribute("categories", crepository.findAll());
		return "addexercise";
	}

	// save exercise (user/admin only)
	@PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(Exercise exercise) {
		erepository.save(exercise);
		return "redirect:exerciselist";	
	}
	
	// edit exercise (user/admin only)
	@PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
	@RequestMapping(value = "/edit/{id}")
	public String addExercise(@PathVariable("id") Long id, Model model){
	model.addAttribute("exercise", erepository.findById(id));
	model.addAttribute("categories", crepository.findAll());
	return "editexercise";
	}
	
	 // validating addexercise form inputs
	@PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
    @RequestMapping(value="/addexercise", method=RequestMethod.POST)
    public String exerciseSubmit(@Valid Exercise exercise, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) { // validation errors 
			return "addexercise";  // return back to form
		} else { // no validation errors
			model.addAttribute("exercise", new Exercise());
			model.addAttribute("categories", crepository.findAll());
			return "exerciselist";
		}
    }

}
