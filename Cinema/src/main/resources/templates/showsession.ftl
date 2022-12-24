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
	<script type="text/javascript">
        function doAjaxPost() {
            var filmName = $('#filmName').val();

            $.ajax({
                type: "GET",
                url: "/sessions/search",
                data: "filmName=" + filmName,
                success: function(response){
					json = JSON.parse(response);

	                var result = "<h2>Результаты поиска по запросу \"" + filmName + "\"</h2>";
                    if (filmName === "") {
                        result += "<p>Вы ничего не ввели. Попробуйте еще раз</p>"
                    }
                    else if (json[0] != null) {
                        console.log(json);
                        result += "<div class=\"cards\">";
                        $.each(json,function(index,value){
                            dateTime1 = value.dateTime;
                            filmName1 = value.film.name;
                            filmPoster = value.film.posterUrl;
                            sessionId = value.id;

                            result += "<div class=\"card card2\">" +
                            "<a href=\"/admin/panel/sessions/"+sessionId+"\" class=\"btn btn-primary\">" +
                            "<img class=\"card-img-top transition\" src=\"/avatars/" + filmPoster + "\"" +
                            "alt=\"Card image cap\"></a>" +
                            "<div class=\"card-body\">" +
                            "<a href=\"/admin/panel/sessions/"+sessionId+"\" class=\"filmTitle\">" +
                            "<h5 class=\"card-title\">" + filmName1 + "</h5></a>" +
                            "<p>" + dateTime1+ "</p>" +
                            "</div></div>";

                            console.log('Индекс: ' + index +
	                                    '; Дата: ' + dateTime1 +
	                                    '; Название фильма: ' + filmName1 +
	                                    '; Постер: ' + filmPoster
                            );
                        });
                        result +="</div>";

                    }
                    else {
                        result += "<p>По вашему запросу ничего не найдено</p>";
                    }
                    $("#searchResults").html(result);
                },
                error: function(e){
                    console.log('Error: ' + e);
                }
            });
        }
	</script>

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
            background: url('/images/sessions.jpeg') center;
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
    <#include "components/adminNav.ftl">
	<div class="inside-wrapper">

		<div class="picture"></div>
		<h2 class="text-primary">All available sessions</h2>
		<div class="cards">
	        <#list sessions as session>
			<div class="card">
				<img class="card-img-top transition" src="/avatars/${session.film.poster}" alt="Card image cap">
				<div class="card-body">
					<h5 class="card-title">${session.film.title}</h5>
					<p class="card-text"><strong>Film description:</strong>
                        ${session.film.description}
					</p>
					<p>
						<strong>Hall name:</strong> ${session.hall.name}
					</p>
					<p> <strong>Date and time: </strong> ${session.dateTime}</p>
					<p>
						<strong>Ticket cost:</strong> ${session.ticketCost} rub.
					</p>
					<a href="/admin/panel/sessions/${session.id?c}" class="btn btn-primary">Session details</a>
					<br>
					<a href="/admin/panel/sessions/delete/${session.id?c}" class="btn btn-primary">Delete</a>
					<br>
					<a href="/admin/panel/sessions/update/${session.id?c}" class="btn btn-primary">Update</a>
				</div>
			</div>
	        </#list>
		</div>
		<br>
		<a class="btn btn-primary" href="/admin/panel/sessions/new" role="button">Add new session</a>

	</div>
    <#include "components/footer.ftl">
</div>
</body>
</html>