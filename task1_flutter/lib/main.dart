import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';

void main() {
  runApp(const MyApp());
}

class MyApp extends StatelessWidget {
  const MyApp({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: Scaffold(
        appBar: AppBar(
          title: const Text("Example 1"),
          backgroundColor: Colors.green,
        ),
        body: Center(
            child: Column(
              mainAxisAlignment: MainAxisAlignment.center,
              crossAxisAlignment: CrossAxisAlignment.center,
              children: [
                Image.asset('asset/img/seasons.png', height: 200, width: 200,),

                Row(
                  mainAxisAlignment: MainAxisAlignment.center,
                  children: [
                    TextButton(
                      onPressed: (){},
                      child:
                      Container(
                        color: CupertinoColors.systemGrey2,
                        width: 60,
                        height: 30,
                        child:
                        Center(child: Text("Button1", style: TextStyle(color: Colors.black),)),
                      ),
                    ),


                    SizedBox(width: 50),

                    TextButton(
                      onPressed: (){},
                      child:
                      Container(
                        color: CupertinoColors.systemGrey2,
                        width: 60,
                        height: 30,
                        child:
                          Center(child: Text("Button2", style: TextStyle(color: Colors.black),)),
                      ),
                    ),
                  ],
                ),



                Row(
                  mainAxisAlignment: MainAxisAlignment.center,
                  children: [
                    TextButton(
                      onPressed: (){},
                      child:
                      Container(
                        color: CupertinoColors.systemGrey2,
                        width: 60,
                        height: 30,
                        child:
                        Center(child: Text("Button3", style: TextStyle(color: Colors.black),)),
                      ),
                    ),


                    SizedBox(width: 50),

                    TextButton(
                      onPressed: (){},
                      child:
                      Container(
                        color: CupertinoColors.systemGrey2,
                        width: 60,
                        height: 30,
                        child:
                        Center(child: Text("Button4", style: TextStyle(color: Colors.black),)),
                      ),
                    ),
                  ],
                ),

                Row(
                  children: const [
                    Text("Email:"),

                    SizedBox(width: 40,),

                    SizedBox(
                      width: 250,
                      child: TextField(
                          decoration: InputDecoration(
                            hintText: "",
                          )
                      ),
                    )
                  ],
                )

              ],
            )
        )
      )
    );
  }
}
