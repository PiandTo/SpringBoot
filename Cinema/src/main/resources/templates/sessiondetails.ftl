<#--<!DOCTYPE html>-->
<#import "spring.ftl" as spring />
<html lang="en">
<head>
	<meta charset="UTF-8">
	<link rel="icon" href="/favicon.ico" type="image/x-icon">
	<link rel="shortcut icon" href="/favicon.ico" type="image/x-icon">

	<title>Session details</title>
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
	<#include "components/adminNav.ftl">
	<div class="inside-wrapper">
		<h2 class="text-primary">${session.film.title}</h2>
		<div class="wrapper_details">
			<div class="image">
				<img class="card-img-top transition" src="/avatars/${session.film.poster}" alt="Card image cap">
			</div>
			<div class="session_details">
				<h3>Session details</h3>
				<p>
					<strong>Date and Time: </strong> ${session.dateTime}
					<br>
					<strong>Ticket cost:</strong> ${session.ticketCost} rub
				</p>

				<h3>Film details</h3>
				<p>
					<strong>Description: </strong> 	${session.film.description}
					<br>
					<strong>Release Year: </strong>${session.film.yearRelease}
					<br>
					<strong>Age Restrictions: </strong>${session.film.ageRestriction}
				</p>
				<h3>Hall details</h3>
				<p>
					<strong>Name: </strong>${session.hall.name}
					<br>
					<strong>Number: </strong>${session.hall.number}
					<br>
					<strong>Available Seats: </strong>${session.hall.availableSeats}
				</p>
                <#if user.role == "ADMIN">
					<a href="/admin/panel/sessions" class="btn btn-primary">All sessions</a>
                </#if>
			</div>

		</div>
	</div>
    <#include "components/footer.ftl">
</div>
</body>
</html>