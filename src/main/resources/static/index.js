$('#save-URL').click(function(){

    var data = 
    {id: $('#input-35').val(), 
    url: $('#input-36').val()
    };

    $.ajax({
        url: 'http://localhost:8080/links',
        type:"POST",
        data: JSON.stringify(data),
        contentType:"application/json; charset=utf-8",
        dataType:"json",
 
      })
});

