var request = null;
function createRequest(){
	try{
		request=new XMLHttpRequest();		
	}catch(trymicrosoft){
		try{
			request=new ActiveXObject("Msxml2.XMLHTTP");
		} catch(othermicrosoft){
			try{
				request=new ActiveXObject("Microsoft.XMLHTTP");
			}catch(failed){
				request=null;
			}
		}
	}
}
function getProffessorsInfo() {
	createRequest();
	var id = document.getElementById("discipline.id").value;	
	if (request == null) {
		alert("Error creating request!");
	}	
	var url = "/secondProject/getProffessors?disciplineId=" + id;
	request.open("GET", url, true);
	request.onreadystatechange = updateProfessors;
	request.send(null);
}

function updateProfessors(){	
	resetFormProf(document.getElementById("proffessor.id"));
	if(request.readyState == 4){		
		if(request.status == 200){				
			var jsonData=eval('(' + request.responseText + ')');			
			var none = document.createElement("option");			
			none.value = "0";
			none.text = "Professor";					
			document.getElementById("proffessor.id").add(none);
//			
//			alert(jsonData.title);
			for (var i = 0; i < jsonData.proffessorList.length; i++){
				var opt = document.createElement("option");			
				opt.value = jsonData.proffessorList[i].id;
				opt.text = jsonData.proffessorList[i].firstName + " " + jsonData.proffessorList[i].lastName;					
				document.getElementById("proffessor.id").add(opt);
			}
			
		}
	}
}