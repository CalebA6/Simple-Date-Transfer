package net.caleba;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.text.JTextComponent;

public class Message extends JFrame {
	
	private static final long serialVersionUID = -8621766415654905045L;
	
	private static final int MIN_WIDTH = 258;
	private static final int BAR_HEIGHT = 39;

	Message(String message) {
		super("New Message");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		Container pane = getContentPane();
		pane.add(new JLabel("New Message: "), BorderLayout.PAGE_START);
		JTextComponent text = new JTextArea(message);
		text.setEditable(false);
		pane.add(new JScrollPane(text), BorderLayout.CENTER);
		Dimension size = pane.getPreferredSize();
		setSize(Math.max((int)size.getWidth(), MIN_WIDTH), (int)size.getHeight() + BAR_HEIGHT);
		setVisible(true);
	}
	
}
