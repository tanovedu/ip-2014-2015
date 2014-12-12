$(document).ready(function() {
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

	// we can assign result to a variable: 
	var list = $("#list1");
	
	// to get children of an element
	console.log(list.children());
	
	console.log($(".pretty"));
	// to search in children of element:
	console.log(list.find(".pretty"));

	// handling events:
	// $("li.pretty").on("click", function() { or
	$("li.pretty").click(function() {
		alert("CLICK!");
	});
	$("li.pretty").on("mouseenter", function() {
		console.log("mouse entered the element");
	});
	
	//creating element
	var newListItem = $("<li/>");
	// setting text of element
	newListItem.text("new");
	// newListItem.append(someOtherElement);
	// add attribute
	newListItem.attr("id", new Date().getTime());
	// append child
	list.append(newListItem);
	// equal to
	// list.append($("<li/>").text("new2").attr("id", new Date().getTime()));

	// create input element
	var itemNameInput = $("<input/>");
	// and add it before list
	itemNameInput.insertBefore(list);
	
	// create button
	var addButton = $("<button/>").text("ADD");
	
	var addElement = function() {
		// get text entered into input:
		var name = itemNameInput.val();
		
		// no value in input
		if (!name) {
			alert("You should enter some text");
			
			// break function invocation so empty
			// item will not be created
			return;
		}
		// clear text in input
		itemNameInput.val("");
		// func() - getter, func(value) - setter
		// same for attr()
		
		list.append($("<li/>").text(name).
				attr("id", new Date().getTime()));
	};
	// add element when clicking on button
	addButton.click(addElement);
	// insert before the list
	addButton.insertBefore(list);
});
/* shorthand:
$(function() {
	"use strict"
	// something when document is ready
});
*/