<#--<!DOCTYPE html>-->
<#import "spring.ftl" as spring />
<html lang="en">
<head>
	<meta charset="UTF-8">
	<link rel="icon" href="/favicon.ico" type="image/x-icon">
	<link rel="shortcut icon" href="/favicon.ico" type="image/x-icon">

	<title>Halls</title>
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
	      integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
	<link rel="stylesheet" href="/styles.css" type="text/css"><!-- Google Tag Manager -->
	<style>
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
            width:  260px;
        }
        input[type=submit] {
            width:  100px;
        }
        .wrapper label {
            width:  100px;
            align-self: center;
        }
        .wrapper table {
            width: 950px;
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
        .btn-logout input {
            width: 80px;
        }
	</style>
</head>
<body>
<div class="wrapper">
    <#include "components/userNav.ftl">
	<div class="inside-wrapper">

		<div class="picture"></div>
		<h2 class="text-primary">Available films</h2>
		<table class="table table-striped">
			<thead>
			<td>#</td>
			<td>Title/poster</td>
			<td>Year of Release</td>
			<td>Age Restrictions</td>
			<td>Description</td>
			</thead>
			<tbody>
            <#list films as film>
				<tr>
					<td>${film.id?c}</td>
					<td>${film.title}
                        <#if film.poster??>
							<br>
							<a href="/avatars/${film.poster}" target="_blank">
								<img src="/avatars/${film.poster}" alt ="none" width="100">
							</a>

                        </#if>
					</td>
					<td>${film.yearRelease}</td>
					<td>${film.ageRestriction}</td>
					<td>${film.description}</td>
					<td><a class="btn btn-primary" href="/films/${film.id?c}/chat/" role="button">Chat</a></td>
				</tr>
            </#list>
			</tbody>
		</table>
	</div>
    <#include "components/footer.ftl">
</div>
</body>
</html>