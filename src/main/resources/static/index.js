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
	
                if (d.result) {
                    alert("Link shortened! Find it at: http://localhost:8080/links/" + name);

                } else {
                    alert("Id already exists. Please choose another Id");
                }
            }
        })


    }
});

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////

$('#register').click(function() {

    var pass = $('#input-3').val();
	var pass1 = $('#input-4').val();
	var username;
	
    var data = {
        username: $('#input-1').val(),
        email: $('#input-2').val(),
	password: $('#input-3').val(),
    };
  
    if ($('#input-1').val().match(/^[-\w\.\$@\*\!]{3,30}$/gm) == null) {

        alert("Username is not valid!");

    } else {
    
	 if ($('#input-2').val().match(/[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,3}/gm) == null) { 
    
	alert("Indalid Email!");

	}else {
  
         if (pass.match(/^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{8,}$/gm) == null) {

             alert("Password must contain at least eight characters containing at least one letter and one number!");

         } else {

             if (pass != pass1) {
                 alert("Passwords must match!");
             } else {
             
                var username1 = $('#input-1').val();
                 
                 $.ajax({
                     url: 'http://localhost:8080/register',
                     type: "POST",
                     data: JSON.stringify(data),
                     contentType: "application/json; charset=utf-8",
                     dataType: "json",
                     success: function (d) {

                         if (d.result) {
                      
                             alert("Thanks for registering!");
                             
                             
                            createCookie("cepums", username1, 1);
							console.log("pec seta: "+readCookie("cepums"));
							window.alert(readCookie("cepums"));
                             console.log("pec seta checkCookie: "+readCookie("cepums"));
                             window.location.assign("http://localhost:8080/register.html");
                         } else {
                             alert("Registration not successfull!");
                         }
                     }
                 });
             }
         }
			
		}
	}
   }
);

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////




////////////


 function createCookie(name, value, days) {
    var expires = '',
    date = new Date();
    if (days) {
      date.setTime(date.getTime() + (days * 24 * 60 * 60 * 1000));
      expires = '; expires=' + date.toGMTString();
    }
  //  document.cookie = name + '=' + value + expires + '; path=/';
    document.cookie = name + "=" + value + expires + "; path=/";
  }
  
   function readCookie(name) {
    var nameEQ = name + '=',
    allCookies = document.cookie.split(';'),
    i,
    cookie;
    for (i = 0; i < allCookies.length; i += 1) {
      cookie = allCookies[i];
      while (cookie.charAt(0) === ' ') {
        cookie = cookie.substring(1, cookie.length);
      }
      if (cookie.indexOf(nameEQ) === 0) {
        return cookie.substring(nameEQ.length, cookie.length);
      }
    }
    return null;
  }
  
  

function getCookie(cname) {
    var name = cname + "=";
    var ca = document.cookie.split(';');
    for(var i = 0; i <ca.length; i++) {
        var c = ca[i];
        while (c.charAt(0)==' ') {
            c = c.substring(1);
        }
        if (c.indexOf(name) == 0) {
            return c.substring(name.length,c.length);
        }
    }
    return "";
}

function checkCookie() {
    var username = getCookie("cepums");
    if (username != "") {
        alert("Welcome again " + username);
    } else {
    console.log("checkCookie username ir index.js: "+username);
        
    }
}

//setCookie('cname',document.getElementById('cname').value,10800000);

getCookie('loan');
