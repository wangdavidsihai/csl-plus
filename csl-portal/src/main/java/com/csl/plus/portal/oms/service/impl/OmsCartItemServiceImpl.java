package com.csl.plus.portal.oms.service.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.csl.plus.oms.entity.OmsCartItem;
import com.csl.plus.oms.mapper.OmsCartItemMapper;
import com.csl.plus.oms.vo.CartProduct;
import com.csl.plus.oms.vo.CartPromotionItem;
import com.csl.plus.pms.entity.PmsProduct;
import com.csl.plus.pms.entity.PmsProductFullReduction;
import com.csl.plus.pms.entity.PmsProductLadder;
import com.csl.plus.pms.entity.PmsSkuStock;
import com.csl.plus.pms.mapper.PmsProductMapper;
import com.csl.plus.pms.vo.PromotionProduct;
import com.csl.plus.portal.oms.service.IOmsCartItemService;
import com.csl.plus.portal.ums.service.IUmsMemberService;
import com.csl.plus.portal.util.UserUtils;
import com.csl.plus.ums.entity.UmsMember;

/**
 * <p>
 * 购物车表 服务实现类
 * </p>
 *
 * @since 2019-04-17
 */
@Service
public class OmsCartItemServiceImpl extends ServiceImpl<OmsCartItemMapper, OmsCartItem> implements IOmsCartItemService {
	@Resource
	private OmsCartItemMapper cartItemMapper;
	@Resource
	private IUmsMemberService memberService;
	@Resource
	private PmsProductMapper pmsProductMapper;

	@Override
	public OmsCartItem add(OmsCartItem cartItem) {
		UmsMember currentMember = UserUtils.getCurrentMember();
		cartItem.setMemberId(currentMember.getId());
		cartItem.setMemberNickname(currentMember.getNickname());
		cartItem.setDeleteStatus(0);
		PmsProduct pmsProduct = pmsProductMapper.selectById(cartItem.getProductId());
		if (org.apache.commons.lang3.StringUtils.isBlank(cartItem.getProductPic())) {
			cartItem.setProductPic(pmsProduct.getPic());
		}
		cartItem.setProductBrand(pmsProduct.getBrandName());
		cartItem.setProductName(pmsProduct.getName());
		cartItem.setProductSn(pmsProduct.getProductSn());
		cartItem.setProductSubTitle(pmsProduct.getSubTitle());
		cartItem.setProductCategoryId(pmsProduct.getProductCategoryId());
		OmsCartItem existCartItem = getCartItem(cartItem);
		if (existCartItem == null) {
			cartItem.setCreateDate(new Date());
			cartItemMapper.insert(cartItem);
		} else {
			cartItem.setModifyDate(new Date());
			existCartItem.setQuantity(existCartItem.getQuantity() + cartItem.getQuantity());
			cartItemMapper.updateById(existCartItem);
			return existCartItem;
		}
		return cartItem;
	}

	/**
	 * 根据会员id,商品id和规格获取购物车中商品
	 */
	private OmsCartItem getCartItem(OmsCartItem cartItem) {
		OmsCartItem example = new OmsCartItem();
		example.setProductId(cartItem.getProductId());
		example.setDeleteStatus(0);

		if (!StringUtils.isEmpty(cartItem.getSp1())) {
			example.setSp1(cartItem.getSp1());
		}
		if (!StringUtils.isEmpty(cartItem.getSp2())) {
			example.setSp2(cartItem.getSp2());
		}
		if (!StringUtils.isEmpty(cartItem.getSp3())) {
			example.setSp3(cartItem.getSp3());
		}
		List<OmsCartItem> cartItemList = cartItemMapper.selectList(new QueryWrapper<>(example));
		if (!CollectionUtils.isEmpty(cartItemList)) {
			return cartItemList.get(0);
		}
		return null;
	}

	@Override
	public List<OmsCartItem> list(Long memberId, List<Long> ids) {

		OmsCartItem example = new OmsCartItem();
		example.setMemberId(memberId);
		example.setDeleteStatus(0);
		if (ids != null && ids.size() > 0) {
			return cartItemMapper.selectList(new QueryWrapper<>(example).in("id", ids));
		}
		return cartItemMapper.selectList(new QueryWrapper<>(example));
	}

	@Override
	public OmsCartItem selectById(Long id) {
		return cartItemMapper.selectById(id);
	}

	@Override
	public List<CartPromotionItem> listPromotion(Long memberId, List<Long> ids) {
		List<OmsCartItem> cartItemList = list(memberId, ids);
		List<CartPromotionItem> cartPromotionItemList = new ArrayList<>();
		if (!CollectionUtils.isEmpty(cartItemList)) {
			cartPromotionItemList = this.calcCartPromotion(cartItemList);
		}
		return cartPromotionItemList;
	}

