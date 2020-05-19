package com.example.androidanimation.bean;

import java.util.Objects;

public class DragResult {

    private String id;
    private String dragText;

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
        DragResult that = (DragResult) o;
        return id.equals(that.id) &&
                dragText.equals(that.dragText);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, dragText);
    }

    @Override
    public String toString() {
        return "DragResult{" +
                "id='" + id + '\'' +
                ", dragText='" + dragText + '\'' +
                '}';
    }
}
