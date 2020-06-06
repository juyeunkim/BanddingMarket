<template>
  <v-container class="Nanum">
    <v-layout wrap class="ma-0">
      <!-- 남은 시간 -->
      <v-flex
        sm12
        xs12
        class="my-1"
        v-bind:style="{
          background: timerColor(),
          'border-radius': '10px',
          'text-align': 'center',
          color: 'white',
          'font-size': '1.4rem',
        }"
      >
        <div
          v-if="
            remainTime.day == 0 &&
              remainTime.hours == 0 &&
              remainTime.minutes == 0 &&
              remainTime.seconds == 0
          "
        >
          <v-icon color="white" style="font-size: 2rem;">mdi-timer</v-icon>
          <span class="mx-1">공구가 마감됬습니다 </span>
        </div>
        <div v-else class="fw700">
          <v-icon color="white" style="font-size: 2rem;">mdi-timer</v-icon>
          <span class="mx-1">{{ remainTime.day }}일 </span>
          <span class="mx-1">{{ remainTime.hours }}시 </span>
          <span class="mx-1">{{ remainTime.minutes }}분 </span>
          <span class="mx-1">{{ remainTime.seconds }}초 </span>
          <!-- <span class="d-none d-sm-flex">남았습니다</span> -->
        </div>
      </v-flex>

      <!--태그-->
      <v-flex sm12 xs12>
        <span>
          <u>#{{ category }}</u>
        </span>
      </v-flex>

      <!--제목-->
      <v-flex sm10 xs12 class="fw700">
        <!-- 크기가 큰 화면 -->
        <span class="titleSize d-none d-sm-flex">{{ title }}</span>
        <!-- xs -->
        <span class="titleMobileSize d-flex d-sm-none">{{ title }}</span>
      </v-flex>

      <!-- 작성자와 작성일 수정 삭제-->
      <v-flex sm12 xs12 style="color: gray;">
        <v-menu offset-y>
          <template v-slot:activator="{ on }">
            <span class="mr-3" v-on="on" style="cursor: pointer;"
              ><u>{{ nick_name }}</u></span
            >
          </template>
          <v-list>
            <v-list-item @click="test">
              <v-list-item-title
                ><v-icon color="primary">mdi-account</v-icon
                ><span>유저정보보기</span></v-list-item-title
              >
            </v-list-item>
            <v-list-item @click="test">
              <v-list-item-title
                ><v-icon style="color: red;">mdi-alarm-light</v-icon
                ><span>유저신고하기</span></v-list-item-title
              >
            </v-list-item>
          </v-list>
        </v-menu>
        <span class="mx-2">{{ write_date }}</span>
        <v-divider vertical></v-divider>
        <!-- <span class="mx-3 d-none d-sm-flex" style="cursor: pointer;" @click="test"
          ><u>수정</u></span
        >
        <v-divider vertical></v-divider>
        <span class="mx-3 d-none d-sm-flex" style="cursor: pointer;" @click="test"
          ><u>삭제</u></span
        > -->
      </v-flex>

      <!-- 선 -->
      <v-flex sm12 xs12>
        <v-divider class="my-3"></v-divider>
      </v-flex>

      <!-- content -->
      <v-flex sm8 xs12>
        {{ context }}
      </v-flex>

      <!-- map -->
      <v-flex sm4 xs12>
        <div id="map" style="height: 300px; width: 100%;" class="my-2"></div>
      </v-flex>

      <!-- 선 -->
      <v-flex sm12 xs12>
        <v-divider class="my-3"></v-divider>
      </v-flex>

      <!-- 인원 sm화면 -->
      <v-flex sm12 class="outerFlex d-none d-sm-flex">
        <v-container>
          <v-row>
            <v-col>
              <div style="font-size: 2rem; text-align: center;" class="fw800">
                참여하기
                <v-btn class="mx-2 " fab dark large color="pink">
                  <v-icon dark>mdi-heart</v-icon>
                </v-btn>
                <v-btn class="mx-2" fab dark large color="grey">
                  <v-icon dark>mdi-heart</v-icon>
                </v-btn>
                취소하기
              </div>
            </v-col>
          </v-row>
          <v-row>
            <v-col>
              <div style="font-size: 1rem; text-align: center;">
                <span>
                  참여인원
                </span>
                <span style="font-size: 2rem;">{{ participants }} / {{ limit_num }}</span>
                <span>
                  제한인원
                </span>
              </div>
            </v-col>
          </v-row>
        </v-container>
      </v-flex>

      <!-- 인원 xs화면 -->
      <v-flex xs12 class="outerFlex d-flex d-sm-none">
        <v-container class="ma-0">
          <v-row>
            <v-col>
              <div style=" text-align: center;" class="fw800">
                <v-btn class="mx-2 " fab dark large color="pink">
                  <v-icon dark>mdi-heart</v-icon>참여
                </v-btn>
                <v-btn class="mx-2" fab dark large color="grey">
                  <v-icon dark>mdi-heart</v-icon>취소
                </v-btn>
              </div>
            </v-col>
          </v-row>
          <v-row>
            <v-col>
              <div style="font-size: 1rem; text-align: center;">
                <span>
                  참여인원
                </span>
                <span style="font-size: 2rem;">{{ participants }} / {{ limit_num }}</span>
                <span>
                  제한인원
                </span>
              </div>
            </v-col>
          </v-row>
        </v-container>
      </v-flex>

      <!-- 선 -->
      <v-flex sm12 xs12>
        <v-divider class="my-3"></v-divider>
      </v-flex>

      <!-- 댓글제목-->
      <v-flex sm12 xs12>
        <span>전체댓글</span>
        <span style="color: orange;"> {{ commentList.length }}</span>
        <span>개</span>
        <v-divider class="my-2"></v-divider>
      </v-flex>

      <v-flex sm11 xs12>
        <v-textarea
          clearable
          outlined
          clear-icon="mdi-cancel"
          label="댓글 입력"
          v-model="contents"
          hide-details
        ></v-textarea>
      </v-flex>

      <v-flex sm1 xs12 style="text-align: right;">
        <v-btn
          @click="writeComment"
          style="height: 100%;width: 100%;background: #82b1ff; color: white;"
          class="fw700"
          >댓글입력</v-btn
        >
      </v-flex>
    </v-layout>

    <v-flex sm12 xs12>
      <v-divider class="my-2"></v-divider>
    </v-flex>

    <!-- 댓글리스트 -->
    <v-hover
      v-slot:default="{ hover }"
      v-for="(comment, index) in commentList"
      :key="'commentapp' + index"
    >
      <v-container
        class="pa-0"
        v-bind:style="{
          width: '100%',
          background: hover ? '#e9e9e9' : 'white',
        }"
      >
        <v-row class="py-1">
          <v-col class="py-1">
            <v-menu offset-y>
              <template v-slot:activator="{ on }">
                <span
                  class="mr-3"
                  v-on="on"
                  style="cursor: pointer; text-decoration: underline; font-weight: bolder;"
                  >{{ comment.id }}</span
                >
              </template>
              <v-list>
                <v-list-item
                  @click="
                    clickUserId = comment.id
                    userInfoDailogFlag = !userInfoDailogFlag
                  "
                >
                  <v-list-item-title
                    ><v-icon color="primary">mdi-account</v-icon
                    ><span>유저정보보기</span></v-list-item-title
                  >
                </v-list-item>
                <v-list-item @click="test">
                  <v-list-item-title
                    ><v-icon style="color: red;">mdi-alarm-light</v-icon
                    ><span>유저신고하기</span></v-list-item-title
                  >
                </v-list-item>
              </v-list>
            </v-menu>
          </v-col>
          <v-spacer></v-spacer>
          <v-col class="py-1" style="text-align: right;">
            <span
              style="cursor: pointer; text-decoration: underline;"
              v-on:click="test2(item.id)"
              class="mx-1"
              >수정</span
            >
            <span
              style="cursor: pointer; text-decoration: underline;"
              @click="test2(item.id)"
              class="mx-1"
              >삭제</span
            >
          </v-col>
        </v-row>
        <v-row>
          <v-col> {{ comment.context }} </v-col>
        </v-row>
        <v-row style="background: white;">
          <v-col> <v-divider> </v-divider></v-col>
        </v-row>
      </v-container>
    </v-hover>

    <v-btn @click="userInfoDailogFlag = !userInfoDailogFlag">
      {{ userInfoDailogFlag }}</v-btn
    >

    <UserInfoDailog
      v-if="userInfoDailogFlag"
      :userId="clickUserId"
      v-on:updateUserDialogFlag="updateUserDialogFlag"
    ></UserInfoDailog>
  </v-container>
