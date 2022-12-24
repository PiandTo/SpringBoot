<!DOCTYPE html>
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
<#--    <link rel="stylesheet" href="/styles.css" type="text/css"><!-- Google Tag Manager &ndash;&gt;-->
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
            background: url('/images/okko.webp') center;
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
            <@spring.message "page.signIn.title"/>
        </h2>
        <div class="btn-group" role="group" aria-label="Basic example">
            <button type="button" class="btn btn-primary en-btn"><@spring.message "lang.eng"/></button>
            <button type="button" class="btn btn-primary ru-btn"><@spring.message "lang.ru"/></button>
        </div>
        <form action="/login" method="post">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
            <div class="form-group">
                <label for="exampleInputEmail1"><@spring.message "page.signIn.email.label"/></label>
                <input type="email" name="email" id="email" class="form-control" aria-describedby="emailHelp"
                       placeholder="<@spring.message "page.signIn.email.placeholder"/>">
            </div>
            <div class="form-group">
                <label for="exampleInputPassword1"><@spring.message "page.signIn.password.label"/></label>
                <input type="password" name="password" id="password" class="form-control"
                       placeholder="<@spring.message "page.signIn.password.placeholder"/>">
            </div>
            <div>
                <label for="remember-me"><@spring.message "page.signIn.rememberMe.label"/></label>
                <input type="checkbox" name="remember-me" id="remember-me"/>
            </div>
            <button type="submit" class="btn btn-primary"><@spring.message "page.signIn.submitButton.label"/></button>
        </form>
    </div>
    <#include "components/footer.ftl">
</div>
</body>
<script src="/js/locale.js"></script>
</html>

