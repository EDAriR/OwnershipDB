package com.syntrontech.ownership;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class FriendOwnershipJDBC {

	private static Logger logger = LoggerFactory.getLogger(FriendOwnershipJDBC.class);

	private String INSERT  = "INSERT INTO ownership "
			+ "(sequence, myId, userId, userName, type, ownershipStatus,"
			+ " modelStatus, tenantId, createTime, updateTime) VALUES";
	
    // myId, userId, userName, type, ownershipStatus,
	// modelStatus, tenantId, createTime, updateTime

	public void insert(StringBuilder values){

		Connection conn = new FRIEND_GET_CONNECTION().getConn();

		PreparedStatement pstmt = null;

		StringBuilder insert = new StringBuilder(INSERT);

		try {
			pstmt = conn.prepareStatement(insert.toString());

			logger.info(pstmt.toString());

			int rs = pstmt.executeUpdate();
		} catch (SQLException e) {
			logger.info("FriendOwnershipJDBC insert fail " + pstmt);
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				conn.close();
			} catch (SQLException e) {
				logger.info("conn or pstmt close fail " + pstmt);
			}
		}
	}

}
