package mif.vu.sarunas.labwork.usecases;

import lombok.Getter;
import lombok.Setter;
import mif.vu.sarunas.labwork.interceptors.LogPerformance;
import mif.vu.sarunas.labwork.mybatis.dao.RecipeIngredientMapper;
import mif.vu.sarunas.labwork.mybatis.dao.RecipeMapper;
import mif.vu.sarunas.labwork.mybatis.model.Recipe;
import mif.vu.sarunas.labwork.mybatis.model.RecipeIngredient;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Model
@LogPerformance
public class RecipesMyBatis implements Serializable {
	@Inject
	private RecipeMapper recipeMapper;

	@Inject
	private RecipeIngredientMapper recipeIngredientMapper;

	private List<Recipe> allRecipes;

	@Getter
	private List<Recipe> recipes;

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
		this.allRecipes = recipeMapper.selectAll();
		this.recipes = this.allRecipes;
	}

	public void filterRecipes() {
		this.recipes = tagFilter == null ? this.allRecipes : allRecipes.stream()
				.filter(recipe -> Objects.equals(recipe.getTagId(), tagFilter))
				.collect(Collectors.toList());
	}

	@Transactional
	public void createRecipe() {
		recipeToCreate.setTagId(creationTag);
		recipeMapper.insert(recipeToCreate);
		for (Long ingredientId : creationIngredients) {
			RecipeIngredient recipeIngredient = new RecipeIngredient();
			recipeIngredient.setRecipesId(recipeToCreate.getId());
			recipeIngredient.setIngredientsId(ingredientId);
			recipeIngredientMapper.insert(recipeIngredient);
		}
	}
}
