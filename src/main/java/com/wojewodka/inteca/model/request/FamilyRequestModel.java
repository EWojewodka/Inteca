package com.wojewodka.inteca.model.request;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public class FamilyRequestModel {

	@Valid
	private List<ChildRequestModel> children;

	@NotNull
	@Valid
	private FatherRequestModel father;

	public List<ChildRequestModel> getChildren() {
		return children;
	}

	public void setChildren(List<ChildRequestModel> children) {
		this.children = children;
	}

	public FatherRequestModel getFather() {
		return father;
	}

	public void setFather(FatherRequestModel father) {
		this.father = father;
	}

}
