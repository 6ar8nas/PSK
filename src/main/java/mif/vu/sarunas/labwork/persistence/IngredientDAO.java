package mif.vu.sarunas.labwork.persistence;

import mif.vu.sarunas.labwork.entities.Ingredient;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

@ApplicationScoped
public class IngredientDAO implements GenericDAO<Ingredient> {

	@Inject
	private EntityManager em;

	public List<Ingredient> findAll() {
		return em.createNamedQuery("Ingredient.findAll", Ingredient.class).getResultList();
	}

	public Ingredient findById(Long id) {
		return em.find(Ingredient.class, id);
	}
	
	public void save(Ingredient ingredient) {
		em.persist(ingredient);
	}

	public Ingredient update(Ingredient ingredient) {
		return em.merge(ingredient);
	}

	public void delete(Ingredient ingredient) {
		if (em.contains(ingredient))
			em.remove(ingredient);
	}
}
