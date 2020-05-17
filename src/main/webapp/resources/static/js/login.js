
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
            var myHeaders = new Headers();
            myHeaders.set('Authorization', response['tokenType']+" "+response['accessToken']);
            setCookie('Authorization', response['accessToken']);   
            window.location.href = "/";
        },
        error: function (jqXHR, textStatus, errorThrown) {
            if (jqXHR.status == 200) {
                M.toast({html: "Неверный пароль!"});
                $('.modal').modal();
                $("#login-modal").modal("open");
            }else if (jqXHR.status == 400) {
                M.toast({html: "Неверный логин!"});
                $('.modal').modal();
                $("#login-modal").modal("open");
            } else {
                console.log(textStatus + ": " + jqXHR.status + " " + errorThrown);
                M.toast({html: textStatus + ": " + jqXHR.status + " " + errorThrown});
            }
        }
    });

}

function setCookie(name, value, options = {}) {

    options = {
        path: '/',
    };

    if (options.expires instanceof Date) {
        options.expires = options.expires.toUTCString();
    }

    let updatedCookie = encodeURIComponent(name) + "=" + encodeURIComponent(value);

    for (let optionKey in options) {
        updatedCookie += "; " + optionKey;
        let optionValue = options[optionKey];
        if (optionValue !== true) {
            updatedCookie += "=" + optionValue;
        }
    }

    document.cookie = updatedCookie;
}


const getJSONfromForm = (formname) => {
    let formData = $("#" + formname).serializeArray();
    return formData;
}