<template>
  <v-container>
    <v-row class="text-center">
      <v-col cols="12" class="mainFontSize">
          SubPage
      </v-col>
      <v-col>
        <v-btn @click="gotoMain">
          메인으로 가기
        </v-btn>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>

export default {
  name: 'Sub',
  components: {
  },
  data: () => ({
  }),
  methods : {
    gotoMain() {
      // this.$router.push({ path: "Main", query: { yno: yno } });
      // this.$router.push({ path: "/"});
      this.$router.push({ name: "Main"});
    }
  }  
  }
</script>

<style scoped>
.mainFontSize{
    font-size:100px
}
</style>
