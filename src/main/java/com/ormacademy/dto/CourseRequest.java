package com.ormacademy.dto;
import jakarta.validation.constraints.NotBlank;
public class CourseRequest {
    @NotBlank(message = "Название не должно быть пустым")
    private String title;
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
}