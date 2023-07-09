// GENERATED CODE - DO NOT MODIFY BY HAND
import 'package:flutter/material.dart';
import 'package:intl/intl.dart';
import 'intl/messages_all.dart';

// **************************************************************************
// Generator: Flutter Intl IDE plugin
// Made by Localizely
// **************************************************************************

// ignore_for_file: non_constant_identifier_names, lines_longer_than_80_chars
// ignore_for_file: join_return_with_assignment, prefer_final_in_for_each
// ignore_for_file: avoid_redundant_argument_values, avoid_escaping_inner_quotes

class S {
  S();

  static S? _current;

  static S get current {
    assert(_current != null,
        'No instance of S was loaded. Try to initialize the S delegate before accessing S.current.');
    return _current!;
  }

  static const AppLocalizationDelegate delegate = AppLocalizationDelegate();

  static Future<S> load(Locale locale) {
    final name = (locale.countryCode?.isEmpty ?? false)
        ? locale.languageCode
        : locale.toString();
    final localeName = Intl.canonicalizedLocale(name);
    return initializeMessages(localeName).then((_) {
      Intl.defaultLocale = localeName;
      final instance = S();
      S._current = instance;

      return instance;
    });
  }

  static S of(BuildContext context) {
    final instance = S.maybeOf(context);
    assert(instance != null,
        'No instance of S present in the widget tree. Did you add S.delegate in localizationsDelegates?');
    return instance!;
  }

  static S? maybeOf(BuildContext context) {
    return Localizations.of<S>(context, S);
  }

  /// `选购经验`
  String get discover_experience_title {
    return Intl.message(
      '选购经验',
      name: 'discover_experience_title',
      desc: '',
      args: [],
    );
  }

  /// `bloc 对count进行相加`
  String get main_title {
    return Intl.message(
      'bloc 对count进行相加',
      name: 'main_title',
      desc: '',
      args: [],
    );
  }

  /// `Open flutter page get result`
  String get main_button_title {
    return Intl.message(
      'Open flutter page get result',
      name: 'main_button_title',
      desc: '',
      args: [],
    );
  }

  /// `测试页面`
  String get test_title {
    return Intl.message(
      '测试页面',
      name: 'test_title',
      desc: '',
      args: [],
    );
  }

  /// `Logcat`
  String get logcat_title {
    return Intl.message(
      'Logcat',
      name: 'logcat_title',
      desc: '',
      args: [],
    );
  }

  /// `关于洞窝APP`
  String get f_text_about_title {
    return Intl.message(
      '关于洞窝APP',
      name: 'f_text_about_title',
      desc: '',
      args: [],
    );
  }

  /// `申请服务`
  String get f_text_apply_service {
    return Intl.message(
      '申请服务',
      name: 'f_text_apply_service',
      desc: '',
      args: [],
    );
  }

  /// `申请售后`
  String get f_text_after_sales {
    return Intl.message(
      '申请售后',
      name: 'f_text_after_sales',
      desc: '',
      args: [],
    );
  }

  /// `信息审核`
  String get f_text_info_review {
    return Intl.message(
      '信息审核',
      name: 'f_text_info_review',
      desc: '',
      args: [],
    );
  }

  /// `售后服务`
  String get f_text_aflter_service {
    return Intl.message(
      '售后服务',
      name: 'f_text_aflter_service',
      desc: '',
      args: [],
    );
  }

  /// `服务完成`
  String get f_text_service_complete {
    return Intl.message(
      '服务完成',
      name: 'f_text_service_complete',
      desc: '',
      args: [],
    );
  }

  /// `商品信息`
  String get f_text_goods_info {
    return Intl.message(
      '商品信息',
      name: 'f_text_goods_info',
      desc: '',
      args: [],
    );
  }

  /// `损失数量`
  String get f_text_loss_amount {
    return Intl.message(
      '损失数量',
      name: 'f_text_loss_amount',
      desc: '',
      args: [],
    );
  }

  /// `服务信息`
  String get f_text_setvice_info {
    return Intl.message(
      '服务信息',
      name: 'f_text_setvice_info',
      desc: '',
      args: [],
    );
  }

  /// `联系电话`
  String get f_text_contact_num {
    return Intl.message(
      '联系电话',
      name: 'f_text_contact_num',
      desc: '',
      args: [],
    );
  }

  /// `所在地址`
  String get f_text_address {
    return Intl.message(
      '所在地址',
      name: 'f_text_address',
      desc: '',
      args: [],
    );
  }

