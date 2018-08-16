$(document).ready(function(){
	$("#modal-dialog").on('click','.ok',doSaveOrUpdate);
	doInitProjectIdAndNames();
	//获得模态框上绑定的id值
	var id=$("#modal-dialog").data("id");
	console.log("id="+ id);
	//假如id有值，说明这是修改，然后根据id获得对象，初始化
	if(id)doGetObjectById(id);
	//当模态框隐藏时在.ok上绑定的事件执行解绑动作
	$("#modal-dialog").on("hidden.bs.modal",function(){
		$(this).off('click','.ok').removeData("id");
	});
	console.log("id="+id);
})

//获得项目的选项信息
function doInitProjectIdAndNames(){
	var url="team/doFindPrjIdNames.do";
	$.getJSON(url,function(result){
		if(result.state==1){
	        setProjectSelectOptions(result.data);
		}else{
			alert(result.message);
		}
	})
}

//初始化项目归属项目下拉列表
function  setProjectSelectOptions(list){
	var selectObj=$("#selectId");
	var optionObj="<option value=[id]>[name]</option>";
	for(var i in list){
	 selectObj.append(optionObj
				.replace("[id]",list[i].id)
				.replace("[name]",list[i].name));
	}
}

//根据id查找team对象
function  doGetObjectById(id){
	var url="team/doFindById.do";
	var params={"id":id};
	console.log("params:"+params)
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
	console.log(id);
	var url=id?"team/doUpdateTeam.do":"team/doSaveObject.do";
	//debugger
	console.log("123456789id="+id);
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
			"id":$("#modal-dialog").data("id"),//模态框获取的id
			"name":$("#nameId").val(),
			"projectId":$("#selectId").val(),
			"valid":$('input[name="valid"]:checked').val(),
			"note":$('#noteId').val()
	};
	//console.log(id);
	//检测获得的结果
	console.log("fsahfsfskjfksljfklsjlshfskjafhskj"+JSON.stringify(params));
	return params;
}





