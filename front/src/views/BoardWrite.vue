<template>
  <v-container class="Nanum">
    <v-layout wrap class="ma-0">
      <!--카테고리-->
      <v-flex sm12 xs12>
        <v-select
          label="카테고리"
          solo
          style="max-width: 15rem;"
          :items="['상품', '음식']"
          hide-details
          dense
          v-model="category"
        ></v-select>
      </v-flex>

      <!--제목-->
      <v-flex sm12 xs12>
        <v-text-field
          label="제목"
          single-line
          hide-details
          v-model="title"
        ></v-text-field>
      </v-flex>
      <!-- content -->
      <v-flex sm12 xs12 class="my-3">
        <v-textarea
          rows="15"
          row-height="20"
          v-model="contents"
          label="내용"
          outlined
        ></v-textarea>
      </v-flex>

      <!--추가정보제목-->
      <v-flex sm12 xs12>
        <v-divider class="my-3"></v-divider>
        <span>추가정보</span>
      </v-flex>

      <!-- 추가 정보 -->
      <v-expansion-panels>
        <!-- 마감시간 -->
        <v-expansion-panel>
          <v-expansion-panel-header disable-icon-rotate>
            마감시간
            <template v-slot:actions>
              <v-icon color="error" v-if="timeCheck == false"
                >mdi-alert-circle</v-icon
              >
              <v-icon color="primary" v-else>mdi-check</v-icon>
            </template>
          </v-expansion-panel-header>
          <v-expansion-panel-content>
            <v-menu
              ref="dateMenu"
              v-model="menu"
              :close-on-content-click="false"
              :return-value.sync="date"
              transition="scale-transition"
              offset-y
              min-width="290px"
            >
              <template v-slot:activator="{ on }">
                <v-text-field
                  v-model="date"
                  label="마감날짜"
                  prepend-icon="mdi-calendar"
                  readonly
                  v-on="on"
                ></v-text-field>
              </template>
              <v-date-picker v-model="date" no-title scrollable>
                <v-spacer></v-spacer>
                <v-btn text color="primary" @click="menu = false">Cancel</v-btn>
                <v-btn text color="primary" @click="$refs.dateMenu.save(date)"
                  >OK</v-btn
                >
              </v-date-picker>
            </v-menu>
            <v-menu
              ref="menu"
              v-model="menu2"
              :close-on-content-click="false"
              :nudge-right="40"
              :return-value.sync="time"
              transition="scale-transition"
              offset-y
              max-width="290px"
              min-width="290px"
            >
              <template v-slot:activator="{ on }">
                <v-text-field
                  v-model="time"
                  label="Picker in menu"
                  prepend-icon="mdi-clock-outline"
                  readonly
                  v-on="on"
                  :append-icon="
                    timeCheck == false ? 'mdi-alert-circle' : 'mdi-check'
                  "
                ></v-text-field>
              </template>
              <v-time-picker
                v-if="menu2"
                v-model="time"
                full-width
                @click:minute="clickTimePickerOK"
              ></v-time-picker>
            </v-menu>
          </v-expansion-panel-content>
        </v-expansion-panel>

        <!--위치고르기 -->
        <v-expansion-panel>
          <v-expansion-panel-header
            disable-icon-rotate
            @click="positionMenuClicked"
          >
            만남 장소 고르기
            <template v-slot:actions>
              <v-icon color="error" v-if="positionCheck == false"
                >mdi-alert-circle</v-icon
              >
              <v-icon color="primary" v-else>mdi-check</v-icon>
            </template>
          </v-expansion-panel-header>
          <v-expansion-panel-content>
            <!-- <v-img src="@/assets/logo.png"></v-img> -->
            <v-container>
              <v-row>
                <v-col cols="4">
                  <v-btn
                    @click="clickPositionType(0)"
                    :color="positionTypeButtonColor(0)"
                    style="width:100%"
                  >
                    사용자 위치 사용하기
                  </v-btn>
                </v-col>
                <v-col cols="4">
                  <v-btn
                    @click="clickPositionType(1)"
                    :color="positionTypeButtonColor(1)"
                    style="width:100%"
                  >
                    현재 위치 사용하기
                  </v-btn>
                </v-col>
                <v-col cols="4">
                  <v-btn
                    @click="clickPositionType(2)"
                    :color="positionTypeButtonColor(2)"
                    style="width:100%"
                  >
                    추천 위치 사용하기
                  </v-btn>
                </v-col>
              </v-row>
              <v-row v-show="positionType != 0">
                <v-col cols="6">
                  <div
                    id="taeminMap"
                    style="height: 300px; width: 100%;"
                    class="my-2"
                  ></div>
                </v-col>
                <!-- 지도 행정명 -->
                <v-col cols="6" v-if="positionType == 1">
                  <!-- {{ map != null ? map.getCenter().getLat() : '' }} -->
                  {{ centerMapName }}
                </v-col>
                <!-- list -->
                <v-col
                  cols="6"
                  v-if="positionType == 2 && recommendList.length != 0"
                >
                  <v-container
                    class="pa-0 mt-3"
                    v-bind:style="{
                      overflow: 'auto',
                      height: '300px',
                    }"
                  >
                    <v-list style="width:100%">
                      <v-list-item
                        v-for="(place, index) in recommendList"
                        :key="index + 'recommand'"
                        class="pa-0"
                      >
                        <v-hover v-slot:default="{ hover }">
                          <v-container
                            v-bind:style="{
                              background:
                                index % 2 == 0 ? '#e4e4e4' : '#f7f7f7',
                              'box-shadow': hover ? '3px 3px #5a5a5a' : 'none',
                              cursor: 'pointer',
                            }"
                            @click="selectPlace(place)"
                            @mouseenter="enterCard(index)"
                            @mouseleave="leaveCard(index)"
                          >
                            <v-row>
                              {{
                                place.type == 1 ? 'cctv' : place.name + '역'
                              }}</v-row
                            >
                            <v-row> {{ place.address }}</v-row>
                          </v-container>
                        </v-hover>
                      </v-list-item>
                    </v-list>
                  </v-container>
                </v-col>
                <v-col
                  cols="6"
                  v-if="positionType == 2 && recommendList.length == 0"
                >
                  추천 장소가 없습니다.
                </v-col>
              </v-row>
              <v-row v-if="positionType == 2">
                <v-col>
                  {{
                    '현재위치 : ' +
                      (selectedPosition.type == undefined
                        ? '선택된 위치 없음'
                        : selectedPosition.type == 1
                        ? '(cctv)' + selectedPosition.address
                        : '(' +
                          selectedPosition.name +
                          '역)' +
                          selectedPosition.address)
                  }}
                </v-col>
              </v-row>
            </v-container>
          </v-expansion-panel-content>
        </v-expansion-panel>

        <!--모집 인원 -->
        <v-expansion-panel>
          <v-expansion-panel-header
            disable-icon-rotate
            @click="personCheck = true"
          >
            모집인원
            <template v-slot:actions>
              <v-icon color="error" v-if="personCheck == false"
                >mdi-alert-circle</v-icon
              >
              <v-icon color="primary" v-else>mdi-check</v-icon>
            </template>
          </v-expansion-panel-header>
          <v-expansion-panel-content>
            <v-slider
              v-model="person"
              :min="2"
              :max="5"
              thumb-label="always"
              label="모집인원"
            ></v-slider>
          </v-expansion-panel-content>
        </v-expansion-panel>
      </v-expansion-panels>

      <!-- <div id="map" style="height: 300px; width: 100%;" class="my-2"></div> -->

      <!-- tag -->
      <v-flex sm12 xs12 class="my-3">
        <span>
          태그
          <vue-tags-input
            v-model="tag"
            :tags="tags"
            @tags-changed="(newTags) => (tags = newTags)"
            @before-adding-tag="checkTag"
          />
        </span>
      </v-flex>

      <!-- 날짜와 시간 -->
      <v-flex sm12 xs12> </v-flex>

      <!-- 위치고르기 -->
      <v-flex sm12 xs12> </v-flex>
    </v-layout>

    <v-layout>
      <v-flex sm12 xs12 style="text-align: end;">
        <v-btn @click="registerBoard">등록하기</v-btn>
      </v-flex>
    </v-layout>
  </v-container>
