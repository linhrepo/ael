/**
 * Define TABLE object which will be used on form
 * @param data {
 * 					tableId
 * 			   }
 * 
 * @returns {
 * 				data
 * 				table @jqueryObject
 * 			}
 * 
 */

var CUSTOME_TABLE = function(data)
{
	this.data = {};
	this.comps = {};
	this._init(data);
};

CUSTOME_TABLE.prototype = {
	_init	: function(data)
	{
		this.data	= data;
		this.table = $("#"+data.tableId);
		this._loadAddButton();
		this.addRemoveButtonHandler();
	},
	/**
	 * Add new row to table based on @tableId
	 * It will copy current content of last row of @tableId table, 
	 * change its index and add it into that table as a new row.
	 * So, the table must contain at least 1 row for running adding action
	 */
	addRow		: function()
	{
		//get table object
		var table = $(this.table);
		$(table).trigger('beforeAddRow',[table]);
		//find last row
		var lastTr = this._findLastDataRow(table,true).row;
		if ($(lastTr).hasClass("hidden")){
			this._refreshDataRow(0, lastTr,true);
		}else{
			var newRolNum = parseInt($(lastTr).find("td[colType='index']").first().html()) + 1;
			//add new row with the same content as last row
			$(lastTr).parent().append("<tr>"+$(lastTr).html()+"</tr>");
			//reload last tr component
			lastTr = $(table).find('tr').last();
			//No. col
			$(lastTr).find("td[colType='index']").first().html(newRolNum);
			//count current data rows in table
			var numOfRow = table.find("tr").length - 2;
			//reset added row content
			this._refreshDataRow(numOfRow, lastTr,true);
			// active remove buttons
			this._addRemoveButtonHandler($(lastTr));
//			this._activeAllRemoveButton();
			$(table).trigger('afterAddRow',[lastTr,table]);
			return lastTr;
		}
		
	},
	/**
	 * Change index of the @row to @newIndex, empty its value if @isEmptyValue is true
	 * @param newIndex
	 * @param row
	 * @param isEmptyValue
	 */
	_refreshDataRow		: function(newIndex,row,isEmptyValue)
	{
		//get list input
		$(row).removeClass("hidden");
		$(row).find("input[valueType='added']").val("false");
		var newRowInputs = $(row).find("input");
		newRowInputs = newRowInputs.add($(row).find("select"));
		for (var i=0 ; i<newRowInputs.length;++i){
			var input = $(newRowInputs[i]);
			//get name attribute
			var name = input.attr("name");
			//find subscript in name
			if (name)
				{
					var startIndex = name.lastIndexOf('[');
					var endIndex = name.lastIndexOf(']');
					//change subscript to @newIndex
					var id = name.substring(0,startIndex)+(newIndex)+name.substring(endIndex+1);
					id = id.replace('[','').replace(']','');
					name = name.substring(0,startIndex+1)+(newIndex)+name.substring(endIndex);
					input.attr("name",name);
					input.attr("id",id);
				}
			//empty this row value if necessary
			if (isEmptyValue == true)
				{
					var emptyOnAdd = $(input).attr("empty-on-add");
					if (!emptyOnAdd || !emptyOnAdd.match("false"))
						{
							input.attr("value","");
						}
				}
		}
	},
	_loadAddButton	: function()
	{
		var that = this;
		this.comps.addButton = $("[target-table='"+this.data.tableId+"']");
		$(this.comps.addButton).on('click', function(){
			that.addRow();
		});
	},
	/**
	 * Find last @tr object which cotains @td object in @table table
	 * @param table
	 * @returns
	 */
	_findLastDataRow	: function(table)
	{
		var rowList = $(table).find("tr");
		
		for (var i=rowList.length - 1 ;i>=0;--i)
			{
				if ($(rowList[i]).find('th').length == 0 )
					{
						break;
					}
			}
		
		if (i>0)
			{
				return {row:rowList[i],index:i};
			}
		return {row:null,index:-1};
	},
	/**
	 * Remove button for each row
	 * @param row
	 */
	_addRemoveButtonHandler	: function(row)
	{
		var that = this;
		$(row).find(".removeRow").each(function(idx){
			$(this).on('click', function(){
				that._toogleRemove(row);
			});
		});
	},
	/**
	 * Remove the @tr object which contain @td object in @tableId 
	 * table if there are currently more than one existing row in the table
	 * @param td
	 * @param tableId
	 * @returns
	 */
	_toogleRemove	: function(row,atLeast1Row)
	{
		//get table object
		var table = $(this.table);
		//find first data row
		var newRowInputs = $(row).find("input");
		newRowInputs = newRowInputs.add($(row).find("select"));
		
		if ($(row).find("input[valueType='deleted']").val() == "true"){
			
			$(row).find("input[valueType='deleted']").val("false");
			$(row).removeClass("removed");
			for (var i=0 ; i<newRowInputs.length;++i){
				$(newRowInputs[i]).attr("readonly",false);
			}
		}else{
			$(table).trigger('beforeRemoveRow',[row,table]);
			$(row).find("input[valueType='deleted']").val("true");
			
			for (var i=0 ; i<newRowInputs.length;++i){
				if ($(newRowInputs[i]).attr("valueType") != "deleted")
				$(newRowInputs[i]).attr("readonly",true);
			}
			$(row).addClass("removed");
			$(table).trigger('afterRemoveRow',[row,table]);
		}
		
	},
	/**
	 * Add event handler for all remove button
	 */
	addRemoveButtonHandler	: function()
	{
		var that = this;
		$(this.table).find("tr").each(function(idx){
			if (idx >0)
				{
					that._addRemoveButtonHandler($(this));
				}
		});
	}
};