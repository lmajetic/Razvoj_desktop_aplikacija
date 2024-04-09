package cat;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextArea;

public class DlgListCats extends JDialog {

	private final JPanel contentPanel = new JPanel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			DlgListCats dialog = new DlgListCats();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public DlgListCats() {
		setTitle("List of cats");
		setBounds(100, 100, 511, 368);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(41, 11, 410, 274);
		contentPanel.add(textArea);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		dohvatIzBaze();
	}
	public void dohvatIzBaze() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
			Connection conn = DriverManager.getConnection("jdbc:mysql://ucka.veleri.hr/lmajetic?" +
														"user=lmajetic&password=11");
			
			String sql = "SELECT * FROM Cat;";
			Statement stmt = conn.prepareStatement(sql);
			ResultSet rs = stmt.getResultSet();
			String text="";
			while (rs.next()) {
				text += "Id: "+ rs.getInt("ID")+"\n";
				text += "Ime: "+ rs.getString("Name")+"\n";
				text += "Vrsta: "+ rs.getString("Type")+"\n";
				text += "Starost: "+ rs.getString("Year")+"\n";
				text += "Opis: "+ rs.getString("Description")+"\n";
			}
			//textArea.setText(text);
			
			conn.close();
			
		}
		catch(Exception ex) {
			JOptionPane.showMessageDialog(null, ex.getMessage(),"Greška", JOptionPane.ERROR_MESSAGE);
		}
	}
}
