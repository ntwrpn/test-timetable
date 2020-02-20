const openAddCreateModal = (event) => {
    $("#add-modal").modal("open");
}

//CREATE ADD FORM TO TABLE
const getFieldTypeByOptions = (type) => {
    switch(type){
      case "integer":
        return "number";
      case "string":
        return "text";
      case "date":
        return "date";
      default:
        return type;
    }
  }


const addFromEvent = () => {
    let json = getJSONfromForm("add-modal-content");
    let name = "users";
    console.log(json);
    
    $.ajax({
        headers: { 
            'Accept': 'application/json',
            'Content-Type': 'application/json' 
        },
        'type': 'POST',
        'async': false,
        'url': "/"+name+"/",
        'data': JSON.stringify(json),
        'dataType': 'json',
        'success': function(response) {
            console.log(response);
        }
    });
    openTableEvent(name);
}

const getJSONfromForm = (formname) => {
    let formData = $("#"+formname).serializeArray();
    let name = "users";
    let optionType = getListDataFromServer(name);
    console.log(formData);
    let json = {};
    for (let data in formData){
        if (getFieldTypeByOptions(optionType[formData[data]['name']])=="text"){
        json[formData[data]['name']] = formData[data]['value'];
        }
        else if (getFieldTypeByOptions(optionType[formData[data]['name']])=="int"){
             json[formData[data]['name']] = parseInt(formData[data]['value']);
        } else if (optionType[formData[data]['name']]=="set"){
            let current_data = getDataFromServer([formData[data]['name']]+"/"+formData[data]['value']);
            if (typeof(json[formData[data]['name']]) == "undefined"){
                json[formData[data]['name']] = [];
            }
            console.log(formData[data]);

            json[formData[data]['name']].push(current_data);
        } 
        else {
            let current_data = getDataFromServer(optionType[formData[data]['name']]+"/"+formData[data]['value']);
            json[formData[data]['name']] = current_data;
        }


    }
    console.log(json);
    return json;
}

const getListDataFromServer = (name) => {
 let data;
 $.ajax({
        headers: { 
            'Accept': 'application/json',
            'Content-Type': 'application/json' 
        },
        'async': false,
        'type': 'OPTIONS',
        'url': "/"+name+"/",
        'success': function(response) {
            data = response;
        }
    });
return data;
}

const getDataFromServer = (name) => {
 let data;
 $.ajax({
        headers: { 
            'Accept': 'application/json',
            'Content-Type': 'application/json' 
        },
        'async': false,
        'type': 'GET',
        'url': "/"+name+"/",
        'success': function(response) {
            data = response;
        }
    });
return data;
}


const clearPostFromModal = () => {
    var container = document.getElementById("add-modal");
    if (container!=null){
        while (container.hasChildNodes()) {
             container.removeChild(container.lastChild);
        }
    }
}

const createPostFormModal = () => {
    let name = "users";
    let data = getListDataFromServer(name);

    let modalForm = document.getElementById("add-modal");
    if (modalForm==null){
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


    for (let key in data){
        if (key=="id"){
            continue;
        }
        let local_var_type = getFieldTypeByOptions(data[key]);
      if(["text", "int"].includes(local_var_type)){
        let loadCaption = document.createElement("p");
        loadCaption.innerText = key;
        loadCaption.id = "add-modal-caption";
        modalContent.appendChild(loadCaption);

        let loadField = document.createElement("input");
        loadField.id = key;
        loadField.name = key;
        loadField.type = local_var_type;
        loadField.className = "validate";
        loadField.required = true;
        modalContent.appendChild(loadField);

        modalForm.appendChild(modalContent);
      } else if (["set"].includes(local_var_type)){
        let loadCaption = document.createElement("p");
        loadCaption.innerText = key;
        loadCaption.id = "add-modal-caption";
        modalContent.appendChild(loadCaption);

        let loadField = document.createElement("select");
        loadField.setAttribute("name", key);
        loadField.className = "select";
        loadField.multiple = true;
        loadField.required = true;
        let big_data;
        if ("set"==local_var_type) {
            big_data = getDataFromServer(key);
        } else{
            big_data = getDataFromServer(local_var_type);
        }
        for (let key_value in big_data){
          let option = document.createElement("option");
          option.id = key;
          option.name = key;
          option.value = big_data[key_value]["user_role_id"];
          option.innerText = big_data[key_value]["role"];
          loadField.appendChild(option);
        }
        modalContent.appendChild(loadField);
        modalForm.appendChild(modalContent);
      
        }
        else if (["boolean"].includes(local_var_type)){
            console.log(local_var_type);

            let loadCaption = document.createElement("p");
            loadCaption.innerText = key;
            loadCaption.id = "add-modal-caption";
            modalContent.appendChild(loadCaption);
    
            let loadField = document.createElement("select");
            loadField.setAttribute("name", key);
            loadField.className = "select";
            loadField.required = true;
            
            let big_data = ["true", "false"];
            for (let key_value in big_data){
              let option = document.createElement("option");
              option.id = key;
              option.name = key_value;
              option.value = key_value;
              option.innerText = key_value;
              loadField.appendChild(option);
            }
            modalContent.appendChild(loadField);
            modalForm.appendChild(modalContent);
          
        }
      else{
        let loadCaption = document.createElement("p");
        loadCaption.innerText = key;
        loadCaption.id = "add-modal-caption";
        modalContent.appendChild(loadCaption);

        let loadField = document.createElement("select");
        loadField.setAttribute("name", key);
        loadField.className = "select";
        loadField.required = true;
        
        let big_data = getDataFromServer(local_var_type);
        for (let key_value in big_data){
          let option = document.createElement("option");
          option.id = key;
          option.name = key;
          option.value = big_data[key_value]["id"];
          option.innerText = big_data[key_value]["name"];
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
    addButton.className = "waves-effect waves-green btn green accent-4";
    addButton.id = "add-load-button";
    addButton.innerText = "Добавить";
    addButton.addEventListener("click", addFromEvent);
    modalFooter.appendChild(addButton);

    let exitButton = document.createElement("a");
    exitButton.className = "modal-close waves-effect waves-green btn red accent-4";
    exitButton.id = "exit-load-button";
    exitButton.innerText = "Выйти";

    modalFooter.appendChild(exitButton);

    modalForm.append(modalFooter);

    $("body").append(modalForm);
    $('.modal').modal();
    $('select').formSelect();
}
