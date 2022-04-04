package com.project.daostrategy;


import com.project.connectionDb.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class QueryDaoInterfaceImpl implements QueryDaoInterface {


    Connection connection = null;
    PreparedStatement ptmt = null;
    ResultSet resultSet = null;

    private final String searchQuery = "SELECT DOC_ID, TERMS, MATCH(TERMS) AGAINST ( ? ) as Relevance FROM document_id_tbl WHERE MATCH(TERMS) AGAINST ( ? )";
    // private final String aggregatedSearchQuery = "INSERT INTO document_id_tbl (DOC_ID, TERMS) VALUE(?, ?)";
    private final String findInQuery = "SELECT DOC_ID, TERMS FROM document_id_tbl WHERE TERMS like ?";

    public QueryDaoInterfaceImpl() {
    }


    private Connection getConnection() throws SQLException {
        Connection conn;
        conn = ConnectionFactory.getInstance().getConnection();
        return conn;
    }

    @Override
    public ArrayList<String> searchSingleToken(String query) {
        ArrayList<String> listId = new ArrayList<>();
        try {

            connection = getConnection();

            ptmt = connection.prepareStatement(searchQuery);
            ptmt.setString(1, query);
            ptmt.setString(2, query);
            resultSet = ptmt.executeQuery();
            while (resultSet.next()) {
                listId.add("Document id " + resultSet.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (ptmt != null)
                    ptmt.close();
                if (connection != null)
                    connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return listId;
    }

    @Override
    public ArrayList<String> searchAggregatedToken(ArrayList<String> searchList) {
        ArrayList<String> responseList = new ArrayList<>();
        String fullMatch = "";

        try {

            connection = getConnection();
            ptmt = connection.prepareStatement(searchQuery);

            for (String termWord: searchList) {
                fullMatch += termWord + " ";
            }
            ptmt.setString(1, fullMatch);
            ptmt.setString(2, fullMatch);
            resultSet = ptmt.executeQuery();
            while (resultSet.next()) {
                responseList.add("Document id " + resultSet.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (ptmt != null)
                    ptmt.close();
                if (connection != null)
                    connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return responseList;
    }

}
