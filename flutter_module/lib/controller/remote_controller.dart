import 'dart:io';

import 'package:flutter/services.dart';
import 'package:flutter_boost/flutter_boost.dart';
import 'package:flutter_module/api/api_config.dart';
import 'package:flutter_module/common/dw_constants.dart';
import 'package:flutter_module/controller/base_controller.dart';
import 'package:flutter_module/router/router_manger.dart';

class RemoteController extends BaseController {

  static const  String METHOD_CHANNEL = "flutter_daimao.flutter.io/method_channel";
  static const MethodChannel _methodChannel = MethodChannel(METHOD_CHANNEL);

  @override
  void intData() {
    _init();
  }

  _init() async {
    ///原生methodchannel方法
    _methodChannel.setMethodCallHandler(platformCallHandler);
  }

  ///获取heander
  Future<Map<String, dynamic>> getHttpHeader() async {
    Map<String, dynamic> header = {};
    if (DWRunTimeConstants.ALONE_RUN) {
      header = assembleParames();
    } else {
      Map<String, dynamic> para = {'message': '传递的参数'};
      var result = await _methodChannel.invokeMethod('getAppHeader', para);
      header = Map<String, dynamic>.from(result);
    }
    return header;
  }

  ///获取bashurl
  Future<String> getBaseUrl() async {
    var baseUrl = DwApiConfig.baseApiUrl;
    if (!DWRunTimeConstants.ALONE_RUN) {
      Map<String, dynamic> para = {'message': '传递的参数'};
      baseUrl =
          await _methodChannel.invokeMethod('getAppBashUrl', para) as String;
    }
    return baseUrl;
  }

  ///获取bashurl
  Future<String> getH5BaseUrl() async {
    var baseUrl = DwApiConfig.baseApiUrl;
    if (!DWRunTimeConstants.ALONE_RUN) {
      Map<String, dynamic> para = {'message': '传递的参数'};
      baseUrl =
          await _methodChannel.invokeMethod('getAppH5BashUrl', para) as String;
    }
    return baseUrl;
  }

  ///获取选择的本地相册
  Future<String> getInsuranceSelectPhoto(int photoListSize) async {
    var str = "";
    Map<String, dynamic> para = {'photoListSize': photoListSize};
    str = await _methodChannel.invokeMethod('insuranceSelectPhoto', para)
        as String;
    return str;
  }

  ///跳转查看大图
  Future<String> jumpLookPhotos(List<String> photos, int index) async {
    var str = "";
    Map<String, dynamic> para = {'photos': photos, 'index': index};
    str = await _methodChannel.invokeMethod('jumpLookPhotos', para) as String;
    return str;
  }

  ///跳转居家宝详情页
  Future<String> jumpInsuranceInstructions(String orderGoodsDetailsId,
      String goodsSkuId, String orderId, String? fromSource) async {
    var str = "";
    Map<String, dynamic> para = {
      'orderGoodsDetailsId': orderGoodsDetailsId,
      'goodsSkuId': goodsSkuId,
      'orderId': orderId,
      'fromSource': fromSource
    };
    str = await _methodChannel.invokeMethod('jumpInsuranceInstructions', para)
        as String;
    return str;
  }

  ///刷新居家保状态
  Future<String> refreshInsuranceStatus() async {
    var str = "";
    Map<String, dynamic> para = {};
    str = await _methodChannel.invokeMethod('refreshInsuranceStatus', para)
        as String;
    return str;
  }

  ///选购经曝光
  Future<String> sensorOperateExporsure(String id, String title) async {
    var str = "";
    Map<String, dynamic> para = {'id': id, 'title': title};
    str = await _methodChannel.invokeMethod('sensorXGJYOperateExporsure', para)
        as String;
    return str;
  }

  ///选购经曝光
  Future<String> sensorOperateClick(String id, String title) async {
    var str = "";
    Map<String, dynamic> para = {'id': id, 'title': title};
    str = await _methodChannel.invokeMethod('sensorXGJYOperateClick', para)
        as String;
    return str;
  }

  ///埋点曝光
  Future<String> sensorInfoOperateClick(Map<String, dynamic> map) async {
    var str = "";
    Map<String, dynamic> para = map;
    if (!DWRunTimeConstants.ALONE_RUN) {
      str = await _methodChannel.invokeMethod('sensorCommonOperateClick', para)
          as String;
    }

    return str;
  }

  ///获取地址
  Future<String> getAddress() async {
    var str = "";
    Map<String, dynamic> para = {};
    str = await _methodChannel.invokeMethod('selectAddress', para) as String;
    return str;
  }

  Future<String> getNewAddress() async {
    var str = "";
    Map<String, dynamic> para = {};
    str = await _methodChannel.invokeMethod('newSelectAddress', para) as String;
    return str;
  }

