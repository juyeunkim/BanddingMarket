<template>
  <v-app-bar
      app
      color="primary"
      dark
    >
      <div class="d-flex align-center">
        <v-img
          alt="Vuetify Logo"
          class="shrink mr-2"
          contain
          src="https://cdn.vuetifyjs.com/images/logos/vuetify-logo-dark.png"
          transition="scale-transition"
          width="40"
        /> 

        <v-img
          alt="Vuetify Name"
          class="shrink mt-1 hidden-sm-and-down"
          contain
          min-width="100"
          src="https://cdn.vuetifyjs.com/images/logos/vuetify-name-dark.png"
          width="100"
        />
      </div>

      <v-spacer></v-spacer>
      
      <v-btn
        :to="{ name: 'Sub'}"
        text
      >
        <span class="mr-2">Subpage로 가기</span>
        <v-icon>mdi-open-in-new</v-icon>
      </v-btn>
      <v-btn
        :to="{ name: 'Admin'}"
        text
      >
        <span class="mr-2">Admin 가기</span>
        <v-icon>mdi-open-in-new</v-icon>
      </v-btn>

      <v-btn
        :to="{ name: 'UserList'}"
        text
      >
        <span class="mr-2">사용자 목록 가기</span>
        <v-icon>mdi-open-in-new</v-icon>
      </v-btn>
    </v-app-bar>
</template>

<script>
  export default {
    name: 'Header',
    data: () => ({
    }),
  }
</script>
