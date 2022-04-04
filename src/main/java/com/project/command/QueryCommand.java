package com.project.command;

import java.util.ArrayList;

public class QueryCommand {


    private String query;
    private ArrayList<String> token;

    public QueryCommand() {
    }

    public QueryCommand(String query, ArrayList<String> token) {
        this.query = query;
        this.token = token;
    }


    public String getQuery() {
        return query;
    }

    public void setQuery(String index) {
        this.query = query;
    }

    public ArrayList<String> getToken() {
        return token;
    }

    public void setToken(ArrayList<String> token) {
        this.token = token;
    }



    @Override
    public String toString() {
        return "Index Command{" +
                "index='" + query + '\'' +
                ", token=" + token +
                '}';
    }

}

