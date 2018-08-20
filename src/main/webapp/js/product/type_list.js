var columns = [
{
field : 'selectItem',
radio : true
},
{
title : '分类id',
field : 'id',
visible : false,
align : 'center',
valign : 'middle',
width : '80px'
},
{
title : '分类名称',
field : 'name',
align : 'center',
valign : 'middle',
sortable : true,
width : '180px'
},
{
title : '上级分类',
field : 'parentName',
align : 'center',
valign : 'middle',
sortable : true,
width : '180px'
},
{
title : '排序号',
field : 'sort',
align : 'center',
valign : 'middle',
sortable : true,
width : '100px'
}];


$(document).ready(function(){
	doGetObjects();
	//$("#formHead").on("click",".btn-add",loadEditPage).on("click","load-product-type",loadTypeTree());
	$('#formHead').on('click','.btn-add,.btn-update',doLoadEditUI);
	$('#formHead').on('click','.btn-delete',doDeleteById)
})
//加载编辑页面
function loadEditPage(){
	var url="productType/editUI.do"
	//container为index.jsp中div的一个id
	$("#container").load(url);
}

//定义树的基本配置
var setting={
			data : {
			    simpleData : {
					enable : true,
					idKey : "id",  //节点数据中保存唯一标识的属性名称
					pIdKey : "parentId",  //节点数据中保存其父节点唯一标识的属性名称
					rootPId : null  //根节点id
				}
		}
	}
function doGetObjects(){//加载数据(以树结构形式进行展示)
	var url="productType/doFindObjects.do";
	var tableId="typeTable";
	//var columns=[{},{}];//表头列名字???
	//构建treetable对象并进行初始化(参考tree.table.js)
	var table=new TreeTable(tableId,url,columns);
	table.setIdField("id");//设置选返回值
	table.setCodeField("id");//设置父子关系
	table.setParentCodeField("parentId");//设置父子关系
	table.setExpandColumn(2);//设置点击第几列展开
	table.setExpandAll(false);//设置初始化时是否全部展开
	table.init();
}
var e = 1;
var TypeObject = {
	id : "typeTable",
	table : null,
	layerIndex : -1
};
TypeObject.initColumn = function() {
	
	return columns;
};

//显示新增菜单页面
function doLoadEditUI(){
	var url = 'productType/editUI.do';
	//debugger
	if($(this).hasClass("btn-update")){
		var selected = $('#typeTable').bootstrapTreeTable('getSelections');
		if (selected.length == 0) {
	        alert("请选择一条记录");
	        return false;
	    } 
		$('#container').data('typeId',selected[0].id);
	}
	$('#container').load(url);
}
function doDeleteById(){
	var selected = $('#typeTable').bootstrapTreeTable('getSelections');
	if (selected.length == 0) {
        alert("请选择一条记录");
        return false;
    } 
	var typeId = selected[0].id
	var params = {'id':typeId};
	var url = 'productType/doDeleteObjectById.do';
	$.post(url,params,function(result){
		if(result.state==1){
			alert('删除成功！');
			doGetObjects();
		}else{
			alert(result.message);
		}
	})
}
