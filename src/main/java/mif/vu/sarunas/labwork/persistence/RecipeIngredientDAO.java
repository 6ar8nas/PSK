package mif.vu.sarunas.labwork.persistence;

import mif.vu.sarunas.labwork.entities.RecipeIngredient;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;
import java.util.UUID;

@ApplicationScoped
public class RecipeIngredientDAO implements GenericDAO<RecipeIngredient> {

	@Inject
	private EntityManager em;

	public List<RecipeIngredient> findAll() {
		return em.createNamedQuery("RecipeIngredient.findAll", RecipeIngredient.class).getResultList();
	}

	public RecipeIngredient findById(UUID id) {
		return em.find(RecipeIngredient.class, id);
	}

	public void save(RecipeIngredient recipeIngredient) {
		em.persist(recipeIngredient);
	}

	public RecipeIngredient update(RecipeIngredient recipeIngredient) {
		return em.merge(recipeIngredient);
	}

	public void delete(RecipeIngredient recipeIngredient) {
		if (em.contains(recipeIngredient))
			em.remove(recipeIngredient);
	}
}
