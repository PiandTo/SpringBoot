let stompClient = null;
let film = {};
let username = null;
let selectedFilm = null;

function setConnected(connected) {
    $("#connect").prop("disabled", connected);
    $("#disconnect").prop("disabled", !connected);
    if (connected) {
        $("#conversation").show();
    }
    else {
        $("#conversation").hide();
    }
    $("#greetings").html("");
}

function connect() {
    let id = document.getElementById("film_id").innerText;
    username = document.getElementById("username").innerText;
    var socket = new SockJS('/ws');
    stompClient = Stomp.over(socket);
    stompClient.connect({'id': id, 'user': username}, function (frame) {
        setConnected(true);
        console.log('Connected: ' + frame);
        stompClient.subscribe('/topic/messages/' + id, function (message) {
            let data = JSON.parse(message.body);
            film = data.film;
            // username = data.username;
            showGreeting(data);
        });
    });
}

function disconnect() {
    let id = document.getElementById("film_id").innerText;
    if (stompClient !== null) {
        stompClient.disconnect('/topic/messages/' + id, function (message) {
            console.log(message);
            let data = JSON.parse(message.body);
            showGreeting(data);
        });
    }
    setConnected(false);
    console.log("Disconnected");
}

function sendMessage() {
    username = document.getElementById("username").innerText;
    let id = document.getElementById("film_id").innerText;
    let message = {
        'type': 'JOIN',
        'username': username,
        'content': $("#content").val(),
        'film': film
    }
    stompClient.send("/app/ws/" + id, {}, JSON.stringify(message));
}

function showGreeting(message) {
    if (message.type === 'JOIN') {
        $("#greetings").append("<tr><td style='color:green'>" + message.username + " connected" + "</td></tr>");
    } else if (message.type === 'LEAVE') {
        console.log(username);
        $("#greetings").append("<tr><td style='color:red'>" + message.username + " disconnected" + "</td></tr>");
    } else {
        $("#greetings").append("<tr><td>" + message.username + ": " + message.content + "</td></tr>");
    }
}

$(function () {
    connect();
    $("form").on('submit', function (e) {
        e.preventDefault();
    });
    $( "#disconnect" ).click(function() { disconnect(); });
    $( "#send" ).click(function() { sendMessage(); });
});
