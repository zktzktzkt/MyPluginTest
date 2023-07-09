import 'package:flutter/material.dart';
import 'package:fluttertoast/fluttertoast.dart';

mixin ToastMixin {
  void _showToast(
    String? msg, {
    Toast toastLength = Toast.LENGTH_SHORT,
    ToastGravity gravity = ToastGravity.CENTER,
    int timeInSecForIos = 1,
  }) {
    Fluttertoast.cancel();
    Fluttertoast.showToast(
      msg: msg ?? "",
      backgroundColor: Colors.black54,
      toastLength: toastLength,
      gravity: gravity,
      timeInSecForIosWeb: timeInSecForIos,
    );
  }

  void showShortToast(String? msg) {
    _showToast(msg, toastLength: Toast.LENGTH_SHORT);
  }

  void showLongToast(String? msg) {
    _showToast(msg, toastLength: Toast.LENGTH_LONG);
  }
}
