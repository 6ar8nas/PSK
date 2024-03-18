package mif.vu.sarunas.labwork.entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@NamedQueries({
		@NamedQuery(name = "Ingredient.findAll", query = "select i from Ingredient as i")
})
@Setter
@Getter
@EqualsAndHashCode
@Entity
@Table(uniqueConstraints = {
		@UniqueConstraint(columnNames = {"name"})
})
public class Ingredient implements Serializable {
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private Long id;

	@Size(min = 1)
	@Basic(optional = false)
	private String name;

	@Basic
	private IngredientCategory category;

	@ManyToMany(mappedBy = "ingredients", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Recipe> recipes = new ArrayList<>();

	public Ingredient() {
	}
}
