$('document').ready(function() {

	$('.table #editButton').on('click',function(event){
		event.preventDefault();
		var href= $(this).attr('href');
		$.get(href, function(invoiceSelling, status){
			$('#idEdit').val(invoiceSelling.id);
			var invoiceDate= invoiceSelling.invoiceDate.substr(0,10);
			$('#dateEdit').val(invoiceDate);
//			$('#txtSupplierEdit').val(invoiceBuying.supplier.fullName);
			$('#supplierEdit').val(invoiceSelling.clientid);
			$('#statusEdit').val(invoiceSelling.invoicestatusid);
			$('#specialRemarksEdit').val(invoiceSelling.specialRemarks);
//			$('#totalCostEdit').val(invoiceSelling.totalCost);
		});
		$('#editModal').modal();
	});

	$('.table #detailsButton').on('click',function(event) {
		event.preventDefault();
		var href= $(this).attr('href');
		$.get(href, function(invoiceSelling, status){
				$('#idDetails').val(invoiceSelling.id);
            	$('#specialRemarkDetails').val(invoiceSelling.specialRemarks);
//           		$('#totalCostDetails').val(invoiceSelling.totalCost);
            	$('#supplierFullNameDetails').val(invoiceSelling.client.fullName);
            	$('#companyNameDetails').val(invoiceSelling.client.details);
            	$('#phoneDetails').val(invoiceSelling.client.phone);
            	$('#cityDetails').val(invoiceSelling.client.location.city);
            	$('#addressDetails').val(invoiceSelling.client.location.address);
            	$('#regionDetails').val(invoiceSelling.client.location.country.name);
//			$('#lastModifiedByDetails').val(country.lastModifiedBy);
//			$('#lastModifiedDateDetails').val(country.lastModifiedDate.substr(0,19).replace("T", " "));
		});
		$('#detailsModal').modal();
	});
//
//	$('.table #deleteButton').on('click',function(event) {
//		event.preventDefault();
//		var href = $(this).attr('href');
//		$('#confirmDeleteButton').attr('href', href);
//		$('#deleteModal').modal();
//	});
});