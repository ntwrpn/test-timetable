const openAddCreateModal = (event) => {
    $("#add-modal").modal("open");
}


const getMappingUrl = (name) => {
    let data;
    $.ajax({
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        'async': false,
        'type': 'GET',
        'url': "/resources/static/json/mapping.json",
        'success': function (response) {
            data = response[name];
        },
        error: function (jqXHR, textStatus, errorThrown) {
            console.log(textStatus + ": " + jqXHR.status + " " + errorThrown);
            M.toast({html: textStatus + ": " + jqXHR.status + " " + errorThrown});
        }
    });
    return data;
}


const getLocalization = () => {
    let data;
    $.ajax({
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        'async': false,
        'type': 'GET',
        'url': "/resources/static/json/localize.json",
        'success': function (response) {
            data = response;
        },
        error: function (jqXHR, textStatus, errorThrown) {
            console.log(textStatus + ": " + jqXHR.status + " " + errorThrown);
            M.toast({html: textStatus + ": " + jqXHR.status + " " + errorThrown});
        }
    });
    return data;
}


const getLocalizedName = (name) => {
    if (LOCALIZATION[name] != undefined) {
        return LOCALIZATION[name];
    } else {
        return name;
    }
}

const getListDataFromServer = (name) => {
    let url = getMappingUrl(name);
    let data;
    $.ajax({
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        'async': false,
        'type': 'OPTIONS',
        'url': url,
        'success': function (response) {
            data = response["properties"];
        },
        error: function (jqXHR, textStatus, errorThrown) {
            console.log(textStatus + ": " + jqXHR.status + " " + errorThrown);
            M.toast({html: textStatus + ": " + jqXHR.status + " " + errorThrown});
        }
    });
    return data;
}

const getDataFromServer = (name, id) => {
    let url = getMappingUrl(name);
    if (id != undefined) {
        url = url + id;
    }
    let data;
    $.ajax({
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        'async': false,
        'type': 'GET',
        'url': url,
        'success': function (response) {
            data = response;
        },
        error: function (jqXHR, textStatus, errorThrown) {
            console.log(textStatus + ": " + jqXHR.status + " " + errorThrown);
            M.toast({html: textStatus + ": " + jqXHR.status + " " + errorThrown});
        }
    });
    return data;
}

let LOCALIZATION = getLocalization();

//CREATE ADD FORM TO TABLE
const getFieldTypeByOptions = (type) => {
    switch (type) {
        case "integer":
            return "number";
        case "string":
            return "text";
        case "object":
            return "object";
        case "date":
            return "date";
        case "array":
            return "list";
        case "boolean":
            return "boolean";
        default:
            return type;
    }
}


const addFromEvent = () => {
    let json = getJSONfromForm("add-modal-content");

    let name = getMappingUrl(localStorage.getItem("current_open_table"));
    let type = 'POST';
    if (json['id'] != undefined && json['id'] != null) {
        type = 'PUT';
        name = name + json['id'];
    }

    $.ajax({
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        'type': type,
        'async': false,
        'url': name,
        'data': JSON.stringify(json),
        'dataType': 'json',
        'success': function (response) {
            if (type == 'PUT') {
                M.toast({html: "Запись изменена"});
            } else if (type == 'POST') {
                M.toast({html: "Запись создана"});
            }
            openTableEvent(localStorage.getItem("current_open_table"), "");

        },
        error: function (jqXHR, textStatus, errorThrown) {
            if (textStatus == "200") {
                console.log(textStatus + ": " + jqXHR.status + " " + errorThrown);
                M.toast({html: textStatus + ": " + jqXHR.status + " " + errorThrown});
            }
            openTableEvent(localStorage.getItem("current_open_table"), "");

        }
    });

}

const getJSONfromForm = (formname) => {
    let formData = $("#" + formname).serializeArray();
    let name = localStorage.getItem("current_open_table");
    let optionType = getListDataFromServer(name);
    let json = {};
    for (let data in formData) {
        if (formData[data]['name'] == "id" || formData[data]['name'] == "password") {
            if (formData[data]['value'] != "undefined") {
                json[formData[data]['name']] = formData[data]['value'];
            } else {
                continue;
            }
        } else if (getFieldTypeByOptions(optionType[formData[data]['name']]["type"]) == "text") {
            json[formData[data]['name']] = formData[data]['value'];

        } else if (getFieldTypeByOptions(optionType[formData[data]['name']]["type"]) == "number") {
            json[formData[data]['name']] = parseInt(formData[data]['value']);
        } else if (["set", "list"].includes(getFieldTypeByOptions(optionType[formData[data]['name']]["type"]))) {
            let current_data = getDataFromServer(optionType[formData[data]['name']]["items"]["id"], formData[data]['value']);
            if (typeof (json[formData[data]['name']]) == "undefined") {
                json[formData[data]['name']] = [];
            }
            json[formData[data]['name']].push(current_data);
        } else if (["boolean", "uuid", "id"].includes(optionType[formData[data]['name']]["type"])) {
            if (formData[data]['value'] != "undefined") {
                json[formData[data]['name']] = formData[data]['value'];
            }
        } else {
            if (formData[data]['value'] != "") {
                let current_data = getDataFromServer(optionType[formData[data]['name']]["id"], formData[data]['value']);
                json[formData[data]['name']] = current_data;
            }
        }
    }
    return json;
}


