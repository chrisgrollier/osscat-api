package net.chrisgrollier.amsuite.osscat.model;

public class UpdatableVersionView extends AbstractVersionView {
	
	public void setComments(String comments) {
		this.comments = comments;
	}
	
	public void setCriticity(Integer criticity) {
		this.criticity = criticity;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public void setLastVersion(String lastVersion) {
		this.lastVersion = lastVersion; 
	}
	
	public void setVersion(String version) {
		this.version = version; 
	}
	
}