  Future<String> selectStoreInfo(String link) async {
    var str = "";
    Map<String, dynamic> para = {'link': link};
    str = await _methodChannel.invokeMethod('selectStoreInfo', para) as String;
    return str;
  }

  Future<String> shareAction(Map<String, dynamic> map) async {
    var str = "";
    str = await _methodChannel.invokeMethod('shareAction', map) as String;
    return str;
  }

  Future<String> contantCustomer(String customerServiceId) async {
    var str = "";
    Map<String, dynamic> para = {'customerServiceId': customerServiceId};
    str = await _methodChannel.invokeMethod('contactCustomerService', para)
        as String;
    return str;
  }

  Future<String> setStatusBarDarkTheme(int status) async {
    var str = "";
    if (Platform.isAndroid) {
      Map<String, dynamic> para = {'setStatusBarDark': status};
      str = await _methodChannel.invokeMethod('setStatusBarDarkTheme', para)
          as String;
    }
    return str;
  }

  Future<String> useCoupon(Map<String, dynamic> map) async {
    var str = "";
    str = await _methodChannel.invokeMethod('useCouponPage', map) as String;

    return str;
  }

  Future<String> searchPage(String activityId) async {
    var str = "";
    Map<String, dynamic> para = {'activityId': activityId};
    str = await _methodChannel.invokeMethod('jumpSearchPage', para) as String;

    return str;
  }

  Future<dynamic> platformCallHandler(MethodCall call) async {
    switch (call.method) {
      case 'callBackFromNative':
        {
          if (BoostNavigator.instance.pageSize() == 1) {
            _methodChannel
                .invokeMethod('callBackFromFlutter', {'message': '传递的参数'});
          } else {
            DaiMaoRouter.popCurrentWidget();
          }
        }
        break;
      case 'getBatteryLevel':
        {
          print('NativeApp    getBatteryLevel platformCallHandler 回调成功');
        }
        break;

      default:
        {
          print('NativeApp    platformCallHandler 回调成功');
        }
        break;
    }
  }

  ///获取bashurl
  Future<String> getBaseOssUrl() async {
    var baseUrl = DwApiConfig.ossUrl;
    if (!DWRunTimeConstants.ALONE_RUN) {
      Map<String, dynamic> para = {'message': '传递的参数'};
      baseUrl =
          await _methodChannel.invokeMethod('getAppOSSUrl', para) as String;
    }
    return baseUrl;
  }

  ///获取到店有礼获取核销二维码
  Future<Uint8List> getCreateQRCode(String code) async {
    var uint8List = Uint8List(1);
    Map<String, dynamic> para = {"pickUpCode" : code};
    uint8List = await _methodChannel.invokeMethod('arriveShopWriteOffQRCode', para) as Uint8List;
    return uint8List;
  }


  ///跳转高德地图
  Future<String> gotoGaodeMap(String lat, String lng) async {
    var str = "";
    Map<String, dynamic> para = {"lat":lat, "lng":lng};
    str = await _methodChannel.invokeMethod('gotoGaodeMap', para) as String;
    return str;
  }
  ///获取是否是debug包
  Future<bool> canGetHttpProxy() async {
    bool isDebug = false;
    if (DWRunTimeConstants.ALONE_RUN) {
      isDebug = true;
    } else {
      var debugStr = await _methodChannel
          .invokeMethod('canHttpProxy', {'message': '传递的参数'}) as String;
      if (debugStr == 'debug') {
        isDebug = true;
      } else {
        isDebug = false;
      }
    }

    return isDebug;
  }

  Map<String, dynamic> assembleParames() {
    Map<String, dynamic> assembleHeader = {};
    assembleHeader['Authorization'] =
        'Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiI3ODcwOTMwNjAzMTQ3MzQ1OTIiLCJpYXQiOjE2ODY3NDgxOTgsImV4cCI6MTcxODI4NDE5OH0.UeQamvbwQ_sB62N8EdDxtOSOadt05YdkiGZFYXIIB6GtulVWrVv7eXQ01AHTtChOhq0I-FemBHP0v1D25VxvQg';

    assembleHeader['screenWidth'] = '1080';
    assembleHeader['screenHeight'] = '2400';
    assembleHeader['deviceOs'] = Platform.isAndroid
        ? 'Android'
        : Platform.isIOS
            ? 'iOS'
            : '未支持的平台';
    assembleHeader['appVer'] = '1.5.0';
    assembleHeader['osModel'] = 'RMX3366';
    assembleHeader['cityId'] = '824358401244008410';
    assembleHeader['cityStationId'] = '824358401244008410';
    assembleHeader['networkType'] = 'WIFI';
    assembleHeader['deviceId'] = 'da97f4e45fda1519';
    assembleHeader['osVer'] = '12';
    assembleHeader['Content-Type'] = 'application/json; charset=UTF-8';
    return assembleHeader;
  }
}
