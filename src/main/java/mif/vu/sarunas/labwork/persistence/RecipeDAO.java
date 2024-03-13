package mif.vu.sarunas.labwork.persistence;

import mif.vu.sarunas.labwork.entities.Recipe;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;
import java.util.UUID;

@ApplicationScoped
public class RecipeDAO implements GenericDAO<Recipe> {

	@Inject
	private EntityManager em;

	public List<Recipe> findAll() {
		return em.createNamedQuery("Recipe.findAll", Recipe.class).getResultList();
	}

	public Recipe findById(UUID id) {
		return em.find(Recipe.class, id);
	}

	public void save(Recipe recipe) {
		em.persist(recipe);
	}

	public Recipe update(Recipe recipe) {
		return em.merge(recipe);
	}

	public void delete(Recipe recipe) {
		if (em.contains(recipe))
			em.remove(recipe);
	}
}
