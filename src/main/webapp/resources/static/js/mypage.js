
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
    'success': function (response) {
        $("#email").val(response["username"]);
        $("#name").val(response["teacher"]["name"]);
        $("#surname").val(response["teacher"]["surname"]);
        $("#patronymic").val(response["teacher"]["patronymic"]);
        $("#position").val(response["teacher"]["position"]);
        $("#rank").val(response["teacher"]["rank"]);
        $("#academicDegree").val(response["teacher"]["academicDegree"]);

        $(document).ready(function () {
            M.updateTextFields();
        });
    }

});




const Update = () => {
    let name = document.getElementById("name").value;
    let surname = document.getElementById("surname").value;
    let patronymic = document.getElementById("patronymic").value;
    let academicDegree = document.getElementById("academicDegree").value;

    $.get("/users/me/", function (data, status) {
        data["name"] = name;
        data["surname"] = surname;
        data["patronymic"] = patronymic;
        $.ajax({
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            'type': 'POST',
            'url': "/users/me/?Academic="+academicDegree,
            'data': JSON.stringify(data),
            'dataType': 'json',
            'success': function (response) {
                M.toast({html: "Информация обновлена"});
            },
            error: function (jqXHR, textStatus, errorThrown) {
                console.log(textStatus + ": " + jqXHR.status + " " + errorThrown);
                M.toast({html: textStatus + ": " + jqXHR.status + " " + errorThrown});
            }
        });

    });

}
