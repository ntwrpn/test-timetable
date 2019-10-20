

const getJSONDataFromLocalStorage = (key) => {
    let dataString = localStorage.getItem(key);

    let data = JSON.parse(dataString);

    return data;
}

const saveJSONDataToLocalStorage = (key, data) => {
    let dataString = JSON.stringify(data);
    localStorage.setItem(key, dataString);
}

const openTableEvent = (value) => {
    //var List = require("collections/list");
    localStorage.setItem("current_open_table", value);
    $.get("/"+value+"/", function(data, status){ 
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

      $.ajax({
        headers: { 
            'Accept': 'application/json',
            'Content-Type': 'application/json' 
        },
        'type': 'OPTIONS',
        'url': "/corps/",
        'success': function(response) {
            console.log(response);
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
          if (typeof(item[key])=="object"){
              for (let role in item[key]){
                td.append(item[key][role]["role"]);
                td.append(" ");
                input.append(td);
            }
          }else {
          td.append(item[key]);
          input.append(td);
            }
        } else{
          id = item[key];
          input.id = id;
        }
      }
      var td = document.createElement("td");
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
      
      var a = document.createElement("a");
      a.className = 'btn-floating btn-large waves-effect waves-light green accent-4';
      a.href = '#1';
      a.value = input.id;
      //a.textContent = 'Удалить';
      a.addEventListener("click", deleteValueFromTable);
      var i = document.createElement("i");
      i.className = 'material-icons';
      i.textContent = 'mode_edit    ';
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
            location.reload();
        }

    });
}


$(document).ready(function(){
  $('.collapsible').collapsible();
  $("ul.tabs").tabs();
  $(".sidenav").sidenav();
  $('.modal').modal();
  openTableEvent("users");
});


