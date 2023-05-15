package com.example.Lab1;

public enum DocType {
    VACATION_REQUEST("Заява на відпустку"),
    JOB_APPLICATION("Заява на приймання до роботи");

    private final String name;

    DocType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
