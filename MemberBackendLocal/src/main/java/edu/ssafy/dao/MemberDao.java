package edu.ssafy.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.ssafy.dto.MemberDto;

public class MemberDao {
	public int insertMember(Connection conn, MemberDto member) throws SQLException {
		String sql = "insert into member(id, pw, name) " + "values(?,?,?)";
		try (PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setString(1, member.getId());
			stmt.setString(2, member.getPw());
			stmt.setString(3, member.getName());
			int check = stmt.executeUpdate();
			return check;
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		return 0;
	}

	// @Todo : 상품 정보 조회
	public MemberDto getMemberById(Connection conn, String memberId) throws SQLException {
		String sql = "select * from member where id = ?";
		try (PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setString(1, memberId);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				return new MemberDto(rs.getString("id"), rs.getString("pw"), rs.getString("name"));
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// @Todo : 상품 정보 수정
	public int updateMember(Connection conn, MemberDto member) throws SQLException {
		String sql = "update member set id = ?, pw = ?, name = ? where id = ?";
		try (PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setString(1, member.getId());
			stmt.setString(2, member.getPw());
			stmt.setString(3, member.getName());
			stmt.setString(4, member.getId());
			int check = stmt.executeUpdate();
			return check;
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		return 0;
	}

	public int deleteMember(Connection conn, String memberId) throws SQLException {
		String sql = "delete from member where id = ?";
		try (PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setString(1, memberId);
			int check = stmt.executeUpdate();
			return check;
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	public List<MemberDto> getAllMembers(Connection conn) throws SQLException {
		List<MemberDto> memberList = new ArrayList<>();
		String sql = "select * from member";
		try (PreparedStatement stmt = conn.prepareStatement(sql)) {
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				memberList.add(new MemberDto(rs.getString("id"), rs.getString("pw"), rs.getString("name")));
			};
			return memberList;
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return memberList;
	}
}
