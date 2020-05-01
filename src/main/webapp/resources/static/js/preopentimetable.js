
createTbody = (data, accept) => {
    var tbody = document.createElement("tbody");
    let name = localStorage.getItem("current_open_table");
    let optionType = getListDataFromServer(name);
    data.forEach(item => {
        var input = document.createElement("tr");
        for (let key in item) {
            var id = 0;
            var td = document.createElement("td");
            if (key != 'id') {
                if (optionType[key]["type"] == "object" && item[key] != null) {
                    if (["employee", "teacher"].includes(key)) {
                        td.append(item[key]["name"] + " " + item[key]["surname"] + " " + item[key]["patronymic"]);
                        input.append(td);
                    } else {
                        td.append(item[key]["name"]);
                        input.append(td);
                    }
                } else if (optionType[key]["type"] == "array" && item[key] != null) {
                    for (let role in item[key]) {
                        td.append(item[key][role]["role"]);
                        td.append(" ");
                        input.append(td);
                    }
                } else if (item[key] == null) {
                    td.append(" ");
                    input.append(td);
                } else {
                    td.append(item[key]);
                    input.append(td);
                }
            } else {
                id = item[key];
                input.id = id;
            }
        }
        var td = document.createElement("td");


        var a = document.createElement("span");
        a.className = "tooltipped";
        a.setAttribute("data-tooltip", "Редактирование расписания");
        a.setAttribute("data-position", "top");
        a.value = item.id;
        a.addEventListener("click", function() {
            redirectTo('/timetable/' + item['id']);
          }, false);
        var i = document.createElement("i");
        i.className = 'material-icons';
        i.textContent = 'view_module';
        a.append(i);
        td.append(a);

        var a = document.createElement("span");
        a.className = "tooltipped";
        a.value = item.id;
        a.addEventListener("click", openToChangeForm);
        a.setAttribute("data-tooltip", "Изменить запись");
        a.setAttribute("data-position", "top");

        var i = document.createElement("i");
        i.className = 'material-icons';
        i.textContent = 'edit';
        a.append(i);
        td.append(a);

        var a = document.createElement("span");
        a.value = item.id;
        a.className = "tooltipped";
        a.setAttribute("data-tooltip", "Удалить запись");
        a.setAttribute("data-position", "top");
        a.addEventListener("click", deleteValueFromTable);
        var i = document.createElement("i");
        i.className = 'material-icons';
        i.textContent = 'delete';
        a.append(i);
        td.append(a);

        input.append(td);
        tbody.appendChild(input);

    });
    return tbody;
}

const redirectTo = (url)=>{
    window.location=url;
}


$(document).ready(function () {
    $('.collapsible').collapsible();
    $("ul.tabs").tabs();
    $(".sidenav").sidenav();
    $('.modal').modal();
    let fullurl = window.location.pathname;
    let url = fullurl.match("lectern\/(.+)\/")[1];
    openTableEvent("urn:jsonschema:com:java:domain:Timetable", "");
});



