$('document').ready(function() {

	$('.table #editButton').on('click',function(event){
		event.preventDefault();
		var href= $(this).attr('href');
		$.get(href, function(supplier, status){
			$('#txtIdEdit').val(supplier.id);
			$('#txtNameEdit').val(supplier.fullName);
			$('#txtCompanyNameEdit').val(supplier.companyName);
			$('#txtEmailEdit').val(supplier.email);
			$('#ddlLocationEdit').val(supplier.locationid);
			$('#txtPhoneEdit').val(supplier.phone);
			$('#txtDetailsEdit').val(supplier.details);
		});
		$('#editModal').modal();
	});

	$('.table #detailsButton').on('click',function(event) {
		event.preventDefault();
		var href= $(this).attr('href');
		$.get(href, function(supplier, status){
				$('#idDetails').val(supplier.id);
            	$('#fullNameDetails').val(supplier.fullName);
           		$('#companyNameDetails').val(supplier.companyName);
            	$('#emailDetails').val(supplier.email);
            	$('#phoneDetails').val(supplier.phone);
            	$('#cityDetails').val(supplier.location.city);
            	$('#locationDetails').val(supplier.location.address);
            	$('#detailsDetails').val(supplier.details);
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