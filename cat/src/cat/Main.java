package cat;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;

public class Main {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main window = new Main();
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
	public Main() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton btnPurr = new JButton("Purr");
		btnPurr.setBounds(10, 92, 113, 43);
		frame.getContentPane().add(btnPurr);
		
		JButton btnMeow = new JButton("Meow");
		btnMeow.setBounds(10, 11, 113, 43);
		frame.getContentPane().add(btnMeow);
		
		JButton btnHiss = new JButton("Hiss");
		btnHiss.setBounds(173, 11, 113, 43);
		frame.getContentPane().add(btnHiss);
	}
}
