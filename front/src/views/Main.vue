<template>
  <v-container>
    <v-row class="text-center">
      <v-col cols="12" class="mainFontSize">
          MainPage
      </v-col>
      <v-col cols="12">
          <HelloWorld text="ddd"></HelloWorld>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
import HelloWorld from '../components/HelloWorld';

export default {
  name: 'Main',

  components: {
    HelloWorld,
  },
    data: () => ({
    }),
  }
</script>

<style scoped>
.mainFontSize{
    font-size:100px
}
</style>
