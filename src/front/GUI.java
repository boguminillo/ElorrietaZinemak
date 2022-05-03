package front;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;

import back.objektuak.Egunak;
import back.objektuak.Erabiltzailea;
import back.objektuak.Funtzioak;
import net.miginfocom.swing.MigLayout;

public class GUI {

	private JFrame frame;
	private JTextField txtLoginErabiltzailea;
	private JPasswordField passwordFieldLogin;
	private JTextField txtErregistroJaiotzeData;
	private JTextField txtErregistroAbizenak;
	private JTextField txtErregistroIzena;
	private JPasswordField passwordFieldErregistroErrepikatu;
	private JPasswordField passwordFieldErregistro;
	private JTextField txtErregistroErabiltzailea;
	private JTable tableEdukiaFilmak;
	private JTable tableEdukiaDokumentalak;
	private JTable tableEdukiaFilmLaburrak;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI window = new GUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public GUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 640, 480);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel principal = new JPanel();
		frame.getContentPane().add(principal, BorderLayout.CENTER);
		principal.setLayout(new CardLayout(0, 0));

		JPanel ongiEtorria = new JPanel();
		ongiEtorria.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				CardLayout cl = (CardLayout) principal.getLayout();
				cl.show(principal, "Sarrera");
			}
		});
		principal.add(ongiEtorria, "OngiEtorria");

		JTabbedPane sarrera = new JTabbedPane(JTabbedPane.TOP);
		principal.add(sarrera, "Sarrera");

		JPanel login = new JPanel();
		sarrera.addTab("Logina", null, login, null);
		login.setLayout(new MigLayout("", "[grow][94px,right][114px,grow,left][grow]", "[grow][19px][][grow][]"));

		JLabel lblLoginErabiltzailea = new JLabel("Erabiltzailea:");
		login.add(lblLoginErabiltzailea, "cell 1 1,alignx right,aligny center");

		txtLoginErabiltzailea = new JTextField();
		lblLoginErabiltzailea.setLabelFor(txtLoginErabiltzailea);
		login.add(txtLoginErabiltzailea, "cell 2 1,growx,aligny center");
		txtLoginErabiltzailea.setColumns(10);

		JLabel lblLoginPasahitza = new JLabel("Pasahitza:");
		login.add(lblLoginPasahitza, "cell 1 2,alignx trailing");

		passwordFieldLogin = new JPasswordField();
		login.add(passwordFieldLogin, "cell 2 2,growx");

		JButton btnLoginHasiera = new JButton("← Hasiera");
		btnLoginHasiera.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				CardLayout cl = (CardLayout) principal.getLayout();
				cl.show(principal, "OngiEtorria");
			}
		});
		login.add(btnLoginHasiera, "cell 0 4,alignx left");

		JButton btnLoginJarraitu = new JButton("Jarraitu →");
		btnLoginJarraitu.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Erabiltzailea erabiltzailea = new Erabiltzailea();
				erabiltzailea.setLogin(txtLoginErabiltzailea.getText());
				erabiltzailea.setPasahitza(new String(passwordFieldLogin.getPassword()));
				if (erabiltzailea.login()) {
					CardLayout cl = (CardLayout) principal.getLayout();
					cl.show(principal, "Edukia");
				}
			}
		});
		login.add(btnLoginJarraitu, "cell 3 4,alignx right");

		JPanel register = new JPanel();
		sarrera.addTab("Erregistroa", null, register, null);
		register.setLayout(new MigLayout("", "[grow][right][left][grow,right]", "[grow][][][][][][][][grow][]"));

		JLabel lblErregistroErabiltzailea = new JLabel("Erabiltzailea:");
		register.add(lblErregistroErabiltzailea, "cell 1 1,alignx trailing");

		txtErregistroErabiltzailea = new JTextField();
		register.add(txtErregistroErabiltzailea, "cell 2 1,growx");
		txtErregistroErabiltzailea.setColumns(10);

		JLabel lblErregistroPasahitza = new JLabel("Pasahitza:");
		register.add(lblErregistroPasahitza, "cell 1 2,alignx trailing");

		passwordFieldErregistro = new JPasswordField();
		register.add(passwordFieldErregistro, "cell 2 2,growx");

		JLabel lblErregistroPasahitzaErrepikatu = new JLabel("Pasahitza errepikatu:");
		register.add(lblErregistroPasahitzaErrepikatu, "cell 1 3,alignx trailing");

		passwordFieldErregistroErrepikatu = new JPasswordField();
		register.add(passwordFieldErregistroErrepikatu, "cell 2 3,growx");

		JLabel lblErregistroIzena = new JLabel("Izena:");
		register.add(lblErregistroIzena, "cell 1 4,alignx trailing");

		txtErregistroIzena = new JTextField();
		register.add(txtErregistroIzena, "cell 2 4,growx");
		txtErregistroIzena.setColumns(10);

		JLabel lblErregistroAbizenak = new JLabel("Abizenak:");
		register.add(lblErregistroAbizenak, "cell 1 5,alignx trailing");

		txtErregistroAbizenak = new JTextField();
		register.add(txtErregistroAbizenak, "cell 2 5,growx");
		txtErregistroAbizenak.setColumns(10);

		JLabel lblErregistroJaiotzeData = new JLabel("Jaiotze data:");
		register.add(lblErregistroJaiotzeData, "cell 1 6,alignx trailing");

		txtErregistroJaiotzeData = new JTextField();
		register.add(txtErregistroJaiotzeData, "cell 2 6,growx");
		txtErregistroJaiotzeData.setColumns(10);

		JLabel lblErregistroFuntzioa = new JLabel("Funtzioa:");
		register.add(lblErregistroFuntzioa, "cell 1 7,alignx trailing");

		JComboBox comboBoxErregistroFuntzioak = new JComboBox();
		comboBoxErregistroFuntzioak.setModel(new DefaultComboBoxModel(Funtzioak.values()));
		register.add(comboBoxErregistroFuntzioak, "cell 2 7,growx");

		JButton btnErregistratu = new JButton("Erregistratu");
		register.add(btnErregistratu, "cell 3 9");

		JPanel egunAutaketa = new JPanel();
		principal.add(egunAutaketa, "EgunAutaketa");
		egunAutaketa.setLayout(new MigLayout("", "[grow][right][left][grow,right]", "[][][grow][][]"));

		JLabel lblEgunaAukeratu = new JLabel("Eguna aukeratu:");
		egunAutaketa.add(lblEgunaAukeratu, "cell 1 1,alignx trailing");

		JComboBox comboBoxEgunAutaketa = new JComboBox();
		comboBoxEgunAutaketa.setModel(new DefaultComboBoxModel(Egunak.values()));
		egunAutaketa.add(comboBoxEgunAutaketa, "cell 2 1,growx");

		JTextArea txtInfoEgunAutaketa = new JTextArea();
		txtInfoEgunAutaketa.setBackground(UIManager.getColor("menu"));
		egunAutaketa.add(txtInfoEgunAutaketa, "cell 1 2 2 1,alignx center,growy");

		JButton btnEgunAutaketaKartelera = new JButton("Kartelera");
		egunAutaketa.add(btnEgunAutaketaKartelera, "cell 1 3");

		JButton btnEgunAutaketaAsteLaburpena = new JButton("Aste Laburpena");
		egunAutaketa.add(btnEgunAutaketaAsteLaburpena, "cell 2 3");

		JButton btnEgunAutaketaHasiera = new JButton("← Hasiera");
		egunAutaketa.add(btnEgunAutaketaHasiera, "cell 0 4");

		JButton btnEgunAutaketaBaieztatu = new JButton("Baieztatu →");
		egunAutaketa.add(btnEgunAutaketaBaieztatu, "cell 3 4");

		JPanel edukia = new JPanel();
		principal.add(edukia, "Edukia");
		edukia.setLayout(new MigLayout("", "[grow][][][][][][grow]", "[grow][][][][][][][][][][grow]"));

		JLabel lblFilmak = new JLabel("Filmak");
		edukia.add(lblFilmak, "cell 3 1,alignx center");

		JScrollPane scrollPaneEdukiaFilmak = new JScrollPane();
		edukia.add(scrollPaneEdukiaFilmak, "cell 1 2 5 1,grow");

		tableEdukiaFilmak = new JTable();
		scrollPaneEdukiaFilmak.setViewportView(tableEdukiaFilmak);
		tableEdukiaFilmak.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "ID", "Izenburua", "Iraupena", "PEGI", "Genero" }) {
			Class[] columnTypes = new Class[] { Integer.class, String.class, Integer.class, Integer.class,
					Object.class };

			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});

		JLabel lblFilmLaburrak = new JLabel("Film laburrak");
		edukia.add(lblFilmLaburrak, "cell 3 3,alignx center");

		JScrollPane scrollPaneEdukiaFilmLaburrak = new JScrollPane();
		edukia.add(scrollPaneEdukiaFilmLaburrak, "cell 1 4 5 1,grow");

		tableEdukiaFilmLaburrak = new JTable();
		scrollPaneEdukiaFilmLaburrak.setViewportView(tableEdukiaFilmLaburrak);
		tableEdukiaFilmLaburrak.setModel(
				new DefaultTableModel(new Object[][] {}, new String[] { "ID", "Izenburua", "Iraupena", "Fabula" }) {
					Class[] columnTypes = new Class[] { Integer.class, String.class, Integer.class, String.class };

					public Class getColumnClass(int columnIndex) {
						return columnTypes[columnIndex];
					}
				});

		JLabel lblDokumentalak = new JLabel("Dokumentalak");
		edukia.add(lblDokumentalak, "cell 3 5,alignx center");

		JScrollPane scrollPaneEdukiaDokumentalak = new JScrollPane();
		edukia.add(scrollPaneEdukiaDokumentalak, "cell 1 6 5 1,grow");

		tableEdukiaDokumentalak = new JTable();
		scrollPaneEdukiaDokumentalak.setViewportView(tableEdukiaDokumentalak);
		tableEdukiaDokumentalak.setModel(
				new DefaultTableModel(new Object[][] {}, new String[] { "ID", "Izenburua", "Iraupena", "Tema" }) {
					Class[] columnTypes = new Class[] { Integer.class, String.class, Integer.class, Object.class};

					public Class getColumnClass(int columnIndex) {
						return columnTypes[columnIndex];
					}
				});

		JButton btnEdukiaEditatu = new JButton("Editatu");
		edukia.add(btnEdukiaEditatu, "cell 1 7,growx");

		JButton btnEdukiaEzabatu = new JButton("Ezabatu");
		edukia.add(btnEdukiaEzabatu, "cell 2 7,growx");

		JButton btnEdukiaBerria = new JButton("Berria");
		edukia.add(btnEdukiaBerria, "cell 3 7,growx");

		JButton btnEdukiaEzeztatu = new JButton("Ezeztatu");
		edukia.add(btnEdukiaEzeztatu, "cell 4 7,growx");

		JButton btnEdukiaBaieztatu = new JButton("Baieztatu");
		edukia.add(btnEdukiaBaieztatu, "cell 5 7,growx");

		JButton btnEdukiaGehitu = new JButton("Gehitu");
		edukia.add(btnEdukiaGehitu, "cell 2 9,growx");

		JButton btnEdukiaLaburpena = new JButton("Laburpena");
		edukia.add(btnEdukiaLaburpena, "cell 4 9,growx");

	}
}
