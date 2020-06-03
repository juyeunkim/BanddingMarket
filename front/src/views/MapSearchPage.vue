<template>
  <v-container fill-height class="fullpage-container" fluid>
    <v-layout wrap class="ma-0">
      <v-flex sm9 xs12>
        <div
          id="map"
          v-bind:style="{
            width: '100%',
            height: windowHeight - headerHeight + 'px',
          }"
        ></div>
      </v-flex>
      <v-flex sm3 hidden-xs-only>
        <v-card
          v-bind:style="{
            width: '100%',
            height: windowHeight - 64 + 'px',
            overflow: 'auto',
          }"
        >
          <v-container id="filterArea" class="pa-0" style="">
            <v-card-title class="headline">검색 필터</v-card-title>
            <v-divider></v-divider>

            <!--카테고리 category-->
            <v-card-title class="pb-0">
              카테고리
            </v-card-title>
            <v-card-title class="py-0">
              <v-row justify-center>
                <v-col cols="6" class="selectButtonArea pa-1">
                  <v-btn
                    style="width: -webkit-fill-available;"
                    @click="categoryButtonClicked('food')"
                    :color="categoryButtonColor('food')"
                    >음식</v-btn
                  >
                </v-col>
                <v-col cols="6" class="selectButtonArea pa-1">
                  <v-btn
                    style="width: -webkit-fill-available;"
                    @click="categoryButtonClicked('delivery')"
                    :color="categoryButtonColor('delivery')"
                    >택배</v-btn
                  >
                </v-col>
              </v-row>
            </v-card-title>
            <v-divider></v-divider>

            <!--거리 distance-->
            <v-card-title class="pb-0">
              거리
            </v-card-title>
            <v-card-title class="py-0">
              <v-row justify-center>
                <v-col
                  cols="4"
                  class="selectButtonArea pa-1"
                  v-for="(dis, index) in distances"
                  :key="'distance' + index"
                >
                  <v-btn
                    style="width: -webkit-fill-available;"
                    @click="distanceButtonClicked(dis)"
                    :color="distanceButtonColor(dis)"
                    >{{ dis }}M</v-btn
                  >
                </v-col>
              </v-row>
            </v-card-title>
          </v-container>

          <v-container
            class="pa-0 mt-3"
            v-bind:style="{
              overflow: 'auto',
              height:
                windowHeight - headerHeight - filterAreaHeight - 12 + 'px',
            }"
          >
            <v-list style="width:100%">
              <v-list-item
                v-for="(search, index) in searchList"
                :key="index + 'store'"
                class="pa-0"
              >
                <v-hover v-slot:default="{ hover }" open-delay="50">
                  <v-card
                    flat
                    :color="hover ? '#B2CCFF' : '#EAEAEA'"
                    class="ma-1 px-3"
                    style="width:100%"
                  >
                    <v-card-text class="subtitle-1 font-weight-bold pa-0 pt-1">
                      {{ search }}
                    </v-card-text>
                  </v-card>
                </v-hover>
              </v-list-item>
            </v-list>
          </v-container>
        </v-card>
      </v-flex>
    </v-layout>
    <!-- 모바일 떠다니는 버튼 -->
    <v-btn
      fab
      dark
      fixed
      bottom
      right
      color="blue --lighten1"
      class="d-flex d-sm-none"
      @click="filterDialog = true"
    >
      <v-icon>mdi-view-headline</v-icon>
    </v-btn>

    <!-- filter dialog -->
    <v-dialog v-model="filterDialog" max-width="290">
      <v-card>
        <v-card-title class="headline">검색 필터</v-card-title>
        <v-divider></v-divider>

        <!--카테고리 category-->
        <v-card-title style="">
          카테고리
        </v-card-title>
        <v-card-title class="py-0">
          <v-row justify-center>
            <v-col cols="6" class="selectButtonArea pa-1">
              <v-btn
                style="width: -webkit-fill-available;"
                @click="categoryButtonClicked('food')"
                :color="categoryButtonColor('food')"
                >음식</v-btn
              >
            </v-col>
            <v-col cols="6" class="selectButtonArea pa-1">
              <v-btn
                style="width: -webkit-fill-available;"
                @click="categoryButtonClicked('delivery')"
                :color="categoryButtonColor('delivery')"
                >택배</v-btn
              >
            </v-col>
          </v-row>
        </v-card-title>
        <v-divider></v-divider>

        <!--거리 distance-->
        <v-card-title style="">
          거리
        </v-card-title>
        <v-card-title class="py-0">
          <v-row justify-center>
            <v-col
              cols="4"
              class="selectButtonArea pa-1"
              v-for="(dis, index) in distances"
              :key="'distance' + index"
            >
              <v-btn
                style="width: -webkit-fill-available;"
                @click="distanceButtonClicked(dis)"
                :color="distanceButtonColor(dis)"
                >{{ dis }}M</v-btn
              >
            </v-col>
          </v-row>
        </v-card-title>

        <v-card-actions>
          <v-spacer></v-spacer>

          <v-btn color="primary" text @click="filterDialog = false">
            닫기
          </v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
  </v-container>
