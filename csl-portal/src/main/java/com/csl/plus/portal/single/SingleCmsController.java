package com.csl.plus.portal.single;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.csl.plus.cms.entity.CmsArticle;
import com.csl.plus.cms.entity.CmsArticleData;
import com.csl.plus.cms.entity.CmsNotice;
import com.csl.plus.cms.entity.CmsNoticeData;
import com.csl.plus.cms.entity.CmsSubject;
import com.csl.plus.cms.entity.CmsSubjectCategory;
import com.csl.plus.cms.entity.CmsSubjectComment;
import com.csl.plus.common.utils.CommonCodeConst;
import com.csl.plus.portal.annotation.IgnoreAuth;
import com.csl.plus.portal.annotation.SysLog;
import com.csl.plus.portal.cms.service.ICmsArticleDataService;
import com.csl.plus.portal.cms.service.ICmsArticleService;
import com.csl.plus.portal.cms.service.ICmsNoticeDataService;
import com.csl.plus.portal.cms.service.ICmsNoticeService;
import com.csl.plus.portal.cms.service.ICmsSubjectCategoryService;
import com.csl.plus.portal.cms.service.ICmsSubjectCommentService;
import com.csl.plus.portal.cms.service.ICmsSubjectService;
import com.csl.plus.portal.marking.service.ISmsGroupService;
import com.csl.plus.portal.pms.service.IPmsProductAttributeCategoryService;
import com.csl.plus.portal.pms.service.IPmsProductCategoryService;
import com.csl.plus.portal.pms.service.IPmsProductService;
import com.csl.plus.portal.ums.service.IUmsMemberLevelService;
import com.csl.plus.ums.entity.UmsMember;
import com.csl.plus.ums.entity.UmsMemberLevel;
import com.csl.plus.utils.CommonResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

/**
 * @Date: 2019/4/2 15:02
 * @Description:
 */
@Slf4j
@RestController
@Api(tags = "CmsController", description = "内容关系管理")
@RequestMapping("/api/single/cms")
public class SingleCmsController extends ApiBaseAction {

	@Resource
	private ISmsGroupService groupService;
	@Resource
	private IUmsMemberLevelService memberLevelService;
	@Resource
	private IPmsProductService pmsProductService;
	@Resource
	private IPmsProductAttributeCategoryService productAttributeCategoryService;
	@Resource
	private IPmsProductCategoryService productCategoryService;

	@Resource
	private ICmsSubjectCategoryService subjectCategoryService;
	@Resource
	private ICmsSubjectService subjectService;
	@Resource
	private ICmsSubjectCommentService commentService;
	@Resource
	private ICmsArticleService cmsArticleService;
	@Resource
	private ICmsArticleDataService cmsArticleDataService;
	@Resource
	private ICmsNoticeService cmsNoticeService;
	@Resource
	private ICmsNoticeDataService cmsNoticeDataService;

	@IgnoreAuth
	@SysLog(MODULE = "cms", REMARK = "查询文章列表")
	@ApiOperation(value = "查询文章列表")
	@GetMapping(value = "/subject/list")
	public Object subjectList(CmsSubject subject,
			@RequestParam(value = "pageSize", required = false, defaultValue = "5") Integer pageSize,
			@RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum) {
		return new CommonResult()
				.success(subjectService.page(new Page<CmsSubject>(pageNum, pageSize), new QueryWrapper<>(subject)));
	}

	@SysLog(MODULE = "cms", REMARK = "查询文章分类列表")
	@IgnoreAuth
	@ApiOperation(value = "查询文章分类列表")
	@GetMapping(value = "/subjectCategory/list")
	public Object cateList(CmsSubjectCategory subjectCategory,
			@RequestParam(value = "pageSize", required = false, defaultValue = "5") Integer pageSize,
			@RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum) {
		return new CommonResult().success(subjectCategoryService.page(new Page<CmsSubjectCategory>(pageNum, pageSize),
				new QueryWrapper<>(subjectCategory)));
	}

	@SysLog(MODULE = "cms", REMARK = "查询文章评论列表")
	@IgnoreAuth
	@ApiOperation(value = "查询文章评论列表")
	@GetMapping(value = "/subjectComment/list")
	public Object subjectList(CmsSubjectComment subjectComment,
			@RequestParam(value = "pageSize", required = false, defaultValue = "5") Integer pageSize,
			@RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum) {
		return new CommonResult().success(commentService.page(new Page<CmsSubjectComment>(pageNum, pageSize),
				new QueryWrapper<>(subjectComment)));
	}

