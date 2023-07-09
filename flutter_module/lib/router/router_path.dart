//路由协议
class RouterPath {
  //指向 Native 页面
  static const PATH_TYPE_NATIVE = "native://";
  //指向 Flutter 页面
  static const PATH_TYPE_FLUTTER = "flutter://";
}

// 主页面
class Main {
  static const _MAIN_FLUTTER = "${RouterPath.PATH_TYPE_FLUTTER}/main";
  static const String PAGE_MAIN = "$_MAIN_FLUTTER/main"; //"flutter:///main/main";
}

// 首页
class Home {
  static const _HOME_FLUTTER = "${RouterPath.PATH_TYPE_FLUTTER}/home";

  static const String PAGE_HOME = "$_HOME_FLUTTER/home";
}

// 发现
class Discover {
  static const _HOME_FLUTTER = "${RouterPath.PATH_TYPE_FLUTTER}/discover";

  /// 选购经验 列表
  static const String PAGE_DISCOVER_LIST = "$_HOME_FLUTTER/discover_list";
}

// 活动
class Activity {
  static const _HOME_FLUTTER = "${RouterPath.PATH_TYPE_FLUTTER}/activity";

  /// 有困难找居然活动页
  static const String PAGE_ACTIVITY = "$_HOME_FLUTTER/page";
}

// 我的
class Mine {
  static const _MINE_FLUTTER = "${RouterPath.PATH_TYPE_FLUTTER}/mine";
  static const String PAGE_MINE = "$_MINE_FLUTTER/mine";
  static const String PAGE_ABOUT = "$_MINE_FLUTTER/about";
}

// 设置
class Setting {
  static const _MINE_FLUTTER = "${RouterPath.PATH_TYPE_FLUTTER}/setting";
  static const String PAGE_SETTING = "$_MINE_FLUTTER/setting";
  static const String PAGE_BLOC_DEMO = "$_MINE_FLUTTER/bloc_demo";
}

// 膨胀金
class ExpandGold {
  static const _EXPANDGOLD_FLUTTER = "${RouterPath.PATH_TYPE_FLUTTER}";
  static const String PAGE_MYEXPANDGOLD = "$_EXPANDGOLD_FLUTTER/expandGold";
  static const String PAGE_EXPANDGOLDList =
      "$_EXPANDGOLD_FLUTTER/expandGoldList";
  static const String PAGE_EXPANDGOLDDETAIL =
      "$_EXPANDGOLD_FLUTTER/expandGoldDetail";
}
// 预约到店礼
class ArriveShop {
  static const _ARRIVESHOP_FLUTTER = "${RouterPath.PATH_TYPE_FLUTTER}/arriveShop";
  /// 到店有礼列表页
  static const String PAGE_ARRIVESHOPLIST = "$_ARRIVESHOP_FLUTTER/arriveShopList";
  /// 到店有礼核销活动详情页
  static const String PAGE_ARRIVE_SHOP_INFO_PAGE = "$_ARRIVESHOP_FLUTTER/arriveShopInfoPage";
  /// 到店有礼支付成功页面
  static const String PAGE_ARRIVE_SHOP_PAY_PAGE = "$_ARRIVESHOP_FLUTTER/arrivePaySuccessPage";

}

// 闲置
class Idle {
  static const _IDLE_FLUTTER = "${RouterPath.PATH_TYPE_FLUTTER}/idle";

  ///闲置个人店铺
  static const String PAGE_IDLE_SHOP = "$_IDLE_FLUTTER/idleShop";
}

// 保险服务
class Insurance {
  static const _IDLE_FLUTTER = "${RouterPath.PATH_TYPE_FLUTTER}/insurance";

  ///保险申请
  static const String PAGE_INSURANCE_APPLY = "$_IDLE_FLUTTER/insuranceApply";

  ///保险申请详情
  static const String PAGE_INSURANCE_DETAIL = "$_IDLE_FLUTTER/insuranceDetail";

  ///保险历史服务单列表
  static const String PAGE_INSURANCE_HISTORY =
      "$_IDLE_FLUTTER/insuranceHistory";
}

// 测试用页面
class Test {
  static const _TEST_NATIVE = "${RouterPath.PATH_TYPE_NATIVE}/test";
  static const _TEST_FLUTTER = "${RouterPath.PATH_TYPE_FLUTTER}/test";
  static const _DO_TEST_FLUTTER = "${RouterPath.PATH_TYPE_FLUTTER}/dotest";
  static const String TEST_GOODSLIST = "$_DO_TEST_FLUTTER/pages";
  static const String TEST_GOODSLIST_RECOMMEND =
      "$_DO_TEST_FLUTTER/pages/goodslist";
  static const String PAGE_HAS_RESULT = "$_TEST_FLUTTER/has_result";
  static const String PAGE_HAS_PARAMS = "$_TEST_FLUTTER/has_params";
  static const String TEST_YOGHOME = "$_DO_TEST_FLUTTER/home"; //yog组件库demo主页
  // 带回调的 native 页面
  static const PAGE_NATIVE_HAS_RESULT = "$_TEST_NATIVE/has_result";
  //轮播图实例
  static const String TEST_SWIPER_BANNER_PAGE = "$_TEST_FLUTTER/testBanner";
  //多语言设置类
  static const String TEST_LANGUAGE_PAGE = "$_TEST_FLUTTER/languate_page";
}
