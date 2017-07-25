import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.smallken.db.DBUtil;

public class TestMysql {

	/**
	 * @param args
	 */
	public static void main(String[] args) {



		//example 1   read localhost file
		DBUtil dbuti = null;
		try {
			dbuti = new DBUtil(true);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		try {

			ResultSet rs = dbuti.Query("SELECT * FROM student");
			List<Map<String, Object>> lt = dbuti.dsToMaps(rs);
			for (Map m : lt) {
				System.out.println(m.get("id").toString());
				System.out.println(m.get("name").toString());
				System.out.println(m.get("year").toString());
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbuti.close();
		}
		 
	}}
