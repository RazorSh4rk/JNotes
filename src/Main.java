import java.awt.Color;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
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
					st.executeUpdate("CREATE TABLE IF NOT EXISTS openNotes (text String)");
		}catch(Exception e){
				PopupWindow p = new PopupWindow("Something went wrong with creating the database, try again.\n\n" + e.getMessage());
				e.printStackTrace();
		}
		
		try(Connection conn = DriverManager.getConnection("jdbc:sqlite:notes.db");
				Statement st = conn.createStatement();){
					ResultSet rs = st.executeQuery("SELECT * FROM openNotes");
					while(rs.next()){
						new NoteWindow(rs.getString("text"), Color.WHITE);
					}
			}catch(Exception e){
				e.printStackTrace();
		}
		new MenuWindow();
	}

}
