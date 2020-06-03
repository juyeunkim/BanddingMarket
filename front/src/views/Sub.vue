<template>
  <v-container>
    <v-layout wrap mt-5 class="mx-3" sm12>
      <!--STORE title-->
      <v-flex sm12 xs12>
        <p class="ma-0 Do" style="font-size: 1.8em;">
          Store
        </p>
        <v-divider class="mb-5 mt-1"></v-divider>
      </v-flex>
    </v-layout>
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
