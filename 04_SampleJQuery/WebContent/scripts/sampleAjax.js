$(document).ready(function() {
	"use strict"

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
		$.each(response, function() {
			// next element is "this"
			// response[i] is "this"
			// console.log(this);
			
			appendToList(list, this);
		});
	}
	
	$.ajax("http://jsonplaceholder.typicode.com/posts", {
	  method: "GET"
	}).then(processResponse);
});
