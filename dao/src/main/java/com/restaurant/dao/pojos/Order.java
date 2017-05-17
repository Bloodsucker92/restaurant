package com.restaurant.dao.pojos;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;


@Table (name = "UORDERS")
@Entity
public class Order implements Serializable {

    private static final long serialVersionUID = 9034736206162114294L;

    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    @Column (name = "UORDER_ID")
    private Integer id;


    @ManyToOne
    @JoinColumn (name = "USER_ID")
    private User user;

    @LazyCollection(LazyCollectionOption.FALSE)
    @ManyToMany (cascade = {CascadeType.PERSIST, CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH})
    @JoinTable (name = "UORDER_COURSE", joinColumns = {@JoinColumn(name = "UORDER_ID")},
            inverseJoinColumns = {@JoinColumn(name = "COURSE_ID")})
    private Set<Course> courseSet = new HashSet<>();

    public Order(){
    }

    public Order(Integer id, User user, Set<Course> courseSet) {
        this.id = id;
        this.user = user;
        this.courseSet = courseSet;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return this.id;
    }

    public User getUser() {
        return this.user;
    }

    public Set<Course> getCourseSet() {
        return this.courseSet;
    }


    public void setUser(User user) {
        this.user = user;
    }

    public void setCourseSet(Set<Course> courseSet) {
        this.courseSet = courseSet;
    }

    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof Order)) return false;
        final Order other = (Order) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$id = this.getId();
        final Object other$id = other.getId();
        if (this$id == null ? other$id != null : !this$id.equals(other$id)) return false;
        final Object this$user = this.getUser();
        final Object other$user = other.getUser();
        if (this$user == null ? other$user != null : !this$user.equals(other$user)) return false;

        return true;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $id = this.getId();
        result = result * PRIME + ($id == null ? 43 : $id.hashCode());
        final Object $user = this.getUser();
        result = result * PRIME + ($user == null ? 43 : $user.hashCode());

        return result;
    }

    protected boolean canEqual(Object other) {
        return other instanceof Order;
    }

    public String toString() {
        return "com.restaurant.dao.pojos.Order(id=" + this.getId() + ", user=" + this.getUser() + ")";
    }


}
