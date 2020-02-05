"use strict";

const draggableProps = {
    helper: 'clone',
    revert: 'invalid',
    appendTo: 'body',
};

const rainbowColors = ["#FF0000", "#E2571E", "#FF7F00", "#FFFF00", "#00FF00", "#96bf33", "#0000FF", "#4B0082", "#8B00FF"];

const createSubjectItemEl = (id, name) => {
    let newItem = document.createElement("li");
    newItem.className = "subject-item collection-item";
    newItem.id = id;
    newItem.innerText = name;

    return newItem;
}

const createLoadItemEl = (id, name) => {
    let newItem = document.createElement("li");
    newItem.className = "load-item";
    newItem.id = id;
    newItem.innerText = name;

    return newItem;
}

const createFormLoadEl = (id, name, hours) => {

    let loadsData = getJSONDataFromLocalStorage("loads");
    let loadEl = document.createElement("div");
    loadEl.id = id;
    loadEl.className = "load-form-item";
    let name_id = loadsData.find(x => x.name == name)["id"];
    changeBGColorOfLoad(loadEl, name_id);

    let loadCap = document.createElement("p");
    loadCap.className = "load-caption";
    loadCap.innerText = name;

    let loadField = document.createElement("input");
    loadField.min = 0;
    loadField.defaultValue = 0;
    loadField.className = "load-field";
    loadField.type = "number";
    if (hours) {
        loadField.value = hours;
    }

    let removeButton = document.createElement("i");
    removeButton.className = "material-icons load-remove-button";
    removeButton.innerHTML = "close";
    removeButton.addEventListener("click", removeFormLoadEl);

    loadEl.append(loadCap);
    loadEl.append(loadField);
    loadEl.append(removeButton);

    return loadEl;
}

const removeFormLoadEl = (event) => {
    let id = event.currentTarget.parentElement.id;
    $(".loads-list")
        .find("#" + id)
        .removeClass("inactive")
        .draggable({ disabled: false })
        .css("background-color", $(event.currentTarget.parentElement).css("background-color"));
    event.currentTarget.parentElement.remove();
}

const renderSubjectsList = (data) => {
    let subjectsList = document.getElementById("subjects-list");

    while (subjectsList.firstChild) {
        subjectsList.removeChild(subjectsList.firstChild);
    }

    let itemsList = data.map(dataItem => {
        let subject = createSubjectItemEl(dataItem["id"], dataItem["name"]);
        subject.addEventListener("click", editSubjectEvent);

        return subject;
    });

    itemsList.forEach(item => subjectsList.appendChild(item));

    let createSubjectButton = document.createElement("li");
    createSubjectButton.id = "create-subject-button";
    createSubjectButton.class = "collection-item";

    let addIcon = document.createElement("a");
    addIcon.className = "material-icons";
    addIcon.innerText = "add";
    createSubjectButton.appendChild(addIcon);
    createSubjectButton.addEventListener("click", createSubjectEvent);

    subjectsList.appendChild(createSubjectButton);
}

const renderLoadsList = (data, id) => {


    let loadsList = document.getElementById("loads-list");

    while (loadsList.firstChild) {
        loadsList.removeChild(loadsList.firstChild);
    }

    let itemsList = data.map(dataItem => {
        let elItem = createLoadItemEl(dataItem["id"], dataItem["name"]);
        $(elItem).draggable(draggableProps);
        let loadsData = getJSONDataFromLocalStorage("loads");
        let name_id = loadsData.find(x => x.name == dataItem["name"])["id"];
        changeBGColorOfLoad(elItem, name_id);

        return elItem;
    });

    if (id) {

        let subjectsData = getJSONDataFromLocalStorage("subjects");
		//let subjectsData = data;
        let currentSubject = subjectsData.find(x => x.id == id);
        let ids = currentSubject["plans_id"].map(load => {
            return load.name_id;
        })


        itemsList.forEach(item => {
            if (ids.includes(+item.id)) {
                $(item).addClass("inactive").css("background-color", "#ffffff").draggable({ disabled: true });
            }
        });
    }

    itemsList.forEach(item => loadsList.appendChild(item));


    let createLoadButton = document.createElement("li");
    createLoadButton.id = "create-load-button";

    let addIcon = document.createElement("a");
    addIcon.className = "material-icons";
    addIcon.innerText = "add";
    addIcon.dataset.target = "loads-modal";
    addIcon.addEventListener("click", openLoadCreateModal);

    createLoadButton.appendChild(addIcon);

    loadsList.appendChild(createLoadButton);
}





