package com.example.Lab4;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class FirstController {

    @GetMapping("/checked-exception")
    public void throwCheckedException(@RequestParam("exception-type") String exceptionType) throws Exception {
        switch (exceptionType) {
            case "IOException":
                throw new IOException("IOException occurred");
            case "SQLException":
                throw new SQLException("SQLException occurred");
            case "ClassNotFoundException":
                throw new ClassNotFoundException("ClassNotFoundException occurred");
            case "ParseException":
                throw new ParseException("ParseException occurred", 0);
            case "InterruptedException":
                throw new InterruptedException("InterruptedException occurred");
            default:
                throw new IllegalArgumentException("Invalid exception type");
        }
    }
}

