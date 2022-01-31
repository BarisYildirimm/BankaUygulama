import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;

public class LoginForm extends JFrame {

	private JPanel contentPane;
	private JTextField txtTcNo;
	private JTextField txtSifre;
	private Baglanti baglanti = new Baglanti();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginForm frame = new LoginForm();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public LoginForm() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 345, 234);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblTcNo = new JLabel("TC No");
		lblTcNo.setBounds(32, 14, 46, 14);
		contentPane.add(lblTcNo);
		
		txtTcNo = new JTextField();
		txtTcNo.setBounds(96, 11, 192, 20);
		contentPane.add(txtTcNo);
		txtTcNo.setColumns(10);
		
		JLabel lblSifre = new JLabel("Sifre");
		lblSifre.setBounds(32, 51, 46, 14);
		contentPane.add(lblSifre);
		
		txtSifre = new JTextField();
		txtSifre.setBounds(96, 42, 192, 20);
		contentPane.add(txtSifre);
		txtSifre.setColumns(10);
		
		JRadioButton rdbtnMudur = new JRadioButton("Mudur");
		rdbtnMudur.setBounds(32, 101, 73, 23);
		contentPane.add(rdbtnMudur);
		
		JRadioButton rdbtnPersonel = new JRadioButton("Personel");
		rdbtnPersonel.setBounds(118, 101, 73, 23);
		contentPane.add(rdbtnPersonel);
		
		JRadioButton rdbtnMusteri = new JRadioButton("Musteri");
		rdbtnMusteri.setBounds(215, 101, 73, 23);
		contentPane.add(rdbtnMusteri);
		
		JButton btnGiris = new JButton("Giri\u015F Yap");
		btnGiris.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Connection conn = baglanti.baglan();
					Statement st = conn.createStatement();
					ResultSet rs = st.executeQuery("Select * from Mudur");
					while(rs.next()) {
						if(txtTcNo.getText().equals(rs.getString("mudurTc")) &&  txtSifre.getText().equals(rs.getString("sifre"))) {
							System.out.println("Giriş Başarılı!");
							Mudur mudur = new Mudur();
							mudur.setAd(rs.getString("mudurAd"));
							mudur.setSoyad(rs.getString("mudurSoyad"));
							MudurForm mfrm = new MudurForm(mudur);
							mfrm.setVisible(true);
							dispose();
							break;
						}
						else {
							System.out.println("Tc No Veya Şifre Yanlış Lütfen Tekrar Deneyiniz!");
						}
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnGiris.setBounds(32, 142, 256, 35);
		contentPane.add(btnGiris);
	}
}
