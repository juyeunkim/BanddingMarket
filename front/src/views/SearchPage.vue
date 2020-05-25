<template>
  <v-container>
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

      <!-- {{ searchList }} -->

      <!-- 인기 검색어 -->
      <v-flex sm9 xs12>
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
      <v-flex sm9 xs12>
        <v-card color="" class="my-2">
          <v-card-title> 음식 실시간 BEST5</v-card-title>

          <v-card-text>
            <p
              v-for="(food, index) in bestFoodList"
              :key="'bestFood' + index"
              
            >
              {{ food[0] }}
              <v-chip @click="clickPopularChip(food[1])" small class="mx-1" color="#d3f9fa"> <b>#{{ food[1] }}</b> </v-chip>
            </p>
          </v-card-text>
          <!-- <v-card-subtitle>인기 검색어</v-card-subtitle> -->

          <!-- <v-card-actions></v-card-actions> -->
        </v-card>
        <v-divider class="my-2"></v-divider>
      </v-flex>
    </v-layout>
  </v-container>
</template>

<script>
import Constant from '../vuex/Constant'
import { mapState } from 'vuex'

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
  },
  computed: {
    ...mapState(['searchList']),
  },
}
</script>

<style scoped></style>
