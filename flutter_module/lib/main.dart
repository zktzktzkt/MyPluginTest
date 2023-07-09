import 'package:flutter/material.dart';
import 'package:flutter/services.dart';
import 'package:flutter_boost/flutter_boost.dart';
import 'package:flutter_module/app.dart';

void main() {
  CustomFlutterBinding();
  WidgetsFlutterBinding.ensureInitialized();
  SystemChrome.setSystemUIOverlayStyle(const SystemUiOverlayStyle(
    statusBarColor: Colors.transparent, //状态栏
    systemNavigationBarColor: Color(0xffffffff), //虚拟按键背景色
    systemNavigationBarDividerColor: Colors.transparent,
    systemNavigationBarIconBrightness: Brightness.light, //虚拟按键图标色
    statusBarIconBrightness: Brightness.light,
    statusBarBrightness: Brightness.light,
  ));
  runApp(App());
  // runApp(getRouter(window.defaultRouteName));
}

class CustomFlutterBinding extends WidgetsFlutterBinding with BoostFlutterBinding {

}

///接收 Android 跳转过来的启动路由参数，如果匹配上了走正常流程
///如果没匹配上，则提示 page not found
Widget getRouter(String routeName) {
  switch (routeName) {
    case "main":
      return MyApp();
    default:
      return MaterialApp(
        home: Scaffold(
          appBar: AppBar(
            title: const Text("Flutter Demo Home Page"),
          ),
          body: const Center(
            child: Text(
              "page not found",
              style: TextStyle(fontSize: 24, color: Colors.red),
            ),
          ),
        ),
      );
  }
}

class MyApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: Scaffold(
        appBar: AppBar(title: Text('GridView Example')),
        body: Column(
          mainAxisAlignment: MainAxisAlignment.start,
          mainAxisSize: MainAxisSize.min,
          children: <Widget>[
            Row(
              mainAxisAlignment: MainAxisAlignment.start,
              children: [
                ClipOval(
                    child: Image(
                  image: AssetImage("images/app_icon.jpg"),
                  width: 50,
                  height: 50,
                  fit: BoxFit.fill,
                )),
                Column(
                  children: [
                    Text(
                      "居然之家（玉泉营店）",
                      style: TextStyle(
                          fontSize: 20,
                          fontWeight: FontWeight.bold,
                          color: Color(0xFF35598C)),
                    ),
                    Row(
                      mainAxisAlignment: MainAxisAlignment.end,
                      children: List.generate(
                        2,
                        (index) => Container(
                          margin: EdgeInsets.only(right: 10),
                          decoration: BoxDecoration(
                              borderRadius: BorderRadius.circular(10),
                              color: Color(0xFF7CB2FF)),
                          child: Padding(
                              padding: EdgeInsets.symmetric(
                                  horizontal: 8, vertical: 2),
                              child: Text(
                                "免费送装上门${index}",
                                style: TextStyle(
                                    fontSize: 10, color: Color(0xFFFFFFFF)),
                              )),
                        ),
                      ),
                    )
                  ],
                )
              ],
            )
          ],
        ),
      ),
    );
  }
}
