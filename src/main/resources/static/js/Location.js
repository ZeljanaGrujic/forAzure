$('document').ready(function() {

	$('.table #editButton').on('click',function(event){
		event.preventDefault();
		var href= $(this).attr('href');
		$.get(href, function(location, status){
			$('#idEdit').val(location.id);
			$('#ddlCountryEdit').val(location.countryid);
			$('#addressEdit').val(location.address);
			$('#cityEdit').val(location.city);
			$('#detailsEdit').val(location.details);
		});
		$('#editModal').modal();
	});

//	$('.table #detailsButton').on('click',function(event) {
//		event.preventDefault();
//		var href= $(this).attr('href');
//		$.get(href, function(country, status){
//			$('#idDetails').val(country.id);
//			$('#descriptionDetails').val(country.description);
//			$('#codeDetails').val(country.code);
//			$('#lastModifiedByDetails').val(country.lastModifiedBy);
//			$('#lastModifiedDateDetails').val(country.lastModifiedDate.substr(0,19).replace("T", " "));
//		});
//		$('#detailsModal').modal();
//	});

	$('.table #deleteButton').on('click',function(event) {
		event.preventDefault();
		var href = $(this).attr('href');
		$('#confirmDeleteButton').attr('href', href);
		$('#deleteModal').modal();
	});
});