  /// `详细地址`
  String get f_text_address_detail {
    return Intl.message(
      '详细地址',
      name: 'f_text_address_detail',
      desc: '',
      args: [],
    );
  }

  /// `请填写详细地址`
  String get f_text_fill_address_detail {
    return Intl.message(
      '请填写详细地址',
      name: 'f_text_fill_address_detail',
      desc: '',
      args: [],
    );
  }

  /// `问题描述`
  String get f_text_problem_desc {
    return Intl.message(
      '问题描述',
      name: 'f_text_problem_desc',
      desc: '',
      args: [],
    );
  }

  /// `请详细描述商品的故障或损坏情况`
  String get f_text_loss_detail_desc {
    return Intl.message(
      '请详细描述商品的故障或损坏情况',
      name: 'f_text_loss_detail_desc',
      desc: '',
      args: [],
    );
  }

  /// `请填写商品当前地址`
  String get f_text_fill_goods_address {
    return Intl.message(
      '请填写商品当前地址',
      name: 'f_text_fill_goods_address',
      desc: '',
      args: [],
    );
  }

  /// `请填写所在地区`
  String get f_text_fill_area_address {
    return Intl.message(
      '请填写所在地区',
      name: 'f_text_fill_area_address',
      desc: '',
      args: [],
    );
  }

  /// `上传图片`
  String get f_text_upload_image {
    return Intl.message(
      '上传图片',
      name: 'f_text_upload_image',
      desc: '',
      args: [],
    );
  }

  /// `请上传商品损失照片`
  String get f_text_upload_error_image {
    return Intl.message(
      '请上传商品损失照片',
      name: 'f_text_upload_error_image',
      desc: '',
      args: [],
    );
  }

  /// `（最多20张）`
  String get f_text_up_to_num {
    return Intl.message(
      '（最多20张）',
      name: 'f_text_up_to_num',
      desc: '',
      args: [],
    );
  }

  /// `提交`
  String get f_text_commnit {
    return Intl.message(
      '提交',
      name: 'f_text_commnit',
      desc: '',
      args: [],
    );
  }

  /// `请填写联系电话`
  String get f_text_fill_phone {
    return Intl.message(
      '请填写联系电话',
      name: 'f_text_fill_phone',
      desc: '',
      args: [],
    );
  }

  /// `请输入正确的手机号码`
  String get f_text_fill_correct_phone {
    return Intl.message(
      '请输入正确的手机号码',
      name: 'f_text_fill_correct_phone',
      desc: '',
      args: [],
    );
  }

  /// `请填写问题描述`
  String get f_text_fill_error_desc {
    return Intl.message(
      '请填写问题描述',
      name: 'f_text_fill_error_desc',
      desc: '',
      args: [],
    );
  }

  /// `请上传受损图片`
  String get f_text_fill_loss_phone {
    return Intl.message(
      '请上传受损图片',
      name: 'f_text_fill_loss_phone',
      desc: '',
      args: [],
    );
  }

  /// `服务商品`
  String get f_text_goods_service {
    return Intl.message(
      '服务商品',
      name: 'f_text_goods_service',
      desc: '',
      args: [],
    );
  }

  /// `服务单号复制成功`
  String get f_text_copy_service_order {
    return Intl.message(
      '服务单号复制成功',
      name: 'f_text_copy_service_order',
      desc: '',
      args: [],
    );
  }

  /// `服务单号`
  String get f_text_service_order {
    return Intl.message(
      '服务单号',
      name: 'f_text_service_order',
      desc: '',
      args: [],
    );
  }

  /// `所在地区`
  String get f_text_location_address {
    return Intl.message(
      '所在地区',
      name: 'f_text_location_address',
      desc: '',
      args: [],
    );
  }

  /// `售后问题`
  String get f_text_sales_question {
    return Intl.message(
      '售后问题',
      name: 'f_text_sales_question',
      desc: '',
      args: [],
    );
  }

  /// `提交补充资料`
  String get f_text_fill_supplementary_info {
    return Intl.message(
      '提交补充资料',
      name: 'f_text_fill_supplementary_info',
      desc: '',
      args: [],
    );
  }

  /// `历史服务单`
  String get f_text_history_service_order {
    return Intl.message(
      '历史服务单',
      name: 'f_text_history_service_order',
      desc: '',
      args: [],
    );
  }

  /// `再次发起售后申请`
  String get f_text_apply_again {
    return Intl.message(
      '再次发起售后申请',
      name: 'f_text_apply_again',
      desc: '',
      args: [],
    );
  }

