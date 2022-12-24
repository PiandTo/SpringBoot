<#--<!DOCTYPE html>-->
<#import "spring.ftl" as spring />
<html lang="en">
<head>
    <meta charset="UTF-8">
    <link rel="icon" href="/favicon.ico" type="image/x-icon">
    <link rel="shortcut icon" href="/favicon.ico" type="image/x-icon">

    <title>Add new Hall</title>
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
        #navbarNav {
            width: 1000px;
            margin: 0 auto;
        }
        form b {
            color: red;
            padding-left: 100px;
        }
    </style>
</head>
<body>
<div class="wrapper">
    <#include "components/adminNav.ftl">
    <div class="inside-wrapper">
    <h2 class="text-primary">Add new hall</h2>
    <#if hall?? && noErrors??>
        Your submitted data <br>
        First name: ${hall.name}<br>
    <#elseif hall??>
    <form action="/admin/panel/hall" method="post">
            <div class="form-group">
                <label for="inputName">Hall name</label>
                <@spring.formInput "hall.name"/>
            </div>
            <@spring.showErrors "<br>"/>
            <div class="form-group">
                <label for="inputNumber">Number</label>
                <@spring.formInput "hall.number"/>
            </div>
            <@spring.showErrors "<br>"/>
            <div class="form-group">
                <label for="inputSeats">Seats</label>
                <@spring.formInput "hall.availableSeats"/>
            </div>
            <@spring.showErrors "<br>"/>
        <br>
        <button type="submit" class="btn btn-primary">Add</button>
    </form>
        <#else >
        <form action="/admin/panel/hall" method="post">
            <div class="form-group">
                <label for="inputName">Name</label>
                <input type="text" name="name" class="form-control" id="inputName" aria-describedby="emailHelp" placeholder="Enter hall name">
            </div>
            <div class="form-group">
                <label for="inputNumber">Number</label>
                <input type="text" name="number" class="form-control" id="inputNumber" placeholder="Enter hall number">
            </div>
            <div class="form-group">
                <label for="inputSeats">Seats</label>
                <input type="text" name="availableSeats" class="form-control" id="inputSeats" placeholder="Enter number of available Seats">
            </div>
            <button type="submit" class="btn btn-primary">Add</button>
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
        </form>
    </#if>
</div>
    <#include "components/footer.ftl">
</div>
</body>
</html>