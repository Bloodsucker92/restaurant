/**
 * Created by dzianismitrakhovich on 22.02.17.
 */
$(function () {


    $('#course-add-form').validate({
        rules:{
            //the course name field is case-insensetive and must contain only letters. Minimum length 3 chars,
            // max length 6 chars
            courseName:{
                required: true,
                regxName: /^[a-zA-Z_ -]*$/i,
                minlength: 3,
                maxlength: 12,
            },

            //the course price field must contain only digits. Minimum length 1 chars,
            // max length 3 chars
            coursePrice:{
                required: true,
                regxPrice: /^[0-9]+$/i,
                minlength: 1,
                maxlength: 3,
            },
        },
        messages:{
            courseName: {
                required: "Please enter a course name"
            },
            coursePrice: {
                required: "Please enter course price"
            }

        }
    })

    $.validator.addMethod("regxName", function(value, element, regexpr) {
        return regexpr.test(value)
    }, "Course name must contain only letters");

    $.validator.addMethod("regxPrice", function(value, element, regexpr) {
        return regexpr.test(value)
    }, "Price must contain only digits.");

})
