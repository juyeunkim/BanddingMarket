<template>
  <v-container>
    <!--user search-->
    <span class="ma-0 font-weight-light Do" style="font-size: 1.8em;">
      <v-icon style="color:black">mdi-align-vertical-bottom</v-icon> 거래현황
    </span>

    <v-flex sm12 xs12 class="mt-4 outerFlex">
      <span>
        지역별 거래현황 입니다.
      </span>
      <v-divider class="mb-5 mt-1"></v-divider>
      <v-col sm="4" xs="5" style="margin-left:5px;">
        <v-select
          :items="dropdown_date"
          menu-props="auto"
          label="기간별로 보기"
          hide-details
        ></v-select>
      </v-col>
      <div id="map" style="max-width: 100%; height:400px;"></div>
    </v-flex>
    <br />
    <!--user search result-->
    <span class="ma-0 font-weight-light Do" style="font-size: 1.8em;">
      <v-icon style="color:black">mdi-information</v-icon> 신고현황
    </span>

    <v-flex sm12 xs12 class="mt-4 outerFlex">
      <span>
        기간별 신고현황입니다.
      </span>
      <v-divider class="mb-5 mt-1"></v-divider>

      <div class="col justify-between row">
        <v-col sm="3" xs="4" style="margin-left:5px;">
          <v-select
            :items="dropdown_date2"
            menu-props="auto"
            label="기간별로 보기"
            hide-details
            v-model="termopt"
            @change="dateChange()"
          ></v-select>
        </v-col>
        <v-col sm="3" xs="4" style="margin-left:5px;">
          <v-select
            :items="dropdown_edit"
            menu-props="auto"
            label="카테고리별로 보기"
            hide-details
            v-model="categoryopt"
          ></v-select>
        </v-col>
      </div>

      <div sm="10" xs="10" style="height:400px">
        <template v-if="rendering">
          <template v-if="propmsg == 'default'">
            <line-chart :ds="defaultlabels"></line-chart>
          </template>
          <template v-if="propmsg == 'month'">
            <line-chart :ds="monthLabels"></line-chart>
          </template>
          <template v-if="propmsg == 'week'">
            <line-chart :ds="weekLabels"></line-chart>
          </template>
        </template>
      </div>
    </v-flex>
  </v-container>
</template>

<script>
import LineChart from "../../views/Chart/BarChart";
import axios from "../../vuex/http-common";

// import * as kakao from require( 'http://dapi.kakao.com/v2/maps/sdk.js?appkey=053dd3145f395e73cbb5211bedf3e97f&libraries=services,clusterer')
export default {
  components: {
    LineChart,
  },

  name: "Main",

  data: () => ({
    termopt: "",
    categoryopt: "",
    result: "",
    hasSaved: false,
    isEditing: null,
    model: null,
    search: null,
    rendering: true,
    map: "",
    propmsg: "default",
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
    monthLabels: ["1st week", "2nd week", "3rd week", "4th week", "5th week"],
    weekLabels: ["Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"],
    positions: [],
    dropdown_date: [
      { text: "전체보기" },
      { text: "연간" },
      { text: "월간" },
      { text: "주간" },
    ],
    dropdown_date2: [{ text: "연간" }, { text: "월간" }, { text: "주간" }],
    dropdown_edit: [
      { text: "전체보기" },
      { text: "욕설" },
      { text: "광고" },
      { text: "미참석" },
      { text: "기타" },
    ],
  }),
  created() {
    this.loadData();
  },
  mounted() {
    if (!(window.kakao && window.kakao.maps)) this.addMapScript();

    // this.propmsg=this.defaultlabels;
    //filterAreaHeight
    // this.filterAreaHeight = document.getElementById('filterArea').offsetHeight
    // console.log(this.filterAreaHeight)
  },

  methods: {
    loadData() {
      console.log("Loading....");

      axios.get("/board").then(response => {
        console.log(response);
        this.positions = response.data.object;
        this.drawMap();
      });
    },

    drawMap() {
      var mapContainer = document.getElementById("map"); // 지도를 표시할 div
      var mapOption = {
        center: new kakao.maps.LatLng(37.5642135, 127.0016985), // 지도의 중심좌표 북위 37.5642135° 동경 127.0016985°
        level: 9, // 지도의 확대 레벨
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
        this.propmsg = "default";
      } else if (this.termopt == "월간") {
        this.propmsg = "month";
      } else if (this.termopt == "주간") {
        this.propmsg = "week";
      }

      setTimeout(() => {
        this.rendering = true;
      }, 1);
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
