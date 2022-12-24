<#--<!DOCTYPE html>-->
<#import "spring.ftl" as spring />
<html lang="en">
<head>
    <meta charset="UTF-8">
    <link rel="icon" href="/favicon.ico" type="image/x-icon">
    <link rel="shortcut icon" href="/favicon.ico" type="image/x-icon">

    <title>Cinema Admin Panel</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <style>
        h1 {
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

        .cards {
            display:  flex;
        }

        .wrapper .picture {
            opacity: 0.5;
            height: 400px;
            background: url('/images/kion.webp') center;
            background-size: 800px 600px;
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
        .wrapper .btn {
            display: block;
            width:  130px;
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
        #navbarNav {
            width: 1000px;
            margin: 0 auto;
        }
    </style>
</head>
<body>
<div class="wrapper">
    <#include "components/homeNav.ftl">
    <div class="inside-wrapper">
        <div class="picture"></div>
        <br>
        <h2 class="text-primary">
            <@spring.message "page.signUp.title"/>
        </h2>
        <div class="btn-group" role="group" aria-label="Basic example">
            <button type="button" class="btn btn-primary en-btn"><@spring.message "lang.eng"/></button>
            <button type="button" class="btn btn-primary ru-btn"><@spring.message "lang.ru"/></button>
        </div>
        <form action="/signUp" method="POST">
            <div class="form-group">
                <label for="exampleInputName"><@spring.message "page.signUp.firstName.label"/></label>
                <input type="text" class="form-control" name="firstName" id="exampleInputName" aria-describedby="emailHelp"
                       placeholder="<@spring.message "page.signUp.firstName.placeholder"/>">
            </div>
            <div class="form-group">
                <label for="exampleInputSurname"><@spring.message "page.signUp.LastName.label"/></label>
                <input type="text" class="form-control" id="exampleInputSurname" name="lastName" aria-describedby="emailHelp"
                       placeholder="<@spring.message "page.signUp.lastName.placeholder"/>">
            </div>
            <div class="form-group">
                <label for="exampleInputPhone"><@spring.message "page.signUp.phoneNumber.label"/></label>
                <input type="tel" class="form-control" id="exampleInputPhone" aria-describedby="emailHelp" name="phoneNumber"
                       placeholder="<@spring.message "page.signUp.phoneNumber.placeholder"/>">
            </div>
            <div class="form-group">
                <label for="exampleInputEmail1"><@spring.message "page.signUp.email.label"/></label>
                <input type="email" class="form-control" aria-describedby="emailHelp" name="email"
                       placeholder="<@spring.message "page.signUp.email.placeholder"/>">
            </div>
            <div class="form-group">
                <label for="exampleInputPassword1"><@spring.message "page.signUp.password.label"/></label>
                <input type="password" class="form-control" name="password"
                       placeholder="<@spring.message "page.signUp.password.placeholder"/>">
            </div>
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
            <button type="submit" class="btn btn-primary"><@spring.message "page.signUp.submitButton.label"/></button>
        </form>
    </div>
    <#include "components/footer.ftl">
</div>
</body>
<script src="/js/locale.js"></script>
</html>
