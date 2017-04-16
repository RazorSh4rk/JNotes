import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class NoteWindow extends JDialog{
	JPanel panel = new JPanel();
	JLabel label = new JLabel();
	
	public NoteWindow(String note, Color c){
		label.setText(note);
		panel.setBackground(c);
		panel.setLayout(new FlowLayout());
		panel.add(label);
		this.add(panel);
		this.setLocationRelativeTo(null);
		this.setSize(300,200);
		this.setVisible(true);
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.pack();
	}
}
