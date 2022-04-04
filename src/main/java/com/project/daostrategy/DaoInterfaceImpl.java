package com.project.daostrategy;



import com.project.command.IndexCommand;
import com.project.connectionDb.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;

public class DaoInterfaceImpl implements DAOinterface {

    Connection connection = null;
    PreparedStatement ptmt = null;
    ResultSet resultSet = null;
    private final String createIndexQry = "INSERT INTO terms_table (TOKEN) VALUE(?)";
    private final String insertTermsListQry = "INSERT INTO document_id_tbl (DOC_ID, TERMS) VALUE(?, ?)";
    private final String checkAvailableIndexQry = "SELECT DOC_ID FROM document_id_tbl WHERE DOC_ID = ?";
    private final String deleteAvailableIndexQry = "DELETE FROM document_id_tbl WHERE DOC_ID = ?";
    private final String DATA_INSERT_SUCCESS = "Data Added Successfully" ;
    private final String ERROR_DATA_INSERT = "Something went wrong while inserting the data. Try again" ;
    private final String DELETED_SUCCESS = "The row was successfully deleted";
    private final String DELETED_NOT_SUCCESS = "The delete process was blocked";
    public DaoInterfaceImpl() {
    }


    private Connection getConnection() throws SQLException {
        Connection conn;
        conn = ConnectionFactory.getInstance().getConnection();
        return conn;
    }

    @Override
    public String insertNewIndex(IndexCommand command) {
       String insertResult = "", fullText = "";
       HashSet<String> tokenArray = command.getToken();
       int documentId;


       documentId = command.getId();
        try {
            connection = getConnection();

            ptmt = connection.prepareStatement(insertTermsListQry);
            for (String termWord: tokenArray) {
                fullText += termWord + " ";
            }
            ptmt.setInt(1, documentId);
            ptmt.setString(2, fullText);
            ptmt.executeUpdate();
            insertResult = DATA_INSERT_SUCCESS;
        } catch (SQLException e) {
            insertResult = ERROR_DATA_INSERT;
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
        return insertResult;
    }

    @Override
    public boolean checkAvailableIndex(int index) {
        boolean response = false;
        int resultLines = 0;
        try {
            connection = getConnection();

            ptmt = connection.prepareStatement(checkAvailableIndexQry);
            ptmt.setInt(1, index);
            resultSet = ptmt.executeQuery();
            while (resultSet.next()) {
                resultLines++;
            }
            if (resultLines > 0) {
                response =  true;
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

        return response;
    }

    @Override
    public void dropOldDocument(int index){
        int deteledRow;
        try {
            connection = getConnection();

            ptmt = connection.prepareStatement(deleteAvailableIndexQry);
            ptmt.setInt(1, index);
            deteledRow = ptmt.executeUpdate();
            if(deteledRow > 0) {
                System.out.println(DELETED_SUCCESS);
            }
            else {
                System.out.println(DELETED_NOT_SUCCESS);
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
    }

}

