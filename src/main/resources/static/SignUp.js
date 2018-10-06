
window.onload = readCookie;

window.onload = setUser;
var value = readCookie("cepums");
var link = 'http://localhost:8080/'+value+'/links';


$('#logOut').click(function() {

  window.onload = eraseCookie("cepums");

window.location.assign("http://localhost:8080");
});


////////////////////////////////////////////////////////////////////////////


$('#save-Reg-URL').click(function() {

    var name = $('#input-36').val();

    var data = {
        id: $('#input-36').val(),
        url: $('#input-35').val()
    };

    if ($('#input-35').val().match(/^(?:http(s)?:\/\/)?[\w.-]+(?:\.[\w\.-]+)+[\w\-\._~:/?%#[\]@!\$&'\(\)\*\+,;=.]+$/gm) == null) {

        alert("Link not valid!");

    } else {

        $.ajax({
            url: link,
            type: "POST",
            data: JSON.stringify(data),
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            success: function(d) {
	
                if (d.result) {
                    alert("Link shortened! Find it at: http://localhost:8080/"+value+"/links/" + name);

                } else {
                    alert("Id already exists. Please choose another Id");
                }
            }
        });


    }
});

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////


 function createCookie(name, value, days) {
    var expires = '',
    date = new Date();
    if (days) {
      date.setTime(date.getTime() + (days * 24 * 60 * 60 * 1000));
      expires = '; expires=' + date.toGMTString();
    }
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

function eraseCookie(name) {
    createCookie("cepums","",-1);
}

  function setUser() {
  var names = readCookie("cepums");
        document.getElementById('usernameText').text = names;
      }
      
      
      
    /*
      
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
    console.log("checkCookie username ir signup.js: "+username);
        
    }
}
*/