	@Override
	public int updateQuantity(Long id, Long memberId, Integer quantity) {
		OmsCartItem cartItem = new OmsCartItem();
		cartItem.setQuantity(quantity);
		QueryWrapper example = new QueryWrapper();
		example.eq("deleteStatus", 0);
		example.eq("memberId", memberId);

		return cartItemMapper.update(cartItem, example);
	}

	@Override
	public int delete(Long memberId, List<Long> ids) {
		OmsCartItem record = new OmsCartItem();
		record.setDeleteStatus(1);
		QueryWrapper<OmsCartItem> example = new QueryWrapper<OmsCartItem>();
		example.in("id", ids).eq("memberId", memberId);
		return cartItemMapper.update(record, example);
	}

	@Override
	public CartProduct getCartProduct(Long productId) {
		return pmsProductMapper.getCartProduct(productId);
	}

	@Override
	public int updateAttr(OmsCartItem cartItem) {
		// 删除原购物车信息
		OmsCartItem updateCart = new OmsCartItem();
		updateCart.setId(cartItem.getId());
		updateCart.setModifyDate(new Date());
		updateCart.setDeleteStatus(1);
		cartItemMapper.updateById(updateCart);
		cartItem.setId(null);
		add(cartItem);
		return 1;
	}

	@Override
	public int clear(Long memberId) {
		OmsCartItem record = new OmsCartItem();
		record.setDeleteStatus(1);
		QueryWrapper<OmsCartItem> example = new QueryWrapper<OmsCartItem>();
		example.eq("memberId", memberId);
		return cartItemMapper.update(record, example);
	}

	@Override
	public OmsCartItem addCart(OmsCartItem cartItem) {
		UmsMember currentMember = UserUtils.getCurrentMember();
		cartItem.setMemberId(currentMember.getId());
		cartItem.setMemberNickname(currentMember.getNickname());
		cartItem.setDeleteStatus(0);
		PmsProduct pmsProduct = pmsProductMapper.selectById(cartItem.getProductId());
		if (org.apache.commons.lang3.StringUtils.isBlank(cartItem.getProductPic())) {
			cartItem.setProductPic(pmsProduct.getPic());
		}
		cartItem.setProductBrand(pmsProduct.getBrandName());
		cartItem.setProductName(pmsProduct.getName());
		cartItem.setProductSn(pmsProduct.getProductSn());
		cartItem.setProductSubTitle(pmsProduct.getSubTitle());
		cartItem.setProductCategoryId(pmsProduct.getProductCategoryId());
		OmsCartItem existCartItem = getCartItem(cartItem);
		if (existCartItem == null) {
			cartItem.setCreateDate(new Date());
			cartItemMapper.insert(cartItem);
		} else {
			cartItem.setModifyDate(new Date());
			existCartItem.setQuantity(existCartItem.getQuantity() + cartItem.getQuantity());
			cartItemMapper.updateById(existCartItem);
			return existCartItem;
		}
		return cartItem;
	}

