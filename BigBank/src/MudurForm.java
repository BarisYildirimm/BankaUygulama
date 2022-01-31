import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.Font;
import java.sql.SQLException;

import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MudurForm extends JFrame {

	private JPanel contentPane;
	static Mudur mudur = new Mudur();
	private JTextField txtTcNo;
	private JTextField txtAd;
	private JTextField txtSoyad;
	private JTextField txtSifre;
	private JTextField txtTcNoSil;
	private JTable table_personel;
	JComboBox<String> cmbxSube = new JComboBox();
	private DefaultTableModel personelModel = null;
	private Object[] personelData = null;
	Personel personel = new Personel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MudurForm frame = new MudurForm(mudur);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws SQLException 
	 */
	public void SubeList() throws SQLException {
		for(int i = 0; i<personel.getSubeList().size(); i++) {
		cmbxSube.addItem(personel.getSubeList().get(i).getSubeAd());
		}
	}
	public void UpdatePersonelModel() throws SQLException {
		DefaultTableModel clearModel = (DefaultTableModel) table_personel.getModel();
		clearModel.setRowCount(0);
		for(int i = 0; i< personel.getPersonelList().size(); i++) {
			personelData[0] = personel.getPersonelList().get(i).getTcno();
			personelData[1] = personel.getPersonelList().get(i).getAd();
			personelData[2] = personel.getPersonelList().get(i).getSoyad();
			personelData[3] = personel.getPersonelList().get(i).getSube();
			personelData[4] = personel.getPersonelList().get(i).getSifre();
			personelModel.addRow(personelData);
		}
	}
	public MudurForm(Mudur mudur) throws SQLException {
		SubeList();
		personelModel = new DefaultTableModel();
		Object[] colPersonelName = new Object[5];
		colPersonelName[0] = "TC NO";
		colPersonelName[1] = "Ad";
		colPersonelName[2] = "Soyad";
		colPersonelName[4] = "Sube";
		colPersonelName[3] = "Sifre";
		
		personelModel.setColumnIdentifiers(colPersonelName);
		personelData = new Object[5];
		for(int i = 0; i< personel.getPersonelList().size(); i++) {
			personelData[0] = personel.getPersonelList().get(i).getTcno();
			personelData[1] = personel.getPersonelList().get(i).getAd();
			personelData[2] = personel.getPersonelList().get(i).getSoyad();
			personelData[3] = personel.getPersonelList().get(i).getSube();
			personelData[4] = personel.getPersonelList().get(i).getSifre();
			personelModel.addRow(personelData);
		}
		
		setTitle("Mudur Ekran\u0131");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 471);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Ho\u015F Geldiniz Say\u0131n,"+mudur.getAd()+" "+mudur.getSoyad());
		lblNewLabel.setFont(new Font("Yu Gothic UI", Font.PLAIN, 17));
		lblNewLabel.setBounds(10, 11, 365, 32);
		contentPane.add(lblNewLabel);
		
		JButton btnCikis = new JButton("\u00C7\u0131k\u0131\u015F");
		btnCikis.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(EXIT_ON_CLOSE);
			}
		});
		btnCikis.setFont(new Font("Yu Gothic UI", Font.PLAIN, 17));
		btnCikis.setBounds(719, 11, 155, 32);
		contentPane.add(btnCikis);
		
		JLabel lblNewLabel_1 = new JLabel("Personel Tc Kimlik No");
		lblNewLabel_1.setFont(new Font("Yu Gothic UI", Font.PLAIN, 15));
		lblNewLabel_1.setBounds(10, 83, 155, 25);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Personel Ad");
		lblNewLabel_2.setFont(new Font("Yu Gothic UI", Font.PLAIN, 15));
		lblNewLabel_2.setBounds(10, 126, 155, 25);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Personel Soyad");
		lblNewLabel_3.setFont(new Font("Yu Gothic UI", Font.PLAIN, 15));
		lblNewLabel_3.setBounds(10, 162, 155, 25);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Personel \u015Eifre");
		lblNewLabel_4.setFont(new Font("Yu Gothic UI", Font.PLAIN, 15));
		lblNewLabel_4.setBounds(10, 198, 155, 25);
		contentPane.add(lblNewLabel_4);
		
		JLabel lblIdOgrenme = new JLabel("ID");
		cmbxSube.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String sube =  (String)cmbxSube.getSelectedItem();
				int subeId;
				try {
					subeId = personel.SubeIdGetir(sube);
					lblIdOgrenme.setText(Integer.toString(subeId));
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		cmbxSube.setBounds(175, 236, 170, 25);
		contentPane.add(cmbxSube);
		
		JLabel lblNewLabel_5 = new JLabel("Personel \u015Eube");
		lblNewLabel_5.setFont(new Font("Yu Gothic UI", Font.PLAIN, 15));
		lblNewLabel_5.setBounds(10, 233, 155, 25);
		contentPane.add(lblNewLabel_5);
		
		txtTcNo = new JTextField();
		txtTcNo.setBounds(175, 88, 170, 25);
		contentPane.add(txtTcNo);
		txtTcNo.setColumns(10);
		
		txtAd = new JTextField();
		txtAd.setColumns(10);
		txtAd.setBounds(175, 129, 170, 25);
		contentPane.add(txtAd);
		
		txtSoyad = new JTextField();
		txtSoyad.setColumns(10);
		txtSoyad.setBounds(175, 165, 170, 25);
		contentPane.add(txtSoyad);
		
		txtSifre = new JTextField();
		txtSifre.setColumns(10);
		txtSifre.setBounds(175, 198, 170, 25);
		contentPane.add(txtSifre);
		
		JButton btnEkle = new JButton("Ekle");
		btnEkle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(txtTcNo.getText().length()==0 || txtAd.getText().length()==0 || txtSoyad.getText().length()==0 || txtSifre.getText().length()==0 || cmbxSube.getSelectedItem()=="") {
					JOptionPane.showMessageDialog(null, "Lütfen Bütün Alanları Doldurunuz!");
				}
				else {
					boolean control = personel.addPersonel(txtTcNo.getText(), txtAd.getText(), txtSoyad.getText(), Integer.parseInt(lblIdOgrenme.getText()),  txtSifre.getText());
					if(control) {
						try {
							JOptionPane.showMessageDialog(null, "Kayıt Başarılı!");
							txtTcNo.setText(null);
							txtAd.setText(null);
							txtSoyad.setText(null);
							lblIdOgrenme.setText("ID");
							txtSifre.setText(null);
							UpdatePersonelModel();
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
					
				}
			}
		});
		btnEkle.setFont(new Font("Yu Gothic UI", Font.PLAIN, 15));
		btnEkle.setBounds(10, 266, 170, 40);
		contentPane.add(btnEkle);
		
		JLabel lblNewLabel_1_1 = new JLabel("Personel Tc Kimlik No");
		lblNewLabel_1_1.setFont(new Font("Yu Gothic UI", Font.PLAIN, 15));
		lblNewLabel_1_1.setBounds(10, 319, 155, 25);
		contentPane.add(lblNewLabel_1_1);
		
		txtTcNoSil = new JTextField();
		txtTcNoSil.setColumns(10);
		txtTcNoSil.setBounds(10, 342, 335, 25);
		contentPane.add(txtTcNoSil);
		
		JButton btnSil = new JButton("Sil");
		btnSil.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(txtTcNoSil.getText().length()==0) {
					JOptionPane.showMessageDialog(null, "Personel Seçiniz!");
				}else {
					int res = JOptionPane.showConfirmDialog(null, "Silinme İşlemi Yapılsın mı?","Dikkat!",JOptionPane.YES_NO_OPTION);
					if(res == 0) {
						boolean control = personel.deletePersonel(txtTcNoSil.getText());
						if(control) {	
							try {
								JOptionPane.showMessageDialog(null, "Silinme Başarılı!");
								UpdatePersonelModel();
							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							}
						else {
							JOptionPane.showMessageDialog(null, "Silinme işlemin Hata!");
						}
					}else {
						JOptionPane.showMessageDialog(null, "Silme işlemi iptal Edildi!");
					}
				
				}
			}
		});
		btnSil.setFont(new Font("Yu Gothic UI", Font.PLAIN, 15));
		btnSil.setBounds(10, 375, 170, 40);
		contentPane.add(btnSil);
		
		JScrollPane wscrollTable = new JScrollPane();
		wscrollTable.setBounds(361, 83, 513, 330);
		contentPane.add(wscrollTable);
		table_personel = new JTable(personelModel);
		table_personel.setBackground(Color.WHITE);
		wscrollTable.setViewportView(table_personel);
		table_personel.getSelectionModel().addListSelectionListener(new ListSelectionListener() {			
			public void valueChanged(ListSelectionEvent e) {
				try {
					txtTcNoSil.setText(table_personel.getValueAt(table_personel.getSelectedRow(), 0).toString());
				}catch(Exception ex) {
					
				}
			
			}
			
		});
		table_personel.getModel().addTableModelListener(new TableModelListener() {
			@Override
			public void tableChanged(TableModelEvent e) {
				if(e.getType() == TableModelEvent.UPDATE) {
					String selectTc = table_personel.getValueAt(table_personel.getSelectedRow(), 0).toString();
					String selectName = table_personel.getValueAt(table_personel.getSelectedRow(), 1).toString();
					String selectSoyad = table_personel.getValueAt(table_personel.getSelectedRow(), 2).toString();
					int selectSube = Integer.parseInt(table_personel.getValueAt(table_personel.getSelectedRow(), 4).toString());
					String selectSifre = table_personel.getValueAt(table_personel.getSelectedRow(), 3).toString();
					boolean control = personel.updatePersonel(selectTc, selectName, selectSoyad, selectSube, selectSifre);
				}			
			}
		});
		lblIdOgrenme.setBounds(144, 241, 35, 14);
		contentPane.add(lblIdOgrenme);
	}
}
