package mif.vu.sarunas.labwork.usecases;

import lombok.Getter;
import lombok.Setter;
import mif.vu.sarunas.labwork.entities.Tag;
import mif.vu.sarunas.labwork.interceptors.LogPerformance;
import mif.vu.sarunas.labwork.persistence.TagDAO;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.List;

@Model
@LogPerformance
public class Tags implements Serializable {
	@Inject
	private TagDAO tagDAO;

	@Getter
	@Setter
	private Tag tagToCreate = new Tag();

	@Getter
	private List<Tag> allTags;

	@PostConstruct
	public void init() {
		loadAllTags();
	}

	@Transactional
	public void createTag() {
		this.tagDAO.save(tagToCreate);
	}

	private void loadAllTags() {
		this.allTags = tagDAO.findAll();
	}
}
