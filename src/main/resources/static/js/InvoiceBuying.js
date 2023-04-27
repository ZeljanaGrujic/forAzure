$('document').ready(function() {

	$('.table #editButton').on('click',function(event){
		event.preventDefault();
		var href= $(this).attr('href');
		$.get(href, function(invoiceBuying, status){
			$('#idEdit').val(invoiceBuying.id);

			var invoiceDate=invoiceBuying.invoiceDate.substr(0,10);
			$('#dateEdit').val(invoiceDate);
//			$('#txtSupplierEdit').val(invoiceBuying.supplier.fullName);
			$('#supplierEdit').val(invoiceBuying.supplierid);
			$('#statusEdit').val(invoiceBuying.invoicestatusid);
//			$('#ddlItemsEdit').val(invoiceBuying.textileid);
			$('#specialRemarksEdit').val(invoiceBuying.specialRemarks);
			$('#totalCostEdit').val(invoiceBuying.totalCost);
			$('#itemsEdit').val(invoiceBuying.items);
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
            	$('#supplierFullNameDetails').val(invoiceSelling.supplier.fullName);
            	$('#companyNameDetails').val(invoiceSelling.supplier.details);
            	$('#phoneDetails').val(invoiceSelling.supplier.phone);
            	$('#cityDetails').val(invoiceSelling.supplier.location.city);
            	$('#addressDetails').val(invoiceSelling.supplier.location.address);
            	$('#regionDetails').val(invoiceSelling.supplier.location.country.name);
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