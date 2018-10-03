$('#login').click(function() {

    var pass = $('#input-37').val();

    var data = {
        username: $('#input-35').val(),
	password: $('#input-37').val(),
    };

	 $.ajax({
            url: 'http://localhost:8080/user/{username}',
            type: "POST",
            data: JSON.stringify(data),
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            success: function(d) {

                if (d) {
                    alert("Login successful!");
			window.location='http://localhost:8080';
                } else {
                    alert("Wrong username or password!");
                }
            }
        });
});
