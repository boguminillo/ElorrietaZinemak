package front;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;

import back.CsvParser;
import back.objektuak.Egunak;
import back.objektuak.Erabiltzailea;
import back.objektuak.FilmLaburra;
import back.objektuak.FilmLuzea;
import back.objektuak.Funtzioak;
import back.objektuak.Proiekzioa;
import net.miginfocom.swing.MigLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

public class GUI {

	private JFrame frmElorrietaZinema;
	private JTextField txtLoginErabiltzailea;
	private JPasswordField passwordFieldLogin;
	private JTextField txtErregistroJaiotzeData;
	private JTextField txtErregistroAbizenak;
	private JTextField txtErregistroIzena;
	private JPasswordField passwordFieldErregistroErrepikatu;
	private JPasswordField passwordFieldErregistro;
	private JTextField txtErregistroErabiltzailea;
	private JComboBox comboBoxEgunAutaketa;
	private JTable tableEdukiaFilmak;
	private JTable tableEdukiaDokumentalak;
	private JTable tableEdukiaFilmLaburrak;
	private ArrayList<Proiekzioa> proiekzioak = CsvParser.irakurriProiekzioenZerrenda("datuak/proiekzioak");
	private ArrayList<Proiekzioa> egunekoProiekzioak = CsvParser.irakurriProiekzioenZerrenda("datuak/ASTELEHENA");

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI window = new GUI();
					window.frmElorrietaZinema.setVisible(true);
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
		frmElorrietaZinema = new JFrame();
		frmElorrietaZinema.setTitle("Elorrieta Zinema");
		frmElorrietaZinema.setBounds(100, 100, 640, 480);
		frmElorrietaZinema.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel principal = new JPanel();
		frmElorrietaZinema.getContentPane().add(principal, BorderLayout.CENTER);
		principal.setLayout(new CardLayout(0, 0));

