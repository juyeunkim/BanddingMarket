<template>
  <v-app-bar id="app-toolbar" app flat color="blue lighten-1" v-if="$route.name != 'Main'" >
    <!-- <v-btn @click="test2" text>set</v-btn> -->
    <!-- <v-btn @click="test2" text>delete</v-btn> -->
    <v-btn dark icon @click.stop="onClickDrawer">
      <v-icon>mdi-view-list</v-icon>
    </v-btn>

    <v-spacer></v-spacer>
    <v-menu offset-y>
      <template v-slot:activator="{ on }">
        <v-icon large v-on="on" style="cursor: pointer;">mdi-account</v-icon>
      </template>
      <v-list>
        <v-list-item >
          <v-list-item-title style="text-align: center">
            <loginDialog></loginDialog>
          </v-list-item-title>
        </v-list-item>
        <v-list-item>
          <v-list-item-title style="text-align: center">
            <joinDialog></joinDialog>
          </v-list-item-title>
        </v-list-item>
      </v-list>
    </v-menu>
    
  </v-app-bar>
</template>

<script>
import { mapMutations, mapState } from 'vuex'
import loginDialog from './LoginDialog'
import joinDialog from './JoinDialog'

export default {
  components: {
    loginDialog,
    joinDialog
  },
  data: () => ({}),
  computed: {
    ...mapState(['drawer']),
  },
  mounted() {},
  beforeDestroy() {},
  methods: {
    ...mapMutations(['setDrawer']),
    onClickDrawer() {
      this.setDrawer(!this.drawer)
    },
    test() {
      alert('dd')
    },
  },
}
</script>
<style scoped></style>
