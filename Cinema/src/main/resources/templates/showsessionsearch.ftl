<#--<!DOCTYPE html>-->
<#import "spring.ftl" as spring />
<html lang="en">
<head>
	<meta charset="UTF-8">
	<link rel="icon" href="/favicon.ico" type="image/x-icon">
	<link rel="shortcut icon" href="/favicon.ico" type="image/x-icon">

	<title>Sessions</title>
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
	      integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
	<link rel="stylesheet" href="/styles.css" type="text/css"><!-- Google Tag Manager -->
	<script src="/static/jquery-3.6.0.min.js"></script>
    <#include "components/doAjaxScript.ftl">

	<style>
		.cards .card2  a {
			background-color: transparent;
			width: 100%;
			height:  100%;
			border:  none;
		}
        .cards .card2 h5, .cards .card2 p {
	        text-align: center;
        }
        h2{
            margin: 40px 0;
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
            background: url('/images/search.jpeg') center;
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
        .wrapper .cards {
            display:  flex;
	        flex-wrap: wrap;
	        justify-content: space-between;
        }
        .card-img-top {
            opacity: 0.4;
        }
        .card-img-top:hover {
            opacity: 1;
        }
        .card {
            margin:  20px 10px;
	        width:  30%;
	        box-sizing: border-box;
        }
        .transition {
            transition: 3s;
        }
        .wrapper .btn {
            margin: 0 auto;
            display: block;
            width:  150px;
        }
        #navbarNav {
            width: 1000px;
            margin: 0 auto;
        }
        .form-label  {
            margin-top:  -30px;
        }
        .btn-logout input {
            width: 80px;
        }
	</style>
</head>
<body>
<div class="wrapper">
    <#if user.role == "ADMIN">
        <#include "components/adminNav.ftl">
    <#else>
        <#include "components/userNav.ftl">
    </#if>
	<div class="inside-wrapper">

		<div class="picture"></div>
		<h2 class="text-primary">Session search</h2>
		<div class="input-group">
			<div class="form-group">
				<input id="filmName" type="text"  class="form-control" />
				<button id="search-button" type="button" class="btn btn-primary search-btn" onclick="doAjaxPost()">
					Search
				</button>
				<csrf/>
			</div>
		</div>

		<div id="searchResults">

		</div>
	</div>
    <#include "components/footer.ftl">
</div>
</body>
</html>