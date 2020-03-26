
$(document).ready(() => {

    $('.modal').modal();
    $("#login-modal").modal("open");
});



const LoginClick = () => {
    let json = getJSONfromForm("login-form");
    let regform = {
        username: json[0]['value'],
        password: json[1]['value']
    }

    $.ajax({
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        'type': 'POST',
        'url': "/api/auth/signin/",
        'data': JSON.stringify(regform),
        'dataType': 'json',
        'success': function (response) {
            window.location.href = "/";
        }
    });

}

const getJSONfromForm = (formname) => {
    let formData = $("#" + formname).serializeArray();
    return formData;
}