package com.csl.plus.oms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.csl.plus.oms.entity.OmsOrder;
import com.csl.plus.oms.entity.OmsOrderOperateHistory;
import com.csl.plus.oms.mapper.OmsOrderMapper;
import com.csl.plus.oms.mapper.OmsOrderOperateHistoryMapper;
import com.csl.plus.oms.service.IOmsOrderOperateHistoryService;
import com.csl.plus.oms.service.IOmsOrderService;
import com.csl.plus.oms.vo.OmsMoneyInfoParam;
import com.csl.plus.oms.vo.OmsOrderDeliveryParam;
import com.csl.plus.oms.vo.OmsReceiverInfoParam;
import com.csl.plus.sys.entity.SysUser;
import com.csl.plus.sys.service.ISysUserService;
import com.csl.plus.util.UserUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 订单表 服务实现类
 * </p>
 *
 * @since 2019-04-17
 */
@Service
public class OmsOrderServiceImpl extends ServiceImpl<OmsOrderMapper, OmsOrder> implements IOmsOrderService {

    @Resource
    private OmsOrderMapper orderMapper;

    @Resource
    private IOmsOrderOperateHistoryService orderOperateHistoryDao;
    @Resource
    private OmsOrderOperateHistoryMapper orderOperateHistoryMapper;
    @Resource
    private ISysUserService sysUserService;

    @Override
    public int delivery(List<OmsOrderDeliveryParam> deliveryParamList) {
        //批量发货
        int count = orderMapper.delivery(deliveryParamList);
        //添加操作记录
        List<OmsOrderOperateHistory> operateHistoryList = deliveryParamList.stream()
                .map(omsOrderDeliveryParam -> {
                    OmsOrderOperateHistory history = new OmsOrderOperateHistory();
                    history.setOrderId(omsOrderDeliveryParam.getOrderId());
                    history.setCreateTime(new Date());
                    history.setOperateMan("后台管理员");
                    history.setOrderStatus(2);
                    history.setNote("完成发货");
                    return history;
                }).collect(Collectors.toList());
        orderOperateHistoryDao.saveBatch(operateHistoryList);
        return count;
    }

    @Override
    public int close(List<Long> ids, String note) {
        OmsOrder record = new OmsOrder();
        record.setStatus(4);
        int count = orderMapper.update(record, new QueryWrapper<OmsOrder>().eq("delete_status", 0).in("id", ids));
        List<OmsOrderOperateHistory> historyList = ids.stream().map(orderId -> {
            OmsOrderOperateHistory history = new OmsOrderOperateHistory();
            history.setOrderId(orderId);
            history.setCreateTime(new Date());
            history.setOperateMan("后台管理员");
            history.setOrderStatus(4);
            history.setNote("订单关闭:" + note);
            return history;
        }).collect(Collectors.toList());
        orderOperateHistoryDao.saveBatch(historyList);
        return count;
    }


    @Override
    public int updateReceiverInfo(OmsReceiverInfoParam receiverInfoParam) {
        OmsOrder order = new OmsOrder();
        order.setId(receiverInfoParam.getOrderId());
//        order.setReceiverName(receiverInfoParam.getReceiverName());
//        order.setReceiverPhone(receiverInfoParam.getReceiverPhone());
//        order.setReceiverPostCode(receiverInfoParam.getReceiverPostCode());
//        order.setReceiverDetailAddress(receiverInfoParam.getReceiverDetailAddress());
//        order.setReceiverProvince(receiverInfoParam.getReceiverProvince());
//        order.setReceiverCity(receiverInfoParam.getReceiverCity());
//        order.setReceiverRegion(receiverInfoParam.getReceiverRegion());
        order.setModifyTime(new Date());
        int count = orderMapper.updateById(order);
        //插入操作记录
        OmsOrderOperateHistory history = new OmsOrderOperateHistory();
        history.setOrderId(receiverInfoParam.getOrderId());
        history.setCreateTime(new Date());
        history.setOperateMan("后台管理员");
        history.setOrderStatus(receiverInfoParam.getStatus());
        history.setNote("修改收货人信息");
        orderOperateHistoryMapper.insert(history);
        return count;
    }

    @Override
    public int updateMoneyInfo(OmsMoneyInfoParam moneyInfoParam) {
        OmsOrder order = new OmsOrder();
        order.setId(moneyInfoParam.getOrderId());
        order.setDiscountAmount(moneyInfoParam.getDiscountAmount());
        order.setModifyTime(new Date());
        int count = orderMapper.updateById(order);
        //插入操作记录
        OmsOrderOperateHistory history = new OmsOrderOperateHistory();
        history.setOrderId(moneyInfoParam.getOrderId());
        history.setCreateTime(new Date());
        history.setOperateMan("后台管理员");
        history.setOrderStatus(moneyInfoParam.getStatus());
        history.setNote("修改费用信息");
        orderOperateHistoryMapper.insert(history);
        return count;
    }

    @Override
    public int updateNote(Long id, String note, Integer status) {
        OmsOrder order = new OmsOrder();
        order.setId(id);
        order.setNote(note);
        order.setModifyTime(new Date());
        int count = orderMapper.updateById(order);
        OmsOrderOperateHistory history = new OmsOrderOperateHistory();
        history.setOrderId(id);
        history.setCreateTime(new Date());
        history.setOperateMan("后台管理员");
        history.setOrderStatus(status);
        history.setNote("修改备注信息：" + note);
        orderOperateHistoryMapper.insert(history);
        return count;
    }

    @Override
    public int updateAssignment(Long id, Long pwid, String note) {

        SysUser sysUser = sysUserService.getById(pwid);
        OmsOrder order = new OmsOrder();
        order.setId(id);
        order.setRecid(sysUser.getUsername());
        order.setModifyTime(new Date());
        int count = orderMapper.updateById(order);
        OmsOrderOperateHistory history = new OmsOrderOperateHistory();
        history.setOrderId(id);
        history.setCreateTime(new Date());
        history.setOperateMan(UserUtils.getCurrentMember().getUsername());
//        history.setOrderStatus(status);
        history.setNote("派单成功：：" + note);
        orderOperateHistoryMapper.insert(history);
        return count;
    }
}
