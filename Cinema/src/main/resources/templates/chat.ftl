<#import "spring.ftl" as spring />
<#--<!DOCTYPE html>-->
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>Hello ChatRoom</title>
    <link href="/webjars/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
    <link href="/static/main.css" rel="stylesheet">
    <script src="/webjars/jquery/3.1.1-1/jquery.min.js"></script>
    <script src="/webjars/sockjs-client/1.0.2/sockjs.min.js"></script>
    <script src="/webjars/stomp-websocket/2.3.3/stomp.min.js"></script>
    <script src="/static/app.js"></script>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <style>
        body {
            font-size: 16px;
        }
        h2{
            padding: 30px 0;
            font-size: 32px;
        }
        button {
            height:  32px;
            font-size:  14px !important;
        }
        .wrapper {
            width:  100%;
            margin: 0 auto;
            padding:  0;
            background: #ebebf5;
        }
        .wrapper .navbar {
            margin-bottom:  0 !important;
        }
        .wrapper .nav-link {
            padding: 0.5rem 1rem !important;
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
            font-size: 14px !important;
        }
        .wrapper .btn {
            font-size: 14px !important;
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
            flex-direction: column;
            margin: 15px 0;
        }
        .wrapper .picture {
            opacity: 0.5;
            height: 400px;
            background: url('/images/chaty.png') center;
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
<noscript><h2 style="color: #ff0000">Seems your browser doesn't support Javascript! Websocket relies on Javascript being
        enabled. Please enable
        Javascript and reload this page!</h2></noscript>
<div class="wrapper">
    <#if user.role == 'ADMIN'>
        <#include "components/adminNav.ftl">
    <#else>
        <#include "components/userNav.ftl">
    </#if>
    <div class="container inside-wrapper">
        <div class="picture"></div>
        <h2 class="text-primary">Chat for film "${film.title}"</h2>
        <div class="row">
            <div class="col-md-6">
                <div class="form-group">
                    <#if film.poster??>
                        <a href="/avatars/${film.poster}" target="_blank">
                            <img src="/avatars/${film.poster}" alt ="none" width="200">
                        </a>
                    </#if>
                    <h1 id="title">${film.title}<br></h1>
                    <p id = "film_id" hidden="true">${film.id?c}</p>
                    <p id = "username" hidden="true">${user.firstName}</p>
                    <p><span>Год релиза: </span>${film.yearRelease}</p>
                    <p><span>Возрастные ограничения: </span> ${film.ageRestriction}</p>
                    <p><span>Описание: </span>${film.description}</p>
                </div>
                <button id="disconnect" class="btn btn-primary" type="submit" disabled="disabled">Leave Chat
                </button><br>
            </div><br><br>
            <div class="col-md-6">
                <form class="form-inline">
                    <div class="form-group">
                        <input type="text" id="content" class="form-control" placeholder="Type your message...">
                        <button id="send" class="btn btn-primary" type="submit">Send</button>
                    </div>
                </form>
                <#if user.role == 'ADMIN'>
                    <a class="btn btn-primary" href="/admin/panel/films" role="button">Return to films</a>
                <#else>
                    <a class="btn btn-primary" href="/films" role="button">Return to films</a>
                </#if>

            </div>
        </div>
        <div class="row">
            <div class="col-md-12">
                <table id="conversation" class="table table-striped">
                    <thead>
                    <tr>
                        <th>Chat room</th>
                    </tr>
                    </thead>
                    <tbody>
                    <#if messages??>
                        <#list messages as item>
                            <tr>
                                <td>${item.username}: ${item.content}</td>
                            </tr>
                        </#list>
                    </#if>
                    </tbody>
                </table>
                <table id="conversation" class="table table-striped">
                    <tbody id="greetings">
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>

</body>
</html>