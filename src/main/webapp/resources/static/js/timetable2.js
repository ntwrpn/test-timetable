"use strict";


const getDataFromServer = (name, id, parameter) => {
    let url = "/api/" + name + "/";
    if (parameter != undefined) {
        url = url + "?" + parameter + "=" + id
    } else if (id != undefined) {
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
            console.log(url + " " + textStatus + ": " + jqXHR.status + " " + errorThrown);
            M.toast({html: url + " " + textStatus + ": " + jqXHR.status + " " + errorThrown});
        }
    });
    return data;
}

const draggableProps = {
    /*opacity: 0.5,
     cursor: "move",
     helper: "clone",
     revert: 'invalid',
     appendTo: 'body',*/
    cursor: "move",
    cursorAt: {top: -12, left: -20},
    scroll: true,
    refreshPositions: true,
    helper: function (event) {
        let id = event.currentTarget.id
        let name = document.getElementById("name" + id);
        if (name == null) {
            name = event.currentTarget;
        }
        return $("<div class='ui-widget-header'>" + name.innerText + "</div>");
    },
    drag: function (event, ui) {
        let elem = getComputedStyle(document.getElementsByClassName('rTable')[0]);

        ui.offset.left = (ui.offset.left) / elem.zoom;
        ui.offset.top = (ui.offset.top) / elem.zoom;

        /*TweenMax.set(this.target, { x: "+=" + (this.x - SX) * ratio, y: "+=" + (this.y - SY) * ratio });*/
    }
};

var ID = function () {
    // Math.random should be unique because of its seeding algorithm.
    // Convert it to base 36 (numbers + letters), and grab the first 9 characters
    // after the decimal.
    return '_' + Math.random().toString(36).substr(2, 9);
};


const rainbowColors = ["#FF0000", "#E2571E", "#FF7F00", "#FFFF00", "#00FF00", "#96bf33", "#0000FF", "#4B0082", "#8B00FF"];

const removeFormLoadEl = (event) => {
    let id = event.currentTarget.parentElement.id;
    $(".loads-list")
            .find("#" + id)
            .removeClass("inactive")
            .draggable({disabled: false})
            .css("background-color", $(event.currentTarget.parentElement).css("background-color"));
    event.currentTarget.parentElement.remove();
}


const createLoadItemEl = (id, name) => {
    let newItem = document.createElement("div");
    newItem.className = "drag-item";
    newItem.id = id;
    let loadCap = document.createElement("p");
    loadCap.id = 'name' + id;
    loadCap.className = "load-caption";
    loadCap.innerText = name;
    newItem.append(loadCap);
    return newItem;
}


const changeBGColorOfLoad = (el, name_id) => {
    let colorID = name_id % rainbowColors.length - 1;
    $(el).css("background-color", rainbowColors[colorID]);
}


const printTest = (event) => {

    SaveTable();
    let classes = getJSONDataFromLocalStorage("classes");
    let flow = getJSONDataFromLocalStorage("flow");
    if (event.currentTarget.innerText.indexOf("Практ") != -1) {
        RenderTable(flow.groups, times, classes, 1);
    } else if (event.currentTarget.innerText.indexOf("Поток") != -1) {
        RenderTable(flow.groups, times, classes, 2);
    } else if (event.currentTarget.innerText.indexOf("Лекци") != -1) {
        RenderTable(flow.groups, times, classes, 4);
    } else {
        RenderTable(flow.groups, times, classes, 1);
    }
    reloadDraggable();


    $("ul.tabs").tabs();
    $(".sidenav").sidenav();
    $('.modal').modal();
    $('select').formSelect();

}

