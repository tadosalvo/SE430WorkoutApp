package com.tadosalvo.se430project;

public class Term {
    private String term;
    private String about;

    public Term(String term, String about) {
        this.term = term;
        this.about = about;
    }

    public String getTerm() { //These are the getters and setters
        return term;
    }

    public String getAbout() {
        return about;
    }

}