</template>

<script>
import VueTagsInput from '@johmun/vue-tags-input'
import http from '../vuex/http-common'

var imageSrcStar =
  'https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/markerStar.png'
var imageSrc = 'https://i1.daumcdn.net/dmaps/apis/n_local_blit_04.png'
var imageSize = new kakao.maps.Size(31, 35)
var markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize)
var imageSizeStar = new kakao.maps.Size(25, 35)
var markerClickImage = new kakao.maps.MarkerImage(imageSrcStar, imageSizeStar)

// var markerImage = new kakao.maps.MarkerImage(
//     '../assets/logo.jpg',
//     // 'https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/marker_red.png',
//     new kakao.maps.Size(31, 35));

export default {
  name: 'BoardWrite',
  components: {
    VueTagsInput,
  },
  data: () => ({
    curTime: new Date().getHours() + ':' + new Date().getMinutes(),
    curDate: new Date().toISOString().substr(0, 10),
    title: '',
    contents: '',
    tag: '',
    tags: [],
    time: new Date().getHours() + ':' + new Date().getMinutes(),
    dateMenu: false,
    menu: false,
    menu2: false,
    date: new Date().toISOString().substr(0, 10),
    timeCheck: false,
    positionCheck: false,
    position: ['position', ''],
    map: null,
    centerMapName: '',
    mapMarker: '',
    personCheck: false,
    person: 2,
    positionType: 1,
    timer: null,
    recommendList: [],
    markers: [],
    selectedPosition: {},
    category: '',
  }),
  created() {
    if (this.$cookies.get('token') == '') {
      alert('잘못된 접근입니다.')
      this.$router.go(-1)
    }
    if (!(window.kakao && window.kakao.maps && window.kakao.services))
      this.addMapScript()
    // this.remainTimeFunction = setInterval(this.calRemainingTime, 1000)
    // console.log(new Date().getHours() + ':' + new Date().getMinutes())
  },
  mounted() {
    // this.drawMap()
    // setTimeout(() => {
    //     this.drawMap()
    //   },1000)
  },
  computed: {},
  beforeDestroy() {},
  methods: {
    test() {
      alert('dd')
    },
    test2(aa) {
      alert('삭제?' + aa)
    },
    addMapScript() {
      const script = document.createElement('script') /* global kakao */
      script.onload = () => kakao.maps.load(this.initMap)
      script.src =
        'http://dapi.kakao.com/v2/maps/sdk.js?appkey=053dd3145f395e73cbb5211bedf3e97f&libraries=services'
      document.head.appendChild(script)
    },
    drawMap() {
      console.log('draws')
      var mapContainer = document.getElementById('taeminMap') // 지도를 표시할 div
      console.log(mapContainer)
      var mapOption = {
        center: new kakao.maps.LatLng(33.450701, 127.1), // 지도의 중심좌표
        level: 7, // 지도의 확대 레벨
      }

      this.map = new kakao.maps.Map(mapContainer, mapOption) // 지도를 생성합니다

      // 지도에 원을 표시합니다

      // 마커를 생성합니다

      // 마커가 지도 위에 표시되도록 설정합니다
      // marker.setMap(map)

      // HTML5의 geolocation으로 사용할 수 있는지 확인합니다
      if (navigator.geolocation) {
        // GeoLocation을 이용해서 접속 위치를 얻어옵니다
        navigator.geolocation.getCurrentPosition((position) => {
          var lat = position.coords.latitude // 위도
          var lon = position.coords.longitude // 경도

          var locPosition = new kakao.maps.LatLng(lat, lon) // 마커가 표시될 위치를 geolocation으로 얻어온 좌표로 생성합니다
          this.selectedPosition = { lat: lat, lon: lon }
          this.position[1] = [lon, lat]
          // var message = '<div style="padding:5px;">여기에 계신가요?!</div>' // 인포윈도우에 표시될 내용입니다
          this.map.setCenter(locPosition)
          this.displayCircle(locPosition)
        })
      } else {
        // HTML5의 GeoLocation을 사용할 수 없을때 마커 표시 위치와 인포윈도우 내용을 설정합니다
        var locPosition = new kakao.maps.LatLng(33.450701, 126.570667)
        // var message = 'geolocation을 사용할수 없어요..'

        this.displayCircle(locPosition)
      }

      kakao.maps.event.addListener(this.map, 'center_changed', () => {
        // 지도의 중심좌표를 얻어옵니다
        var latlng = this.map.getCenter()
        var lon = latlng.getLng()
        var lat = latlng.getLat()

        if (this.mapMarker != '') this.mapMarker.setMap(null)

        this.mapMarker = new kakao.maps.Marker({
          map: this.map,
          position: latlng,
          // image :markerImage
        })

        // this.mapMarker.setImage(markerImage)

        this.position[1] = [lon, lat]

        if (this.positionType == 2) {
          // console.log('Ddddddd')
          clearTimeout(this.timer)
          this.timer = setTimeout(() => {
            this.loadrecommandList()
          }, 300)
        }

        // marker.setMap(this.map)

        var geocoder = new kakao.maps.services.Geocoder()
        geocoder.coord2Address(lon, lat, (result, status) => {
          // console.log(result[0].address.region_3depth_name)
          // console.log(result[0].address.region_2depth_name)
          if (status === kakao.maps.services.Status.OK) {
            // console.log(result)
            // console.log(result[0].address.address_name)
            this.centerMapName = result[0].address.address_name
            // console.log(this.centerMapName)
            // console.log(result[0])
          } else {
            this.centerMapName = ''
          }
        })
      })
    },
    displayCircle(locPosition) {
      // 마커를 생성합니다

      console.log('displayCircle')

      var mapCircle = new kakao.maps.Circle({
        center: locPosition, // 원의 중심좌표 입니다
        radius: 5, // 미터 단위의 원의 반지름입니다
        // strokeWeight: 5, // 선의 두께입니다
        // strokeColor: 'CFE7FF', // 선의 색깔입니다
        // strokeOpacity: 1, // 선의 불투명도 입니다 1에서 0 사이의 값이며 0에 가까울수록 투명합니다
        // strokeStyle: 'dashed', // 선의 스타일 입니다
        fillColor: '#ff0000', // 채우기 색깔입니다
        fillOpacity: 0.7, // 채우기 불투명도 입니다
      })

      mapCircle.setMap(this.map)
    },
    checkTag(obj) {
      console.log(obj)
      if (this.tags.length >= 3) {
        alert('태그는 최대 3개까지 가능합니다.')
        this.tag = ''
      } else {
        obj.addTag()
      }
    },
    clickTimePickerOK() {
      this.$refs.menu.save(this.time)
      this.timeCheck = true
      console.log(this.timeCheck)
    },
    positionMenuClicked() {
      this.positionCheck = true
      setTimeout(() => {
        this.drawMap()
      }, 1000)
      // this.drawMap()
    },
    clickPositionType(type) {
      if (this.positionType != type && type == 2) {
        this.loadrecommandList()
        console.log('Dd')
      } else if (this.positionType != type && type == 1) {
        this.deleteMarker()
      }
      this.positionType = type
    },
    positionTypeButtonColor(type) {
      if (this.positionType == type) {
        return 'blue'
      }
    },
    loadrecommandList() {
      var lon = this.position[1][0]
      var lat = this.position[1][1]

      console.log('loadrecommandList')
      http
        .post('/map/search/safeLocation', {
          category: 0,
          dist: 1000,
          latitude: lat,
          longitude: lon,
        })
        .then((response) => {
          console.log(response.data.object)
          this.recommendList = response.data.object
          this.createmarkers()
        })
    },
    createmarkers() {
      this.deleteMarker()
      for (var i = 0, len = this.recommendList.length; i < len; i++) {
        // 마커를 생성하고 지도위에 표시합니다
        this.addMarker(this.recommendList[i])
      }
    },
    addMarker(place) {
      var position = new kakao.maps.LatLng(place.latitude, place.longitude)
      // 기본 마커이미지, 오버 마커이미지, 클릭 마커이미지를 생성합니다

      // 마커를 생성하고 이미지는 기본 마커 이미지를 사용합니다
      var marker = new kakao.maps.Marker({
        map: this.map,
        position: position,
        clickable: true,
      })

      marker.setImage(markerImage)

      // var remainTime = this.calDate(board.deadlineDate)

      // var iwContent =
      //   '<div style="width:200px;" @click="this.test">' +
      //   '<span style="font-size:1.5rem">' +
      //   board.title +
      //   '</span>' +
      //   '<span style="color:yellow">' +
      //   '★' +
      //   '</span>' +
      //   '<span>' +
      //   board.user.reputation +
      //   '</span>' +
      //   '<hr>' +
      //   '<div style="text-align: right;">' +
      //   '</div>' +
      //   '</div>'
      // // var iwContent =
      // //   '<v-card> ' +
      // //   ' <v-icon> ' +
      // //   'mdi-map-marker' +
      // //   ' </v-icon> ' +
      // //   ' </v-card> '
      // board
      // var iwRemoveable = true // removeable 속성을 ture 로 설정하면 인포윈도우를 닫을 수 있는 x버튼이 표시됩니다

      // // 인포윈도우를 생성합니다
      // var infowindow = new kakao.maps.InfoWindow({
      //   content: iwContent,
      //   removable: iwRemoveable,
      //   clickable: true
      // })

      // 마커에 mouseover 이벤트를 등록합니다
      kakao.maps.event.addListener(marker, 'mouseover', () => {})

      // 마커에 mouseout 이벤트를 등록합니다
      kakao.maps.event.addListener(marker, 'mouseout', () => {})

      // 마커에 클릭이벤트를 등록합니다
      kakao.maps.event.addListener(marker, 'click', () => {})

      this.markers.push(marker)
    },
    deleteMarker() {
      for (var i = 0; i < this.markers.length; i++) {
        this.markers[i].setMap(null)
      }
      this.markers = []
    },
    enterCard(index) {
      this.markers[index].setImage(markerClickImage)
    },
    leaveCard(index) {
      this.markers[index].setImage(markerImage)
    },
    selectPlace(place) {
      this.selectedPosition = place
      console.log(this.selectedPosition)
    },
    registerBoard() {
      if (this.category == '') {
        alert('카테고리를 설정해주세요.')
        this.$vuetify.goTo(0)
        return
      } else if (this.timeCheck == false) {
        alert('만남 시간을 작성해주세요.')
        return
      } else if (this.personCheck == false) {
        alert('참여인원수를 작성해주세요.')
        return
      } else if (this.positionCheck == false) {
        alert('만남 장소를 작성해주세요.')
        return
      }

      var keyword = ''
      for (let index = 0; index < this.tags.length; index++) {
        keyword += '#' + this.tags[index].text
        // console.log(this.tags[index].text)
      }

      var tmp = {
        category: this.category == '상품' ? 0 : 1,
        context: this.contents,
        deadlineDate: this.date + 'T' + this.time + ':00.000Z',
        keyword: keyword,
        limit_num: this.person,
        title: this.title,
        user: { user_id: Number(this.$cookies.get('user_id')) },
      }

      if (this.positionType == 1) {
        tmp['board_locationX'] = this.position[1][0] + ''
        tmp['board_locationY'] = this.position[1][1] + ''
      } else if (this.positionType == 2) {
        console.log(this.selectedPosition)
        if (this.selectedPosition == {}) {
          alert('모임장소를 선택해주세요!')
          return
        }
        tmp['board_locationX'] = this.selectedPosition.longitude + ''
        tmp['board_locationY'] = this.selectedPosition.latitude + ''
      }

      http
        .post('/board', tmp)
        .then((res) => {
          console.log(res)
          this.$router.push({ path: '/board', query: { id: res.data.object.board_id } })
        })
        .catch((err) => {
          console.log(err)
        })

      // console.log(parm)
    },
  },
}
</script>

<style scoped>
@import url('https://fonts.googleapis.com/css2?family=Nanum+Gothic:wght@400;700;800 &display=swap');

.Nanum {
  font-family: 'Nanum Gothic', sans-serif;
}

.fw700 {
  font-weight: 700;
}

.fw800 {
  font-weight: 800;
}

.titleSize {
  font-size: 3rem;
}
.titleMobileSize {
  font-size: 1.4rem;
}
.center {
  align-self: center;
  text-align: center;
}
.outerFlex {
  background: #dffbff;
  border-radius: 20px;
  padding: 10px;
  margin-top: 5px;
  margin-bottom: 5px;
}

.insertCommentButton {
  height: 100%;
  width: 100%;
  background: orange;
  color: white;
}
</style>
