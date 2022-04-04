let nav_button_light = "#14006f";
let nav_button_dark = "#0e0053";

let movement_button_light = "#976f0d";
let movement_button_dark = "#5c4408";

let interaction_button_light = "#4e5c0b";
let interaction_button_dark = "#2b3206";

let information_button_light = "#474d2c";
let information_button_dark = "#242817";

let puzzle_button_light = "#521e0c";
let puzzle_button_dark = "#2c1007";

let map_start_light = "#0c6501";
let map_start_dark = "#083602FF";

let map_danger_light = "#ff0000";
let map_danger_dark = "#6f0000";

let map_safe_light = "#00537f";
let map_safe_dark = "#022b40";

function show_map_msg(elementID) {
    const command = document.getElementById("command");
    const description = document.getElementById("description");
    const legend = document.querySelectorAll(".legend");
    legend.forEach(legend => {
        legend.style.display = "none";
    })
    command.innerHTML = elementID.id;
    description.innerHTML = elementID.value + "";
}
function show_info_window(elementID){
    const info = document.getElementById("info")
    const command = document.getElementById("command");
    const description = document.getElementById("description");
    info.style.display = "initial";
    if(elementID.parentElement.className === "interaction"){
        command.innerHTML = elementID.id + "&nbsp;&lt;item&gt;";
    } else{
        command.innerHTML = elementID.id;
    }
    description.innerHTML = elementID.value + "";

    if(info.style.height !== '150px') {
        let id = null;
        let pos = 0;
        clearInterval(id);
        id = setInterval(frame, 2);

        function frame() {
            if (pos === 150) {
                clearInterval(id);
            } else {
                pos += 2;
                info.style.height = pos + 'px';
            }
        }
    }
}
function hide_info_window(){
    const info = document.getElementById("info");
    const command = document.getElementById("command");
    const description = document.getElementById("description");
    command.innerHTML = "";
    description.innerHTML = "";

    let id = null;
    let pos = 150;
    clearInterval(id);
    id = setInterval(frame, 2);
    function frame() {
        if (pos === 0) {
            clearInterval(id);
        } else {
            pos-=2;
            info.style.height = pos + 'px';
        }
    }
}
function button_up(elementID, backgroundColor){
    const button = elementID;
    button.style["boxShadow"] = "inset 0 0 20px black";
    button.style["backgroundColor"] = backgroundColor;
    button.style.color = "#ececec";
}
function button_down(elementID, backgroundColor) {
    const button = elementID;
    button.style["boxShadow"] = "inset 10px 10px 20px black";
    button.style["backgroundColor"] = backgroundColor;
    button.style.color = "#a7a7a7";
}