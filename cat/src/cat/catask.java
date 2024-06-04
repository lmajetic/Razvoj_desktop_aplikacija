package cat;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JTextArea;
import javax.swing.AbstractButton;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.*;

public class catask extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textFieldName;
	private JTextField textFieldYear;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			catask dialog = new catask();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public catask() {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(null);
		contentPanel.setBounds(0, 0, 434, 228);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel);
		contentPanel.setLayout(null);
		{
			textFieldName = new JTextField();
			textFieldName.setColumns(10);
			textFieldName.setBounds(87, 8, 132, 20);
			contentPanel.add(textFieldName);
		}
		{
			JLabel lblName = new JLabel("Name:");
			lblName.setBounds(10, 11, 67, 14);
			contentPanel.add(lblName);
		}
		{
			JLabel lblYear = new JLabel("Year:");
			lblYear.setBounds(10, 55, 67, 14);
			contentPanel.add(lblYear);
		}
		{
			textFieldYear = new JTextField();
			textFieldYear.setColumns(10);
			textFieldYear.setBounds(87, 52, 132, 20);
			contentPanel.add(textFieldYear);
		}
		{
			JLabel lblType = new JLabel("Type:");
			lblType.setBounds(10, 95, 67, 14);
			contentPanel.add(lblType);
		}
		{
			JLabel lblDescription = new JLabel("Description:");
			lblDescription.setBounds(10, 143, 87, 14);
			contentPanel.add(lblDescription);
		}
		
		
		JComboBox comboBoxType = new JComboBox();
		comboBoxType.setModel(new DefaultComboBoxModel(new String[] {"Calico", "Tuxedo", "Tabby", "Other"}));
		comboBoxType.setBounds(87, 91, 132, 22);
		contentPanel.add(comboBoxType);
		
		JTextArea textAreaDesc = new JTextArea();
		textAreaDesc.setBounds(87, 138, 322, 79);
		contentPanel.add(textAreaDesc);
		

		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBounds(0, 228, 434, 33);
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						String name = textFieldName.getText();
						String year = textFieldYear.getText();
						String type = (String) comboBoxType.getSelectedItem();
						String desc = textAreaDesc.getText();
						
						try {
							Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
							Connection conn = DriverManager.getConnection("jdbc:mysql://ucka.veleri.hr/lmajetic?" +
																		"user=lmajetic&password=11");
							String sql = "INSERT INTO Cat(Name, Type, Year, Description) VALUES (?, ?, ?, ?);";
							PreparedStatement stmt = conn.prepareStatement(sql);
							stmt.setString(1,name);
							stmt.setString(2,type);
							stmt.setString(3,year);
							stmt.setString(4,desc);
							
							stmt.execute();
							conn.close();
							
						}
						catch(Exception ex) {
							JOptionPane.showMessageDialog(null, ex.getMessage(),"Greška", JOptionPane.ERROR_MESSAGE);
						}
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
}
