package mif.vu.sarunas.labwork.entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@NamedQueries({
		@NamedQuery(name = "Tag.findAll", query = "select t from Tag as t")
})
@Setter
@Getter
@EqualsAndHashCode
@Entity
public class Tag implements Serializable {
	@GeneratedValue
	@Id
	private UUID id;

	@Size(max = 15)
	@Basic(optional = false)
	private String name;

	@ManyToMany(fetch = FetchType.LAZY)
	private List<Recipe> recipes = new ArrayList<>();

	public Tag() {
	}
}
