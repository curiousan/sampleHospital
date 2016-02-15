

$("document").ready(function () {

    $("#getUserList").on("click",getxmlfromServer);

    function getxmlfromServer() {
        console.log('GETTING user LIST');

        $.ajax({
            method: "GET",
            url: "http://localhost:8080/sampleHospital/webapi/users",
            datatype: "xml",            
            success: postUserList

        });
    }
    function postUserList(data) {
        console.log('listing users');
       var area  = $('#userList');
        area.empty();
        $(data).find('user').each(function () {
            var name = $(this).find('fullName').text();
            $('<div class="name"></div>').html(name).appendTo('#userList');
        });

    }
});