		JPanel ongiEtorria = new JPanel();
		ongiEtorria.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				CardLayout cl = (CardLayout) principal.getLayout();
				cl.show(principal, "Sarrera");
			}
		});

		/* OngiEtorri Argazkia */

		ImageIcon ongietorriArgazkia = new ImageIcon("assets/ongietorri.jpg");
		// TODO Tenemos que poner el logo en el ongietorria (editar con inkscape)

		JLabel lblIconOngiEtorri = new JLabel();
		Font font = new Font("Agency FB", Font.BOLD, 36);
		lblIconOngiEtorri.setText("Ongi Etorri Elorrieta Zinemara!!");
		lblIconOngiEtorri.setIcon(ongietorriArgazkia);
		lblIconOngiEtorri.setHorizontalTextPosition(JLabel.CENTER);
		lblIconOngiEtorri.setVerticalTextPosition(JLabel.TOP);
		lblIconOngiEtorri.setForeground(new Color(255, 255, 255));
		lblIconOngiEtorri.setFont(font);
		lblIconOngiEtorri.setIconTextGap(15);
		lblIconOngiEtorri.setBackground(Color.black);
		lblIconOngiEtorri.setOpaque(true);
		lblIconOngiEtorri.setVerticalAlignment(JLabel.CENTER);
		lblIconOngiEtorri.setHorizontalAlignment(JLabel.CENTER);

		principal.add(ongiEtorria, "OngiEtorria");
		ongiEtorria.setVisible(true);
		ongiEtorria.setLayout(new BorderLayout(0, 0));
		ongiEtorria.add(lblIconOngiEtorri);

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

		JPanel register = new JPanel();
		sarrera.addTab("Erregistroa", null, register, null);
		register.setLayout(new MigLayout("", "[grow][right][left][grow,right]", "[grow][][][][][][][][grow][]"));

		JComboBox comboBoxErregistroFuntzioak = new JComboBox();
		comboBoxErregistroFuntzioak.setModel(new DefaultComboBoxModel(Funtzioak.values()));
		register.add(comboBoxErregistroFuntzioak, "cell 2 7,growx");

		JButton btnLoginHasiera = new JButton("← Hasiera");
		btnLoginHasiera.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				CardLayout cl = (CardLayout) principal.getLayout();
				cl.show(principal, "OngiEtorria");
				txtLoginErabiltzailea.setText("");
				passwordFieldLogin.setText("");
				txtErregistroErabiltzailea.setText("");
				passwordFieldErregistro.setText("");
				passwordFieldErregistroErrepikatu.setText("");
				txtErregistroIzena.setText("");
				txtErregistroAbizenak.setText("");
				txtErregistroJaiotzeData.setText("");
				comboBoxErregistroFuntzioak.setSelectedIndex(0);
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
					cl.show(principal, "EgunAutaketa");
					// Hacemos dos cambios para asegurar que se dispare el evento que mostrara el
					// resumen del dia
					comboBoxEgunAutaketa.setSelectedIndex(1);
					comboBoxEgunAutaketa.setSelectedIndex(0);
				} else if (!erabiltzailea.login())
					JOptionPane.showMessageDialog(null, "Erabiltzailea edota pasahitza txarto idatzita daude.");
			}
		});
		login.add(btnLoginJarraitu, "cell 3 4,alignx right");

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

		JButton btnErregistratu = new JButton("Erregistratu");
		btnErregistratu.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				LocalDate jaiotzeData;
				try {
					jaiotzeData = LocalDate.parse(txtErregistroJaiotzeData.getText());
				} catch (DateTimeParseException e1) {
					JOptionPane.showMessageDialog(null, "Jaiotze data ez da zuzena.(yyyy-mm-dd)");
					return;
				}
				String login = txtErregistroErabiltzailea.getText();
				String pasahitza = new String(passwordFieldErregistro.getPassword());
				String pasahitzaErrepikatu = new String(passwordFieldErregistroErrepikatu.getPassword());
				String izena = txtErregistroIzena.getText();
				String abizenak = txtErregistroAbizenak.getText();
				Funtzioak funtzioa = (Funtzioak) comboBoxErregistroFuntzioak.getSelectedItem();
				if (!pasahitza.equals(pasahitzaErrepikatu)) {
					JOptionPane.showMessageDialog(null, "Pasahitzak ez dira berdinak.");
				} else if (login.isBlank() || pasahitza.isBlank() || izena.isBlank() || abizenak.isBlank()) {
					JOptionPane.showMessageDialog(null, "Eremu guztiak bete behar dira.");
				} else if (jaiotzeData.isAfter(LocalDate.now())) {
					JOptionPane.showMessageDialog(null, "Jaiotze data ez da zuzena.");
				} else {
					Erabiltzailea erabiltzailea = new Erabiltzailea();
					erabiltzailea.setLogin(login);
					erabiltzailea.setPasahitza(pasahitza);
					erabiltzailea.setIzena(izena);
					erabiltzailea.setAbizena(abizenak);
					erabiltzailea.setJaiotzeData(jaiotzeData);
					erabiltzailea.setFuntzioa(funtzioa);
					if (erabiltzailea.erregistratutaDago()) {
						JOptionPane.showMessageDialog(null, "Erabiltzailea erregistratuta dago.");
					} else {
						JOptionPane.showMessageDialog(null, "Erabiltzailea erregistratu egin da.");
						erabiltzailea.erregistratu();
						sarrera.setSelectedIndex(0);
					}
				}
				txtErregistroErabiltzailea.setText("");
				passwordFieldErregistro.setText("");
				passwordFieldErregistroErrepikatu.setText("");
				txtErregistroIzena.setText("");
				txtErregistroAbizenak.setText("");
				txtErregistroJaiotzeData.setText("");
				comboBoxErregistroFuntzioak.setSelectedIndex(0);
			}
		});
		register.add(btnErregistratu, "cell 3 9");

		JPanel egunAutaketa = new JPanel();
		principal.add(egunAutaketa, "EgunAutaketa");
		egunAutaketa.setLayout(new MigLayout("", "[grow][right][left][grow,right]", "[][][grow][][]"));

		JLabel lblEgunaAukeratu = new JLabel("Eguna aukeratu:");
		egunAutaketa.add(lblEgunaAukeratu, "cell 1 1,alignx trailing");

		JTextArea txtInfoEgunAutaketa = new JTextArea();
		txtInfoEgunAutaketa.setEditable(false);
		txtInfoEgunAutaketa.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String eguna = comboBoxEgunAutaketa.getSelectedItem().toString();
				egunekoProiekzioak = CsvParser.irakurriProiekzioenZerrenda("datuak/" + eguna);
				CardLayout cl = (CardLayout) principal.getLayout();
				cl.show(principal, "Edukia");
			}
		});
		txtInfoEgunAutaketa.setBackground(UIManager.getColor("menu"));
		egunAutaketa.add(txtInfoEgunAutaketa, "cell 1 2 2 1,alignx center,growy");

		comboBoxEgunAutaketa = new JComboBox();
		comboBoxEgunAutaketa.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				String eguna = comboBoxEgunAutaketa.getSelectedItem().toString();
				egunekoProiekzioak = CsvParser.irakurriProiekzioenZerrenda("datuak/" + eguna);
				txtInfoEgunAutaketa.setText(Erabilgarriak.egunaAukeratu(egunekoProiekzioak, eguna));
			}
		});
		comboBoxEgunAutaketa.setModel(new DefaultComboBoxModel(Egunak.values()));
		egunAutaketa.add(comboBoxEgunAutaketa, "cell 2 1,growx");

		JButton btnEgunAutaketaAsteLaburpena = new JButton("Aste Laburpena");
		btnEgunAutaketaAsteLaburpena.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				CardLayout cl = (CardLayout) principal.getLayout();
				cl.show(principal, "AsteLaburpena");
			}
		});
		egunAutaketa.add(btnEgunAutaketaAsteLaburpena, "cell 2 3");

		JButton btnEgunAutaketaHasiera = new JButton("← Hasiera");
		btnEgunAutaketaHasiera.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				CardLayout cl = (CardLayout) principal.getLayout();
				cl.show(principal, "OngiEtorria");
				txtLoginErabiltzailea.setText("");
				passwordFieldLogin.setText("");
				txtErregistroErabiltzailea.setText("");
				passwordFieldErregistro.setText("");
				passwordFieldErregistroErrepikatu.setText("");
				txtErregistroIzena.setText("");
				txtErregistroAbizenak.setText("");
				txtErregistroJaiotzeData.setText("");
				comboBoxErregistroFuntzioak.setSelectedIndex(0);
			}
		});
		egunAutaketa.add(btnEgunAutaketaHasiera, "cell 0 4");

		JButton btnEgunAutaketaBaieztatu = new JButton("Baieztatu →");
		btnEgunAutaketaBaieztatu.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				CardLayout cl = (CardLayout) principal.getLayout();
				cl.show(principal, "Baieztapena");
			}
		});
		egunAutaketa.add(btnEgunAutaketaBaieztatu, "cell 3 4");

		JPanel edukia = new JPanel();
		principal.add(edukia, "Edukia");
		edukia.setLayout(
				new MigLayout("", "[grow][leading][][][][][grow]", "[grow][][][][][grow][][][][][][][][][][][grow]"));

		JLabel lblFilmak = new JLabel("Filmak");
		edukia.add(lblFilmak, "cell 3 1,alignx center");

		JButton btnEdukiaBerriaFilma = new JButton("+");
		edukia.add(btnEdukiaBerriaFilma, "cell 4 1,growx");

		JScrollPane scrollPaneEdukiaFilmak = new JScrollPane();
		edukia.add(scrollPaneEdukiaFilmak, "cell 1 2 5 1,grow");

		tableEdukiaFilmak = new JTable();
		scrollPaneEdukiaFilmak.setViewportView(tableEdukiaFilmak);

		DefaultTableModel modelPanelEdukiaFilmak = new DefaultTableModel(new Object[][] {},
				new String[] { "ID", "Izenburua", "Iraupena", "Produktora", "PEGI", "Genero" }) {
			Class[] columnTypes = new Class[] { Integer.class, String.class, Integer.class, String.class, Integer.class,
					Object.class };

			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		};
		tableEdukiaFilmak.setModel(modelPanelEdukiaFilmak);
		Erabilgarriak.tablaEdukia(modelPanelEdukiaFilmak, proiekzioak, Erabilgarriak.FILMA);

		JButton btnEdukiaEzabatuFilma = new JButton("Ezabatu");
		edukia.add(btnEdukiaEzabatuFilma, "flowy,cell 6 2,growx");

		JButton btnEdukiaGehituFilma = new JButton("Gehitu");
		edukia.add(btnEdukiaGehituFilma, "cell 6 2,growx");

		JLabel lblFilmLaburrak = new JLabel("Film laburrak");
		edukia.add(lblFilmLaburrak, "cell 3 6,alignx center");

		JButton btnEdukiaBerriaFilmaL = new JButton("+");
		edukia.add(btnEdukiaBerriaFilmaL, "cell 4 6,growx");

		JScrollPane scrollPaneEdukiaFilmLaburrak = new JScrollPane();
		edukia.add(scrollPaneEdukiaFilmLaburrak, "cell 1 7 5 1,grow");

		tableEdukiaFilmLaburrak = new JTable();
		scrollPaneEdukiaFilmLaburrak.setViewportView(tableEdukiaFilmLaburrak);
		DefaultTableModel modelPanelEdukiaFilmLaburrak = new DefaultTableModel(new Object[][] {},
				new String[] { "ID", "Izenburua", "Iraupena", "Fabula" }) {
			Class[] columnTypes = new Class[] { Integer.class, String.class, Integer.class, String.class };

			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		};
		tableEdukiaFilmLaburrak.setModel(modelPanelEdukiaFilmLaburrak);
		Erabilgarriak.tablaEdukia(modelPanelEdukiaFilmLaburrak, proiekzioak, Erabilgarriak.FILMLABURRA);

		JButton btnEdukiaEzabatuFilmaL = new JButton("Ezabatu");
		edukia.add(btnEdukiaEzabatuFilmaL, "flowy,cell 6 7,growx");

		JButton btnEdukiaGehituFilmaL = new JButton("Gehitu");
		edukia.add(btnEdukiaGehituFilmaL, "cell 6 7,growx");

		JLabel lblDokumentalak = new JLabel("Dokumentalak");
		edukia.add(lblDokumentalak, "cell 3 10,alignx center");

		JButton btnEdukiaBerriaDok = new JButton("+");
		edukia.add(btnEdukiaBerriaDok, "cell 4 10,growx");

		JScrollPane scrollPaneEdukiaDokumentalak = new JScrollPane();
		edukia.add(scrollPaneEdukiaDokumentalak, "cell 1 11 5 1,grow");

		tableEdukiaDokumentalak = new JTable();
		scrollPaneEdukiaDokumentalak.setViewportView(tableEdukiaDokumentalak);
		DefaultTableModel modelPanelEdukiaDokumentalak = new DefaultTableModel(new Object[][] {},
				new String[] { "ID", "Izenburua", "Iraupena", "Tema" }) {
			Class[] columnTypes = new Class[] { Integer.class, String.class, Integer.class, Object.class };

			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		};
		tableEdukiaDokumentalak.setModel(modelPanelEdukiaDokumentalak);
		Erabilgarriak.tablaEdukia(modelPanelEdukiaDokumentalak, proiekzioak, Erabilgarriak.DOKUMENTALA);

		JButton btnEdukiaEzabatuDok = new JButton("Ezabatu");
		edukia.add(btnEdukiaEzabatuDok, "flowy,cell 6 11,growx");

		JButton btnEdukiaGehituDok = new JButton("Gehitu");
		edukia.add(btnEdukiaGehituDok, "cell 6 11,growx");

		JButton btnEdukiaEzeztatuDok = new JButton("Ezeztatu");
		edukia.add(btnEdukiaEzeztatuDok, "cell 2 15,growx");

		JButton btnEdukiaLaburpena = new JButton("Laburpena");
		btnEdukiaLaburpena.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				CardLayout cl = (CardLayout) principal.getLayout();
				cl.show(principal, "EgunLaburpena");
			}
		});

		JButton btnEdukiaBaieztatu = new JButton("Baieztatu");
		btnEdukiaBaieztatu.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				proiekzioak.clear();
				proiekzioak.addAll(Erabilgarriak.taulaToArrayList(modelPanelEdukiaDokumentalak, Erabilgarriak.DOKUMENTALA));
				proiekzioak.addAll(Erabilgarriak.taulaToArrayList(modelPanelEdukiaFilmLaburrak, Erabilgarriak.FILMLABURRA));
				proiekzioak.addAll(Erabilgarriak.taulaToArrayList(modelPanelEdukiaFilmak, Erabilgarriak.FILMA));
			}
		});
		edukia.add(btnEdukiaBaieztatu, "cell 4 15,growx");
		edukia.add(btnEdukiaLaburpena, "cell 5 15,alignx center");

		JPanel egunLaburpena = new JPanel();
		principal.add(egunLaburpena, "EgunLaburpena");
		egunLaburpena.setLayout(new MigLayout("", "[grow][right][left][grow]", "[grow][bottom][top][grow][]"));

		JButton btnEgunLaburpenaBaieztatu = new JButton("Baieztatu");
		btnEgunLaburpenaBaieztatu.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				CardLayout cl = (CardLayout) principal.getLayout();
				cl.show(principal, "EgunAutaketa");
				// TODO Explicar lo de abajo lel
				comboBoxEgunAutaketa.setSelectedIndex(1);
				comboBoxEgunAutaketa.setSelectedIndex(0);
			}
		});
		egunLaburpena.add(btnEgunLaburpenaBaieztatu, "cell 3 4,alignx right");

		JPanel asteLaburpena = new JPanel();
		principal.add(asteLaburpena, "AsteLaburpena");
		asteLaburpena.setLayout(new MigLayout("", "[grow][right][center][][grow]", "[grow][bottom][top][grow][]"));

		JButton btnAsteLaburpenaEgunAutaketa = new JButton("Egun Autaketa");
		btnAsteLaburpenaEgunAutaketa.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				CardLayout cl = (CardLayout) principal.getLayout();
				cl.show(principal, "EgunAutaketa");
				// TODO Explicar lo de abajo lel
				comboBoxEgunAutaketa.setSelectedIndex(1);
				comboBoxEgunAutaketa.setSelectedIndex(0);
			}
		});
		asteLaburpena.add(btnAsteLaburpenaEgunAutaketa, "cell 2 4");

		JPanel baieztapena = new JPanel();
		principal.add(baieztapena, "Baieztapena");
		baieztapena.setLayout(new MigLayout("", "[grow][][grow][][grow]", "[154.00,grow][grow][grow][grow,bottom]"));

		JButton btnBaieztapenaBukatu = new JButton("Loginera Itzuli");
		btnBaieztapenaBukatu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CardLayout cl = (CardLayout) principal.getLayout();
				JOptionPane.showMessageDialog(null, "Dena ondo gorde da.");
				txtLoginErabiltzailea.setText("");
				passwordFieldLogin.setText("");
				txtErregistroErabiltzailea.setText("");
				passwordFieldErregistro.setText("");
				passwordFieldErregistroErrepikatu.setText("");
				txtErregistroIzena.setText("");
				txtErregistroAbizenak.setText("");
				txtErregistroJaiotzeData.setText("");
				comboBoxErregistroFuntzioak.setSelectedIndex(0);
				// TODO
				cl.show(principal, "OngiEtorria");

			}

		});

		JLabel lblNewLabel = new JLabel("New label");
		baieztapena.add(lblNewLabel, "cell 2 0,alignx center");

		JLabel lblBaieztapena = new JLabel("New label");
		baieztapena.add(lblBaieztapena, "cell 2 1,alignx center,aligny bottom");
		baieztapena.add(btnBaieztapenaBukatu, "cell 2 2,alignx center,aligny top");

	}
}
