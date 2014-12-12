$(document).ready(function() {
	"use strict"

	// execute GET request to given url
	$.ajax("http://jsonplaceholder.typicode.com/posts", {
	  method: "POST",
	  // add request body as JSON:
	  data: {
		  userId: 1,
		  body: "newContent",
		  title: "newPost"
	  }
	// function in then() is callback that will be called
	// when response is received
	}).then(function(data) {
		console.log("response received");
		// get response of the request
		console.log(data);
		// you can access response fields directly
		console.log("title: " + data.title);
	});
	console.log("this is called before 'response received'");
	
	// DO NOT USE:
	// var response = $.ajax();
	// console.log(response);
	// only async calls
});
