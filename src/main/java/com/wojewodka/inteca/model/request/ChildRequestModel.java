package com.wojewodka.inteca.model.request;

import com.wojewodka.inteca.model.family.Person;

public  class ChildRequestModel extends Person {
	private String sex;

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

}