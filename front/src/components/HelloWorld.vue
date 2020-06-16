<template>
  <v-container>
    <v-row class="text-center">
      <v-col cols="12">
        hELLOworld 컴포넌트 {{text}}
      </v-col>

    </v-row>
  </v-container>
</template>

<script>
  export default {
    name: 'HelloWorld',
    props : ['text', ],
    data: () => ({
    }),
  }
</script>
