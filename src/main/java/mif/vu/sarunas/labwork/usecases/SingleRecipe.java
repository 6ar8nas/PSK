package mif.vu.sarunas.labwork.usecases;

import lombok.Getter;
import mif.vu.sarunas.labwork.entities.Recipe;
import mif.vu.sarunas.labwork.interceptors.LogPerformance;
import mif.vu.sarunas.labwork.persistence.RecipeDAO;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.Map;

@Model
@ViewScoped
@LogPerformance
public class SingleRecipe implements Serializable {
	@Inject
	private RecipeDAO recipeDAO;

	@Getter
	private Recipe recipe;

	@PostConstruct
	public void init() {
		Map<String, String> requestParameters =
				FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
		Long recipeId = Long.parseLong(requestParameters.get("recipeId"));
		this.recipe = recipeDAO.findById(recipeId);
	}
}
