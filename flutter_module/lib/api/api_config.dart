
class DwApiConfig {
  static const baseApiUrl = "https://gatewaysit.jrdaimao.com";
  static const ossUrl = "https://juranapp-test.oss-cn-beijing.aliyuncs.com/";
  // static const baseApiUrl="https://gatewaydev2.jrdaimao.com";
  static const configApiUrl =
      "/easyhome-app-application/common/config"; //接口测试调用
  static const searchApiUrl =
      "/easyhome-app-application/search/result"; //接口测试调用
  static const listApiUrl =
      "/easyhome-app-application/operation/otherOperationalList"; //接口测试调用
  static const homeOperationalListApiUrl =
      "/easyhome-app-application/operation/homeOperationalList"; //接口 list
  static const recommendGoodsListApiUrl =
      "/easyhome-app-application/goods/listRecommendGoods"; //接口 list
  static const personalListApiUrl =
      "/easyhome-app-application/goods/second-hand/personal/list"; //二手闲置店铺接口

  static const recommendContentListApiUrl =
      "/easyhome-app-application/operation/operContent/sort/contentList"; //接口 list
  static const orderGoodsServiceTicketDetailForOriginal =
      "/easyhome-app-application/serviceTicket/orderGoodsServiceTicketDetailForOriginal"; //居家保详情接口
  static const updatePhoneAddress =
      "/easyhome-app-application/serviceTicket/updatePhoneAddress"; //修改电话 地址
  static const insuranceServiceFinish =
      "/easyhome-app-application/serviceTicket/serviceFinish"; //确认完成
  static const insuranceCancleService =
      "/easyhome-app-application/serviceTicket/cancleService"; //撤销申请
  static const insuranceSubmitMaterials =
      "/easyhome-app-application/serviceTicket/submitMaterials"; //提交资料
  static const createOrderGoodsServiceTicket =
      "/easyhome-app-application/serviceTicket/createOrderGoodsServiceTicket"; //创建服务单
  static const getOrderGoodsInsurance =
      "/easyhome-app-application/consume/orderShops/getOrderGoodsInsurance"; //第一次进入创建服务单
  static const decorateGetOrderGoodsInsurance =
      "/easyhome-app-application/consume/orderShops/decorate/getOrderGoodsInsurance"; //第一次进入创建服务单装饰订单进入
  static const submitMaterialsDetailsForOriginal =
      "/easyhome-app-application/serviceTicket/submitMaterialsDetailsForOriginal"; //修改进入创建服务单
  static const orderGoodsServiceTicketHistoryList =
      "/easyhome-app-application/serviceTicket/orderGoodsServiceTicketHistoryList"; //历史服务单

  static const discoverOperationList =
      "/easyhome-app-application/operation/otherOperationalList"; //选购经验

  static const activityStatusApiUrl =
      "/easyhome-app-application/smartApi/homeDifficulty/checkDifficultyStatus"; //活动页详情

  static const activityClaimCouponsApiUrl =
      "/easyhome-app-application/homeDifficulty/collectHomeDifficultyCoupon"; //领取优惠券

  static const activityCustomerServiceApiUrl =
      "/easyhome-app-application/smartApi/goods/goodsForNew/customerService"; //获取联系客服id

  static const expansionGoldOrderPageListApiUrl =
      "/easyhome-app-application/individual/expansionGoldOrderPageList"; //膨胀金订单
  static const expansionGoldAccountApiUrl =
      "/easyhome-app-application/individual/getPumpGoldAccountInfo";
  static const expansionGoldMyCampaignListApiUrl =
      "/easyhome-app-application/individual/myCampaignList";
  static const expansionGoldAccountOrderPageListApiUrl =
      "/easyhome-app-application/individual/accountOrderPageList";
  static const expansionGoldContinueAccountInfoApiUrl =
      "/easyhome-app-application/individual/getPumpGoldContinueAccountInfo";
  static const expansionGoldCPumpGoldCityBannerApiUrl =
      "/easyhome-app-application/pumpGold/queryPumpGoldCityBanner";
  static const arriveShopCampaignpDetailApiUrl =
      "/easyhome-app-application/my/subscribe/campaign/detail";//到店礼 核销详情
  static const arriveShopOrderListApiUrl =
      "/easyhome-app-application/my/subscribe/gift/enroll/list";
  static const arrivalGiftRefundApiUrl =
      "/easyhome-app-application/customer/arrivalGift/refund"; // 到店礼退款

}
