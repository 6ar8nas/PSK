package mif.vu.sarunas.labwork.entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.math.BigDecimal;

@Setter
@Getter
@EqualsAndHashCode
@Entity
public class Service {
	@GeneratedValue
	@Id
	private Long id;

	@Basic
	private String name;

	@Basic
	private BigDecimal price;
}
