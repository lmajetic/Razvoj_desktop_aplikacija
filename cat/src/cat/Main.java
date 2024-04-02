package cat;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import javax.swing.BoxLayout;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import net.miginfocom.swing.MigLayout;
import java.awt.event.ActionListener;
import java.util.concurrent.TimeUnit;
import java.awt.event.ActionEvent;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Choice;
import java.awt.Label;
import java.awt.TextField;
import javax.swing.JTextArea;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.Window.Type;

public class Main {

	private JFrame frmCat;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main window = new Main();
					window.frmCat.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Main() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmCat = new JFrame();
		frmCat.setTitle("Cat");
		frmCat.setBounds(100, 100, 450, 350);
		frmCat.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JButton btnMeow = new JButton("Meow");
		btnMeow.setBounds(33, 7, 91, 38);
		btnMeow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (btnMeow.getText() == "Meow") {
					btnMeow.setText("Mrow");
				}
				else btnMeow.setText("Meow");
			}
		});
		
		JButton btnPurr = new JButton("Purr");
		btnPurr.setBounds(163, 7, 95, 38);
		btnPurr.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (btnPurr.getText() == "Purr") {
					btnPurr.setText("Angry");
					btnPurr.setEnabled(false);
					try {
						  Thread.sleep(5000);
						} catch (InterruptedException e1) {
						  Thread.currentThread().interrupt();
						}
					btnPurr.setEnabled(true);
				}
				else btnPurr.setText("Purr");
			}
		});
		
		JLabel label = new JLabel("");
		
		JButton btnHiss = new JButton("Hiss");
		btnHiss.setBounds(163, 64, 95, 38);
		frmCat.getContentPane().setLayout(null);
		frmCat.getContentPane().add(btnMeow);
		frmCat.getContentPane().add(btnHiss);
		frmCat.getContentPane().add(btnPurr);
		
		JButton btnAddCat = new JButton("Add cat");
		btnAddCat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				catask dlg = new catask();
				dlg.setVisible(true);
			}
		});
		btnAddCat.setBounds(33, 67, 89, 32);
		frmCat.getContentPane().add(btnAddCat);
		
		
	}
}
