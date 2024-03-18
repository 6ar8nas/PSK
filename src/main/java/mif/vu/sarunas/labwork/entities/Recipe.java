package mif.vu.sarunas.labwork.entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@NamedQueries({
		@NamedQuery(name = "Recipe.findAll", query = "select r from Recipe as r"),
		@NamedQuery(name = "Recipe.findAllByFilters", query = "SELECT r FROM Recipe r JOIN r.ingredients i WHERE (r.tag.id = :tagId and i.id IN :ingredientIds) GROUP BY r HAVING COUNT(DISTINCT i) = :ingredientCount"),
		@NamedQuery(name = "Recipe.findAllByIngredients", query = "SELECT r FROM Recipe r JOIN r.ingredients i WHERE i.id IN :ingredientIds GROUP BY r HAVING COUNT(DISTINCT i) = :ingredientCount"),
		@NamedQuery(name = "Recipe.findAllByTag", query = "select r from Recipe as r where r.tag.id = :tagId"),
})
@Setter
@Getter
@EqualsAndHashCode
@Entity
public class Recipe implements Serializable {
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private Long id;

	@Size(min = 1)
	@Basic(optional = false)
	private String name;

	@Basic
	private String description;

	@Basic(optional = false)
	private String instructions;

	@Basic(optional = false)
	@Column(name = "CREATION_DATE")
	private LocalDateTime creationDate = LocalDateTime.now();

	@ManyToMany
	private List<Ingredient> ingredients = new ArrayList<>();

	@ManyToOne(optional = false)
	private Tag tag;

	public Recipe() {
	}
}
