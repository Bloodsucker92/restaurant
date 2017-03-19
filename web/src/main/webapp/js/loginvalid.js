$(function () {
    $('#login-form').validate({
        rules:{
            login:{
                required: true,
                minlength: 3,
                maxlength: 10
            },
            password:{
                required: true,
                minlength: 3,
                maxlength: 10
            },
        },
        messages:{
            login: {
                required: "Please enter a login"
            },
            password: {
                required: "Please enter a password"
            }

        }
    })

})