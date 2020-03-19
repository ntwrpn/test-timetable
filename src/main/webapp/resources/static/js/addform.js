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
      case "boolean":
        return "boolean";
      default:
        return type;
    }
  }


const addFromEvent = () => {
    let json = getJSONfromForm("add-modal-content");

    let name = "/"+localStorage.getItem("current_open_table")+"/";
    console.log(json);
    let type = 'POST';
    if (json['id']!=undefined && json['id']!=null){
        type = 'PUT';
        name = name+json['id'];
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
        'success': function(response) {
            console.log(response);
        }
    });
    openTableEvent(name);
}

const getJSONfromForm = (formname) => {
    let formData = $("#"+formname).serializeArray();
    let name =  localStorage.getItem("current_open_table");
    let optionType = getListDataFromServer(name);
    console.log(formData);
    let json = {};
    for (let data in formData){
        if (getFieldTypeByOptions(optionType[formData[data]['name']])=="text"){
        json[formData[data]['name']] = formData[data]['value'];
        }
        else if (getFieldTypeByOptions(optionType[formData[data]['name']])=="int"){
             json[formData[data]['name']] = parseInt(formData[data]['value']);
        } else if (["set", "list"].includes(optionType[formData[data]['name']])){
            let current_data = getDataFromServer([formData[data]['name']]+"/"+formData[data]['value']);
            if (typeof(json[formData[data]['name']]) == "undefined"){
                json[formData[data]['name']] = [];
            }
            json[formData[data]['name']].push(current_data);
        } else if (["boolean", "uuid", "id"].includes(optionType[formData[data]['name']])){
            if (formData[data]['value']!="undefined"){
                json[formData[data]['name']]=formData[data]['value'];
            }
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

const createPostFormModal = (changeData) => {
    console.log(changeData);
    let name =  localStorage.getItem("current_open_table");
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
             let loadField = document.createElement("input");
            loadField.id = key;
            loadField.name = key;
            loadField.type = "hidden";
            loadField.value = changeData[key];
            modalContent.appendChild(loadField);
            continue;
        } else if (key=="password" && changeData.id!=undefined){
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
        if (changeData!=undefined && changeData[key]!=null){
            loadField.value = changeData[key];
        }
        loadField.type = local_var_type;
        loadField.className = "validate";
        loadField.required = true;
        modalContent.appendChild(loadField);

        modalForm.appendChild(modalContent);
      } else if (["set", "list"].includes(local_var_type)){
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
        if (["set", "list"].includes(local_var_type)) {
            big_data = getDataFromServer(key);
        } else{
            big_data = getDataFromServer(local_var_type);
        }
        for (let key_value in big_data){
          let option = document.createElement("option");
          console.log(big_data[key_value]);
          console.log(changeData[key]);
          option.id = key;
          option.name = key;
          option.value = big_data[key_value]["id"];
          option.innerText = big_data[key_value]["role"];
          if (changeData!=undefined && changeData[key]!=null){
            if (changeData[key].find(obj => obj.id == big_data[key_value]["id"])){
                option.selected=true;
            }
           }
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
            
            let big_data = [true, false];
            for (let key_value in big_data){
              let option = document.createElement("option");
              option.id = key;
              option.name = key;
              option.value = big_data[key_value].toString();
              option.innerText = big_data[key_value].toString();
              if (changeData!=undefined && changeData[key]!=null){
                if (changeData[key]==true){
                    option.selected=true;
                }
               }
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
        console.log(key);
        let big_data = getDataFromServer(local_var_type);
        for (let key_value in big_data){
          let option = document.createElement("option");
          option.id = key;
          option.name = key;
          option.value = big_data[key_value]["id"];
          option.innerText = big_data[key_value]["name"];
          console.log(changeData);

          if (changeData!=undefined && changeData[key]!=null){
            if (changeData[key] == big_data[key_value]["id"]){
                option.selected=true;
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
