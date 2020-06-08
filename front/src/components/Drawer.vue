<template>
  <v-navigation-drawer
      v-model="realDrawer"
      absolute
      temporary
    >
      <v-list
        nav
        dense
      >
        <v-list-item-group
          active-class="deep-purple--text text--accent-4"
        >
          <v-list-item>
            <v-list-item-icon>
              <v-icon>mdi-mdi-magnify</v-icon>
            </v-list-item-icon>
            <v-list-item-title>검색</v-list-item-title>
          </v-list-item>

          <v-list-item>
            <v-list-item-icon>
              <v-icon>mdi-map</v-icon>
            </v-list-item-icon>
            <v-list-item-title>지도검색</v-list-item-title>
          </v-list-item>

        </v-list-item-group>
      </v-list>
    </v-navigation-drawer>
</template>

<script>
import { mapMutations, mapState } from "vuex";

export default {
  props: {
    opened: {
      type: Boolean,
      default: false
    }
  },
  data: () => ({
    links: [
      {
        to: "/",
        icon: "mdi-home",
        text: "Home"
      },
      {
        to: "/search",
        icon: "mdi-card-search",
        text: "맛집 검색"
      },
      {
        to: "/nearbyStore",
        icon: "mdi-map",
        text: "근처 음식점"
      },
      {
        to: "/aboutus",
        icon: "mdi-human-greeting",
        text: "AboutUs"
      },
      
    ]
  }),
  computed: {
    ...mapState(['drawer']),
    realDrawer: {
      get() {
        return this.drawer;
      },
      set(val) {
        this.setDrawer(val);
      }
    }
  },

  methods: {
    ...mapMutations(["setDrawer"]),
  }
};
</script>
