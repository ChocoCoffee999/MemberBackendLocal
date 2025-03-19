package edu.ssafy.repository;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import edu.ssafy.dao.MemberDao;
import edu.ssafy.dto.MemberDto;
import edu.ssafy.util.DBUtil;

public class MemberRepositoryImpl implements MemberRepository{
	private MemberDao memberDao = new MemberDao();
	@Override
	public int MemberInsert(MemberDto m) throws Exception {
		Connection conn = DBUtil.getConnection();
		int ret = 0;
		try {
			conn.setAutoCommit(false);
			ret = memberDao.insertMember(conn, m);
			conn.commit();
		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
			throw e;
		} finally {
			DBUtil.close(conn);
		}
		return ret;
	}

	@Override
	public int MemberUpdate(MemberDto m) throws SQLException {
		Connection conn = DBUtil.getConnection();
		int ret = 0;
		try {
			conn.setAutoCommit(false);
			ret = memberDao.updateMember(conn, m);
			conn.commit();
		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
			throw e;
		} finally {
			DBUtil.close(conn);
		}
		return ret;
	}

	@Override
	public int MemberDelete(String id) throws SQLException {
		Connection conn = DBUtil.getConnection();
		int ret = 0;
		try {
			conn.setAutoCommit(false);
			ret = memberDao.deleteMember(conn, id);
			conn.commit();
		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
			throw e;
		} finally {
			DBUtil.close(conn);
		}
		return ret;
	}

	@Override
	public List<MemberDto> MemberSelectAll() throws SQLException {
		Connection conn = DBUtil.getConnection();
		List<MemberDto> retList = null;
		try {
			conn.setAutoCommit(false);
			retList = memberDao.getAllMembers(conn);
			conn.commit();
		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
			throw e;
		} finally {
			DBUtil.close(conn);
		}
		return retList;
	}

	@Override
	public MemberDto MemberSelect(String id) throws SQLException {
		Connection conn = DBUtil.getConnection();
		MemberDto ret = null;
		try {
			conn.setAutoCommit(false);
			ret = memberDao.getMemberById(conn, id);
			conn.commit();
		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
			throw e;
		} finally {
			DBUtil.close(conn);
		}
		return ret;
	}

}
