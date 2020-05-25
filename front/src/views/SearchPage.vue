<template>
  <v-container class="fullpage-container">
    <v-layout wrap mt-5 class="mx-3" sm12 justify-center>
      <!--Search-->
      <v-flex sm10 xs12>
        <v-text-field
          label="검색어를 입력하세요."
          single-line
          outlined
          prepend-inner-icon="mdi-magnify"
          autofocus
          @keydown.enter="search"
          v-model="keyword"
        ></v-text-field>
      </v-flex>

      <!-- <v-btn @click="test">
        test
      </v-btn> -->

      <!-- {{ searchList }} -->

      <!-- 인기 검색어 -->
      <v-flex sm9 xs12 v-if="!searchList">
        <v-card color="" class="my-2">
          <v-card-title> 인기검색어</v-card-title>

          <v-card-text>
            <v-chip
              v-for="(word, index) in popularSearchList"
              :key="'popular' + index"
              class="mx-1"
              @click="clickPopularChip(word)"
            >
              {{ word }}
            </v-chip>
          </v-card-text>
          <!-- <v-card-subtitle>인기 검색어</v-card-subtitle> -->

          <!-- <v-card-actions></v-card-actions> -->
        </v-card>
        <v-divider class="my-2"></v-divider>
      </v-flex>

      <!-- 음식 베스트 -->
      <v-flex sm9 xs12 v-if="!searchList">
        <v-card color="" class="my-2">
          <v-card-title> 음식 실시간 BEST5</v-card-title>

          <v-card-text>
            <p v-for="(food, index) in bestFoodList" :key="'bestFood' + index">
              {{ food[0] }}
              <v-chip
                @click="clickPopularChip(food[1])"
                small
                class="mx-1"
                color="#d3f9fa"
              >
                <b>#{{ food[1] }}</b>
              </v-chip>
            </p>
          </v-card-text>
          <!-- <v-card-subtitle>인기 검색어</v-card-subtitle> -->

          <!-- <v-card-actions></v-card-actions> -->
        </v-card>
        <v-divider class="my-2"></v-divider>
      </v-flex>
    </v-layout>
    <v-layout justify-center>
      <v-flex sm12 xs12>
        <v-btn text><v-icon>mdi-filter-variant</v-icon> 검색 필터</v-btn>
        <v-divider class="mb-3"></v-divider>
      </v-flex>
    </v-layout>
    <v-layout sm12 xs12 style="position: relative;height:80%;overflow: hidden;">
      <v-flex>
        <scroller
          :on-refresh="refresh"
          :on-infinite="infinite"
          refreshText="새로고침"
          class="page"
          style="position: absolute"
        >
          <v-card
            v-for="(word, index) in searchList"
            :key="'searchList' + index"
            class="pa-1 ma-2"
            :class="{'grey-bg': index % 2 == 0}"
          >
            {{ word }}
          </v-card>
        </scroller>
      </v-flex>
    </v-layout>
  </v-container>
</template>

<script>
import Constant from '../vuex/Constant'
import { mapState } from 'vuex'
// import axios from 'axios'

export default {
  name: 'SearchPage',
  components: {},
  data: () => ({
    keyword: '',
    popularSearchList: ['떡볶이', '하이'],
    bestFoodList: [
      ['어쩌구저쩌꾸', '떡볶이'],
      ['따뜻한저쩌꾸', '해장국'],
    ],
    refreshFlag: false,
  }),
  created() {
    //   console.log(this.$store.getters.searchList)
  },
  methods: {
    search() {
      console.log('serach Function')
      this.$store.dispatch(Constant.SET_SEARCHLIST, {
        searchList: ['aaa', 'bbbb'],
      })
    },
    clickPopularChip(word) {
      this.keyword = word
      this.search()
    },
    refresh(done) {
      //   console.log('re')
      //   axios
      //     .get(
      //       'https://raw.githubusercontent.com/joshua1988/doit-vuejs/master/data/demo.json'
      //     )
      //     .then((response) => {
      //       // console.log(response.data)
      //       // var list = []
      //       // response.data.forEach(element => list.push(element.store_name))
      //       this.search()
      //       console.log(response.data)
      //       done()
      //       this.refreshFlag = true
      //     })
      done()
    },
    infinite(done) {
      setTimeout(() => {
        this.$store.dispatch(Constant.SET_SEARCHLIST, {
          searchList: ['aaa', 'bbbb'],
        })
        console.log('ddd')
        done()
      }, 1000)
      //   this.search()
      //   this.search()
    },
    test() {
      this.$store.dispatch(Constant.SET_SEARCHLIST, {
        searchList: ['aaa', 'bbbb'],
      })
    },
  },
  computed: {
    ...mapState(['searchList']),
  },
}
</script>

<style scoped>
/* .image-container {
  overflow: hidden;
  display: flex;
  align-items: center;
  justify-content: center;
}
.page {
  display: block;
  text-align: center;
  color: #eee;
} */
.fullpage-container {
  width: 100%;
  height: 100%;
}
.grey-bg {
    background: #eee;
}
</style>
