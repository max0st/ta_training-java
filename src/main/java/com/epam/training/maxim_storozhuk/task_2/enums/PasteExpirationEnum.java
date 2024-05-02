package com.epam.training.maxim_storozhuk.task_2.enums;

public enum PasteExpirationEnum {
    NEVER("//li[text()='NEVER']"),
    TEN_MINUTES("//li[text()='10 Minutes']"),
    ONE_HOUR("//li[text()='1 Hour']"),
    ONE_DAY("//li[text()='1 Day']"),
    ONE_WEEK("//li[text()='1 Week']"),
    ONE_MONTH("//li[text()='1 Month']"),
    ONE_YEAR("//li[text()='1 Year']");

    private final String expirationSelector;

    PasteExpirationEnum(String xpath) {
        this.expirationSelector = xpath;
    }
    public String getExpirationSelector() {
        return expirationSelector; // Геттер для получения значения xpath
    }
}

