package com.csl.plus.portal.vo;

import java.util.List;

import com.csl.plus.cms.entity.CmsSubject;
import com.csl.plus.marking.entity.SmsCoupon;
import com.csl.plus.marking.entity.SmsHomeAdvertise;
import com.csl.plus.marking.entity.SmsRedPacket;
import com.csl.plus.pms.entity.PmsProductAttributeCategory;

import lombok.Data;

/**
 * Created by Administrator on 2017/10/18 0018.
 */
@Data
public class IndexData {
	private List<TArticleDO> module_list;
	private List<SmsHomeAdvertise> banner_list;
	private List<TArticleDO> nav_icon_list;
	private List<PmsProductAttributeCategory> cat_list;
	private int cat_goods_cols;
	private List<TArticleDO> block_list;
	private List<SmsCoupon> coupon_list;
	private List<CmsSubject> subjectList;

	private List<SmsRedPacket> redPacketList;

}
