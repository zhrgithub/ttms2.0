$(document).ready(function(){
	$("#modal-dialog").on('click','.ok',doSaveOrUpdate);
	//获得模态框上绑定的id值
	var id=$("#modal-dialog").data("id");
	console.log(id+"1235154");
	//假如id有值，说明这是修改，然后根据id获得对象，初始化
	if(id)doGetObjectById(id);
	//当模态框隐藏时在.ok上绑定的事件执行解绑动作
	$("#modal-dialog").on("hidden.bs.modal",function(){
		$(this).off('click','.ok').removeData("id");
	});
})
//根据id查找project对象
function  doGetObjectById(id){
	var url="project/doFindById.do";
	var params={"id":id};
	$.post(url,params,function(result){
		if(result.state==1){
			//1.初始化表单数据
			doFillFormData(result.data);
		}else{
			alert(result.message);
		}
	});
}

//将获得的数据填充到form表单当中
function doFillFormData(obj){
	console.log("doFillFormData");
	$("#nameId").val(obj.name);
	$("#codeId").val(obj.code);
	$("#beginDateId").val(obj.beginDate);
	$("#endDateId").val(obj.endDate);
	$("#noteId").html(obj.note);
	//启用禁用
	$('#editFormId input[name="valid"]').each(function(){
		console.log($(this).val()+"/"+obj.valid);
		if($(this).val()==obj.valid){
			$(this).prop("checked",true);
		}
	})
}

//保存或者更新数据
function doSaveOrUpdate(){
	//验证存入的表单值不为空
	if($("#editFormId").valid()){//required
	//1.获得表单数据
	var params=doGetEditFormData();
	//2.将数据提交到服务器
	var id=$("#modal-dialog").data("id");
	console.log(id+"45678913515153564545455555");
	var url=id?"project/doUpdateProject.do":"project/doSaveProject.do";
	console.log(id+"66666666666666666666666666666666666666");
	$.post(url,params,function(result){
		if(result.state==1){
			//1.隐藏模态框
			$("#modal-dialog").modal("hide");
			//2.重新查询列表数据
			doGetObjects();
		}else{
			alert(result.message);
		}
	})
	}
}
//获得表单数据
function doGetEditFormData(){
	
	var params={
			"id":$("#modal-dialog").data("id"),//更新时需要
			"name":$("#nameId").val(),
			"code":$("#codeId").val(),
			"beginDate":$("#beginDateId").val(),
			"endDate":$("#endDateId").val(),
			"valid":$('input[name="valid"]:checked').val(),
			"note":$('#noteId').val()
	};
	//检测获得的结果
	console.log(JSON.stringify(params));
	return params;
}










