package com.eslicue.model;

import java.time.LocalDate;

public class Photo {
    private LocalDate date;
    private String url;

    public Photo(LocalDate date, String url) {
        this.date = date;
        this.url = url;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
