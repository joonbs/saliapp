package hh.swd20.harjoitustyo.saliapp;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import hh.swd20.harjoitustyo.saliapp.domain.Category;
import hh.swd20.harjoitustyo.saliapp.domain.CategoryRepository;

//@RunWith(SpringRunner.class)  //JUnit4
@ExtendWith(SpringExtension.class)  // JUnit5 eli Jupiter
@DataJpaTest
public class CategoryRepositoryTest {

    @Autowired
    private CategoryRepository crepository;

    @Test  // testataan CategoryRepositoryn findByName()-metodin toimivuutta (search)
    public void findByNameShouldReturnCategoryName() {
        List<Category> categories = crepository.findByName("Veto");
        
        assertThat(categories).hasSize(1);
        assertThat(categories.get(0).getName()).isEqualTo("Veto");
    }
    
    @Test // testataan CategoryRepositoryn save()-metodin toimivuutta (create)
    public void createNewCategory() {
    	Category category = new Category("Jalat");
    	crepository.save(category);
    	assertThat(category.getCategoryId()).isNotNull();
    }    

}