const createDataItemEl = (key, name) => {
    let newItem = document.createElement("li");
    newItem.className = "subject-item collection-item";
    newItem.id = key;
    newItem.innerText = name;
    return newItem;
}

const renderDataList = (data) => {
    let subjectsList = document.getElementById("data-list");

    while (subjectsList.firstChild) {
        subjectsList.removeChild(subjectsList.firstChild);
    }

    let itemsList = data.map(dataItem => {
        let subject = createDataItemEl(dataItem["key"], dataItem["name"]);
        subject.addEventListener("click", openTableEvent);
        subject.class = "btn waves-effect waves-teal";

        return subject;
    });

    itemsList.forEach(item => subjectsList.appendChild(item));

    let createSubjectButton = document.createElement("li");
    createSubjectButton.id = "create-data-button";
    createSubjectButton.class = "collection-item";
/*
    let addIcon = document.createElement("a");
    addIcon.className = "material-icons";
    addIcon.innerText = "add";
    createSubjectButton.appendChild(addIcon);
    createSubjectButton.addEventListener("click", createSubjectEvent);

    subjectsList.appendChild(createSubjectButton);*/


   
}

const openTableEvent = (event) => {
    //var List = require("collections/list");
    let value = event.currentTarget.id;
    renderTableEvent(value);
}


const renderTableEvent = (value) => {
    localStorage.setItem("current_open_table", value);
    clearPostFromModal();
    createPostFormModal();
    $.get("/"+value+"/", function(data, status){ 
    saveJSONDataToLocalStorage(value, data);
    var container = document.getElementById("data-tr-table");
   
    while (container.hasChildNodes()) {
         container.removeChild(container.lastChild);
    }
      var a = document.createElement("a");
      a.className = 'btn-floating btn-large waves-effect waves-light btn greeen accent-4';
      a.dataset.target = "add-modal";
      a.addEventListener("click", openAddCreateModal);
      var i = document.createElement("i");
      i.className = 'material-icons';
      i.textContent = 'add';
      a.append(i);
      container.append(a)
    if (data.length>0){
        var thread = createThread(data);
        container.appendChild(thread);
        var tbody = createTbody(data);
        container.appendChild(tbody);
    }
    });

}

const createThread = (data) => {
  var thread = document.createElement("tr");
  for (let key in data[0]){
    var th = document.createElement("th");
    if (key != 'id'){
      th.append(key);
      thread.append(th);
    }
  }
  var th = document.createElement("th");
  th.append("Действия");
  thread.append(th);
  return thread;
}

