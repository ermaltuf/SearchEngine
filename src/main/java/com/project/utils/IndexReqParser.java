package com.project.utils;

import com.project.command.ConstantCommand;
import com.project.command.IndexCommand;
import com.project.daostrategy.DAOinterface;
import com.project.daostrategy.DaoInterfaceImpl;

import java.util.Arrays;
import java.util.HashSet;

public class IndexReqParser {

    public static String commandParser(String command) {

        String result = "", delimiter = " ";
        String[] commandList, tokenArray;

        commandList = command.split(delimiter);
        String index = commandList[0];

        if (commandList.length < 2) {
            result = ConstantCommand.EMPTY_DOC_ID;
            return result;
        }
        tokenArray = Arrays.copyOfRange(commandList, 2, commandList.length);

        if (tokenArray.length == 0) {
            result = ConstantCommand.EXIT_COMMAND_EMPTY_ARRAY;
            return result;
        }
        try {
            int id = Integer.parseInt(commandList[1]);
            HashSet<String> tokenList = new HashSet<>(Arrays.asList(tokenArray));

            if (!ValidationClass.validateID(id) || !ValidationClass.validateTokenIndex(tokenList)) {
                result = ConstantCommand.WRONG_FORMAT_IDorTOKEN;
                return result;
            }
            IndexCommand indexClass = new IndexCommand();

            indexClass.setIndex(index);
            indexClass.setId(id);
            indexClass.setToken(tokenList);

            DAOinterface persistTokens = new DaoInterfaceImpl();
            if (persistTokens.checkAvailableIndex(id)) {
                persistTokens.dropOldDocument(id);
            }
            result = persistTokens.insertNewIndex(indexClass);
            return result;

        } catch (NumberFormatException e) {
            result = ConstantCommand.WRONG_FORMAT_INDEXID;
            return result;
        }
    }
}