const renderLoadsList = (data, type_of_lesson) => {
    let loadsList = document.getElementById("loads-list");
    let ulList = document.createElement("ul");
    ulList.className = "collapsible";
    while (loadsList.firstChild) {
        loadsList.removeChild(loadsList.firstChild);
    }

    type_of_lesson.forEach(lesson => {
        let li = document.createElement("li");
        let lesson_type_data = data.filter(x => x.severities.some(y => y.severity.id == lesson.id));
        let Caption = document.createElement("div");
        Caption.innerText = lesson.name;
        Caption.id = lesson.id;
        Caption.className = "collapsible-header";
        Caption.addEventListener("click", printTest);
        let Body = document.createElement("div");
        Body.className = "collapsible-body";
        li.appendChild(Caption);
        let itemsList = lesson_type_data.map(dataItem => {
            let elItem = createLoadItemEl(dataItem["id"], dataItem["name"]);
            $(elItem).draggable({
                press: function (event, ui) {
                    if (ui.draggable[0].className.includes("lessonType0")) {
                        console.log("Lection");
                    }
                    ;
                },
                onPress: function () {
                    SX = this.x;
                    SY = this.y;
                },
                onDrag: function () {
                    let SX = 1
                    let SY = 1;
                    let elem = getComputedStyle(document.getElementsByClassName('rTable')[0]);
                    var ratio = (1 / elem.zoom) - 1;
                    TweenMax.set(this.target, {x: "+=" + (this.x - SX) * ratio, y: "+=" + (this.y - SY) * ratio});
                }
            }, draggableProps);
            return elItem;
        });


        itemsList.forEach(item => Body.appendChild(item));
        li.append(Body);
        ulList.append(li);
    })
    loadsList.append(ulList);
    $('.collapsible').collapsible();

}


const ClearTable = () => {
    saveJSONDataToLocalStorage("classes", []);
}


const getJSONDataFromLocalStorage = (key) => {
    let dataString = localStorage.getItem(key);
    let data = JSON.parse(dataString);
    return data;
}

const saveJSONDataToLocalStorage = (key, data) => {
    let dataString = JSON.stringify(data);
    localStorage.setItem(key, dataString);
}

const getSelectName = (classname, data, selected) => {
    let div_input1 = document.createElement("div");
    div_input1.className = classname;

    let loadCap = document.createElement("select");
    for (let index in data) {
        let option = document.createElement("option");
        if (data[index].id == selected) {
            option.selected = true;
        }
        option.value = data[index].id;
        option.id = data[index].id;
        option.innerText = data[index].name + " " + data[index].surname + " " + data[index].patronymic;
        loadCap.append(option);
    }
    div_input1.append(loadCap);

    return div_input1;
}


const getSelect = (classname, data, selected) => {
    let div_input1 = document.createElement("div");
    div_input1.className = classname;

    let loadCap = document.createElement("select");
    for (let index in data) {
        let option = document.createElement("option");
        if (data[index].id == selected) {
            option.selected = true;
        }
        option.value = data[index].id;
        option.id = data[index].id;
        option.innerText = data[index].name;
        loadCap.append(option);
    }
    div_input1.append(loadCap);

    return div_input1;
}

const createFormLoadEl = (id, div, name, teacher, corps, classroom, subject) => {
    if (div) {
        name = div.getElementsByClassName('load-caption')[0].innerText;
        teacher = div.getElementsByClassName('teacher')[0];
        corps = div.getElementsByClassName('corps')[0];
        classroom = div.getElementsByClassName('classroom')[0];
        if (div.className == "drag-item ui-draggable ui-draggable-handle") {
            let subjects = getJSONDataFromLocalStorage("subjects");
            subject = subjects.find(x => x.id == id).severities.find(x => x.severity.id == div.parentElement.parentElement.childNodes[0].id).severity.name;
            div = div.cloneNode(true);
            div.id = ID();
        } else {
            subject = div.getElementsByClassName('load-caption')[1].innerText;
        }
        return createLoadWithDiv(id, div, name, teacher, corps, classroom, subject);
    }
    return createLoadWithoutDiv(id, name, teacher, corps, classroom, subject);
}



