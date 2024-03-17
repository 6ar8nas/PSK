package mif.vu.sarunas.labwork.persistence;

import java.util.List;

public interface GenericDAO<T> {
	T findById(Long id);

	List<T> findAll();

	void save(T entity);

	T update(T entity);

	void delete(T entity);
}
