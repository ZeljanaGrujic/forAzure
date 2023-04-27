$('document').ready(function() {

//	$('.table #editButton').on('click',function(event){
//		event.preventDefault();
//		var href= $(this).attr('href');
//		$.get(href, function(textileType, status){
//			$('#idEdit').val(textileType.id);
//			$('#descriptionEdit').val(textileType.description);
//			$('#detailsEdit').val(textileType.details);
//		});
//		$('#editModal').modal();
//	});

	$('.table #detailsButton').on('click',function(event) {
		event.preventDefault();
		var href= $(this).attr('href');
		$.get(href, function(invoiceStatus, status){
			$('#idDetails').val(invoiceStatus.id);
			$('#descriptionDetails').val(invoiceStatus.description);
			$('#detailsDetails').val(invoiceStatus.details);
//			$('#lastModifiedByDetails').val(country.lastModifiedBy);
//			$('#lastModifiedDateDetails').val(country.lastModifiedDate.substr(0,19).replace("T", " "));
		});
		$('#detailsModal').modal();
	});

//	$('.table #deleteButton').on('click',function(event) {
//		event.preventDefault();
//		var href = $(this).attr('href');
//		$('#confirmDeleteButton').attr('href', href);
//		$('#deleteModal').modal();
//	});
});