const createTbody = (data) => {
  var tbody = document.createElement("tbody");
  data.forEach(item => {
      var input = document.createElement("tr");
      for (let key in item){
        var id = 0;
        var td = document.createElement("td");
        if (key != 'id'){
            if (typeof(item[key])!="object"){
                td.append(item[key]);
            } else if(item[key]==null) {
                td.append("None");
            } else {
                td.append(item[key].name);
            }
          input.append(td);
        } else{
          id = item[key];
          input.id = id;
        }
      }

      
      var td = document.createElement("td");
      
      var a = document.createElement("a");
      a.className = 'btn-floating btn-large waves-effect waves-light green accent-4';
      a.value = input.id;
      a.addEventListener("click", deleteValueFromTable);
      var i = document.createElement("i");
      i.className = 'material-icons';
      i.textContent = 'mode_edit';
      a.append(i);
      td.append(a);
     
      
      var a = document.createElement("a");
      a.className = 'btn-floating btn-large waves-effect waves-light btn red';
      a.href = '#1';
      a.value = input.id;
      //a.textContent = 'Удалить';
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

const deleteValueFromTable = (event) => {
    let id = event.currentTarget.value;
    var value = localStorage.getItem("current_open_table");    
    $.ajax({
        headers: { 
            'Accept': 'application/json',
            'Content-Type': 'application/json' 
        },
        'type': 'DELETE',
        'url': "/"+value+"/"+id,
        'success': function(response) {
            renderTableEvent(value);
        }

    });
}


var ID = function () {
  // Math.random should be unique because of its seeding algorithm.
  // Convert it to base 36 (numbers + letters), and grab the first 9 characters
  // after the decimal.
  return '_' + Math.random().toString(36).substr(2, 9);
};




const getJSONDataFromLocalStorage = (key) => {
    let dataString = localStorage.getItem(key);

    let data = JSON.parse(dataString);

    return data;
}

const saveJSONDataToLocalStorage = (key, data) => {
    let dataString = JSON.stringify(data);
    localStorage.setItem(key, dataString);
}

const ObjIdentity = (key) => {
    let strID = localStorage.getItem(key + "_identity");

    let id = +strID;

    localStorage.setItem(key + "_identity", id + 1);

    return id;
}

const addSubjectEvent = () => {
    let subjectName = document.getElementById("subject-name").value;

    let loadsFormList = Array.from(document.getElementById("loads-form").childNodes);

    let subjectLoads = [];
    let loadsData = getJSONDataFromLocalStorage("loads");

   loadsFormList.forEach(loadEl => {
        if (loadEl.tagName == "DIV") {
            let loadID = +loadEl.id;
            let name = loadEl.children[0].innerHTML;
            let loadValue = loadEl.children[1].value;
            let learningseveritylist = {
                name_id: loadsData.find(x => x.name == name)["id"],
                hours: loadValue
            }
            if (loadID!=0){
                learningseveritylist.id = loadID;
            }
        
            subjectLoads.push(learningseveritylist);
           
        }
    });
    let syllabus = {
        name: subjectName,
        plans_id: subjectLoads
    };
    
    console.log("edit syllabus ", syllabus);
    
    $.ajax({
        headers: { 
            'Accept': 'application/json',
            'Content-Type': 'application/json' 
        },
        'type': 'POST',
        'async': false,
        'url': "/syllabus/",
        'data': JSON.stringify(syllabus),
        'dataType': 'json',
        'success': function(response) {
            console.log(response);
        }

    });
    clearSubjectFormFields();
    let response = getDataFromServer("syllabus");
    saveJSONDataToLocalStorage("subjects", response);
    renderSubjectsList(response);


}

const addItemToLocalStorage = (listName, item) => {
    let data = getJSONDataFromLocalStorage(listName);

    data.push(item);

    saveJSONDataToLocalStorage(listName, data);
}

const clearSubjectFormFields = () => {
    let loadsFormListEl = document.getElementById("loads-form");

    document.getElementById("subject-name").value = "";

    while (loadsFormListEl.firstChild) {
        loadsFormListEl.removeChild(loadsFormListEl.firstChild);
    };
}

const subjectCreateMode = () => {
    clearSubjectFormFields();

    let subjectsFormHeader = document.getElementById("subjects-form-header");
    let formHeaderText = subjectsFormHeader.firstElementChild;
    formHeaderText.innerText = "Добавление учебного предмета";

    let formButtons = document.getElementById("form-buttons");

    while (formButtons.firstChild) {
        formButtons.removeChild(formButtons.firstChild);
    }

    let addSubjectButton = document.createElement("a");
    addSubjectButton.id = "update-subject-button";
    addSubjectButton.className = "waves-effect waves-light btn green accent-4";
    addSubjectButton.innerText = "Добавить";
    addSubjectButton.addEventListener("click", addSubjectEvent);
    formButtons.appendChild(addSubjectButton);
}

const subjectEditMode = (id) => {
    clearSubjectFormFields();

    let subjectsFormHeader = document.getElementById("subjects-form-header");
    let formHeaderText = subjectsFormHeader.firstElementChild;
    formHeaderText.innerText = "Изменение учебного предмета";

    let subjectsData = getJSONDataFromLocalStorage("subjects");

    let loadsData = getJSONDataFromLocalStorage("loads");

	//let loadsData = data;

    let currentSubject = subjectsData.find(x => x.id == id);

    renderLoadsList(loadsData, id);

    document.getElementById("subject-name").value = currentSubject["name"];

    let loadsFormListEl = document.getElementById("loads-form");
    
    console.log(loadsFormListEl);

    
    console.log(currentSubject["plans_id"]);
    
   let loadEls = currentSubject["plans_id"].map(load => {
                return createFormLoadEl(load["id"], loadsData.find(x => x.id == load["name_id"])["name"], load["hours"]);
            });

    loadEls.forEach(el => {
                loadsFormListEl.appendChild(el);
            });
 
/*
    $.ajax({
        headers: { 
            'Accept': 'application/json',
            'Content-Type': 'application/json' 
        },
        'type': 'POST',
        'url': "/learningseveritylist/bylist/",
        'data': JSON.stringify(currentSubject["plans_id"]),
        'dataType': 'json',
        'success': function(response) {
            console.log("/learningseveritylist/bylist/", response);
            let loadEls = response.map(load => {
                return createFormLoadEl(load["id"], loadsData.find(x => x.id == load["name_id"])["name"], load["hours"]);
            });

            loadEls.forEach(el => {
                loadsFormListEl.appendChild(el);
            });
        }

    });
*/


    /*
    loadEls.forEach(el => {
        loadsFormListEl.appendChild(el);
    });*/

    let formButtons = document.getElementById("form-buttons");

    while (formButtons.firstChild) {
        formButtons.removeChild(formButtons.firstChild);
    }

    let updateSubjectButton = document.createElement("a");
    updateSubjectButton.id = "update-subject-button";
    updateSubjectButton.className = "waves-effect waves-light btn blue accent-4";
    updateSubjectButton.innerText = "Принять";
    updateSubjectButton.addEventListener("click", updateSubjectEvent);
    formButtons.appendChild(updateSubjectButton);

    let cancelSubjectButton = document.createElement("a");
    cancelSubjectButton.id = "cancel-subject-button";
    cancelSubjectButton.className = "waves-effect waves-light btn yellow accent-4";
    cancelSubjectButton.innerText = "Отменить";
    cancelSubjectButton.addEventListener("click", cancelSubjectEvent);
    formButtons.appendChild(cancelSubjectButton);

    let exitSubjectButton = document.createElement("a");
    exitSubjectButton.id = "exit-subject-button";
    exitSubjectButton.className = "waves-effect waves-light btn red accent-4";
    exitSubjectButton.innerText = "Выход";
    exitSubjectButton.addEventListener("click", exitSubjectEvent);
    formButtons.appendChild(exitSubjectButton);

    localStorage.setItem("current_edit_subject_id", currentSubject["id"]);
}

const editSubjectEvent = (event) => {
    let id = event.currentTarget.id;
    subjectEditMode(+id);
}

const createSubjectEvent = (event) => {
    subjectCreateMode();
}

const setItemToLocalStorage = (listName, item) => {
    let data = getJSONDataFromLocalStorage(listName);

    let i = data.findIndex(x => x.id == item.id);

    data[i] = item;

    saveJSONDataToLocalStorage(listName, data);
}

const updateSubjectEvent = () => {
    let subjectName = document.getElementById("subject-name").value;

    let loadsFormList = Array.from(document.getElementById("loads-form").childNodes);
    let loadsData = getJSONDataFromLocalStorage("loads");


    let subjectLoads = [];
    loadsFormList.forEach(loadEl => {
        if (loadEl.tagName == "DIV") {
            let loadID = +loadEl.id;
            let name = loadEl.children[0].innerHTML;
            let loadValue = loadEl.children[1].value;
            let learningseveritylist = {
                name_id: loadsData.find(x => x.name == name)["id"],
                hours: loadValue
            }
            if (loadID!=0){
                learningseveritylist.id = loadID;
            }
        
            subjectLoads.push(learningseveritylist);
           
        }
    });
    let currId = localStorage.getItem("current_edit_subject_id");
    console.log("PUT INTO SYLLABUS", currId, subjectLoads);
    let syllabus = {
        id: currId,
        name: subjectName,
        plans_id: subjectLoads
    };
    
    console.log("edit syllabus ", syllabus);
    
    $.ajax({
        headers: { 
            'Accept': 'application/json',
            'Content-Type': 'application/json' 
        },
        'type': 'PUT',
        'async': false,
        'url': "/syllabus/"+currId,
        'data': JSON.stringify(syllabus),
        'dataType': 'json',
        'success': function(response) {
            console.log(response);

        }

    });

    
    /*
	here you need uose pose and get loads
	*/

    localStorage.removeItem("current_edit_subject_id");
    
    let data = getDataFromServer("syllabus");
    console.log(data);
    saveJSONDataToLocalStorage("subjects", data);
    renderSubjectsList(data);

    subjectCreateMode();

}

const cancelSubjectEvent = () => {
    let id = +localStorage.getItem("current_edit_subject_id");
    subjectEditMode(id);
}

const exitSubjectEvent = () => {
    localStorage.removeItem("current_edit_subject_id");
    subjectCreateMode();
}

const changeBGColorOfLoad = (el, name_id) => {
    let colorID = name_id % rainbowColors.length - 1;
    $(el).css("background-color", rainbowColors[colorID]);
}

const openLoadCreateModal = (event) => {
    $("#loads-modal").modal("open");
}

const openAddCreateModal = (event) => {
    $("#add-modal").modal("open");
}

const createLoadsFormModal = () => {
    let modalForm = document.createElement("div");
    modalForm.className = "modal";
    modalForm.id = "loads-modal";

    let modalContent = document.createElement("div");
    modalContent.className = "modal-content";

    let modalHeader = document.createElement("h4");
    modalHeader.innerText = "Создание нового вида нагрузки";
    modalHeader.id = "loads-modal-header";
    modalContent.appendChild(modalHeader);

    let loadCaption = document.createElement("p");
    loadCaption.innerText = "Название нагрузки";
    loadCaption.id = "loads-modal-caption";
    modalContent.appendChild(loadCaption);

    let loadField = document.createElement("input");
    loadField.id = "load-input-field";
    modalContent.appendChild(loadField);

    modalForm.appendChild(modalContent);

    let modalFooter = document.createElement("div");
    modalFooter.className = "modal-footer";
    modalFooter.id = "loads-modal-buttons";

    let addButton = document.createElement("a");
    addButton.className = "modal-close waves-effect waves-green btn green accent-4";
    addButton.id = "add-load-button";
    addButton.innerText = "Добавить";
    addButton.addEventListener("click", addLoadEvent);
    modalFooter.appendChild(addButton);

    let exitButton = document.createElement("a");
    exitButton.className = "modal-close waves-effect waves-green btn red accent-4";
    exitButton.id = "exit-load-button";
    exitButton.innerText = "Выйти";

    modalFooter.appendChild(exitButton);

    modalForm.append(modalFooter);

    $("body").append(modalForm);
}

const createSubjectFormModal = () => {
    let modalForm = document.createElement("div");
    modalForm.className = "modal";
    modalForm.id = "subject-modal";

    let modalContent = document.createElement("div");
    modalContent.className = "modal-content";

    let modalHeader = document.createElement("h4");
    modalHeader.innerText = "Создание нового предмета";
    modalHeader.id = "subject-modal-header";
    modalContent.appendChild(modalHeader);

    let loadCaption = document.createElement("p");
    loadCaption.innerText = "Название предмета";
    loadCaption.id = "subject-modal-caption";
    modalContent.appendChild(loadCaption);

    let loadField = document.createElement("input");
    loadField.id = "subject-input-field";
    modalContent.appendChild(loadField);

    modalForm.appendChild(modalContent);

    let modalFooter = document.createElement("div");
    modalFooter.className = "modal-footer";
    modalFooter.id = "subject-modal-buttons";

    let addButton = document.createElement("a");
    addButton.className = "modal-close waves-effect waves-green btn green accent-4";
    addButton.id = "add-load-button";
    addButton.innerText = "Добавить";
    addButton.addEventListener("click", addLoadEvent);
    modalFooter.appendChild(addButton);

    let exitButton = document.createElement("a");
    exitButton.className = "modal-close waves-effect waves-green btn red accent-4";
    exitButton.id = "exit-load-button";
    exitButton.innerText = "Выйти";

    modalFooter.appendChild(exitButton);

    modalForm.append(modalFooter);

    $("body").append(modalForm);
}




const addLoadEvent = () => {
    let loadName = document.getElementById("load-input-field").value;

    let load = {
        id: ObjIdentity("loads"),
        name: loadName,
    };
    
    let to_load = {
        name: loadName
    }

    $.ajax({
        headers: { 
            'Accept': 'application/json',
            'Content-Type': 'application/json' 
        },
        'type': 'POST',
        'url': "/learningseverity/",
        'data': JSON.stringify(to_load),
        'dataType': 'json',
        'success': function(response) {
            console.log(response);
                $.get("/learningseverity/", function(data, status){ 
                saveJSONDataToLocalStorage("loads", data);
                console.log(data);
                renderSubjectsList(data);
	});
        }

    });
	
   // addItemToLocalStorage("loads", load);

    let data = getJSONDataFromLocalStorage("loads");
    renderLoadsList(data);

    document.getElementById("load-input-field").value = "";
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
    let name = localStorage.getItem("current_open_table");
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
    renderTableEvent(name);
}

const getJSONfromForm = (formname) => {
    let formData = $("#"+formname).serializeArray();
    let name = localStorage.getItem("current_open_table");
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
    let name = localStorage.getItem("current_open_table");
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
          option.value = big_data[key_value]["id"];
          option.innerText = big_data[key_value]["name"];
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



$(document).ready(() => {


    $.get("/learningseverity/", function(data, status){ 
        console.log(data);
	saveJSONDataToLocalStorage("loads", data);        
        renderLoadsList(data);
	});
	
    $.get("/syllabus/", function(data, status){ 
        saveJSONDataToLocalStorage("subjects", data);
        renderSubjectsList(data);
    
	});
	
    if (localStorage.getItem("subjects_identity") == undefined) {
        localStorage.setItem("subjects_identity", 0);
    }

    if (localStorage.getItem("loads_identity") == undefined) {
        localStorage.setItem("loads_identity", 0);
    }


    createLoadsFormModal();

    $("ul.tabs").tabs();
    $(".sidenav").sidenav();
    $('.modal').modal();
    $('select').formSelect();


    $(() => {
        $(".subjects-form").droppable({
            drop: function(event, ui) {
                let loadEl = createFormLoadEl(0, ui.draggable.text());

                $(loadEl).appendTo(".loads-form-list");

                ui.draggable.addClass("inactive").css("background-color", "#ffffff").draggable({ disabled: true });
            }
        });
    });

    let subjectsData = getJSONDataFromLocalStorage("subjects");
    let loadsData = getJSONDataFromLocalStorage("loads");
    let dataformData = [
        {name:"Подгруппы", key:"subgroup"},
        {name:"Группы", key:"groups"},
        {name:"Поток", key:"flow"},
        {name:"Кафедры", key:"lectern"},
        {name:"Типы кафедр", key:"lecterntype"},
        {name:"Занятия", key:"lesson"},
        //{name:"Пла ханятийн", key:"neededlesson"},
        {name:"Роли", key:"role"},
        {name:"Специальности", key:"speciality"},
        {name:"Предметы", key:"subject"},
        {name:"Предподаватели", key:"teacher"},
        //{name:"canteach", key:"canteach"},
        {name:"Аудитории", key:"classroom"},
        {name:"Типы аудиторий", key:"classroomtype"},
        {name:"Корпуса", key:"corps"},
        {name:"Факультеты", key:"faculty"},
        {name:"Смены", key:"turn"}
        //{name:"Типы занятий", key:"typeoflesson"}
    ]

    renderDataList(dataformData);

});