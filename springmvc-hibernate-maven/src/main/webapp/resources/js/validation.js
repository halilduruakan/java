//VALIDATION CONTROLLER

var registrationValidationController = (function() {
	var minLenghtEightReg, passwordReg, emailReg;

	//minimum 8 lenght string(any character exept new line)
	minLenghtEightReg = /^.{8,50}$/;

	//password regex at least one digit, one upper, one lower
	passwordReg = /^.*((?=.*\d)(?=.*[a-z])(?=.*[A-Z]){8,}).*$/

	//email regex
	emailReg = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;

	var usernameValidation = function(username) {
		if(username.match(minLenghtEightReg) == null) {
			return "Username lenght must greater than eight";
		}
		return "";
	}

	var passwordValidation = function(password) {

		if(password.length < 8){
			return "Password length must greater than eight"
		}else if(password.match(passwordReg) == null) {
			return "Password must include at least one digit,upper and lower character"
		}
		return "";
	}

	var passwordConfirmationValidation = function(password, confirmation) {
		if ( password == confirmation) {
			return "";
		}
		return "Password and confirmation doesnot match";
	}

	var emailValidation = function(email) {
		if(email.match(emailReg) == null) {
			return "Email must be valid email";
		}
		return "";
	}

	var validation = function(id, value) {
		switch (id) {
			case "username":
				return usernameValidation(value);
			case "password":
				return passwordValidation(value);
			case "email":
				return emailValidation(value);
			case "passwordconfirmation":
				return passwordConfirmationValidation(value[0],value[1]);
			default:
				return customValidation(id,value);
		}

	};

	var customValidation = function(id,value) {

		return id + " : Attributes, Has : " + value + "unexpected attributes";

	};


	return {
		validateElement: function(elementMap) {
			var errors = new Map();
			elementMap.forEach(function(value,key,map){
					if(key == "passwordconfirmation") {
						errors.set(key,validation(key,[value,map.get("password")]))
					}else {
						errors.set(key,validation(key,value));
					}
				});
			return errors;
		},
		isValidated: function(errors) {
			var validated = true;
			errors.forEach(function(value) {
				if(value != "") {
					validated = false;
				}
			});
			return validated;
		}
	};

})();

//UI CONTROLLER
var UIController = (function() {

	var DOMstrings = {
			username: '.username',
			password: '.password',
			email: '.email',
			registrationBtn: '.registration_button',
			error:'.error'

	};

	return {
		getInput: function() {
			var inputsMap,inputArray;
			inputsMap = new Map();
			inputArray = Array.prototype.slice.call(document.querySelectorAll("input"));

			inputArray.forEach(function(current,index,array){
				inputsMap.set(current.id,current.value);
			});

			return inputsMap;
		},
		getDOMstrings: function() {
			return DOMstrings;
		},
		addErrors: function(map) {
			map.forEach(function(value,key){
				document.querySelector("#" + key).
						 insertAdjacentHTML('afterend', '<div class="error">'+ value+"</div>")
			});
		},
		clearFields: function() {
			var fields, fieldsArr;

			fields = document.querySelectorAll("input");
			fieldsArr = Array.prototype.slice.call(fields);

			fieldsArr.forEach(function(current) {
				current.value = "";
			});
		},
		clearErrors: function() {
			var errorArr;
			errorArr = Array.prototype.slice.call(document.querySelectorAll(DOMstrings.error));
			if(errorArr.length != 0){
				errorArr.forEach(function(current, index, array) {
					current.parentNode.removeChild(current);
				});
			}
		},
		getUsername: function() {
			return document.querySelector(DOMstrings.username).value;
		},
		getEmail: function() {
			return document.querySelector(DOMstrings.email).value;
		}

	}

})();

// GLOBAL APP CONTROLLER
var controller = (function(registrationValCtrl, UICtrl) {

	var setUpEventListener = function() {
		var DOM = UICtrl.getDOMstrings();
		var url = window.location;

		document.querySelector(DOM.registrationBtn).addEventListener('click',function() {
			var isValidated;
			isValidated = validateInput();

			if(isValidated) {
				isUsernameUniq(url);
				isEmailUniq(url);
			}
			UICtrl.clearFields();
		});

	};

	var validateInput = function() {

		var input,errors;
		//Clear Error Nodes if exist
		UICtrl.clearErrors();
		//GET INPUT FROM FORM FIELDS
		input = UICtrl.getInput();
		//EXECUTE VALIDATION BUSINESS LOGIC VIA VALIDATION CONTROLLER
		errors = registrationValCtrl.validateElement(input);
		//ADD ERROR MESSAGE IN UI
		UICtrl.addErrors(errors);

		return registrationValCtrl.isValidated(errors);
	}

	var isUsernameUniq = function(url) {
		//GET EMAIL IN FORM FIELDS
		var username, errors;
		errors = new Map();

		username = UICtrl.getUsername();

		//SEND AJAX REQUEST TO SPRING MVC

		$.ajax({
			type : "post",
			url: url + "/isUsernameExist",
			data : { "username" : username },
			async: false,
			success : function(result) {
				errors.set("username",result);
			}
		});

		//MANUPULATE UI
		UICtrl.addErrors(errors);

	}

	var isEmailUniq = function(url) {
		//GET EMAIL IN FORM FIELDS
		var email, errors;
		errors = new Map();

		email = UICtrl.getEmail();

		//SEND AJAX REQUEST TO SPRING MVC

		$.ajax({
			type : "post",
			url: url + "/isEmailExist",
			data : { "email" : email },
			async: false,
			success : function(result) {
				errors.set("email",result);
			}
		});

		//MANUPULATE UI
		UICtrl.addErrors(errors);
	}


	return {
		init: function() {
			setUpEventListener();
		}
	};

})(registrationValidationController, UIController);


controller.init();
