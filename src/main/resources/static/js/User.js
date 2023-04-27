$('document').ready(function() {

	$('.table #editButton').on('click',function(event){
		event.preventDefault();
		var href= $(this).attr('href');
		$.get(href, function(user, status){
			$('#idEdit').val(user.id);
			$('#firstnameEdit').val(user.firstname);
			$('#lastnameEdit').val(user.lastname);
			$('#usernameEdit').val(user.username);
			$('#passwordEdit').val(user.password);
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