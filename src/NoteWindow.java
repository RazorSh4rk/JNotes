import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class NoteWindow extends JDialog{
	JPanel panel = new JPanel();
	JLabel label = new JLabel();
	public String note;
	public Color c;
	
	public NoteWindow(String note, Color c){
		this.note = note; this.c = c;
		label.setText(note);
		panel.setBackground(c);
		panel.setLayout(new FlowLayout());
		panel.add(label);
		this.add(panel);	
		Toolkit tk = Toolkit.getDefaultToolkit();
	    Dimension screenSize = tk.getScreenSize();
	    int screenHeight = screenSize.height;
	    int screenWidth = screenSize.width;
		this.setVisible(true);
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.pack();
		this.setSize(this.getWidth() + 50, this.getHeight() + 20);
		this.setLocation(screenWidth - 100, 50);
	}
}
