$('#register').click(function() {

    var pass = $('#input-37').val();
	var pass1 = $('#input-38').val();

    var data = {
        username: $('#input-35').val(),
        email: $('#input-36').val(),
	password: $('#input-37').val(),
    };

    if ($('#input-35').val().match(/^[a-z0-9_-]{3,15}$/gm) == null) {


        alert("Username is not valid!");

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