(function() {
	"use strict"
	
	// display alert on the screen
	alert("Hello world");
	
	// define variable
	var i;
	
	// assign value
	i = 1;
	// increase value
	i++;
	
	if (i === 2) {
		alert("TWO");
	}
	// for, while
	
	// to define array
	var array = [];
	// add to array
	array.push("Hello");
	// size of array
	alert(array.length);
	// access element of an array by index 
	alert(array[0]);

	// define object:
	var o = {
		some: "value"	
	};
	// access field:
	alert(o.some);
	// change field value:
	o.some = "value2";
	// dynamically add fields:
	o.newField = "newValue";
	alert(o.newField);
	
	// dynamic name of field
	o[o.newField] = "dynamic name";
	alert(o.newValue);
	
	// objects are passed by reference
	var otherObject = {};
	// object can contains other objects
	o.subObject = otherObject;
	o.subObject.id = 1;
	if (o.subObject.id === otherObject.id) {
		alert("TRUE");
	}
	
})();