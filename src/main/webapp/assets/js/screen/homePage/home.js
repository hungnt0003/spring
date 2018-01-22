// CAROUSEL DETAIL
$("#owl-demo-details").owlCarousel({

	autoPlay : 3000, // Set AutoPlay to 3 seconds

	items : 4,
	itemsDesktop : [ 1199, 3 ],
	itemsDesktopSmall : [ 979, 3 ]

});

// CLOSE PAGE DETAIL
$(document).ready(function() {
	$(".close-redirect").click(function(event) {
		event.preventDefault();
		linkLocation = this.href;
		$("body").fadeOut(1000, redirectPage);
	});
	function redirectPage() {
		window.location = linkLocation;
	}
});