</template>

<script>
import { mapState } from "vuex";
var MARKER_WIDTH = 33, // 기본, 클릭 마커의 너비
  MARKER_HEIGHT = 36, // 기본, 클릭 마커의 높이
  OFFSET_X = 12, // 기본, 클릭 마커의 기준 X좌표
  OFFSET_Y = MARKER_HEIGHT, // 기본, 클릭 마커의 기준 Y좌표
  OVER_MARKER_WIDTH = 40, // 오버 마커의 너비
  OVER_MARKER_HEIGHT = 42, // 오버 마커의 높이
  OVER_OFFSET_X = 13, // 오버 마커의 기준 X좌표
  OVER_OFFSET_Y = OVER_MARKER_HEIGHT, // 오버 마커의 기준 Y좌표
  SPRITE_MARKER_URL =
    "https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/markers_sprites2.png", // 스프라이트 마커 이미지 URL
  SPRITE_WIDTH = 126, // 스프라이트 이미지 너비
  SPRITE_HEIGHT = 146, // 스프라이트 이미지 높이
  SPRITE_GAP = 10; // 스프라이트 이미지에서 마커간 간격

var markerSize;
var markerOffset;
var overMarkerSize;
var overMarkerOffset;
var spriteImageSize;
export default {
  name: "MapSearchPage",
  components: {},
  data: () => ({
    map: null,
    refreshFlag: false,
    filterDialog: false,
    categoryFood: true,
    categoryDelivery: true,
    distance: 100,
    distances: [100, 200, 300],
    windowHeight: 100,
    filterAreaHeight: 100,
    headerHeight: 64,
    positions: [
      { name: "a", latitude: 37.4954257, longitude: 127.039 },
      { name: "b", latitude: 37.4954257, longitude: 127.04 },
      { name: "c", latitude: 37.4954257, longitude: 127.041 },
    ],
    selectedMarker: null,
  }),
  created() {
    if (!(window.kakao && window.kakao.maps && window.kakao.services))
      this.addMapScript();
    if (!window.kakao.services) this.addServiceScript();

    // console.log(window.kakao.services)

    this.windowHeight = window.innerHeight;
    window.addEventListener("resize", () => {
      this.windowHeight = window.innerHeight;
      // console.log(this.windowHeight)
    });

    markerSize = new kakao.maps.Size(MARKER_WIDTH, MARKER_HEIGHT); // 기본, 클릭 마커의 크기
    markerOffset = new kakao.maps.Point(OFFSET_X, OFFSET_Y); // 기본, 클릭 마커의 기준좌표
    overMarkerSize = new kakao.maps.Size(OVER_MARKER_WIDTH, OVER_MARKER_HEIGHT); // 오버 마커의 크기
    overMarkerOffset = new kakao.maps.Point(OVER_OFFSET_X, OVER_OFFSET_Y); // 오버 마커의 기준 좌표
    spriteImageSize = new kakao.maps.Size(SPRITE_WIDTH, SPRITE_HEIGHT); // 스프라이트 이미지의 크기
  },
  mounted() {
    this.drawMap();
    //filterAreaHeight
    this.filterAreaHeight = document.getElementById("filterArea").offsetHeight;
    // console.log(this.filterAreaHeight)
  },
  computed: {
    ...mapState(["searchList"]),
  },
  methods: {
    addMapScript() {
      console.log("ddd");
      const script = document.createElement("script"); /* global kakao */
      script.onload = () => kakao.maps.load(this.initMap);
      script.src =
        "http://dapi.kakao.com/v2/maps/sdk.js?appkey=053dd3145f395e73cbb5211bedf3e97f&libraries=services";
      document.head.appendChild(script);
    },
    addServiceScript() {
      // const script = document.createElement('script') /* global kakao */
      // script.onload = () => kakao.maps.load(this.initMap)
      // script.src =
      //   'http://dapi.kakao.com/v2/maps/sdk.js?appkey=053dd3145f395e73cbb5211bedf3e97f&libraries=services'
      // document.head.appendChild(script)
    },
    drawMap() {
      var mapContainer = document.getElementById("map"); // 지도를 표시할 div
      var mapOption = {
        center: new kakao.maps.LatLng(33.450701, 127.1), // 지도의 중심좌표
        level: 4, // 지도의 확대 레벨
      };

      this.map = new kakao.maps.Map(mapContainer, mapOption); // 지도를 생성합니다

      // HTML5의 geolocation으로 사용할 수 있는지 확인합니다
      if (navigator.geolocation) {
        // GeoLocation을 이용해서 접속 위치를 얻어옵니다
        navigator.geolocation.getCurrentPosition(position => {
          var lat = position.coords.latitude; // 위도
          var lon = position.coords.longitude; // 경도

          console.log(lat, lon);

          var locPosition = new kakao.maps.LatLng(37.493, 127.042); // 마커가 표시될 위치를 geolocation으로 얻어온 좌표로 생성합니다

          var message = '<div style="padding:5px;">여기에 계신가요?!</div>'; // 인포윈도우에 표시될 내용입니다

          //현재위치 행정구역명으로 바꾸기
          // var geocoder = new kakao.maps.services.Geocoder()
          // geocoder.coord2Address(lon, lat, (result, status) => {
          //   console.log(result[0].address.region_3depth_name)
          //   console.log(result[0].address.region_2depth_name)
          //   if (status === kakao.maps.services.Status.OK) {
          //     // console.log(result)
          //     // console.log(result[0].address.address_name)
          //     message =
          //       '<div style="padding:5px;">' +
          //       result[0].address.region_2depth_name +" "+ result[0].address.region_3depth_name+
          //       '</div>'
          //     this.displayMarker(locPosition, message)
          //   }else{
          //     this.displayMarker(locPosition, message)
          //   }
          // })

          for (var i = 0, len = this.positions.length; i < len; i++) {
            var gapX = MARKER_WIDTH + SPRITE_GAP, // 스프라이트 이미지에서 마커로 사용할 이미지 X좌표 간격 값
              originY = (MARKER_HEIGHT + SPRITE_GAP) * i, // 스프라이트 이미지에서 기본, 클릭 마커로 사용할 Y좌표 값
              overOriginY = (OVER_MARKER_HEIGHT + SPRITE_GAP) * i, // 스프라이트 이미지에서 오버 마커로 사용할 Y좌표 값
              normalOrigin = new kakao.maps.Point(0, originY), // 스프라이트 이미지에서 기본 마커로 사용할 영역의 좌상단 좌표
              clickOrigin = new kakao.maps.Point(gapX, originY), // 스프라이트 이미지에서 마우스오버 마커로 사용할 영역의 좌상단 좌표
              overOrigin = new kakao.maps.Point(gapX * 2, overOriginY); // 스프라이트 이미지에서 클릭 마커로 사용할 영역의 좌상단 좌표

            // 마커를 생성하고 지도위에 표시합니다
            this.addMarker(
              this.positions[i],
              normalOrigin,
              overOrigin,
              clickOrigin
            );
          }

          this.displayMarker(locPosition, message);
        });
      } else {
        // HTML5의 GeoLocation을 사용할 수 없을때 마커 표시 위치와 인포윈도우 내용을 설정합니다
        var locPosition = new kakao.maps.LatLng(33.450701, 126.570667);
        var message = "geolocation을 사용할수 없어요..";

        this.displayMarker(locPosition, message);
      }
    },
    addMarker(positions, normalOrigin, overOrigin, clickOrigin) {
      var position = new kakao.maps.LatLng(
        positions.latitude,
        positions.longitude
      );
      // 기본 마커이미지, 오버 마커이미지, 클릭 마커이미지를 생성합니다
      var normalImage = this.createMarkerImage(
          markerSize,
          markerOffset,
          normalOrigin
        ),
        overImage = this.createMarkerImage(
          overMarkerSize,
          overMarkerOffset,
          overOrigin
        ),
        clickImage = this.createMarkerImage(
          markerSize,
          markerOffset,
          clickOrigin
        );

      // 마커를 생성하고 이미지는 기본 마커 이미지를 사용합니다
      var marker = new kakao.maps.Marker({
        map: this.map,
        position: position,
        image: normalImage,
      });

      // 마커 객체에 마커아이디와 마커의 기본 이미지를 추가합니다
      marker.normalImage = normalImage;

      // 마커에 mouseover 이벤트를 등록합니다
      kakao.maps.event.addListener(marker, "mouseover", () => {
        // 클릭된 마커가 없고, mouseover된 마커가 클릭된 마커가 아니면
        // 마커의 이미지를 오버 이미지로 변경합니다
        if (!this.selectedMarker || this.selectedMarker !== marker) {
          marker.setImage(overImage);
        }
      });

      // 마커에 mouseout 이벤트를 등록합니다
      kakao.maps.event.addListener(marker, "mouseout", () => {
        // 클릭된 마커가 없고, mouseout된 마커가 클릭된 마커가 아니면
        // 마커의 이미지를 기본 이미지로 변경합니다
        if (!this.selectedMarker || this.selectedMarker !== marker) {
          marker.setImage(normalImage);
        }
      });

      // 마커에 click 이벤트를 등록합니다
      kakao.maps.event.addListener(marker, "click", () => {
        // 클릭된 마커가 없고, click 마커가 클릭된 마커가 아니면
        // 마커의 이미지를 클릭 이미지로 변경합니다
        if (!this.selectedMarker || this.selectedMarker !== marker) {
          // 클릭된 마커 객체가 null이 아니면
          // 클릭된 마커의 이미지를 기본 이미지로 변경하고
          !!this.selectedMarker &&
            this.selectedMarker.setImage(this.selectedMarker.normalImage);

          // 현재 클릭된 마커의 이미지는 클릭 이미지로 변경합니다
          marker.setImage(clickImage);
        }

        // 클릭된 마커를 현재 클릭된 마커 객체로 설정합니다
        this.selectedMarker = marker;
      });
    },
    createMarkerImage(markerSize, offset, spriteOrigin) {
      var markerImage = new kakao.maps.MarkerImage(
        SPRITE_MARKER_URL, // 스프라이트 마커 이미지 URL
        markerSize, // 마커의 크기
        {
          offset: offset, // 마커 이미지에서의 기준 좌표
          spriteOrigin: spriteOrigin, // 스트라이프 이미지 중 사용할 영역의 좌상단 좌표
          spriteSize: spriteImageSize, // 스프라이트 이미지의 크기
        }
      );

      return markerImage;
    },
    displayMarker(locPosition, message) {
      // 마커를 생성합니다
      var marker = new kakao.maps.Marker({
        map: this.map,
        position: locPosition,
      });

      var iwContent = message; // 인포윈도우에 표시할 내용
      var iwRemoveable = true;

      // 인포윈도우를 생성합니다
      var infowindow = new kakao.maps.InfoWindow({
        content: iwContent,
        removable: iwRemoveable,
      });

      // 인포윈도우를 마커위에 표시합니다
      infowindow.open(this.map, marker);

      // 지도 중심좌표를 접속위치로 변경합니다
      this.map.setCenter(locPosition);
    },
    categoryButtonColor(category) {
      if (category == "food" && this.categoryFood) {
        return "primary";
      } else if (category == "delivery" && this.categoryDelivery) {
        return "primary";
      } else {
        return "white";
      }
    },
    categoryButtonClicked(category) {
      if (category == "food" && this.categoryFood && !this.categoryDelivery)
        return;
      if (category == "delivery" && !this.categoryFood && this.categoryDelivery)
        return;

      if (category == "food") {
        this.categoryFood = !this.categoryFood;
      } else if (category == "delivery") {
        this.categoryDelivery = !this.categoryDelivery;
      }
    },
    distanceButtonColor(distance) {
      // console.log(distance)
      if (this.distance == distance) return "primary";
      else return "white";
    },
    distanceButtonClicked(distance) {
      this.distance = distance;
    },
  },
};
</script>

<style scoped>
.fullpage-container {
  /* height: 100%;*/
  /* position: fixed; */
  margin: 0px;
  padding: 0px;
}
.fullpage-map {
  width: 100%;
  height: 100%;
  position: absolute;
}
</style>
