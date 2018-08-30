package com.wojewodka.inteca.services.database;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Scope("prototype")
@Service
@Primary
public class DBAWrapperDefault implements DBAWrapper {

	@Autowired
	private DataSource dataSource;

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public <T> T run(DBAction action) {
		Connection con = null;
		try {
			con = dataSource.getConnection();
			con.setAutoCommit(false);
			T result = (T) action.run(con);
			con.commit();
			if (!con.isClosed())
				con.close();

			return result;

		} catch (Exception e) {
			try {
				con.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			try {
				if (con != null && !con.isClosed()) {
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}

		return null;
	}

}
