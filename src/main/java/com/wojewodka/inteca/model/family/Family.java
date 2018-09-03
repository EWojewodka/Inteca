package com.wojewodka.inteca.model.family;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonView;
import com.wojewodka.inteca.api.ViewScope;
import com.wojewodka.inteca.services.dbo.DatabaseObjectImpl;
import com.wojewodka.inteca.services.dbo.annotations.DBColumn;
import com.wojewodka.inteca.services.dbo.annotations.DBTable;
import com.wojewodka.inteca.services.dbo.annotations.Id;

@DBTable(name = "inteca_families")
public class Family extends DatabaseObjectImpl {

	@Id(name = "family_id")
	private int id;

	@DBColumn(name = "father_id")
	private int fatherId;

	@JsonView(ViewScope.Basic.class)
	private Father father;

	@JsonView(ViewScope.Basic.class)
	private List<Child> children = new ArrayList<>();

	public Family(Father father) {
		this(father.getId());
		this.father = father;
	}

	public Family(int fatherId) {
		this.fatherId = fatherId;
	}

	public int getFatherId() {
		return fatherId;
	}

	public void setFatherId(int fatherId) {
		this.fatherId = fatherId;
	}

	@Override
	public int getId() {
		return id;
	}

	public Father getFather() {
		return father;
	}

	public void setFather(Father father) {
		this.father = father;
	}

	public List<Child> getChildren() {
		return children;
	}

	public void setChildren(List<Child> children) {
		this.children = children;
	}

}
