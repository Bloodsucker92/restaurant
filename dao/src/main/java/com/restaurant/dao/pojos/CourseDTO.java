package com.restaurant.dao.pojos;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CourseDTO {
    
    private Integer id;

    @NotBlank(message = "Please fill in this field")
    @Size(min = 2, max = 12, message = "Course name must contain from 2 to 12 symbols")
    @Pattern(regexp = "^[a-zA-Z_]+$", message = "This field must contain only letters")
    private String courseName;

    @NotNull(message = "Please fill in this field")
    @Min(value = 1, message = "Course price field must contain at least one figure")
    @Max(value = 999, message = "Course price field must contain no more than 3 figures")
    private Integer coursePrice;
    
    @NotNull
    private String courseCategory;
}
