package com.example.Lab4;

@RestController
@RequestMapping("/api")
public class SecondController {

    @GetMapping("/unchecked-exception")
    public void throwUncheckedException() {
        throw new NullPointerException("NullPointerException occurred");
    }
}