const createLoadWithoutDiv = (id, name, teacher, corps, classroom, subject) => {
    let loadEl = document.createElement("div");
    $(loadEl).draggable(draggableProps);
    loadEl.id = id;
    loadEl.value = name;
    loadEl.className = "drag-item-inline";



    let removeButton = document.createElement("i");
    removeButton.className = "material-icons load-remove-button tiny right";
    removeButton.innerHTML = "close";
    removeButton.addEventListener("click", removeFormLoadEl);
    loadEl.append(removeButton);

    let loadCap = document.createElement("p");
    loadCap.className = "load-caption";

    loadCap.id = "name" + id;
    loadCap.innerText = name;
    loadEl.append(loadCap);

    loadCap = document.createElement("p");
    loadCap.className = "load-caption";

    loadCap.id = "type" + id;
    loadCap.innerText = subject;
    loadEl.append(loadCap);


    //TODO - create lectern list
    let subjects = getJSONDataFromLocalStorage("subjects");
    let subj = subjects.find(x => x.name == name);
    let teachers_list = [];

    if (subj != undefined) {
        teachers_list = getDataFromServer("teacher");//teachers.filter(x => x.subjects.filter(y => y.name == name).length > 0);
    }//teachers.filter(x => x.subjects.filter(y => y.name == name).length > 0);
    let div_input = getSelectName("teacher input-field", teachers_list, teacher);

    div_input.id = "teacher" + id;
    loadEl.append(div_input);
    let div_input1 = getSelect("corps input-field1", global_corps, corps);
    div_input1.getElementsByTagName("select")[0].addEventListener("change", getClassroomSortByCorpsId);
    div_input1.id = "corps" + id;
    loadEl.append(div_input1);

    div_input1 = getSelect("classroom input-field1", classrooms.filter(x => x.corps.id == corps), classroom);
    div_input1.id = "classroom" + id;
    loadEl.append(div_input1);
    return loadEl;
}


const getClassroomSortByCorpsId = (event) => {
    let div_id = event.currentTarget.parentElement.parentElement.parentElement.id;
    let id = event.currentTarget.options[event.currentTarget.options.selectedIndex].value;
    let classroomByCorps = getDataFromServer("classroom", id, "corpsId");
    let select = document.getElementById("classroom" + div_id);
    select.innerHTML = getSelect("classroom input-field1", classroomByCorps).innerHTML;
    $('select').formSelect();
}



const createLoadWithDiv = (id, div, name, teacher, corps, classroom, subject) => {
    id = div.id;
    let loadEl = document.createElement("div");

    $(loadEl).draggable({
        onPress: function () {
            SX = this.x;
            SY = this.y;
        },
        onDrag: function () {
            let SX = 1
            let SY = 1;
            let elem = getComputedStyle(document.getElementsByClassName('rTable')[0]);
            var ratio = (1 / elem.zoom) - 1;
            TweenMax.set(this.target, {x: "+=" + (this.x - SX) * ratio, y: "+=" + (this.y - SY) * ratio});
        }
    }, draggableProps);
    loadEl.id = id;
    loadEl.value = name;
    loadEl.className = "drag-item-inline";

    let removeButton = document.createElement("i");
    removeButton.className = "material-icons load-remove-button tiny right";
    removeButton.innerHTML = "close";
    removeButton.addEventListener("click", removeFormLoadEl);
    loadEl.append(removeButton);

    let loadCap = document.createElement("p");
    loadCap.className = "load-caption";
    loadCap.id = "name" + id;
    loadCap.innerText = name;
    loadEl.append(loadCap);

    loadCap = document.createElement("p");
    loadCap.className = "load-caption";
    loadCap.id = "type" + id;
    loadCap.innerText = subject;
    loadEl.append(loadCap);

    let div_input = document.createElement("div");
    if (teacher) {
        div_input = teacher;
    } else {
        //TODO - teacher list
        let teachers_list = [];
        let subjects = getJSONDataFromLocalStorage("subjects");
        let subj = subjects.find(x => x.name == name);
        if (subj != undefined) {
            teachers_list = getDataFromServer("teacher");//teachers.filter(x => x.subjects.filter(y => y.name == name).length > 0);
        }//teachers.filter(x => x.subjects.filter(y => y.name == name).length > 0);

        //let teachers_list = teachers;//teachers.filter(x => x.subjects.filter(y => y.name == name).length > 0);
        div_input = getSelectName("teacher input-field", teachers_list);
    }
    div_input.id = "teacher" + id;
    loadEl.append(div_input);

    let div_group = document.createElement("div");
    div_group.className = "row";

    let div_input1 = document.createElement("div");
    div_input1.className = "input-field";
    if (corps) {
        div_input1 = corps;
    } else {
        div_input1 = getSelect("corps input-field1", global_corps);
    }
    div_input1.id = "corps" + id;
    div_input1.getElementsByTagName("select")[0].addEventListener("change", getClassroomSortByCorpsId);

    div_group.append(div_input1);
    loadEl.append(div_input1);

    div_input1 = document.createElement("div");
    div_input1.className = "input-field";
    if (classroom) {
        div_input1 = classroom;
    } else {
        div_input1 = getSelect("classroom input-field1", []);
    }
    div_input1.id = "classroom" + id;
    div_group.append(div_input1);
    loadEl.append(div_input1);
    return loadEl;
}


