$('#save-URL').click(function() {

    var name = $('#input-36').val();

    var data = {
        id: $('#input-36').val(),
        url: $('#input-35').val()
    };

    if ($('#input-35').val().match(/^(?:http(s)?:\/\/)?[\w.-]+(?:\.[\w\.-]+)+[\w\-\._~:/?%#[\]@!\$&'\(\)\*\+,;=.]+$/gm) == null) {

        alert("Link not valid!");

    } else {

        $.ajax({
            url: 'http://localhost:8080/links',
            type: "POST",
            data: JSON.stringify(data),
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            success: function(d) {
	console.log(d.result);
                if (d.result) {
                    alert("Link shortened! Find it at: localhost:8080/links/" + name);

                } else {
                    alert("Id already exists. Please choose another Id");
                }
            }
        })


    }
});

//////////////////////////////////////////////////////////////////////////////////////

$('#register').click(function() {

    var pass = $('#input-37').val();
	var pass1 = $('#input-38').val();

    var data = {
        username: $('#input-35').val(),
        email: $('#input-36').val(),
	password: $('#input-37').val(),
    };

    if ($('#input-35').val().match(/^[a-zA-Z0-9]+([a-zA-Z0-9](_|-| )[a-zA-Z0-9])*[a-zA-Z0-9]*$/gm) == null) {

	console.log($('#input-35').val());
        alert("Username is not valid!");
	windows.location = ("SignUp.html");
    } else {

	 if ($('#input-36').val().match(/[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,3}/gm) == null) { 
	
	alert("Indalid Email!");

	}else {

		if (pass.match(/^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{8,}$/gm) == null ) {

        alert("Password must contain at least eight characters containing at least one letter and one number!");

    		} else {
    		

			if(pass != pass1){
			alert("Passwords must match!");
			}
			

				        $.ajax({
				            url: 'http://localhost:8080/register',
				            type: "POST",
				            data: JSON.stringify(data),
				            contentType: "application/json; charset=utf-8",
				            dataType: "json",
				            success: function(d) {
				
				                if (d.result) {
				                    alert("Thanks for registering!");
						windows.locaction = ("SignUp.html");
				                } else {
				                    alert("Registration not successfull!");
				                }
				            }
      					  });
				}
			
		}
	}
   }
);