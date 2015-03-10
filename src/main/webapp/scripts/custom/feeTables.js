/**
 * Calculate total value for a row
 */
var FEE_TABLE_CONTROL ={
	/**
	 * Calculate total value for a specific row
	 */	
	calculate_total:	function(tr){
			var amount = 0;
			$(tr).find("input.amount").each(function(){
				amount+=parseFloat(accounting.unformat($(this).val()));
			});
			
			var vat = parseFloat(accounting.unformat($(tr).find("input.vat").val())),
			vatAmount = amount*vat/100,
			total = vatAmount+amount;
			$(tr).find("input.vatAmount").val(accounting.formatMoney(vatAmount,UTIL.MONEY_STYLE));
			$(tr).find("input.total").val(accounting.formatMoney(total,UTIL.MONEY_STYLE));
	},
	/**
	 * Init fee table
	 */
	init	: function(tableId){
		$("#"+tableId).find("tr").each(function(){
			FEE_TABLE_CONTROL.calculate_total($(this));
			FEE_TABLE_CONTROL.addRowEvents($(this));
		});
		FEE_TABLE_CONTROL.addTableEvents(tableId);
		FEE_TABLE_CONTROL.initTotalTable(tableId);
	},
	/**
	 * Add event for a specific row
	 */
	addRowEvents : function(tr){
		var tableId= $(tr).closest("table").attr("id");
		$(tr).find("input.amount").on("change",function(){
			FEE_TABLE_CONTROL.calculate_total($(this).closest("tr"));
			FEE_TABLE_CONTROL.initTotalTable(tableId);
		});
		
		$(tr).find("input.vat").on("change",function(){
			FEE_TABLE_CONTROL.calculate_total($(this).closest("tr"));
			FEE_TABLE_CONTROL.initTotalTable(tableId);
		});
		
		$(tr).find("select.masterFee").on("change",function(){
			FEE_TABLE_CONTROL.initTotalTable(tableId);
		});
	},
	/**
	 * Add event for the table
	 */
	addTableEvents : function(tableId){
		$("#"+tableId).bind("afterAddRow",function(ev,tr){
			FEE_TABLE_CONTROL.addRowEvents(tr);
		});
	},
	//SET UP TOTAL TABLE
	initTotalTable : function(tableId){
		var totalTableId = tableId+"Summary",
		tableTotal = $("#"+totalTableId),
		masterList = {};
		//empty content
		$(tableTotal).find("tbody").html("");
		//calculate values for rendering
		$("#"+tableId).find(".masterFee").each(function(){
			var currSelected = $(this).find(":selected").val();
			if (currSelected != undefined){
				if (masterList[currSelected] == undefined){
					masterList[currSelected] = {
							text :  $(this).find(":selected").html(),
							value: 0.0
					};
				}
				
				//add to value
				var value = parseFloat(accounting.unformat($(this).closest("tr").find(".total").val()));
				masterList[currSelected].value = parseFloat(masterList[currSelected].value)+value;
			}
		});
		//render table
		var html="",j=0;
		for (var i in masterList){
			++j;
			html += "<tr>" +
					"<td colType='index'>"+j+"</td>" +
					"<td colType='index'>"+masterList[i].text+"</td>" +
					"<td colType='index'>"+accounting.formatMoney(masterList[i].value,UTIL.MONEY_STYLE)+"</td>" +
					"</tr>";
			
		}
		$(tableTotal).find("tbody").html(html);
	}
};

$(document).ready(function(){
	//feetable
	$("table.feeTable").each(function(){
		FEE_TABLE_CONTROL.init($(this).attr("id"));
	});
});




