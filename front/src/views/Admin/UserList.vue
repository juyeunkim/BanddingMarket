<template>
  <v-container>

  <!--top user search-->



    <v-flex sm12 xs12 >
      <span class="ma-0 font-weight-light Do" style="font-size: 1.8em;">
        <v-icon color='red'>fas fa-search</v-icon> TOP USER
    </span>

      <v-simple-table class="ma-3" >
        <template v-slot:default>
          <div class="col justify-between row text-center">
          <v-flex sm3 xs9 v-for="user in topUserList" :key="user.nickName" style="margin-left:50px;" >
            <!-- <img src={{ episode.thumbnail }}> 실제할땐 요런식으로  -->
            <v-flex>
              <v-img :src=user.img :aspect-ratio="1 / 1" style="border-radius:50%;"  class="ma-3"></v-img>
              <tbody sm4 xs4 class="col" >
                <tr>
                  <td colspan="2" style="font-size: 1.3em;">
                    {{user.nickName}}
                  </td>
                </tr>
                <tr>
                  <td>
                    이름: {{user.name}}
                  </td>
                </tr>
                <tr>
                  <td>
                    평점: {{user.reputation}}
                  </td>
                </tr>
                <tr>
                  <td>
                  누적 게시글: {{user.sel}}
                  </td>
                </tr>
              </tbody>           
            </v-flex>


          </v-flex>
          </div>
          


          
        </template>

      </v-simple-table>
    </v-flex>
    <!--user search-->
    <span class="ma-0 font-weight-light Do" style="font-size: 1.8em;">
        <v-icon color='red'>fas fa-search</v-icon> 회원 이름 검색
    </span>

    <v-flex sm12 xs12 class="mt-4 outerFlex">
      
      <v-tabs
        background-color="transparent"
        center-active
        height="auto"
        >
      <v-autocomplete
        :items="userList"
        :filter="customFilter"
        color="white"
        item-text="nickName"
        label="User"
       :search-input.sync="search"
      ></v-autocomplete>  
        
      </v-tabs>
    </v-flex>
    <br/>
  <!--user search result-->
    <span class="ma-0 font-weight-light Do" style="font-size: 1.8em;">
        <v-icon color='red'>fas fa-search</v-icon> 검색 결과
    </span>
    
    <v-flex sm12 xs12 class="mt-4 outerFlex">
      
      <span>
       <b style="color:orange">{{result.nickName}}</b>님의 정보입니다.
      </span>
      <v-divider class="mb-5 mt-1"></v-divider>
      <div class="col justify-between row">
        <v-flex sm6 xs12>
          <v-tabs
            background-color="transparent"
            center-active
            height="auto"
            >
          
            <ul>
              <li>닉네임: {{result.nickName}}</li>
              <li>이름: {{result.name}}</li>
              <li>주소: {{result.addr}}</li>
              <li>평점: {{result.reputation}}</li>

            </ul>
          </v-tabs>
        </v-flex>
        <v-flex sm6 xs12 style="overflow:auto; height:200px;" >
          <v-flex v-for = "board in boardList" :key="board.id" style="margin-top:15px; margin-right:20px;">

           <v-card
            color="#E6E6E6"
            
          >
            <v-card-title class="headline" >{{board.title}}</v-card-title>
            <v-card-subtitle >{{board.context}}</v-card-subtitle>

            
          </v-card>
          </v-flex>

        </v-flex>


      </div>


    </v-flex>






  </v-container>

  
</template>

<script>
// import HelloWorld from '../../components/HelloWorld.vue';

