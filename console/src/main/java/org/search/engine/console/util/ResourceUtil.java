package org.search.engine.console.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class ResourceUtil {

  public static Map<String, Properties> propertiesMap;

  static {

    try {
      propertiesMap = loadPropertiesMap();
    } catch (Exception e) {
      e.printStackTrace();
      System.exit(-1);
    }
  }

  private static Map<String, Properties> loadPropertiesMap() throws Exception {
    Properties applicationProps = loadProperties("application.properties");
    String textsFilename =
        "texts." + applicationProps.getProperty("texts.language") + ".properties";
    Properties textsProps = loadProperties(textsFilename);
    Map<String, Properties> map = new HashMap<>();
    map.put("app", applicationProps);
    map.put("texts", textsProps);
    return map;
  }


  private static Properties loadProperties(String filename) throws Exception {
    BufferedReader reader = new BufferedReader(new InputStreamReader(getInputStream(filename)));
    Properties properties = new Properties();
    properties.load(reader);
    reader.close();
    return properties;
  }


  private static InputStream getInputStream(String fileName) {
    return ClassLoader.getSystemResourceAsStream(fileName);
  }

  public static Properties getApplicationProperties() {
    return propertiesMap.get("app");
  }

  public static Properties getTextsProperties() {
    return propertiesMap.get("texts");
  }

  public static Map<String, Properties> getPropertiesMap() {
    return propertiesMap;
  }

}