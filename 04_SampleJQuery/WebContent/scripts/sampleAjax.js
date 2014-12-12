$(document).ready(function() {
	"use strict"
	function handleError(error) {
		console.error("error", error, arguments);
	}
	
	function appendToList(list, post) {
		var newElement = $("<li/>");
		newElement.text(post.title);
		list.append(newElement);
	}
	function processResponse(response) {
		// get list where response will be added
		var list = $("#list1");
		//iterating over array, something like:
		//for(var i=0; i<response.length; i++)
		
		// iterate over first 5 elements:		
		//$.each(response.slice(0, 5), function() {
		
		var i = 0;
		$.each(response, function() {
			// next element is "this"
			// response[i] is "this"
			// console.log(this);
			
			appendToList(list, this);
			// iterate over first 5 elements:		
			if (++i >= 5) {
				// when in $.each() function returns false
				// loop is canceled
				return false;
			}
		});
	}
	
	$.ajax("http://jsonplaceholder.typicode.com/posts", {
	  method: "GET"
	// second argument is function that will be
	// called when error occurs
	}).then(processResponse, handleError);
});
