import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter_boost/flutter_boost.dart';
import 'package:flutter_module/common/alias.dart';
import 'package:flutter_module/common/dw_constants.dart';
import 'package:flutter_module/defalut_error_widget.dart';
import 'package:flutter_module/i18n/generated/l10n.dart';
import 'package:flutter_module/router/router_path.dart';
import 'package:flutter_module/ui/widget/has_params_widget.dart';

/**
 * 路由配置类，在此配置flutter页面索引
 */
class DaiMaoRouter {

  /**
   * 根据路由跳转
   */
  static Route<dynamic>? generateRoute(RouteSettings settings, String? uniqueId) {

    final path = settings.name!;
    //就是原生FlutterBoostRouteOptions的arguments参数
    final arguments = settings.arguments as Map<String, dynamic>? ?? {};

    setIosLanguageLocal(arguments);

    switch (path) {
      case '/':
      case Main.PAGE_MAIN:
        return CupertinoPageRoute(
            settings: settings,
            builder: (_) {
              if (DWRunTimeConstants.ALONE_RUN) {
                return DefalutErrorWidget();
                // return MainWidget();
              } else {
                return DefalutErrorWidget();
              }
            });
      case Test.PAGE_HAS_PARAMS:
        return CupertinoPageRoute(
            settings: settings,
            builder: (_) {
              Map<String, dynamic> map = settings.arguments as Map<String, dynamic>;
              return HasParamsWidget(map);
            });
      default:
        return null;
    }
  }

  ///适配ios国际化
  /// "zh_CN" = 简体中文
  /// "zh_MO" = 澳门 繁体中文
  /// "en_US" = 英文
  static void setIosLanguageLocal(Map<String, dynamic> arguments) {
    try {
      if (arguments.isNotEmpty) {
        String languageStr = arguments['language'] as String;
        if (languageStr.isNotEmpty) {
          var language = languageStr.split("_");
          if (language.length > 1) {
            S.load(Locale(language[0], language[1]));
            print("language  = $language");
          }
        }
      }
      // ignore: empty_catches
    } on Error catch (e) {}
  }

  static jumptoFlutter(
      String path, BuildContext context, ContextValueChanged callback) {
    BoostNavigator.instance
        .push(path)
        .then((value) => {callback(context, value)});
  }
  static jumptoFlutterWithArguments(
      String path, Map<String, dynamic> arguments, BuildContext context, ContextValueChanged callback) {
    BoostNavigator.instance
        .push(path, arguments: arguments)
        .then((value) => {callback(context, value)});
  }

  static popCurrentWidget() async {
    await BoostNavigator.instance.pop();
  }

  static popCurrentWidgetWithParams(dynamic result) {
    BoostNavigator.instance.pop(result);
  }
}
