package com.csl.plus.portal.marking.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.csl.plus.cms.entity.CmsArticle;
import com.csl.plus.cms.entity.CmsNotice;
import com.csl.plus.cms.entity.CmsSubject;
import com.csl.plus.marking.entity.*;
import com.csl.plus.marking.mapper.SmsHomeAdvertiseMapper;
import com.csl.plus.oms.vo.HomeContentResult;
import com.csl.plus.pms.entity.PmsBrand;
import com.csl.plus.pms.entity.PmsProduct;
import com.csl.plus.pms.entity.PmsProductAttributeCategory;
import com.csl.plus.pms.entity.PmsProductCategory;
import com.csl.plus.portal.cms.service.*;
import com.csl.plus.portal.marking.service.*;
import com.csl.plus.portal.oms.service.IOmsOrderService;
import com.csl.plus.portal.pms.service.IPmsBrandService;
import com.csl.plus.portal.pms.service.IPmsProductAttributeCategoryService;
import com.csl.plus.portal.pms.service.IPmsProductCategoryService;
import com.csl.plus.portal.pms.service.IPmsProductService;
import com.csl.plus.portal.res.service.*;
import com.csl.plus.portal.rms.service.IFinRequirementCategoryService;
import com.csl.plus.portal.rms.service.IPrdRequirementCategoryService;
import com.csl.plus.portal.rms.service.ITechRequirementCategoryService;
import com.csl.plus.portal.ums.service.IUmsMemberLevelService;
import com.csl.plus.portal.ums.service.IUmsMemberService;
import com.csl.plus.res.entity.*;
import com.csl.plus.rms.entity.FinRequirementCategory;
import com.csl.plus.rms.entity.PrdRequirementCategory;
import com.csl.plus.rms.entity.TechRequirementCategory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 首页轮播广告表 服务实现类
 * </p>
 *
 * @since 2019-04-19
 */
