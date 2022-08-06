package com.liu.pro.forkjoinpool;

import java.util.List;

public class Menu {
    private Integer id;
    private String menuName;
    private Integer parentId;
    private List<Menu> child;

    public Menu() {
    }

    public Menu(Integer id, String menuName, Integer parentId) {
        this.id = id;
        this.menuName = menuName;
        this.parentId = parentId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public List<Menu>  getChild() {
        return child;
    }

    public void setChild(List<Menu>  child) {
        this.child = child;
    }

    @Override
    public String toString() {
        return "Menu{" +
                "id=" + id +
                ", menuName='" + menuName + '\'' +
                ", parentId=" + parentId +
                ", child=" + child +
                '}';
    }
}
