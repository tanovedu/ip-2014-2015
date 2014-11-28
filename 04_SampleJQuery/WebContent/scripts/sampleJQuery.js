$(document).ready(function(){
	"use strict"
	
	// search for jQuery selectors
	// by element name:
	console.log($("body"));
	// by ID - using #
	console.log($("#list1"));
	// by CSS class - using .
	console.log($(".pretty"));
	// by element name and CSS class:
	console.log($("li.pretty"));
	// by parent (using SPACE) - children of ul that have class pretty:
	console.log($("ul .pretty"));

});