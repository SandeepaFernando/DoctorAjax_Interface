$(document).ready(function() {
	if ($("#alertSuccess").text().trim() == "") {
		$("#alertSuccess").hide();
	}
	$("#alertError").hide();
});

// SAVE ============================================
$(document).on("click", "#btnSave", function(event) {
	// Clear alerts---------------------
	$("#alertSuccess").text("");
	$("#alertSuccess").hide();
	$("#alertError").text("");
	$("#alertError").hide();
	// Form validation-------------------
	var status = validateItemForm();
	if (status != true) {
		$("#alertError").text(status);
		$("#alertError").show();
		return;
	}
	
	// If valid------------------------
	console.log(" statusxxxxxxxxxxxxxxxxx ");
	var type = ($("#hidItemIDSave").val() == "") ? "POST" : "PUT";

	$.ajax({
		url : "DoctorAPI",
		type : type,
		data : $("#formDoctor").serialize(),
		dataType : "text",
		complete : function(response, status) {
			onItemSaveComplete(response.responseText, status);
		}
	});


});



function onItemSaveComplete(response, status) {
	console.log("response "+ response);
	console.log(" status "+ status);
	if (status == "success") {
		var resultSet = JSON.parse(response);
		if (resultSet.status.trim() == "success") {
			$("#alertSuccess").text("Successfully saved.");
			$("#alertSuccess").show();
			$("#divItemsGrid").html(resultSet.data);
		} else if (resultSet.status.trim() == "error") {
			$("#alertError").text(resultSet.data);
			$("#alertError").show();
		}
	} else if (status == "error") {
		$("#alertError").text("Error while saving.");
		$("#alertError").show();
	} else {
		$("#alertError").text("Unknown error while saving..");
		$("#alertError").show();
	}
	$("#hidItemIDSave").val("");
	$("#formDoctor")[0].reset();
}

$(document).on("click", ".btnRemove", function(event) {
	$.ajax({
		url : "DoctorAPI",
		type : "DELETE",
		data : "did=" + $(this).data("itemid"),
		dataType : "text",
		complete : function(response, status) {
			onItemDeleteComplete(response.responseText, status);
		}
	});
});

function onItemDeleteComplete(response, status) {
	console.log("response "+ response);
	console.log(" status "+ status);
	if (status == "success") {
		var resultSet = JSON.parse(response);
		if (resultSet.status.trim() == "success") {
			$("#alertSuccess").text("Successfully deleted.");
			$("#alertSuccess").show();
			$("#divItemsGrid").html(resultSet.data);
		} else if (resultSet.status.trim() == "error") {
			$("#alertError").text(resultSet.data);
			$("#alertError").show();
		}
	} else if (status == "error") {
		$("#alertError").text("Error while deleting.");
		$("#alertError").show();
	} else {
		$("#alertError").text("Unknown error while deleting..");
		$("#alertError").show();
	}
}

// UPDATE==========================================
$(document).on("click",".btnUpdate",function(event) {
			$("#hidItemIDSave").val(
			$(this).closest("tr").find('#hidItemIDUpdate').val());
			$("#did").val($(this).closest("tr").find('td:eq(0)').text());
			$("#dname").val($(this).closest("tr").find('td:eq(1)').text());
			$("#daddress").val($(this).closest("tr").find('td:eq(2)').text());
			$("#dspecialty").val($(this).closest("tr").find('td:eq(3)').text());
			$("#dmobile").val($(this).closest("tr").find('td:eq(4)').text());
		});

// CLIENTMODEL=========================================================================
function validateItemForm() {
	// CODE
	if ($("#did").val().trim() == "") {
		return "Insert Doctor Id.";
	}
	// NAME
	if ($("#dname").val().trim() == "") {
		return "Insert Doctor Name.";
	}

	// PRICE-------------------------------
	if ($("#daddress").val().trim() == "") {
		return "Insert Doctor Address.";
	}
	
	if ($("#dspecialty").val().trim() == "") {
		return "Insert Doctor Specialty.";
	}
	
	if ($("#dmobile").val().trim() == "") {
		return "Insert Mobile.";
	}
	// is numerical value
	var tmpPrice = $("#dmobile").val().trim();
	var len = tmpPrice.length;
	if(len != 10){
		return "You enterd "+ len+ " Numbers! Enter 10 Numbers to continue."; 
	}
	
	if (!$.isNumeric(tmpPrice)) {
		return "Insert a numerical value.";

	}

	return true;
}