  /// `确认服务完成`
  String get f_text_service_over {
    return Intl.message(
      '确认服务完成',
      name: 'f_text_service_over',
      desc: '',
      args: [],
    );
  }

  /// `撤销申请`
  String get f_text_cancel_apply {
    return Intl.message(
      '撤销申请',
      name: 'f_text_cancel_apply',
      desc: '',
      args: [],
    );
  }

  /// `服务进度`
  String get f_text_service_progress {
    return Intl.message(
      '服务进度',
      name: 'f_text_service_progress',
      desc: '',
      args: [],
    );
  }

  /// `加载中…`
  String get f_text_loading {
    return Intl.message(
      '加载中…',
      name: 'f_text_loading',
      desc: '',
      args: [],
    );
  }

  /// `提交失败`
  String get f_text_commint_error {
    return Intl.message(
      '提交失败',
      name: 'f_text_commint_error',
      desc: '',
      args: [],
    );
  }

  /// `提交成功`
  String get f_text_commint_cuccess {
    return Intl.message(
      '提交成功',
      name: 'f_text_commint_cuccess',
      desc: '',
      args: [],
    );
  }

  /// `取消`
  String get f_text_cancel {
    return Intl.message(
      '取消',
      name: 'f_text_cancel',
      desc: '',
      args: [],
    );
  }

  /// `确认`
  String get f_text_confirm {
    return Intl.message(
      '确认',
      name: 'f_text_confirm',
      desc: '',
      args: [],
    );
  }

  /// `修改地址`
  String get f_text_change_address {
    return Intl.message(
      '修改地址',
      name: 'f_text_change_address',
      desc: '',
      args: [],
    );
  }

  /// `选择地区`
  String get f_text_select_area {
    return Intl.message(
      '选择地区',
      name: 'f_text_select_area',
      desc: '',
      args: [],
    );
  }

  /// `省、市、区、街道`
  String get f_text_address_desc {
    return Intl.message(
      '省、市、区、街道',
      name: 'f_text_address_desc',
      desc: '',
      args: [],
    );
  }

  /// `请输入楼栋、单元、门牌号`
  String get f_text_address_detail_desc {
    return Intl.message(
      '请输入楼栋、单元、门牌号',
      name: 'f_text_address_detail_desc',
      desc: '',
      args: [],
    );
  }

  /// `请输入新手机号码`
  String get f_text_fill_new_phone_num {
    return Intl.message(
      '请输入新手机号码',
      name: 'f_text_fill_new_phone_num',
      desc: '',
      args: [],
    );
  }

  /// `修改手机号`
  String get f_text_change_new_phone_num {
    return Intl.message(
      '修改手机号',
      name: 'f_text_change_new_phone_num',
      desc: '',
      args: [],
    );
  }

  /// `手机号不能为空`
  String get f_text_phone_num_not_empty {
    return Intl.message(
      '手机号不能为空',
      name: 'f_text_phone_num_not_empty',
      desc: '',
      args: [],
    );
  }

  /// `请选择地区`
  String get f_text_please_select_area {
    return Intl.message(
      '请选择地区',
      name: 'f_text_please_select_area',
      desc: '',
      args: [],
    );
  }

  /// `确认撤销申请？`
  String get f_text_confirm_request {
    return Intl.message(
      '确认撤销申请？',
      name: 'f_text_confirm_request',
      desc: '',
      args: [],
    );
  }

  /// `洞窝`
  String get f_text_app_name {
    return Intl.message(
      '洞窝',
      name: 'f_text_app_name',
      desc: '',
      args: [],
    );
  }

  /// `当前版本号:`
  String get f_text_current_version {
    return Intl.message(
      '当前版本号:',
      name: 'f_text_current_version',
      desc: '',
      args: [],
    );
  }

  /// `《用户协议》`
  String get f_text_yhxy {
    return Intl.message(
      '《用户协议》',
      name: 'f_text_yhxy',
      desc: '',
      args: [],
    );
  }

  /// `《隐私政策》`
  String get f_text_yszc {
    return Intl.message(
      '《隐私政策》',
      name: 'f_text_yszc',
      desc: '',
      args: [],
    );
  }

  /// `证件信息`
  String get f_text_zjxx {
    return Intl.message(
      '证件信息',
      name: 'f_text_zjxx',
      desc: '',
      args: [],
    );
  }

  /// `Copyright by 洞窝`
  String get f_text_app_desc {
    return Intl.message(
      'Copyright by 洞窝',
      name: 'f_text_app_desc',
      desc: '',
      args: [],
    );
  }

