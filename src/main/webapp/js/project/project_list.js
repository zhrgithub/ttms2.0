$(document).ready(function(){
					$("#queryFormId").on("click",".btn-search",doGetObjects);	
					$("#queryFormId").on("click",".btn-valid,.btn-invalid",doValidById);	//启用和禁用
					$("#queryFormId").on("click",".btn-add,.btn-update",showEditDialog);	//添加或者更新
					//页面加载完成执行此方法
					//1.发起ajax请求(findObjects.do)
					//2.将返回的结果填充到content位置
					doGetObjects();
				});

				//显示编辑模态框
				function showEditDialog(){
					var url="project/editUI.do";
					var title;
					if($(this).hasClass("btn-add")){
						title="添加项目"
					}
					if($(this).hasClass("btn-update")){
						title="修改项目"
							//将要修改的记录的id值绑定到模态框上
							//目的是通过一个模块实现添加或者更新操作
							var id=$("#modal-dialog").data("id",$(this).parent().parent().data("id"));//绑定id值，根据对象找到修改
						console.log(id);
					
					}
					//在模态框的 .moday-body位置异步加载url
					$("#modal-dialog .modal-body").load(url,function(){
						console.log("url:"+url);
						$(".modal-title").html(title);
						$("#modal-dialog").modal("show");
					})
				}
				
				
//获取项目信息
function  doGetObjects(){
						//findPageObject(Project project,PageObject object)
						var url="project/findPageObjects.do";
						var params=getQueryFormData();
						
						//动态的给属性赋值   加载时为undefined
						var pageCurrent=$("#pageId").data("pageCurrent");
						if(pageCurrent){params.pageCurrent=pageCurrent};
						  console.log(params.name+"/"+params.valid+"/"+pageCurrent);
						$.post(url,params,function(result){
							if (result.state==1) {
								//map{"list":[{},{}],"pageObject":{"pageCount":2,...}}
								//设置表格tbody中的内容
								setTableRows(result.data.list);//map中的list(根据key取值list)
								//设置分页
								setPagination(result.data.pageObject);
							} else {
											alert(result.message);
							}
						});
					}
				
//获得查询表单中的数据
function getQueryFormData(){
	
	//获得tbody对应的dom节点对象
	var params={
	"name":$("#searchNameId").val().trim(),
	"valid":$("#searchValidId").val().trim(),
//	"pageCurrent":$("#pageId").data("pageCurrent")
	}
	return params;
}

//将从服务端获得的列表数据填充的表格中
function setTableRows(list){
			 var tBody=$('#tbodyId');
			tBody.empty();
			var temp='<td><input type="checkbox" name="checkedItem" value="[id]"/></td>'
				+'<td>[code]</td>'
				+'<td>[name]</td>'
				+'<td>[beginDate]</td>'
				+'<td>[endDate]</td>'
				+'<td>[valid]</td>'
				+'<td><button type="button" class="btn btn-success btn-update">修改</button></td>'
				 //追加新的数据
				 for(var i in list){//循环一次取一行数据(对应一对tr对象)
				  var tr=$('<tr></tr>');//创建一对tr对象
				  tr.data("id",list[i].id);//绑定数据,便于后续获得此数据进行修改等操作
		          tr.append(temp
		         .replace('[id]',list[i].id)
		         .replace('[code]',list[i].code)		        
		         .replace('[name]',list[i].name)
		         .replace('[beginDate]',list[i].beginDate)
		         .replace('[endDate]',list[i].endDate)
		         .replace('[valid]',list[i].valid?'启用':'禁用')
		          );
					tBody.append(tr);//将tr对象追加tbody
			}
		}		

/*启用/禁用*/
		function doValidById(){
				
			//1.判定触发的是启用还是禁用按钮（根据类选择器）
			var state;//定义一个状态值，表示状态
			if($(this).hasClass("btn-valid")){
				state=1;//启用（将选中的记录的valid修改为1）
			}else{
				state=0;//禁用
			}
			//2.获得选中的checkbox对应的id值
			var checkedIds=getCheckedIds();
			if(checkedIds==' '){
				console.log("值为空");
			}
			console.log("checkedIds="+checkedIds);//在浏览器的控制台输出已经选择的id
			var params={"checkedIds":checkedIds,"valid":state};
			//3.获得的数据通过Ajax发送异步请求到服务器然后执行更新操作
			//执行更新操作
			var url="project/doValidById.do";
			$.post(url,params,function(result){
				if (result.state==1) {//1表示查询成功了
					doGetObjects();//重新查询
				} else {
					alert(result.message);
				}
			})
}		
		function getCheckedIds(){
			 var checkedIds=' ';
			 //获得tbody对象中名字为checkedItem的input对象
			 
			 $('tbody input[name="checkedItem"]')
			 //迭代对象
			 .each(function(){
				 //判定这个input对象是否选中的input
				 if($(this).is(":checked")){
					 //将选中的checkbox的值拼接成字符串
					 if(checkedIds==' '){
						 checkedIds+=$(this).val();//this表示当前点击按钮对象
					 }else{
						 checkedIds+=","+$(this).val();
					 }//"1,2,3,4,5";
				 };
			 })
			 return checkedIds;
		 }

				
				
				
				
				
				
				
				
				
				
				
				