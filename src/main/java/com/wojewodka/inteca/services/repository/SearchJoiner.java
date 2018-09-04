package com.wojewodka.inteca.services.repository;

import com.wojewodka.inteca.services.dbo.AnnotationManager;
import com.wojewodka.inteca.services.dbo.DatabaseObject;

public class SearchJoiner {

	public enum JoinType {
		JOIN("JOIN"),
		/**/
		LEFT_JOIN("LEFT JOIN"),
		/**/
		RIGHT_JOIN("RIGHT JOIN");

		private String sqlName;

		private JoinType(String sqlName) {
			this.sqlName = sqlName;
		}

		public String getSqlName() {
			return sqlName;
		}

	}

	private String leftTable;

	private String rightTable;

	private String alias;

	private String condition;

	private JoinType type;

	public SearchJoiner(Class<? extends DatabaseObject> leftTable, Class<? extends DatabaseObject> rightTable,
			String alias, String condition, JoinType type) {
		AnnotationManager instace = AnnotationManager.getInstance();
		this.leftTable = instace.getInfo(leftTable).getTableName();
		this.rightTable = instace.getInfo(rightTable).getTableName();
		this.alias = alias;
		this.condition = condition;
		this.type = type;
	}

	public String getLeftTable() {
		return leftTable;
	}

	public String getRightTable() {
		return rightTable;
	}

	public String getAlias() {
		return alias;
	}

	public String getCondition() {
		return condition;
	}

	public JoinType getType() {
		return type;
	}

}