  /// `查看完整服务进度`
  String get f_text_full_service_progress {
    return Intl.message(
      '查看完整服务进度',
      name: 'f_text_full_service_progress',
      desc: '',
      args: [],
    );
  }

  /// `收起`
  String get f_text_put_away {
    return Intl.message(
      '收起',
      name: 'f_text_put_away',
      desc: '',
      args: [],
    );
  }

  /// `保障详情`
  String get f_text_coverage_detail {
    return Intl.message(
      '保障详情',
      name: 'f_text_coverage_detail',
      desc: '',
      args: [],
    );
  }

  /// `服务详情`
  String get f_text_service_detail {
    return Intl.message(
      '服务详情',
      name: 'f_text_service_detail',
      desc: '',
      args: [],
    );
  }

  /// `发起时间`
  String get f_text_start_time {
    return Intl.message(
      '发起时间',
      name: 'f_text_start_time',
      desc: '',
      args: [],
    );
  }

  /// `设置多语言`
  String get language_page_title {
    return Intl.message(
      '设置多语言',
      name: 'language_page_title',
      desc: '',
      args: [],
    );
  }

  /// `我的消费券`
  String get f_text_my_coupon {
    return Intl.message(
      '我的消费券',
      name: 'f_text_my_coupon',
      desc: '',
      args: [],
    );
  }

  /// `使用流程`
  String get f_text_Flow {
    return Intl.message(
      '使用流程',
      name: 'f_text_Flow',
      desc: '',
      args: [],
    );
  }

  /// `洞心服务,温暖品质生活`
  String get f_text_dongxin_service {
    return Intl.message(
      '洞心服务,温暖品质生活',
      name: 'f_text_dongxin_service',
      desc: '',
      args: [],
    );
  }

  /// `活动细则`
  String get f_text_activity_bylaws {
    return Intl.message(
      '活动细则',
      name: 'f_text_activity_bylaws',
      desc: '',
      args: [],
    );
  }

  /// `获得消费券`
  String get f_text_consumer_coupon {
    return Intl.message(
      '获得消费券',
      name: 'f_text_consumer_coupon',
      desc: '',
      args: [],
    );
  }

  /// `购买商品`
  String get f_text_Purchase_Goods {
    return Intl.message(
      '购买商品',
      name: 'f_text_Purchase_Goods',
      desc: '',
      args: [],
    );
  }

  /// `送装服务`
  String get f_text_Delivery_service {
    return Intl.message(
      '送装服务',
      name: 'f_text_Delivery_service',
      desc: '',
      args: [],
    );
  }

  /// `线上下单`
  String get f_text_Online_order {
    return Intl.message(
      '线上下单',
      name: 'f_text_Online_order',
      desc: '',
      args: [],
    );
  }

  /// `预约服务`
  String get f_text_reservation_service {
    return Intl.message(
      '预约服务',
      name: 'f_text_reservation_service',
      desc: '',
      args: [],
    );
  }

  /// `上门服务`
  String get f_text_door_service {
    return Intl.message(
      '上门服务',
      name: 'f_text_door_service',
      desc: '',
      args: [],
    );
  }

  /// `家有困难找居然`
  String get f_text_difficulty_easyhome {
    return Intl.message(
      '家有困难找居然',
      name: 'f_text_difficulty_easyhome',
      desc: '',
      args: [],
    );
  }

  /// `已使用`
  String get f_did_use {
    return Intl.message(
      '已使用',
      name: 'f_did_use',
      desc: '',
      args: [],
    );
  }

  /// `有效期`
  String get f_text_expirationdate {
    return Intl.message(
      '有效期',
      name: 'f_text_expirationdate',
      desc: '',
      args: [],
    );
  }

  /// `无门槛`
  String get f_text_No_threshold {
    return Intl.message(
      '无门槛',
      name: 'f_text_No_threshold',
      desc: '',
      args: [],
    );
  }

  /// `满`
  String get f_text_Man {
    return Intl.message(
      '满',
      name: 'f_text_Man',
      desc: '',
      args: [],
    );
  }

  /// `减`
  String get f_text_jian {
    return Intl.message(
      '减',
      name: 'f_text_jian',
      desc: '',
      args: [],
    );
  }

  /// `领券成功`
  String get f_text_claim_success {
    return Intl.message(
      '领券成功',
      name: 'f_text_claim_success',
      desc: '',
      args: [],
    );
  }

  /// `去使用`
  String get f_text_goto_use {
    return Intl.message(
      '去使用',
      name: 'f_text_goto_use',
      desc: '',
      args: [],
    );
  }

