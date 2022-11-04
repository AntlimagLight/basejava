package com.urise.webapp.model;

public enum ContactType {
    EMAIL("Электронная почта") {
        @Override
        public String toHtml0(String value) {
            return getTitle() + ": " + toLink0("mailto:" + value, value);
        }
    },
    HOMEPAGE("Домашняя страница") {
        @Override
        public String toHtml0(String value) {
            return toLink0(value);
        }
    },
    PHONE("Телефон"),
    SKYPE("Скайп") {
        @Override
        public String toHtml0(String value) {
            return getTitle() + ": " + toLink0("skype:" + value, value);
        }
    },
    DISCORD("Дискорд"),
    GITHUB("Профиль GitHub") {
        @Override
        public String toHtml0(String value) {
            return toLink0(value);
        }
    },
    LINKEDIN("Профиль LinkedIn") {
        @Override
        public String toHtml0(String value) {
            return toLink0(value);
        }
    },
    STACKOVERFLOW("Профиль Stackoverflow") {
        @Override
        public String toHtml0(String value) {
            return toLink0(value);
        }
    };

    private final String title;

    ContactType(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    protected String toHtml0(String value) {
        return title + ": " + value;
    }

    public String toHtml(String value) {
        return (value == null) ? "" : toHtml0(value);
    }

    public String toLink0(String href) {
        return toLink0(href, href);
    }

    public static String toLink0(String href, String title) {
        return "<a href='" + href + "'>" + title + "</a>";
    }

    public String toLink(String href) {
        return (href == null) ? "" : toLink0(href);
    }

    public String toText(String value) {
        return (value == null) ? "" : value;
    }
}