@Service
public class SmsHomeAdvertiseServiceImpl extends ServiceImpl<SmsHomeAdvertiseMapper, SmsHomeAdvertise>
        implements ISmsHomeAdvertiseService {
    @Autowired
    private IUmsMemberService memberService;
    @Autowired
    private ISmsHomeAdvertiseService advertiseService;
    @Autowired
    private IOmsOrderService orderService;
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
    private ISmsHomeBrandService homeBrandService;
    @Resource
    private ISmsHomeNewProductService homeNewProductService;
    @Resource
    private ISmsHomeRecommendProductService homeRecommendProductService;
    @Resource
    private ISmsHomeRecommendSubjectService homeRecommendSubjectService;

    @Resource
    private ICmsSubjectCategoryService subjectCategoryService;
    @Resource
    private ICmsSubjectService subjectService;
    @Resource
    private ICmsSubjectCommentService commentService;
    @Resource
    private IPmsBrandService brandService;
    @Resource
    private ICmsArticleService articleService;
    @Resource
    private ICmsNoticeService noticeService;

    @Resource
    private IPmsProductCategoryService pmsProductCategoryService;
    @Resource
    private IPrdRequirementCategoryService prdRequirementCategoryService;
    @Resource
    private ITechRequirementCategoryService techRequirementCategoryService;
    @Resource
    private IFinRequirementCategoryService finRequirementCategoryService;

    @Resource
    private IResFinanceCategoryService resFinanceCategoryService;
    @Resource
    private IResFinanceService resFinanceService;
    @Resource
    private IResProductCategoryService resProductCategoryService;
    @Resource
    private IResProductService resProductService;
    @Resource
    private IResProjCategoryService resProjCategoryService;
    @Resource
    private IResProjProductionService resProjProductionService;
    @Resource
    private IResTechnicalCategoryService resTechnicalCategoryService;
    @Resource
    private IResTechnicalService resTechnicalService;
    @Resource
    private IResExpertService expertService;


    @Override
    public HomeContentResult singelContent() {
        HomeContentResult result = new HomeContentResult();
        // 获取首页广告
        result.setAdvertiseList(getHomeAdvertiseList());
        result.setCmsArticleList(this.getArticleList(0, 5));
        result.setCmsNoticeList(this.getNoticeList(0, 5));

        // 获取推荐品牌
        result.setBrandList(this.getRecommendBrandList(0, 4));
        // 获取新品推荐
        result.setNewProductList(this.getNewProductList(0, 4));
        result.setPmsProductCategoryList(this.getPmsProductCategoryList(0, 10));
        result.setFinRequirementCategoryList(this.getFinRequirementCategoryList(0, 10));
        result.setTechRequirementCategoryList(this.getTechRequirementCategoryList(0, 10));
        result.setPrdRequirementCategoryList(this.getPrdRequirementCategoryList(0, 10));

        //返回资源库
        result.setFinResourceCategories(this.getFinResourceCategoryList(0, 5));
        result.setFinResourceList(this.getFinResourceList(0, 5));
        result.setExpertResourceList(this.getExpertList(0, 5));
        result.setProdResourceCategories(this.getProdResourceCategoryList(0, 5));
        result.setProdResourceList(this.getProdResourceList(0, 5));
        result.setProjectResourceCategories(this.getProjectResourceCategoryList(0, 5));
        result.setProjectResourceList(this.getProjectResourceList(0, 5));
        result.setTechResourceCategories(this.getTechResourceCategoryList(0, 5));
        result.setTechResourceList(this.getTechResourceList(0, 5));

        // 获取人气推荐
        //result.setHotProductList(this.getHotProductList(0, 4));
        // 获取推荐专题
        //result.setSubjectList(this.getRecommendSubjectList(0, 4));
        List<PmsProductAttributeCategory> productAttributeCategoryList = productAttributeCategoryService
                .list(new QueryWrapper<>());

        for (PmsProductAttributeCategory gt : productAttributeCategoryList) {
            PmsProduct productQueryParam = new PmsProduct();
            productQueryParam.setProductAttributeCategoryId(gt.getId());
            productQueryParam.setPublishStatus(1);
            productQueryParam.setVerifyStatus(1);
            List<PmsProduct> goodsList = pmsProductService.list(new QueryWrapper<>(productQueryParam));
            if (goodsList != null && goodsList.size() > 0) {
                PmsProduct pmsProduct = goodsList.get(0);
                PmsProduct product = new PmsProduct();
                BeanUtils.copyProperties(pmsProduct, product);
                // product.setType(1);
                goodsList.add(product);
            }
            gt.setGoodsList(goodsList);
        }
        //result.setCat_list(productAttributeCategoryList);
        return result;
    }

    @Override
    public List<PmsBrand> getRecommendBrandList(int pageNum, int pageSize) {
        List<SmsHomeBrand> brands = homeBrandService.list(new QueryWrapper<>());
        List<Long> ids = new ArrayList<>();
        for (SmsHomeBrand shb : brands) {
            ids.add(shb.getBrandId());
        }
        return (List<PmsBrand>) brandService.listByIds(ids);

    }

    @Override
    public List<PmsProduct> getNewProductList(int pageNum, int pageSize) {
        List<SmsHomeNewProduct> products = homeNewProductService.list(new QueryWrapper<>());
        List<Long> ids = new ArrayList<>();
        for (SmsHomeNewProduct sp : products) {
            ids.add(sp.getProductId());
        }
        return (List<PmsProduct>) pmsProductService.listByIds(ids);
    }

    @Override
    public List<PmsProduct> getHotProductList(int pageNum, int pageSize) {
        List<SmsHomeRecommendProduct> products = homeRecommendProductService.list(new QueryWrapper<>());
        List<Long> ids = new ArrayList<>();
        for (SmsHomeRecommendProduct sp : products) {
            ids.add(sp.getProductId());
        }
        return (List<PmsProduct>) pmsProductService.listByIds(ids);
    }

    @Override
    public List<CmsSubject> getRecommendSubjectList(int pageNum, int pageSize) {
        List<SmsHomeRecommendSubject> subjects = homeRecommendSubjectService.list(new QueryWrapper<>());
        List<Long> ids = new ArrayList<>();
        for (SmsHomeRecommendSubject sub : subjects) {
            ids.add(sub.getSubjectId());
        }
        return (List<CmsSubject>) subjectService.listByIds(ids);
    }

    @Override
    public List<SmsHomeAdvertise> getHomeAdvertiseList() {
        SmsHomeAdvertise advertise = new SmsHomeAdvertise();
        advertise.setStatus(1);
        return advertiseService.list(new QueryWrapper<>(advertise));
    }

    @Override
    public List<CmsArticle> getArticleList(int pageNum, int pageSize) {
        CmsArticle cmsArticle = new CmsArticle();
        return articleService.list(new QueryWrapper<>(cmsArticle));
    }

    @Override
    public List<CmsNotice> getNoticeList(int pageNum, int pageSize) {
        CmsNotice cmsNotice = new CmsNotice();
        return noticeService.list(new QueryWrapper<>(cmsNotice));
    }

    @Override
    public List<ResExpert> getExpertList(int pageNum, int pageSize) {
        ResExpert resExpert = new ResExpert();
        return expertService.list(new QueryWrapper<>(resExpert));
    }

    @Override
    public List<PmsProductCategory> getPmsProductCategoryList(int pageNum, int pageSize) {
        PmsProductCategory pmsProductCategory = new PmsProductCategory();
        return pmsProductCategoryService.list(new QueryWrapper<>(pmsProductCategory));
    }

    @Override
    public List<PrdRequirementCategory> getPrdRequirementCategoryList(int pageNum, int pageSize) {
        PrdRequirementCategory prdRequirementCategory = new PrdRequirementCategory();
        return prdRequirementCategoryService.list(new QueryWrapper<>(prdRequirementCategory));
    }

    @Override
    public List<TechRequirementCategory> getTechRequirementCategoryList(int pageNum, int pageSize) {
        TechRequirementCategory techRequirementCategory = new TechRequirementCategory();
        return techRequirementCategoryService.list(new QueryWrapper<>(techRequirementCategory));
    }

    @Override
    public List<FinRequirementCategory> getFinRequirementCategoryList(int pageNum, int pageSize) {
        FinRequirementCategory finRequirementCategory = new FinRequirementCategory();
        return finRequirementCategoryService.list(new QueryWrapper<>(finRequirementCategory));
    }

    @Override
    public List<ResFinanceCategory> getFinResourceCategoryList(int pageNum, int pageSize) {
        ResFinanceCategory resFinanceCategory = new ResFinanceCategory();
        return resFinanceCategoryService.list(new QueryWrapper<>(resFinanceCategory));
    }

    @Override
    public List<ResFinance> getFinResourceList(int pageNum, int pageSize) {
        ResFinance resFinance = new ResFinance();
        return resFinanceService.list(new QueryWrapper<>(resFinance));
    }

    @Override
    public List<ResTechnicalCategory> getTechResourceCategoryList(int pageNum, int pageSize) {
        ResTechnicalCategory resTechnicalCategory = new ResTechnicalCategory();
        return resTechnicalCategoryService.list(new QueryWrapper<>(resTechnicalCategory));
    }

    @Override
    public List<ResTechnical> getTechResourceList(int pageNum, int pageSize) {
        ResTechnical resTechnical = new ResTechnical();
        return resTechnicalService.list(new QueryWrapper<>(resTechnical));
    }

    @Override
    public List<ResProductCategory> getProdResourceCategoryList(int pageNum, int pageSize) {
        ResProductCategory resProductCategory = new ResProductCategory();
        return resProductCategoryService.list(new QueryWrapper<>(resProductCategory));
    }

    @Override
    public List<ResProduct> getProdResourceList(int pageNum, int pageSize) {
        ResProduct resProduct = new ResProduct();
        return resProductService.list(new QueryWrapper<>(resProduct));
    }

    @Override
    public List<ResProjCategory> getProjectResourceCategoryList(int pageNum, int pageSize) {
        ResProjCategory resProjCategory = new ResProjCategory();
        return resProjCategoryService.list(new QueryWrapper<>(resProjCategory));
    }

    @Override
    public List<ResProjProduction> getProjectResourceList(int pageNum, int pageSize) {
        ResProjProduction resProjProduction = new ResProjProduction();
        return resProjProductionService.list(new QueryWrapper<>(resProjProduction));
    }

}
