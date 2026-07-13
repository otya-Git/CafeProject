package dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.CafeTable;

public class CafeTableDAO extends DAO {
	public List<CafeTabel> selectAll() threws Excption{
		
		List<CafeTabel> list = new ArrayList<>();
		
		Connection con = get Connection();
		
		String sql =
				"SLECT * FROM category CA"
	}

}
