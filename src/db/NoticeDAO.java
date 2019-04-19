package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.InitialContext;
import javax.sql.DataSource;

public class NoticeDAO {
	
	Connection con;
	PreparedStatement ptmt;
	ResultSet rs;
	String sql;
	
	public NoticeDAO() {
		// TODO Auto-generated constructor stub
		try {
			DataSource ds = (DataSource)new InitialContext().lookup("java:comp/env/notice");
			con = ds.getConnection();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	
	
	
	public int total() {
		
		int res = 0;
		
		try {
			sql = "select count(*) from notice";
			
			ptmt = con.prepareStatement(sql);

			rs = ptmt.executeQuery();
			
			rs.next();
				
			res = rs.getInt(1);		
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();		
		}

		return res;
	}
	
	
	
	public Object list() {
		ArrayList<NoticeDTO> res = new ArrayList<NoticeDTO>();
		
		
		
		try {
			sql = "select * from notice ";
			
			ptmt = con.prepareStatement(sql);
			
			
						
			rs = ptmt.executeQuery();
			
			while(rs.next()) {
				NoticeDTO dto = new NoticeDTO();
				dto.setId(rs.getInt("id"));
				dto.setGid(rs.getInt("gid"));
				dto.setSeq(rs.getInt("seq"));
				dto.setLevel(rs.getInt("level"));
				dto.setNo(rs.getInt("no"));
				dto.setTitle(rs.getString("title"));
				dto.setPname(rs.getString("pname"));
				dto.setRegdate(rs.getTimestamp("regdate"));
				
				res.add(dto);
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			
			close();
		}
		
		
		return res;
	}
	
	
	public void addCount(NoticeDTO dto) {
		
		sql = "update notice set no = no + 1 where  id = ?";
		
		try {
			ptmt = con.prepareStatement(sql);
			ptmt.setInt(1, dto.id);
			ptmt.executeUpdate();
			
					
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	
	
	
	
	public NoticeDTO detail(NoticeDTO dto) {
		NoticeDTO res = null;
		
		sql = "select * from notice where  id = ?";
		
		try {
			ptmt = con.prepareStatement(sql);
			ptmt.setInt(1, dto.id);
			rs = ptmt.executeQuery();
			
			if(rs.next()) {
				res = new NoticeDTO();
				res.setId(rs.getInt("id"));
				res.setGid(rs.getInt("gid"));
				res.setSeq(rs.getInt("seq"));
				res.setLevel(rs.getInt("level"));
				res.setNo(rs.getInt("no"));
				res.setTitle(rs.getString("title"));
				res.setPname(rs.getString("pname"));
				res.setRegdate(rs.getTimestamp("regdate"));
				res.setUpfile(rs.getString("upfile"));
				res.setContent(rs.getString("content"));
			}
					
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		
		}

		return res;
	}
	
	
	
	public NoticeDTO fileDelete(NoticeDTO dto) {
		NoticeDTO res = null;
		
		sql = "select * from notice where  id = ? and pw = ?";
		
		try {
			ptmt = con.prepareStatement(sql);
			ptmt.setInt(1, dto.id);
			ptmt.setString(2, dto.pw);
			rs = ptmt.executeQuery();
			
			if(rs.next()) {
				res = new NoticeDTO();
				res.setId(rs.getInt("id"));
				res.setGid(rs.getInt("gid"));
				res.setSeq(rs.getInt("seq"));
				res.setLevel(rs.getInt("level"));
				res.setNo(rs.getInt("no"));
				res.setTitle(rs.getString("title"));
				res.setPname(rs.getString("pname"));
				res.setRegdate(rs.getTimestamp("regdate"));
				res.setUpfile(rs.getString("upfile"));
				res.setContent(rs.getString("content"));
				
				
				sql = "update Notice set upfile = null where id = ?";
				
				ptmt = con.prepareStatement(sql);
				
				ptmt.setInt(1, dto.getId());
				
				ptmt.executeUpdate(); 
			}
					
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			
			close();
		}

		return res;
	}
	
	
	
	public void write(NoticeDTO dto) {
		
		
		try {
			
			sql = "insert into notice "
			+ "(seq, level, no, title, pname, pw, content, upfile, regdate ) values "
			+ "(0    ,  0  ,-1,  ? ,  ? ,   ? ,  ? , ? ,  sysdate() )";
			
			ptmt = con.prepareStatement(sql);
			ptmt.setString(1, dto.getTitle());
			ptmt.setString(2, dto.getPname());
			ptmt.setString(3, dto.getPw());
			ptmt.setString(4, dto.getContent());
			ptmt.setString(5, dto.getUpfile());
			ptmt.executeUpdate();
			
			sql = "select max(id) from Notice";
			
			ptmt = con.prepareStatement(sql);
			
			rs = ptmt.executeQuery();
			
			rs.next();
			
			dto.setId(rs.getInt(1));
			
			
			sql = "update notice set gid = id where  id = ?";
			ptmt = con.prepareStatement(sql);
			ptmt.setInt(1, dto.id);
			ptmt.executeUpdate();

					
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			
			close();
		}

	}
	
	
	
	
	public boolean delete(NoticeDTO dto) {
		
		boolean res = false;
		
		try {
			
			sql = "delete from notice where id = ? and pw = ? ";
			
			ptmt = con.prepareStatement(sql);
			
			ptmt.setInt(1, dto.getId());
			ptmt.setString(2, dto.getPw());
			
			res = ptmt.executeUpdate() > 0;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			
			close();
		}
		
		
		return res;

	}
	
	
	public boolean modify(NoticeDTO dto) {
		
		boolean res = false;
		
		try {
			
			sql = "update notice set title = ?, pname = ?, content = ?, no = no-1 , upfile = ?"
					+ " where id = ? and pw = ? ";
			
			ptmt = con.prepareStatement(sql);
			
			ptmt.setString(1, dto.getTitle());
			ptmt.setString(2, dto.getPname());
			ptmt.setString(3, dto.getContent());
			ptmt.setString(4, dto.getUpfile());
			ptmt.setInt(5, dto.getId());
			ptmt.setString(6, dto.getPw());
			
			res = ptmt.executeUpdate() > 0;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			
			close();
		}
		
		
		return res;

	}
	
	
	
	
	public void reply(NoticeDTO dto) {
		
		
		try {
			
			NoticeDTO ori = detail(dto);
			
			sql = "update notice set seq = seq +1 where gid = ? and seq > ?";
			
			ptmt = con.prepareStatement(sql);
			
			ptmt.setInt(1, ori.gid);
			ptmt.setInt(2, ori.seq);
			
			ptmt.executeUpdate();
			
			sql = "insert into notice "
			+ "(gid, seq, level, no, title, pname, pw, content, regdate ) values "
			+ "( ?,   ?,    ?  ,-1,   ? ,    ? ,    ? ,  ? ,   sysdate() )";
			
			ptmt = con.prepareStatement(sql);
			
			ptmt.setInt(1, ori.gid);
			ptmt.setInt(2, ori.seq+1);
			ptmt.setInt(3, ori.level+1);
			ptmt.setString(4, dto.getTitle());
			ptmt.setString(5, dto.getPname());
			ptmt.setString(6, dto.getPw());
			ptmt.setString(7, dto.getContent());
			
			ptmt.executeUpdate();
			
			
			sql = "select max(id) from notice";
			
			ptmt = con.prepareStatement(sql);
			
			rs = ptmt.executeQuery();
			
			rs.next();
			
			dto.setId(rs.getInt(1));
			
					
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			
			close();
		}

	}
	
	
	
	
	public void close() {
		if(rs!=null) try {rs.close();} catch (SQLException e) {	}
		if(ptmt!=null) try {ptmt.close();} catch (SQLException e) {	}
		if(con!=null) try {con.close();} catch (SQLException e) {	}
	}

}
