package com.netcracker.denisik.rwstorage.rwstorageimpl;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class LoadFile {
    private static LoadFile instance;
    private Properties properties;

    private LoadFile() throws IOException {
        properties = new Properties();
        properties.load(new FileReader("dao/src/main/resources/config.properties"));
    }

    public static LoadFile getInstance() throws IOException {
        if (instance == null) {
            instance = new LoadFile();
        }
        return instance;
    }

    public Properties getProperties() {
        return properties;
    }
}
