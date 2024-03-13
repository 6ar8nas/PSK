package mif.vu.sarunas.labwork.persistence;

import mif.vu.sarunas.labwork.entities.Tag;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;
import java.util.UUID;

@ApplicationScoped
public class TagDAO implements GenericDAO<Tag> {

	@Inject
	private EntityManager em;

	public List<Tag> findAll() {
		return em.createNamedQuery("Tag.findAll", Tag.class).getResultList();
	}

	public Tag findById(UUID id) {
		return em.find(Tag.class, id);
	}

	public void save(Tag tag) {
		em.persist(tag);
	}

	public Tag update(Tag tag) {
		return em.merge(tag);
	}

	public void delete(Tag tag) {
		if (em.contains(tag))
			em.remove(tag);
	}
}
