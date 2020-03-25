
$(document).ready(() => {

    $("ul.tabs").tabs();
    $(".sidenav").sidenav();
    $('.modal').modal();
    $('select').formSelect();
    $('.collapsible').collapsible();
});


$.ajax({
    headers: { 
        'Accept': 'application/json',
        'Content-Type': 'application/json' 
    },
    'type': 'GET',
    'url': "/users/me/",
    'success': function(response) {
        $("#email").val(response["username"]);
        $("#name").val(response["teacher"]["name"]);
        $("#surname").val(response["teacher"]["surname"]);
        $("#patronymic").val(response["teacher"]["patronymic"]);
        $("#position").val(response["teacher"]["position"]);
        $("#rank").val(response["teacher"]["rank"]);
        $("#academicDegree").val(response["teacher"]["academicDegree"]);

        $(document).ready(function() {
          M.updateTextFields();
        });
    }

});


const Update = () => {
    let json = getJSONfromForm("mypage-form");
    console.log(json);
}

const getJSONfromForm = (formname) => {
    let formData = $("#"+formname).first().serializeArray();
    return formData;
}