export default {
  name: 'Main',

  components: {
    // HelloWorld,
  },
      
  data: () => ({
      result: "",
      hasSaved: false,
      isEditing: null,
      model: null,
      search: null,
      topUserList: [
        { nickName: 'jason07999', name:'이재혁', addr: '용인시', sel: 1, reputation:4.5, img:"https://user-images.githubusercontent.com/38865267/82810300-0c49ce00-9ec9-11ea-9b1d-114c80a200d4.jpg"},
        { nickName: 'wjg', name:'김주연',addr: '서울대입구', sel: 2, reputation:4.5, img:"https://user-images.githubusercontent.com/38865267/82821509-37d7b300-9edf-11ea-95c2-535856a38f6f.png" },
        { nickName: 'juheeekim', name:'김주희',addr: '사당', sel: 3, reputation:4.5, img:"https://user-images.githubusercontent.com/38865267/82821457-1ecf0200-9edf-11ea-966c-ec42d2771291.png" }
      ],
      userList: [
        { nickName: 'jason07999', name:'이재혁', addr: '용인시', id: 1, reputation:4.5, img:"https://user-images.githubusercontent.com/38865267/82810300-0c49ce00-9ec9-11ea-9b1d-114c80a200d4.jpg"  },
        { nickName: 'wjg', name:'김주연', addr: '서울대입구', id: 2, reputation:4.5, img:"https://user-images.githubusercontent.com/38865267/82821509-37d7b300-9edf-11ea-95c2-535856a38f6f.png"  },
        { nickName: 'juheeekim', name:'김주희', addr: '사당', id: 3, reputation:4.5, img:"https://user-images.githubusercontent.com/38865267/82821457-1ecf0200-9edf-11ea-966c-ec42d2771291.png"  },
        { nickName: 'taemin', name:'김태민',addr: '역삼역', id: 4, reputation:4.5, img:"https://user-images.githubusercontent.com/38865267/82821457-1ecf0200-9edf-11ea-966c-ec42d2771291.png"  },
        { nickName: 'hun0202', name:'이훈', addr: '서울시', id: 5, reputation:4.5, img:"https://user-images.githubusercontent.com/38865267/82821457-1ecf0200-9edf-11ea-966c-ec42d2771291.png"  },
      ],
      boardList: [
        { title:'치킨시켜드실분', context:'치킨멤버구함',id:1},
        { title:'엽떡반반나눠요~', context:'순한맛드실분...?',id:2},
        { title:'쿠팡에서 감자구매', context:'-',id:3},
        { title:'피자 반반씩 시킬분있나요', context:'...',id:4},
      ]
    }),
    methods: {
      customFilter (item, queryText) {
        const textOne = item.nickName.toLowerCase()
        const textTwo = item.addr.toLowerCase()
        const searchText = queryText.toLowerCase()
        return textOne.indexOf(searchText) > -1 ||
          textTwo.indexOf(searchText) > -1
      },
    },
    watch: {
      search (val) {
     
        // console.log(this.states)
         for(var user of this.userList){
          //  console.log(state)
           if(user.nickName == val){
             this.result=user
           }
         } 
       
      },
    },  




  }




  
</script>

<style scoped>
@import url('https://fonts.googleapis.com/css2?family=Do+Hyeon&display=swap');

.v-card--reveal {
  align-items: center;
  bottom: 0;
  justify-content: center;
  opacity: 0.7;
  position: absolute;
  width: 100%;
}
.Do {
  font-family: 'Do Hyeon', sans-serif;
}
.reviewContent {
  text-align: -webkit-center; 
  border-radius: 10px; 
  background-color: #ededed; 
  border: 4px dashed #bdb5bd;
}

.mobilereviewContent {
  text-align: -webkit-center; 
  border-radius: 10px; 
  background-color: #ededed; 
  border: 2px dashed #bdb5bd;
}

.reviewCard {
  margin-bottom: 1em;
  margin-left:1em;
  margin-right:1em;
  padding-left: 1em;
  padding-right: 1em;
}

.mobilereviewCard {
  margin-bottom: 1em;
  padding-left: 1em;
  padding-right: 1em;
  padding-bottom: 1em;
}
.container{
  border-style: solid;
  border-color: #82b1ff;
  border-radius: 20px;
  border-width: 8px;
  background-color: white; 
  padding-top: 0.5em; 
  margin-top: 1.5em; 
  max-width : 800px
}

.outerFlex {
  background: #dffbff;
  border-radius: 20px;
  padding: 10px;
  margin-top: 5px;
  margin-bottom: 5px;
}


</style>
