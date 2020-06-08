<template>
  <v-container class="fullpage-container">
    <v-layout>
      <v-flex sm11 xs11 class="my-3">
        <vue-tags-input
          v-model="tag"
          :tags="tags"
          @tags-changed="(newTags) => (tags = newTags)"
          placeholder="키워드를 입력해주세요"
          @before-adding-tag="checkTag"
        />
      </v-flex>
      <v-flex sm1 xs1 class="my-3">
        <v-btn @click="searchByKeyword" style="width:100%;height:100%">
          검색
        </v-btn>
      </v-flex>
    </v-layout>

    <v-layout>
      
    </v-layout>

    <v-layout>
      <v-flex sm12 xs12 class="my-3">
        <v-hover
          v-slot:default="{ hover }"
          v-for="(board, index) in searchList"
          :key="'board' + index"
        >
          <v-container
            class="my-3 boardCard"
            v-bind:style="{
              background: index % 2 == 0 ? '#e7e7e7' : '#f7f7f7',
              'box-shadow':hover ? '3px 3px #5a5a5a':'none',
              cursor:'pointer'
            }"
            @click="goToBoardDetail(board.board_id)"
            
          >
            <v-row>
              <v-col cols="9" class="pr-0">
                <v-icon>mdi-map-marker-radius-outline</v-icon>내 위치로부터
                {{
                  calDistance(board.board_locationX, board.board_locationY)
                }}
                |
                <v-icon color="orange">mdi-star</v-icon>평점 {{ board.user == undefined ? "" : board.user.reputation }}
              </v-col>
              <v-col
                v-bind:style="{
                  color:
                    calDate(board.deadlineDate) == '마감'
                      ? '#999999'
                      : '#ff0000',
                }"
                style="text-align: right; font-size:15px"
                cols="3"
                class="pl-0"
              >
                {{ calDate(board.deadlineDate) }}
              </v-col>
            </v-row>
            <v-row>
              <v-col class="py-0" >
                <v-chip
                  v-for="(keyword, index) in board.keyword.split('#').slice(1)"
                  :key="board.board_id + ' ' + index + ' ' + keyword"
                  color="#14d3ff"
                  class="mx-1"
                  style="color:white; font-weight: bold; border-radius: 6px !important"
                >
                  #{{ keyword }}
                </v-chip>
              </v-col>
            </v-row>
            <v-row>
              <v-col>
                <b style="font-size:1.5rem">{{board.title}}</b>
              </v-col>
            </v-row>
            <v-row> </v-row>

            <!-- <v-card-actions></v-card-actions> -->
            <!-- <v-divider></v-divider> -->
          </v-container>
        </v-hover>
      </v-flex>
    </v-layout>
  </v-container>
</template>

<script>
import VueTagsInput from '@johmun/vue-tags-input'
import { mapState } from 'vuex'
import Constant from '../vuex/Constant'
// import axios from 'axios'

export default {
  name: 'SearchPage',
  components: {
    VueTagsInput,
  },
  data: () => ({
    tags: [],
    tag: '',
    curPosition: [],
    curDate: '',
  }),
  created() {
    this.getCurPosition()
    this.$store.dispatch(Constant.SET_SEARCHALLLIST)
  },
  methods: {
    goToBoardDetail(id){
      this.$router.push({ path: "/board", query: { id: id } });
    },
    searchByKeyword(){
      if(this.tags.length == 0){
        alert("검색어를 입력해주세요")
      }else{
        var keyword = ""
        for (let index = 0; index < this.tags.length; index++) {
          keyword += "#"+this.tags[index].text;
          // console.log(this.tags[index].text)
        }
        console.log(keyword)
        this.$store.dispatch(Constant.SEARCH_BYKEYWORD,{
          keyword: keyword
        })
      }
    },
    test(){
      alert("ddd")
    },
    getCurPosition() {
      if (navigator.geolocation) {
        // GeoLocation을 이용해서 접속 위치를 얻어옵니다
        navigator.geolocation.getCurrentPosition((position) => {
          var lat = position.coords.latitude // 위도
          var lon = position.coords.longitude // 경도
          this.curPosition = [lat, lon]
          console.log(lat + ' ' + lon)
        })
      } else {
        this.curPosition = [0, 0]
        console.log(0 + ' ' + 0)
      }
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
    calDistance(lat, lon) {
      var curLat = this.curPosition[0]
      var curLon = this.curPosition[1]
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
          return '마감 ' + day + '일전'
        } else if (hours > 0) {
          return '마감 ' + hours + '시간전'
        } else if (minutes > 0) {
          return '마감 ' + minutes + '분전'
        } else {
          return '마감 ' + seconds + '초전'
        }
      }
    },
  },
  computed: {
    ...mapState(['searchList']),
  },
}
</script>

<style lang="css">
/* style the background and the text color of the input ... */
.vue-tags-input {
  background: #fd9696;
  max-width: 100%;
  min-width: 100%;
}
.ti-input {
  height: 55px;
  font-size: 2rem;
}
.vue-tags-input .ti-input {
  border: 3px solid #ccc;
  border-radius: 10px;
}
/* default styles for all the tags */
.vue-tags-input .ti-tag {
  position: relative;
  background: #20cbff;
  color: #ffffff;
  border-radius: 10px;
}

.boardCard {
  border-radius: 10px;
}
</style>
