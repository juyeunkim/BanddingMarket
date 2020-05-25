<template>
  <v-app id="App">
    
    <Header></Header>

    <v-content>
      <router-view/>
    </v-content>


    <Footer></Footer>
  </v-app>
</template>

<script>
import Header from "./components/Header"
import Footer from "./components/Footer"


export default {
  name: 'App',
  components: {
    Header,
    Footer
  },

  data: () => ({
    //
  }),
};
</script>
