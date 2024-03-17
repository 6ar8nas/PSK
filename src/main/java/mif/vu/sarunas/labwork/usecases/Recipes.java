package mif.vu.sarunas.labwork.usecases;

import lombok.Getter;
import lombok.Setter;
import mif.vu.sarunas.labwork.entities.Ingredient;
import mif.vu.sarunas.labwork.entities.Recipe;
import mif.vu.sarunas.labwork.interceptors.LogPerformance;
import mif.vu.sarunas.labwork.persistence.IngredientDAO;
import mif.vu.sarunas.labwork.persistence.RecipeDAO;
import mif.vu.sarunas.labwork.persistence.TagDAO;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Model
@LogPerformance
public class Recipes implements Serializable {
	@Inject
	private RecipeDAO recipeDAO;

	@Inject
	private IngredientDAO ingredientDAO;

	@Inject
	private TagDAO tagDAO;

	@Getter
	private List<Recipe> recipes;

	@Getter
	@Setter
	private List<Long> ingredientFilters = new ArrayList<>();

	@Getter
	@Setter
	private Long tagFilter = null;

	@Getter
	@Setter
	private Recipe recipeToCreate = new Recipe();

	@Getter
	@Setter
	private Long creationTag = null;

	@Getter
	@Setter
	private List<Long> creationIngredients = new ArrayList<>();

	@PostConstruct
	public void init() {
		this.recipes = recipeDAO.findAll();
	}

	public void filterRecipes() {
		this.recipes = recipeDAO.findAllByFilters(this.ingredientFilters, this.tagFilter);
	}

	@Transactional
	public void createRecipe() {
		recipeToCreate.setTag(tagDAO.findById(creationTag));
		List<Ingredient> ingredients = new ArrayList<>();
		for (Long ingredientId : creationIngredients) {
			ingredients.add(ingredientDAO.findById(ingredientId));
		}
		recipeToCreate.setIngredients(ingredients);
		recipeDAO.save(recipeToCreate);
	}
}
