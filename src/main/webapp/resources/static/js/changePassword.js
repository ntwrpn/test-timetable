
$(document).ready(() => {

    $('.modal').modal();
    $("#login-modal").modal("open");
});


const ChangeClick = () => {
    let json = getJSONfromForm("login-form");
    console.log(json);
    let regform = {
        id: json[0]['value'],
        token: json[1]['value'],
        password: json[2]['value'],
    }

    $.ajax({
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        'type': 'POST',
        'url': "/changePassword",
        'data': JSON.stringify(regform),
        'dataType': 'json',
        error: function (jqXHR, textStatus, errorThrown) {
            console.log(textStatus + ": " + jqXHR.status + " " + errorThrown);
            M.toast({html: textStatus + ": " + jqXHR.status + " " + errorThrown});
        }
    });

}

const getJSONfromForm = (formname) => {
    let formData = $("#" + formname).serializeArray();
    return formData;
}

