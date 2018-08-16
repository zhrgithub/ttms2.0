	$(document).ready(function(){
		$("#pageId").on('click','.pre,.next,.firstPage,.lastPage',jumpToPage);
				});

	//设置分页
	function setPagination(pageObject){
		//绑定总页数
		$("#pageId").data("pageCount",pageObject.pageCount);
		//绑定当前页数
		$("#pageId").data("pageCurrent",pageObject.pageCurrent);
	}

//跳转到下一页
function jumpToPage(){
	//获得点击对象上类(.class)属性对应的值
	var clazz=$(this).attr("class");
	//获得pageId对象上绑定的pageCurrent对应的值
	var pageCurrent=$('#pageId').data("pageCurrent");
	//获得pageId对象上绑定的pageCount对应的值
	var pageCount=$('#pageId').data("pageCount")
	//转到第一页
	if('firstPage' == clazz){
		pageCurrent = 1;
	}
	//判断点击的是否是上一页
	if(clazz=='pre'&&pageCurrent>1){
		pageCurrent--;
	}
	//判断点击的是否是下一页
	if(clazz=='next'&&pageCurrent<pageCount){
		pageCurrent++;
	}
	
	if('lastPage' == clazz){
		pageCurrent =pageCount;
	}
	
	
	//重写绑定pageCurrent的值,
	$('#pageId').data("pageCurrent",pageCurrent);
	//重新执行查询操作
	doGetObjects();
}

