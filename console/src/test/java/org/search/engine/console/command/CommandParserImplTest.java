package org.search.engine.console.command;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.search.engine.console.exception.CommandParseException;

class CommandParserImplTest {

  static CommandParser commandParser;

  @BeforeAll
  static void setup() {
    commandParser = new CommandParserImpl();
  }

  @AfterAll
  static void tear() {
  }


  @BeforeEach
  void setUp() {
  }

  @AfterEach
  void tearDown() {
  }


  @Test
  void testParseIndex() {
    String input0 = "index 1 banana apple orange";
    String input1 = "index 1 banana apple.orange";
    String input2 = "index 1";
    String input3 = "ind$x 1";

    Command command0 = commandParser.parse(input0);
    assertNotNull(command0, "Input parsed is null");

    Command command1 = commandParser.parse(input1);
    assertNotNull(command1, "Input parsed is null");

    CommandParseException cpe0 = assertThrows(CommandParseException.class,
        () -> commandParser.parse(input2));
    assertTrue(cpe0.getMessage().contains("Can't parse input"));

    CommandParseException cpe1 = assertThrows(CommandParseException.class,
        () -> commandParser.parse(input3));
    assertTrue(cpe1.getMessage().contains("Can't parse unknown input"));

  }

  @Test
  void testParseQuery() {
    String input0 = "query butter";
    String input1 = "query (butter | milk)";
    String input2 = "query (butter | potato) & salt";
    String input3 = "query (butter | potato) & (salt | sugar)";

    String input4 = "qu3ry (butt%r | potato) & (s$lt | su$-ar)";
    String input5 = "query (butt%r | potato) & (s$lt | su$-ar)";
    String input6 = "query (butter a potato) & (salt | sugar)";
    String input7 = "query butter | milk";

    Command command0 = commandParser.parse(input0);
    assertNotNull(command0, "Input parsed is null");

    Command command1 = commandParser.parse(input1);
    assertNotNull(command1, "Input parsed is null");

    Command command2 = commandParser.parse(input2);
    assertNotNull(command2, "Input parsed is null");

    Command command3 = commandParser.parse(input3);
    assertNotNull(command3, "Input parsed is null");

    CommandParseException cpe1 = assertThrows(CommandParseException.class,
        () -> commandParser.parse(input4));
    assertTrue(cpe1.getMessage().contains("Can't parse unknown input"));

    CommandParseException cpe2 = assertThrows(CommandParseException.class,
        () -> commandParser.parse(input5));
    assertTrue(cpe2.getMessage().contains("Can't parse input"));

    CommandParseException cpe3 = assertThrows(CommandParseException.class,
        () -> commandParser.parse(input6));
    assertTrue(cpe3.getMessage().contains("Can't parse input"));

    CommandParseException cpe4 = assertThrows(CommandParseException.class,
        () -> commandParser.parse(input7));
    assertTrue(cpe4.getMessage().contains("Can't parse input"));
  }


}