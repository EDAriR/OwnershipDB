package com.syntrontech.ownership;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CIPOwnershipJDBC {

    private static Logger logger = LoggerFactory.getLogger(CIPOwnershipJDBC.class);

    private static final String GET_ALL_STMT = "select * from ownership order by sequence;";
    private static final String INSERT_STMT = "INSERT INTO ownership "
            + "(sequence, myId, userId, userName, type, ownershipStatus,"
            + " modelStatus, tenantId, createTime, updateTime) "
            + "VALUES(nextval('ownership_sequence_seq'), ?, ?, ?, ?, ?,"
            + "?, ?, ?, ?)";


    public List<StringBuilder> getAll() throws SQLException {


        Connection conn = new CIP_GET_CONNECTION().getConn();
        PreparedStatement pstmt = null;
        StringBuilder comma = new StringBuilder(",");
        StringBuilder rightparenthesis = new StringBuilder(")");

        List<StringBuilder> stringBuilders = new ArrayList<>();
        try {
            pstmt = conn.prepareStatement(GET_ALL_STMT);

            logger.info(pstmt.toString());

            ResultSet rs = pstmt.executeQuery();

            if (rs != null) {
                while (rs.next()) {
                    StringBuilder nextownership = new StringBuilder("(nextval('ownership_sequence_seq'),");

                	String my_id = ifNullString(rs.getString("my_id"));
                    nextownership.append(my_id).append(comma);

                    String user_id = ifNullString(rs.getString("user_id"));
                    nextownership.append(user_id).append(comma);

                    String user_name = ifNullString(rs.getString("user_name"));
                    nextownership.append(user_name).append(comma);

                    String ownership_type = ifNullString(rs.getString("ownership_type"));
                    nextownership.append(ownership_type).append(comma);

                    String ownership_status = ifNullString(rs.getString("ownership_status"));
                    nextownership.append(ownership_status).append(comma);

                    String model_status = ifNullString(rs.getString("model_status"));
                    nextownership.append(model_status).append(comma);

                    String tenant_id = ifNullString(rs.getString("tenant_id"));
                    nextownership.append(tenant_id).append(comma);

                    String create_time = ifNullString(rs.getString("create_time"));
                    nextownership.append(create_time).append(comma);

                    String update_time = ifNullString(rs.getString("update_time"));
                    nextownership.append(update_time).append(rightparenthesis);

                    stringBuilders.add(nextownership);
                }
            }

        } catch (SQLException e) {
            logger.info("CIPOwnershipJDBC getAll fail " + pstmt);
        } finally {
            try {
                if (pstmt != null)
                    pstmt.close();
                conn.close();
            } catch (SQLException e) {
                logger.info("conn or pstmt close fail " + pstmt);
            }
        }

        return stringBuilders;
    }

    private String ifNullString(String str) {
        return str == null? "''":str;
    }

}
