package org.leolo.factorio.resolver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.Properties;
import java.util.Set;
import java.util.function.BiConsumer;

public class ConfigurationManager {

    private static ConfigurationManager instance;
    private Logger log = LoggerFactory.getLogger(ConfigurationManager.class);
    private Properties properties;
    private File factorioRoot;
    private File outputPath;

    public static synchronized ConfigurationManager getInstance(){
        if(instance == null){
            instance = new ConfigurationManager();
        }
        return instance;
    }

    private  ConfigurationManager(){
        log.info("ConfigurationManager created.");
        InputStream is = getClass().getClassLoader().getResourceAsStream("config.properties");
        properties = new Properties();
        if(is==null){
            log.error("Config file cannot be located.");
            return;
        }
        try {
            properties.load(is);
        } catch (IOException e) {
            log.error("Unable to load config file", e);
        }
        log.info("{} properties loaded.", properties.size());
        if(properties.containsKey(Constants.ConfigurationKey.FACTORIO_ROOT)){
            factorioRoot = new File(properties.getProperty(Constants.ConfigurationKey.FACTORIO_ROOT));
            log.info("Factorio root is {}", factorioRoot.getAbsolutePath());
        }
        if(properties.containsKey(Constants.ConfigurationKey.OUTPUT_PATH)){
            outputPath = new File(properties.getProperty(Constants.ConfigurationKey.OUTPUT_PATH));
            log.info("Output path is {}", outputPath.getAbsolutePath());
        }
    }

    public int size() {
        return properties.size();
    }

    public Enumeration<Object> keys() {
        return properties.keys();
    }

    public boolean containsKey(Object key) {
        return properties.containsKey(key);
    }

    public Object get(Object key) {
        return properties.get(key);
    }

    public Set<Object> keySet() {
        return properties.keySet();
    }

    public void forEach(BiConsumer<? super Object, ? super Object> action) {
        properties.forEach(action);
    }

    public File getFactorioRoot() {
        return factorioRoot;
    }
}
