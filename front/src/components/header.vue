<template>
  <v-app-bar
    id="app-toolbar"
    app
    flat
    color="blue lighten-1"
    v-if="$route.name != 'Main'"
  >
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
        <v-list-item v-if="$cookies.get('token') != ''">
          <v-list-item-title style="text-align: center">
            <v-container>
              <v-row class="outer">
                <v-col cols="12" style="align:'right'">
                  <v-icon style="font-size:100px">
                    mdi-account
                  </v-icon>
                </v-col>
              </v-row>
              <v-row>
                <v-col ols="12" style="align:'right'">
                  <div>
                    <span>{{ $cookies.get('userName') }}님 안녕하세요?</span>
                  </div>
                </v-col>
              </v-row>
            </v-container>
          </v-list-item-title>
        </v-list-item>
        <v-list-item v-if="$cookies.get('token') == ''">
          <v-list-item-title style="text-align: center">
            <loginDialog></loginDialog>
          </v-list-item-title>
        </v-list-item>
        <v-list-item v-if="$cookies.get('token') == ''">
          <v-list-item-title style="text-align: center">
            <joinDialog></joinDialog>
          </v-list-item-title>
        </v-list-item>
        <v-list-item v-if="$cookies.get('token') != ''">
          <v-list-item-title style="text-align: center">
            <v-btn @click="goToBoardWrite"><v-icon>mdi-pencil</v-icon>글쓰러가기</v-btn>
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
    joinDialog,
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
    goToBoardWrite() {
      this.$router.push({ path: "/boardwrite" });
    }
  },
}
</script>
<style scoped></style>
