package com.restaurant.dao.pojos;

/* A course POJO */

import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table (name = "COURSE")
@org.hibernate.annotations.Cache(region = "Course", usage = CacheConcurrencyStrategy.READ_WRITE)
public class Course implements Serializable{

    private static final long serialVersionUID = 2L;

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    public Course(Integer id, String courseName, Integer coursePrice, CourseCategory courseCategory, String imgPath, Set<Order> orderSet) {
        this.courseName = courseName;
        this.coursePrice = coursePrice;
        this.courseCategory = courseCategory;
        this.imgPath = imgPath;

        this.orderSet = orderSet;
        this.id = id;
    }

    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    @Column (name = "COURSE_ID", unique = true)
    private Integer id;

    @Column (name = "COURSE_NAME")
    private String courseName;

    @Column (name = "COURSE_PRICE")
    private Integer coursePrice;

    @Column (name = "IMG_PATH")
    private String imgPath;

    @ManyToOne
    @JoinColumn (name = "COURSE_CATEGORY_ID")
    private CourseCategory courseCategory;

    @ManyToMany(mappedBy = "courseList", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Order> orderSet = new HashSet<>();

    public Course() {
    }


    public Integer getId() {
        return this.id;
    }

    public String getCourseName() {
        return this.courseName;
    }

    public Integer getCoursePrice() {
        return this.coursePrice;
    }

    public CourseCategory getCourseCategory() {
        return this.courseCategory;
    }

    public Set<Order> getOrderSet() {
        return this.orderSet;
    }


    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public void setCoursePrice(Integer coursePrice) {
        this.coursePrice = coursePrice;
    }

    public void setCourseCategory(CourseCategory courseCategory) {
        this.courseCategory = courseCategory;
    }

    public void setOrderSet(Set<Order> orderSet) {
        this.orderSet = orderSet;
    }

    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof Course)) return false;
        final Course other = (Course) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$id = this.getId();
        final Object other$id = other.getId();
        if (this$id == null ? other$id != null : !this$id.equals(other$id)) return false;
        final Object this$courseName = this.getCourseName();
        final Object other$courseName = other.getCourseName();
        if (this$courseName == null ? other$courseName != null : !this$courseName.equals(other$courseName))
            return false;
        final Object this$coursePrice = this.getCoursePrice();
        final Object other$coursePrice = other.getCoursePrice();
        if (this$coursePrice == null ? other$coursePrice != null : !this$coursePrice.equals(other$coursePrice))
            return false;
        final Object this$courseCategory = this.getCourseCategory();
        final Object other$courseCategory = other.getCourseCategory();
        if (this$courseCategory == null ? other$courseCategory != null : !this$courseCategory.equals(other$courseCategory))
            return false;
        return true;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $id = this.getId();
        result = result * PRIME + ($id == null ? 43 : $id.hashCode());
        final Object $courseName = this.getCourseName();
        result = result * PRIME + ($courseName == null ? 43 : $courseName.hashCode());
        final Object $coursePrice = this.getCoursePrice();
        result = result * PRIME + ($coursePrice == null ? 43 : $coursePrice.hashCode());
        final Object $courseCategory = this.getCourseCategory();
        result = result * PRIME + ($courseCategory == null ? 43 : $courseCategory.hashCode());

        return result;
    }

    protected boolean canEqual(Object other) {
        return other instanceof Course;
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", courseName='" + courseName + '\'' +
                ", coursePrice=" + coursePrice +
                ", imgPath='" + imgPath + '\'' +
                ", courseCategory=" + courseCategory +
                '}';
    }
}
