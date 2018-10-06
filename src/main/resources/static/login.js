$('#login_button').click(function() {

    var pass = $('#input-37').val();
	var userna = $('#input-35').val();
	
    var data = {
        username: $('#input-35').val(),
	password: $('#input-37').val(),
    };
    
	console.log("username: "+userna);
	console.log("password: "+pass);
	
	var link = "http://localhost:8080/pass/"+userna;
	$.ajax({
            url: link,
            type: "POST",
            data: JSON.stringify(data),
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            success: function(d) {

                if (d) {
                    alert("Login successful!");
                    window.onload = eraseCookie("cepums");
                    window.onload = createCookie("cepums", userna, 1);
			window.location.assign("http://localhost:8080/register.html");
                } else {
                    alert("Wrong username or password!");
                }
            }
        });
});


function eraseCookie(name) {
    createCookie("cepums","",-1);
}


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
  
  