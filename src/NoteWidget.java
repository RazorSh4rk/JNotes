import java.awt.Color;
import java.awt.FlowLayout;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class NoteWidget extends JDialog{	
	
	JPanel panel = new JPanel();
	JLabel label = new JLabel();	
	
	public NoteWidget(String note, Color c){
		label.setText(note);
		panel.setBackground(c);
		panel.setLayout(new FlowLayout());
		panel.add(label);
		this.add(panel);
		this.setLocationRelativeTo(null);
		this.setSize(300,200);
		this.setVisible(true);
		this.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
	}
}
