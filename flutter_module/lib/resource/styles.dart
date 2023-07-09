// ignore_for_file: non_constant_identifier_names
part of 'r.dart';

class _StyleReference {
  const _StyleReference();

  final _text = const TextStyle(fontSize: 16);

  final box = const BoxDecoration();

  final strut = const StrutStyle(height: 1.4);

  final strut13 = const StrutStyle(height: 1.7);

  //region 颜色
  TextStyle get primary {
    return _text.copyWith(color: R.color.primary);
  }

  TextStyle get secondary {
    return _text.copyWith(color: R.color.secondary);
  }

  TextStyle get white {
    return _text.copyWith(color: Colors.white);
  }

  TextStyle get grey {
    return _text.copyWith(color: Colors.grey);
  }

  TextStyle get text1 {
    return _text.copyWith(color: R.color.text1);
  }

  TextStyle get text2 {
    return _text.copyWith(color: R.color.text2);
  }

  TextStyle get text3 {
    return _text.copyWith(color: R.color.text3);
  }

  TextStyle get text4 {
    return _text.copyWith(color: R.color.text4);
  }

  TextStyle get transparent {
    return _text.copyWith(color: Colors.transparent);
  }

  TextStyle get blue {
    return _text.copyWith(color: Colors.blue);
  }

  TextStyle get red1 {
    return _text.copyWith(color: Colors.red);
  }

  TextStyle get title {
    return _text.copyWith(color: R.color.text4);
  }
  TextStyle get secondtitle {
    return _text.copyWith(color: R.color.color_666666);
  }



  ShapeBorder get top_rc {
    return RoundedRectangleBorder(
      borderRadius: BorderRadius.vertical(top: Radius.circular(16)),
    );
  }

  /// 光晕效果
  BoxDecoration get shadow => BoxDecoration(
        color: Colors.white,
        boxShadow: [
          BoxShadow(
            blurRadius: 16,
            spreadRadius: 4,
            color: Colors.grey[200]!,
            offset: Offset(0, 0),
          ),
        ],
      );

  /// 光晕效果
  BoxDecoration get shadow_primary => BoxDecoration(
        color: R.color.primary,
        boxShadow: [
          BoxShadow(
            blurRadius: 10,
            spreadRadius: 0,
            color: R.color.primary.withOpacity(0.8),
            offset: Offset(0, 0),
          ),
        ],
      );

  /// 登录输入背景框效果
  BoxDecoration get loginInputBg => BoxDecoration(
    color: Colors.white,
    border: Border.all(color: R.color.primary),
    borderRadius: BorderRadius.circular(16),
  );

  InputDecoration input([String? hint]) => InputDecoration(
        hintText: hint,
        hintStyle: R.style.text2,
        enabledBorder: UnderlineInputBorder(
          borderSide: BorderSide(color: R.color.divider),
        ),
        border: UnderlineInputBorder(
          borderSide: BorderSide(color: R.color.divider),
        ),
        counterStyle: R.style.text2.f26,
      );

  InputDecoration input_rc([
    String? hint,
    Color? fillColor,
    double borderRadius = 18,
  ]) {
    final border = OutlineInputBorder(
      borderSide: BorderSide(color: R.color.primary),
      borderRadius: BorderRadius.circular(borderRadius),
    );
    return InputDecoration(
      hintText: hint,
      hintStyle: R.style.text2.f26,
      filled: true,
      border: border,
      disabledBorder: border,
      enabledBorder: border,
      errorBorder: border,
      focusedBorder: border,
      focusedErrorBorder: border,
      // contentPadding: const EdgeInsets.all(kSpace16),
      fillColor: fillColor ?? R.color.secondary,
      counterStyle: R.style.text2.f26,
    );
  }

  InputDecoration input_power([
    String? hint,
    Color? fillColor,
    double borderRadius = 4,
  ]) {
    final border = OutlineInputBorder(
      borderSide: BorderSide(color: R.color.power_input_bg),
      borderRadius: BorderRadius.circular(borderRadius),
    );
    return InputDecoration(
      hintText: hint,
      hintStyle: R.style.text3.f32,
      filled: true,
      border: border,
      disabledBorder: border,
      enabledBorder: border,
      errorBorder: border,
      focusedBorder: border,
      focusedErrorBorder: border,
      // contentPadding: const EdgeInsets.all(kSpace16),
      fillColor: fillColor ?? R.color.power_input_bg,
      counterStyle: R.style.text1.f32,
    );
  }

