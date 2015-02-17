$(document).ready(function() {
	"use strict"
	// events are propagated from selected element
	// up to the document (through all parents)
	// we catch the event on the first parent - document
	$(document).on("click",
		// we filter only click events on elements
		// that have attribute data-dismiss-sample
		// with value 'alert'
		"[data-dismiss-sample='alert']", function(event) {
		// you can use this instead of event.target
		$(event.target.closest(".alert")).slideUp("slow", function() {
			$(this).remove();
		});
		// see bootstrap implementation: https://github.com/twbs/bootstrap/blob/master/js/alert.js
	});
	
});