	@SysLog(MODULE = "cms", REMARK = "创建文章")
	@ApiOperation(value = "创建文章")
	@PostMapping(value = "/createSubject")
	public Object createSubject(CmsSubject subject, BindingResult result) {
		CommonResult commonResult;
		UmsMember member = this.getCurrentMember();
		if (member.getMemberLevelId() > 0) {
			UmsMemberLevel memberLevel = memberLevelService.getById(member.getMemberLevelId());
			CmsSubject newSubject = new CmsSubject();
			newSubject.setMemberId(member.getId());
			List<CmsSubject> subjects = subjectService.list(new QueryWrapper<>(newSubject));
			if (subjects != null && subjects.size() > memberLevel.getArticlecount()) {
				commonResult = new CommonResult().failed("你今天已经有发" + memberLevel.getArticlecount() + "篇文章");
				return commonResult;
			}
		}
		subject.setMemberId(member.getId());
		boolean count = subjectService.save(subject);
		if (count) {
			commonResult = new CommonResult().success(count);
		} else {
			commonResult = new CommonResult().failed();
		}
		return commonResult;
	}

	/**
	 * 新闻列表
	 */
	@IgnoreAuth
	@SysLog(MODULE = "cms", REMARK = "查询新闻列表")
	@ApiOperation(value = "查询新闻列表")
	@GetMapping(value = "/article/list")
	public Object getCmsArticleByPage(CmsArticle entity,
			@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
			@RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize) {
		try {
			entity.setStatus(CommonCodeConst.STATUS_ACTIVE);
			return new CommonResult().success(
					cmsArticleService.page(new Page<CmsArticle>(pageNum, pageSize), new QueryWrapper<>(entity)));
		} catch (Exception e) {
			log.error("根据条件查询所有文章表列表：%s", e.getMessage(), e);
		}
		return new CommonResult().failed();
	}

	/**
	 * 新闻详细内容
	 */
	@IgnoreAuth
	@SysLog(MODULE = "cms", REMARK = "根据Id查询新闻内容")
	@ApiOperation("根据Id查询新闻内容")
	@GetMapping("/article/{id}")
	public Object getCmsArticleDataById(@PathVariable String id,
			@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
			@RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize) {
		try {
			CmsArticleData entity = new CmsArticleData();
			entity.setId(id);
			entity.setStatus(CommonCodeConst.STATUS_ACTIVE);
			return new CommonResult().success(cmsArticleDataService.page(new Page<CmsArticleData>(pageNum, pageSize),
					new QueryWrapper<>(entity)));
		} catch (Exception e) {
			log.error("根据条件查询新闻内容：%s", e.getMessage(), e);
		}
		return new CommonResult().failed();
	}

	/**
	 * 通知列表
	 */
	@IgnoreAuth
	@SysLog(MODULE = "cms", REMARK = "查询通知列表")
	@ApiOperation("查询通知列表")
	@GetMapping("/notice/list")
	public Object getCmsNoticeByPage(CmsNotice entity,
			@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
			@RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize) {
		try {
			entity.setStatus(CommonCodeConst.STATUS_ACTIVE);
			return new CommonResult()
					.success(cmsNoticeService.page(new Page<CmsNotice>(pageNum, pageSize), new QueryWrapper<>(entity)));
		} catch (Exception e) {
			log.error("查询所有通知表列表：%s", e.getMessage(), e);
		}
		return new CommonResult().failed();
	}

	@IgnoreAuth
	@SysLog(MODULE = "cms", REMARK = "根据Id查询通知内容")
	@ApiOperation("根据Id查询通知内容")
	@GetMapping("/notice/{id}")
	public Object getCmsNoticeDataByPage(@PathVariable String id,
			@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
			@RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize) {
		try {
			CmsNoticeData entity = new CmsNoticeData();
			entity.setId(id);
			entity.setStatus(CommonCodeConst.STATUS_ACTIVE);
			return new CommonResult().success(
					cmsNoticeDataService.page(new Page<CmsNoticeData>(pageNum, pageSize), new QueryWrapper<>(entity)));
		} catch (Exception e) {
			log.error("根据Id查询通知内容：%s", e.getMessage(), e);
		}
		return new CommonResult().failed();
	}
}