  InputDecoration input_search([String? hint, TextStyle? hintStyle]) =>
      InputDecoration(
        hintText: hint,
        hintStyle: hintStyle ?? R.style.text2.f28,
        isCollapsed: true,
        contentPadding: const EdgeInsets.symmetric(vertical: 12).r,
        border: InputBorder.none,
        disabledBorder: InputBorder.none,
        enabledBorder: InputBorder.none,
        errorBorder: InputBorder.none,
        focusedBorder: InputBorder.none,
        focusedErrorBorder: InputBorder.none,
        counterStyle: R.style.text1.f28,
      );

  /// 通用横线
  Container get commonLine => Container(
    height: 0.5,
    color: R.color.color_line,
  );
  // decoration: BoxDecoration(
  // color: R.color.power_input_bg,
  // borderRadius: BorderRadius.all(Radius.circular(4))
  // )
  InputDecoration input_none([String? hint, TextStyle? hintStyle]) =>
      InputDecoration(
        hintText: hint,
        hintStyle: hintStyle ?? R.style.text2.f28,
        contentPadding: const EdgeInsets.symmetric(vertical: 10),
        border: InputBorder.none,
        disabledBorder: InputBorder.none,
        enabledBorder: InputBorder.none,
        errorBorder: InputBorder.none,
        focusedBorder: InputBorder.none,
        focusedErrorBorder: InputBorder.none,
        counterStyle: R.style.text3.f28,
      );

  BorderRadius diagonal([double value = 8]) {
    return BorderRadius.only(
      topLeft: Radius.circular(value),
      bottomRight: Radius.circular(value),
    );
  }


  List<TextInputFormatter> get max_length_formatter {
    return [
      LengthLimitingTextInputFormatter(140),
    ];
  }
}

extension TextStyleX on TextStyle {
  //region 颜色
  TextStyle get primary {
    return copyWith(color: R.color.primary);
  }

  TextStyle get white {
    return copyWith(color: Colors.white);
  }

  TextStyle get secondary {
    return copyWith(color: R.color.secondary);
  }

  TextStyle get grey {
    return copyWith(color: Colors.grey);
  }

  TextStyle get text1 {
    return copyWith(color: R.color.text1);
  }

  TextStyle get text2 {
    return copyWith(color: R.color.text2);
  }

  TextStyle get text4 {
    return copyWith(color: R.color.text2);
  }

  TextStyle opacity(double opacity) {
    return copyWith(color: color!.withOpacity(opacity));
  }

  //endregion

  //region 字重
  TextStyle get semi_bold {
    return copyWith(fontWeight: FontWeight.w500);
  }

  TextStyle get bold {
    return copyWith(fontWeight: FontWeight.w600);
  }

  TextStyle get extra_bold {
    return copyWith(fontWeight: FontWeight.w800);
  }

  //endregion

  //region 大小
  TextStyle get f16 {
    return copyWith(fontSize: 16.sp);
  }

  TextStyle get f18 {
    return copyWith(fontSize: 18.sp);
  }

  TextStyle get f20 {
    return copyWith(fontSize: 20.sp);
  }

  TextStyle get f22 {
    return copyWith(fontSize: 22.sp);
  }

  TextStyle get f24 {
    return copyWith(fontSize: 24.sp);
  }

  TextStyle get f26 {
    return copyWith(fontSize: 26.sp);
  }

  TextStyle get f28 {
    return copyWith(fontSize: 28.sp);
  }

  TextStyle get f30 {
    return copyWith(fontSize: 30.sp);
  }

  TextStyle get f32 {
    return copyWith(fontSize: 32.sp);
  }

  TextStyle get f34 {
    return copyWith(fontSize: 34.sp);
  }

  TextStyle get f36 {
    return copyWith(fontSize: 36.sp);
  }

  TextStyle get f38 {
    return copyWith(fontSize: 38.sp);
  }

  TextStyle get f40 {
    return copyWith(fontSize: 40.sp);
  }

  TextStyle get f42 {
    return copyWith(fontSize: 42.sp);
  }

  TextStyle get f48 {
    return copyWith(fontSize: 48.sp);
  }

  TextStyle get f50 {
    return copyWith(fontSize: 50.sp);
  }

  TextStyle get f52 {
    return copyWith(fontSize: 52.sp);
  }

  TextStyle get f54 {
    return copyWith(fontSize: 54.sp);
  }

  TextStyle get f56 {
    return copyWith(fontSize: 56.sp);
  }


  //region 样式
  TextStyle get lineThrough {
    return copyWith(decoration: TextDecoration.lineThrough);
  }

  TextStyle get underline {
    return copyWith(decoration: TextDecoration.underline);
  }

  TextStyle get italic {
    return copyWith(fontStyle: FontStyle.italic);
  }
//endregion
}
