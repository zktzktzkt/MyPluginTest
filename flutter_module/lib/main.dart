import 'dart:ui';

import 'package:flutter/material.dart';

void main() {
  runApp(getRouter(window.defaultRouteName));
}

///接收 Android 跳转过来的启动路由参数，如果匹配上了走正常流程
///如果没匹配上，则提示 page not found
Widget getRouter(String routeName) {
  switch(routeName){
    case "main":
      return const MyApp();
    default:
      return MaterialApp(
        home: Scaffold(
          appBar: AppBar(
            title: const Text("Flutter Demo Home Page"),
          ),
          body: const Center(
            child: Text(
              "page not found",
              style: TextStyle(
                  fontSize: 24,
                  color: Colors.red
              ),
            ),
          ),
        ),
      );
  }
}

class MyApp extends StatelessWidget {

  const MyApp({super.key});

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Flutter Demo',
      // initialRoute:"main", //名为"/"的路由作为应用的home(首页)
      theme: ThemeData(
        primarySwatch: Colors.blue,
      ),
      home: const MyHomePage(title: 'Flutter Demo Home Page'),
    );
  }
}

class MyHomePage extends StatefulWidget {

  const MyHomePage({super.key, required this.title});
  final String title;

  @override
  State<MyHomePage> createState() => _MyHomePageState();
}

class _MyHomePageState extends State<MyHomePage> {
  int _counter = 0;

  void _incrementCounter() {
    setState(() {
      _counter++;
    });
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text(widget.title),
      ),
      body: Center(
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: <Widget>[
            const Text(
              'You have pushed the button this many times:',
            ),
            Text(
              '$_counter',
              style: Theme.of(context).textTheme.headlineMedium,
            ),
          ],
        ),
      ),
      floatingActionButton: FloatingActionButton(
        onPressed: _incrementCounter,
        tooltip: 'Increment',
        child: const Icon(Icons.add),
      ),
    );
  }
}
