import React from 'react';
import { View, Text, Image, ScrollView, TextInput, Button } from 'react-native';

const App = () => {
  return (
    <View style={{flex:1, justifyContent: 'center'}}>
      {/* Top image */}
      <View style={{alignItems: 'center'}}>
      <Image
          source={require('./img/seasons.png')
          }
          style={{ width: 200, height: 200}}
        />
      </View>

      {/* 4 Buttons */}
      <View>
        {/* First Line of Buttons */}
        <View style={{flexDirection:'row', justifyContent:'space-evenly', padding:20}}>
          <Button title="Button1" color="#C0C0C0"></Button>
          <Button title="Button2" color="#C0C0C0"></Button>
        </View>
        
        {/* Second Line of Buttons */}
        <View style={{flexDirection:'row', justifyContent:'space-evenly', padding:20}}>
        <Button title="Button3" color="#C0C0C0"></Button>
          <Button title="Button4" color="#C0C0C0"></Button>
        </View>

      </View>



      {/* Email */}
      <View style={{flexDirection:'row', justifyContent:'flex-start'}}>
        <Text style={{padding:30}}>Email</Text>

        <TextInput defaultValue='Enter you email here' style={{textAlign:'left', flex:1}}></TextInput>
        

      </View>


    </View>
  );
}

export default App;