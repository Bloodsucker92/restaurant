package com.restaurant.dao.pojos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
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
    private List<Course> courseList = new ArrayList<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Order order = (Order) o;

        if (!id.equals(order.id)) return false;
        return user.equals(order.user);

    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + user.hashCode();
        return result;
    }

    protected boolean canEqual(Object other) {
        return other instanceof Order;
    }

    public String toString() {
        return "com.restaurant.dao.pojos.Order(id=" + this.getId() + ", user=" + this.getUser() + ")";
    }


}
