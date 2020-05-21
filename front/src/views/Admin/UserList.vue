<template>
  <v-container>
    <v-row class="text-center">
      <v-col cols="12" class="mainFontSize">
          MainPage
      </v-col>
      
    </v-row>

    <v-col cols="12">
          <HelloWorld text="ddd" class ="reviewCard"></HelloWorld>
      </v-col>
  </v-container>

  
</template>

<script>
import HelloWorld from '../../components/HelloWorld.vue';

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
@import url('https://fonts.googleapis.com/css2?family=Do+Hyeong&display=swap');
.v-card--reveal {
  align-items: center;
  bottom: 0;
  justify-content: center;
  opacity: 0.7;
  position: absolute;
  width: 100%;
}
.Do {
  font-family: 'Do Hyeon', sans-serif;
}
.reviewContent {
  text-align: -webkit-center; 
  border-radius: 10px; 
  background-color: #ededed; 
  border: 4px dashed #bdb5bd;
}

.mobilereviewContent {
  text-align: -webkit-center; 
  border-radius: 10px; 
  background-color: #ededed; 
  border: 2px dashed #bdb5bd;
}

.reviewCard {
  margin-bottom: 1em;
  margin-left:1em;
  margin-right:1em;
  padding-left: 1em;
  padding-right: 1em;
}

.mobilereviewCard {
  margin-bottom: 1em;
  padding-left: 1em;
  padding-right: 1em;
  padding-bottom: 1em;
}
.container{
  border-style: solid;
  border-color: #82b1ff;
  border-radius: 20px;
  border-width: 8px;
  background-color: white; 
  padding-top: 0.5em; 
  margin-top: 1.5em; 
  max-width : 800px
}

.outerFlex {
  background: #dffbff;
  border-radius: 20px;
  padding: 10px;
  margin-top: 5px;
  margin-bottom: 5px;
}


</style>
