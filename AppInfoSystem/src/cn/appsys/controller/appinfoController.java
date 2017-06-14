package cn.appsys.controller;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.io.FilenameUtils;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

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
	private Logger logger = Logger.getLogger(appinfoController.class);
	
	ApplicationContext context = new ClassPathXmlApplicationContext(
			"applicationContext-mybatis.xml");
	
	
	//修改之前的内容
	@RequestMapping(value="/appinfomodify")
	public String editapplist(Integer id,Model model,HttpServletRequest request){
		appinfoService appservice = (appinfoService) context
				.getBean("AppinfoService");
		app_info appinfos = new app_info();
		appinfos=appservice.selectedit(id);
		request.setAttribute("appInfo", appinfos);
		
		model.addAttribute("pid",id);
		
		return "/developer/appinfomodify";
	}
	
	@RequestMapping(value="/appinfomodifysave")
	public String editapplist(@Valid app_info appinfo,@RequestParam("pid") String pid){
		System.out.println(pid);
		appinfoService appservice = (appinfoService) context
				.getBean("AppinfoService");
		appinfo.setId(Integer.parseInt(pid));
		if(appservice.editappinfo(appinfo)){
			return "redirect:/developer/appinfolist.html";
		}else{
			
			return "/developer/appinfomodify";
		}
		
	}
	
	
	/**
	 * 增加
	 * @return
	 */
	// 增加的平台加载
	@RequestMapping(value = "/appAddinfo")
	@ResponseBody
	public Object addpingtai() {
		data_dictService dictservice = (data_dictService) context
				.getBean("dictService");
		// 所属平台
		List<data_dictionary> listsss = dictservice.getdatalist("所属平台");

		return JSONArray.toJSONString(listsss);
	}

	// 增加的一级分类加载
	@RequestMapping(value = "/categorylevell")
	@ResponseBody
	public Object addinfoyijijiazai() {
		// 一级分类
		categoryService categoryservic = (categoryService) context
				.getBean("categoryservice");
		List<app_category> objs1 = categoryservic.getjibie(0);

		return JSONArray.toJSONString(objs1);
	}

	// 增加app基础信息
	@RequestMapping(value = "/appinfoadd.html")
	public String addappinfo() {

		return "/developer/appinfoadd";
	}

	@RequestMapping(value = "/appinfoaddsave",method=RequestMethod.POST)
	public String addappinfo(
			@RequestParam(required = false) String softwareName,
			@RequestParam(required = false) String APKName,
			@RequestParam(required = false) String supportROM,
			@RequestParam(required = false) String interfaceLanguage,
			@RequestParam(required = false) String softwareSize,
			@RequestParam(required = false) String downloads,
			@RequestParam(required = false) String flatformId,
			@RequestParam(required = false) String categoryLevel1,
			@RequestParam(required = false) String categoryLevel2,
			@RequestParam(required = false) String categoryLevel3,
			@RequestParam(required = false) String status,
			@RequestParam(required = false) String appInfo,
			@RequestParam(value ="a_logoPicPath", required = false) MultipartFile attach,
			HttpServletRequest request) {
		System.out.println("进啦********************");
		
		String idPicPath = null;
		//判断文件是否为空
		if(!attach.isEmpty()){
			String path = request.getSession().getServletContext().getRealPath("statics"+File.separator+"uploadfiles");			
			String oldFileName = attach.getOriginalFilename();//原文件名		
			String prefix=FilenameUtils.getExtension(oldFileName);//原文件后缀    	       
			int filesize = 500000;
			
	        if(attach.getSize() >  filesize){//上传大小不得超过 500k
            	request.setAttribute("uploadFileError", " * 上传大小不得超过 500k");
	        	return "/developer/appinfoadd";
            }else if(prefix.equalsIgnoreCase("jpg") || prefix.equalsIgnoreCase("png") 
            		|| prefix.equalsIgnoreCase("jpeg") || prefix.equalsIgnoreCase("pneg")){//上传图片格式不正确
            	String fileName = System.currentTimeMillis()+"_Personal.jpg";  
                logger.debug("new fileName======== " + attach.getName());
                File targetFile = new File(path, fileName);  
                if(!targetFile.exists()){  
                    targetFile.mkdirs();  
                }  
                //保存  
                try {  
                	attach.transferTo(targetFile);  
                } catch (Exception e) {  
                    e.printStackTrace();  
                    request.setAttribute("uploadFileError", " * 上传失败！");
                    return "/developer/appinfoadd";
                }  
                idPicPath = path+File.separator+fileName;
            }else{
            	request.setAttribute("uploadFileError", " * 上传图片格式不正确");
            	return "/developer/appinfoadd";
            }
		}
		

		if (softwareSize == null || softwareSize.equals("")) {
			softwareSize = "0";
		}
		if (downloads == null || downloads.equals("")) {
			downloads = "0";
		}
		if (flatformId == null || flatformId.equals("")) {
			flatformId = "0";
		}
		if (categoryLevel1 == null || categoryLevel1.equals("")) {
			categoryLevel1 = "0";
		}
		if (categoryLevel2 == null || categoryLevel2.equals("")) {
			categoryLevel2 = "0";
		}
		if (categoryLevel3 == null || categoryLevel3.equals("")) {
			categoryLevel3 = "0";
		}
		if (status == null || status.equals("")) {
			status = "0";
		}

		app_info appinfo = new app_info();
		appinfo.setSoftwareName(softwareName);
		appinfo.setAPKName(APKName);
		appinfo.setSupportROM(supportROM);
		appinfo.setInterfaceLanguage(interfaceLanguage);
		appinfo.setSoftwareSize(Double.parseDouble(softwareSize));
		appinfo.setDownloads(Integer.parseInt(downloads));
		appinfo.setFlatformId(Integer.parseInt(flatformId));
		appinfo.setCategoryLevel1(Integer.parseInt(categoryLevel1));
		appinfo.setCategoryLevel2(Integer.parseInt(categoryLevel2));
		appinfo.setCategoryLevel3(Integer.parseInt(categoryLevel3));
		appinfo.setSTATUS(Integer.parseInt(status));
		appinfo.setAppInfo(appInfo);
		appinfo.setLogoPicPath(idPicPath);
		appinfo.setVersionId(38);
		appinfoService appservice = (appinfoService) context
				.getBean("AppinfoService");
		boolean flag = appservice.addinfolist(appinfo);
		if (flag) {
			return "redirect:/developer/appinfolist.html";
		} else {
			return "/developer/appinfoadd";
		}

	}

	// 判断是否存在
	@RequestMapping(value = "/apkexist")
	@ResponseBody
	public Object getapkexist(String APKName) {
		HashMap<String, String> result = new HashMap<String, String>();
		appinfoService appservice = (appinfoService) context
				.getBean("AppinfoService");
		if (APKName.trim().length() == 0) {
			result.put("APKName", "empty");
		} else {
			boolean flag = appservice.getAPKName(APKName);
			if (flag) {
				result.put("APKName", "exist");
			} else {
				result.put("APKName", "noexist");
			}
		}
		return JSONArray.toJSONString(result);

	}

	/**
	 * 查询列表
	 * 
	 * @param querySoftwareName
	 * @param queryStatus
	 * @param queryFlatformId
	 * @param queryCategoryLevel1
	 * @param queryCategoryLevel2
	 * @param queryCategoryLevel3
	 * @param pageIndex
	 * @param request
	 * @return
	 */

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
	public Object getsanji(@RequestParam Integer pid) {
		categoryService categoryservic = (categoryService) context
				.getBean("categoryservice");
		// 三级分类

		// 二级分类
		List<app_category> objs3 = categoryservic.getjibie(pid);
		System.out.println(JSONArray.toJSONString(objs3));
		return JSONArray.toJSONString(objs3);

	}

}
