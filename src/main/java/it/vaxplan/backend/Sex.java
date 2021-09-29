package it.vaxplan.backend;

public enum Sex {

    MALE("Maschio"),
    FEMALE("Femmina"),
    ANY("Qualsiasi altro");

    private final String string;

    Sex(String name) {
        string = name;
    }

    @Override
    public String toString() {
        return string;
    }

}
