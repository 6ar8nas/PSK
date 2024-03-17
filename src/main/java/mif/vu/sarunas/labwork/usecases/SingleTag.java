package mif.vu.sarunas.labwork.usecases;

import lombok.Getter;
import mif.vu.sarunas.labwork.entities.Tag;
import mif.vu.sarunas.labwork.interceptors.LogPerformance;
import mif.vu.sarunas.labwork.persistence.TagDAO;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.Map;

@Model
@ViewScoped
@LogPerformance
public class SingleTag implements Serializable {
	@Inject
	private TagDAO tagDAO;

	@Getter
	private Tag tag;

	@PostConstruct
	public void init() {
		Map<String, String> requestParameters =
				FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
		Long tagId = Long.parseLong(requestParameters.get("tagId"));
		this.tag = tagDAO.findById(tagId);
	}
}
