package com.restaurant.dao.beans;

public class Course {
    private int id;
    private String courseName;
    private int coursePrice;
    private String courseType;

    public Course(int id, String courseName, int coursePrice, String courseType) {
        this.id = id;
        this.courseName = courseName;
        this.coursePrice = coursePrice;
        this.courseType = courseType;
    }

    public Course() {
    }

    public String getCourseType() {
        return courseType;
    }

    public void setCourseType(String courseType) {
        this.courseType = courseType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public int getCoursePrice() {
        return coursePrice;
    }

    public void setCoursePrice(int coursePrice) {
        this.coursePrice = coursePrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Course course = (Course) o;

        if (id != course.id) return false;
        if (coursePrice != course.coursePrice) return false;
        if (!courseName.equals(course.courseName)) return false;
        return courseType.equals(course.courseType);

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + courseName.hashCode();
        result = 31 * result + coursePrice;
        result = 31 * result + courseType.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", courseName='" + courseName + '\'' +
                ", coursePrice=" + coursePrice +
                ", courseType='" + courseType + '\'' +
                '}';
    }
}
