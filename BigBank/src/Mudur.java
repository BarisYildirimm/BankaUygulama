public class Mudur {
	private String ad,soyad,tcno,sifre;
	private int sube;
	public String getAd() {
		return ad;
	}
	public void setAd(String ad) {
		this.ad = ad;
	}
	public Mudur() {}
	
	public Mudur(String ad, String soyad, String tcno, String sifre, int sube) {
		super();
		this.ad = ad;
		this.soyad = soyad;
		this.tcno = tcno;
		this.sifre = sifre;
		this.sube = sube;
	}
	public String getSoyad() {
		return soyad;
	}
	public void setSoyad(String soyad) {
		this.soyad = soyad;
	}
	public String getTcno() {
		return tcno;
	}
	public void setTcno(String tcno) {
		this.tcno = tcno;
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
	public void setSube(int sube) {
		this.sube = sube;
	}
	
}
