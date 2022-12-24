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
	<link rel="stylesheet" href="/styles.css" type="text/css"><!-- Google Tag Manager -->
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
        .wrapper .btn {
            margin: 0 auto;
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
        .btn-logout input {
            width: 80px;
        }
	</style>
</head>
<body>
<div class="wrapper">
    <#include "components/userNav.ftl">
	<div class="inside-wrapper">
		<h1 class="text-primary">Cinema Panel for authorized users</h1>
		<p>
			<strong>Description:</strong> It's a movie theatre Admin Panel. Here we used Spring MVC
			mechanisms to implement a movie theatre administrator functionality.
			<br><br>
		</p>
		<div class="cards">
			<div class="card" style="width: 18rem;">
				<img class="card-img-top transition" src="/images/films.jpeg" alt="Card image cap">
				<div class="card-body">
					<h5 class="card-title">Films</h5>
					<p class="card-text">The list of all movies is available here. You can see the list of films and chat with other users and admins.</p>
					<a href="/films" class="btn btn-primary">All films</a>
				</div>
			</div>
			<div class="card" style="width: 18rem;">
				<img class="card-img-top transition" src="/images/search.jpeg" alt="Card image cap">
				<div class="card-body">
					<h5 class="card-title">Search a session</h5>
					<p class="card-text">It's a page for searching a session, using the film name.<br><br><br></p>
					<a href="/sessions/search/" class="btn btn-primary">Search</a>
				</div>
			</div>
			<div class="card" style="width: 18rem;">
				<img class="card-img-top transition" src="/images/profile.webp" alt="Card image cap" height="185.34px">
				<div class="card-body">
					<h5 class="card-title">Profile</h5>
					<p class="card-text">It's a profile page. You can see information about the date / time / IP address of all your authentications </p>
					<a href="/profile" class="btn btn-primary">Profile</a>
				</div>
			</div>
		</div>
	</div>
    <#include "components/footer.ftl">
</div>
</body>
</html>