  /// `联系人`
  String get f_text_contacts {
    return Intl.message(
      '联系人',
      name: 'f_text_contacts',
      desc: '',
      args: [],
    );
  }

  /// `联系人电话`
  String get f_text_contacts_phone {
    return Intl.message(
      '联系人电话',
      name: 'f_text_contacts_phone',
      desc: '',
      args: [],
    );
  }

  /// `我的地址`
  String get f_text_my_address {
    return Intl.message(
      '我的地址',
      name: 'f_text_my_address',
      desc: '',
      args: [],
    );
  }

  /// `我的定位`
  String get f_text_my_positioning {
    return Intl.message(
      '我的定位',
      name: 'f_text_my_positioning',
      desc: '',
      args: [],
    );
  }

  /// `立即领券`
  String get f_text_get_coupon {
    return Intl.message(
      '立即领券',
      name: 'f_text_get_coupon',
      desc: '',
      args: [],
    );
  }

  /// `填写领取信息`
  String get f_text_fill_claim_information {
    return Intl.message(
      '填写领取信息',
      name: 'f_text_fill_claim_information',
      desc: '',
      args: [],
    );
  }

  /// `膨胀金充值`
  String get f_text_expandgold_recharge {
    return Intl.message(
      '膨胀金充值',
      name: 'f_text_expandgold_recharge',
      desc: '',
      args: [],
    );
  }

  /// `洞窝膨胀金`
  String get f_text_dongwo_expandgold {
    return Intl.message(
      '洞窝膨胀金',
      name: 'f_text_dongwo_expandgold',
      desc: '',
      args: [],
    );
  }

  /// `我的膨胀金`
  String get f_text_my_expandgold {
    return Intl.message(
      '我的膨胀金',
      name: 'f_text_my_expandgold',
      desc: '',
      args: [],
    );
  }

  /// `我的活动`
  String get f_text_my_activity {
    return Intl.message(
      '我的活动',
      name: 'f_text_my_activity',
      desc: '',
      args: [],
    );
  }

  /// `累计已省`
  String get f_text_accumulate_save {
    return Intl.message(
      '累计已省',
      name: 'f_text_accumulate_save',
      desc: '',
      args: [],
    );
  }

  /// `账户明细`
  String get f_text_account_detailed {
    return Intl.message(
      '账户明细',
      name: 'f_text_account_detailed',
      desc: '',
      args: [],
    );
  }

  /// `进行中`
  String get f_text_in_progress {
    return Intl.message(
      '进行中',
      name: 'f_text_in_progress',
      desc: '',
      args: [],
    );
  }

  /// `已失效`
  String get f_text_expired {
    return Intl.message(
      '已失效',
      name: 'f_text_expired',
      desc: '',
      args: [],
    );
  }

  /// `最新活动`
  String get f_text_new_activity {
    return Intl.message(
      '最新活动',
      name: 'f_text_new_activity',
      desc: '',
      args: [],
    );
  }

  /// `暂无活动`
  String get f_text_no_activity {
    return Intl.message(
      '暂无活动',
      name: 'f_text_no_activity',
      desc: '',
      args: [],
    );
  }

  /// `暂无记录`
  String get f_text_no_record {
    return Intl.message(
      '暂无记录',
      name: 'f_text_no_record',
      desc: '',
      args: [],
    );
  }

  /// `总资产(元)`
  String get f_text_total_assets {
    return Intl.message(
      '总资产(元)',
      name: 'f_text_total_assets',
      desc: '',
      args: [],
    );
  }

  /// `膨胀金详情`
  String get f_text_expandgold_detail {
    return Intl.message(
      '膨胀金详情',
      name: 'f_text_expandgold_detail',
      desc: '',
      args: [],
    );
  }

  /// `参与商品`
  String get f_text_participating_products {
    return Intl.message(
      '参与商品',
      name: 'f_text_participating_products',
      desc: '',
      args: [],
    );
  }

  /// `膨胀金余额(元)`
  String get f_text_expandgold_balance {
    return Intl.message(
      '膨胀金余额(元)',
      name: 'f_text_expandgold_balance',
      desc: '',
      args: [],
    );
  }

  /// `累计为您节省(元)`
  String get f_text_accumulate_saveforyou {
    return Intl.message(
      '累计为您节省(元)',
      name: 'f_text_accumulate_saveforyou',
      desc: '',
      args: [],
    );
  }

  /// `全部`
  String get f_text_whole {
    return Intl.message(
      '全部',
      name: 'f_text_whole',
      desc: '',
      args: [],
    );
  }

