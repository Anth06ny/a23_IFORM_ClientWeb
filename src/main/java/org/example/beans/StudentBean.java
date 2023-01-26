package org.example.beans;

public class StudentBean {

    private String name;
    private int note;

    public StudentBean() {
    }

    public StudentBean(String name, int note) {
        this.name = name;
        this.note = note;
    }

    @Override
    public String toString() {
        return "StudentBean{" +
                "name='" + name + '\'' +
                ", note=" + note +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNote() {
        return note;
    }

    public void setNote(int note) {
        this.note = note;
    }
}
