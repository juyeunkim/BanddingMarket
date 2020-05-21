<template>
  <v-container>
    <v-row class="text-center">
      <div class="cen">
            <input type="text" v-model="newData">
            <v-btn @click="insertData">입력</v-btn>
            <div v-if="flag2" >아웃풋: {{newData}}</div>
            
      </div>  


      <v-col cols="12" class="mainFontSize">
          AdminPage
          
          <h6>{{message}}</h6>
          <v-btn @click="getData">
              data 출력
          </v-btn>
          <br/>

          <div v-if="flag">
            <ul>
              <li v-for="(result,index) in results" v-bind:key="index"  class="shadow" >{{result}}</li>
            </ul>
          </div>
      </v-col>
      <v-col>
        <v-btn @click="gotoMain">
          메인으로 가기
        </v-btn>
        
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
  import axios from "axios";



export default {
  name: 'Admin',
  components: {
  },
  data: () => ({message: 'This is Admin page!',
  results:"",flag:false,newData:"",flag2:false
  }),
  methods : {
    
    getData: function() {
      axios.get('https://raw.githubusercontent.com/joshua1988/doit-vuejs/master/data/demo.json').then(response => {
            // console.log(response.data)
            // var list = []
            // response.data.forEach(element => list.push(element.store_name))
            console.log(response.data.fe1)
            this.results = response.data
            this.flag = true
            // this.results = list
          })
    },      

    insertData: function(){ 
      this.flag2 = true
    }
     
    
    
    
    ,
    gotoMain() {
      // this.$router.push({ path: "Main", query: { yno: yno } });
      // this.$router.push({ path: "/"});
      this.$router.push({ name: "Main"});
    }
  }  
  }
</script>

<style scoped>
.mainFontSize{
    font-size:100px
}
.shadow{
  box-shadow: 5px 10px 10px rgba(0, 0, 0, 0.76);
  font-size: 50px;
}
input {
  border-style: groove;
  width: 200px;
}
button{
  border-style: groove;
}

.cen{
  align-content: center;
}
</style>
