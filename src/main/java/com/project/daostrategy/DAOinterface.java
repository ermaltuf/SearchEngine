package com.project.daostrategy;

import com.project.command.IndexCommand;

public interface DAOinterface {

    public String insertNewIndex(IndexCommand command);
    public boolean checkAvailableIndex(int index);
    public void dropOldDocument(int index);
    //public void insertNewDocumentId(IndexCommand command);
    // public void controllRepetitiveTerms (CommandParser command);
    // public void checkQuery (CommandParser command);
}
