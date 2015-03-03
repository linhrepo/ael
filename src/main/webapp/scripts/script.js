
// This function is used by the login screen to validate user/pass
// are entered.
function validateRequired(form) {
    var bValid = true;
    var focusField = null;
    var i = 0;
    var fields = new Array();
    oRequired = new required();

    for (x in oRequired) {
        if ((form[oRequired[x][0]].type == 'text' || form[oRequired[x][0]].type == 'textarea' || form[oRequired[x][0]].type == 'select-one' || form[oRequired[x][0]].type == 'radio' || form[oRequired[x][0]].type == 'password') && form[oRequired[x][0]].value == '') {
           if (i == 0)
              focusField = form[oRequired[x][0]];

           fields[i++] = oRequired[x][1];

           bValid = false;
        }
    }

    if (fields.length > 0) {
       focusField.focus();
       alert(fields.join('\n'));
    }

    return bValid;
}

// This function is a generic function to create form elements
function createFormElement(element, type, name, id, value, parent) {
    var e = document.createElement(element);
    e.setAttribute("name", name);
    e.setAttribute("type", type);
    e.setAttribute("id", id);
    e.setAttribute("value", value);
    parent.appendChild(e);
}

function confirmDelete(obj) {
    var msg = "Are you sure you want to delete this " + obj + "?";
    ans = confirm(msg);
    return ans;
}

// 18n version of confirmDelete. Message must be already built.
function confirmMessage(obj) {
    var msg = "" + obj;
    ans = confirm(msg);
    return ans;
}

//return message from jquery i18n
function showMessage(key,placesholders){
	return jQuery.i18n.prop(key);
}

/**
 * Control all pages
 */
$(document).ready(function(){
	//init i18n jquery version
	$.i18n.properties({
	    name:'Messages', 
	    path:contextPath+'/scripts/bundle/', 
	    mode:'both',
	    language:'en'
	});

	//control loading mask for ajax calls
	$(document).bind("ajaxSend", function() {
		UTIL.showLoadingMask();
	}).bind("ajaxComplete", function() {
		UTIL.hideLoadingMask();
	});
	
	//init all datatable
	$(".datatable").dataTable();
	
	//init all select2
	
	SELECT_CONTROL.init();
	
	//init datatable wihout paging, ordering
	$(".datatableEmpty").dataTable( {
        "paging":   false,
        "ordering": false,
        "info":     false,
        "searching":false
    } );
	
	//inform table
	$(".inFormTable").each(function(){
		var id = $(this).attr("id");
		$(this).inFormTable({
			tableId:id
		});
		
	});
	
	$(".inFormTableDetail").each(function(){
		var id = $(this).attr("id");
		$(this).inFormTableDeatil({
			tableId:id
		});
	});
	
	//inint nested tables
	$(".nestedDatatable").each(function(){
		var id = $(this).attr("id"),
		childUrl = $(this).attr("childDetailURL"),
		emptyMessage = $(this).attr("emptyMessage"),
		detailTableInfo = $(this).attr("detailTableInfo"),
		detailTableMapping = $(this).attr("detailTableMapping"),
		editDetail = $(this).attr("editDetail"),
		successLoadMessage = $(this).attr("successLoadMessage");
		$(this).nestedTable({
			tableId : id,
			urlForChild:contextPath+childUrl,
			emptyMessage: showMessage(emptyMessage),
			detailTableInfo: detailTableInfo,
			openIcon: contextPath+"/images/SD7Dz.png",
			closeIcon:contextPath+"/images/d4ICC.png",
			editDetail:editDetail.length >0 ? contextPath+editDetail : false,
			detailTableMapping:detailTableMapping,
			successLoadMessage:showMessage(successLoadMessage)
		});
	});
	
	//select for displaying
	var checkTypeOfContainer = function(comp){
		var dests = $(comp).attr("showElements").split(";");
		for (var i in dests){
			var comps = dests[i].split(":"),
			targets = comps[1].split(",");
			if ($(comp).find(":selected").val() == comps[0]){
				for (var j in targets){
					$("#"+targets[j]).show();
				}
			}
			else{
				for (var j in targets){
					$("#"+targets[j]).hide();
				}
			}
		}
		
	};
	$("[showElements]").each(function(){
			checkTypeOfContainer($(this));
			$(this).on("change",function(){
				checkTypeOfContainer($(this));
			});
	});
	
	$("input.money").each(function(){
		$(this).val(accounting.formatMoney($(this).val(),UTIL.MONEY_STYLE)); 
	}).on("blur",function(){
		$(this).val(accounting.formatMoney($(this).val(),UTIL.MONEY_STYLE)); 
	});
	
	$("input.number").each(function(){
		$(this).val(accounting.formatMoney($(this).val(),UTIL.NUMBER_STYLE)); 
	}).on("blur",function(){
		$(this).val(accounting.formatMoney($(this).val(),UTIL.NUMBER_STYLE)); 
	});
});
