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