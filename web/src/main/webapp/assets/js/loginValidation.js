$(document).ready(function () {
    var validator = $("#retry-login-form").bootstrapValidator({
        feedbackIcons: {
            valid: "glyphicon glyphicon-ok",
            invalid: "glyphicon glyphicon-remove",
            validating: 'glyphicon glyphicon-refresh'
        },
        fields:{
            login: {
                message: "Login is required",
                validators:{
                    notEmpty:{
                        message: "Please enter a login"
                    },
                    stringLength:{
                        min: 3,
                        max: 6,
                        message: "Login must be between 3 and 6 characters long"
                    },
                    regexp: {
                        regexp: /^[a-zA-Z0-9_]+$/,
                        message: 'The login can only consist of alphabetical, number and underscore'
                    }

                }
            },
            password: {
                message: "Password is required",
                validators:{
                    notEmpty:{
                        message: "Please enter a password"
                    },
                    stringLength:{
                        min: 3,
                        max: 6,
                        message: "Password must be between 3 and 6 characters long"
                    },
                    regexp: {
                        regexp: /^[a-zA-Z0-9_]+$/,
                        message: 'The password can only consist of alphabetical, number and underscore'
                    }

                }
            }
        }
    })
});