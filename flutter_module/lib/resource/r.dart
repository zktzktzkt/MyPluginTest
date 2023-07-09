import 'package:flutter/material.dart';
import 'package:flutter/services.dart';
import 'package:flutter_screenutil/flutter_screenutil.dart';
part 'colors.dart';
part 'styles.dart';
part 'strings.dart';
part 'drawables.dart';

class R {
  R._();

  static const color = _ColorReference();
  static const style = _StyleReference();
  static const drawable = _DrawableReference();

  // static S string(BuildContext context) => S.of(context);
}
