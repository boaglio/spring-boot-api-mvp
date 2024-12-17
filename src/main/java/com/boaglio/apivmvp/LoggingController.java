package com.boaglio.apivmvp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.logging.LogLevel;
import org.springframework.boot.logging.LoggingSystem;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class LoggingController {

    @Autowired
    private LoggingSystem loggingSystem;

    @PostMapping("/trace")
    public void setLogLevelTrace() {
        loggingSystem.setLogLevel("com.boaglio",LogLevel.TRACE);
        logger.info("TRACE active");
        testLogs();
    }

    private static final Logger logger = LoggerFactory.getLogger(LoggingController.class);

    @GetMapping("/test")
    public String getTest() {
        testLogs();
        return "Ok";
    }

    public void testLogs() {
        logger.error("This is an ERROR level log message!");
        logger.warn("This is a WARN level log message!");
        logger.info("This is an INFO level log message!");
        logger.debug("This is a DEBUG level log message!");
        logger.trace("This is a TRACE level log message!");
    }

}
