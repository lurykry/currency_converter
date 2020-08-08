const request = new XMLHttpRequest();


const btn = document.getElementById("btn_convert");

const optFrom = document.getElementById("valute_from");
const optTo = document.getElementById("valute_to");

const valFrom = document.getElementById("value_from");
const valTo = document.getElementById("value_to");


const csrfHeader = document.getElementById("_csrf_header").getAttribute('content');
const csrfToken = document.getElementById("_csrf").getAttribute('content');

let div = document.getElementById("for_input");

btn.addEventListener('click', function (e) {

    let current_date = (new Date()).toLocaleDateString('pt-br').split( '/' ).reverse( ).join( '.' );

    let valueValuteFrom = optFrom.options[optFrom.selectedIndex].value;
    let valueValuteTo = optTo.options[optTo.selectedIndex].value;

    let textValuteFrom = optFrom.options[optFrom.selectedIndex].text;
    let textValuteTo = optTo.options[optTo.selectedIndex].text;

    let valueValueFrom = valFrom.value;


    let obj = {
        currencyFromId: valueValuteFrom,
        currencyToId: valueValuteTo,
        currencyCharCodeFrom: textValuteFrom,
        currencyCharCodeTo: textValuteTo,
        amountOfMoney: valueValueFrom,
        dateOfConversion: current_date
    };

    if(obj.amountOfMoney !== "")
        ajaxRequest(JSON.stringify(obj));
    else{
        div.textContent = "please, fill in the field";
        div.style.visibility = "visible";
        setTimeout(function () {
           div.style.visibility = "hidden";
        },4000);
    }

})

function ajaxRequest(obj) {

    let paths = window.location.pathname.split("/");
    let endPoint = paths[paths.length - 1];
    let fullUrl = window.location.href;
    let indx = fullUrl.lastIndexOf(endPoint);
    let url = fullUrl.slice(0, indx) + "converter";

    request.open("POST", url, true);
    request.setRequestHeader('Content-Type', 'application/json');
    request.setRequestHeader(csrfHeader,csrfToken);
    request.onload = function () {
        let response = JSON.parse(request.responseText);
        if(request.status === 500){
            div.textContent = "Ooops, something went really wrong.  We are trying our best to fix it!";
            div.style.visibility = "visible";
            setTimeout(function () {
                div.style.visibility = "hidden";
            },12000);
        } else{
            if(response.message !== ""){
                alert(response.message);
            }
            valTo.value = response.result;
        }

    }
    request.send(obj);
}