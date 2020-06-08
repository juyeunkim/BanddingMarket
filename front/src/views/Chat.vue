<template>
  <div>
      <h3>채팅 메인화면</h3>
      <h3>진행중인 채팅</h3>
      <div>
      <div v-for="message in messages" :key="message.id">
        <span class="text-info">[{{ message.nickname }}]:</span>
        <span>{{message.message}}</span>
        <span>{{message.timestamp}}</span>
        <span>{{message.user_id}}</span>
      </div>
    </div>
      <h3>채팅 리스트</h3>

      <!-- <ChatList />
      <Chatting /> -->
  </div>
  

</template>

<script>
import fb from '../firebase/init'
import moment from "moment";

export default {
    // component:{
    //     ChatList,Chatting
    // }
    data(){
        return{
            messages:[]
        }
    },
    created() {
        let ref = fb.collection("chat").doc("w72rSxL30U1fcADuOe88").collection("messages").orderBy("timestamp");
        ref.onSnapshot(snapshot => {
            snapshot.docChanges().forEach(change => {
                if(change.type == "added"){
                    let doc = change.doc;
                    this.messages.push({
                        nickname: doc.data().nickname,
                        message: doc.data().message,
                        timestamp: moment(doc.data().timestamp).format("LTS"),
                        img: doc.data().img
                    })
                }
            })
        })
        
        
    }

    
}
</script>

<style>

</style>