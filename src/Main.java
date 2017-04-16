import java.awt.Color;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {

	public static void main(String[] args) {
		try{
			Class.forName("org.sqlite.JDBC");
		}catch(Exception e){
			PopupWindow p = new PopupWindow("Something went wrong.\n" + e.getMessage());
		}
		
		try(Connection conn = DriverManager.getConnection("jdbc:sqlite:notes.db");
				Statement st = conn.createStatement();){
					st.executeUpdate("CREATE TABLE IF NOT EXISTS notes (id INTEGER, text STRING)");
		}catch(Exception e){
				PopupWindow p = new PopupWindow("Something went wrong with creating the database, try again.\n\n" + e.getMessage());
				e.printStackTrace();
		}
		
		//Initializer i = new Initializer();
		//NoteAdder n = new NoteAdder();
		//NoteWidget w = new NoteWidget("asd", Color.RED);		
		//NoteSelector s = new NoteSelector();
		
		new MenuWindow();
	}

}