	@Override
	public List<CartPromotionItem> calcCartPromotion(List<OmsCartItem> cartItemList) {
		// 1.先根据productId对CartItem进行分组，以spu为单位进行计算优惠
		Map<Long, List<OmsCartItem>> productCartMap = groupCartItemBySpu(cartItemList);
		// 2.查询所有商品的优惠相关信息
		List<PromotionProduct> promotionProductList = getPromotionProductList(cartItemList);
		// 3.根据商品促销类型计算商品促销优惠价格
		List<CartPromotionItem> cartPromotionItemList = new ArrayList<>();
		for (Map.Entry<Long, List<OmsCartItem>> entry : productCartMap.entrySet()) {
			Long productId = entry.getKey();
			PromotionProduct promotionProduct = getPromotionProductById(productId, promotionProductList);
			List<OmsCartItem> itemList = entry.getValue();
			Integer promotionType = promotionProduct.getPromotionType();
			if (promotionType == 1) {
				// 单品促销
				for (OmsCartItem item : itemList) {
					CartPromotionItem cartPromotionItem = new CartPromotionItem();
					BeanUtils.copyProperties(item, cartPromotionItem);
					cartPromotionItem.setPromotionMessage("单品促销");
					// 商品原价-促销价
					PmsSkuStock skuStock = getOriginalPrice(promotionProduct, item.getProductSkuId());
					BigDecimal originalPrice = skuStock.getPrice();
					cartPromotionItem.setReduceAmount(originalPrice.subtract(skuStock.getPromotionPrice()));
					cartPromotionItem.setRealStock(skuStock.getStock() - skuStock.getLockStock());
					cartPromotionItem.setIntegration(promotionProduct.getGiftPoint());
					cartPromotionItem.setGrowth(promotionProduct.getGiftGrowth());
					cartPromotionItemList.add(cartPromotionItem);
				}
			} else if (promotionType == 3) {
				// 打折优惠
				int count = getCartItemCount(itemList);
				PmsProductLadder ladder = getProductLadder(count, promotionProduct.getProductLadderList());
				if (ladder != null) {
					for (OmsCartItem item : itemList) {
						CartPromotionItem cartPromotionItem = new CartPromotionItem();
						BeanUtils.copyProperties(item, cartPromotionItem);
						String message = getLadderPromotionMessage(ladder);
						cartPromotionItem.setPromotionMessage(message);
						// 商品原价-折扣金额*商品原价
						PmsSkuStock skuStock = getOriginalPrice(promotionProduct, item.getProductSkuId());
						BigDecimal originalPrice = skuStock.getPrice();
						BigDecimal reduceAmount = originalPrice.subtract(ladder.getDiscount().multiply(originalPrice));
						cartPromotionItem.setReduceAmount(reduceAmount);
						cartPromotionItem.setRealStock(skuStock.getStock() - skuStock.getLockStock());
						cartPromotionItem.setIntegration(promotionProduct.getGiftPoint());
						cartPromotionItem.setGrowth(promotionProduct.getGiftGrowth());
						cartPromotionItemList.add(cartPromotionItem);
					}
				} else {
					handleNoReduce(cartPromotionItemList, itemList, promotionProduct);
				}
			} else if (promotionType == 4) {
				// 满减
				BigDecimal totalAmount = getCartItemAmount(itemList, promotionProductList);
				PmsProductFullReduction fullReduction = getProductFullReduction(totalAmount,
						promotionProduct.getProductFullReductionList());
				if (fullReduction != null) {
					for (OmsCartItem item : itemList) {
						CartPromotionItem cartPromotionItem = new CartPromotionItem();
						BeanUtils.copyProperties(item, cartPromotionItem);
						String message = getFullReductionPromotionMessage(fullReduction);
						cartPromotionItem.setPromotionMessage(message);
						// (商品原价/总价)*满减金额
						PmsSkuStock skuStock = getOriginalPrice(promotionProduct, item.getProductSkuId());
						BigDecimal originalPrice = skuStock.getPrice();
						BigDecimal reduceAmount = originalPrice.divide(totalAmount, RoundingMode.HALF_EVEN)
								.multiply(fullReduction.getReducePrice());
						cartPromotionItem.setReduceAmount(reduceAmount);
						cartPromotionItem.setRealStock(skuStock.getStock() - skuStock.getLockStock());
						cartPromotionItem.setIntegration(promotionProduct.getGiftPoint());
						cartPromotionItem.setGrowth(promotionProduct.getGiftGrowth());
						cartPromotionItemList.add(cartPromotionItem);
					}
				} else {
					handleNoReduce(cartPromotionItemList, itemList, promotionProduct);
				}
			} else {
				// 无优惠
				handleNoReduce(cartPromotionItemList, itemList, promotionProduct);
			}
		}
		return cartPromotionItemList;
	}

	/**
	 * 查询所有商品的优惠相关信息
	 */
	private List<PromotionProduct> getPromotionProductList(List<OmsCartItem> cartItemList) {
		List<Long> productIdList = new ArrayList<>();
		for (OmsCartItem cartItem : cartItemList) {
			productIdList.add(cartItem.getProductId());
		}
		return pmsProductMapper.getPromotionProductList(productIdList);
	}

	/**
	 * 以spu为单位对购物车中商品进行分组
	 */
	private Map<Long, List<OmsCartItem>> groupCartItemBySpu(List<OmsCartItem> cartItemList) {
		Map<Long, List<OmsCartItem>> productCartMap = new TreeMap<>();
		for (OmsCartItem cartItem : cartItemList) {
			List<OmsCartItem> productCartItemList = productCartMap.get(cartItem.getProductId());
			if (productCartItemList == null) {
				productCartItemList = new ArrayList<>();
				productCartItemList.add(cartItem);
				productCartMap.put(cartItem.getProductId(), productCartItemList);
			} else {
				productCartItemList.add(cartItem);
			}
		}
		return productCartMap;
	}