const clearPostFromModal = () => {
    var container = document.getElementById("add-modal");
    if (container != null) {
        while (container.hasChildNodes()) {
            container.removeChild(container.lastChild);
        }
    }
}

const createPostFormModal = (changeData) => {
    let name = localStorage.getItem("current_open_table");
    let data = getListDataFromServer(name);

    let modalForm = document.getElementById("add-modal");
    if (modalForm == null) {
        modalForm = document.createElement("div");
        modalForm.className = "modal";
        modalForm.id = "add-modal";
    }

    let modalContent = document.createElement("form");
    modalContent.className = "modal-content";
    modalContent.id = "add-modal-content";

    let modalHeader = document.createElement("h4");
    modalHeader.innerText = "Добавить запись";
    modalHeader.id = "add-modal-header";
    modalContent.appendChild(modalHeader);


    if (name == "urn:jsonschema:com:java:domain:Users" && typeof changeData != "object") {
        let loadCaption = document.createElement("p");
        loadCaption.innerText = "Пароль";
        loadCaption.id = "add-modal-caption";
        modalContent.appendChild(loadCaption);

        let loadField = document.createElement("input");
        loadField.id = "password";
        loadField.name = "password";
        loadField.type = "password";
        loadField.minlength = "6"
        loadField.maxlength = "40"
        modalContent.appendChild(loadField);
    }

    for (let key in data) {
        if (key == "id") {
            let loadField = document.createElement("input");
            loadField.id = key;
            loadField.name = key;
            loadField.type = "hidden";
            loadField.value = changeData[key];
            modalContent.appendChild(loadField);
            continue;
        }

        let local_var_type = getFieldTypeByOptions(data[key]["type"]);
        if (["path"].includes(key)) {
            let loadCaption = document.createElement("p");
            loadCaption.innerText = getLocalizedName(key);
            loadCaption.id = "add-modal-caption";
            modalContent.appendChild(loadCaption);

            let loadField = document.createElement("select");
            loadField.setAttribute("name", key);
            loadField.className = "select";
            loadField.required = true;
            let big_data;
            $.ajax({
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json'
                },
                'async': false,
                'type': 'GET',
                'url': "/resources/static/json/mapping.json",
                'success': function (response) {
                    big_data = response;
                },
                error: function (jqXHR, textStatus, errorThrown) {
                    console.log(textStatus + ": " + jqXHR.status + " " + errorThrown);
                    M.toast({html: textStatus + ": " + jqXHR.status + " " + errorThrown});
                }
            });
            big_data["Все ресурсы"] = "*";
            for (let key_value in big_data) {
                let option = document.createElement("option");
                option.id = key;
                option.name = key;
                option.value = big_data[key_value];
                option.innerText = key_value;
                if (changeData != undefined && changeData[key] != null) {
                    if (changeData[key] == true) {
                        option.selected = true;
                    }
                }
                loadField.appendChild(option);
            }
            modalContent.appendChild(loadField);
            modalForm.appendChild(modalContent);

        } else if (["type"].includes(key)) {
            let loadCaption = document.createElement("p");
            loadCaption.innerText = getLocalizedName(key);
            loadCaption.id = "add-modal-caption";
            modalContent.appendChild(loadCaption);

            let loadField = document.createElement("select");
            loadField.setAttribute("name", key);
            loadField.className = "select";
            loadField.required = true;


            let big_data = {"Добавлять новые записи": "POST",
                "Изменять записи": "PUT",
                "Удалять записи": "DELETE",
                "Просматривать записи": "GET",
                "Все права": "*"};
            for (let key_value in big_data) {
                let option = document.createElement("option");
                option.id = key;
                option.name = key;
                option.value = big_data[key_value];
                option.innerText = key_value;
                if (changeData != undefined && changeData[key] != null) {
                    if (changeData[key] == true) {
                        option.selected = true;
                    }
                }
                loadField.appendChild(option);
            }
            modalContent.appendChild(loadField);
            modalForm.appendChild(modalContent);

        } else if (data[key]["enum"]!=undefined) {
            let loadCaption = document.createElement("p");
            loadCaption.innerText = getLocalizedName(key);
            loadCaption.id = "add-modal-caption";
            modalContent.appendChild(loadCaption);

            let loadField = document.createElement("select");
            loadField.setAttribute("name", key);
            loadField.className = "select";
            loadField.required = true;
            console.log(changeData);
            let big_data = data[key]["enum"];
            for (let key_value in big_data) {
                let option = document.createElement("option");
                option.name = key;
                option.value = big_data[key_value];
                option.innerText = big_data[key_value];
                if (changeData != undefined && changeData[key] != null) {
                    if (changeData[key] == big_data[key_value]) {
                        option.selected = true;
                    }
                }
                loadField.appendChild(option);
            }
            modalContent.appendChild(loadField);
            modalForm.appendChild(modalContent);

        } else if (["text", "int", "number"].includes(local_var_type)) {
            let loadCaption = document.createElement("p");
            loadCaption.innerText = getLocalizedName(key);
            loadCaption.id = "add-modal-caption";
            modalContent.appendChild(loadCaption);

            let loadField = document.createElement("input");
            loadField.id = key;
            loadField.name = key;
            if (changeData != undefined && changeData[key] != null) {
                loadField.value = changeData[key];
            }
            loadField.type = local_var_type;
            loadField.className = "validate";
            loadField.required = true;
            modalContent.appendChild(loadField);

            modalForm.appendChild(modalContent);
        } else if (["set", "list"].includes(local_var_type)) {
            let loadCaption = document.createElement("p");
            loadCaption.innerText = getLocalizedName(key);
            loadCaption.id = "add-modal-caption";
            modalContent.appendChild(loadCaption);

            let loadField = document.createElement("select");
            loadField.setAttribute("name", key);
            loadField.className = "select";
            loadField.multiple = true;
            loadField.required = true;

            let option = document.createElement("option");
            option.id = key;
            option.name = key;
            option.value = "";
            option.innerText = "--";
            loadField.appendChild(option);

            let big_data = getDataFromServer(data[key]["items"]["id"]);
            for (let key_value in big_data) {
                let option = document.createElement("option");
                option.name = key;
                option.value = big_data[key_value]["id"];
                option.innerText = big_data[key_value]["role"];
                if (changeData != undefined && changeData[key] != null) {
                    if (changeData[key].find(obj => obj.id == big_data[key_value]["id"])) {
                        option.selected = true;
                    }
                }
                loadField.appendChild(option);
            }
            modalContent.appendChild(loadField);
            modalForm.appendChild(modalContent);

        } else if (["boolean"].includes(local_var_type)) {
            let loadCaption = document.createElement("p");
            loadCaption.innerText = getLocalizedName(key);
            loadCaption.id = "add-modal-caption";
            modalContent.appendChild(loadCaption);

            let loadField = document.createElement("select");
            loadField.setAttribute("name", key);
            loadField.className = "select";
            loadField.required = true;


            let big_data = [true, false];
            for (let key_value in big_data) {
                let option = document.createElement("option");
                option.id = key;
                option.name = key;
                option.value = big_data[key_value].toString();
                option.innerText = big_data[key_value].toString();
                if (changeData != undefined && changeData[key] != null) {
                    if (changeData[key] == true) {
                        option.selected = true;
                    }
                }
                loadField.appendChild(option);
            }
            modalContent.appendChild(loadField);
            modalForm.appendChild(modalContent);

        } else {
            let loadCaption = document.createElement("p");
            loadCaption.innerText = getLocalizedName(key);
            loadCaption.id = "add-modal-caption";
            modalContent.appendChild(loadCaption);

            let loadField = document.createElement("select");
            loadField.setAttribute("name", key);
            loadField.className = "select";
            loadField.required = true;
            let big_data = getDataFromServer(data[key]["id"]);

            let option = document.createElement("option");
            option.id = key;
            option.name = key;
            option.value = "";
            option.innerText = "";
            loadField.appendChild(option);


            for (let key_value in big_data) {
                let option = document.createElement("option");
                option.id = key;
                option.name = key;
                option.value = big_data[key_value]["id"];
                option.innerText = big_data[key_value]["name"];
                if (changeData != undefined && changeData[key] != null) {
                    if (changeData[key]["id"] == big_data[key_value]["id"]) {
                        option.selected = true;
                    }
                }
                loadField.appendChild(option);
            }
            modalContent.appendChild(loadField);
            modalForm.appendChild(modalContent);
        }
    }

    let modalFooter = document.createElement("div");
    modalFooter.className = "modal-footer";
    modalFooter.id = "add-modal-buttons";

    let addButton = document.createElement("a");
    addButton.className = "modal-close waves-effect waves-green btn green accent-4";
    addButton.id = "add-load-button";
    //addButton.href = "#!";
    addButton.innerText = "Добавить";
    addButton.addEventListener("click", addFromEvent);
    modalFooter.appendChild(addButton);

    let exitButton = document.createElement("a");
    exitButton.className = "modal-close waves-effect waves-green btn red accent-4";
    exitButton.id = "exit-load-button";
    //exitButton.href = "#!";
    exitButton.innerText = "Выйти";

    modalFooter.appendChild(exitButton);

    modalForm.append(modalFooter);

    $("body").append(modalForm);
    $('.modal').modal();
    $('select').formSelect();
}
