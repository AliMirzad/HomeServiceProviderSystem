$("#saveNewUser").click(function () {
    let form = new FormData();
    form.append("firstName", $("#first-name").val())
    form.append("lastName", $("#last-name").val())
    form.append("email", $("#email").val())
    form.append("nationalCode", $("#national-code").val())
    form.append("password", $("#password").val())
    form.append("type", $(".user-radio:checked").val())
    form.append('profileImage', $('input[type=file]')[0].files[0])

    $.ajax({
        url: "http://localhost:8080/api/user/register",
        type: "POST",
        contentType: false,
        data: form,
        cache: false,
        processData: false,
        dataType:"text",
        success: function (data) {
            alert(data);
        },
        error: function (error) {
            console.log(error);
        },
    });
});