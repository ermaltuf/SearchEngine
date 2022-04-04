package com.project.searchMethod;



import com.project.command.ConstantCommand;
import com.project.daostrategy.QueryDaoInterface;
import com.project.daostrategy.QueryDaoInterfaceImpl;

import java.util.ArrayList;

public class CreateSearchMethod {

    //private static String result;

    public static String initSearchStrategy(ArrayList<String> tokenList) {
        String result = "";
        SearchType type;
        if (tokenList.size() == 1) {
            type = SearchType.SINGLE_TOKEN;
        } else if (tokenList.size() > 1) {
            type = SearchType.AGGREGATED_TOKEN;
        } else {
            type = SearchType.COMPLEX_SEARCH;
        }

        switch (type) {
            case SINGLE_TOKEN:
                QueryDaoInterface initSingleQuery = new QueryDaoInterfaceImpl();
                ArrayList<String> response = initSingleQuery.searchSingleToken(tokenList.get(0));
                if (!response.isEmpty()) {
                    //System.out.println(ConstantCommand.FOUND_RESULT + response);
                    result = ConstantCommand.FOUND_RESULT + response;
                } else {
                   // System.out.println(ConstantCommand.NO_RESULT);
                    result = ConstantCommand.NO_RESULT;
                }
                break;
            case AGGREGATED_TOKEN:

                QueryDaoInterface initAggregatedQuery = new QueryDaoInterfaceImpl();
                ArrayList<String> aggregatedResponse = initAggregatedQuery.searchAggregatedToken(tokenList);

                if (!aggregatedResponse.isEmpty()) {
                    System.out.println(ConstantCommand.FOUND_RESULT + aggregatedResponse);
                    result = ConstantCommand.FOUND_RESULT + aggregatedResponse;

                } else {
                    //System.out.println(ConstantCommand.NO_RESULT);
                    result = ConstantCommand.NO_RESULT;

                }
                break;
            case COMPLEX_SEARCH:
                System.out.println("High level");
                break;
        }
        return result;
    }
}
