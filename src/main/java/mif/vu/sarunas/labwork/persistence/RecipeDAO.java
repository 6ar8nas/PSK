package mif.vu.sarunas.labwork.persistence;

import mif.vu.sarunas.labwork.entities.Recipe;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

@ApplicationScoped
public class RecipeDAO implements GenericDAO<Recipe> {

	@Inject
	private EntityManager em;

	public List<Recipe> findAll() {
		return em.createNamedQuery("Recipe.findAll", Recipe.class).getResultList();
	}

	public Recipe findById(Long id) {
		return em.find(Recipe.class, id);
	}

	public List<Recipe> findAllByFilters(List<Long> ingredientIds, Long tagId) {
		if (ingredientIds.isEmpty() && tagId == null) return findAll();

		TypedQuery<Recipe> query;
		if (!ingredientIds.isEmpty() && tagId != null) {
			query = em.createNamedQuery("Recipe.findAllByFilters", Recipe.class);
			query.setParameter("tagId", tagId);
			query.setParameter("ingredientIds", ingredientIds);
			query.setParameter("ingredientCount", (long) ingredientIds.size());
		} else if (!ingredientIds.isEmpty()) {
			query = em.createNamedQuery("Recipe.findAllByIngredients", Recipe.class);
			query.setParameter("ingredientIds", ingredientIds);
			query.setParameter("ingredientCount", (long) ingredientIds.size());
		} else {
			query = em.createNamedQuery("Recipe.findAllByTag", Recipe.class);
			query.setParameter("tagId", tagId);
		}

		return query.getResultList();
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
