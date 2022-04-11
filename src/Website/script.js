let get_started_button_up = "#4e749a #6a9dd0";
let get_started_button_down = "#4e749a #6a9dd0";


function map_popup(thisElement, targetElementID) {
    const element = document.getElementById(targetElementID);
    element.innerHTML = thisElement.id + "<br>" + thisElement.dataset.value + "<br><br>Click again to remove details";
    element.style.display = "grid";
}

function remove_map_popup(targetElementID) {
    const element = document.getElementById(targetElementID);
    element.style.display = "none";
}

function button_press(element,color){
    const gradient_colors = color.split(" ");
    element.style.backgroundImage = "radial-gradient(" + gradient_colors[0] + ", " +
        gradient_colors[1] + ")";
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