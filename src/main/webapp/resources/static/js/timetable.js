
$(document).ready(() => {
    $('.collapsible').collapsible();
    $('.modal').modal();
    openPage();
});


const openPage = () => {

    let days = {
        1: "Понедельник",
        2: "Вторник",
        3: "Среда",
        4: "Четверг",
        5: "Пятница",
        6: "Суббота"
    }

    let times = [
        "8:00-9:35",
        "9:55-11:30"
    ]

    let table_json = {
        10701216: {
            1: {
                "8:00-9:35": {
                    name: "test",
                    tacher: "test"
                },
                "9:55-11:30": {
                    name: "test",
                    tacher: "test"
                }
            }
        },
        10701116: {
            1: {
                "8:00-9:35": {
                    name: "test",
                    tacher: "test"
                },
                "9:55-11:30": {
                    name: "test",
                    tacher: "test"
                }
            }
        }
    }


    let pageForm = document.getElementById("timetable");
    //Создание заголовка
    let table = document.createElement("table");
    table.class = "highlight";
    table.border = "2";
    table.align = "center";
    let tr = document.createElement("tr");
    tr.id = "header";
    let td = document.createElement("td");
    td.innerText = "Дни";
    tr.appendChild(td);
    td = document.createElement("td");
    td.innerText = "Время";
    tr.appendChild(td);

    for (key in table_json) {
        let td = document.createElement("td");
        td.id = key;
        td.innerText = key;
        tr.appendChild(td);
    }
    table.appendChild(tr);
    //Рисование строк
    for (var i = 1; i < 7; i++) {
        let tr = document.createElement("tr");
        let td = document.createElement("td");
        td.innerText = days[i];
        //Рисование времени
        let tdtimes = document.createElement("td");
        let trtimes = document.createElement("tr");
        times.forEach(item => {
            let tdd = document.createElement("td");
            let trtimes = document.createElement("tr");
            tdd.innerText = item;
            trtimes.appendChild(tdd);
            tdtimes.appendChild(trtimes);
        });
        tr.appendChild(td);
        tr.appendChild(tdtimes);


        for (key in table_json) {
            let td = document.createElement("td");
            td.id = key;
            let tdd = document.createElement("td");
            let varia = table_json[key][i];

            for (time in varia) {
                let trtimes = document.createElement("tr");
                let tdtimes = document.createElement("td");
                console.log(time);
                tdtimes.innerText = varia[time]['name'];
                trtimes.appendChild(tdtimes);
                td.appendChild(trtimes);
            }
            //td.innerText = table_json[key][i];
            //td.appendChild();
            tr.appendChild(td);
        }


        table.appendChild(tr);
    }

    pageForm.appendChild(table);

}
