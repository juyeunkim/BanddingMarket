<template>
  <v-container>
    <!--user search-->
    <span class="ma-0 font-weight-light Do" style="font-size: 1.8em;">
      <v-icon style="color:black">mdi-account-search</v-icon> 회원 이름 검색
    </span>

    <v-flex sm12 xs12 class="mt-4 outerFlex">
      <v-flex style="padding-left: 30px;">
        <v-btn icon @click="selectAll()">
          <v-icon style="color:black">mdi-folder-multiple-outline</v-icon>
           &nbsp; 전체보기
        </v-btn>
      </v-flex>
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
        <b style="color:orange">{{ userEmail }}</b
        >님의 신고목록입니다.
      </span>
      <v-divider class="mb-5 mt-1"></v-divider>

      <v-flex cols="6">
        <v-col cols="5" sm="5">
          <v-select
            :items="dropdown_edit"
            menu-props="auto"
            label="신고 카테고리"
            hide-details
            v-model="categoryOpt"
            @change="categoryChange()"
          ></v-select>
        </v-col>
      </v-flex>
      <div class="col justify-between row">
        <v-flex cols="6">
          <p class="ma-0 font-weight-light Do" style="color:orange">
            처리할 목록
          </p>
        </v-flex>
        <v-flex cols="6">
          <p class="ma-0 font-weight-light Do" style="color:orange">
            완료된 목록
          </p>
        </v-flex>
      </div>

      <div class="col justify-between row">
        <!-- 원본 -->

        <v-flex sm6 xs12 style="overflow:auto; height:500px;">
          <v-flex
            v-for="report in reportList"
            :key="report.id"
            style="margin-top:15px; margin-right:20px;"
          >
            <div v-if="userEmail == null">
              <div v-if="report.status == 0">
                <v-card color="#f7f1e3">
                  <v-card-title class="headline">{{
                    report.category
                  }}</v-card-title>
                  <v-card-subtitle>{{ report.context }}</v-card-subtitle>
                  <v-card-subtitle
                    >신고자: {{ report.writerEmail }} <br />
                    피신고자: {{ report.reportedEmail }}</v-card-subtitle
                  >
                  <v-flex style="padding-left: 240px;">
                    <v-btn icon @click="checkReport(report)">
                      <v-icon style="color:green">mdi-check-bold</v-icon>
                    </v-btn>

                    <v-btn icon @click="deleteReport(report)">
                      <v-icon style="color:red">mdi-close-circle</v-icon>
                    </v-btn>
                  </v-flex>
                </v-card>
              </div>
            </div>

            <div v-if="userEmail == report.writerEmail">
              <div v-if="report.status == 0">
                <v-card color="#f7f1e3">
                  <v-card-title class="headline">{{
                    report.category
                  }}</v-card-title>
                  <v-card-subtitle>{{ report.context }}</v-card-subtitle>
                  <v-card-subtitle
                    >신고자: {{ report.writerEmail }} <br />
                    피신고자: {{ report.reportedEmail }}</v-card-subtitle
                  >
                  <v-flex style="padding-left: 240px;">
                    <v-btn icon @click="checkReport(report)">
                      <v-icon style="color:green">mdi-check-bold</v-icon>
                    </v-btn>

                    <v-btn icon @click="deleteReport(report)">
                      <v-icon style="color:red">mdi-close-circle</v-icon>
                    </v-btn>
                  </v-flex>
                </v-card>
              </div>
            </div>
          </v-flex>
        </v-flex>

        <!-- 수정할곳 -->
        <v-flex sm6 xs12 style="overflow:auto; height:500px;">
          <v-flex
            v-for="report in reportList"
            :key="report.id"
            style="margin-top:15px; margin-right:20px; margin-left:20px;"
          >
            <div v-if="userEmail == null">
              <div v-if="report.status == 1">
                <v-card color="#F8EFBA">
                  <v-card-title class="headline">{{
                    report.category
                  }}</v-card-title>
                  <v-card-subtitle>{{ report.context }}</v-card-subtitle>
                  <v-card-subtitle
                    >신고자: {{ report.writerEmail }} 
                    <br/> 피신고자:
                    {{ report.reportedEmail }}</v-card-subtitle
                  >
                  <v-flex style="padding-left: 260px;">
                    <v-btn icon @click="cancelReport(report)">
                      <v-icon style="color:red">mdi-cancel</v-icon>
                    </v-btn>
                  </v-flex>
                </v-card>
              </div>
            </div>
            <div v-if="userEmail == report.writerEmail">
              <div v-if="report.status == 1">
                <v-card color="#F8EFBA">
                  <v-card-title class="headline">{{
                    report.category
                  }}</v-card-title>
                  <v-card-subtitle>{{ report.context }}</v-card-subtitle>
                  <v-card-subtitle
                    >신고자: {{ report.writerEmail }} <br
                    > 피신고자:
                    {{ report.reportedEmail }}</v-card-subtitle
                  >
                  <v-flex style="padding-left: 260px;">
                    <v-btn icon @click="cancelReport(report)">
                      <v-icon style="color:red">mdi-cancel</v-icon>
                    </v-btn>
                  </v-flex>
                </v-card>
              </div>
            </div>
          </v-flex>
        </v-flex>
      </div>
    </v-flex>
  </v-container>
