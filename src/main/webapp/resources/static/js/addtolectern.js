const getJSONDataFromLocalStorage = (key) => {
    let dataString = localStorage.getItem(key);

    let data = JSON.parse(dataString);

    return data;
}

const saveJSONDataToLocalStorage = (key, data) => {
    let dataString = JSON.stringify(data);
    localStorage.setItem(key, dataString);
}
const deleteValueFromTable = (event) => {
    let id = event.currentTarget.value;
    let name = localStorage.getItem("current_open_table");

    $.ajax({
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        'type': 'POST',
        'url': "deleteuserfromteacher" + "?teacherId=" + id,
        'success': function (response) {
            M.toast({html: "Связь удалена"});
            openTableEvent("table-form", "data-tr-table", "urn:jsonschema:com:java:domain:Teacher");
            $("#add-modal").modal("close");
        },
        error: function (jqXHR, textStatus, errorThrown) {
            console.log(textStatus + ": " + jqXHR.status + " " + errorThrown);
            M.toast({html: textStatus + ": " + jqXHR.status + " " + errorThrown});
        }

    });

}



const acceptValueFromTable = (event) => {
    let id = event.currentTarget.value;
    let url = "addusertoteacher/?userId=" + id + "&teacherId=" + localStorage.getItem("teacher");
    $.ajax({
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        'type': 'POST',
        'url': url,
        'success': function (response) {
            M.toast({html: "Аккаунты связаны"});
            openTableEvent("table-form", "data-tr-table", "urn:jsonschema:com:java:domain:Teacher");
            $("#add-modal").modal("close");
        },
        error: function (jqXHR, textStatus, errorThrown) {
            console.log(textStatus + ": " + jqXHR.status + " " + errorThrown);
            M.toast({html: textStatus + ": " + jqXHR.status + " " + errorThrown});
            $("#add-modal").modal("close");

        }

    });


}


const openTableEvent = (form, table_name, value, add) => {
    //$("#add-modal").modal("close");
    localStorage.setItem("current_open_table", value);

    clearPostFromModal();
    createPostFormModal(value);


    let url = getMappingUrl(value);
    if (value == "urn:jsonschema:com:java:domain:Teacher") {
        let fullurl = window.location.pathname;
        url += "?lecternId=" + fullurl.match("lectern\/(.+)\/")[1];
    } else if (value == "urn:jsonschema:com:java:domain:Users") {
        url += "?clear=true";
    }

    $.get(url, function (data, status) {
        saveJSONDataToLocalStorage(value, data);
        var container = document.getElementById(form);
        while (container.hasChildNodes()) {
            container.removeChild(container.lastChild);
        }
        var table = document.createElement("table");
        table.id = table_name;


        if (data.length > 0) {
            var thread = createThread(data);
            table.appendChild(thread);
            var tbody = createTbody(data, add);
            table.appendChild(tbody);
            container.appendChild(table);
        }
        $(document).ready(function () {
            $(table).DataTable({
                "language": {
                    "url": "/resources/static/json/russian.json"
                }
            });
            $(table).on('draw.dt', function () {
                $('select').formSelect();
            });
            if (add) {
                $("#add-modal").modal("open");
            }

        });

    });

}

const createThread = (data) => {
    var threadHeader = document.createElement("thead");

    var thread = document.createElement("tr");
    for (let key in data[0]) {
        var th = document.createElement("th");
        if (key != 'id' && key != "username" && key != "userRoles") {
            th.append(getLocalizedName(key));
            thread.append(th);
        }
    }
    var th = document.createElement("th");
    th.append("Действия");
    thread.append(th);
    threadHeader.append(thread);
    return threadHeader;
}

const createTbody = (data, add) => {
    var tbody = document.createElement("tbody");
    let name = localStorage.getItem("current_open_table");
    let optionType = getListDataFromServer(name);
    data.forEach(item => {
        var input = document.createElement("tr");
        for (let key in item) {
            var id = 0;
            var td = document.createElement("td");
            if (key != 'id' && key != "username" && key != "userRoles") {
                if (optionType[key]["type"] == "object" && item[key] != null) {
                    if (["employee"].includes(key)) {
                        td.append(item[key]["deanery"]["name"]);
                        input.append(td);
                    } else if (["teacher"].includes(key)) {
                        if (item[key]["lectern"] != null) {
                            td.append(item[key]["lectern"]["name"]);
                            input.append(td);
                        } else {
                            td.append("");
                            input.append(td);
                        }
                    } else if (["users"].includes(key)) {
                        if (item[key]["username"] != null) {
                            td.append(item[key]["username"]);
                            input.append(td);
                        } else {
                            td.append("");
                            input.append(td);
                        }
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


        var a = document.createElement("a");
        a.className = 'btn btn waves-effect waves-light green accent-4';
        a.href = '#1';
        a.value = item.id;
        if (add) {
            a.addEventListener("click", acceptValueFromTable);
            a.append("Подтвердить");
            td.append(a);
            input.append(td);

        } else {
            a.addEventListener("click", opentableinform);
            a.append("Связать");
            td.append(a);
            var a = document.createElement("a");
            a.className = 'btn btn waves-effect waves-light red accent-4';
            a.href = '#1';
            a.value = item.id;
            a.addEventListener("click", deleteValueFromTable);
            a.append("Удалить связь");
            td.append(a);
            input.append(td);
        }







        deleteValueFromTable
        tbody.appendChild(input);

    });
    return tbody;
}

const opentableinform = (event) => {
    localStorage.setItem("teacher", event.currentTarget.value)
    openTableEvent("add-modal", "data-tr-table", "urn:jsonschema:com:java:domain:Users", "true");
}

const openToChangeForm = (event) => {
    clearPostFromModal();
    let id = event.currentTarget.value;
    let value = localStorage.getItem("current_open_table");
    let url = getMappingUrl(value);
    $.get(url + id, function (data, status) {
        createPostFormModal(data);
        $("#add-modal").modal("open");
    });
}

$(document).ready(function () {
    $('.collapsible').collapsible();
    $("ul.tabs").tabs();
    $(".sidenav").sidenav();
    $('.modal').modal();
    openTableEvent("table-form", "data-tr-table1", "urn:jsonschema:com:java:domain:Teacher");
});



