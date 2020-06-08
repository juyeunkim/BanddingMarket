<template>
  <v-container class="Nanum">
   test
  </v-container>
</template>

<script>
export default {
  name: 'test',
  components: {
  },
  data: () => ({
   
  }),
  created() {
  },
  mounted() {
  },
  computed: {},
  beforeDestroy() {},
  methods: {
   
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
