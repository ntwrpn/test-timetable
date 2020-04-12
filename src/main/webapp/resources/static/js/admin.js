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
    
    var $event = $(event.currentTarget);
    $event.removeClass('tooltipped').tooltip('destroy');
    let id = event.currentTarget.value;
    let name = localStorage.getItem("current_open_table");
    let url = getMappingUrl(name);
    $.ajax({
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        'type': 'DELETE',
        'url': url + id,
        'success': function (response) {
            M.toast({html: "Запись удалена"});
            openTableEvent(name, "");
        },
        error: function (jqXHR, textStatus, errorThrown) {
            console.log(textStatus + ": " + jqXHR.status + " " + errorThrown);
            M.toast({html: textStatus + ": " + jqXHR.status + " " + errorThrown});
        }

    });

}


const acceptValueFromTable = (event) => {
    let id = event.currentTarget.value;
    $.ajax({
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        'type': 'POST',
        'url': "/users/accept/" + id,
        'success': function (response) {
            M.toast({html: "Юзер подтвержден!"});
            openTableEvent("urn:jsonschema:com:java:domain:Users", "?enabled=False");
        },
        error: function (jqXHR, textStatus, errorThrown) {
            console.log(textStatus + ": " + jqXHR.status + " " + errorThrown);
            M.toast({html: textStatus + ": " + jqXHR.status + " " + errorThrown});
        }

    });


}


const openTableEvent = (value, add) => {
    $("#add-modal").modal("close");
    localStorage.setItem("current_open_table", value);

    clearPostFromModal();
    createPostFormModal(value);


    let url = getMappingUrl(value);

    $.get(url + add, function (data, status) {
        saveJSONDataToLocalStorage(value, data);
        var container = document.getElementById("table-form");
        container.className = 'display z-depth-4 table-format-01';
        while (container.hasChildNodes()) {
            container.removeChild(container.lastChild);
        }
        var table = document.createElement("table");
        table.id = 'data-tr-table';


        if (data.length > 0) {
            var thread = createThread(data);
            table.appendChild(thread);
            if (add == '?enabled=False') {
                var tbody = createTbody(data, true);
            } else {
                var tbody = createTbody(data, false);
            }
            table.appendChild(tbody);
            container.appendChild(table);
        }
        $(document).ready(function () {
            $('#data-tr-table').DataTable({
                "language": {
                    "url": "/resources/static/json/russian.json"
                },
                "columnDefs": [{"width": "50px", "targets": -1}]
            });
            $('#data-tr-table').on('draw.dt', function () {
                $('select').formSelect();
                $('.tooltipped').tooltip();
            });
        });

    });

}

const createThread = (data) => {
    var threadHeader = document.createElement("thead");

    var thread = document.createElement("tr");
    for (let key in data[0]) {
        var th = document.createElement("th");
        if (key != 'id') {
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

const createTbody = (data, accept) => {
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



        if (accept == true) {
            var a = document.createElement("span");
            a.className = "tooltipped";
            a.setAttribute("data-tooltip", "Подтвердить пользователя");
            a.setAttribute("data-position", "top");
            a.href = '#1';
            a.value = item.id;
            a.addEventListener("click", acceptValueFromTable);
            var i = document.createElement("i");
            i.className = 'material-icons';
            i.textContent = 'done';
            a.append(i);
            td.append(a);
        }
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

