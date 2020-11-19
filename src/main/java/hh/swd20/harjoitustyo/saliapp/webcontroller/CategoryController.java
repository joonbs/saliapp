package hh.swd20.harjoitustyo.saliapp.webcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import hh.swd20.harjoitustyo.saliapp.domain.Exercise;
import hh.swd20.harjoitustyo.saliapp.domain.ExerciseRepository;
import hh.swd20.harjoitustyo.saliapp.domain.Category;
import hh.swd20.harjoitustyo.saliapp.domain.CategoryRepository;

@Controller
public class CategoryController {
	// inject repositories
	@Autowired
	private ExerciseRepository erepository;
	
	@Autowired
	private CategoryRepository crepository;
	
	// list all categories
	@RequestMapping("/categorylist")
	public String categoryList(Model model) {
		model.addAttribute("categories", crepository.findAll());
		return "categorylist";
	}	
	
	// add category
	@RequestMapping(value = "/addcategory")
	public String addCategory(Model model) {
		model.addAttribute("category", new Category());
		model.addAttribute("exercise", erepository.findAll());
		return "addcategory";
	}

}
