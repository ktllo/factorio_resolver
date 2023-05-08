package org.leolo.factorio.resolver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MainApp {
    Logger log = LoggerFactory.getLogger(MainApp.class);
    public static void main(String[] args) {
        new MainApp().run();
    }

    private void run() {
        ConfigurationManager.getInstance();
    }
}