const getSubjectsBySpeciality = (speciality) => {
    return subjects;
}




const getGroupsNamesList = (groups) => {
    let list = [];
    groups.forEach(group => {
        list.push(group.name);
    })
    return list;
}

const getGroupsIdList = (groups) => {
    let list = [];
    groups.forEach(group => {
        list.push(group.id);
    })
    return list;
}

const SaveTable = () => {
    let container = document.getElementsByClassName('rTable')[0];
    let list = container.getElementsByClassName("drag-item-inline");
    let classes = [];
    for (let index = 0; index < list.length; index++) {
        let divs = list[index];
        let group_name = divs.parentElement.id.split(",");

        let group = [];
        let flow = getJSONDataFromLocalStorage("flow");
        let subjects = getJSONDataFromLocalStorage("subjects");

        group_name.forEach(name => {
            group.push(flow.groups.find(x => x.name == name))
        })
        let time = divs.parentElement.parentElement.id;
        let day = divs.parentElement.parentElement.parentElement.id;
        let corps_id = document.getElementById("corps" + divs.id).getElementsByTagName("select")[0].options.selectedIndex;
        let teacher_id = document.getElementById("teacher" + divs.id).getElementsByTagName("select")[0].value;
        let classroom_id = document.getElementById("classroom" + divs.id).getElementsByTagName("select")[0].value;
        let type_name = document.getElementById("type" + divs.id).innerText;

        let name = divs.getElementsByClassName('load-caption')[0].innerText;
        let subject = subjects.find(x => x.name == name);
        let corps = global_corps.find(x => x.id == corps_id);
        let classroom = classrooms.find(x => x.id == classroom_id);
        let teachers = getJSONDataFromLocalStorage("teachers");
        let teacher = teachers.find(x => x.id == teacher_id);

        let order = {
            id: divs.id,
            name: name,
            groups: group,
            subject: subject,
            type: type_name,
            day: parseInt(day),
            time: time,
            corps: corps,
            classroom: classroom,
            teacher: teacher
        }
        classes.push(order);
    }
    saveJSONDataToLocalStorage("classes", classes);
}

