function validateForm() {
    var x = document.forms["loginForm"]["login"].value;
    var y = document.forms["loginForm"]["password"].value;
    if (x == "" | y == "") {
        alert("All fields must be filled out");
        return false;
    }
}
