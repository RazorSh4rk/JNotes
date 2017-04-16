import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class NoteAdder extends JFrame implements ActionListener{
	
	JPanel panel = new JPanel();
	JPanel panel0 = new JPanel();
	JPanel panel1 = new JPanel();
	JTextArea textA = new JTextArea();
	JButton ok = new JButton("Ok");
	JButton cancel = new JButton("Cancel");
	
	public NoteAdder(){
		
		panel.setLayout(new FlowLayout());
		panel0.setLayout(new GridLayout());
		panel1.setLayout(new FlowLayout());
		
		ok.addActionListener(this);
		cancel.addActionListener(this);
		
		textA.setBackground(Color.LIGHT_GRAY);
		textA.setColumns(35);
		textA.setRows(35);
		
		panel0.add(textA);
		panel1.add(ok);
		panel1.add(cancel);
		panel.add(panel0);
		panel.add(panel1);
		this.add(panel);
		
		this.setSize(400, 600);
		this.setVisible(true);
		this.setResizable(false);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setLocationRelativeTo(null);
		
		
		try{
			Class.forName("org.sqlite.JDBC");
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	@Override
	public void actionPerformed(ActionEvent a) {
		if(a.getSource() == cancel){
			this.dispose();
		}else if(a.getSource() == ok){
			if(!"".equals(textA.getText())){
				
				try(Connection conn = DriverManager.getConnection("jdbc:sqlite:notes.db");
					Statement st = conn.createStatement();){
					ResultSet rs = st.executeQuery("SELECT id FROM notes");
					int id = 0;
					while(rs.next()){
						if(rs.getInt("id") == id){
							id = rs.getInt("id") + 1;
						}
					}
					System.out.println("INSERT INTO notes VALUES(" + id + ", '" + textA.getText() + "')");
					st.executeUpdate("INSERT INTO notes VALUES(" + id + ", '" + textA.getText() + "')");
				
				}catch(Exception e){
					PopupWindow p = new PopupWindow("Something went wrong, try again.\n\n" + e.getMessage());
					e.printStackTrace();
				}
				
			}else{
				PopupWindow p = new PopupWindow("Please write something");
			}
		}
	}
}