const RenderTable = (groups, times, classes, size) => {
    let container = document.getElementsByClassName('rTable')[0];
    while (container.hasChildNodes()) {
        container.removeChild(container.lastChild);
    }
    var rTableHeading = document.createElement("div");
    rTableHeading.className = 'rTableHeading';
    var rTableRow = document.createElement("div");
    rTableRow.className = 'rTableRow';
    var rTableHead = document.createElement("div");
    rTableHead.className = 'rTableHead td4';
    rTableRow.append(rTableHead);
    var rTableHead = document.createElement("div");
    rTableHead.className = 'rTableHead td3';
    rTableRow.append(rTableHead);

    for (let group in groups) {
        var rTableHead = document.createElement("div");
        rTableHead.className = 'rTableHead td1';
        rTableHead.textContent = groups[group].name;
        rTableRow.append(rTableHead);
    }
    rTableHeading.append(rTableRow);
    container.append(rTableHeading);


    var rTableBody = document.createElement("div");
    rTableBody.className = 'rTableBody';

    for (let day = 1; day < 7; day++) {

        var rTableRow = document.createElement("div");
        rTableRow.className = 'rTableRow';
        var rTableHead = document.createElement("div");
        rTableHead.className = 'rTableHead td1';
        rTableHead.textContent = day;
        rTableRow.append(rTableHead);

        var rCell = document.createElement("div");
        rCell.className = 'rCell';
        for (let time in times) {
            var rTableRowTime = document.createElement("div");
            rTableRowTime.className = 'rTableRow';
            var rTableHead = document.createElement("div");
            rTableHead.className = 'rCellTime1';
            /*rTableHead.textContent = times[time];*/
            let loadCaption = document.createElement("p");
            loadCaption.innerText = times[time];
            loadCaption.id = "subject-modal-caption";
            rTableHead.appendChild(loadCaption);
            rTableRowTime.append(rTableHead);
            rCell.append(rTableRowTime);
        }
        rTableRow.append(rCell);
        var rCell = document.createElement("div");
        rCell.className = 'rTableRow';
        rCell.id = day;
        for (let time in times) {
            var rCellE = document.createElement("div");
            rCellE.className = 'rTableRowDrag';
            rCellE.id = times[time];
            let previos = [];
            for (let group = 0; group < groups.length; group++) {
                var rTableRowDr = document.createElement("div");
                let orders = classes.filter(x => x.day == day && x.time == times[time] && x.groups.find(x => x.id == groups[group].id));
                rTableRowDr.id = groups[group].name;
                if (orders.length != 0 && orders[0].groups.length >= groups.length) { //write lecternlesson  for all groups
                    rTableRowDr.className = 'rTableCell' + groups.length + ' droppable';
                    rTableRowDr.id = getGroupsNamesList(groups);
                    group = group + groups.length - 1;
                    orders.forEach(order => {
                        if (order.teacher && order.classroom) {
                            rTableRowDr.append(createFormLoadEl(order.id, null, order.name, order.teacher.id, order.classroom.corps.id, order.classroom.id, order.type));
                        }
                    });
                } else if ((size == 4 && orders.length == 0) && //write flow lessons
                        group != groups.length - 1 &&
                        (groups.length - group) >= 3 &&
                        classes.filter(x => x.day == day && x.time == times[time]).length == 0) {
                    rTableRowDr.className = 'rTableCell' + groups.length + ' droppable';
                    rTableRowDr.id = getGroupsNamesList(groups);
                    group = group + groups.length - 1;
                    orders.forEach(order => {
                        if (order.teacher && order.classroom) {
                            rTableRowDr.append(createFormLoadEl(order.id, null, order.name, order.teacher.id, order.classroom.corps.id, order.classroom.id, order.type));
                        }
                    });
                } else if ((size != 1 && orders.length == 0 && size != 4) && //write speciality lessons on count_groups block
                        (size != 1 && previos.length == 0) && (group == groups.indexOf(groups.filter(x => x.speciality.id == groups[group].speciality.id)[0])) &&
                        classes.filter(x => x.day == day && x.time == times[time])
                        ) {
                    let count_sp_group = groups.filter(x => x.speciality.id == groups[group].speciality.id).length;
                    rTableRowDr.className = 'rTableCell' + count_sp_group + ' droppable';
                    rTableRowDr.id = getGroupsNamesList(groups.filter(x => x.speciality.id == groups[group].speciality.id));
                    group += count_sp_group - 1;
                    orders.forEach(order => {
                        if (order.teacher && order.classroom) {
                            rTableRowDr.append(createFormLoadEl(order.id, null, order.name, order.teacher.id, order.classroom.corps.id, order.classroom.id, order.type));
                        }
                    });
                } else { //write practice on 1 block
                    rTableRowDr.className = 'rTableCell1 droppable';
                    orders.forEach(order => {
                        if (order.teacher && order.classroom) {
                            rTableRowDr.append(createFormLoadEl(order.id, null, order.name, order.teacher.id, order.classroom.corps.id, order.classroom.id, order.type));
                        }
                        if ((group % 2 != 0) || (size != 4 && group % 2 != 0)) {
                            orders = [];
                        }
                    });
                }
                rCellE.append(rTableRowDr);
                previos = orders;
            }
            rCell.append(rCellE);
        }
        rTableRow.append(rCell);
        rTableBody.append(rTableRow);
    }
    container.append(rTableBody);
}


