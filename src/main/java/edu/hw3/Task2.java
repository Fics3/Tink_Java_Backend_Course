package edu.hw3;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Stack;

final class Task2 {
    private static final Map<Character, Character> POSSIBLE_BRACKETS = Map.of('(', ')',
        '[', ']',
        '{', '}'
    );

    private Task2() {

    }

    public static List<String> clusterize(String message) {
        List<String> resultClusters = new ArrayList<>();
        Stack<Character> bracketsCount = new Stack<>();
        int clusterStart = 0;
        for (int i = 0; i < message.length(); i++) {
            char curBracket = message.charAt(i);

            if (POSSIBLE_BRACKETS.containsKey(curBracket)) {
                bracketsCount.push(curBracket);
            } else if (POSSIBLE_BRACKETS.containsValue(curBracket)) {

                if (!bracketsCount.isEmpty()) {
                    char openBracket = bracketsCount.pop();

                    if (isMatching(openBracket, curBracket) && bracketsCount.isEmpty()) {
                        resultClusters.add(message.substring(clusterStart, i + 1));
                        clusterStart = i + 1;

                    } else if (!isMatching(openBracket, curBracket)) {
                        bracketsCount.clear();
                        clusterStart = i + 1;
                    }
                } else {
                    clusterStart = i + 1;
                }
            }
        }
        return resultClusters;
    }

    private static boolean isMatching(char openBracket, char curCloseBracket) {
        return Objects.equals(POSSIBLE_BRACKETS.get(openBracket).charValue(), curCloseBracket);
    }

}
