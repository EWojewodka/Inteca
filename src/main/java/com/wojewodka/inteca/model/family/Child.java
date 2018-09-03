package com.wojewodka.inteca.model.family;

import java.util.Date;

import com.wojewodka.inteca.services.dbo.DatabaseObjectImpl;
import com.wojewodka.inteca.services.dbo.annotations.DBColumn;
import com.wojewodka.inteca.services.dbo.annotations.DBTable;
import com.wojewodka.inteca.services.dbo.annotations.Id;

@DBTable(name = "inteca_children")
public class Child extends DatabaseObjectImpl{

	@Id(name = "child_id")
	private int id;

	@DBColumn(name = "first_name")
	private String firstname;

	@DBColumn(name = "second_name")
	private String secondName;

	@DBColumn(name = "pesel")
	private String pesel;

	@DBColumn(name = "born")
	private Date born;

	@DBColumn(name = "sex")
	private String sex;

	@DBColumn(name = "family_id")
	private int familyId;

	public Date getBorn() {
		return born;
	}

	public void setBorn(Date born) {
		this.born = born;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getPesel() {
		return pesel;
	}

	public void setPesel(String pesel) {
		this.pesel = pesel;
	}

	public String getSecondName() {
		return secondName;
	}

	public void setSecondName(String secondName) {
		this.secondName = secondName;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public int getFamilyId() {
		return familyId;
	}

	public void setFamilyId(int familyId) {
		this.familyId = familyId;
	}

}
