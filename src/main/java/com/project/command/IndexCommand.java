package com.project.command;

import java.util.HashSet;

public class IndexCommand {


    private String index;
    private int id;
    private HashSet<String> token;

    public IndexCommand() {
    }

    public IndexCommand(String index, int id, HashSet<String> token) {
        this.index = index;
        this.id = id;
        this.token = token;
    }


    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public HashSet<String> getToken() {
        return token;
    }

    public void setToken(HashSet<String> token) {
        this.token = token;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Index Command{" +
                "index='" + index + '\'' +
                ", id=" + id +
                ", token=" + token +
                '}';
    }


}
