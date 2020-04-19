package com.csl.plus.marking.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.csl.plus.marking.entity.SmsCoupon;
import com.csl.plus.marking.entity.SmsCouponProductCategoryRelation;
import com.csl.plus.marking.entity.SmsCouponProductRelation;
import com.csl.plus.marking.mapper.SmsCouponMapper;
import com.csl.plus.marking.mapper.SmsCouponProductCategoryRelationMapper;
import com.csl.plus.marking.mapper.SmsCouponProductRelationMapper;
import com.csl.plus.marking.service.ISmsCouponProductCategoryRelationService;
import com.csl.plus.marking.service.ISmsCouponProductRelationService;
import com.csl.plus.marking.service.ISmsCouponService;
import com.csl.plus.marking.vo.SmsCouponParam;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 * 邀请码表 服务实现类
 * </p>
 *
 * @since 2019-04-19
 */
@Service
public class SmsCouponServiceImpl extends ServiceImpl<SmsCouponMapper, SmsCoupon> implements ISmsCouponService {

    @Resource
    private SmsCouponMapper couponMapper;
    @Resource
    private SmsCouponProductRelationMapper productRelationMapper;
    @Resource
    private SmsCouponProductCategoryRelationMapper productCategoryRelationMapper;
    @Resource
    private ISmsCouponProductRelationService productRelationDao;
    @Resource
    private ISmsCouponProductCategoryRelationService productCategoryRelationDao;

    @Override
    public boolean saves(SmsCouponParam couponParam) {
        couponParam.setCount(couponParam.getPublishCount());
        couponParam.setUseCount(0);
        couponParam.setReceiveCount(0);
        //插入邀请码表
        int count = couponMapper.insert(couponParam);
        //插入邀请码和商品关系表
        if (couponParam.getUseType().equals(2)) {
            for (SmsCouponProductRelation productRelation : couponParam.getProductRelationList()) {
                productRelation.setCouponId(couponParam.getId());
            }
            productRelationDao.saveBatch(couponParam.getProductRelationList());
        }
        //插入邀请码和商品分类关系表
//        if (couponParam.getUseType().equals(1)) {
//            for (SmsCouponProductCategoryRelation couponProductCategoryRelation : couponParam.getProductCategoryRelationList()) {
//                couponProductCategoryRelation.setCouponId(couponParam.getId());
//            }
//            productCategoryRelationDao.saveBatch(couponParam.getProductCategoryRelationList());
//        }
        return true;
    }

    @Override
    public boolean updateByIds(SmsCouponParam couponParam) {
        couponParam.setId(couponParam.getId());
        int count = couponMapper.updateById(couponParam);
        //删除后插入邀请码和商品关系表
        if (couponParam.getUseType().equals(2)) {
            for (SmsCouponProductRelation productRelation : couponParam.getProductRelationList()) {
                productRelation.setCouponId(couponParam.getId());
            }
            deleteProductRelation(couponParam.getId());
            productRelationDao.saveBatch(couponParam.getProductRelationList());
        }
        //删除后插入邀请码和商品分类关系表
        if (couponParam.getUseType().equals(1)) {
            for (SmsCouponProductCategoryRelation couponProductCategoryRelation : couponParam.getProductCategoryRelationList()) {
                couponProductCategoryRelation.setCouponId(couponParam.getId());
            }
            deleteProductCategoryRelation(couponParam.getId());
            productCategoryRelationDao.saveBatch(couponParam.getProductCategoryRelationList());
        }
        return true;
    }

    private void deleteProductCategoryRelation(Long id) {
        productCategoryRelationMapper.delete(new QueryWrapper<>(new SmsCouponProductCategoryRelation()).eq("coupon_id", id));
    }

    private void deleteProductRelation(Long id) {
        productRelationMapper.delete(new QueryWrapper<>(new SmsCouponProductRelation()).eq("coupon_id", id));
    }

    @Override
    public int delete(Long id) {
        //删除邀请码
        int count = couponMapper.deleteById(id);
        //删除商品关联
        deleteProductRelation(id);
        //删除商品分类关联
        deleteProductCategoryRelation(id);
        return count;
    }

    @Override
    public SmsCouponParam getItem(Long id) {
        return couponMapper.getItem(id);
    }
}