  /// `充值`
  String get f_text_recharge {
    return Intl.message(
      '充值',
      name: 'f_text_recharge',
      desc: '',
      args: [],
    );
  }

  /// `储值`
  String get f_text_stored_value {
    return Intl.message(
      '储值',
      name: 'f_text_stored_value',
      desc: '',
      args: [],
    );
  }

  /// `消费`
  String get f_text_consumption {
    return Intl.message(
      '消费',
      name: 'f_text_consumption',
      desc: '',
      args: [],
    );
  }

  /// `继续充值`
  String get f_text_continue_recharging {
    return Intl.message(
      '继续充值',
      name: 'f_text_continue_recharging',
      desc: '',
      args: [],
    );
  }

  /// `展开更多`
  String get f_text_expand_more {
    return Intl.message(
      '展开更多',
      name: 'f_text_expand_more',
      desc: '',
      args: [],
    );
  }

  /// `使用时间`
  String get f_text_use_time {
    return Intl.message(
      '使用时间',
      name: 'f_text_use_time',
      desc: '',
      args: [],
    );
  }

  /// `订单`
  String get f_text_order {
    return Intl.message(
      '订单',
      name: 'f_text_order',
      desc: '',
      args: [],
    );
  }

  /// `活动明细`
  String get f_text_activity_rule {
    return Intl.message(
      '活动明细',
      name: 'f_text_activity_rule',
      desc: '',
      args: [],
    );
  }

  /// `预约到店礼`
  String get f_text_arrive_shop_List {
    return Intl.message(
      '预约到店礼',
      name: 'f_text_arrive_shop_List',
      desc: '',
      args: [],
    );
  }

  /// `待使用`
  String get f_text_WaitingToBeUsed {
    return Intl.message(
      '待使用',
      name: 'f_text_WaitingToBeUsed',
      desc: '',
      args: [],
    );
  }

  /// `已完成`
  String get f_text_AlreadyOver {
    return Intl.message(
      '已完成',
      name: 'f_text_AlreadyOver',
      desc: '',
      args: [],
    );
  }

  /// `取消/退款/过期`
  String get f_text_CancelRefundPastDue {
    return Intl.message(
      '取消/退款/过期',
      name: 'f_text_CancelRefundPastDue',
      desc: '',
      args: [],
    );
  }

  /// `使用门店`
  String get f_text_use_shop_store {
    return Intl.message(
      '使用门店',
      name: 'f_text_use_shop_store',
      desc: '',
      args: [],
    );
  }

  /// `活动时间`
  String get f_text_new_activity_time {
    return Intl.message(
      '活动时间',
      name: 'f_text_new_activity_time',
      desc: '',
      args: [],
    );
  }

  /// `已退款`
  String get f_text_already_refund {
    return Intl.message(
      '已退款',
      name: 'f_text_already_refund',
      desc: '',
      args: [],
    );
  }

  /// `已取消`
  String get f_text_already_cancel {
    return Intl.message(
      '已取消',
      name: 'f_text_already_cancel',
      desc: '',
      args: [],
    );
  }

  /// `已过期`
  String get f_text_already_pastDue {
    return Intl.message(
      '已过期',
      name: 'f_text_already_pastDue',
      desc: '',
      args: [],
    );
  }

  /// `查看详情`
  String get f_text_lookDetail {
    return Intl.message(
      '查看详情',
      name: 'f_text_lookDetail',
      desc: '',
      args: [],
    );
  }

  /// `到店礼`
  String get f_text_arrive_shop_detail {
    return Intl.message(
      '到店礼',
      name: 'f_text_arrive_shop_detail',
      desc: '',
      args: [],
    );
  }

  /// `预约报名`
  String get f_text_arrive_shop_reservation_title {
    return Intl.message(
      '预约报名',
      name: 'f_text_arrive_shop_reservation_title',
      desc: '',
      args: [],
    );
  }

  /// `活动名称`
  String get f_text_action_info_name {
    return Intl.message(
      '活动名称',
      name: 'f_text_action_info_name',
      desc: '',
      args: [],
    );
  }

  /// `手机号`
  String get f_text_phone_num {
    return Intl.message(
      '手机号',
      name: 'f_text_phone_num',
      desc: '',
      args: [],
    );
  }

  /// `活动信息`
  String get f_text_action_info {
    return Intl.message(
      '活动信息',
      name: 'f_text_action_info',
      desc: '',
      args: [],
    );
  }

  /// `查看详情`
  String get f_text_detail {
    return Intl.message(
      '查看详情',
      name: 'f_text_detail',
      desc: '',
      args: [],
    );
  }

