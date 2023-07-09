package com.asm.demo.flutter;

import android.content.Context;

import com.idlefish.flutterboost.FlutterBoost;
import com.idlefish.flutterboost.FlutterBoostRouteOptions;
import com.idlefish.flutterboost.containers.FlutterActivityLaunchConfigs;

import java.util.HashMap;
import java.util.Map;

/**
 * 跳转flutter管理
 */
public class FlutterJumpManager {

    // for onActivityResult
//    public static final String ACTIVITY_RESULT_KEY = FlutterActivityLaunchConfigs.ACTIVITY_RESULT_KEY;
//    private static final String language = "language";
//    private static long clickTimeMillis = 0;

    public static void jumpMain(Context activity) {
        Map<String, Object> params = new HashMap();
        FlutterBoost.instance().open(new FlutterBoostRouteOptions.Builder()
//                .pageName("flutter:///main/main")
                .pageName("flutter:///test/has_params")
                .arguments(params)
                .build());
    }


    /**
     * 启动flutter页面
     * @param activity
     * @param params
     * @param url
     */
    /*private static void startActivity(Activity activity, HashMap<String, Object> params, String url){
        if (StringUtils.isEmpty(url)) {
            return;
        }
        HashMap<String, Object> hashMapParams = new HashMap<>();
        String appLanguageType = DWLanguageUtils.getAppLanguageType(activity);
        hashMapParams.put(language, appLanguageType);

        if (params != null && !params.isEmpty()) {
            Iterator<String> iterator = params.keySet().iterator();
            while (iterator.hasNext()){
                String nextKey = iterator.next();
                hashMapParams.put(nextKey, params.get(nextKey));
            }
        }

        Intent intent = new DaiMaoFlutterActivity.CachedEngineIntentBuilder(DaiMaoFlutterActivity.class)
                .destroyEngineWithActivity(false)
                .url(url)
                .urlParams(hashMapParams)
                .build(activity);
        activity.startActivity(intent);
    }
    *//**
     * 跳转居家保详情
     * @param serviceTicketId 服务单id
     * @param fromSource 1：居家宝商品2：装饰订单商品
     *//*
    public static void jumpInsuranceDetail(String serviceTicketId,String fromSource, Context context) {
        if (null != serviceTicketId && !TextUtils.isEmpty(serviceTicketId)) {
            HashMap<String, Object> params = new HashMap<>();
            String appLanguageType = DWLanguageUtils.getAppLanguageType(context);
            params.put(language, appLanguageType);
            params.put("serviceTicketId", serviceTicketId);
            params.put("fromSource", fromSource);
            FlutterBoostRouteOptions options = new FlutterBoostRouteOptions.Builder()
                    .pageName("flutter:///insurance/insuranceDetail")
                    .arguments(params)
                    .requestCode(1111)
                    .build();
            FlutterBoost.instance().open(options);
        }
    }

    *//**
     * 跳转居家保申请
     * @param activity
     * @param orderGoodsDetailsId 订单id
     * @param fromSource 1：居家宝商品2：装饰订单商品
     *//*
    public static void jumpInsuranceApply(Activity activity,String orderGoodsDetailsId,String fromSource) {
        // 1. 打开Flutter页面，等待返回结果
        HashMap<String, Object> params = new HashMap<>();
        String appLanguageType = DWLanguageUtils.getAppLanguageType(activity);
        params.put(language, appLanguageType);
        params.put("orderGoodsDetailsId", orderGoodsDetailsId);
        params.put("fromSource", fromSource);
        Intent intent = new DaiMaoFlutterActivity.CachedEngineIntentBuilder(DaiMaoFlutterActivity.class)
                .destroyEngineWithActivity(false)
                .url("flutter:///insurance/insuranceApply")
                .urlParams(params)
                .build(activity);
        activity.startActivityForResult(intent, 1009);
    }

    //跳转关于
    public static void jumpMineAbout(HashMap<String, Object> params, Activity activity) {
        startActivity(activity, params, "flutter:///mine/about");
    }

    //选购经验列表
    public static void jumpDiscoverMoreList(Context activity) {
        Map params = new HashMap<String, Object>();
        String appLanguageType = DWLanguageUtils.getAppLanguageType(activity);
        params.put(language, appLanguageType);
        params.put("cityStationId", MMKV.defaultMMKV().decodeString("cityStationId", "824358401244008410"));
        params.put("memberId", AppUserConfig.getInstance().getUserInfo().getMemberId());
        FlutterBoostRouteOptions options = new FlutterBoostRouteOptions.Builder()
                .pageName("flutter:///discover/discover_list")
                .arguments(params)
                .build();
        FlutterBoost.instance().open(options);
    }

    *//**
     * 跳转有困难找居然之家
     *//*
    public static void jumpActivityFindJRPage(Activity activity) {
        HashMap<String, Object> params = new HashMap<>();
        UserInfo userInfo = AppUserConfig.getInstance().getUserInfo();
        if (userInfo != null && activity != null && !StringUtils.isEmpty(userInfo.getNickname()) && !StringUtils.isEmpty(userInfo.getPhone())) {
            String appLanguageType = DWLanguageUtils.getAppLanguageType(activity);
            params.put(language, appLanguageType);
            params.put("name", userInfo.getNickname());
            params.put("phone", userInfo.getPhone());
            Intent intent = new DaiMaoFlutterActivity.CachedEngineIntentBuilder(DaiMaoFlutterActivity.class)
                    .destroyEngineWithActivity(false)
                    .url("flutter:///activity/page")
                    .urlParams(params)
                    .build(activity);
            activity.startActivity(intent);
        }

    }

    *//**
     * 跳转我的膨胀金
     *//*
    public static void jumpMyExpandedGold(Activity activity) {
        UserInfo userInfo = AppUserConfig.getInstance().getUserInfo();
        if (userInfo != null && activity != null && !StringUtils.isEmpty(userInfo.getNickname()) && !StringUtils.isEmpty(userInfo.getPhone())) {
            HashMap<String, Object> params = new HashMap<>();
            String appLanguageType = DWLanguageUtils.getAppLanguageType(activity);
            params.put(language, appLanguageType);
            Intent intent = new DaiMaoFlutterActivity.CachedEngineIntentBuilder(DaiMaoFlutterActivity.class)
                    .destroyEngineWithActivity(false)
                    .url("flutter:///expandGold")
                    .urlParams(params)
                    .build(activity);
            activity.startActivity(intent);
        }
    }

    *//**
     * 跳转到店礼列表
     *//*
    public static void jumpArriveShopListPage(Activity activity) {

        if (System.currentTimeMillis() - clickTimeMillis > 500) {
            HashMap<String, Object> params = new HashMap<>();
            UserInfo userInfo = AppUserConfig.getInstance().getUserInfo();
            if (userInfo != null && activity != null && !StringUtils.isEmpty(userInfo.getNickname()) && !StringUtils.isEmpty(userInfo.getPhone())) {
                startActivity(activity, params, "flutter:///arriveShop/arriveShopList");
                clickTimeMillis = System.currentTimeMillis();
            }
        }
    }

    *//**
     * 跳转到店有礼核销详情页页面
     * @param appointId
     *//*
    public static void jumpArriveShopWriteOffDetailPage(Activity activity, String appointId) {
        if (activity != null && !StringUtils.isEmpty(appointId)) {
            UserInfo userInfo = AppUserConfig.getInstance().getUserInfo();
            if (userInfo != null && !StringUtils.isEmpty(userInfo.getNickname())) {
                HashMap<String, Object> params = new HashMap<>();
                params.put("appointId", appointId);
                startActivity(activity, params, "flutter:///arriveShop/arriveShopInfoPage");
            }
        }
    }
    *//**
     * 跳转到店礼详情
     * @param appointId
     *//*
    public static void jumpArriveShopPaySuccessPage(Activity activity, String appointId) {
        if (activity != null && !StringUtils.isEmpty(appointId)) {
            UserInfo userInfo = AppUserConfig.getInstance().getUserInfo();
            if (userInfo != null && !StringUtils.isEmpty(userInfo.getNickname())) {
                HashMap<String, Object> params = new HashMap<>();
                params.put("appointId", appointId);
                startActivity(activity, params, "flutter:///arriveShop/arrivePaySuccessPage");
            }
        }
    }*/
}
