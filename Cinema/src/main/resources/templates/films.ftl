<#--<!DOCTYPE html>-->
<#import "spring.ftl" as spring />
<html lang="en">
<head>
    <meta charset="UTF-8">
    <link rel="icon" href="/favicon.ico" type="image/x-icon">
    <link rel="shortcut icon" href="/favicon.ico" type="image/x-icon">

    <title>Add new Film</title>
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
            background: url('/images/films.jpeg') center;
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
        .btn-logout input {
            width: 80px;
        }
    </style>
</head>
<body>
<div class="wrapper">
    <div class="nav-wrapper">
        <#include "components/adminNav.ftl">
    </div>

    <div class="inside-wrapper">
        <h2 class="text-primary">Add new film</h2>
        <#if film?? && noErrors??>
            Your submitted data <br>
            Film title: ${film.title}<br>
        <#elseif film??>
            <form action="/admin/panel/film" method="post">
                <div class="form-group">
                    <label for="inputName">Film title</label>
                    <@spring.formInput "film.title"/>
                </div>
                <@spring.showErrors "<br>"/>
                <div class="form-group">
                    <label for="inputNumber">Year of release</label>
                    <@spring.formInput "film.yearRelease"/>
                </div>
                <@spring.showErrors "<br>"/>
                <div class="form-group">
                    <label for="inputSeats">Age restrictions</label>
                    <@spring.formInput "film.ageRestriction"/>
                </div>
                <@spring.showErrors "<br>"/>
                <div class="form-group">
                    <label for="inputSeats">Description</label>
                    <@spring.formInput "film.description"/>
                </div>
                <@spring.showErrors "<br>"/>
                <br>
                <button type="submit" class="btn btn-primary">Add</button>
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
            </form>
        <#else >
            <form action="/admin/panel/film" method="post">
                <div class="form-group">
                    <label for="inputName">Film title</label>
                    <input type="text" name="title" class="form-control" id="inputTitle" aria-describedby="emailHelp" placeholder="Enter film title">
                </div>
                <div class="form-group">
                    <label for="inputNumber">Year of release</label>
                    <input type="text" name="yearRelease" class="form-control" id="inputYearOfRelease" placeholder="Enter year of release">
                </div>
                <div class="form-group">
                    <label for="inputSeats">Age restrictions</label>
                    <input type="text" name="ageRestriction" class="form-control" id="inputAgeRestrictions" placeholder="Enter age restrictions">
                </div>
                <div class="form-group">
                    <label for="inputSeats">Description</label>
                    <input type="text" name="description" class="form-control" id="inputDescription" placeholder="Enter description">
                </div>
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
                <button type="submit" class="btn btn-primary">Add</button>
            </form>
        </#if>
    </div>
    <#include "components/footer.ftl">
</div>
</body>
</html>