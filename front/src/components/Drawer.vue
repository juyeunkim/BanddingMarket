<template>
  <v-navigation-drawer v-model="realDrawer" absolute temporary>
    <v-list nav dense>
      <v-list-item-group active-class="deep-purple--text text--accent-4">
   
        <v-list-item v-for="(link, index) in links"
            :key="index+'link'"
            @click="goTo(link.to)">
          <v-list-item-icon>
            <v-icon>{{link.icon}}</v-icon>
          </v-list-item-icon>
          <v-list-item-title>{{link.text}}</v-list-item-title>
        </v-list-item>
      </v-list-item-group>
    </v-list>
  </v-navigation-drawer>
</template>

<script>
import { mapMutations, mapState } from 'vuex'

export default {
  props: {
    opened: {
      type: Boolean,
      default: false,
    },
  },
  data: () => ({
    links: [
      {
        to: '/',
        icon: 'mdi-home',
        text: 'Main',
      },
      {
        to: '/search',
        icon: 'mdi-card-search',
        text: '키워드검색',
      },
      {
        to: '/mapsearch',
        icon: 'mdi-map',
        text: '지도검색',
      },
    ],
  }),
  computed: {
    ...mapState(['drawer']),
    realDrawer: {
      get() {
        return this.drawer
      },
      set(val) {
        this.setDrawer(val)
      },
    },
  },
  methods: {
    ...mapMutations(['setDrawer']),
    goTo(route) {
      this.$router.push({ path: route });
    },
  },
}
</script>
