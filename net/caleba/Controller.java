package net.caleba;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.text.JTextComponent;

public class Controller extends JFrame {
	
	private static final long serialVersionUID = 2510954439373152463L;
	
	private static String message = "";

	Controller() {
		super("Simple Data Transfer Controller");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		Container pane = getContentPane();
		pane.add(new JLabel("Message: "), BorderLayout.PAGE_START);
		JTextComponent text = new JTextArea();
		pane.add(new JScrollPane(text), BorderLayout.CENTER);
		pane.add(new UpdateMessageButton(text), BorderLayout.PAGE_END);
		setSize(600, 300);
		setVisible(true);
	}
	
	public static String getMessage() {
		return message;
	}
	
	public static void setMessage(String message) {
		Controller.message = message;
	}

}

class UpdateMessageButton extends JButton implements ActionListener {
	
	private static final long serialVersionUID = -6192962508403455027L;
	
	private JTextComponent message;
	
	UpdateMessageButton(JTextComponent message) {
		super("Update Message");
		this.message = message;
		addActionListener(this);
	}
	
	public void actionPerformed(ActionEvent e) {
		Controller.setMessage(message.getText());
	}
	
}