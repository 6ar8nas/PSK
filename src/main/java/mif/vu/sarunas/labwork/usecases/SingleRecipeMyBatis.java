package mif.vu.sarunas.labwork.usecases;

import lombok.Getter;
import mif.vu.sarunas.labwork.entities.IngredientCategory;
import mif.vu.sarunas.labwork.interceptors.LogPerformance;
import mif.vu.sarunas.labwork.mybatis.dao.IngredientMapper;
import mif.vu.sarunas.labwork.mybatis.dao.RecipeIngredientMapper;
import mif.vu.sarunas.labwork.mybatis.dao.RecipeMapper;
import mif.vu.sarunas.labwork.mybatis.dao.TagMapper;
import mif.vu.sarunas.labwork.mybatis.model.Ingredient;
import mif.vu.sarunas.labwork.mybatis.model.Recipe;
import mif.vu.sarunas.labwork.mybatis.model.RecipeIngredient;
import mif.vu.sarunas.labwork.mybatis.model.Tag;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Model
@ViewScoped
@LogPerformance
public class SingleRecipeMyBatis implements Serializable {
	@Inject
	private RecipeMapper recipeMapper;

	@Inject
	private RecipeIngredientMapper recipeIngredientMapper;

	@Inject
	private IngredientMapper ingredientMapper;

	@Inject
	private TagMapper tagMapper;

	@Getter
	private Recipe recipe;

	@Getter
	private List<Ingredient> ingredients;

	@Getter
	private Tag tag;

	@PostConstruct
	public void init() {
		Map<String, String> requestParameters =
				FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
		Long recipeId = Long.parseLong(requestParameters.get("recipeId"));
		this.recipe = recipeMapper.selectByPrimaryKey(recipeId);
		this.ingredients = recipeIngredientMapper.selectAll().stream()
				.filter(recipe -> Objects.equals(recipeId, recipe.getRecipesId()))
				.map(RecipeIngredient::getIngredientsId)
				.map(ingredientMapper::selectByPrimaryKey)
				.collect(Collectors.toList());
		this.tag = tagMapper.selectByPrimaryKey(recipe.getTagId());
	}

	public String convertCategoryToString(Integer category) {
		return IngredientCategory.values()[category].name();
	}
}
