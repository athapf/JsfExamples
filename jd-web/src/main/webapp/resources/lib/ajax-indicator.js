/**
 * Created by thaler on 04.02.17.
 */

var ajaxFlag = false;
var ajaxCounter = 0;

var map = {};

function obadd(key, fn) {
    map[key] = fn;
}

function obclear() {
    map = {};
}

function incrAjaxCounter() {
    ajaxCounter += 1;
    console.log("incr counter to " + ajaxCounter);
}

function decrAjaxCounter() {
    setTimeout(deferDecr, 20);
}

function deferDecr() {
    if(ajaxCounter > 0) {
        ajaxCounter -= 1;
        console.log("decr counter to: " + ajaxCounter);
        if(ajaxCounter == 0) {
             console.log(" do trigger ...");
            trigger();
        }
    }
}

function trigger() {
    console.log("trigger");
    for(k in map) {
        console.log("--> " + map[k]);
        map[k]();
    }
}

function ajaxEventHandler(event) {
    var ajaxloader = document.getElementById("form:ajaxloader");

    switch (event.status) {
        case "begin":
            console.log("start ajax call ...");
            incrAjaxCounter();
            ajaxloader.style.display = 'inline';
            break;

        case "complete":
            console.log("... end ajax call.");
            decrAjaxCounter();
            ajaxloader.style.display = 'none';
            break;
    }
}
