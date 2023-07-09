import 'package:flutter/material.dart';
import 'package:flutter_module/common/decorated/decorated_text.widget.dart';

class DefalutErrorWidget extends StatelessWidget{
  @override
  Widget build(BuildContext context) {
    return Scaffold(
        appBar: AppBar(
            toolbarHeight: 45,
            // leading: IconButton(
            //   onPressed: () {
            //     Navigator.pop(context);
            //   },
            //   icon: Image.asset(
            //     R.drawable.common_topbar_back,
            //     width: 24,
            //     height: 24,
            //   ),
            // ),
            title: Text(
              "测试测试",
              style: const TextStyle(
                // fontFamily: "PingFang SC",
                  color: Color(0xff000000),
                  fontSize: 18,
                  fontWeight: FontWeight.w700),
              // style: R.style.title.f28.bold,
            )),
        body:DecoratedText(
          ""
        ));
  }

}