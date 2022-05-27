package kiwi.gui;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class ErrorScreen extends JPanel {
	private static final long serialVersionUID = 631221936161982097L;

	public ErrorScreen() {
		this.add(new JLabel("Not Found"));
	}
}
