/**
 * 
 */

$('#advanceformList tr').each(function(){
	
});

$("#advanceformList").change(function(){ 
	getRemainingAdvance();
});

function getRemainingAdvance() {
	var docIdList = [];
	
	 $('#advanceformList tr').each(function(){
		 if (!$(this).hasClass('hidden')) {
			 docIdList[docIdList.length] =  $(this).find('select').val();
		}		 
	 });
	$.ajax({
		type : "GET",
		url : ctx + "/users/advanceForm/getRemainAdvance",
		data : "docIdList="+docIdList,
		dataType : "text",
		success : function(response) {
			var p = jQuery.parseJSON(response);
			 $('#advanceformList tr').each(function(){
				 if (!$(this).hasClass('hidden')) {
					 var docId =  $(this).find('select').val();
					 var amount = $(this).find(".advanceAmount").val();
					 for (var key in p) {
						 if (p.hasOwnProperty(key)) {
							 if (key == docId) {
								 var remain = 0;
								 if (p[key]!=0) {
									 remain = p[key] - amount;
								}
									$(this).find(".remainAdvance").val(remain);
								}
						 }						
						}
				}		 
			 });
			
		},
		error : function(xhr, status, error) {
			alert(xhr.responseText);
		},
		cache : false
});
	return false;
}