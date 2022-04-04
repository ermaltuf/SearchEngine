package com.project.daostrategy;

import java.util.ArrayList;

public interface QueryDaoInterface {

    public ArrayList<String> searchSingleToken(String command);
    public ArrayList<String> searchAggregatedToken(ArrayList<String> searchList);
    //public void dropOldDocument(int index);
}