function setZoom(zoom, el) {

    let transformOrigin = [0, 0];
    el = el || instance.getContainer();
    var p = ["webkit", "moz", "ms", "o"],
            s = "scale(" + zoom + ")",
            oString = (transformOrigin[0] * 100) + "% " + (transformOrigin[1] * 100) + "%";
    for (var i = 0; i < p.length; i++) {
        el.style[p[i] + "Transform"] = s;
        el.style[p[i] + "TransformOrigin"] = oString;
    }
    el.style["zoom"] = s;


}

function showVal(a) {
    var zoomScale = Number(a) / 10;
    setZoom(zoomScale, document.getElementsByClassName('rTable')[0]);
}


function reloadDraggable() {

    $(() => {
        let SX, SY = 1;
        $(".droppable").droppable({
            classes: {
                "ui-droppable-hover": "ui-state-hover"
            },
            drop: function (event, ui) {
                let loadEl = createFormLoadEl(ui.draggable[0].id, ui.draggable[0]);
                if (ui.draggable[0].className == "drag-item-inline") {
                    ui.draggable[0].parentNode.removeChild(ui.draggable[0]);
                }
                $(loadEl).appendTo(this);
                $('select').formSelect();
                SaveTable();
            }
        });

    });
}


var ID = function () {
    // Math.random should be unique because of its seeding algorithm.
    // Convert it to base 36 (numbers + letters), and grab the first 9 characters
    // after the decimal.
    return '_' + Math.random().toString(36).substr(2, 9);
};


const getClearDayAndTime = (classes, type_of_lesson, subject, group) => {
    let days = [];
    for (var day = 1; day < 7; day++) {
        days[day] = [];
        for (let time = 0; time < times.length; time++) {
            if (type_of_lesson == 0) {
                if (classes.find(x => x.day == day && x.subject.id == subject.id)) {
                    days[day] = [];
                    days[day + 1] = [];
                    day++;
                    break;
                } else if (classes.find(x => x.day == day && x.time == times[time])) {
                    ;
                } else if (classes.filter(x => x.day == day && x.subject.type.id == type_of_lesson).length >= 2) {
                    days[day] = [];
                } else {
                    days[day].push(times[time]);
                }

            } else if (type_of_lesson == 1) {
                if (classes.find(x => x.day == day && x.subject.id == subject.id && x.groups[0].name == group)) {
                    days[day] = [];
                    days[day + 1] = [];
                    day++;
                    break;
                } else if (classes.find(x => x.day == day && x.time == times[time])) {
                    ;
                } else if (classes.filter(x => x.day == day && x.subject.type.id == type_of_lesson).length >= 2 || classes.filter(x => x.day == day && x.subject.type.id == 0).length >= 2) {
                    days[day] = [];
                } else {
                    days[day].push(times[time]);
                }
            } else if (type_of_lesson == 2) {
                if (classes.filter(x => x.day == day).filter(x => x.groups.filter(y => y.name == group).length > 0).length >= 4) {
                    ;
                    days[day] = [];
                } else if (classes.find(x => x.day == day && x.subject.id == subject.id && x.groups[0].name == group) || classes.filter(x => x.day == day && x.subject.id == subject.id).length >= 2) {
                    days[day] = [];
                } else if (classes.find(x => x.day == day - 1 && x.subject.id == subject.id && x.groups[0].name == group)) {
                    days[day] = [];
                } else if (classes.find(x => x.day == day && x.time == times[time] && x.groups.filter(y => y.name == group).length > 0)) {
                    ;
                } else if (classes.find(x => x.day == day && x.time == times[time] && x.subject.name == subject.name)) {
                    ;
                } else {
                    days[day].push(times[time]);
                }
            }
        }
    }

    return days;
}


