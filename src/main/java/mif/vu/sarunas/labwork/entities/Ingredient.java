package mif.vu.sarunas.labwork.entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@NamedQueries({
		@NamedQuery(name = "Ingredient.findAll", query = "select i from Ingredient as i")
})
@Setter
@Getter
@EqualsAndHashCode
@Entity
public class Ingredient implements Serializable {
	@GeneratedValue
	@Id
	private UUID id;

	@Basic(optional = false)
	private String name;

	@Basic
	private IngredientCategory category;

	@OneToMany(mappedBy = "ingredient", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
	private List<RecipeIngredient> recipeIngredients = new ArrayList<>();

	public Ingredient() {
	}
}
