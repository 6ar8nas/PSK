package mif.vu.sarunas.labwork.entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Positive;
import java.io.Serializable;
import java.util.UUID;

@NamedQueries({
		@NamedQuery(name = "RecipeIngredient.findAll", query = "select ri from RecipeIngredient as ri")
})
@Setter
@Getter
@EqualsAndHashCode
@Entity
@Table(name = "RECIPE_INGREDIENT")
public class RecipeIngredient implements Serializable {
	@GeneratedValue
	@Id
	private UUID id;

	@Basic(optional = false)
	private UnitsOfMeasure units;

	@Positive
	@Basic(optional = false)
	private Integer quantity;

	@ManyToOne
	@JoinColumn(name = "RECIPE_ID")
	private Recipe recipe;

	@ManyToOne
	@JoinColumn(name = "INGREDIENT_ID")
	private Ingredient ingredient;

	public RecipeIngredient() {
	}
}