//рассчитывать количество часов по колонкам
//контроль по аудиром
let teachers = [{
        id: 0,
        name: "Иван Иванов Иванович",
        subjects: [{
                id: 1,
                name: "Математика"
            },
            {
                id: 0,
                name: "Физика"
            }
        ]
    }, {
        id: 1,
        name: "Инна Иванова Ивановна",
        subjects: [{
                id: 0,
                name: "ФИЗО"
            }]
    }, {
        id: 2,
        name: "Игорь Игорев Игоревич",
        subjects: [{
                id: 4,
                name: "История"
            }]
    }, {
        id: 3,
        name: "Инга Игоревна",
        subjects: [{
                "id": 5,
                "name": "Языки Программирования"
            }]
    }]

let global_corps = getDataFromServer("corps");


let times = ["8:00-8:30", "9:55-11:15", "11:30-13:00", "13:15-14:45", "15:00-16:40"];




function openNav() {
    document.getElementById("mySidenav").style.width = "250px";
    document.getElementById("table").style.marginLeft = "250px";
    document.getElementById("openLeft").style.display = 'none';
}

function closeNav() {
    document.getElementById("mySidenav").style.width = "0";
    document.getElementById("table").style.marginLeft = "0";
    document.getElementById("openLeft").style.display = 'block';
}


const OpenLoadSpeciality = (event) => {
    $("ul.tabs").tabs();
    $(".sidenav").sidenav();
    $('.modal').modal();
    $('select').formSelect();
}


const getSubjectsByPlan = (planId) => {
    let subjects = getDataFromServer("studyplan", planId).subjects;
    saveJSONDataToLocalStorage("subjects", subjects);
    return subjects;
}

const createLoadSpecialityForm = (id) => {

    let study_plan_list = getDataFromServer("studyplan", id, "specialityId");

    let newItem = document.createElement("div");
    let loadCaption = document.createElement("p");
    loadCaption.innerText = "Учебный план";
    loadCaption.id = "add-modal-caption";
    newItem.appendChild(loadCaption);

    let loadField = document.createElement("select");
    loadField.setAttribute("name", id);
    loadField.className = "select";
    loadField.required = true;

    loadField.addEventListener("change", function (event) {

        let loads = getSubjectsByPlan(event.currentTarget.value);

        let type_of_classes = [];
        loads.forEach(item => {
            item.severities.forEach(severity => {
                if (!type_of_classes.some(obj => obj.id == severity.severity.id)) {
                    type_of_classes.push(severity.severity);
                }
            });
        });
        renderLoadsList(loads, type_of_classes);
    }, false);

    let option = document.createElement("option");
    option.id = id;
    option.name = id;
    option.value = "undefined";
    option.innerText = "";
    loadField.appendChild(option);

    for (let key_value in study_plan_list) {
        let option = document.createElement("option");
        option.id = id;
        option.name = id;
        option.value = study_plan_list[key_value].id;
        option.innerText = study_plan_list[key_value].name;
        loadField.appendChild(option);
    }
    newItem.appendChild(loadField);
    return newItem;
}

