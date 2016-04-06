import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class HelloWorld {

	private JFrame frame;
	private JTextField textField1;
	private JTextField textField2;
	private JTextField answer;
	private float num1, num2, ans;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HelloWorld window = new HelloWorld();
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
	public HelloWorld() {
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
		
		textField1 = new JTextField();
		textField1.setBounds(24, 24, 86, 20);
		frame.getContentPane().add(textField1);
		textField1.setColumns(10);
		
		textField2 = new JTextField();
		textField2.setBounds(150, 24, 86, 20);
		frame.getContentPane().add(textField2);
		textField2.setColumns(10);
		
		JButton btnNewButton = new JButton("Plus");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				try{
					num1 = Float.parseFloat(textField1.getText());
					num2 = Float.parseFloat(textField2.getText());
					
					ans = num1 + num2;
					answer.setText(Float.toString(ans));
				}catch(Exception e1){
					JOptionPane.showMessageDialog(null, "Bitte Zahl eingeben");
				}
			}
		});
		btnNewButton.setBounds(24, 71, 89, 23);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Minus");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					num1 = Float.parseFloat(textField1.getText());
					num2 = Float.parseFloat(textField2.getText());
					
					ans = num1 - num2;
					answer.setText(Float.toString(ans));
				}catch(Exception e2){
					JOptionPane.showMessageDialog(null, "Bitte Zahl eingeben");
				}
				
			}
		});
		btnNewButton_1.setBounds(150, 71, 89, 23);
		frame.getContentPane().add(btnNewButton_1);
		
		answer = new JTextField();
		answer.setBounds(319, 24, 86, 20);
		frame.getContentPane().add(answer);
		answer.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Ergebnis:");
		lblNewLabel.setFont(new Font("Calibri", Font.BOLD, 12));
		lblNewLabel.setBounds(263, 27, 46, 14);
		frame.getContentPane().add(lblNewLabel);
	}
}
