package aplication;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import db.DB;
import db.DbException;

public class Programa {

	public static void main(String[] args) {

		Connection conn = null;
		Statement st = null;

		try {
			conn = DB.getConnection();
			
			conn.setAutoCommit(false); //com false as operações não são confirmadas automaticamente e dependem da confirmação do programador

			st = conn.createStatement();
			
			

			int rows1 = st.executeUpdate("UPDATE seller SET baseSalary = 2090 WHERE DepartmentId = 1");
			
			//Criando um erro falso para a linha 2 (rows2) não ser atualizada
			//int x = 1;
			//if (x<2) {
				//throw new SQLException("Erro falso");
			//}
			
			int rows2 = st.executeUpdate("UPDATE seller SET baseSalary = 3090 WHERE DepartmentId = 2");
			
			conn.commit(); //Para confirmar que verdadeiramente as operações terminaram
			
			
			System.out.println("rows1: " + rows1);
			System.out.println("rows2: " + rows2);

		} catch (SQLException e) {
			try {
				conn.rollback(); //Para voltar ao estado inicial
				throw new DbException("Transação não efetuada! Causa: " + e.getMessage());
			} catch (SQLException e1) {
				throw new DbException("Erro no rollBack! Causa: " + e1.getMessage());
			} 
			
		} finally {
			DB.closeStatement(st);
			DB.closeConnection();
		}

	}

}