</template>

<script>
import axios from "../../vuex/http-common";

// import HelloWorld from '../../components/HelloWorld.vue';

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
    categoryOpt: null,
    userEmail: null,
    dropdown_edit: [
      { text: "전체보기" },
      { text: "욕설" },
      { text: "광고" },
      { text: "미참석" },
      { text: "기타" },
    ],
    userList: [],
    reportList: [],
  }),
  created() {
    this.loadData();
  },
  methods: {
    customFilter(item, queryText) {
      const textOne = item.nickName.toLowerCase();
      const textTwo = item.addr.toLowerCase();
      const searchText = queryText.toLowerCase();
      return (
        textOne.indexOf(searchText) > -1 || textTwo.indexOf(searchText) > -1
      );
    },
    loadData() {
      console.log("Loading....");
      axios.get("/user/allUser").then(response => {
        // console.log(response);
        this.userList = response.data;
      });

      axios.get("/report/findAll").then(response => {
        console.log(response);
        this.reportList = response.data;
        console.log(this.reportList);
      });
    },

    checkReport(obj) {
      axios
        .put("/report/approve", { reportId: obj.reportId })
        .then(response => {
          console.log(response);
          if (this.categoryOpt == "전체보기" || this.categoryOpt == null) {
            axios.get("/report/findAll").then(response => {
              console.log(response);
              this.reportList = response.data;
              console.log(this.reportList);
            });
          } else {
            axios
              .get("/report/findByCategory/" + this.categoryOpt)
              .then(response => {
                console.log(response);
                this.reportList = response.data;
                console.log(this.reportList);
              });
          }
        });
    },
    deleteReport(obj) {
      axios.put("/report/deny", { reportId: obj.reportId }).then(response => {
        console.log(response);
        if (this.categoryOpt == "전체보기" || this.categoryOpt == null) {
          axios.get("/report/findAll").then(response => {
            console.log(response);
            this.reportList = response.data;
            console.log(this.reportList);
          });
        } else {
          axios
            .get("/report/findByCategory/" + this.categoryOpt)
            .then(response => {
              console.log(response);
              this.reportList = response.data;
              console.log(this.reportList);
            });
        }
      });
    },
    cancelReport(obj) {
      axios.put("/report/cancle", { reportId: obj.reportId }).then(response => {
        console.log(response);
        if (this.categoryOpt == "전체보기" || this.categoryOpt == null) {
          axios.get("/report/findAll").then(response => {
            console.log(response);
            this.reportList = response.data;
            console.log(this.reportList);
          });
        } else {
          axios
            .get("/report/findByCategory/" + this.categoryOpt)
            .then(response => {
              console.log(response);
              this.reportList = response.data;
              console.log(this.reportList);
            });
        }
      });
    },
    selectAll() {
      this.userEmail = null;
      this.search = null;
    },
    categoryChange() {
      console.log(this.categoryOpt);
      if (this.categoryOpt == "전체보기") {
        axios.get("/report/findAll").then(response => {
          console.log(response);
          this.reportList = response.data;
          console.log(this.reportList);
        });
      } else {
        axios
          .get("/report/findByCategory/" + this.categoryOpt)
          .then(response => {
            console.log(response);
            this.reportList = response.data;
            console.log(this.reportList);
          });
      }
    },
  },
  watch: {
    search(val) {
      // console.log(this.states)
      for (var user of this.userList) {
        //  console.log(state)
        if (user.email == val) {
          this.userEmail = user.email;
        }
      }
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
