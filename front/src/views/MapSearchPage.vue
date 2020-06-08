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
                v-for="(board, index) in searchList"
                :key="index + 'store'"
                class="pa-0"
              >
                <!-- <v-hover v-slot:default="{ hover }" open-delay="50">
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
                </v-hover> -->
                <v-hover v-slot:default="{ hover }">
                  <v-container
                    class="my-1 boardCard"
                    v-bind:style="{
                      background: index % 2 == 0 ? '#e4e4e4' : '#f7f7f7',
                      'box-shadow': hover ? '3px 3px #5a5a5a' : 'none',
                      cursor: 'pointer',
                    }"
                    @click="goToBoardDetail(board.board_id)"
                    @mouseenter="enterBoardCard(index)"
                    @mouseleave="leaveBoardCard(index)"
                  >
                    <v-row>
                      <v-col class="py-0">
                        <v-icon>mdi-map-marker</v-icon>
                        {{
                          calDistance(
                            board.board_locationY,
                            board.board_locationX
                          )
                        }}
                        / 평점
                        {{
                          board.user == undefined ? '' : board.user.reputation
                        }}
                      </v-col>
                    </v-row>
                    <v-row>
                      <v-col
                        v-bind:style="{
                          color:
                            calDate(board.deadlineDate) == '마감'
                              ? '#999999'
                              : '#ff0000',
                        }"
                        style="font-size:15px"
                        class="py-0"
                      >
                        {{ calDate(board.deadlineDate) }}
                      </v-col>
                    </v-row>
                    <v-row>
                      <v-col class="py-0">
                        <v-chip
                          v-for="(keyword, index) in board.keyword
                            .split('#')
                            .slice(1)"
                          :key="board.board_id + ' ' + index + ' ' + keyword"
                          color="#f076b6"
                          style="color:white ; font-size:0.8rem ; margin-left:1px"
                          small
                        >
                          #{{ keyword }}
                        </v-chip>
                      </v-col>
                    </v-row>
                    <v-row>
                      <v-col class="py-0">
                        <b style="font-size:1rem;">{{ board.title }}</b>
                      </v-col>
                    </v-row>
                    <v-row> </v-row>

                    <!-- <v-card-actions></v-card-actions> -->
                    <!-- <v-divider></v-divider> -->
                  </v-container>
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
import Constant from '../vuex/Constant'
import { mapState } from 'vuex'

var imageSrcStar =
  'https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/markerStar.png'
