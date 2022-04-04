package com.project.utils;

import com.project.command.ConstantCommand;
import com.project.command.QueryCommand;
import com.project.searchMethod.CreateSearchMethod;

import java.util.ArrayList;
import java.util.Arrays;

public class QueryReqParser {

    public static String commandSearchParser(String command) {
        String result = "", delimiter = " ", index = "";
        String[] commandList;
        commandList = command.split(delimiter);
        index = commandList[0];
        if (!index.equalsIgnoreCase("query")) {
            result = ConstantCommand.EMPTY_COMMAND;
            return result;
        }
        String[] tokenArray = Arrays.copyOfRange(commandList, 1, commandList.length);
        if (tokenArray.length == 0) {
            result = ConstantCommand.EXIT_COMMAND_EMPTY_ARRAY;
            return result;
        }
        ArrayList<String> tokenList = new ArrayList<>(Arrays.asList(tokenArray));
        if (!ValidationClass.validateToken(tokenList)) {
            result = ConstantCommand.BLOCK_COMMAND;
            return result;
        }
        QueryCommand queryClass = new QueryCommand();
        queryClass.setQuery(index);
        queryClass.setToken(tokenList);
        result = CreateSearchMethod.initSearchStrategy(tokenList);
        return result;
    }

}
