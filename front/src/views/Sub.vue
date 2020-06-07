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

          <!-- <v-card-actions></v-card-actions> -->
        </v-card>
        <v-divider class="my-2"></v-divider>
      </v-flex>
    </v-layout>

    <!-- filter button -->
    <v-layout justify-center>
      <v-flex sm12 xs12>
        <v-btn text @click.stop="filterDialog = true"
          ><v-icon>mdi-filter-variant</v-icon> 검색 필터</v-btn
        >
        <v-divider class="mb-3"></v-divider>
      </v-flex>
    </v-layout>

    <!-- 스크롤 -->
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
            :class="{ 'grey-bg': index % 2 == 0 }"
          >
            {{ word }}
          </v-card>
        </scroller>
      </v-flex>
    </v-layout>

    <!-- 필터 dialog -->
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
    filterDialog: false,
    categoryFood: true,
    categoryDelivery: true,
    distance: 100,
    distances: [100, 200, 300],
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
        // this.$store.dispatch(Constant.SET_SEARCHLIST, {
        //   searchList: ['aaa', 'bbbb'],
        // })
        // console.log('ddd')
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
    categoryButtonColor(category) {
      if (category == 'food' && this.categoryFood) {
        return 'primary'
      } else if (category == 'delivery' && this.categoryDelivery) {
        return 'primary'
      } else {
        return 'white'
      }
    },
    categoryButtonClicked(category) {
      if (category == 'food' && this.categoryFood && !this.categoryDelivery)
        return
      if (category == 'delivery' && !this.categoryFood && this.categoryDelivery)
        return

      if (category == 'food') {
        this.categoryFood = !this.categoryFood
      } else if (category == 'delivery') {
        this.categoryDelivery = !this.categoryDelivery
      }
    },
    distanceButtonColor(distance) {
      // console.log(distance)
      if (this.distance == distance) return 'primary'
      else return 'white'
    },
    distanceButtonClicked(distance) {
      this.distance = distance
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
  /* width: 100%; */
  /* height: 100%; */
  position: absolute; 
  top:0; 
  bottom:0; 
  left:0; 
  right:0;
}
.grey-bg {
  background: #eee;
}
.selectButtonArea {
  text-align: center;
}
</style>
