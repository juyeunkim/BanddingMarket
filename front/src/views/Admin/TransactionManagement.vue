<template>
  <v-container>
    <!--user search-->
    <span class="ma-0 font-weight-light Do" style="font-size: 1.8em;">
      <v-icon style="color: black;">mdi-align-vertical-bottom</v-icon> 거래현황
    </span>

    <v-flex sm12 xs12 class="mt-4 outerFlex">
      <span>
        지역별 거래현황 입니다.
      </span>
      <v-divider class="mb-5 mt-1"></v-divider>
      <v-col sm="4" xs="5" style="margin-left: 5px;">
        <v-select
          :items="dropdown_date"
          menu-props="auto"
          label="기간별로 보기"
          hide-details
          v-model="mapTermOpt"
          @change="dateChangeMap()"
        ></v-select>
      </v-col>
      <div id="map" style="max-width: 100%; height: 400px;"></div>
    </v-flex>
    <br />
    <!--user search result-->
    <span class="ma-0 font-weight-light Do" style="font-size: 1.8em;">
      <v-icon style="color: black;">mdi-information</v-icon> 신고현황
    </span>

    <v-flex sm12 xs12 class="mt-4 outerFlex">
      <span>
        기간별 신고현황입니다.
      </span>
      <v-divider class="mb-5 mt-1"></v-divider>

      <div class="col justify-between row">
        <v-col sm="3" xs="4" style="margin-left: 5px;">
          <v-select
            :items="dropdown_date2"
            menu-props="auto"
            label="기간별로 보기"
            hide-details
            v-model="termopt"
            @change="dateChange()"
          ></v-select>
        </v-col>
        <v-col sm="3" xs="4" style="margin-left: 5px;"> </v-col>
      </div>

      <div sm="10" xs="10" style="height: 400px;">
        <template v-if="rendering">
          <line-chart :ds="propResult"></line-chart>
        </template>
      </div>
    </v-flex>
  </v-container>
</template>

<script>
import LineChart from "../../views/Chart/BarChart";
import axios from "../../vuex/http-common";

import Vue from "vue";
import moment from "moment";
import VueMomentJS from "vue-momentjs";

Vue.use(VueMomentJS, moment);

