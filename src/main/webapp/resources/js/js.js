function removeDiv(){
	if(counter == 1){
		alert("No more textboxes to remove");
		return false;
	}
	counter--;
	var i = 2;
	document.getElementById("TextBoxDiv" + counter).remove();
}
function addPhoneDiv(){
	  if(counter > 4){
		  alert("Only 4 textboxes allow");
		  return false;
	  }	  
	  var div = document.createElement('div');  
	  div.id = "TextBoxDiv" + counter;
	  var select = document.createElement("Select");
	  select.setAttribute("id", "listPhones" + counter + ".phoneType.id");
	  select.setAttribute("name", "listPhones[" + counter + "].phoneType.id");	  
	  select.setAttribute("form", "student");
	  var none = document.createElement("option");			
	  none.value = "0";
	  none.text = "PhoneType";					
	  select.add(none);
	  var opt1 = document.createElement("option");			
	  opt1.value = "1";
	  opt1.text = "Home";
	  opt1.id = "Home" + counter;
	  opt1.name = "Home" + counter;
	  select.add(opt1);
	  var opt2 = document.createElement("option");			
	  opt2.value = "2";
	  opt2.text = "Mobile";		
	  opt2.id = "Mobile" + counter;
	  opt2.name = "Mobile" + counter;
	  select.add(opt2);
	  div.appendChild(select);
	  var input = document.createElement("input");
	  input.setAttribute("type", "text");
	  input.setAttribute("form", "student");
	  input.setAttribute("id", "listPhones" + counter + ".phone");
	  input.setAttribute("name", "listPhones[" + counter + "].phone");
//	  input.setAttribute("placeholder", "(123)45-67-89");
//	  input.setAttribute("onkeyup", "mask('addPhone" + counter +"', '(000)00-00-00', event);");	  
	  div.appendChild(input);
	  document.getElementById("TextBoxesGroup").appendChild(div);
	  counter++;
}
function resetFormProf(element){
	for (var i=0; i<=element.length; i++)
	{
		element.remove(i);
	}
}