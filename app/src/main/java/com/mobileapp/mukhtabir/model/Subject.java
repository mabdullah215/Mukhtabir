package com.mobileapp.mukhtabir.model;

public class Subject
{
    String image;
    String title;
    int mcqs;
    User instructor;

    public User getInstructor() {
        return instructor;
    }

    public void setInstructor(User instructor) {
        this.instructor = instructor;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getMcqs() {
        return mcqs;
    }

    public void setMcqs(int mcqs) {
        this.mcqs = mcqs;
    }
}
