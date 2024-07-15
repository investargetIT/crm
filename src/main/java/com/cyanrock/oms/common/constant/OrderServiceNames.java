package com.cyanrock.oms.common.constant;

/**
 * 订单服务列表
 *
 * @author JonyFu
 *
 */
public interface OrderServiceNames {
    /**
     * 挑仓
     */
    String WARECHOOSE = "/omsapi/order/wareChoose";
    /**
     * 设置商品信息
     */
    String SETITEMINFO = "/omsapi/order/setItemInfo";

    /**
     * 初始化订单同步数据
     */
    String OMSNOTIFY_SYNCALLDATA = "/omsnotify/api/syncAllData";
    /**
     * oms2.0 统一下单
     */
    String ORDER_SAVE = "/omsapi/order/createOrder";

    /**
     * 2.0订单同步数据初始化
     */
    String OMSNOTIFY_API_INITSYNCDATA = "/omsnotify/api/initSyncData";

    /**
     * 获取2.0订单相关的数据
     */
    String OMSNOTIFY_API_GETORDERDATA = "/omsnotify/api/getOrderData";
    /**
     * 获取订单收货地址相关的数据
     */
    String OMSNOTIFY_API_GETORDERADDRDATA = "/omsnotify/api/getOrderAddrData";
    /**
     * api预订单确认或者取消
     */
    String OMSDATA_ORDER_BOOKINGORDER = "/omsapi/order/bookingOrder";
    /**
     * 订单是否完结
     */
    String OMSDATA_ORDER_ORDERCOMPLETED = "/omsapi/order/orderCompleted";
    /**
     * oms2.0 取消订单申请
     */
    String CANCEL_ORDER = "/omsapi/orderUpdate/cancelOrder";
    /**
     * 企业站取消订单申请
     */
    String CANCEL_ORDER_BY_FRONT = "/omsapi/orderUpdate/cancelOrderByFront";
    /**
     * 查询订单修改详情
     */
    String GET_ORDER_UPDATE = "/omsapi/orderUpdate/getOrderUpdateByFront/%s";
    /**
     * oms2.0修改订单特殊编码
     */
    String UPDATE_ORDER_SPECIAL_CODE = "/omsapi/order/updateSpecialCode";
    /**
     * oms2.0修改订单客户采购编号
     */
    String UPDATE_ORDER_PURCHAS_NO = "/omsapi/order/updatePurchaseNo";
    /**
     * oms2.0查询取消申请状态
     */
    String QUERY_REQUEST_STATUS = "/omsapi/orderUpdate/queryRequestStatus";
    /**
     * oms2.0查询订单状态
     */
    String QUERY_ORDER_STATUS = "/omsapi/order/queryOrderStatus";

    /**
     * oms2.0财务拒绝
     */
    String FINANCE_REFUSE = "/omsapi/finance/refuse/";

    /**
     * 妥投
     */
    String DELIVERY_NOTICE = "/omsapi/order/deliveryNotice";

    /**
     * CRM单笔订单信用审核支持接口
     */
    String CREDIT_REVIEW_INFO = "/omsapi/order/getCreditReviewInfo";

    /**
     * 同步sap成功-去CRM扣减信用额度
     */
    String OCCUPY_AVAL_CREDIT = "/omsapi/order/occupyAvalCredit";
    /**
     * 根据客户号获取对应下单编号
     */
    String QUERY_ORDER_NO = "/omsapi/order/queryOrderNo";
    /**
     * 根据订单号和起止时间获取booking和billing信息
     */
    String GET_BOOKING_AND_BILLING = "/reportdata/orderbooking/getBookingAndBilling";
    /**
     * 查询发货单信息-分页
     */
    String QUERY_OBD_INFO = "/omsapi/sap/queryObdInfo";
    /**
     * 通过obd查询物流信息
     */
    String GET_LOGISTICS ="/omsapi/tms/getLogistics";
    /**
     * 更新付款信息ec
     */
    String UPDATE_PAY_ORDERINFO ="/omsapi/order/updatePayOrderInfo";
    /**
     * 支付超时-powerjob
     */
    String AUTOMATICCANCELLATION_JOB ="/schedule/salesorder/automaticCancellation";
    /**
     * 支付超时 -双重补偿，确保最终一致性
     */
    String RECANCELLATION = "/omsnotify/salesorder/reCancellation";

    /**
     * 物料管理查询oms订单
     */
    String GET_ORDER_INFO_BY_MATERIAL ="/omsnotify/salesorder/getOrderInfoByMaterial";
    /**
     * 更新订单联系人信息
     */
    String UPDATE_ORDER_CONTACT_BY_WEB_SITE = "/omsnotify/salesorder/updateOrderContact";
    /**
     * 获取客户下某人的gps订单信息
     */
    String GET_ORDER_CONTACT = "/omsnotify/salesorder/getOrderContact";
    /**
     * 更新订单信息（临时）
     */
    String UPDATE_ORDER_INFO_BY_WEB_SITE = "/omsnotify/salesorder/updateOrderInfo";

    /**
     * 财务审核订单周期性调用CRM信用额度接口自动放单
     */
    String AUTOMATIC_RELEASE_ORDER = "/omsapi/finance/automaticReleaseOrder";

    /**
     * 订单同步失败时发邮件
     */
    String SYNC_ORDER_CALLBACK_SEND_EMAIL = "/omsapi/sap/syncOrderCallBackSendEmail/%s";

    /**
     * API 发货单整单签收状态同步
     */
    String OBD_ITEM_SIGNATURE = "/omsapi/sap/obdItemSignatureByApi";



    /**
     * 企业站报价单转订单价格调整计算
     */
    String CALCULATION_AMOUNT = "/omsapi/order/calculationAmountByEnterprise";
}
