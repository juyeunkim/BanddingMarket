<template>
  <v-container>
    <!--user search-->
    <span class="ma-0 font-weight-light Do" style="font-size: 1.8em;">
        <v-icon style="color:black">mdi-align-vertical-bottom</v-icon> 거래현황
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
        <v-icon style="color:black">mdi-information</v-icon> 검색 결과
    </span>
    
    <v-flex sm12 xs12 class="mt-4 outerFlex">
      
      <span>
       <b style="color:orange">{{result.nickName}}</b>님의 신고목록입니다.
      </span>
      <v-divider class="mb-5 mt-1"></v-divider>
      <div class="col justify-between row">
        <!-- 원본 -->
        <v-flex cols="6">
          <v-col cols="6" sm="6">
            <p class="ma-0 font-weight-light Do" style="color:orange">처리할 목록</p>

            <v-select
              :items="dropdown_edit"
              menu-props="auto"
              label="신고 카테고리"
              hide-details
            ></v-select>
          </v-col>


        </v-flex>
        <v-flex cols="6">
          <v-col cols="6" sm="6" style="margin-left:30px;">
            <p class="ma-0 font-weight-light Do" style="color:#F97F51">처리 완료</p>

            <v-select
              :items="dropdown_edit"
              menu-props="auto"
              label="신고 카테고리"
              hide-details
            ></v-select>
          </v-col>




        </v-flex>
        
        <v-flex sm6 xs12 style="overflow:auto; height:500px;" >
          
          


          <v-flex v-for = "report in reportList" :key="report.id" style="margin-top:15px; margin-right:20px;">
            <div v-if="report.status == 0">
              <v-card
                color="#f7f1e3"
                
              >
                <v-card-title class="headline" >{{report.category}}</v-card-title>
                <v-card-subtitle >{{report.context}}</v-card-subtitle>
                <v-card-subtitle >신고자: {{report.writer}}  | &nbsp; &nbsp; 피신고자: {{report.reported}}</v-card-subtitle>
                <v-flex style="padding-left: 240px;">
                  <v-btn
                        icon
                        @click="checkReport(report)"
                        
                  >
                        <v-icon style="color:green">mdi-check-bold</v-icon>
                  </v-btn>

                  <v-btn
                        icon
                        @click="deleteReport(report)"
                        
                  >
                        <v-icon style="color:red">mdi-close-circle</v-icon>
                  </v-btn>
                  
                </v-flex>
              </v-card>
            </div> 
          </v-flex>

        </v-flex>

        <!-- 수정할곳 -->
        <v-flex sm6 xs12 style="overflow:auto; height:500px;" >

          <v-flex v-for = "report in reportList" :key="report.id" style="margin-top:15px; margin-right:20px; margin-left:20px;">
            <div v-if="report.status == 1">
              <v-card
                color="#F8EFBA"
                
              >
                <v-card-title class="headline">{{report.category}}</v-card-title>
                <v-card-subtitle >{{report.context}}</v-card-subtitle>
                <v-card-subtitle >신고자: {{report.writer}}  | &nbsp; &nbsp; 피신고자: {{report.reported}}</v-card-subtitle>
                <v-flex style="padding-left: 260px;">
                <v-btn
                        icon
                        @click="cancelReport(report)"
                        
                  >
                        <v-icon style="color:red">mdi-cancel</v-icon>
                  </v-btn>
                </v-flex>
                
              </v-card>

            </div>
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
      dropdown_edit: [
        { text: '전체보기' },
        { text: '욕설' },
        { text: '광고' },
        { text: '미참석' },
        { text: '기타' },
      ],
      topUserList: [
        { nickName: 'jason07999', name:'이재혁', addr: '용인시', sel: 1, reputation:4.5, img:"https://user-images.githubusercontent.com/38865267/82810300-0c49ce00-9ec9-11ea-9b1d-114c80a200d4.jpg"},
        { nickName: 'wjg', name:'김주연',addr: '서울대입구', sel: 2, reputation:2.5, img:"https://user-images.githubusercontent.com/38865267/82821509-37d7b300-9edf-11ea-95c2-535856a38f6f.png" },
        { nickName: 'juheeekim', name:'김주희',addr: '사당', sel: 3, reputation:4.5, img:"https://user-images.githubusercontent.com/38865267/82821457-1ecf0200-9edf-11ea-966c-ec42d2771291.png" }
      ],
      userList: [
        { nickName: 'jason07999', name:'이재혁', addr: '용인시', id: 1, reputation:4.5, img:"https://user-images.githubusercontent.com/38865267/82810300-0c49ce00-9ec9-11ea-9b1d-114c80a200d4.jpg"  },
        { nickName: 'wjg', name:'김주연', addr: '서울대입구', id: 2, reputation:2.5, img:"https://user-images.githubusercontent.com/38865267/82821509-37d7b300-9edf-11ea-95c2-535856a38f6f.png"  },
        { nickName: 'juheeekim', name:'김주희', addr: '사당', id: 3, reputation:4.5, img:"https://user-images.githubusercontent.com/38865267/82821457-1ecf0200-9edf-11ea-966c-ec42d2771291.png"  },
        { nickName: 'taemin', name:'김태민',addr: '역삼역', id: 4, reputation:4.5, img:"https://user-images.githubusercontent.com/38865267/82821457-1ecf0200-9edf-11ea-966c-ec42d2771291.png"  },
        { nickName: 'hun0202', name:'이훈', addr: '서울시', id: 5, reputation:4.5, img:"https://user-images.githubusercontent.com/38865267/82821457-1ecf0200-9edf-11ea-966c-ec42d2771291.png"  },
      ],
      reportList: [
        { category:'욕설', context:'갑자기 부모욕하고 나갔어요',id:8,writer:'jason07999',reported:'wjg',status:1},
        { category:'욕설', context:'X발이라고 욕을..',id:1,writer:'jason07999',reported:'wjg', status:0},       
        { category:'미참석', context:'이 사용자.. 참석하지 않았어요',id:3,writer:'jason07999',reported:'wjg', status:0},
        { category:'욕설', context:'욕설 신고입니다.',id:4,writer:'jason07999',reported:'wjg', status:0},
        { category:'광고', context:'자기 가게홍보였네요',id:5,writer:'jason07999',reported:'wjg', status:1},
        { category:'광고', context:'알고보니 작성자가 음식점 주인입니다.',writer:'jason07999',reported:'wjg', id:6,status:1},
        { category:'미참석', context:'돈을 입금안하고 도망갔습니다.',writer:'jason07999',reported:'wjg', id:7,status:1},
        { category:'욕설', context:'갑자기 욕을 하네요',id:2,writer:'jason07999',reported:'wjg', status:1},
        
      ],
    }),
    methods: {
      customFilter (item, queryText) {
        const textOne = item.nickName.toLowerCase()
        const textTwo = item.addr.toLowerCase()
        const searchText = queryText.toLowerCase()
        return textOne.indexOf(searchText) > -1 ||
          textTwo.indexOf(searchText) > -1
      },
      checkReport(obj){
        console.log('check event')
        console.log(obj)
        obj.status=1
      },
      deleteReport(obj){
        console.log('delete event')
        console.log(obj)
        obj.status=2
      },
      cancelReport(obj){
        console.log('cancel event')
        console.log(obj)
        obj.status=0
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
