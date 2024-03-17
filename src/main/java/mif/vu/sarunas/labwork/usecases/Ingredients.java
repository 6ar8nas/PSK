package mif.vu.sarunas.labwork.usecases;

import lombok.Getter;
import lombok.Setter;
import mif.vu.sarunas.labwork.entities.Ingredient;
import mif.vu.sarunas.labwork.entities.IngredientCategory;
import mif.vu.sarunas.labwork.interceptors.LogPerformance;
import mif.vu.sarunas.labwork.persistence.IngredientDAO;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.List;

@Model
@LogPerformance
public class Ingredients implements Serializable {
	@Inject
	private IngredientDAO ingredientDAO;

	@Getter
	@Setter
	private Ingredient ingredientToCreate = new Ingredient();

	@Getter
	private List<Ingredient> allIngredients;

	@Getter
	private IngredientCategory[] allCategories;

	@PostConstruct
	public void init() {
		loadAllIngredients();
	}

	@Transactional
	public void createIngredient() {
		this.ingredientDAO.save(ingredientToCreate);
	}

	private void loadAllIngredients() {
		this.allIngredients = ingredientDAO.findAll();
		this.allCategories = IngredientCategory.values();
	}
}