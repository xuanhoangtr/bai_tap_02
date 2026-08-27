package vn.hcmute.models;

import java.io.Serializable;

public class CategoryModel implements Serializable {
    private static final long serialVersionUID = 1L;

    private int id;
    private String name;
    private String icon;

    public CategoryModel() {
    }

    public CategoryModel(int id, String name, String icon) {
        this.id = id;
        this.name = name;
        this.icon = icon;
    }

    public CategoryModel(String name, String icon) {
        this.name = name;
        this.icon = icon;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
}