var imageSrc = 'https://i1.daumcdn.net/dmaps/apis/n_local_blit_04.png'
var imageSize = new kakao.maps.Size(31, 35)
var markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize)
var imageSizeStar = new kakao.maps.Size(25, 35)
var markerClickImage = new kakao.maps.MarkerImage(imageSrcStar, imageSizeStar)
export default {
  name: 'MapSearchPage',
  components: {},
  data: () => ({
    map: null,
    refreshFlag: false,
    filterDialog: false,
    category: 'food',
    distance: 200,
    distances: [200, 500, 1000],
    windowHeight: 100,
    filterAreaHeight: 100,
    headerHeight: 64,
    curPosition: [0, 0],
    markers: [],
  }),
  created() {
    if (!(window.kakao && window.kakao.maps && window.kakao.services))
      this.addMapScript()
    if (!window.kakao.services) this.addServiceScript()

    // console.log(window.kakao.services)

    this.windowHeight = window.innerHeight
    window.addEventListener('resize', () => {
      this.windowHeight = window.innerHeight
      // console.log(this.windowHeight)
    })
  },
  mounted() {
    this.drawMap()
    //filterAreaHeight
    this.filterAreaHeight = document.getElementById('filterArea').offsetHeight
    // console.log(this.filterAreaHeight)
  },
  computed: {
    ...mapState(['searchList']),
  },
  methods: {
    addMapScript() {
      console.log('ddd')
      const script = document.createElement('script') /* global kakao */
      script.onload = () => kakao.maps.load(this.initMap)
      script.src =
        'http://dapi.kakao.com/v2/maps/sdk.js?appkey=053dd3145f395e73cbb5211bedf3e97f&libraries=services'
      document.head.appendChild(script)
    },
    addServiceScript() {
      // const script = document.createElement('script') /* global kakao */
      // script.onload = () => kakao.maps.load(this.initMap)
      // script.src =
      //   'http://dapi.kakao.com/v2/maps/sdk.js?appkey=053dd3145f395e73cbb5211bedf3e97f&libraries=services'
      // document.head.appendChild(script)
    },
    drawMap() {
      var mapContainer = document.getElementById('map') // 지도를 표시할 div
      var mapOption = {
        center: new kakao.maps.LatLng(33.450701, 127.1), // 지도의 중심좌표
        level: 4, // 지도의 확대 레벨
      }

      this.map = new kakao.maps.Map(mapContainer, mapOption) // 지도를 생성합니다

      // HTML5의 geolocation으로 사용할 수 있는지 확인합니다
      if (navigator.geolocation) {
        // GeoLocation을 이용해서 접속 위치를 얻어옵니다
        navigator.geolocation.getCurrentPosition((position) => {
          var lat = position.coords.latitude // 위도
          var lon = position.coords.longitude // 경도

          console.log(lat, lon)
          this.curPosition = [lat, lon]
          var locPosition = new kakao.maps.LatLng(lat, lon) // 마커가 표시될 위치를 geolocation으로 얻어온 좌표로 생성합니다

          var message = '<div style="padding:5px;">여기에 계신가요?!</div>' // 인포윈도우에 표시될 내용입니다

          this.displayMarker(locPosition, message)
          this.loadBoardList(lat, lon)
        })
      } else {
        // HTML5의 GeoLocation을 사용할 수 없을때 마커 표시 위치와 인포윈도우 내용을 설정합니다
        var locPosition = new kakao.maps.LatLng(33.450701, 126.570667)
        var message = 'geolocation을 사용할수 없어요..'

        this.displayMarker(locPosition, message)
      }
    },
    createMarkers() {
      this.deleteMarkers()
      for (var i = 0, len = this.searchList.length; i < len; i++) {
        console.log(i)
        // 마커를 생성하고 지도위에 표시합니다
        this.addMarker(
          this.searchList[i].board_locationY,
          this.searchList[i].board_locationX,
          this.searchList[i]
        )
      }
      console.log('3333')
      console.log(this.markers)
    },
    addMarker(lat, lon, board) {
      var position = new kakao.maps.LatLng(lat, lon)
      // 기본 마커이미지, 오버 마커이미지, 클릭 마커이미지를 생성합니다

      // 마커를 생성하고 이미지는 기본 마커 이미지를 사용합니다
      var marker = new kakao.maps.Marker({
        map: this.map,
        position: position,
        clickable: true,
      })

      marker.setImage(markerImage)

      // var remainTime = this.calDate(board.deadlineDate)

      var iwContent =
        '<div style="width:200px;" @click="this.test">' +
        '<span style="font-size:1.5rem">' +
        board.title +
        '</span>' +
        '<span style="color:yellow">' +
        '★' +
        '</span>' +
        '<span>' +
        board.user.reputation +
        '</span>' +        
        '<hr>' +
        '<div style="text-align: right;">' +        
        '</div>' +
        '</div>'
      // var iwContent =
      //   '<v-card> ' +
      //   ' <v-icon> ' +
      //   'mdi-map-marker' +
      //   ' </v-icon> ' +
      //   ' </v-card> '
      board
      var iwRemoveable = true // removeable 속성을 ture 로 설정하면 인포윈도우를 닫을 수 있는 x버튼이 표시됩니다

      // 인포윈도우를 생성합니다
      var infowindow = new kakao.maps.InfoWindow({
        content: iwContent,
        removable: iwRemoveable,
        clickable: true
      })

      // 마커에 mouseover 이벤트를 등록합니다
      kakao.maps.event.addListener(marker, 'mouseover', () => {
        marker.setImage(markerClickImage)
        // infowindow.open(this.map, marker)
      })

      // 마커에 mouseout 이벤트를 등록합니다
      kakao.maps.event.addListener(marker, 'mouseout', () => {
        marker.setImage(markerImage)
        // infowindow.open(null, marker)
      })

      // 마커에 클릭이벤트를 등록합니다
      kakao.maps.event.addListener(marker, 'click', () => {
        // alert('dd')
        // 마커 위에 인포윈도우를 표시합니다
        // console.log(infowindow)
        infowindow.open(this.map, marker)
      })

      this.markers.push(marker)
    },
    displayMarker(locPosition, message) {
      // 마커를 생성합니다
      var marker = new kakao.maps.Marker({
        map: this.map,
        position: locPosition,
      })

      var iwContent = message // 인포윈도우에 표시할 내용
      var iwRemoveable = true

      // 인포윈도우를 생성합니다
      var infowindow = new kakao.maps.InfoWindow({
        content: iwContent,
        removable: iwRemoveable,
      })

      // 인포윈도우를 마커위에 표시합니다
      infowindow.open(this.map, marker)

      // 지도 중심좌표를 접속위치로 변경합니다
      this.map.setCenter(locPosition)
    },
    categoryButtonColor(categoryClicked) {
      if (categoryClicked == this.category) {
        return 'primary'
      } else {
        return 'white'
      }
    },
    categoryButtonClicked(categoryClicked) {
      if (categoryClicked != this.category) {
        this.category = categoryClicked
        this.loadBoardList(this.curPosition[0], this.curPosition[1])
      }
    },
    distanceButtonColor(distance) {
      // console.log(distance)
      if (this.distance == distance) return 'primary'
      else return 'white'
    },
    distanceButtonClicked(distance) {
      this.distance = distance
      this.loadBoardList(this.curPosition[0], this.curPosition[1])
    },
    loadBoardList(lat, lon) {
      this.$store.dispatch(Constant.SEARCH_BYDISTANCE, {
        latitude: lat,
        longitude: lon,
        distance: this.distance,
        category: this.category == 'food' ? 1 : 0,
        // category: 0,
        callback: this.createMarkers,
      })
    },
    deleteMarkers() {
      for (var i = 0; i < this.markers.length; i++) {
        this.markers[i].setMap(null)
      }
      this.markers = []
    },
    calDate(date) {
      let today = new Date()

      var diff = Math.floor((Date.parse(date) - Date.parse(today)) / 1000)

      if (diff <= 0) {
        this.remainTime = {
          day: 0,
          hours: 0,
          minutes: 0,
          seconds: 0,
        }

        clearInterval(this.remainTimeFunction)
        return '마감'
      } else {
        var day = Math.floor(diff / (3600 * 24))
        var hours = Math.floor((diff - day * 3600 * 24) / 3600)
        var minutes = Math.floor((diff - day * 3600 * 24 - hours * 3600) / 60)
        var seconds = diff - day * 3600 * 24 - hours * 3600 - minutes * 60

        // console.log(day + ' ' + hours + ' ' + minutes + ' ' + seconds)
        if (day > 0) {
          return day + '일 남음'
        } else if (hours > 0) {
          return hours + '시간 남음'
        } else if (minutes > 0) {
          return minutes + '분 남음'
        } else {
          return seconds + '초 남은'
        }
      }
    },
    calDistance(lat, lon) {
      var curLat = this.curPosition[0]
      var curLon = this.curPosition[1]
      console.log('calDistance ' + curLat + ' ' + curLon)
      if (curLat == 0) {
        return '현재위치를 설정해주세요.'
      }
      var X =
        ((Math.cos(curLat) * 6400 * 2 * 3.14) / 360) * Math.abs(curLon - lon)
      var Y = 111 * Math.abs(curLat - lat)
      var D = Math.sqrt(Math.pow(X, 2) + Math.pow(Y, 2))

      // console.log(Math.floor(D))

      if (Math.floor(D) == 0) {
        //m단위일때
        return D.toFixed(3) * 1000 + 'm'
      } else {
        //km 일때
        return Math.floor(D) + 'km'
      }
    },
    goToBoardDetail(id) {
      this.$router.push({ path: '/board', query: { id: id } })
    },
    enterBoardCard(index) {
      console.log(index)
      this.markers[index].setImage(markerClickImage)
    },
    leaveBoardCard(index) {
      this.markers[index].setImage(markerImage)
    },
    test(){
      console.log("ddd")
    }
  },
}
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
.taemin {
  color: red;
}
</style>
