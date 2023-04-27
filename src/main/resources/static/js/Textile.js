$('document').ready(function() {

	$('.table #editButton').on('click',function(event){
		event.preventDefault();
		var href= $(this).attr('href');
		$.get(href, function(textile, status){
			$('#txtIdEdit').val(textile.id);
			$('#txtUniqueCodeEdit').val(textile.uniqueCode);
			$('#ddlTypeEdit').val(textile.textiletypeid);
			$('#ddlUseEdit').val(textile.purpose);
			$('#ddlModelEdit').val(textile.textilemodelid);
			$('#ddlMakeEdit').val(textile.textilemakeid);
			$('#ddlSupplierlEdit').val(textile.supplierid);
			$('#txtQuantityEdit').val(textile.availableQuantity);
			$('#txtPriceEdit').val(textile.piecePrice);
			$('#ddlStatusEdit').val(textile.textilestatusid);
			$('#txtSpecDescriptionEdit').val(textile.specialDescription);

			var acquisitionDate=textile.acquisitionDate.substr(0,10);
			$('#txtDateEdit').val(acquisitionDate);
			$('#ddlEmployeeEdit').val(textile.employeeid);
		});
		$('#editModal').modal();
	});

	$('.table #detailsButton').on('click',function(event) {
		event.preventDefault();
		var href= $(this).attr('href');
		$.get(href, function(textile, status){
				$('#idDetails').val(textile.id);
				$('#quantityDetails').val(textile.availableQuantity);
            	$('#statusTypeDetails').val(textile.textileStatus.description);
            	$('#statusMeaningDetails').val(textile.textileStatus.details);
            	var acquisitionDate=textile.acquisitionDate.substr(0,10);
            	$('#dateDetails').val(acquisitionDate);
            	$('#priceDetails').val(textile.piecePrice);
            	$('#specialDescriptionDetails').val(textile.specialDescription);
            	$('#supplierDetails').val(textile.supplier.fullName);
            	$('#companyDetails').val(textile.supplier.companyName);
            	$('#phoneDetails').val(textile.supplier.phone);
		});
		$('#detailsModal').modal();
	});

	$('.table #deleteButton').on('click',function(event) {
		event.preventDefault();
		var href = $(this).attr('href');
		$('#confirmDeleteButton').attr('href', href);
		$('#deleteModal').modal();
	});

	$('.table #photoButton').on('click',function(event) {
       event.preventDefault();
       var href = $(this).attr('href');
       $('#photoModal #textilePhoto').attr('src', href);
       $('#photoModal').modal();
    });
});