$("#search").click(function () {

    let form = new FormData();
    form.append("id", $("#id").val())
    form.append("email", $("#email").val())
    form.append("firstName", $("#first-name").val())
    form.append("lastName", $("#last-name").val())

    $.ajax({
        url: "http://localhost:8080/api/user/gridSearch/",
        type: "POST",
        contentType: false,
        data: form,
        cache: false,
        processData: false,
        dataType: 'json',
        }).done(function(response){
            alert('ali')
    })
        // success: function (response) {
            // if (response.length === 0) {
            //     alert("there is no user with this information")
            //     return;
            // }
            // $("#TableRowForUsers").find("tr:gt(0)").remove();
            // for (let i = 0; i < response.length; i++) {
            //     $('#TableRowForUsers').append(
            //         '<tr>' +
            //         '<td>' + response[i]['id'] + '</td>' +
            //         '<td>' + response[i]['email'] + '</td>' +
            //         '<td>' + response[i]['firstName'] + '</td>' +
            //         '<td>' + response[i]['lastName'] + '</td>' +
            //         '<tr>');
            //         // '<tr>\' +
            //         // '<td>' + response[i]['id'] + '</td>\<' +
            //         // 'td>' + response[i]['id']  + '</td>\<' +
            //         // 'td>' + response[i]['email']  + '</td>\<' +
            //         // 'td>' + response[i]['firstName'] +
            //         // '</td>\<td>' + response[i]['lastName'] + '</td>\' +
            //         // '<tr>')
            // }
    /*}, fail: (function (request) {
        alert("ali")
        // Swal.fire({
        //     title: 'error!',
        //     text: request.responseText,
        //     didOpen: function () {
        //         Swal.showLoading()
        //         setTimeout(function () {
        //             Swal.close()
        //         }, 2000)
        //     }
        // })
    })*/
    // })
});