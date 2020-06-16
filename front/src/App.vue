<template>
  <v-app id="App">
    
    <Header></Header>
    <Drawer></Drawer>

    <v-content>
      <router-view/>
    </v-content>


    <!-- <Footer></Footer> -->
  </v-app>
</template>

<script>
import Header from "./components/Header"
import Drawer from "@/components/Drawer";
// import Footer from "./components/Footer"


export default {
  name: 'App',
  components: {
    Header,
    Drawer

    // Footer
  },

  data: () => ({
    //
  }),
};
</script>
