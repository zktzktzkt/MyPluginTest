import 'package:flutter/material.dart';
import 'package:flutter_module/common/alias.dart';
import 'package:flutter_module/common/decorated/decorated_text.widget.dart';

class DecoratedAutoSizeText extends StatelessWidget {
  final String text;
  final TextStyle style;
  final double? maxFontSize;
  final double? minFontSize;

  final EdgeInsetsGeometry? padding;
  final EdgeInsetsGeometry? margin;
  final BoxDecoration? decoration;
  final StrutStyle strutStyle;
  final ContextCallback? onPressed;
  final int? maxLines;
  final TextAlign? textAlign;
  final TextOverflow? overflow;
  final BoxConstraints? constraints;
  final int flex;
  final double? width;
  final double? height;
  final bool? visible;
  final bool? center;
  final bool? sliver;
  final Matrix4? transform;
  final Widget? leftWidget;
  final Widget? rightWidget;
  final bool softWrap;
  final bool? material;
  final MainAxisAlignment? mainAxisAlignment;
  final CrossAxisAlignment? crossAxisAlignment;

  DecoratedAutoSizeText({
    required this.text,
    required this.style,
    this.maxFontSize,
    this.minFontSize,
    this.padding,
    this.margin,
    this.decoration,
    this.strutStyle = const StrutStyle(),
    this.onPressed,
    this.maxLines,
    this.textAlign,
    this.overflow,
    this.constraints,
    this.flex = 1,
    this.visible,
    this.width,
    this.height,
    this.center,
    this.transform,
    this.sliver,
    this.leftWidget,
    this.rightWidget,
    this.softWrap = true,
    this.material,
    this.mainAxisAlignment,
    this.crossAxisAlignment,
  });

  @override
  Widget build(BuildContext context) {
    return LayoutBuilder(
      builder: (BuildContext context, BoxConstraints constraints) {
        double fontSize = style.fontSize!;
        double textWidth = _textWidth(text, style);

        while (textWidth > constraints.maxWidth && (maxFontSize == null || fontSize > maxFontSize!)) {
          fontSize = fontSize - 1;
          textWidth = _textWidth(text, style.copyWith(fontSize: fontSize));
        }

        if (minFontSize != null && fontSize < minFontSize!) {
          fontSize = minFontSize!;
        }

        return DecoratedText(
          text,
          style: style.copyWith(fontSize: fontSize),
          maxLines: 1,
          overflow: TextOverflow.ellipsis,

          padding:padding,
          margin: margin,
          decoration:decoration,
          strutStyle:strutStyle,
          onPressed:onPressed,
          // maxLines:maxLines,
          // overflow:overflow,

          textAlign:textAlign,
          constraints:constraints,
          flex:flex,
          visible:visible,
          width:width,
          height:height,
          center:center,
          transform:transform,
          sliver:sliver,
          leftWidget:leftWidget,
          rightWidget:rightWidget,
          softWrap:softWrap,
          material:material,
          mainAxisAlignment:mainAxisAlignment,
          crossAxisAlignment:crossAxisAlignment,
        );
      },
    );
  }

  double _textWidth(String text, TextStyle style) {
    final TextPainter textPainter = TextPainter(
      text: TextSpan(text: text, style: style),
      maxLines: 1,
      textDirection: TextDirection.ltr,
    )..layout(minWidth: 0, maxWidth: double.infinity);
    return textPainter.width;
  }
}