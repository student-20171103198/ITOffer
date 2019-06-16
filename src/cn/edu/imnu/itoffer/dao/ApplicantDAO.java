package cn.edu.imnu.itoffer.dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;

import cn.edu.imnu.itoffer.until.DBUtil;

public class ApplicantDAO {

	public boolean isExistEmail(String email) {
		String sql = "select * from tb_applicant where applicant_email = ?";
		
		Connection conn = DBUtil.getConnection();
		PreparedStatement pstmt = null;
		ResultSet result = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, email);
			result = pstmt.executeQuery();
			if(result.next()) {
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.closeJDBC(result, pstmt, conn);
		}
		
		return false;
	}

	public void save(String email, String password) {
		String sql = "insert into tb_applicant(applicant_email,applicant_pwd,applicant_registdate) value(?,?,?)";
		Connection conn = DBUtil.getConnection();
		PreparedStatement pstmt = null;
		try {
			 pstmt = conn.prepareStatement(sql);
			 pstmt.setString(1, email);
			 pstmt.setString(2, password);
			 pstmt.setTimestamp(3, new Timestamp(new Date().getTime()));
			 pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.closeJDBC(null, pstmt, conn);
		}
		
	}

	public int login(String email, String password) {
		String sql = "select * from tb_applicant where applicant_email = ? and applicant_pwd = ?";
		int applicantID = 0;
		Connection conn = DBUtil.getConnection();
		PreparedStatement pstmt = null;
		ResultSet result = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, email);
			pstmt.setString(2, password);
			result = pstmt.executeQuery();
			if(result.next()) {
				applicantID = result.getInt("applicant_id");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.closeJDBC(result, pstmt, conn);
		}
		return applicantID;
	}

	public int isExistResume(int applicantID) {
		String sql = "select * from tb_resume_basicinfo where applicant_id=?";
		int resumeID = 0;
		Connection conn = DBUtil.getConnection();
		PreparedStatement pstmt = null;
		ResultSet result = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, applicantID);
			result = pstmt.executeQuery();
			if(result.next()) {
				resumeID = result.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.closeJDBC(result, pstmt, conn);
		}
		return resumeID;
	}


}
