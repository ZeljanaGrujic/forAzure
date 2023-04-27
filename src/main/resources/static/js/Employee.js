$('document').ready(function() {

	$('.table #editButton').on('click',function(event){
		event.preventDefault();
		var href= $(this).attr('href');
		$.get(href, function(employee, status){
			$('#txtIdEdit').val(employee.id);
			$('#txtfirstnameEdit').val(employee.firstname);
			$('#txtlastnameEdit').val(employee.lastname);
			$('#txtnicknameEdit').val(employee.nickname);
			$('#txtjmbgEdit').val(employee.jmbg);

			var dateOfBirth=employee.dateOfBirth.substr(0,10);
			$('#txtBirthDateEdit').val(dateOfBirth);

			$('#txtphoneEdit').val(employee.phone);
			$('#txtemailEdit').val(employee.email);

			var hireDate=employee.hireDate.substr(0,10);
			$('#txtHireDateEdit').val(hireDate);

			$('#ddlEmployeeTypeEdit').val(employee.employeetypeid);
			$('#ddlJobTitleEdit').val(employee.jobtitleid);
			$('#ddlLocationEdit').val(employee.locationid);
			$('#ddlCountryEdit').val(employee.countryid);
//			$('#txtUsernameEdit').val(employee.username);
			//$('#flpImageEdit').val(employee.photo);

		});
		$('#editModal').modal();
	});

	$('.table #detailsButton').on('click',function(event) {
		event.preventDefault();
		var href= $(this).attr('href');
		$.get(href, function(employee, status){
				$('#idDetails').val(employee.id);
				var dateOfBirth=employee.dateOfBirth.substr(0,10);
                $('#dateOfBirthDetails').val(dateOfBirth);

                $('#jmbgDetails').val(employee.jmbg);
                $('#phoneDetails').val(employee.phone);
                $('#locationcityDetails').val(employee.location.city);
                $('#locationaddressDetails').val(employee.location.address);
                $('#regionDetails').val(employee.country.name);

                var hireDate=employee.hireDate.substr(0,10);
                $('#hireDateDetails').val(hireDate);
                $('#employeeTypeDetails').val(employee.employeeType.description);
                $('#typeDetailsDetails').val(employee.employeeType.details);
                $('#emailDetails').val(employee.email);
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
   $('#photoModal #employeePhoto').attr('src', href);
   $('#photoModal').modal();
});

});