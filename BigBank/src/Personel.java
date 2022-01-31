import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Personel {
	Baglanti baglanti = new Baglanti();
	Statement st = null;
	ResultSet rs = null;
	PreparedStatement preparedStatement = null;
	
	private String tcno,ad,soyad,sifre;
	private int sube;
	public String getTcno() {
		return tcno;
	}
	public void setTcno(String tcno) {
		this.tcno = tcno;
	}
	public String getAd() {
		return ad;
	}
	
	public void setAd(String ad) {
		this.ad = ad;
	}
	public String getSoyad() {
		return soyad;
	}
	public void setSoyad(String soyad) {
		this.soyad = soyad;
	}
	public String getSifre() {
		return sifre;
	}
	public void setSifre(String sifre) {
		this.sifre = sifre;
	}
	public int getSube() {
		return sube;
	}
	public void setSube(int bolum) {
		this.sube = bolum;
	}
	public Personel(String tcno, String ad, String soyad, String sifre, int sube) {
		super();
		this.tcno = tcno;
		this.ad = ad;
		this.soyad = soyad;
		this.sifre = sifre;
		this.sube = sube;
	}
	public Personel() {
		
	}
	public ArrayList<Personel> getPersonelList() throws SQLException{
		ArrayList<Personel> list = new ArrayList<>();
		Connection conn = baglanti.baglan();
		Personel obj;
		try {
			st = conn.createStatement();
			rs = st.executeQuery("Select * From Personel");
			while(rs.next()) {
				obj = new Personel(
						rs.getString("personelTc"),
						rs.getString("personelAd"),
						rs.getString("personelSoyad"),
						rs.getString("subeIdFk"),
						rs.getInt("sifre")
						);
				list.add(obj);
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		finally {
			st.close();
			rs.close();
			conn.close();
		}
		return list;
	}
	public ArrayList<Sube> getSubeList() throws SQLException{
		ArrayList<Sube> list = new ArrayList<>();
		Connection conn = baglanti.baglan();
		Sube obj;
		try {
			st = conn.createStatement();
			rs = st.executeQuery("Select * From Sube");
			while(rs.next()) {
				obj = new Sube(
						rs.getInt("subeId"),
						rs.getString("subeAd")
						);
				list.add(obj);
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		finally {
			st.close();
			rs.close();
			conn.close();
		}
		return list;
	}
	public int SubeIdGetir(String sube2) throws SQLException {
		Connection conn = baglanti.baglan();
		int id = 12;
		try {
			st = conn.createStatement();
			rs = st.executeQuery("select subeId from Sube where subeAd='" + sube2 + "'");
			while(rs.next()) {
				id = rs.getInt("subeId");
				break;
			}
		} catch (SQLException e) {
			System.out.println("Sube Listeleme Hatası");
		}finally {
			st.close();
			rs.close();
			conn.close();
		}
		return id;
	}
	public boolean addPersonel(String tcno,String ad,String soyad,int sube,String sifre) {
		String query = "insert into Personel" + "(personelTc,personelAd,personelSoyad,subeIdFk,sifre) values" +"(?,?,?,?,?)";
		Connection conn = baglanti.baglan();
		boolean key = false;
		try {
			st = conn.createStatement();
			preparedStatement = conn.prepareStatement(query);
			preparedStatement.setString(1, tcno);
			preparedStatement.setString(2, ad);
			preparedStatement.setString(3, soyad);
			preparedStatement.setInt(4, sube);
			preparedStatement.setString(5,sifre);
			preparedStatement.executeUpdate();
			key = true;	
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if(key) 
			return true;
		else 
			return false;
	}
	public boolean deletePersonel(String tcno) {
		String query = "delete from Personel where personelTc = ?";
		Connection conn = baglanti.baglan();
		boolean key = false;
		try {
			st = conn.createStatement();
			preparedStatement = conn.prepareStatement(query);
			preparedStatement.setString(1, tcno);
			preparedStatement.executeUpdate();
			key = true;	
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if(key) 
			return true;
		else 
			return false;
	}
	public boolean updatePersonel(String tcno,String ad,String soyad,int sube,String sifre) {
		String query = "Update Personel Set personelAd=?,personelSoyad=?,subeIdFk=?,sifre=? Where personelTc=?";
		Connection conn = baglanti.baglan();
		boolean key = false;
		try {
			st = conn.createStatement();
			preparedStatement = conn.prepareStatement(query);
			preparedStatement.setString(1, ad);
			preparedStatement.setString(2,soyad);
			preparedStatement.setInt(3, sube);
			preparedStatement.setString(4, sifre);
			preparedStatement.setString(5,tcno);
			preparedStatement.executeUpdate();
			key = true;	
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if(key) 
			return true;
		else 
			return false;
	}
}