  /// `到店礼品包`
  String get f_text_arrive_shop_gift {
    return Intl.message(
      '到店礼品包',
      name: 'f_text_arrive_shop_gift',
      desc: '',
      args: [],
    );
  }

  /// `查看活动详情`
  String get f_text_for_detail {
    return Intl.message(
      '查看活动详情',
      name: 'f_text_for_detail',
      desc: '',
      args: [],
    );
  }

  /// `活动时间`
  String get f_text_action_time {
    return Intl.message(
      '活动时间',
      name: 'f_text_action_time',
      desc: '',
      args: [],
    );
  }

  /// `前往活动地点并出示二维码`
  String get f_text_qwmc_cserm {
    return Intl.message(
      '前往活动地点并出示二维码',
      name: 'f_text_qwmc_cserm',
      desc: '',
      args: [],
    );
  }

  /// `核销码`
  String get f_text_hxm {
    return Intl.message(
      '核销码',
      name: 'f_text_hxm',
      desc: '',
      args: [],
    );
  }

  /// `导航`
  String get f_text_navigation {
    return Intl.message(
      '导航',
      name: 'f_text_navigation',
      desc: '',
      args: [],
    );
  }

  /// `已核销`
  String get f_text_yhx {
    return Intl.message(
      '已核销',
      name: 'f_text_yhx',
      desc: '',
      args: [],
    );
  }

  /// `已退款`
  String get f_text_ytk {
    return Intl.message(
      '已退款',
      name: 'f_text_ytk',
      desc: '',
      args: [],
    );
  }

  /// `已取消`
  String get f_text_yqx {
    return Intl.message(
      '已取消',
      name: 'f_text_yqx',
      desc: '',
      args: [],
    );
  }

  /// `开始时间`
  String get f_text_new_start_time {
    return Intl.message(
      '开始时间',
      name: 'f_text_new_start_time',
      desc: '',
      args: [],
    );
  }

  /// `结束时间`
  String get f_text_new_end_time {
    return Intl.message(
      '结束时间',
      name: 'f_text_new_end_time',
      desc: '',
      args: [],
    );
  }

  /// `核销时间`
  String get f_text_hxsj {
    return Intl.message(
      '核销时间',
      name: 'f_text_hxsj',
      desc: '',
      args: [],
    );
  }

  /// `到店核销时，至多可选择N个权益`
  String get f_text_ddhxqyms {
    return Intl.message(
      '到店核销时，至多可选择N个权益',
      name: 'f_text_ddhxqyms',
      desc: '',
      args: [],
    );
  }

  /// `礼品价值`
  String get f_text_lpjz {
    return Intl.message(
      '礼品价值',
      name: 'f_text_lpjz',
      desc: '',
      args: [],
    );
  }

  /// `订单详情`
  String get f_text_ddxq {
    return Intl.message(
      '订单详情',
      name: 'f_text_ddxq',
      desc: '',
      args: [],
    );
  }

  /// `订单号`
  String get f_text_ddh {
    return Intl.message(
      '订单号',
      name: 'f_text_ddh',
      desc: '',
      args: [],
    );
  }

  /// `订单时间`
  String get f_text_ddtjsj {
    return Intl.message(
      '订单时间',
      name: 'f_text_ddtjsj',
      desc: '',
      args: [],
    );
  }

  /// `订单金额`
  String get f_text_ddje {
    return Intl.message(
      '订单金额',
      name: 'f_text_ddje',
      desc: '',
      args: [],
    );
  }

  /// `支付方式`
  String get f_text_zffs {
    return Intl.message(
      '支付方式',
      name: 'f_text_zffs',
      desc: '',
      args: [],
    );
  }

  /// `支付金额`
  String get f_text_zfje {
    return Intl.message(
      '支付金额',
      name: 'f_text_zfje',
      desc: '',
      args: [],
    );
  }

  /// `姓名`
  String get f_text_xm {
    return Intl.message(
      '姓名',
      name: 'f_text_xm',
      desc: '',
      args: [],
    );
  }

  /// `退款金额`
  String get f_text_tkje {
    return Intl.message(
      '退款金额',
      name: 'f_text_tkje',
      desc: '',
      args: [],
    );
  }

  /// `退款方式`
  String get f_text_tkfs {
    return Intl.message(
      '退款方式',
      name: 'f_text_tkfs',
      desc: '',
      args: [],
    );
  }

  /// `退款流水ID`
  String get f_text_tklsh {
    return Intl.message(
      '退款流水ID',
      name: 'f_text_tklsh',
      desc: '',
      args: [],
    );
  }

