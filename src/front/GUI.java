package front;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import javax.swing.JTabbedPane;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTextField;
import net.miginfocom.swing.MigLayout;
import javax.swing.JPasswordField;
import javax.swing.JButton;

public class GUI {

	private JFrame frame;
	private JTextField textField;
	private JPasswordField passwordField;

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
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel principal = new JPanel();
		frame.getContentPane().add(principal, BorderLayout.CENTER);
		principal.setLayout(new CardLayout(0, 0));
		
		JTabbedPane sarrera = new JTabbedPane(JTabbedPane.TOP);
		principal.add(sarrera, "name_4539061051498");
		
		JPanel login = new JPanel();
		sarrera.addTab("Logina", null, login, null);
		login.setLayout(new MigLayout("", "[grow][94px,right][114px,grow,left][grow]", "[grow][19px][][grow][]"));
		
		JLabel lblErabiltzailea = new JLabel("Erabiltzailea:");
		login.add(lblErabiltzailea, "cell 1 1,alignx right,aligny center");
		
		textField = new JTextField();
		login.add(textField, "cell 2 1,growx,aligny center");
		textField.setColumns(10);
		
		JLabel lblPasahitza = new JLabel("Pasahitza:");
		login.add(lblPasahitza, "cell 1 2,alignx trailing");
		
		passwordField = new JPasswordField();
		login.add(passwordField, "cell 2 2,growx");
		
		JButton btnHasiera = new JButton("← Hasiera");
		login.add(btnHasiera, "cell 0 4,alignx left");
		
		JButton btnJarraitu = new JButton("Jarraitu →");
		login.add(btnJarraitu, "cell 3 4,alignx right");
		
		JPanel register = new JPanel();
		sarrera.addTab("Erregistroa", null, register, null);
	}

}
