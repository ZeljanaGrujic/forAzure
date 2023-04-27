$('document').ready(function(){

var password = document.getElementById("password")
var confirmPassword = document.getElementById("confirmPassword");

function validatePassword(){
	  if(password.value != confirmPassword.value) {
	    confirmPassword.setCustomValidity("Lozinke se ne poklapaju!");
	  } else {
	    confirmPassword.setCustomValidity('');
	  }
	}
	password.onchange = function(){validatePassword()};
	confirmPassword.onkeyup = function(){validatePassword()};

});