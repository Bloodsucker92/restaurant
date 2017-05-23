package com.restaurant.dao.pojos;

/* A User POJO */

import lombok.AllArgsConstructor;
import org.hibernate.annotations.Fetch;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Entity
@Table (name = "USER")
public class User {

    private final static long serialVersionUid = 1L;
    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    @Column (name = "USER_ID")
    private Integer id;
    @Column (name = "NAME", unique = true)
    @NotBlank (message = "Please fill in this field")
    @Size (min = 3, max = 6, message = "Username must contain from 3 to 6 symblos")
    @Pattern(regexp = "^[a-zA-Z0-9_]+$", message = "This field must contain only letters and numbers")
    private String username;
    @Column (name = "PASSWORD")
    @NotBlank (message = "Please fill out this field")
    @Size (min = 3, max = 6, message = "Username must contain from 3 to 6 symblos")
    @Pattern(regexp = "^[a-zA-Z0-9_]+$", message = "This field must contain only letters and numbers")
    private String userpassword;
    @Column (name = "ACCESS")
    private String access;

    @OneToMany (mappedBy = "user", cascade = {CascadeType.PERSIST, CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH}, fetch = FetchType.EAGER)
    private List<Order> orderList = new ArrayList<>();

    public User() {
    }

    public Integer getId() {
        return this.id;
    }

    public String getUsername() {
        return this.username;
    }

    public String getUserpassword() {
        return this.userpassword;
    }

    public String getAccess() {
        return this.access;
    }

    public List<Order> getOrderList() {
        return this.orderList;
    }


    public void setUsername(String username) {
        this.username = username;
    }

    public void setUserpassword(String userpassword) {
        this.userpassword = userpassword;
    }

    public void setAccess(String access) {
        this.access = access;
    }

    public void setOrderList(List<Order> orderList) {
        this.orderList = orderList;
    }

    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof User)) return false;
        final User other = (User) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$id = this.getId();
        final Object other$id = other.getId();
        if (this$id == null ? other$id != null : !this$id.equals(other$id)) return false;
        final Object this$username = this.getUsername();
        final Object other$username = other.getUsername();
        if (this$username == null ? other$username != null : !this$username.equals(other$username)) return false;
        final Object this$userpassword = this.getUserpassword();
        final Object other$userpassword = other.getUserpassword();
        if (this$userpassword == null ? other$userpassword != null : !this$userpassword.equals(other$userpassword))
            return false;
        final Object this$access = this.getAccess();
        final Object other$access = other.getAccess();
        if (this$access == null ? other$access != null : !this$access.equals(other$access)) return false;
        return true;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $id = this.getId();
        result = result * PRIME + ($id == null ? 43 : $id.hashCode());
        final Object $username = this.getUsername();
        result = result * PRIME + ($username == null ? 43 : $username.hashCode());
        final Object $userpassword = this.getUserpassword();
        result = result * PRIME + ($userpassword == null ? 43 : $userpassword.hashCode());
        final Object $access = this.getAccess();
        result = result * PRIME + ($access == null ? 43 : $access.hashCode());

        return result;
    }

    protected boolean canEqual(Object other) {
        return other instanceof User;
    }

    public String toString() {
        return "com.restaurant.dao.pojos.User(id=" + this.getId() + ", username=" + this.getUsername() + ", userpassword=" + this.getUserpassword() + ", access=" + this.getAccess() + ", orderList=" + ")";
    }

}
