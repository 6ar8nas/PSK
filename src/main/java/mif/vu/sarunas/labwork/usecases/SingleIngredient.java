package mif.vu.sarunas.labwork.usecases;

import lombok.Getter;
import mif.vu.sarunas.labwork.entities.Ingredient;
import mif.vu.sarunas.labwork.interceptors.LogPerformance;
import mif.vu.sarunas.labwork.persistence.IngredientDAO;

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
public class SingleIngredient implements Serializable {
	@Inject
	private IngredientDAO ingredientDAO;

	@Getter
	private Ingredient ingredient;

	@PostConstruct
	public void init() {
		Map<String, String> requestParameters =
				FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
		Long ingredientId = Long.parseLong(requestParameters.get("ingredientId"));
		this.ingredient = ingredientDAO.findById(ingredientId);
	}
}
