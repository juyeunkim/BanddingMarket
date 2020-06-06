<template>
  <v-container class="Nanum">
    <v-layout wrap class="ma-0">
      <!--카테고리-->
      <v-flex sm12 xs12>
        <v-select
          label="카테고리"
          solo
          style="max-width: 15rem;"
          :items="['택배', '음식']"
          hide-details
          dense
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
            <v-radio-group v-model="position[0]">
              <v-radio label="프로필 위치 사용하기" value="none"></v-radio>
              <v-radio
                label="사용자 지정 위치 사용하기"
                value="position"
                @click="test"
              ></v-radio>
              <v-container>
                <v-row>
                  <v-col cols="6">
                    <div
                      id="taeminMap"
                      style="height: 300px; width: 100%;"
                      class="my-2"
                    ></div>
                  </v-col>
                  <v-col cols="6">
                    <!-- {{ map != null ? map.getCenter().getLat() : '' }} -->
                    {{ centerMapName }}
                  </v-col>
                </v-row>
              </v-container>
            </v-radio-group>
          </v-expansion-panel-content>
        </v-expansion-panel>

        <v-expansion-panel>
          <v-expansion-panel-header disable-icon-rotate>
            Item
            <template v-slot:actions>
              <v-icon color="error">mdi-alert-circle</v-icon>
            </template>
          </v-expansion-panel-header>
          <v-expansion-panel-content>
            Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do
            eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim
            ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut
            aliquip ex ea commodo consequat.
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
  </v-container>
</template>

<script>
import VueTagsInput from '@johmun/vue-tags-input'
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
    deadlineTime: null,
    timeMenu: false,
    time: new Date().getHours() + ':' + new Date().getMinutes(),
    dateMenu: false,
    deadlineDate: null,
    menu: false,
    menu2: false,
    date: new Date().toISOString().substr(0, 10),
    timeCheck: false,
    positionCheck: false,
    position: ['position',""],
    map: null,
    centerMapName: '',
    mapMarker: '',
  }),
  created() {
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
        level: 4, // 지도의 확대 레벨
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

        if(this.mapMarker != "")
          this.mapMarker.setMap(null)

        this.mapMarker = new kakao.maps.Marker({
            map:this.map,
            position: latlng,
        })

        this.position[1]=[lon,lat]

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
        this.tag = ""
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
