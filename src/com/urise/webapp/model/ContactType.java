package com.urise.webapp.model;

public enum ContactType {
    EMAIL("Электронная почта"),
    HOMEPAGE("Домашняя страница"),
    PHONE("Телефон"),
    SKYPE("Скайп"),
    DISCORD("Дискорд"),
    GITHUB("Профиль GitHub"),
    LINKEDIN("Профиль LinkedIn"),
    STACKOVERFLOW("Профиль Stackoverflow");

    private final String title;

    ContactType(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
