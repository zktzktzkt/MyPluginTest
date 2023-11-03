import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';

import '../alias.dart';

class DecoratedFlex extends StatelessWidget {
  const DecoratedFlex({
    Key? key,
    this.padding,
    this.margin,
    this.color,
    this.decoration,
    this.foregroundDecoration,
    this.constraints,
    this.transform,
    this.offset,
    this.width,
    this.height,
    required this.direction,
    this.alignment,
    this.mainAxisAlignment = MainAxisAlignment.start,
    this.mainAxisSize = MainAxisSize.max,
    this.crossAxisAlignment = CrossAxisAlignment.center,
    this.textBaseline,
    this.onPressed,
    this.onLongPressed,
    this.onDoubleTap,
    this.onVerticalDragEnd,
    this.onHorizontalDragEnd,
    this.behavior,
    this.itemSpacing = 0,
    this.divider,
    this.visible,
    this.expanded,
    this.flexible,
    this.flex,
    this.forceItemSameExtent = false,
    this.elevation,
    this.scrollable,
    this.widthFactor,
    this.heightFactor,
    this.material = false,
    this.textStyle,
    this.repaintBoundaryKey,
    this.animationDuration,
    this.animationCurve,
    this.theme,
    this.topEnd,
    this.center,
    this.sliver = false,
    this.verticalDirection,
    this.clipBehavior = Clip.none,
    this.children = const [],
  }) : super(key: key);

  //region Container
  final EdgeInsets? padding;
  final EdgeInsets? margin;
  final Color? color;
  final Decoration? decoration;
  final Decoration? foregroundDecoration;
  final BoxConstraints? constraints;
  final Matrix4? transform;
  final double? width;
  final double? height;

  //endregion

  //region Flex
  final Axis direction;
  final AlignmentGeometry? alignment;
  final MainAxisAlignment mainAxisAlignment;
  final MainAxisSize mainAxisSize;
  final CrossAxisAlignment crossAxisAlignment;
  final TextBaseline? textBaseline;

  //endregion

  //region GestureDetector
  final ContextCallback? onPressed;
  final ContextCallback? onLongPressed;
  final ContextCallback? onDoubleTap;
  final GestureDragEndCallback? onVerticalDragEnd;
  final GestureDragEndCallback? onHorizontalDragEnd;
  final HitTestBehavior? behavior;

  //endregion

  //region Material
  final bool material;
  final double? elevation;

  //endregion

  //region FractionallySizedBox
  final double? widthFactor;
  final double? heightFactor;

  //endregion

  /// 元素间距
  final double? itemSpacing;

  /// 分隔控件 与[itemSpacing]功能类似, 但是优先使用[divider]
  final Widget? divider;

  /// 是否可见
  final bool? visible;

  /// 是否展开
  final bool? expanded;

  /// 是否展开
  final bool? flexible;

  /// 比例
  final int? flex;

  /// 是否强制子控件等长
  final bool? forceItemSameExtent;

  /// 作用在Transform.translate上的偏移量
  final Offset? offset;

  /// 是否可滚动
  final bool? scrollable;

  /// 内部统一的TextStyle
  final TextStyle? textStyle;

  /// 是否需要[RepaintBoundary]
  final GlobalKey? repaintBoundaryKey;

  /// 动画时长
  final Duration? animationDuration;

  /// 动画曲线
  final Curve? animationCurve;

  /// 主题
  final ThemeData? theme;

  /// 右上角控件
  final Widget? topEnd;

  /// 是否加center
  final bool? center;

  /// 是否sliver
  final bool? sliver;

  /// 垂直方向
  final VerticalDirection? verticalDirection;

  final Clip clipBehavior;

  /// 子元素
  final List<Widget> children;

