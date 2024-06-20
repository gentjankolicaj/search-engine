package org.search.engine.console.command;

import java.util.ArrayList;
import java.util.List;
import org.search.engine.console.exception.CommandParseException;
import org.search.engine.console.exception.IndexParseException;
import org.search.engine.console.exception.QueryParseException;

public class CommandParserImpl implements CommandParser {

  private final static String alphaNumericRegex = "^[a-zA-Z0-9.]+$";


  @Override
  public Command parse(String input, String regex) throws CommandParseException {
    return null;
  }

  @Override
  public Command parse(String input) throws CommandParseException {
    if (input == null || input.length() == 0) {
      throw new CommandParseException("Can't parse null or size=0 input");
    }

    List<String> inputList = splitInput(input);
    if (inputList == null || inputList.size() == 0) {
      throw new CommandParseException("Can't parse null or size=0 input arguments");
    }

    if (isIndex(inputList.get(0))) {
      if (inputList.size() <= 2) {
        throw new IndexParseException(
            "Can't parse input.Command 'index' must have at least 'index_value' and token.Example : index 1 apple");
      }
      if (!isInteger(inputList.get(1))) {
        throw new IndexParseException(
            "Can't parse input.'" + inputList.get(1) + "' not valid as index value.");
      }
      for (int i = 2; i < inputList.size(); i++) {
        String s = inputList.get(i);
        if (!isAlphaNumeric(s)) {
          throw new IndexParseException(
              "Can't parse input." + s + " contains non-alpha numeric characters");
        }
      }
      return new ConsoleCommand(1, inputList);
    } else if (isQuery(inputList.get(0))) {
      if (inputList.size() <= 1) {
        throw new QueryParseException(
            "Can't parse input.Command 'query' must have at least 'query' and token.Example : query apple");
      }
      //Query with single token
      if (inputList.size() != 2) {
        int tokenCounter = 0;
        int prefixParenthesisCounter = 0;
        int suffixParenthesisCounter = 0;
        int operationCounter = 0;
        String prefixParenthesis = "(";
        String suffixParenthesis = ")";
        String andOperation = "&";
        String orOperation = "|";
        for (int i = 1; i < inputList.size(); i++) {
          String s = inputList.get(i);
          if (s.contains(prefixParenthesis)) {
            prefixParenthesisCounter++;
          } else if (s.contains(suffixParenthesis)) {
            suffixParenthesisCounter++;
          } else if (s.contains(andOperation) || s.contains(orOperation)) {
            operationCounter++;
          } else {
            if (isAlphaNumeric(s)) {
              tokenCounter++;
            } else {
              throw new QueryParseException(
                  "Can't parse input.'" + s + "' contains non alpha-numeric characters");
            }
          }
        }
        //tokenCounter counts total alphanumeric tokens
        //prefixParenthesisCounter must be tokenCounter/2
        //suffixParenthesisCounter must be tokenCounter/2
        //setOperationCounter must be tokenCounter-1

        //Check for ( total number and  if is in right index
        //Check for ) total number and if is in right index
        validateParenthesis(prefixParenthesisCounter, suffixParenthesisCounter, tokenCounter,
            inputList.subList(1, inputList.size()));

        //Check for &/| total number and if are in right index
        validateSetOperations(operationCounter, tokenCounter,
            inputList.subList(1, inputList.size()));

      }
      return new ConsoleCommand(2, inputList);
    } else {
      throw new CommandParseException("Can't parse unknown input " + inputList.get(0));
    }

  }


  private void validateParenthesis(int prefixParenthesisCounter, int suffixParenthesisCounter,
      int tokenCounter, List<String> inputList) {
    if (prefixParenthesisCounter != (tokenCounter / 2)) {
      throw new QueryParseException(
          "Can't parse input.Command 'query' must have a total of " + (tokenCounter / 2)
              + " '(' not " + prefixParenthesisCounter);
    }
    if (suffixParenthesisCounter != (tokenCounter / 2)) {
      throw new QueryParseException(
          "Can't parse input.Command 'query' must have a total of " + (tokenCounter / 2)
              + " ')' not " + suffixParenthesisCounter);
    } else {
      //Prefix parenthesis index rule : i*6
      //Suffix parenthesis index rule : i*6+4
      String prefixParenthesis = "(";
      String suffixParenthesis = ")";
      int maxParenthesis = tokenCounter / 2;
      for (int i = 0; i < maxParenthesis; i++) {
        int prefixParenthesisIndex = i * 6;
        int suffixParenthesisIndex = i * 6 + 4;
        String s1 = inputList.get(prefixParenthesisIndex);
        String s2 = inputList.get(suffixParenthesisIndex);
        if (!s1.equals(prefixParenthesis)) {
          throw new QueryParseException(
              "Can't parse input.Command 'query' must have ( at index " + prefixParenthesisIndex);
        }
        if (!s2.equals(suffixParenthesis)) {
          throw new QueryParseException(
              "Can't parse input.Command 'query' must have ) at index " + suffixParenthesisIndex);
        }
      }
    }
  }


  private void validateSetOperations(int operationCounter, int tokenCounter,
      List<String> inputList) {
    if (operationCounter != (tokenCounter - 1)) {
      throw new QueryParseException(
          "Can't parse input.Command 'query' must have a total of " + (tokenCounter - 1)
              + " '&' or '|' operations  not " + operationCounter);
    } else {
      //Operations index rule : 2*(i+i)+i
      String andOperation = "&";
      String orOperation = "|";
      int maxOperation = tokenCounter - 1;
      for (int i = 0; i < maxOperation; i++) {
        int operationIndex = 2 * (i + 1) + i;
        String s = inputList.get(operationIndex);
        if (!s.equals(andOperation) && !s.equals(orOperation)) {
          throw new QueryParseException(
              "Can't parse input.Command 'query' must have '&' or '|' at index " + operationIndex);
        }
      }
    }
  }

  boolean isIndex(String s) {
    return s != null && s.equals("index");
  }

  boolean isQuery(String s) {
    return s != null && s.equals("query");
  }

  boolean isInteger(String str) {
    try {
      Integer.parseInt(str);
      return true;
    } catch (Exception e) {
      return false;
    }
  }

  boolean isAlphaNumeric(String s) {
    return s != null && s.matches(alphaNumericRegex);
  }

  List<String> splitInput(String s) {
    List<String> wordList = new ArrayList<>();
    char whitespace = ' ';
    char prefixParenthesis = '(';
    char suffixParenthesis = ')';
    char andOperation = '&';
    char orOperation = '|';
    String tmpWord = "";
    for (int i = 0; i < s.length(); i++) {
      char tmpChar = s.charAt(i);
      if (whitespace == tmpChar && tmpWord.length() > 0) {
        wordList.add(tmpWord);
        tmpWord = "";
      } else if (prefixParenthesis == tmpChar || suffixParenthesis == tmpChar
          || andOperation == tmpChar || orOperation == tmpChar) {
        if (tmpWord.length() > 0) {
          wordList.add(tmpWord);
          wordList.add("" + tmpChar);
          tmpWord = "";
        } else {
          wordList.add("" + tmpChar);
        }
      } else if (whitespace != tmpChar) {
        tmpWord = tmpWord + tmpChar;
      }
    }
    if (tmpWord.length() > 0) {
      wordList.add(tmpWord);
    }
    return wordList;
  }


}
