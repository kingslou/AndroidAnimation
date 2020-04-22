package com.example.androidanimation.bean;

import java.io.Serializable;
import java.util.Objects;

public class DragInfo implements Serializable {

    private String id;
    private String dragText;
    private String dragPosition;

    public DragInfo(String id, String dragText, String dragPosition) {
        this.id = id;
        this.dragText = dragText;
        this.dragPosition = dragPosition;
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

    public String getDragPosition() {
        return dragPosition;
    }

    public void setDragPosition(String dragPosition) {
        this.dragPosition = dragPosition;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DragInfo dragInfo = (DragInfo) o;
        return Objects.equals(id, dragInfo.id) &&
                Objects.equals(dragText, dragInfo.dragText) &&
                Objects.equals(dragPosition, dragInfo.dragPosition);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, dragText, dragPosition);
    }
}