  @override
  Widget build(BuildContext context) {
    List<Widget> _children = children;

    if (forceItemSameExtent == true) {
      _children = children.map<Widget>((it) => Expanded(child: it)).toList();
    }

    Widget result = Flex(
      direction: direction,
      mainAxisAlignment: mainAxisAlignment,
      mainAxisSize: mainAxisSize,
      crossAxisAlignment: crossAxisAlignment,
      textBaseline: textBaseline,
      verticalDirection: verticalDirection ?? VerticalDirection.down,
      children: itemSpacing != 0 || divider != null
          ? addItemDivider(_children, itemSpacing!, divider)
          : _children,
    );

    if (topEnd != null) {
      result = Stack(children: <Widget>[
        result,
        Positioned(top: 0, right: 0, child: topEnd!),
      ]);
    }

    if (material || elevation != null) {
      result = Material(
        elevation: elevation ?? 0,
        color: color,
        child: result,
      );
    }

    if (padding != null ||
        margin != null ||
        width != null ||
        height != null ||
        color != null ||
        decoration != null ||
        foregroundDecoration != null ||
        constraints != null ||
        transform != null ||
        alignment != null) {
      if (animationDuration != null && animationDuration != Duration.zero) {
        result = AnimatedContainer(
          duration: animationDuration!,
          curve: animationCurve ?? Curves.linear,
          padding: padding,
          margin: margin,
          width: width,
          height: height,
          color: color,
          decoration: decoration,
          foregroundDecoration: foregroundDecoration,
          constraints: constraints,
          transform: transform,
          alignment: alignment,
          child: result,
        );
      } else {
        result = Container(
          padding: padding,
          margin: margin,
          width: width,
          height: height,
          color: color,
          decoration: decoration,
          foregroundDecoration: foregroundDecoration,
          constraints: constraints,
          transform: transform,
          alignment: alignment,
          clipBehavior: clipBehavior,
          child: result,
        );
      }
    }

    if (onPressed != null ||
        onLongPressed != null ||
        onVerticalDragEnd != null ||
        onHorizontalDragEnd != null ||
        onDoubleTap != null) {
      result = GestureDetector(
        behavior: behavior ?? HitTestBehavior.opaque,
        onTap: onPressed == null ? null : () => onPressed!(context),
        onLongPress:
            onLongPressed == null ? null : () => onLongPressed!(context),
        onVerticalDragEnd: onVerticalDragEnd,
        onHorizontalDragEnd: onHorizontalDragEnd,
        onDoubleTap: onDoubleTap == null ? null : () => onDoubleTap!(context),
        child: result,
      );
    }

    if (widthFactor != null || heightFactor != null) {
      result = FractionallySizedBox(
        widthFactor: widthFactor,
        heightFactor: heightFactor,
        child: result,
      );
    }

    if (textStyle != null) {
      result = DefaultTextStyle(style: textStyle!, child: result);
    }

    if (repaintBoundaryKey != null) {
      result = RepaintBoundary(key: repaintBoundaryKey, child: result);
    }

    if (scrollable == true) {
      result = SingleChildScrollView(child: result, scrollDirection: direction);
    }

    if (visible != null) {
      result = Visibility(visible: visible!, child: result);
    }

    if (theme != null) {
      result = Theme(data: theme!, child: result);
    }

    if (center == true) {
      result = Center(child: result);
    }

    if (offset != null) {
      result = Transform.translate(offset: offset!, child: result);
    }

    if (expanded == true) {
      result = Expanded(child: result, flex: flex ?? 1);
    }

    if (flexible == true) {
      result = Flexible(child: result, flex: flex ?? 1);
    }

    if (sliver == true) {
      result = SliverToBoxAdapter(child: result);
    }

    return result;
  }

  List<Widget> addItemDivider(
    List<Widget> children,
    double itemSpacing,
    Widget? divider,
  ) {
    final result = List<Widget>.from(children);

    // 确认要往哪几个index(以最终的插入后的List为参考系)插空间
    int currentLength = result.length;
    if (currentLength > 1) {
      final indexes = <int>[];
      // `currentLength + (currentLength - 1)`是插入后的长度
      // 这里的循环是在纸上画过得出的结论
      for (int i = 1; i < currentLength + (currentLength - 1); i += 2) {
        indexes.add(i);
      }

      if (direction == Axis.horizontal) {
        indexes.forEach((index) {
          result.insert(index, divider ?? SizedBox(width: itemSpacing));
        });
      } else if (direction == Axis.vertical) {
        indexes.forEach((index) {
          result.insert(index, divider ?? SizedBox(height: itemSpacing));
        });
      }
    }

    return result;
  }
}