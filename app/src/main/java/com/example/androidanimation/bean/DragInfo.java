package com.example.androidanimation.bean;

import java.io.Serializable;
import java.util.Objects;

public class DragInfo implements Serializable {

    private String id;
    private String dragText;

    public DragInfo(String id, String dragText) {
        this.id = id;
        this.dragText = dragText;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDragText() {
        return dragText;
    }

    public void setDragText(String dragText) {
        this.dragText = dragText;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DragInfo dragInfo = (DragInfo) o;
        return id.equals(dragInfo.id) &&
                dragText.equals(dragInfo.dragText);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, dragText);
    }
}
