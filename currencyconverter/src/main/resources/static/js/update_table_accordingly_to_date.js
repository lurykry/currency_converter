const request = new XMLHttpRequest();
const host = new URL(window.location.href).host;
const adminUrl = "/admin/findRecord?date=";
const userUrl =  "/user/findRecord?date=";
const date =  document.getElementById("inputDate");
const tableBody =  document.getElementById("table_body");




date.addEventListener("change", function () {
    ajaxRequest(updateTable);
});

function updateTable(json){
    var serverResponse= JSON.parse(json);
    tableBody.textContent = "";
    for(var i =0; i < serverResponse.length; i++){
        var tr = document.createElement("tr");
        for(var j = 0; j < Object.keys(serverResponse[0]).length; j++){
            if(window.location.href.includes("user") &&
                Object.keys(serverResponse[i])[j] === "user"){
                continue;
            }
            var td = document.createElement("td");
            td.textContent = Object.values(serverResponse[i])[j];
            tr.appendChild(td);
        }
        tableBody.appendChild(tr);
    }
}

function ajaxRequest(updateTable){
    if(window.location.href.includes("admin"))
        request.open("GET", adminUrl + date.value, true);
    else
        request.open("GET", userUrl + date.value, true);

    request.setRequestHeader('Content-Type', 'application/json');
    request.onload = function () {
        updateTable(request.responseText);
    }
    request.send();
}
