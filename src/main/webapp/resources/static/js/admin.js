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
    console.log(event.currentTarget);
    let name = localStorage.getItem("current_open_table");

    console.log(id);
    $.ajax({
        headers: { 
            'Accept': 'application/json',
            'Content-Type': 'application/json' 
        },
        'type': 'DELETE',
        'url': "/"+name+"/"+id,
        'success': function(response) {
            openTableEvent(name, "");
        }

    });

}


const acceptValueFromTable = (event) => {
  let id = event.currentTarget.value;
  console.log(event.currentTarget);
  console.log(id);
  $.ajax({
      headers: { 
          'Accept': 'application/json',
          'Content-Type': 'application/json' 
      },
      'type': 'POST',
      'url': "/users/accept/"+id,
      'success': function(response) {
          openTableEvent("users", "?enabled=False");
      }

  });


}

const openTableEvent = (value,add) => {
    //var List = require("collections/list");

    clearPostFromModal();
    createPostFormModal(value);

    localStorage.setItem("current_open_table", value);
    $.get("/"+value+"/"+add, function(data, status){ 
      saveJSONDataToLocalStorage(value, data);
      var container = document.getElementById("table-form");
      container.className='display';
      //var list = new List([1, 2, 3]);
      while (container.hasChildNodes()) {
          container.removeChild(container.lastChild);
      }
      var table = document.createElement("table");
      table.id='data-tr-table';
      

      if (data.length>0){
          var thread = createThread(data);
          table.appendChild(thread);
          if (add=='?enabled=False'){
            var tbody = createTbody(data, true);
          }
          else {
            var tbody = createTbody(data, false);
          }
          table.appendChild(tbody);
          container.appendChild(table);
      }
      $(document).ready( function () {
        $('#data-tr-table').DataTable();
       });
    });
  

  /*$(document).ready( function () {
    $('#data-tr-table').DataTable();
   });*/
    //let dataformData = getJSONDataFromLocalStorage(value);
}

const createThread = (data) => {
  var threadHeader = document.createElement("thead");

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
  threadHeader.append(thread);
  return threadHeader;
}

const createTbody = (data, accept) => {
  var tbody = document.createElement("tbody");

  data.forEach(item => {
      var input = document.createElement("tr");
      for (let key in item){
        var id = 0;
        var td = document.createElement("td");
        if (key != 'id'){
          if (typeof(item[key])=="object" && item[key]!=null){
              for (let role in item[key]){
                 if (item[key][role]["role"]!=undefined){
                    td.append(item[key][role]["role"]);
                    td.append(" ");
                    input.append(td);
                 } else{
                      td.append(item[key]["name"]);
                      input.append(td);
                      break;
                 }
            }
          } else if (item[key]==null){
            td.append(" ");
            input.append(td);
          } else {
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
      //a.href = '#1';
      a.value = item.id;
      //a.textContent = 'Удалить';
      a.addEventListener("click", deleteValueFromTable);
      var i = document.createElement("i");
      i.className = 'material-icons';
      i.textContent = 'delete';
      a.append(i);
      td.append(a);

      var a = document.createElement("a");
      a.className = 'btn-floating btn-large waves-effect waves-light btn yellow';
      //a.href = '#1';
      a.value = item.id;
      //a.textContent = 'Удалить';
      a.addEventListener("click", openToChangeForm);
      var i = document.createElement("i");
      i.className = 'material-icons';
      i.textContent = 'edit';
      a.append(i);
      td.append(a);

      if (accept==true){
        var a = document.createElement("a");
        a.className = 'btn-floating btn-large waves-effect waves-light green accent-4';
        a.href = '#1';
        a.value = item.id;
        a.addEventListener("click", acceptValueFromTable);
        var i = document.createElement("i");
        i.className = 'material-icons';
        i.textContent = 'done';
        a.append(i);
        td.append(a);
      }
      input.append(td);
      tbody.appendChild(input);
      
  });
  return tbody;
}



const openToChangeForm = (event)=> {
  let id = event.currentTarget.value;
  let value = localStorage.getItem("current_open_table");
  $.get("/"+value+"/"+id+"/", function(data, status){ 
    clearPostFromModal();
    createPostFormModal(data);
    openAddCreateModal();
  });
}

$(document).ready(function(){
  $('.collapsible').collapsible();
  $("ul.tabs").tabs();
  $(".sidenav").sidenav();
  $('.modal').modal();
  openTableEvent("users", "?enabled=True");
});