// import * as kakao from require( 'http://dapi.kakao.com/v2/maps/sdk.js?appkey=053dd3145f395e73cbb5211bedf3e97f&libraries=services,clusterer')
export default {
  components: {
    LineChart,
  },

  name: "Main",

  data: () => ({
    termopt: "",
    mapTermOpt: "",
    result: "",
    hasSaved: false,
    isEditing: null,
    model: null,
    search: null,
    rendering: false,
    map: "",
    propmsg: "default",
    propdata: "",
    reportList: [],
    reportDict: {},
    reportCnt: [],
    propResult: { label: "", data: "" },
    defaultlabels: [
      "January",
      "February",
      "March",
      "April",
      "May",
      "June",
      "July",
      "August",
      "September",
      "October",
      "November",
      "December",
    ],
    monthLabels: [],
    weekLabels: ["Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"],
    positions: [],
    dropdown_date: [
      { text: "전체보기" },
      { text: "연간" },
      { text: "월간" },
      { text: "주간" },
    ],
    dropdown_date2: [{ text: "연간" }, { text: "월간" }, { text: "주간" }],
  }),
  created() {
    this.loadData();
  },
  mounted() {
    if (!(window.kakao && window.kakao.maps)) this.addMapScript();
  },

  methods: {
    loadData() {
      console.log("Loading....");

      axios.get("/board").then(response => {
        console.log(response);
        this.positions = response.data.object;
        this.drawMap();
        this.initGraph();
      });
    },
    initGraph() {
      this.propResult = { label: "", data: "" };
      this.propmsg = "default";
      this.reportCnt = [];
      this.reportDict = {
        1: 0,
        2: 0,
        3: 0,
        4: 0,
        5: 0,
        6: 0,
        7: 0,
        8: 0,
        9: 0,
        10: 0,
        11: 0,
        12: 0,
      };
      axios
        .get("http://k02a3031.p.ssafy.io:8080/report/findByYear")
        .then(response => {
          this.reportList = response.data;
          for (var report of this.reportList) {
            var x = this.$moment(report.writeDate).format("MM");
            x = Number(x);
            this.reportDict[x] += 1;
          }

          for (var key in this.reportDict) {
            this.reportCnt.push(this.reportDict[key]);
          }
          console.log("dict: " + this.reportCnt);
          this.propResult["label"] = this.defaultlabels;
          this.propResult["data"] = this.reportCnt;

          // setTimeout(() => {
          this.rendering = true;
          // }, 10);
        });
    },
    drawMap() {
      var mapContainer = document.getElementById("map"); // 지도를 표시할 div
      var mapOption = {
        center: new kakao.maps.LatLng(37.5542135, 127.0016985), // 지도의 중심좌표 북위 37.5642135° 동경 127.0016985°
        level: 10, // 지도의 확대 레벨
      };

      this.map = new kakao.maps.Map(mapContainer, mapOption);
      //여기서 부터 객체넣어보자
      var clusterer = new kakao.maps.MarkerClusterer({
        map: this.map, // 마커들을 클러스터로 관리하고 표시할 지도 객체
        averageCenter: true, // 클러스터에 포함된 마커들의 평균 위치를 클러스터 마커 위치로 설정
        minLevel: 5, // 클러스터 할 최소 지도 레벨
        disableClickZoom: true, // 클러스터 마커를 클릭했을 때 지도가 확대되지 않도록 설정한다
      });
      var markers = [];
      console.log("positions 객체 출력");
      console.log(this.positions);
      for (var position of this.positions) {
        console.log(position.board_locationX + " " + position.board_locationY);
        if (
          position.board_locationX != null &&
          position.board_locationY != null
        ) {
          markers.push(
            new kakao.maps.Marker({
              position: new kakao.maps.LatLng(
                Number(position.board_locationY),
                Number(position.board_locationX)
              ),
            })
          );
        }
      }
      clusterer.addMarkers(markers);
    },
    dateChangeMap() {
      if (this.mapTermOpt == "연간") {
        console.log("map 연간");
        axios.get("/board/searchByYear").then(response => {
          console.log(response);
          this.positions = response.data.object;
          this.drawMap();
        });
      } else if (this.mapTermOpt == "월간") {
        console.log("map 월");
        axios.get("/board/searchByMonth").then(response => {
          console.log(response);
          this.positions = response.data.object;
          this.drawMap();
        });
      } else if (this.mapTermOpt == "주간") {
        console.log("map 주간");
        axios.get("/board/searchByWeek").then(response => {
          console.log(response);
          this.positions = response.data.object;
          this.drawMap();
        });
      }else if(this.mapTermOpt=="전체보기"){
        console.log("map 전체보기");
        axios.get("/board").then(response => {
          console.log(response);
          this.positions = response.data.object;
          this.drawMap();
        });
      }
    },
    addMapScript() {
      const script = document.createElement("script"); /* global kakao */
      script.onload = () => kakao.maps.load(this.initMap);
      script.src =
        // "//dapi.kakao.com/v2/maps/sdk.js?appkey=053dd3145f395e73cbb5211bedf3e97f&libraries=services,clusterer,drawing";
        document.head.appendChild(script);
    },
    customFilter(item, queryText) {
      const textOne = item.nickName.toLowerCase();
      const textTwo = item.addr.toLowerCase();
      const searchText = queryText.toLowerCase();
      return (
        textOne.indexOf(searchText) > -1 || textTwo.indexOf(searchText) > -1
      );
    },

    dateChange() {
      console.log("check event - dateChange() ");
      console.log(this.termopt);
      this.rendering = false;
      console.log("check: " + this.rendering);

      if (this.termopt == "연간") {
        this.propResult = { label: "", data: "" };
        this.propmsg = "default";
        this.reportCnt = [];
        this.reportDict = {
          1: 0,
          2: 0,
          3: 0,
          4: 0,
          5: 0,
          6: 0,
          7: 0,
          8: 0,
          9: 0,
          10: 0,
          11: 0,
          12: 0,
        };
        axios
          .get("http://k02a3031.p.ssafy.io:8080/report/findByYear")
          .then(response => {
            this.reportList = response.data;
            for (var report of this.reportList) {
              var x = this.$moment(report.writeDate).format("MM");
              x = Number(x);
              this.reportDict[x] += 1;
            }

            for (var key in this.reportDict) {
              this.reportCnt.push(this.reportDict[key]);
            }
            this.propResult["label"] = this.defaultlabels;
            this.propResult["data"] = this.reportCnt;

            // setTimeout(() => {
            this.rendering = true;
            // }, 10);
          });
      } else if (this.termopt == "월간") {
        this.propmsg = "month";
        this.propResult = { label: "", data: "" };
        this.monthLabels = [];
        for (var i = 1; i <= 31; i++) {
          this.monthLabels.push(i);
        }

        this.reportCnt = [];
        this.reportDict = {
          1: 0,
          2: 0,
          3: 0,
          4: 0,
          5: 0,
          6: 0,
          7: 0,
          8: 0,
          9: 0,
          10: 0,
          11: 0,
          12: 0,
          13: 0,
          14: 0,
          15: 0,
          16: 0,
          17: 0,
          18: 0,
          19: 0,
          20: 0,
          21: 0,
          22: 0,
          23: 0,
          24: 0,
          25: 0,
          26: 0,
          27: 0,
          28: 0,
          29: 0,
          30: 0,
          31: 0,
        };
        axios
          .get("http://k02a3031.p.ssafy.io:8080/report/findByMonth")
          .then(response => {
            // console.log(response);
            this.reportList = response.data;
            // console.log(this.reportList);
            for (var report of this.reportList) {
              var x = this.$moment(report.writeDate).format("DD");
              x = Number(x);
              this.reportDict[x] += 1;
            }

            console.log("dict: " + this.reportDict);
            for (var key in this.reportDict) {
              this.reportCnt.push(this.reportDict[key]);
            }
            console.log("dict: " + this.reportCnt);
            this.propResult["label"] = this.monthLabels;
            this.propResult["data"] = this.reportCnt;
            console.log(this.propResult);

            // setTimeout(() => {
            this.rendering = true;
            // }, 10);
          });
      } else if (this.termopt == "주간") {
        this.propmsg = "week";
        this.propResult = { label: "", data: "" };
        this.reportCnt = [];
        this.reportDict = {
          1: 0,
          2: 0,
          3: 0,
          4: 0,
          5: 0,
          6: 0,
          7: 0,
        };

        axios
          .get("http://k02a3031.p.ssafy.io:8080/report/findByWeek")
          .then(response => {
            this.reportList = response.data;
            console.log("주간: " + this.reportList);
            var j = 0;
            var defaultDate = 0;
            for (var report of this.reportList) {
              j++;

              var x = this.$moment(report.writeDate).format("DD");
              x = Number(x);
              console.log(x);

              if (j == 1) {
                defaultDate = x;
              }

              this.reportDict[x - defaultDate + 1] += 1;
            }

            console.log("dict: " + this.reportDict);
            for (var key in this.reportDict) {
              this.reportCnt.push(this.reportDict[key]);
            }
            console.log("dict: " + this.reportCnt);
            this.propResult["label"] = this.weekLabels;
            this.propResult["data"] = this.reportCnt;
            console.log(this.propResult);

            // setTimeout(() => {
            this.rendering = true;
            // }, 10);
          });
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
