package mif.vu.sarunas.labwork.persistence;

import java.util.List;
import java.util.UUID;

public interface GenericDAO<T> {
	T findById(UUID id);

	List<T> findAll();

	void save(T entity);

	T update(T entity);

	void delete(T entity);
}
