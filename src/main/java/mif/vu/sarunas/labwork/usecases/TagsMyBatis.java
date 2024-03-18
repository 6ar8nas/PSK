package mif.vu.sarunas.labwork.usecases;

import lombok.Getter;
import lombok.Setter;
import mif.vu.sarunas.labwork.interceptors.LogPerformance;
import mif.vu.sarunas.labwork.mybatis.dao.TagMapper;
import mif.vu.sarunas.labwork.mybatis.model.Tag;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.List;

@Model
@LogPerformance
public class TagsMyBatis implements Serializable {
	@Inject
	private TagMapper tagMapper;

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
		this.tagMapper.insert(tagToCreate);
	}

	private void loadAllTags() {
		this.allTags = tagMapper.selectAll();
	}
}
