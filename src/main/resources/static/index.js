$('#save-URL').click(function(){

	var name =  $('#input-36').val();

    var data = 
    {id: $('#input-36').val(), 
    url: $('#input-35').val()
    };

	if($('#input-35').val().match(/^((ftp|http|https):\/\/)?(www.)?(?!.*(ftp|http|https|www.))[a-zA-Z0-9_-]+(\.[a-zA-Z]+)+((\/)[\w#]+)*(\/\w+\?[a-zA-Z0-9_]+=\w+(&[a-zA-Z0-9_]+=\w+)*)?$/gm)  ==null) {		
	
	alert("Link not valid!");
	
	}else{

$.ajax({
        url: 'http://localhost:8080/links',
        type:"POST",
        data: JSON.stringify(data),
        contentType:"application/json; charset=utf-8",
        dataType:"json",
 
      })
	alert("Link shortened! Find it at: localhost:8080/links"+name);
}
});

/*
function idCheck(String name) {
    return p1 * p2;              // The function returns the product of p1 and p2
}
*/
