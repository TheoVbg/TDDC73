
import { StyleSheet, Text, View, Button } from 'react-native';
import { StackActions } from '@react-navigation/native';

export default function ProjectScreen({ navigation, route }) {
    let Pname = route.params.Pname;
    let Pdescription = route.params.Pdescription;
    let Plicense = route.params.Plicense;
    let Pcommits = route.params.Pcommits;
    let Pbranches = route.params.Pbranches;

  return (
    <View style={styles.container}>
      <Text style={styles.Name}>{Pname}</Text>
      <Text style={styles.Description}>{Pdescription}</Text>

      <View style={styles.lign}>
        <View style={styles.colomn}>
            <Text style={styles.Informations}>
            License
            </Text>
            <Text style={styles.Informations}>
            Commits
            </Text>
            <Text style={styles.Informations}>
            Branches
            </Text>
        </View>


        <View style={styles.colomn}>
            <Text style={styles.Informations}>
            {Plicense}
            </Text>
            <Text style={styles.Informations}>
            {Pcommits}
            </Text>
            <Text style={styles.Informations}>
            {Pbranches}
            </Text>
        </View>
        
    </View>
            
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: '#babdc2',
    justifyContent: 'center',
  },

  Name: {
    color : 'black',
    fontSize : 28,
    fontWeight : 'bold',
    textAlign: 'center',
    marginBottom : 50,
  },

  Description: {
    color : 'black',
    fontSize : 18,
    textAlign: 'center',
    marginBottom : 50,
  },

  column: {
    flex : 1,
    flexDirection : 'column',
  },

  lign: {
    flexDirection : 'row',
    justifyContent : 'space-around',
  },

  Informations: {
    color : 'black',
    fontSize : 12,
    textAlign : 'center',
    marginBottom : 30,
    
  },
});
