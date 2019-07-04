package aplication;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import db.DB;

public class Programa {

	public static void main(String[] args) {

		Connection conn = null;
		Statement st = null;

		try {
			conn = DB.getConnection();

			st = conn.createStatement();

			int rows1 = st.executeUpdate("UPDATE seller SET baseSalary = 2090 WHERE DepartmentId = 1");
			
			//Criando um erro faldo para a linha 2 (rows2) não ser atualizada
			int x = 1;
			if (x<2) {
				throw new SQLException("Erro falso");
			}
			
			int rows2 = st.executeUpdate("UPDATE seller SET baseSalary = 3090 WHERE DepartmentId = 2");
			
			System.out.println("rows1: " + rows1);
			System.out.println("rows2: " + rows2);

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DB.closeStatement(st);
			DB.closeConnection();
		}

	}

}
