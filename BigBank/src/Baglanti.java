import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Baglanti {
	public Connection baglan(){
		String url = "jdbc:sqlserver://LAPTOP-LHOO29TP;databaseName=WorldBank";
		Connection _conn = null;
		try {
			 _conn = DriverManager.getConnection(url,"baris","1234");
			System.out.println("Veri Tabanına Baglandi!");
		} catch (SQLException e) {
			System.out.println("Veri Tabanı Baglanti Hatası!");
			e.printStackTrace();
		}	
		return _conn;
   }
}
