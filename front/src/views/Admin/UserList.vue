<template>
  <v-container>
    <!--top user search-->

    <v-flex sm12 xs12>
      <span class="ma-0 font-weight-light Do" style="font-size: 1.8em;">
        <v-icon style="color:black">mdi-trophy-variant</v-icon> TOP USER
      </span>

      <v-simple-table class="ma-3">
        <template v-slot:default>
          <div class="col justify-between row text-center">
            <v-flex
              sm3
              xs9
              v-for="user in topUserList"
              :key="user.nickname"
              style="margin-left:50px;"
            >
              <!-- <img src={{ episode.thumbnail }}> 실제할땐 요런식으로  -->
              <v-flex>
                <v-img
                  :src="user.img"
                  :aspect-ratio="1 / 1"
                  style="border-radius:50%;"
                  class="ma-3"
                ></v-img>
                <tbody sm4 xs4 class="col">
                  <tr>
                    <td colspan="2" style="font-size: 1.3em;">
                      {{ user.nickname }}
                    </td>
                  </tr>
                  <tr>
                    <td>이름: {{ user.name }}</td>
                  </tr>
                  <tr>
                    <td>평점: {{ user.reputation }}</td>
                  </tr>
                  <tr>
                    <td>누적 게시글: {{ user.sel }}</td>
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
      <v-icon style="color:black">mdi-account-search</v-icon> 회원 이메일 검색
    </span>

    <v-flex sm12 xs12 class="mt-4 outerFlex">
      <v-tabs background-color="transparent" center-active height="auto">
        <v-autocomplete
          :items="userList"
          color="white"
          item-text="email"
          label="User"
          :search-input.sync="search"
        ></v-autocomplete>
      </v-tabs>
    </v-flex>
    <br />
    <!--user search result-->
    <span class="ma-0 font-weight-light Do" style="font-size: 1.8em;">
      <v-icon style="color:black">mdi-information</v-icon> 검색 결과
    </span>

    <v-flex sm12 xs12 class="mt-4 outerFlex">
      <span>
        <b style="color:orange">{{ result.email }}</b
        >님의 정보입니다.
      </span>
      <v-divider class="mb-5 mt-1"></v-divider>
      <div class="col justify-between row">
        <v-flex sm6 xs12>
          <v-tabs background-color="transparent" center-active height="auto">
            <ul>
              <li>닉네임: {{ result.nickname }}</li>
              <li>이름: {{ result.name }}</li>
              <li>주소: {{ result.addr }}</li>
              <li>평점: {{ result.reputation }}</li>
            </ul>
          </v-tabs>
        </v-flex>
        <v-flex sm6 xs12 style="overflow:auto; height:200px;">
          <v-flex
            v-for="board in boardList"
            :key="board.id"
            style="margin-top:15px; margin-right:20px;"
          >
            <v-card color="#E6E6E6">
              <v-card-title class="headline">{{ board.title }}</v-card-title>
              <v-card-subtitle>{{ board.context }}</v-card-subtitle>
            </v-card>
          </v-flex>
        </v-flex>
      </div>
    </v-flex>
  </v-container>
</template>

<script>
// import HelloWorld from '../../components/HelloWorld.vue';
import axios from '../../vuex/http-common';
export default {
  name: "Main",

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
     
    ],
    userList: [
      
    ],
    boardList: [
      
    ],
  }),
  created() {
    this.loadData();
    
  },

  methods: {
    customFilter(item, queryText) {
      const textOne = item.nickname.toLowerCase();
      const textTwo = item.addr.toLowerCase();
      const searchText = queryText.toLowerCase();
      return (
        textOne.indexOf(searchText) > -1 || textTwo.indexOf(searchText) > -1
      );
    },
    loadData() {
      console.log("Loading....");

      axios.get('/user/topUser').then((response) =>{
        // console.log(response);
        this.topUserList=response.data;

      })

      


      axios.get('/user/allUser').then((response) =>{
        // console.log(response);
        this.userList=response.data;

      })
    },
  },
  watch: {
    search(val) {
      // console.log(this.states)
      for (var user of this.userList) {
        //  console.log(state)
        if (user.email == val) {
          this.result = user;
        }
      }
      console.log(this.result.user_id)
      axios.post('/board/searchById',this.result.user_id).then((response) =>{
        console.log(response);
        this.boardList=response.data.object;

      })
    },
  },
};
</script>

<style scoped>
@import url("https://fonts.googleapis.com/css2?family=Do+Hyeon&display=swap");

.v-card--reveal {
  align-items: center;
  bottom: 0;
  justify-content: center;
  opacity: 0.7;
  position: absolute;
  width: 100%;
}
.Do {
  font-family: "Do Hyeon", sans-serif;
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
  margin-left: 1em;
  margin-right: 1em;
  padding-left: 1em;
  padding-right: 1em;
}

.mobilereviewCard {
  margin-bottom: 1em;
  padding-left: 1em;
  padding-right: 1em;
  padding-bottom: 1em;
}
.container {
  border-style: solid;
  border-color: #82b1ff;
  border-radius: 20px;
  border-width: 8px;
  background-color: white;
  padding-top: 0.5em;
  margin-top: 1.5em;
  max-width: 800px;
}

.outerFlex {
  background: #dffbff;
  border-radius: 20px;
  padding: 10px;
  margin-top: 5px;
  margin-bottom: 5px;
}
</style>
