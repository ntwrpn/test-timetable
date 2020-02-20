
$(document).ready(() => {

    $('.modal').modal();
    $("#login-modal").modal("open");
});


const RegistrationClick = () => {
    let json = getJSONfromForm("register-form");
    console.log(json);
    if (json[3].value != json[2].value || json[3].value==""){
        return null;
    }
    else{
        let regform = {
            username: json[0]['value'],
            fullname: json[1]['value'],
            password: json[2]['value']
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
    let formData = $("#"+formname).serializeArray();
    return formData;
}

