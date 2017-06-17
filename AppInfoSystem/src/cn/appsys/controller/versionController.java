package cn.appsys.controller;

import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.commons.io.FilenameUtils;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONArray;

import cn.appsys.pojo.app_info;
import cn.appsys.pojo.app_version;
import cn.appsys.pojo.dev_user;
import cn.appsys.service.appinfoService;
import cn.appsys.service.versionService;
import cn.appsys.tools.Constants;

@RequestMapping(value = "/developer")
@Controller
public class versionController {
	private Logger logger = Logger.getLogger(versionController.class);

	ApplicationContext context = new ClassPathXmlApplicationContext(
			"applicationContext-mybatis.xml");

	// 新增版本之前的内容
	@RequestMapping(value = "/appversionadd")
	public String addversion(@RequestParam String id, Model model,
			HttpServletRequest request) {
		versionService versionservice = (versionService) context
				.getBean("versionService");
		List<app_version> lists = versionservice.selectaddversion(Integer
				.parseInt(id));
		request.setAttribute("appVersionList", lists);
		model.addAttribute("appid", id);
		return "/developer/appversionadd";

	}

	@RequestMapping(value = "/addversionsave", method = RequestMethod.POST)
	public String addversion(
			@Valid app_version appversion,
			@RequestParam Integer appid,
			HttpSession session,
			HttpServletRequest request,
			@RequestParam(value = "a_downloadLink", required = false) MultipartFile attach) {
		versionService versionservice = (versionService) context
				.getBean("versionService");
		appversion.setCreatedBy(((dev_user) session
				.getAttribute(Constants.DEVUSER_SESSION)).getId());
		appversion.setCreationDate(new Date());
		appversion.setAppId(appid);
		String apkLocPath = null;
		// 判断文件是否为空
		String oldFileName = null;
		if (attach.isEmpty()) {
			String path = request.getSession().getServletContext()
					.getRealPath("statics" + File.separator + "uploadfiles");
			oldFileName = attach.getOriginalFilename();// 原文件名
			System.out.println(oldFileName);
			String prefix = FilenameUtils.getExtension(oldFileName);// 原文件后缀
			System.out.println(prefix + "******************************");
			int filesize = 500000;
			if (attach.getSize() > filesize) {// 上传大小不得超过 500k
				request.setAttribute("uploadFileError", " * 上传大小不得超过 500k");
				return "/developer/appversionadd";
			} else if (prefix.equalsIgnoreCase("apk")) {// 上传图片格式不正确
				String fileName = System.currentTimeMillis() + "_Personal.apk";
				logger.debug("new fileName======== " + attach.getName());
				File targetFile = new File(path, fileName);
				if (!targetFile.exists()) {
					targetFile.mkdirs();
				}
				// 保存
				try {
					attach.transferTo(targetFile);
				} catch (Exception e) {
					e.printStackTrace();
					request.setAttribute("uploadFileError", " * 上传失败！");
					return "/developer/appversionadd";
				}
				apkLocPath = path + File.separator + fileName;
			} else {
				request.setAttribute("uploadFileError", " * 上传图片格式不正确");
				return "/developer/appversionadd";
			}
		}
		appversion.setApkLocPath(apkLocPath);
		appversion.setApkFileName(oldFileName);
		appversion.setModifyDate(new Date());
		if (versionservice.addversion(appversion)) {
			appinfoService appservice = (appinfoService) context
					.getBean("AppinfoService");
			app_info appinfo = new app_info();
			appinfo.setModifyBy(((dev_user) session
					.getAttribute(Constants.DEVUSER_SESSION)).getId());
			appinfo.setModifyDate(new Date());
			appinfo.setId(appid);
			appinfo.setVersionId(versionservice.selectid());

			if (appservice.editversion(appinfo)) {
				return "redirect:/developer/appinfolist.html";
			} else {
				return "/developer/appversionadd";
			}

		} else {
			return "/developer/appversionadd";
		}

	}

