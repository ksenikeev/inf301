package ru.itis.inf301.freemarker;

public class Reg {
    private Long id;
    public String getId() {
        System.out.println("get id");
        return String.valueOf(id);
    }
    public void setId(Long id) {
        this.id = id;
    }
}
