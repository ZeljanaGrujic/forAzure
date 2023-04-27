$('document').ready(function() {

	$('.table #editButton').on('click',function(event){
		event.preventDefault();
		var href= $(this).attr('href');
		$.get(href, function(client, status){
			$('#txtIdEdit').val(client.id);
			$('#txtFullNameEdit').val(client.fullName);
//			$('#txtSurnameEdit').val(client.surname);
			$('#txtEmailEdit').val(client.email);
			$('#ddlLocationEdit').val(client.locationid);
			$('#txtPhoneEdit').val(client.phone);
			$('#txtDetailsEdit').val(client.details);
		});
		$('#editModal').modal();
	});

	$('.table #detailsButton').on('click',function(event) {
		event.preventDefault();
		var href= $(this).attr('href');
		$.get(href, function(client, status){
				$('#idDetails').val(client.id);
            	$('#fullNameDetails').val(client.fullName);
//           		$('#surnameDetails').val(client.surname);
            	$('#emailDetails').val(client.email);
            	$('#phoneDetails').val(client.phone);
            	$('#cityDetails').val(client.location.city);
            	$('#locationDetails').val(client.location.address);
            	$('#detailsDetails').val(client.details);
//			$('#lastModifiedByDetails').val(country.lastModifiedBy);
//			$('#lastModifiedDateDetails').val(country.lastModifiedDate.substr(0,19).replace("T", " "));
		});
		$('#detailsModal').modal();
	});

	$('.table #deleteButton').on('click',function(event) {
		event.preventDefault();
		var href = $(this).attr('href');
		$('#confirmDeleteButton').attr('href', href);
		$('#deleteModal').modal();
	});
});