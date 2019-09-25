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


    let loadEl = document.createElement("div");
    loadEl.id = id;
    loadEl.className = "load-form-item";
    changeBGColorOfLoad(loadEl);

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
        changeBGColorOfLoad(elItem);
        return elItem;
    });

    if (id) {

        let subjectsData = getJSONDataFromLocalStorage("subjects");
		//let subjectsData = data;
        let currentSubject = subjectsData.find(x => x.id == id);
        console.log('plans '+ currentSubject["plans_id"]);
        let ids = currentSubject["plans_id"].map(load => {
            return load;
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
    $.get("/simplemvcapp-entity-gen/"+value+"/", function(data, status){ 
    saveJSONDataToLocalStorage(value, data);
    var container = document.getElementById("data-tr-table");
    //var list = new List([1, 2, 3]);
    while (container.hasChildNodes()) {
         container.removeChild(container.lastChild);
    }
    if (data.length>0){
        var thread = createThread(data);
        container.appendChild(thread);
        var tbody = createTbody(data);
        container.appendChild(tbody);
    }
	});
    let dataformData = getJSONDataFromLocalStorage(value);
    
  
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
          td.append(item[key]);
          input.append(td);
        } else{
          id = item[key];
          input.id = id;
        }
      }
      tbody.appendChild(input);
  });
  return tbody;
}









const getJSONDataFromLocalStorage = (key) => {
    let dataString = localStorage.getItem(key);

    let data = JSON.parse(dataString);

	console.log(data);
    return data;
}

const saveJSONDataToLocalStorage = (key, data) => {
    let dataString = JSON.stringify(data);
	console.log(data);
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

    loadsFormList.forEach(loadEl => {
        if (loadEl.tagName == "DIV") {
            let loadID = +loadEl.id;
            let loadValue = loadEl.children[1].value;

            subjectLoads.push(loadID);
        }
    });

    let subject = {
        //id: ObjIdentity("subjects"),
        name: subjectName,
        plans_id: subjectLoads,
    };

    //addItemToLocalStorage("subjects", subject);
    $.ajax({
        headers: { 
            'Accept': 'application/json',
            'Content-Type': 'application/json' 
        },
        'type': 'POST',
        'url': "/simplemvcapp-entity-gen/syllabus/",
        'data': JSON.stringify(subject),
        'dataType': 'json',
        'success': function(response) {
            console.log(response);
                $.get("/simplemvcapp-entity-gen/syllabus/", function(data, status){ 
                saveJSONDataToLocalStorage("subjects", data);
                renderSubjectsList(data);
	});
        }

    });
    /*let data = getJSONDataFromLocalStorage("subjects");
    renderSubjectsList(data);*/

    clearSubjectFormFields();
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
    formHeaderText.innerText = "�?зменение учебного предмета";

    let subjectsData = getJSONDataFromLocalStorage("subjects");

    let loadsData = getJSONDataFromLocalStorage("loads");

	//let loadsData = data;

    let currentSubject = subjectsData.find(x => x.id == id);

    renderLoadsList(loadsData, id);

    document.getElementById("subject-name").value = currentSubject["name"];

    let loadsFormListEl = document.getElementById("loads-form");
    var loads;/*
    let loadEls = currentSubject["plans_id"].map(load => {

	$.getJSON("/simplemvcapp-entity-gen/learningseveritylist/"+load,function(data, status){ 
        console.log(data);
        return createFormLoadEl(data[0]["id"], loadsData.find(x => x.id == data[0]["id"])["name_id"], data[0]["hours"]);
	//return data;
    });
        //return createFormLoadEl(load["id"], loadsData.find(x => x.id == load["id"])["name"], load["hours"]);
    });*/
    console.log(currentSubject["plans_id"]);

 

    $.ajax({
        headers: { 
            'Accept': 'application/json',
            'Content-Type': 'application/json' 
        },
        'type': 'POST',
        'url': "/simplemvcapp-entity-gen/learningseveritylist/bylist/",
        'data': JSON.stringify(currentSubject["plans_id"]),
        'dataType': 'json',
        'success': function(response) {
            console.log(response);
            let loadEls = response.map(load => {
                return createFormLoadEl(load["id"], loadsData.find(x => x.id == load["name_id"])["name"], load["hours"]);
            });

            loadEls.forEach(el => {
                loadsFormListEl.appendChild(el);
            });
        }

    });



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

    let subjectLoads = [];

    /*
	here you need uose pose and get loads
	*/
	loadsFormList.forEach(loadEl => {
        if (loadEl.tagName == "DIV") {
            let loadID = +loadEl.id;
            let loadValue = loadEl.children[1].value;

            subjectLoads.push(loadID);
        }
    });
	
    let subject = {
        id: +localStorage.getItem("current_edit_subject_id"),
        name: subjectName,
        plans_id: subjectLoads,
    };

    localStorage.removeItem("current_edit_subject_id");
    setItemToLocalStorage("subjects", subject);

    let data = getJSONDataFromLocalStorage("subjects");
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

const changeBGColorOfLoad = (el) => {
    let colorID = el.id % rainbowColors.length - 1;
    $(el).css("background-color", rainbowColors[colorID]);
}

const openLoadCreateModal = (event) => {
    $("#loads-modal").modal("open");
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

const addLoadEvent = () => {
    let loadName = document.getElementById("load-input-field").value;

    let load = {
        id: ObjIdentity("loads"),
        name: loadName,
    };


	
    addItemToLocalStorage("loads", load);

    let data = getJSONDataFromLocalStorage("loads");
    renderLoadsList(data);

    document.getElementById("load-input-field").value = "";
}

$(document).ready(() => {


    $.get("/simplemvcapp-entity-gen/learningseverity/", function(data, status){ 
        console.log(data);
        
        renderLoadsList(data);
	saveJSONDataToLocalStorage("loads", data);
	});
	
    $.get("/simplemvcapp-entity-gen/syllabus/", function(data, status){ 
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

    $(() => {
        $(".subjects-form").droppable({
            drop: function(event, ui) {
                let loadEl = createFormLoadEl(ui.draggable.attr("id"), ui.draggable.text());

                $(loadEl).appendTo(".loads-form-list");

                ui.draggable.addClass("inactive").css("background-color", "#ffffff").draggable({ disabled: true });
            }
        });
    });

    let subjectsData = getJSONDataFromLocalStorage("subjects");
    let loadsData = getJSONDataFromLocalStorage("loads");
    let dataformData = [
        {name:"Подгруппы", key:"subgroup"},
        {name:"Кафедры", key:"lectern"},
        {name:"Типы кафежр", key:"lecterntype"},
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