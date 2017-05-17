package com.restaurant.dao.pojos;


import lombok.NoArgsConstructor;
import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@Entity
@Table (name = "COURSE_CATEGORY")
@org.hibernate.annotations.Cache(region = "courseCategory", usage = CacheConcurrencyStrategy.READ_ONLY)
public class CourseCategory {

    private static final long serialVersionUID = 3L;

    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    @Column (name = "COURSE_CATEGORY_ID", unique = true, nullable = false)
    private Integer id;

    @Column (name = "COURSE_TYPE")
    private String courseCategory;

    @OneToMany (mappedBy = "courseCategory", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private Set<Course> courseSet = new HashSet<>();

    public Integer getId() {
        return this.id;
    }

    public String getCourseCategory() {
        return this.courseCategory;
    }

    public Set<Course> getCourseSet() {
        return this.courseSet;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setCourseCategory(String courseCategoryName) {
        this.courseCategory = courseCategoryName;
    }

    public void setCourseSet(Set<Course> courseSet) {
        this.courseSet = courseSet;
    }

    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof CourseCategory)) return false;
        final CourseCategory other = (CourseCategory) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$id = this.getId();
        final Object other$id = other.getId();
        if (this$id == null ? other$id != null : !this$id.equals(other$id)) return false;
        final Object this$courseCategoryName = this.getCourseCategory();
        final Object other$courseCategoryName = other.getCourseCategory();
        if (this$courseCategoryName == null ? other$courseCategoryName != null : !this$courseCategoryName.equals(other$courseCategoryName))
            return false;

        return true;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $id = this.getId();
        result = result * PRIME + ($id == null ? 43 : $id.hashCode());
        final Object $courseCategoryName = this.getCourseCategory();
        result = result * PRIME + ($courseCategoryName == null ? 43 : $courseCategoryName.hashCode());

        return result;
    }

    protected boolean canEqual(Object other) {
        return other instanceof CourseCategory;
    }

    public String toString() {
        return "com.restaurant.dao.pojos.CourseCategory(id=" + this.getId() + ", courseCategoryName=" + this.getCourseCategory() + ")";
    }
}
