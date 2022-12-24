'use strict';
let enBtn = document.querySelectorAll(".ru-btn")[0];
let ruBtn = document.querySelectorAll(".en-btn")[0];

enBtn.addEventListener("click", function(e) {
    e.preventDefault();
    window.location.replace(location.protocol + '//' + location.host + location.pathname+"?lang=ru")
})

ruBtn.addEventListener("click", function(e) {
    e.preventDefault();
    window.location.replace(location.protocol + '//' + location.host + location.pathname+"?lang=en")
})