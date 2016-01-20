package Sort;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TransactionP {
	public static void main(String[] args){
		Connection conn;
		Statement stmt;
		ResultSet rs;
		String sql = null;
		
		String jdbcUrl="jdbc:mysql://localhost:3307/test";
		String userID = "root";
		String userPW = "db1004";
		int inventory = 0;
		try {
			
			conn = DriverManager.getConnection(jdbcUrl, userID, userPW); conn.setAutoCommit(false);
			PreparedStatement pstmt = conn.prepareStatement(sql);
			sql = "start transaction";
			pstmt.execute();
			
			sql = "UPDATE book_inventory set inventory = inventory -1";
			pstmt.execute();
			
			sql = "select * from book_inventory";
			stmt = conn.createStatement();
			
			rs = stmt.executeQuery(sql);
			while(rs.next()){
				int num = rs.getInt(1);
				String title = rs.getString("title");
				String author = rs.getString("author");
				inventory = rs.getInt("inventory");
				System.out.println(num+author+inventory+"num,title,inventory");
			}
			
			if(inventory < 0) {
				conn.rollback();
				System.out.println("재고가 없어서 수행할수 없습니다.");
			}
			conn.commit();
			
		}catch(SQLException e) {
			System.out.println("Driver Error"+ e.getMessage());
			return;
		}
	}

}