	// 修改版本之前的内容
	@RequestMapping(value = "appversionmodify")
	public String editVersion(@RequestParam(required = false) String aid,
			Model model, HttpServletRequest request) {
		System.out.println(aid);
		versionService versionservice = (versionService) context
				.getBean("versionService");
		List<app_version> lists = versionservice.selectaddversion(Integer
				.parseInt(aid));
		request.setAttribute("appVersionList", lists);
		model.addAttribute("appid", aid);
		app_version appver = versionservice.selectLast(Integer.parseInt(aid));
		request.setAttribute("appVersion", appver);

		return "/developer/appversionmodify";

	}

	// 查看
	@RequestMapping(value = "/appview")
	public String selectinfoByid(@RequestParam(required = false) String id,
			HttpServletRequest request) {
		appinfoService appservice = (appinfoService) context
				.getBean("AppinfoService");
		app_info appinfo = appservice.selectinfoById(Integer.parseInt(id));
		request.setAttribute("appInfo", appinfo);
		versionService versionservice = (versionService) context
				.getBean("versionService");
		System.out.println(Integer.parseInt(id));
		List<app_version> lists2 = versionservice.selectaddversion(Integer
				.parseInt(id));
		request.setAttribute("appVersionList", lists2);
		return "/developer/appinfoview";
	}

	// 确定修改
	@RequestMapping(value = "/appversionmodifysave")
	public String editVersion(
			@Valid app_version appversion,
			HttpServletRequest request,
			@RequestParam(value = "attach", required = false) MultipartFile attach) {
		versionService versionservice = (versionService) context
				.getBean("versionService");
		String apkLocPath = null;
		// 判断文件是否为空
		String oldFileName = null;
		if (attach.isEmpty()) {
			String path = request.getSession().getServletContext()
					.getRealPath("statics" + File.separator + "uploadfiles");
			oldFileName = attach.getOriginalFilename();// 原文件名
			String prefix = FilenameUtils.getExtension(oldFileName);// 原文件后缀
			int filesize = 500000;
			if (attach.getSize() > filesize) {// 上传大小不得超过 500k
				request.setAttribute("uploadFileError", " * 上传大小不得超过 500k");
				return "/developer/appversionmodifysave";
			} else if (prefix.equalsIgnoreCase("apk")) {// 上传图片格式不正确
				String fileName = System.currentTimeMillis() + "_Personal.apk";
				logger.debug("new fileName======== " + attach.getName());
				File targetFile = new File(path, fileName);
				if (!targetFile.exists()) {
					targetFile.mkdirs();
				}
				// 保存
				try {
					attach.transferTo(targetFile);
				} catch (Exception e) {
					e.printStackTrace();
					request.setAttribute("uploadFileError", " * 上传失败！");
					return "/developer/appversionmodifysave";
				}
				apkLocPath = path + File.separator + fileName;
			} else {
				request.setAttribute("uploadFileError", " * 上传图片格式不正确");
				return "/developer/appversionmodifysave";
			}
		}

		appversion.setApkLocPath(apkLocPath);
		appversion.setApkFileName(oldFileName);
		if (versionservice.editVersion(appversion)) {
			return "redirect:/developer/appinfolist.html";
		} else {
			return "/developer/appversionmodifysave";
		}

	}

	// 删除
	@ResponseBody
	@RequestMapping(value = "delapp")
	public Object delete(int id) {

		HashMap<String, String> result = new HashMap<String, String>();
		appinfoService appservice = (appinfoService) context
				.getBean("AppinfoService");
		app_info appinfo = appservice.selectedit(id);
		if (appinfo == null) {
			result.put("delResult", "notexist");
		} else {
			if (appservice.deleteByid(id)) {
				result.put("delResult", "true");
			} else {
				result.put("delResult", "false");
			}
		}
		return JSONArray.toJSONString(result);
	}
}
