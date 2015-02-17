$(document).ready(function() {
	"use strict"
	$(document).on("click",
		"[data-ajax-fetch]", function(event) {
		// URL is get from the attribute value
		var url = $(event.target).attr("data-ajax-fetch");
		$.get(url).then(function(response) {
			// where to store the response - from data-target attribute
			var targetSelector = $(event.target).attr("data-target");
			
			// find the target
			var target = $(targetSelector);

			// fill the target with response
			target.text(response);
		})
	});
	
});
