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
		@NamedQuery(name = "Tag.findAll", query = "select t from Tag as t")
})
@Setter
@Getter
@EqualsAndHashCode
@Entity
@Table(uniqueConstraints = {
		@UniqueConstraint(columnNames = {"name"})
})
public class Tag implements Serializable {
	@GeneratedValue
	@Id
	private Long id;

	@Size(max = 15)
	@Size(min = 1)
	@Basic(optional = false)
	private String name;

	@OneToMany(mappedBy = "tag", fetch = FetchType.LAZY)
	private List<Recipe> recipes = new ArrayList<>();

	public Tag() {
	}
}
