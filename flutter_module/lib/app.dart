import 'package:flutter/material.dart';
import 'package:flutter_boost/flutter_boost.dart';
import 'package:flutter_easyloading/flutter_easyloading.dart';
import 'package:flutter_localizations/flutter_localizations.dart';
import 'package:flutter_module/controller/remote_controller.dart';
import 'package:flutter_module/i18n/generated/l10n.dart';
import 'package:flutter_module/resource/r.dart';
import 'package:flutter_module/router/router_manger.dart';
import 'package:flutter_module/utils/log_utils.dart';
import 'package:flutter_screenutil/flutter_screenutil.dart';
import 'package:get/get.dart';

class App extends StatefulWidget {
  @override
  createState() => _AppPageSate();
}

class _AppPageSate extends State<App> {
  @override
  void initState() {
    super.initState();
    //主题默认初始化
    // YogInitializer.register(allThemeConfig: DwGeneralConfigUtils.defaultAllConfig);
    if (isInDebugMode) {
      LogUtil.init(isDebug: true);
    }
  }

  @override
  Widget build(BuildContext context) {
    Get.put(RemoteController());
    return FlutterBoostApp(routeFactory, appBuilder: appBuilder);
  }

  /**
   * 初始化跳转路径以及页面
   */
  Route<dynamic>? routeFactory(RouteSettings settings, String? uniqueId) {
    return DaiMaoRouter.generateRoute(settings, uniqueId);
  }

  Widget appBuilder(Widget home) {
    return MaterialApp(
      home: home,
      debugShowCheckedModeBanner: false,
      builder: (BuildContext context, Widget? child) {
        ScreenUtil.init(context, designSize: const Size(750, 1624));
        return FlutterEasyLoading(
          child: home,
        );
      },
      localizationsDelegates: const [
        S.delegate,
        GlobalCupertinoLocalizations.delegate,
        GlobalMaterialLocalizations.delegate,
        GlobalWidgetsLocalizations.delegate
      ],
      supportedLocales: S.delegate.supportedLocales,
      theme: ThemeData(
        textSelectionTheme: TextSelectionThemeData(
          cursorColor: R.color.primary,
        ),
        bottomSheetTheme: const BottomSheetThemeData(
          backgroundColor: Colors.white,
          shape: RoundedRectangleBorder(
            borderRadius: BorderRadius.vertical(top: Radius.circular(60)),
          ),
        ),
        scaffoldBackgroundColor: R.color.page_bg,
        primaryColor: Colors.white,
        primaryColorLight: Colors.white,
        primaryColorDark: Colors.white,
        dialogTheme: DialogTheme(
          shape: RoundedRectangleBorder(
            borderRadius: BorderRadius.circular(16),
          ),
        ),
        fontFamily: 'Noto Sans',
        splashColor: R.color.primary.withOpacity(.1),
        popupMenuTheme: PopupMenuThemeData(
          color: Colors.white,
          shape: RoundedRectangleBorder(
            borderRadius: BorderRadius.circular(16),
          ),
        ),
        appBarTheme: const AppBarTheme(
          centerTitle: true,
          elevation: 0,
          backgroundColor: Colors.white,
          // systemOverlayStyle: SystemUiOverlayStyle(
          //     statusBarColor: Colors.black,
          //     statusBarBrightness: Brightness.light)
        ),
        checkboxTheme: CheckboxThemeData(
          fillColor: MaterialStateProperty.all(R.color.primary),
          checkColor: MaterialStateProperty.all(Colors.white),
          shape: RoundedRectangleBorder(
            side: BorderSide(color: R.color.primary, width: 2),
            borderRadius: BorderRadius.circular(90),
          ),
        ),
        buttonTheme: const ButtonThemeData(minWidth: 56, height: 36),
      ),
    );
  }

  void onRoutePushed(String pageName, String uniqueId, Map params, Route route,
      Future future) {
    debugPrint("onRoutePushed pageName:$pageName,uniqueId:$uniqueId");
  }

  bool get isInDebugMode {
    bool inDebugMode = false;
    assert(inDebugMode = true); //如果debug模式下会触发赋值
    return inDebugMode;
  }
}