  /// `退款时间`
  String get f_text_tksj {
    return Intl.message(
      '退款时间',
      name: 'f_text_tksj',
      desc: '',
      args: [],
    );
  }

  /// `报名成功`
  String get f_text_bmcg {
    return Intl.message(
      '报名成功',
      name: 'f_text_bmcg',
      desc: '',
      args: [],
    );
  }

  /// `报名已取消`
  String get f_text_bmyqx {
    return Intl.message(
      '报名已取消',
      name: 'f_text_bmyqx',
      desc: '',
      args: [],
    );
  }

  /// `核销完成`
  String get f_text_hxwc {
    return Intl.message(
      '核销完成',
      name: 'f_text_hxwc',
      desc: '',
      args: [],
    );
  }

  /// `退款成功`
  String get f_text_tkcg {
    return Intl.message(
      '退款成功',
      name: 'f_text_tkcg',
      desc: '',
      args: [],
    );
  }

  /// `活动结束`
  String get f_text_hdjs {
    return Intl.message(
      '活动结束',
      name: 'f_text_hdjs',
      desc: '',
      args: [],
    );
  }

  /// `再想想`
  String get f_text_zxx {
    return Intl.message(
      '再想想',
      name: 'f_text_zxx',
      desc: '',
      args: [],
    );
  }

  /// `取消后将不再享受此福利真的要取消订单吗？`
  String get f_text_qxddts {
    return Intl.message(
      '取消后将不再享受此福利真的要取消订单吗？',
      name: 'f_text_qxddts',
      desc: '',
      args: [],
    );
  }

  /// `领取成功`
  String get f_text_gmcg {
    return Intl.message(
      '领取成功',
      name: 'f_text_gmcg',
      desc: '',
      args: [],
    );
  }

  /// `退款成功`
  String get f_text_gmsb {
    return Intl.message(
      '退款成功',
      name: 'f_text_gmsb',
      desc: '',
      args: [],
    );
  }

  /// `(工作日)`
  String get f_text_gzr {
    return Intl.message(
      '(工作日)',
      name: 'f_text_gzr',
      desc: '',
      args: [],
    );
  }

  /// `(节假日)`
  String get f_text_jjr {
    return Intl.message(
      '(节假日)',
      name: 'f_text_jjr',
      desc: '',
      args: [],
    );
  }

  /// `满`
  String get f_text_man {
    return Intl.message(
      '满',
      name: 'f_text_man',
      desc: '',
      args: [],
    );
  }

  /// `使用`
  String get f_text_used {
    return Intl.message(
      '使用',
      name: 'f_text_used',
      desc: '',
      args: [],
    );
  }

  /// `页面开小差啦`
  String get f_text_error_title {
    return Intl.message(
      '页面开小差啦',
      name: 'f_text_error_title',
      desc: '',
      args: [],
    );
  }

  /// `手机号`
  String get f_text_sjh {
    return Intl.message(
      '手机号',
      name: 'f_text_sjh',
      desc: '',
      args: [],
    );
  }

  /// `查看活动`
  String get f_text_view_activity {
    return Intl.message(
      '查看活动',
      name: 'f_text_view_activity',
      desc: '',
      args: [],
    );
  }

  /// `适用于`
  String get f_text_activity_suit {
    return Intl.message(
      '适用于',
      name: 'f_text_activity_suit',
      desc: '',
      args: [],
    );
  }

  /// `消费记录`
  String get f_text_consumption_record {
    return Intl.message(
      '消费记录',
      name: 'f_text_consumption_record',
      desc: '',
      args: [],
    );
  }
}

class AppLocalizationDelegate extends LocalizationsDelegate<S> {
  const AppLocalizationDelegate();

  List<Locale> get supportedLocales {
    return const <Locale>[
      Locale.fromSubtags(languageCode: 'zh', countryCode: 'CN'),
      Locale.fromSubtags(languageCode: 'en', countryCode: 'US'),
      Locale.fromSubtags(languageCode: 'zh', countryCode: 'MO'),
    ];
  }

  @override
  bool isSupported(Locale locale) => _isSupported(locale);
  @override
  Future<S> load(Locale locale) => S.load(locale);
  @override
  bool shouldReload(AppLocalizationDelegate old) => false;

  bool _isSupported(Locale locale) {
    for (var supportedLocale in supportedLocales) {
      if (supportedLocale.languageCode == locale.languageCode) {
        return true;
      }
    }
    return false;
  }
}
