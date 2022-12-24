<#--<!DOCTYPE html>-->
<#import "spring.ftl" as spring />
<html lang="en">
<head>
    <meta charset="UTF-8">
    <link rel="icon" href="/favicon.ico" type="image/x-icon">
    <link rel="shortcut icon" href="/favicon.ico" type="image/x-icon">

    <title>Film chat</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <link rel="stylesheet" href="/styles.css" type="text/css"><!-- Google Tag Manager -->

    <style>
        h2{
            padding: 40px 0;
        }
        .wrapper {
            width:  100%;
            margin: 0 auto;
            padding:  0;
            background: #ebebf5;
        }
        .inside-wrapper {
            background: white;
            width: 1000px;
            margin: 0 auto;
            padding: 0 20px 20px 20px;
            min-height: 1000px;
        }
        .wrapper input {
            width:  500px;
        }
        .wrapper label {
            width:  100px;
            align-self: center;
        }
        .wrapper table {
            width: 600px;
        }
        .form-group  {
            display: flex;
            flex-direction: row;
            margin: 15px 0;
        }
        .wrapper .picture {
            opacity: 0.5;
            height: 400px;
            background: url('/images/halls.jpg') center;
            background-size: 1000px 600px;
        }
        footer {
            height:  60px;
        }
        footer p {
            padding-left: 10px;
            padding-top: 18px;
            width: 1000px;
            margin: 0 auto;
        }
        .card-img-top {
            opacity: 0.4;
        }
        .card-img-top:hover {
            opacity: 1;
        }
        .card {
            margin:  20px;
        }
        .transition {
            transition: 3s;
        }
        .wrapper .wrapper_details {
            display: flex;
            flex-direction: row;
        }
        .image {
            width:  65%;
            margin: 0 20px;
            box-sizing: border-box;
        }
        #navbarNav {
            width: 1000px;
            margin: 0 auto;
        }
        .btn-logout input {
            width: 80px;
        }
    </style>
</head>
<body>
<div class="wrapper">
    <nav class="navbar navbar-expand-lg navbar-light bg-light">
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div id="navbarNav">
            <ul class="navbar-nav">
                <li class="nav-item active">
                    <a class="nav-link" href="/">Home</a>
                </li>
                <li class="nav-item active">
                    <a class="nav-link" href="/admin">Admin</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/admin/panel/halls">Halls</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/admin/panel/films">Films</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/admin/panel/sessions">Sessions</a>
                </li>
                <li>
                    <a class="nav-link" href="/sessions/search/">Search</a>
                </li>
            </ul>
        </div>
    </nav>
    <div class="inside-wrapper">
        <h2 class="text-primary">Chat for film "${film.title}"</h2>
        <a href="/admin/panel/films" class="btn btn-primary">All films</a>
    </div>

    <div id="username-page">
        <div class="username-page-container">
            <h1 class="title">Type your username</h1>
            <form id="usernameForm" name="usernameForm">
                <div class="form-group">
                    <input type="text" id="name" placeholder="Username" autocomplete="off" class="form-control" />
                </div>
                <div class="form-group">
                    <button type="submit" class="accent username-submit">Start Chatting</button>
                </div>
            </form>
        </div>
    </div>

    <div id="chat-page" class="hidden">
        <div class="chat-container">
            <div class="chat-header">
                <h2>Spring WebSocket Chat Demo</h2>
            </div>
            <div class="connecting">
                Connecting...
            </div>
            <ul id="messageArea">

            </ul>
            <form id="messageForm" name="messageForm">
                <div class="form-group">
                    <div class="input-group clearfix">
                        <input type="text" id="message" placeholder="Type a message..." autocomplete="off" class="form-control"/>
                        <button type="submit" class="primary">Send</button>
                    </div>
                </div>
            </form>
        </div>
    </div>

    <footer class="bg-secondary">
        <p class="text-light">
            @Copyright 2022 Cinema Admin Panel, authors: Snaomi, Hemelia (school 21)
        </p>

    </footer>
</div>
<script src="https://cdnjs.cloudflare.com/ajax/libs/sockjs-client/1.1.4/sockjs.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/stomp.js/2.3.3/stomp.min.js"></script>
<script src="../js/app.js"></script>

</body>
</html>