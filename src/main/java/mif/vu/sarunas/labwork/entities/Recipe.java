package mif.vu.sarunas.labwork.entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@NamedQueries({
		@NamedQuery(name = "Recipe.findAll", query = "select r from Recipe as r")
})
@Setter
@Getter
@EqualsAndHashCode
@Entity
public class Recipe implements Serializable {
	@GeneratedValue
	@Id
	private UUID id;

	@Basic(optional = false)
	private String name;

	@Basic
	private String description;

	@Basic(optional = false)
	private String instructions;

	@Basic
	@Column(name = "PREPARATION_TIME")
	private LocalTime preparationTime;

	@Basic(optional = false)
	@Column(name = "CREATION_DATE")
	private LocalDateTime creationDate = LocalDateTime.now();

	@OneToMany(mappedBy = "recipe", orphanRemoval = true, cascade = CascadeType.ALL)
	private List<RecipeIngredient> ingredients = new ArrayList<>();

	@ManyToMany(mappedBy = "recipes", fetch = FetchType.LAZY)
	private List<Tag> tags = new ArrayList<>();

	public Recipe() {
	}
}
