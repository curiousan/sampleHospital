
function onLoad(link){
		var url = "http://localhost:8080/sampleHospital/webapi/";
		var xhttp;
		
		if(window.XMLHttpRequest){
			xhttp = new XMLHttpRequest();
		}
		else{
			xhttp = new ActiveXObject(Miscrosoft.XMLHTTP);
		}
		
		xhttp.onreadystatechange = function(location){
			if(xhttp.readyState == 4 && xhttp.status == 200){
				load(xhttp);
			}
		};	
		
		xhttp.open('GET', url + link, true );
		xhttp.send();
	}


// to load messages

$(function(){
	
	$('#message').on('click', function(){
		$('#hidem').hide();
		onLoad('messages');
		
	});
});	


/*
// to load notifications

$(function(){
	
	$('#notifications').on('click', function(){
		
		onLoad('alerts');
		
	});
});

*/

function load(xml){
	
	var xmlReq = xml.responseXML;
	var i;
	var table;
	
	table = '<table><tr><th> Id </th><th> Message</th><th> Date </th>';
	
	x = xmlReq.getElementsByTagName("message");
	
	for(i = 0; i < x.length; i++){
		
		 table += "<tr><td>" +
		    x[i].getElementsByTagName("id")[0].childNodes[0].nodeValue +
		    "</td><td>" +
		    x[i].getElementsByTagName("content")[0].childNodes[0].nodeValue +
		    "</td></tr>";
		    x[i].getElementsByTagName("date")[0].childNodes[0].nodeValue +
		    "</td></tr></table>";
		}
		  document.getElementById("listMessage").innerHTML = table;
	}
	

