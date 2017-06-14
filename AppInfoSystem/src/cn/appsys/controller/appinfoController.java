package cn.appsys.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;

import cn.appsys.pojo.app_category;
import cn.appsys.pojo.app_info;
import cn.appsys.pojo.data_dictionary;
import cn.appsys.pojo.pages;
import cn.appsys.service.appinfoService;
import cn.appsys.service.categoryService;
import cn.appsys.service.data_dictService;
import cn.appsys.tools.Constants;

//app列表页面
@RequestMapping(value = "/developer")
@Controller
public class appinfoController {
	ApplicationContext context = new ClassPathXmlApplicationContext(
			"applicationContext-mybatis.xml");

	//增加的平台加载
	@RequestMapping(value = "/appAddinfo")
	@ResponseBody
	public Object addyiji() {
		data_dictService dictservice = (data_dictService) context
				.getBean("dictService");
		// 所属平台
		List<data_dictionary> listsss = dictservice.getdatalist("所属平台");

		// 一级分类
		categoryService categoryservic = (categoryService) context
				.getBean("categoryservice");
		List<app_category> objs1 = categoryservic.getjibie(0);
		
		return  JSONArray.toJSONString(listsss);
	}
	
	@RequestMapping(value = "/categorylevell")
	@ResponseBody
	public Object addinfojiazai() {
		// 一级分类
		categoryService categoryservic = (categoryService) context
				.getBean("categoryservice");
		List<app_category> objs1 = categoryservic.getjibie(0);
		
		return  JSONArray.toJSONString(objs1);
	}
	
	// 查询列表
	@RequestMapping(value = "/appinfolist.html")
	public String getinfo(
			@RequestParam(required = false) String querySoftwareName,
			@RequestParam(required = false) String queryStatus,
			@RequestParam(required = false) String queryFlatformId,
			@RequestParam(required = false) String queryCategoryLevel1,
			@RequestParam(required = false) String queryCategoryLevel2,
			@RequestParam(required = false) String queryCategoryLevel3,
			@RequestParam(required = false) String pageIndex,
			HttpServletRequest request) {
		appinfoService appservice = (appinfoService) context
				.getBean("AppinfoService");

		if (queryStatus == null || queryStatus.equals("")) {
			queryStatus = "0";
		}
		if (queryFlatformId == null || queryFlatformId.equals("")) {
			queryFlatformId = "0";
		}
		if (queryCategoryLevel1 == null || queryCategoryLevel1.equals("")) {
			queryCategoryLevel1 = "0";
		}
		if (queryCategoryLevel2 == null || queryCategoryLevel2.equals("")) {
			queryCategoryLevel2 = "0";
		}
		if (queryCategoryLevel3 == null || queryCategoryLevel3.equals("")) {
			queryCategoryLevel3 = "0";
		}
		if (pageIndex == null || pageIndex.equals("")) {
			pageIndex = "1";
		}
		app_info appinfo = new app_info();
		appinfo.setSoftwareName(querySoftwareName);
		appinfo.setSTATUS(Integer.parseInt(queryStatus));

		appinfo.setFlatformId(Integer.parseInt(queryFlatformId));
		appinfo.setCategoryLevel1(Integer.parseInt(queryCategoryLevel1));
		appinfo.setCategoryLevel2(Integer.parseInt(queryCategoryLevel2));
		appinfo.setCategoryLevel3(Integer.parseInt(queryCategoryLevel3));

		int totalCount = appservice.getCount(appinfo);
		int currentPageNo = Integer.parseInt(pageIndex);
		int totalPageCount = totalCount % Constants.PAGE_SIZE == 0 ? totalCount
				/ Constants.PAGE_SIZE : totalCount / Constants.PAGE_SIZE + 1;

		pages pages = new pages();
		pages.setTotalCount(totalCount);
		pages.setCurrentPageNo(currentPageNo);
		pages.setPageSize(Constants.PAGE_SIZE);
		pages.setTotalPageCount(totalPageCount);

		request.setAttribute("pages", pages);

		Map map = new HashMap();
		map.put("appinfo", appinfo);
		map.put("currentPageNo", (Integer.parseInt(pageIndex) - 1)
				* Constants.PAGE_SIZE);
		map.put("pageSize", Constants.PAGE_SIZE);

		List<app_info> lists = appservice.getappinfo(map);
		request.setAttribute("appInfoList", lists);

		// APP状态
		data_dictService dictservice = (data_dictService) context
				.getBean("dictService");
		List<data_dictionary> listss = dictservice.getdatalist("APP状态");

		request.setAttribute("statusList", listss);

		request.setAttribute("querySoftwareName", querySoftwareName);
		request.setAttribute("queryStatus", queryStatus);
		request.setAttribute("queryFlatformId", queryFlatformId);
		request.setAttribute("queryCategoryLevel1", queryCategoryLevel1);
		request.setAttribute("queryCategoryLevel2", queryCategoryLevel2);
		request.setAttribute("queryCategoryLevel3", queryCategoryLevel3);

		// 所属平台
		List<data_dictionary> listsss = dictservice.getdatalist("所属平台");

		request.setAttribute("flatFormList", listsss);

		// 一级分类
		categoryService categoryservic = (categoryService) context
				.getBean("categoryservice");
		List<app_category> objs1 = categoryservic.getjibie(0);
		request.setAttribute("categoryLevel1List", objs1);

		return "/developer/appinfolist";

	}

	// 二级分类
	@RequestMapping(value = "/categoryleve")
	@ResponseBody
	public Object geterji(@RequestParam Integer pid) {
		categoryService categoryservic = (categoryService) context
				.getBean("categoryservice");
		// 二级分类
		List<app_category> objs2 = categoryservic.getjibie(pid);
		return JSONArray.toJSONString(objs2);

	}

	// 三级分类
	@RequestMapping(value = "/categorylevellist")
	@ResponseBody
	public Object getsanji(@RequestParam("pid") Integer pid) {
		categoryService categoryservic = (categoryService) context
				.getBean("categoryservice");
		// 三级分类

		List<app_category> objs3 = categoryservic.getjibie(pid);
		System.out.println(JSONArray.toJSONString(objs3));
		return JSONArray.toJSONString(objs3);

	}

	// 增加app基础信息
	@RequestMapping(value = "/appinfoadd.html")
	public String addappinfo() {

		return "/developer/appinfoadd";

	}
}