	/**
	 * 获取满减促销消息
	 */
	private String getFullReductionPromotionMessage(PmsProductFullReduction fullReduction) {
		StringBuilder sb = new StringBuilder();
		sb.append("满减优惠：");
		sb.append("满");
		sb.append(fullReduction.getFullPrice());
		sb.append("元，");
		sb.append("减");
		sb.append(fullReduction.getReducePrice());
		sb.append("元");
		return sb.toString();
	}

	/**
	 * 对没满足优惠条件的商品进行处理
	 */
	private void handleNoReduce(List<CartPromotionItem> cartPromotionItemList, List<OmsCartItem> itemList,
			PromotionProduct promotionProduct) {
		for (OmsCartItem item : itemList) {
			CartPromotionItem cartPromotionItem = new CartPromotionItem();
			BeanUtils.copyProperties(item, cartPromotionItem);
			cartPromotionItem.setPromotionMessage("无优惠");
			cartPromotionItem.setReduceAmount(new BigDecimal(0));
			PmsSkuStock skuStock = getOriginalPrice(promotionProduct, item.getProductSkuId());
			cartPromotionItem.setRealStock(skuStock.getStock() - skuStock.getLockStock());
			cartPromotionItem.setIntegration(promotionProduct.getGiftPoint());
			cartPromotionItem.setGrowth(promotionProduct.getGiftGrowth());
			cartPromotionItemList.add(cartPromotionItem);
		}
	}

	private PmsProductFullReduction getProductFullReduction(BigDecimal totalAmount,
			List<PmsProductFullReduction> fullReductionList) {
		// 按条件从高到低排序
		fullReductionList.sort(new Comparator<PmsProductFullReduction>() {
			@Override
			public int compare(PmsProductFullReduction o1, PmsProductFullReduction o2) {
				return o2.getFullPrice().subtract(o1.getFullPrice()).intValue();
			}
		});
		for (PmsProductFullReduction fullReduction : fullReductionList) {
			if (totalAmount.subtract(fullReduction.getFullPrice()).intValue() >= 0) {
				return fullReduction;
			}
		}
		return null;
	}

	/**
	 * 获取打折优惠的促销信息
	 */
	private String getLadderPromotionMessage(PmsProductLadder ladder) {
		StringBuilder sb = new StringBuilder();
		sb.append("打折优惠：");
		sb.append("满");
		sb.append(ladder.getCount());
		sb.append("件，");
		sb.append("打");
		sb.append(ladder.getDiscount().multiply(new BigDecimal(10)));
		sb.append("折");
		return sb.toString();
	}

	/**
	 * 根据购买商品数量获取满足条件的打折优惠策略
	 */
	private PmsProductLadder getProductLadder(int count, List<PmsProductLadder> productLadderList) {
		// 按数量从大到小排序
		productLadderList.sort(new Comparator<PmsProductLadder>() {
			@Override
			public int compare(PmsProductLadder o1, PmsProductLadder o2) {
				return o2.getCount() - o1.getCount();
			}
		});
		for (PmsProductLadder productLadder : productLadderList) {
			if (count >= productLadder.getCount()) {
				return productLadder;
			}
		}
		return null;
	}

	/**
	 * 获取购物车中指定商品的数量
	 */
	private int getCartItemCount(List<OmsCartItem> itemList) {
		int count = 0;
		for (OmsCartItem item : itemList) {
			count += item.getQuantity();
		}
		return count;
	}

	/**
	 * 获取购物车中指定商品的总价
	 */
	private BigDecimal getCartItemAmount(List<OmsCartItem> itemList, List<PromotionProduct> promotionProductList) {
		BigDecimal amount = new BigDecimal(0);
		for (OmsCartItem item : itemList) {
			// 计算出商品原价
			PromotionProduct promotionProduct = getPromotionProductById(item.getProductId(), promotionProductList);
			PmsSkuStock skuStock = getOriginalPrice(promotionProduct, item.getProductSkuId());
			amount = amount.add(skuStock.getPrice().multiply(new BigDecimal(item.getQuantity())));
		}
		return amount;
	}

	/**
	 * 获取商品的原价
	 */
	private PmsSkuStock getOriginalPrice(PromotionProduct promotionProduct, Long productSkuId) {
		for (PmsSkuStock skuStock : promotionProduct.getSkuStockList()) {
			if (productSkuId.equals(skuStock.getId())) {
				return skuStock;
			}
		}
		return null;
	}

	/**
	 * 根据商品id获取商品的促销信息
	 */
	private PromotionProduct getPromotionProductById(Long productId, List<PromotionProduct> promotionProductList) {
		for (PromotionProduct promotionProduct : promotionProductList) {
			if (productId.equals(promotionProduct.getId())) {
				return promotionProduct;
			}
		}
		return null;
	}
}
