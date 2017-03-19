/**
 * Created by dzianismitrakhovich on 22.02.17.
 */
$(function () {


    $('#reg-form').validate({
        rules:{
            //the login is case-insensetive and must contain only letters. Minimum length 3 chars,
            // max length 6 chars
            loginReg:{
                required: true,
                regxLog: /^[a-z]+$/i,
                minlength: 3,
                maxlength: 6,
            },

            //the password is case-insensetive and must contain only digits or letters. Minimum length 3 chars,
            // max length 6 chars
            passwordReg:{
                required: true,
                regxPas: /^[a-z0-9]+$/i,
                minlength: 3,
                maxlength: 6,
            },
        },
        messages:{
            loginReg: {
                required: "Please enter a login"
            },
            passwordReg: {
                required: "Please enter a password"
            }

        }
    })

    $.validator.addMethod("regxLog", function(value, element, regexpr) {
        return regexpr.test(value)
    }, "User name must contain only letters");

    $.validator.addMethod("regxPas", function(value, element, regexpr) {
        return regexpr.test(value)
    }, "Password must contain only digits or letters.");

})
