<template>
  <v-app-bar id="app-toolbar" app flat color="blue lighten-1">
    <!-- <v-btn @click="test2" text>set</v-btn> -->
    <!-- <v-btn @click="test2" text>delete</v-btn> -->
    <v-btn dark icon @click.stop="onClickDrawer">
      <v-icon>mdi-view-list</v-icon>
    </v-btn>
    
  </v-app-bar>
</template>

<script>
import { mapMutations, mapState } from "vuex";

export default {
  components: {
  },
  data: () => ({
  }),
  computed: {
    ...mapState(['drawer']),
  },
  mounted() {
    
  },
  beforeDestroy() {
  },
  methods: {
    ...mapMutations(["setDrawer"]),
    onClickDrawer() {
      this.setDrawer(!this.drawer);
    },
  }
};
</script>
<style scoped>
</style>
