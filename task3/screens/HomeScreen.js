
import { StyleSheet, Button, TouchableWithoutFeedback, View, ScrollView } from 'react-native';

import React from 'react';
import type {Node} from 'react';
import {
  SafeAreaView,
  StatusBar,
  Text,
  useColorScheme,
} from 'react-native';

import {
  Colors,
  DebugInstructions,
  Header,
  LearnMoreLinks,
  ReloadInstructions,
} from 'react-native/Libraries/NewAppScreen';
import { ApolloClient, ApolloProvider, InMemoryCache, gql, useQuery, selectHttpOptionsAndBodyInternal, } from '@apollo/client';
import { SelectList } from 'react-native-dropdown-select-list'

request = "";
setQuery("*");
GET_REPO = gql (request);

const languages = [
    {key:'*', value:'Top Overall'},
    {key:'javascript', value:'JavaScript'},
    {key:'typescript', value:'TypeScript'},
    {key:'go', value:'Go'},
    {key:'rust', value:'Rust'},
    {key:'swift', value:'Swift'},
    {key:'web', value:'Web'},
    {key:'php', value:'PHP'},
    {key:'css', value:'CSS'},
    {key:'c', value:'C'},
    {key:'c#', value:'C#'},
    {key:'c++', value:'C++'},
    {key:'python', value:'Python'},
    {key:'ruby', value:'Ruby'},
    {key:'java', value:'Java'},
]

let Navigation;

function setQuery (key)
{
    request = `
query {
    search(query: "is:public created:>2023-01-01 sort:stars language:`+key+`", type: REPOSITORY, first: 50) {
      repositoryCount
      edges {
        node {
          ... on Repository {
            name
            url
            description
            forkCount
            nameWithOwner
            stargazerCount
            licenseInfo {
              name
            }
            object(expression:"main")
            {
              ... on Commit{
                history{
                  totalCount
                }
              }
            }
            
            refs(first: 100, refPrefix:"refs/heads/"){
              totalCount
            }
            
          }
        }
      }
    }
  }
`;
}





const App: () => Node = () => {


    
  const [selected, setSelected] = React.useState("");

  setQuery(selected);
  GET_REPO = gql (request);
  const{data, loading, error} = useQuery(GET_REPO);
  

  const first = data?.search.repositoryCount;



  
  
  


  let projectList = [];





  let projectNames = [];
  let projectNamesWithOwner = [];
  let projectDescriptions = [];
  let projectForks = [];
  let projectStars = [];
  let projectLicenses = [];
  let projectsCommits = [];
  let projectBranches = [];


  for (let j=0; j<50; j++)
  {
    projectNames.push(JSON.stringify(data?.search.edges[j].node.name)?.replace(/"/g, '')||'');
    projectNamesWithOwner.push(JSON.stringify(data?.search.edges[j].node.nameWithOwner)?.replace(/"/g, '')||'');
    projectDescriptions.push(JSON.stringify(data?.search.edges[j].node.description)?.replace(/"/g, '')||'');
    projectForks.push(JSON.stringify(data?.search.edges[j].node.forkCount)?.replace(/"/g, '')||'');
    projectStars.push(JSON.stringify(data?.search.edges[j].node.stargazerCount)?.replace(/"/g, '')||'');
    
    projectBranches.push(JSON.stringify(data?.search.edges[j].node.refs.totalCount)?.replace(/"/g, '')||'');


    if(JSON.stringify(data?.search.edges[j].node.licenseInfo) == "null")
    {
        projectLicenses.push("");
    }
    else
    {
        projectLicenses.push(JSON.stringify(data?.search.edges[j].node.licenseInfo.name)?.replace(/"/g, '')||'');
    }





    if(JSON.stringify(data?.search.edges[j].node.object) == "null")
    {
        projectsCommits.push("");
    }
    else
    {
        projectsCommits.push(JSON.stringify(data?.search.edges[j].node.object.history.totalCount)?.replace(/"/g, '')||'');
    }
    
  }


  for (let j=0; j<50; j++)
  {
    projectList.push(
        <TouchableWithoutFeedback onPress={() => Navigation.navigate("ProjectScreen", {Pname : projectNames[j], Pdescription : projectDescriptions[j], Plicense : projectLicenses[j], Pcommits : projectsCommits[j], Pbranches : projectBranches[j]})}>
        <View style={styles.container}>
                <Text style={styles.Name}>
                    {projectNames[j]}
                </Text>
                <Text style={styles.NameWithOwner}>
                    {projectNamesWithOwner[j]}
                </Text>
                <Text style={styles.Description}>
                    {projectDescriptions[j]}
                </Text>

                <View style={styles.stat}>
                    <View style={styles.boxstars}>
                        <Text style={styles.stars}>
                            Stars : {projectStars[j]}
                        </Text>
                    </View>
                    <View style={styles.boxfork}>
                        <Text style={styles.fork}>
                            Forks : {projectForks[j]}
                        </Text>
                    </View>
                </View>
        </View>
        </TouchableWithoutFeedback>
    )
  }





  return (
    <View style={styles.homeBG}>
        <SelectList 
            setSelected={(val) => setSelected(val)} 
            data={languages} 
            save="key"
            boxStyles={styles.selectList}
            inputStyles={styles.selectFont}
            placeholder="Language"
            dropdownStyles={styles.dropList}
            dropdownTextStyles={styles.dropFont}
        />
        <ScrollView>
            {projectList}
        </ScrollView> 
    </View>
      
  );
};











const styles = StyleSheet.create({
    

  container: {
    flexDirection: 'column',
    backgroundColor: '#57595c',
    margin : 10,
    paddingLeft: 10,
    paddingTop : 5,
  },

  homeBG: {
    backgroundColor: '#1a1a1a',
  },


  Name : {
    color : '#b8bab3',
    fontSize : 20,
    fontWeight : 'bold',
  },

  NameWithOwner : {
    color : '#b8bab3',
    fontSize : 12,
    marginBottom : 10,
  },

  Description : {
    color : '#b8bab3',
    fontSize : 12,
    fontWeight : 'bold',
    marginBottom : 10,
  },



  stat: {
    flexDirection: 'row-reverse',
  },

  boxfork:
  {
    backgroundColor: '#2b2b29',
    width : 80,
  },

  fork:
  {
    textAlign: 'center',
    color : '#b8bab3',
    fontSize : 12,
    fontWeight : 'bold',
  },

  boxstars:
  {
    backgroundColor: '#bad667',
    width : 80,
  },

  stars:
  {
    textAlign: 'center',
    color : 'black',
    fontSize : 12,
    fontWeight : 'bold',
  },

  selectList:
  {
    backgroundColor: '#677391'
  },

  selectFont:
  {
    color : '#b8bab3',
    fontSize : 18,
    fontWeight : 'bold',
  },

  selectList:
  {
    backgroundColor: '#677391'
  },

  dropFont:
  {
    color : '#b8bab3',
    fontSize : 18,
    fontWeight : 'bold',
  },

  dropList:
  {
    backgroundColor: '#677391'
  },


  

});










































const client = new ApolloClient({
  uri: 'https://api.github.com/graphql',
  headers: {
    authorization: `Bearer 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`,
  },
  cache: new InMemoryCache(),

});


export default function HomeScreen({ navigation })
{
    Navigation = navigation;
  return(
    <ApolloProvider client={client}>
    <App />
  </ApolloProvider>

  );
}