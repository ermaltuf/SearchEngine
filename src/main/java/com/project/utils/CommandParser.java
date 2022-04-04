//package com.project.utils;
//
//
//
//import com.project.command.ConstantCommand;
//import com.project.command.IndexCommand;
//import com.project.command.QueryCommand;
//import com.project.daostrategy.DAOinterface;
//import com.project.daostrategy.DaoInterfaceImpl;
//import com.project.searchMethod.CreateSearchMethod;
//
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.HashSet;
//
//public class CommandParser {
//    private String command;
//
//
//    public String getCommand() {
//        return command;
//    }
//
//    public void setCommand(String command) {
//        this.command = command;
//    }
//
//    public static String commandParser(String com) {
//        String result = "";
//        String[] commandList = com.split(" ");
//        String index = commandList[0].toString();
//        if (index != null && index.equalsIgnoreCase(ConstantCommand.INDEX_COMMAND)) {
//            if (commandList.length < 2) {
//                System.out.println(ConstantCommand.EMPTY_DOC_ID);
//            } else {
//                try {
//                    int id = Integer.parseInt(commandList[1]);
//
//                    String[] tokenArray = Arrays.copyOfRange(commandList, 2, commandList.length);
//                    if (tokenArray.length > 0) {
//                        HashSet<String> tokenList = new HashSet<>(Arrays.asList(tokenArray));
//
//                        if (ValidationClass.validateID(id) && ValidationClass.validateTokenIndex(tokenList)) {
//
//                            IndexCommand indexClass = new IndexCommand();
//
//                            indexClass.setIndex(index);
//                            indexClass.setId(id);
//                            indexClass.setToken(tokenList);
//
//                            DAOinterface persistTokens = new DaoInterfaceImpl();
//                            if(persistTokens.checkAvailableIndex(id).equals(ConstantCommand.FOUND_INDEX)){
//                                persistTokens.dropOldDocument(id);
//                            }
//                            persistTokens.insertNewIndex(indexClass);
//                            System.out.println(indexClass.toString());
//                        } else {
//                            System.out.println(ConstantCommand.WRONG_FORMAT_ID);
//                        }
//                    } else {
//                        System.out.println(ConstantCommand.EXIT_COMMAND_EMPTY_ARRAY);
//                    }
//                } catch (NumberFormatException e) {
//                    System.out.println(e + " -- " + ConstantCommand.WRONG_FORMAT_ID);
//                }
//            }
//        } else if (index != null && index.equalsIgnoreCase(ConstantCommand.QUERY_COMMAND)) {
//
//            String[] tokenArray = Arrays.copyOfRange(commandList, 1, commandList.length);
//            if (tokenArray.length > 0) {
//                ArrayList<String> tokenList = new ArrayList<>(Arrays.asList(tokenArray));
//                if (ValidationClass.validateToken(tokenList)) {
//                    QueryCommand queryClass = new QueryCommand();
//                    queryClass.setQuery(index);
//                    queryClass.setToken(tokenList);
//                    result = CreateSearchMethod.initSearchStrategy(tokenList);
//
//                }else {
//                   // System.out.println(ConstantCommand.BLOCK_COMMAND);
//                    result = ConstantCommand.BLOCK_COMMAND;
//                }
//            } else {
//                //System.out.println(ConstantCommand.EXIT_COMMAND_EMPTY_ARRAY);
//                result = ConstantCommand.EXIT_COMMAND_EMPTY_ARRAY;
//            }
//
//        } else {
//            // System.out.println(ConstantCommand.EMPTY_COMMAND);
//            result = ConstantCommand.EMPTY_COMMAND;
//        }
//        return result;
//    }
//}
