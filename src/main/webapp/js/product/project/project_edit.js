var id = $('#modal-dialog').data("id");
if (id) {doGetObjectById();}
$('#modal-dialog').on('click', '.ok', submitEditForm);
$("#modal-dialog").on("hidden.bs.modal", function() {
	$(this).off('click', '.ok')
	       .removeData("id");
})
//保存表单数据
function submitEditForm(){
	 if($('#editFormId').valid()){
	 var data = getFormParameterValues();
	 var url=id?'project/update.do':'project/save.do';
	 $.post(url,data,function(jsonResult){
		if(jsonResult.state==1){
		 $('#modal-dialog').modal('hide');
		 doGetObjects();
		}else{ 
			alert(jsonResult.message);
		}
	 });
	}
}
//获取表单数据
function getFormParameterValues(){
	var name = $('#nameId').val();
	var code=$('#codeId').val();
	var beginDate=$('#beginDateId').val();
	var endDate=$('#endDateId').val();
	var valid = $('input[name="valid"]:checked').val();
	var note=$('#noteId').val();
	var params = {
			'id':id,
			'name':name,
			'code':code,
			'beginDate':beginDate,
			'endDate':endDate,
			'valid':valid,
			'note':note
	};
	return params;
}
//================================================
function doGetObjectById() {
	var params = {'id':id};
	var url ='project/findById.do';
	$.post(url, params, function(result) {
		if (result.state == 1) {
			fillUpdateForm(result.data)
		} else {
			alert(result.message);
		}
	})
}
//填充编辑页面表单
function fillUpdateForm(obj){	
	$('#nameId').val(obj.name);
	$('#codeId').val(obj.code);
	$('#beginDateId').val(new Date(obj.beginDate).toLocaleDateString());
	$('#endDateId').val(new Date(obj.endDate).toLocaleDateString());
 	$('#noteId').html(obj.note);
 	$('input[name="valid"]').each(function(){
 		if($(this).val()==obj.valid){
 			$(this).prop("checked",true);
 		}
 	})
}