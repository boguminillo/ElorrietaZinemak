package front;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
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
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import back.CsvParser;
import back.objektuak.Dokumentala;
import back.objektuak.Egunak;
import back.objektuak.Erabiltzailea;
import back.objektuak.FilmLaburra;
import back.objektuak.Filma;
import back.objektuak.Funtzioak;
import back.objektuak.Proiekzioa;
import net.miginfocom.swing.MigLayout;

public class GUI {

	private String eguna;
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
	private JList<String> listaEdukiEgunarenLaburpena;
	private JLabel lblEdukiaDenboraLibre;
	private JList<String> listEgunLaburpena;
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
		frmElorrietaZinema.setBounds(100, 100, 1000, 500);
		frmElorrietaZinema.setMinimumSize(new Dimension(1000, 500));
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
				int denbora = Erabilgarriak.denboraLibre(egunekoProiekzioak, eguna);
				if (denbora == 0) {
					CardLayout cl = (CardLayout) principal.getLayout();
					cl.show(principal, "EgunLaburpena");
					Erabilgarriak.egunLaburpenaBete(listEgunLaburpena, egunekoProiekzioak, eguna);
				} else {
					Erabilgarriak.listaBete(listaEdukiEgunarenLaburpena, egunekoProiekzioak);
					lblEdukiaDenboraLibre
							.setText("Denbora librea: " + denbora);
					CardLayout cl = (CardLayout) principal.getLayout();
					cl.show(principal, "Edukia");
				}
			}
		});
		txtInfoEgunAutaketa.setBackground(UIManager.getColor("menu"));
		egunAutaketa.add(txtInfoEgunAutaketa, "cell 1 2 2 1,alignx center,growy");

		comboBoxEgunAutaketa = new JComboBox();
		comboBoxEgunAutaketa.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				eguna = comboBoxEgunAutaketa.getSelectedItem().toString();
				egunekoProiekzioak = CsvParser.irakurriProiekzioenZerrenda("datuak/" + eguna);
				txtInfoEgunAutaketa.setText(Erabilgarriak.egunaAukeratu(egunekoProiekzioak, eguna));
			}
		});
		comboBoxEgunAutaketa.setModel(new DefaultComboBoxModel(Egunak.values()));
		egunAutaketa.add(comboBoxEgunAutaketa, "cell 2 1,growx");

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
				proiekzioak = CsvParser.irakurriProiekzioenZerrenda("datuak/proiekzioak");
			}
		});
		egunAutaketa.add(btnEgunAutaketaHasiera, "cell 0 4");

		JButton btnEgunAutaketaBaieztatu = new JButton("Baieztatu →");
		btnEgunAutaketaBaieztatu.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				CsvParser.idatziProiekzioenZerrenda("datuak/proiekzioak", proiekzioak);
				JOptionPane.showMessageDialog(null, "Proiekzioak ondo gorde dira.");
				CardLayout cl = (CardLayout) principal.getLayout();
				cl.show(principal, "Sarrera");
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
		egunAutaketa.add(btnEgunAutaketaBaieztatu, "cell 3 4");

		JPanel edukia = new JPanel();
		principal.add(edukia, "Edukia");
		edukia.setLayout(
				new MigLayout("", "[grow][leading][][][][][][][grow]", "[grow][][grow][][][][][][grow]"));

		JLabel lblFilmak = new JLabel("Filmak");
		edukia.add(lblFilmak, "cell 3 1,alignx center");

		JButton btnEdukiaBerriaFilma = new JButton("+");
		btnEdukiaBerriaFilma.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				DefaultTableModel model = (DefaultTableModel) tableEdukiaFilmak.getModel();
				int id = Erabilgarriak.hurrengoId(proiekzioak);
				model.addRow(new Object[] { id, "", "", "", "", "" });
				Filma filma = new Filma();
				filma.setId(id);
				proiekzioak.add(filma);
			}
		});
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

			boolean[] canEdit = new boolean[] {
					false, true, true, true, true, true
			};

			public boolean isCellEditable(int rowIndex, int columnIndex) {
				return canEdit[columnIndex];
			}
		};
		tableEdukiaFilmak.setModel(modelPanelEdukiaFilmak);
		Erabilgarriak.tablaEdukia(modelPanelEdukiaFilmak, proiekzioak, Erabilgarriak.FILMA);

		JButton btnEdukiaGehituFilma = new JButton(">>");
		btnEdukiaGehituFilma.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int id = (int) tableEdukiaFilmak.getValueAt(tableEdukiaFilmak.getSelectedRow(), 0);
				Filma proiekzioa = (Filma) Erabilgarriak.proiekzioaLortu(id, proiekzioak);
				if (proiekzioa.getIraupena() > Erabilgarriak.denboraLibre(egunekoProiekzioak, eguna)) {
					JOptionPane.showMessageDialog(null, "Ez dago denborarik");
				} else if (Erabilgarriak.generoaBadago(proiekzioa.getGeneroa(), egunekoProiekzioak)) {
					JOptionPane.showMessageDialog(null, "Genero bereko proiekzioa dago");
				} else if (proiekzioa.getIraupena() < 1 || proiekzioa.getIzenburua().isBlank()) {
					JOptionPane.showMessageDialog(null,
							"Filmaren izenburua eta iraupena beharrezkoak dira (aldaketak baieztatu behar dira erabili ahal izateko)");
				} else {
					egunekoProiekzioak.add(proiekzioa);
					Erabilgarriak.listaBete(listaEdukiEgunarenLaburpena, egunekoProiekzioak);
					lblEdukiaDenboraLibre
							.setText("Denbora librea: " + Erabilgarriak.denboraLibre(egunekoProiekzioak, eguna));
				}
			}
		});
		edukia.add(btnEdukiaGehituFilma, "cell 6 2,growx");

		listaEdukiEgunarenLaburpena = new JList<String>();
		edukia.add(listaEdukiEgunarenLaburpena, "cell 7 2 1 5,grow");

		JLabel lblFilmLaburrak = new JLabel("Film laburrak");
		edukia.add(lblFilmLaburrak, "cell 3 3,alignx center");

		JButton btnEdukiaBerriaFilmaL = new JButton("+");
		btnEdukiaBerriaFilmaL.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				DefaultTableModel model = (DefaultTableModel) tableEdukiaFilmLaburrak.getModel();
				int id = Erabilgarriak.hurrengoId(proiekzioak);
				model.addRow(new Object[] { id, "", "", "" });
				FilmLaburra filmLaburra = new FilmLaburra();
				filmLaburra.setId(id);
				proiekzioak.add(filmLaburra);
			}
		});
		edukia.add(btnEdukiaBerriaFilmaL, "cell 4 3,growx");

		JScrollPane scrollPaneEdukiaFilmLaburrak = new JScrollPane();
		edukia.add(scrollPaneEdukiaFilmLaburrak, "cell 1 4 5 1,grow");

		tableEdukiaFilmLaburrak = new JTable();
		scrollPaneEdukiaFilmLaburrak.setViewportView(tableEdukiaFilmLaburrak);
		DefaultTableModel modelPanelEdukiaFilmLaburrak = new DefaultTableModel(new Object[][] {},
				new String[] { "ID", "Izenburua", "Iraupena", "Fabula" }) {
			Class[] columnTypes = new Class[] { Integer.class, String.class, Integer.class, String.class };

			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}

			boolean[] canEdit = new boolean[] {
					false, true, true, true
			};

			public boolean isCellEditable(int rowIndex, int columnIndex) {
				return canEdit[columnIndex];
			}
		};
		tableEdukiaFilmLaburrak.setModel(modelPanelEdukiaFilmLaburrak);
		Erabilgarriak.tablaEdukia(modelPanelEdukiaFilmLaburrak, proiekzioak, Erabilgarriak.FILMLABURRA);

		JButton btnEdukiaEzabatu = new JButton("<<");
		btnEdukiaEzabatu.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Erabilgarriak.ezabatuFilma(listaEdukiEgunarenLaburpena, egunekoProiekzioak);
				lblEdukiaDenboraLibre
						.setText("Denbora librea: " + Erabilgarriak.denboraLibre(egunekoProiekzioak, eguna));
			}
		});
		edukia.add(btnEdukiaEzabatu, "flowy,cell 6 4,growx");

		JButton btnEdukiaGehituFilmaL = new JButton(">>");
		btnEdukiaGehituFilmaL.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int id = (int) tableEdukiaFilmLaburrak.getValueAt(tableEdukiaFilmLaburrak.getSelectedRow(), 0);
				Proiekzioa proiekzioa = Erabilgarriak.proiekzioaLortu(id, proiekzioak);
				if (proiekzioa.getIraupena() > Erabilgarriak.denboraLibre(egunekoProiekzioak, eguna)) {
					JOptionPane.showMessageDialog(null, "Ez dago denborarik");
				} else if (Erabilgarriak.proiekzioaBadago(proiekzioa, egunekoProiekzioak)) {
					JOptionPane.showMessageDialog(null, "Proiekzioa badago egunan");
				} else if (proiekzioa.getIraupena() < 1 || proiekzioa.getIzenburua().isBlank()) {
					JOptionPane.showMessageDialog(null,
							"Filmaren izenburua eta iraupena beharrezkoak dira (aldaketak baieztatu behar dira erabili ahal izateko)");
				} else {
					egunekoProiekzioak.add(proiekzioa);
					Erabilgarriak.listaBete(listaEdukiEgunarenLaburpena, egunekoProiekzioak);
					int denbora = Erabilgarriak.denboraLibre(egunekoProiekzioak, eguna);
					lblEdukiaDenboraLibre
							.setText("Denbora librea: " + denbora);
					if (denbora == 0) {
						CardLayout cl = (CardLayout) principal.getLayout();
						cl.show(principal, "EgunLaburpena");
						Erabilgarriak.egunLaburpenaBete(listEgunLaburpena, egunekoProiekzioak, eguna);
					}
				}
			}
		});
		edukia.add(btnEdukiaGehituFilmaL, "cell 6 4,growx");

		JLabel lblDokumentalak = new JLabel("Dokumentalak");
		edukia.add(lblDokumentalak, "cell 3 5,alignx center");

		JButton btnEdukiaBerriaDok = new JButton("+");
		btnEdukiaBerriaDok.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				DefaultTableModel model = (DefaultTableModel) tableEdukiaDokumentalak.getModel();
				int id = Erabilgarriak.hurrengoId(proiekzioak);
				model.addRow(new Object[] { id, "", "", "", "" });
				Dokumentala dokumentala = new Dokumentala();
				dokumentala.setId(id);
				proiekzioak.add(dokumentala);
			}
		});
		edukia.add(btnEdukiaBerriaDok, "cell 4 5,growx");

		JScrollPane scrollPaneEdukiaDokumentalak = new JScrollPane();
		edukia.add(scrollPaneEdukiaDokumentalak, "cell 1 6 5 1,grow");

		tableEdukiaDokumentalak = new JTable();
		scrollPaneEdukiaDokumentalak.setViewportView(tableEdukiaDokumentalak);
		DefaultTableModel modelPanelEdukiaDokumentalak = new DefaultTableModel(new Object[][] {},
				new String[] { "ID", "Izenburua", "Iraupena", "Produktora", "Tema" }) {
			Class[] columnTypes = new Class[] { Integer.class, String.class, Integer.class, String.class,
					Object.class };

			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}

			boolean[] canEdit = new boolean[] {
					false, true, true, true, true
			};

			public boolean isCellEditable(int rowIndex, int columnIndex) {
				return canEdit[columnIndex];
			}
		};
		tableEdukiaDokumentalak.setModel(modelPanelEdukiaDokumentalak);
		Erabilgarriak.tablaEdukia(modelPanelEdukiaDokumentalak, proiekzioak, Erabilgarriak.DOKUMENTALA);

		JButton btnEdukiaGehituDok = new JButton(">>");
		btnEdukiaGehituDok.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int id = (int) tableEdukiaDokumentalak.getValueAt(tableEdukiaDokumentalak.getSelectedRow(), 0);
				Proiekzioa proiekzioa = Erabilgarriak.proiekzioaLortu(id, proiekzioak);
				if (proiekzioa.getIraupena() > Erabilgarriak.denboraLibre(egunekoProiekzioak, eguna)) {
					JOptionPane.showMessageDialog(null, "Ez dago denborarik");
				} else if (Erabilgarriak.proiekzioaBadago(proiekzioa, egunekoProiekzioak)) {
					JOptionPane.showMessageDialog(null, "Proiekzioa badago egunan");
				} else if (proiekzioa.getIraupena() < 1 || proiekzioa.getIzenburua().isBlank()) {
					JOptionPane.showMessageDialog(null,
							"Filmaren izenburua eta iraupena beharrezkoak dira (aldaketak baieztatu behar dira erabili ahal izateko)");
				} else {
					egunekoProiekzioak.add(proiekzioa);
					Erabilgarriak.listaBete(listaEdukiEgunarenLaburpena, egunekoProiekzioak);
					lblEdukiaDenboraLibre
							.setText("Denbora librea: " + Erabilgarriak.denboraLibre(egunekoProiekzioak, eguna));
				}
			}
		});
		edukia.add(btnEdukiaGehituDok, "cell 6 6,growx");

		JButton btnEdukiaEzeztatuDok = new JButton("Ezeztatu");
		btnEdukiaEzeztatuDok.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				proiekzioak = CsvParser.irakurriProiekzioenZerrenda("datuak/proiekzioak");
				Erabilgarriak.tablaEdukia(modelPanelEdukiaFilmak, proiekzioak, Erabilgarriak.FILMA);
				Erabilgarriak.tablaEdukia(modelPanelEdukiaDokumentalak, proiekzioak, Erabilgarriak.DOKUMENTALA);
				Erabilgarriak.tablaEdukia(modelPanelEdukiaFilmLaburrak, proiekzioak, Erabilgarriak.FILMLABURRA);
				egunekoProiekzioak = CsvParser.irakurriProiekzioenZerrenda("datuak/" + eguna);
				Erabilgarriak.listaBete(listaEdukiEgunarenLaburpena, egunekoProiekzioak);
				lblEdukiaDenboraLibre
						.setText("Denbora librea: " + Erabilgarriak.denboraLibre(egunekoProiekzioak, eguna));
				JOptionPane.showMessageDialog(null, "Edukia ezeztatuta");
			}
		});

		edukia.add(btnEdukiaEzeztatuDok, "cell 2 7,growx");

		JButton btnEdukiaBaieztatu = new JButton("Baieztatu");
		btnEdukiaBaieztatu.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				proiekzioak.clear();
				proiekzioak.addAll(
						Erabilgarriak.taulaToArrayList(modelPanelEdukiaDokumentalak, Erabilgarriak.DOKUMENTALA));
				proiekzioak.addAll(
						Erabilgarriak.taulaToArrayList(modelPanelEdukiaFilmLaburrak, Erabilgarriak.FILMLABURRA));
				proiekzioak.addAll(Erabilgarriak.taulaToArrayList(modelPanelEdukiaFilmak, Erabilgarriak.FILMA));
				CsvParser.idatziProiekzioenZerrenda("datuak/proiekzioak", proiekzioak);
				JOptionPane.showMessageDialog(null, "Edukia ondo gorde da");
			}
		});
		edukia.add(btnEdukiaBaieztatu, "cell 5 7,alignx center");

		lblEdukiaDenboraLibre = new JLabel("Denbora librea:");
		edukia.add(lblEdukiaDenboraLibre, "cell 7 7");

		JPanel egunLaburpena = new JPanel();
		principal.add(egunLaburpena, "EgunLaburpena");
		egunLaburpena.setLayout(new MigLayout("", "[grow][grow,right][grow]", "[grow][grow,top][grow][]"));

		JButton btnEgunLaburpenaBaieztatu = new JButton("Baieztatu");
		btnEgunLaburpenaBaieztatu.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				CsvParser.idatziProiekzioenZerrenda("datuak/" + eguna, egunekoProiekzioak);
				CardLayout cl = (CardLayout) principal.getLayout();
				cl.show(principal, "EgunAutaketa");
				// TODO Explicar lo de abajo lel
				comboBoxEgunAutaketa.setSelectedIndex(1);
				comboBoxEgunAutaketa.setSelectedIndex(0);
			}
		});

		listEgunLaburpena = new JList<String>();
		listEgunLaburpena.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				String id = listEgunLaburpena.getSelectedValue()
				.substring(0, listEgunLaburpena.getSelectedValue().indexOf(" - ")).trim();
				Proiekzioa proiekzioa = Erabilgarriak.proiekzioaLortu(Integer.parseInt(id), egunekoProiekzioak);
				JOptionPane.showMessageDialog(null, proiekzioa.toTestua());
			}
		});
		
		egunLaburpena.add(listEgunLaburpena, "cell 1 1,grow");
		egunLaburpena.add(btnEgunLaburpenaBaieztatu, "cell 2 3,alignx right");
		
		JPanel asteLaburpena = new JPanel();
		// documentar que esta pantalla no se veia bien por el tamaño y se soluciono con el jscrollpane
		JScrollPane scrollPaneAsteLaburpena = new JScrollPane(asteLaburpena);
		principal.add(scrollPaneAsteLaburpena, "AsteLaburpena");
		asteLaburpena.setLayout(new MigLayout("", "[grow][][][][][][][][grow]", "[grow][bottom][][][grow][]"));
		
		JLabel lblAstelehena = new JLabel("Astelehena");
		asteLaburpena.add(lblAstelehena, "cell 1 1");
		
		JLabel lblAsteartea = new JLabel("Asteartea");
		asteLaburpena.add(lblAsteartea, "cell 2 1");

		JLabel lblAsteazkena = new JLabel("Asteazkena");
		asteLaburpena.add(lblAsteazkena, "cell 3 1");

		JLabel lblOsteguna = new JLabel("Osteguna");
		asteLaburpena.add(lblOsteguna, "cell 4 1");

		JLabel lblOstirala = new JLabel("Ostirala");
		asteLaburpena.add(lblOstirala, "cell 5 1");

		JLabel lblLarunbata = new JLabel("Larunbata");
		asteLaburpena.add(lblLarunbata, "cell 6 1");

		JLabel lblIgandea = new JLabel("Igandea");
		asteLaburpena.add(lblIgandea, "cell 7 1");

		JList<String> listAstelehena = new JList<String>();
		listAstelehena.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				String id = listAstelehena.getSelectedValue()
						.substring(0, listAstelehena.getSelectedValue().indexOf(" - ")).trim();
				Proiekzioa proiekzioa = Erabilgarriak.proiekzioaLortu(Integer.parseInt(id), proiekzioak);
				JOptionPane.showMessageDialog(null, proiekzioa.toTestua());
			}
		});
		asteLaburpena.add(listAstelehena, "cell 1 2,grow");

		JList<String> listAsteartea = new JList<String>();
		listAsteartea.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				String id = listAsteartea.getSelectedValue()
						.substring(0, listAsteartea.getSelectedValue().indexOf(" - ")).trim();
				Proiekzioa proiekzioa = Erabilgarriak.proiekzioaLortu(Integer.parseInt(id), proiekzioak);
				JOptionPane.showMessageDialog(null, proiekzioa.toTestua());
			}
		});
		asteLaburpena.add(listAsteartea, "cell 2 2,grow");

		JList<String> listAsteazkena = new JList<String>();
		listAsteazkena.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				String id = listAsteazkena.getSelectedValue()
						.substring(0, listAsteazkena.getSelectedValue().indexOf(" - ")).trim();
				Proiekzioa proiekzioa = Erabilgarriak.proiekzioaLortu(Integer.parseInt(id), proiekzioak);
				JOptionPane.showMessageDialog(null, proiekzioa.toTestua());
			}
		});
		asteLaburpena.add(listAsteazkena, "cell 3 2,grow");

		JList<String> listOsteguna = new JList<String>();
		listOsteguna.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				String id = listOsteguna.getSelectedValue()
						.substring(0, listOsteguna.getSelectedValue().indexOf(" - ")).trim();
				Proiekzioa proiekzioa = Erabilgarriak.proiekzioaLortu(Integer.parseInt(id), proiekzioak);
				JOptionPane.showMessageDialog(null, proiekzioa.toTestua());
			}
		});
		asteLaburpena.add(listOsteguna, "cell 4 2,grow");

		JList<String> listOstirala = new JList<String>();
		listOstirala.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				String id = listOstirala.getSelectedValue()
						.substring(0, listOstirala.getSelectedValue().indexOf(" - ")).trim();
				Proiekzioa proiekzioa = Erabilgarriak.proiekzioaLortu(Integer.parseInt(id), proiekzioak);
				JOptionPane.showMessageDialog(null, proiekzioa.toTestua());
			}
		});
		asteLaburpena.add(listOstirala, "cell 5 2,grow");

		JList<String> listLarunbata = new JList<String>();
		listLarunbata.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				String id = listLarunbata.getSelectedValue()
						.substring(0, listLarunbata.getSelectedValue().indexOf(" - ")).trim();
				Proiekzioa proiekzioa = Erabilgarriak.proiekzioaLortu(Integer.parseInt(id), proiekzioak);
				JOptionPane.showMessageDialog(null, proiekzioa.toTestua());
			}
		});
		asteLaburpena.add(listLarunbata, "cell 6 2,grow");

		JList<String> listIgandea = new JList<String>();
		listIgandea.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				String id = listIgandea.getSelectedValue()
						.substring(0, listIgandea.getSelectedValue().indexOf(" - ")).trim();
				Proiekzioa proiekzioa = Erabilgarriak.proiekzioaLortu(Integer.parseInt(id), proiekzioak);
				JOptionPane.showMessageDialog(null, proiekzioa.toTestua());
			}
		});
		asteLaburpena.add(listIgandea, "cell 7 2,grow");

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

		JLabel lblDenboraAstelehena = new JLabel("Denbora");
		asteLaburpena.add(lblDenboraAstelehena, "cell 1 3");

		JLabel lblDenboraAsteartea = new JLabel("Denbora");
		asteLaburpena.add(lblDenboraAsteartea, "cell 2 3");

		JLabel lblDenboraAsteazkena = new JLabel("Denbora");
		asteLaburpena.add(lblDenboraAsteazkena, "cell 3 3");

		JLabel lblDenboraOsteguna = new JLabel("Denbora");
		asteLaburpena.add(lblDenboraOsteguna, "cell 4 3");

		JLabel lblDenboraOstirala = new JLabel("Denbora");
		asteLaburpena.add(lblDenboraOstirala, "cell 5 3");

		JLabel lblDenboraLarunbata = new JLabel("Denbora");
		asteLaburpena.add(lblDenboraLarunbata, "cell 6 3");

		JLabel lblDenboraIgandea = new JLabel("Denbora");
		asteLaburpena.add(lblDenboraIgandea, "cell 7 3");
		asteLaburpena.add(btnAsteLaburpenaEgunAutaketa, "cell 4 5");

		JButton btnEgunAutaketaAsteLaburpena = new JButton("Aste Laburpena");
		btnEgunAutaketaAsteLaburpena.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				eguna = Egunak.ASTELEHENA.toString();
				egunekoProiekzioak = CsvParser.irakurriProiekzioenZerrenda("datuak/" + eguna);
				Erabilgarriak.egunLaburpenaBete(listAstelehena, egunekoProiekzioak, eguna);
				lblDenboraAstelehena.setText("Denbora librea: " + Erabilgarriak.denboraLibre(egunekoProiekzioak, eguna));

				eguna = Egunak.ASTEARTEA.toString();
				egunekoProiekzioak = CsvParser.irakurriProiekzioenZerrenda("datuak/" + eguna);
				Erabilgarriak.egunLaburpenaBete(listAsteartea, egunekoProiekzioak, eguna);
				lblDenboraAsteartea.setText("Denbora librea: " + Erabilgarriak.denboraLibre(egunekoProiekzioak, eguna));

				eguna = Egunak.ASTEAZKENA.toString();
				egunekoProiekzioak = CsvParser.irakurriProiekzioenZerrenda("datuak/" + eguna);
				Erabilgarriak.egunLaburpenaBete(listAsteazkena, egunekoProiekzioak, eguna);
				lblDenboraAsteazkena.setText("Denbora librea: " + Erabilgarriak.denboraLibre(egunekoProiekzioak, eguna));

				eguna = Egunak.OSTEGUNA.toString();
				egunekoProiekzioak = CsvParser.irakurriProiekzioenZerrenda("datuak/" + eguna);
				Erabilgarriak.egunLaburpenaBete(listOsteguna, egunekoProiekzioak, eguna);
				lblDenboraOsteguna.setText("Denbora librea: " + Erabilgarriak.denboraLibre(egunekoProiekzioak, eguna));

				eguna = Egunak.OSTIRALA.toString();
				egunekoProiekzioak = CsvParser.irakurriProiekzioenZerrenda("datuak/" + eguna);
				Erabilgarriak.egunLaburpenaBete(listOstirala, egunekoProiekzioak, eguna);
				lblDenboraOstirala.setText("Denbora librea: " + Erabilgarriak.denboraLibre(egunekoProiekzioak, eguna));

				eguna = Egunak.LARUNBATA.toString();
				egunekoProiekzioak = CsvParser.irakurriProiekzioenZerrenda("datuak/" + eguna);
				Erabilgarriak.egunLaburpenaBete(listLarunbata, egunekoProiekzioak, eguna);
				lblDenboraLarunbata.setText("Denbora librea: " + Erabilgarriak.denboraLibre(egunekoProiekzioak, eguna));

				eguna = Egunak.IGANDEA.toString();
				egunekoProiekzioak = CsvParser.irakurriProiekzioenZerrenda("datuak/" + eguna);
				Erabilgarriak.egunLaburpenaBete(listIgandea, egunekoProiekzioak, eguna);
				lblDenboraIgandea.setText("Denbora librea: " + Erabilgarriak.denboraLibre(egunekoProiekzioak, eguna));
				
				CardLayout cl = (CardLayout) principal.getLayout();
				cl.show(principal, "AsteLaburpena");
			}
		});
		egunAutaketa.add(btnEgunAutaketaAsteLaburpena, "cell 2 3");
		
	}

}
