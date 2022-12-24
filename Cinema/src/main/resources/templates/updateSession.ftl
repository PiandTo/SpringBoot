<#--<!DOCTYPE html>-->
<#import "spring.ftl" as spring />
<html lang="en">
<head>
    <meta charset="UTF-8">
    <link rel="icon" href="/favicon.ico" type="image/x-icon">
    <link rel="shortcut icon" href="/favicon.ico" type="image/x-icon">

    <title>Update Session</title>
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
            background: url(https://www.pridex.ru/InFo-data/item_018/img_0004629.jpg) center;
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
        #navbarNav {
            width: 1000px;
            margin: 0 auto;
        }
        form b {
            color: red;
            padding-left: 100px;
        }
        .btn-logout input {
            width: 80px;
        }
    </style>
</head>
<body>
<div class="wrapper">
    <#include "components/adminNav.ftl">
    <div class="inside-wrapper">
        <h2 class="text-primary">Update session</h2>
        <#if session?? && noErrors??>
            Your submitted data <br>
            Session id: ${session.id?c}<br>
        <#elseif session??>
            <form action="/admin/panel/sessions/update/${id}" method="post">
                <div class="form-group">
                    <label for="inputDateTime">Session Date and time</label>
                    <@spring.formInput "session.dateTime"/>
                    <@spring.showErrors "<br>"/>
                </div>
                <div class="form-group">
                    <label for="inputHallId">Hall</label>
                    <select name="hall.hallId">
                        <#list halls as hall>
                            <#if hall.hallId == session.hall.hallId>
                                <option value="${hall.hallId}" selected>${hall.name}</option>
                            <#else>
                                <option value="${hall.hallId}">${hall.name}</option>
                            </#if>
                        </#list>
                    </select>
                </div>
                <@spring.showErrors "<br>"/>
                <div class="form-group">
                    <label for="inputFilmId">Film</label>
                    <select name="film.id">
                        <#list films as film>
                            <#if film.id == session.film.id>
                                <option value="${film.id?c}" selected>${film.title}</option>
                            <#else>
                                <option value="${film.id?c}">${film.title}</option>
                            </#if>
                        </#list>
                    </select>
                </div>
                <@spring.showErrors "<br>"/>
                <div class="form-group">
                    <label for="inputTicketCost">Ticket cost</label>
                    <@spring.formInput "session.ticketCost"/>
                </div>
                <@spring.showErrors "<br>"/>
                <br>
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
                <button type="submit" class="btn btn-primary">Update session</button>
            </form>
            <#else >
            <form action="/admin/panel/sessions/update/${id}" method="post">
                <div class="form-group">
                    <label for="inputDateTime">Session Date and time</label>
                    <input value=${session.dateTime} type="datetime-local" name="dateTime" class="form-control" id="inputDateTime" aria-describedby="emailHelp" placeholder="Enter session Date and Time">
                </div>
                <div class="form-group">
                    <label for="inputHallId">Hall</label>
                    <select name="hall.hallId">
                        <#list halls as hall>
                            <#if hall.hallId = session.hall.hallId>
                                <option value="${hall.hallId}" selected>${hall.name}</option>
                            <#else>
                                <option value="${hall.hallId}">${hall.name}</option>
                            </#if>
                        </#list>
                    </select>
                </div>
                <div class="form-group">
                    <label for="inputFilmId">Film</label>
                    <select name="film.id">
                        <#list films as film>
                            <#if film.id == session.film.id>
                                <option value="${film.id?c}" selected>${film.title}</option>
                            <#else>
                                <option value="${film.id?c}">${film.title}</option>
                            </#if>
                        </#list>
                    </select>
                </div>
                <div class="form-group">
                    <label for="inputTicketCost">Ticket cost</label>
                    <input value=${session.ticketCost} type="text" name="ticketCost" class="form-control" id="inputTicketCost" placeholder="Enter ticket cost">
                </div>
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
                <button type="submit" class="btn btn-primary">Update session</button>
            </form>
        </#if>
    </div>
    <#include "components/footer.ftl">
</div>
</body>
</html>