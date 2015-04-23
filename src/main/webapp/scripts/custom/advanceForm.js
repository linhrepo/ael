/**
 * 
 */


$("#advanceformList").change(function(){ 
	getRemainingAdvance();
});

function getRemainingAdvance() {
	var map = new Object();
	var docIdList = [];
	var emp = $('#empId').val();
	if (emp==null) {
		emp = $('#employee\\.id').val();
	}
	 $('#advanceformList > tbody > tr').each(function(){
		 if (!$(this).hasClass('hidden')) {
			 var jobNo = $(this).find('select').val();
			 var amount = accounting.unformat($(this).find(".advanceAmount").val());
			 docIdList[docIdList.length] =  jobNo;
			 if (jobNo in map) {
				var tmp = accounting.unformat(map[jobNo]);
				if (!isNaN(amount)) {
					tmp = tmp + amount;
				}				
				map[jobNo]= tmp;
			}
			 else {
				 map[jobNo]= amount;
			}
		}		 
	 });
	 try {
		 if (docIdList.length>0) {
			 $.ajax({
					type : "GET",
					url : ctx + "/users/advanceForm/getRemainAdvance",
					data : "docIdList="+docIdList+"&userId="+ emp,
					dataType : "text",
					success : function(response) {
						var p = jQuery.parseJSON(response);
						if (isApproved!=true) {
							$('#advanceformList > tbody > tr').each(function(){
								 if (!$(this).hasClass('hidden')) {
									 var docId =  $(this).find('select').val();
									 var amount = $(this).find(".advanceAmount").val();
									 for (var key in p) {
										 if (p.hasOwnProperty(key)) {
											 if (key == docId) {
													$(this).find(".remainAdvance").val(accounting.formatMoney(p[key],UTIL.MONEY_STYLE));
												}
										 }						
										}
								}		 
							 });
						}else {
							$('#advanceformList > tbody > tr').each(function(){
								 if (!$(this).hasClass('hidden')) {
									 var docId =  $(this).find('select').val();
									 var amount = $(this).find(".advanceAmount").val();
									 for (var key in p) {
										 if (p.hasOwnProperty(key)) {
											 if (key == docId) {
												 var remain = 0;
												 if (p[key]!=0) {
													 remain = p[key] -  map[key];
												}
													$(this).find(".remainAdvance").val(accounting.formatMoney(remain,UTIL.MONEY_STYLE));
												}
										 }						
										}
								}		 
							 });
						}
						 
						
					},
					error : function(xhr, status, error) {
						alert(xhr.responseText);
					},
					cache : false
			});
				return false;
		}
			
		 }
		catch(err) {
		}
	
}