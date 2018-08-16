package cn.aliyun.ttms.product.controller;

import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import cn.aliyun.ttms.common.web.JsonResult;
import cn.aliyun.ttms.product.entity.ProductType;
import cn.aliyun.ttms.product.service.ProductTypeService;

@Controller
@RequestMapping("/productType")
public class ProductController {
		@Resource
		private ProductTypeService productTypeService;
		@RequestMapping("/editUI")
		public String editUI(){
			return "product/type_edit";
		}
		@RequestMapping("/listUI")
		public String listUI(){
			return "product/type_list";
		}
		@RequestMapping("/doFindObjects")
		@ResponseBody
		public JsonResult doFindObjects(){
			List<Map<String, Object>> list=productTypeService.findObjects();
			return new JsonResult(list);
		}
		@RequestMapping("/doFindTreeNodes")
		@ResponseBody
		public JsonResult doFindTreeNodes() {
			List<Map<String,Object>> map=
					productTypeService.findTreeNodes();
			System.out.println("ProductController.map="+map);
			return new JsonResult(map);
		}
		
		@RequestMapping("doSaveObject")
		@ResponseBody
		public JsonResult doSaveObject(ProductType type) {
			productTypeService.saveObject(type);
			return new JsonResult();
		}
		
		
		
		
}