const renderSpecialityLoadForm = () => {
    let loadsList = document.getElementById("speciality-study-plan-list");
    let ulList = document.createElement("ul");
    ulList.className = "collapsible";
    while (loadsList.firstChild) {
        loadsList.removeChild(loadsList.firstChild);
    }
    let sp_list = [];
    let flow = getJSONDataFromLocalStorage("flow");
    flow.groups.forEach(item => {
        if (!sp_list.some(obj => obj.id == item.speciality.id)) {
            sp_list.push(item.speciality);
        }
    });
    sp_list.forEach(item => {
        let li = document.createElement("li");
        let Caption = document.createElement("div");
        Caption.innerText = item.name;
        Caption.id = item.id;
        Caption.className = "collapsible-header";
        Caption.addEventListener("click", OpenLoadSpeciality);
        let Body = document.createElement("div");
        Body.className = "collapsible-body";
        Body.appendChild(createLoadSpecialityForm(item.id));
        li.appendChild(Caption);
        li.append(Body);
        ulList.append(li);
    });
    loadsList.append(ulList);
    $('.collapsible').collapsible();
}


const renderSpecialityList = (data, type_of_lesson) => {
    let loadsList = document.getElementById("speciality-study-plan-list");
    let ulList = document.createElement("ul");
    ulList.className = "collapsible";
    while (loadsList.firstChild) {
        loadsList.removeChild(loadsList.firstChild);
    }
    type_of_lesson.forEach(lesson => {
        let li = document.createElement("li");
        let lesson_type_data = data.filter(x => x.type.id == lesson.id);
        let Caption = document.createElement("div");
        Caption.innerText = lesson.name;
        Caption.id = lesson.id;
        Caption.className = "collapsible-header";
        Caption.addEventListener("click", printTest);
        let Body = document.createElement("div");
        Body.className = "collapsible-body";
        li.appendChild(Caption);
        let itemsList = lesson_type_data.map(dataItem => {
            let elItem = createLoadItemEl(dataItem["id"], dataItem["name"]);
            $(elItem).draggable({
                press: function (event, ui) {
                    if (ui.draggable[0].className.includes("lessonType0")) {
                        console.log("Lection");
                    }
                },
                onPress: function () {
                    SX = this.x;
                    SY = this.y;
                },
                onDrag: function () {
                    let SX = 1
                    let SY = 1;
                    let elem = getComputedStyle(document.getElementsByClassName('rTable')[0]);
                    var ratio = (1 / elem.zoom) - 1;
                    TweenMax.set(this.target, {x: "+=" + (this.x - SX) * ratio, y: "+=" + (this.y - SY) * ratio});
                }
            }, draggableProps);
            let loadsData = getJSONDataFromLocalStorage("loads");
            let name_id = loadsData.find(x => x.name == dataItem["name"])["id"];
            return elItem;
        });
        itemsList.forEach(item => Body.appendChild(item));
        li.append(Body);
        ulList.append(li);
    })
    loadsList.append(ulList);
    $('.collapsible').collapsible();
}


let classrooms = getDataFromServer("classroom");

$(document).ready(() => {

    let fullurl = window.location.pathname;
    let timetableId = fullurl.match("timetable\/(.+)")[1];
    let timetable = getDataFromServer("timetable", timetableId);

    let flow = {
        groups: getDataFromServer("groups", timetable.flow.id, "flowId").sort((a, b) => (a.speciality.id != b.speciality.id) ? 1 : -1)
    };
    let teachers = getDataFromServer("teacher");
    saveJSONDataToLocalStorage("teachers", teachers);
    saveJSONDataToLocalStorage("flow", flow);

    let classes = getJSONDataFromLocalStorage("classes");
    if (classes == null)
        classes = [];
    RenderTable(flow.groups, times, classes, 4);
    reloadDraggable();
    renderSpecialityLoadForm();


    $("ul.tabs").tabs();
    $(".sidenav").sidenav();
    $('.modal').modal();
    $('select').formSelect();

    $('#zoomSlider').on("input", function (e) {
        let value = e.target.value;
        $('#zoomSliderValue').val(Math.round(value * 100) + '%');
        TweenLite.to("#rTable", 0.2, {zoom: value});
    });

});