</template>

<script>
import UserInfoDailog from '../components/UserInfoDialog'
export default {
  name: 'Board',
  components: {
    UserInfoDailog,
  },
  data: () => ({
    clickUserId: 1,
    board_id: 55,
    board_locationx: 37.4954257,
    board_locationy: 127.039,
    category: '배달',
    context:
      '떡볶이 같이 드실 분 찾아요! 장소는 역삼동이고요. 저희 뭐에서 만들어먹어요!?',
    write_date: '2020-06-02 16:05:00',
    limit_num: '5',
    participants: '2',
    title: '떡볶이 대환영! 저랑 같이 먹어여',
    deadline_date: '2020-06-06 20:00:30',
    user_id: '1',
    nick_name: 'nickname',
    tmp_date: '2020-06-02 23:00:00',
    remainTime: {},
    remainTimeFunction: null,
    map: null,
    commentList: [
      {
        id: 'nickname',
        context:
          '떡볶이 먹고 싶은데 너무 멀어요!!! 일단 참여해여ㅋ 떡볶이 먹고 싶은데 너무 멀어요!!! 일단 참여해여ㅋ 떡볶이 먹고 싶은데 너무 멀어요!!! 일단 참여해여ㅋ',
        board: '?',
        user: '??',
      },
      {
        id: 'nickname2',
        context: '떡볶이 먹고 싶은데 너무 멀어요!!! 일단 참여해여ㅋ',
        board: '?',
        user: '??',
      },
      {
        id: 'nickname3',
        context: '떡볶이 먹고 싶은데 너무 멀어요!!! 일단 참여해여ㅋ',
        board: '?',
        user: '??',
      },
      {
        id: 'nickname4',
        context: '떡볶이 먹고 싶은데 너무 멀어요!!! 일단 참여해여ㅋ',
        board: '?',
        user: '??',
      },
      {
        id: 'nickname5',
        context: '떡볶이 먹고 싶은데 너무 멀어요!!! 일단 참여해여ㅋ',
        board: '?',
        user: '??',
      },
      {
        id: 'nickname6',
        context: '떡볶이 먹고 싶은데 너무 멀어요!!! 일단 참여해여ㅋ',
        board: '?',
        user: '??',
      },
      {
        id: 'nickname7',
        context: '떡볶이 먹고 싶은데 너무 멀어요!!! 일단 참여해여ㅋ',
        board: '?',
        user: '??',
      },
    ],
    headers: [
      { text: 'id', value: 'id', width: 10 },
      { text: 'context', value: 'context', width: 5 },
      { text: 'controller', value: 'controller', width: 10, align: 'right' },
    ],
    userInfoDailogFlag: false,
    contents: '',
  }),
  created() {
    if (!(window.kakao && window.kakao.maps && window.kakao.services))
      this.addMapScript()
    this.remainTimeFunction = setInterval(this.calRemainingTime, 1000)
  },
  mounted() {
    this.drawMap()
  },
  computed: {},
  beforeDestroy() {
    clearInterval(this.remainTimeFunction)
  },
  methods: {
    test() {
      console.log('ddd')
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
      var mapContainer = document.getElementById('map') // 지도를 표시할 div
      var position = new kakao.maps.LatLng(
        this.board_locationx,
        this.board_locationy
      )
      var mapOption = {
        center: position, // 지도의 중심좌표
        level: 4, // 지도의 확대 레벨
      }

      this.map = new kakao.maps.Map(mapContainer, mapOption) // 지도를 생성합니다

      new kakao.maps.Marker({
        map: this.map,
        position: position,
      })
    },
    calRemainingTime() {
      let today = new Date()
      // console.log('calRemainingTime')
      //현재시간 계산
      let curDate =
        today.getFullYear() +
        '-' +
        (today.getMonth() + 1) +
        '-' +
        today.getDate() +
        ' ' +
        today.getHours() +
        ':' +
        today.getMinutes() +
        ':' +
        today.getSeconds()
      //현재시간과 마감날짜 계산
      var diff = Math.floor(
        (Date.parse(this.deadline_date) - Date.parse(curDate)) / 1000
      )
      //마감시간이면 멈춘다.
      if (diff <= 0) {
        this.remainTime = {
          day: 0,
          hours: 0,
          minutes: 0,
          seconds: 0,
        }

        clearInterval(this.remainTimeFunction)
        return
      }

      var day = Math.floor(diff / (3600 * 24))
      var hours = Math.floor((diff - day * 3600 * 24) / 3600)
      var minutes = Math.floor((diff - day * 3600 * 24 - hours * 3600) / 60)
      var seconds = diff - day * 3600 * 24 - hours * 3600 - minutes * 60

      // console.log(day + ' ' + hours + ' ' + minutes + ' ' + seconds)
      // console.log(
      //   this.remainTime.day +
      //     ' ' +
      //     this.remainTime.hours +
      //     ' ' +
      //     this.remainTime.minutes +
      //     ' ' +
      //     this.remainTime.seconds
      // )

      this.remainTime = {
        day: day,
        hours: hours,
        minutes: minutes,
        seconds: seconds,
      }
      // }
    },
    timerColor() {
      if (
        this.remainTime.day == 0 &&
        this.remainTime.hours == 0 &&
        this.remainTime.minutes == 0 &&
        this.remainTime.seconds == 0
      ) {
        return '#ffa5a5'
      } else {
        return '#39dfed'
      }
    },
    updateUserDialogFlag(flag) {
      this.userInfoDailogFlag = flag
    },
    writeComment() {
      alert(this.contents)
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
