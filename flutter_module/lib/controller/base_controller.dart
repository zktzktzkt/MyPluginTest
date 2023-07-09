import 'package:flutter_module/common/toast_mixin.dart';
import 'package:get/get.dart';

abstract class BaseController extends GetxController with ToastMixin {

  RxString barTitleString = "--".obs;

  void intData();

  @override
  void onInit() {
    super.onInit();
    print('>>>>>>>onInit');
  }

  @override
  void onDetached() {
    print('>>>>>>>onDetached');
  }

  @override
  void onInactive() {
    print('>>>>>>>onInactive');
  }

  @override
  void onPaused() {
    print('>>>>>>>onPaused');
  }

  @override
  void onResumed() {
    print('>>>>>>>onResumed');
  }

  @override
  void onReady() {
    super.onReady();
    print('>>>>>>>onReady');
    intData();
  }

  @override
  void onClose() {
    super.onClose();
    print('>>>>>>>onClose');
  }

}
