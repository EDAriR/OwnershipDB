package com.syntrontech.ownership;

import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.error.YAMLException;

import java.io.*;
import java.util.LinkedHashMap;
import java.util.Map;

public class YAMLReader {

    public static String getSetting(String key1, String key2) throws YAMLException {
        System.out.println("YAMLReader ==> key1:" + key1 + ", key2:" + key2);
        return getSetting(key1).get(key2);
    }


    @SuppressWarnings("unchecked")
	public static Map<String, String> getSetting(String key) throws YAMLException {

        Yaml yaml = new Yaml();

        InputStream in = null;
        try {

            System.out.println("dir : " + System.getProperty("user.dir"));

            String dir = System.getProperty("user.dir");

            in = new FileInputStream(dir + "/setting.yml");

            System.out.println("get setting successful");

        } catch (FileNotFoundException e) {
            in = YAMLReader.class.getResourceAsStream("/setting.yml");
            System.out.println("get setting fail use defautl setting successful");

        } catch (YAMLException e) {
            in = YAMLReader.class.getResourceAsStream("/setting.yml");
        }catch (Exception ex){
            ex.printStackTrace();
        }

        Map<String, Map<String, String>> obj = (LinkedHashMap<String, Map<String, String>>) yaml.load(in);

        return obj.get(key);

    }

}
