import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MenuWindow extends JFrame implements ActionListener{
	
	JPanel panel = new JPanel();
	JButton add = new JButton("New Note");
	JButton manage = new JButton("Manage database");
	JButton pin = new JButton("Pin a note");
	
	public MenuWindow(){
		add.addActionListener(this);
		manage.addActionListener(this);
		pin.addActionListener(this);
		
		panel.setLayout(new FlowLayout());
		panel.add(add);
		panel.add(manage);
		panel.add(pin);
		this.add(panel);
		
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent evt) {
				new TrayMenu();
			}
		});

		this.setSize(200, 200);
		this.setVisible(true);
		this.setResizable(false);
		this.setDefaultCloseOperation(HIDE_ON_CLOSE);
		this.setLocationRelativeTo(null);
	}

	@Override
	public void actionPerformed(ActionEvent a) {
		if(a.getSource() == add){
			new NoteAdder();
		}else if(a.getSource() == manage){
			new Initializer();
		}else if(a.getSource() == pin){
			new NoteSelector();
		}
	}
	
	
}
