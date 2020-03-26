
$(document).ready(() => {

    $('.modal').modal();
    $("#login-modal").modal("open");
});


const RegistrationClick = () => {
    let json = getJSONfromForm("register-form");
    console.log(json);
    if (json[4].value != json[5].value || json[5].value == "") {
        return null;
    } else {
        let regform = {
            username: json[0]['value'],
            name: json[1]['value'],
            surname: json[2]['value'],
            patronymic: json[3]['value'],
            password: json[5]['value']
        }

        $.ajax({
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            'type': 'POST',
            'url': "/registration/",
            'data': JSON.stringify(regform),
            'dataType': 'json'
        });
    }
}

const getJSONfromForm = (formname) => {
    let formData = $("#" + formname).serializeArray();
    return formData;
}

