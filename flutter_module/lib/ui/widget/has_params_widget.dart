import 'package:flutter/material.dart';
import 'package:flutter_boost/flutter_boost.dart';
import 'package:flutter_module/router/router_key.dart';

//普通页面（有传参）
class HasParamsWidget extends StatelessWidget {
  Map params;

  HasParamsWidget(this.params);

  @override
  Widget build(BuildContext context) {
    return Scaffold(
        appBar: AppBar(
          title: Text('HasParamsActivity'),
          leading: IconButton(
            icon: Icon(Icons.arrow_back, color: Colors.white),
            onPressed: () {
              BoostNavigator.instance.pop("");
            },
          ),
        ),
        body: Column(children: <Widget>[
          Container(
            margin: const EdgeInsets.only(top: 40.0),
            child: Text('This is a flutter activity',
                style: TextStyle(fontSize: 28.0, color: Colors.blue)),
            alignment: AlignmentDirectional.center,
          ),
          Container(
            margin: const EdgeInsets.only(top: 40.0),
            child: Text(
                'Native request params :\n ID = ' +
                    params[HasParams.EXTRA_KEY_ID].toString() +
                    '  GROUP = ' +
                    params[HasParams.EXTRA_KEY_GROUP].toString(),
                style: TextStyle(fontSize: 28.0, color: Colors.blue)),
            alignment: AlignmentDirectional.center,
          )
        ]));
  }
}
