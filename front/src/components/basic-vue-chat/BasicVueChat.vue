<template>
  <div
    class="basic-vue-chat"
    @click="setEmojiPickerToggle(false)">
    <section class="window">
      <header class="window__header__container">
        <slot name="header">
          {{ this.chat_info.title }}
        </slot>
      </header>
      <section
        id="window__messages__container"
        class="window__messages__container">
        <slot>
          <messages-list
            :temp='temp'
            :feed="feed"
            class="messages-list"  />
        </slot>
      </section>
      <div class="window__input__container">
        <slot name="input-container">
          <input-container
            :toggle-emoji-picker="toggleEmojiPicker"
            @newOwnMessage="onNewOwnMessage"
            @openEmojiPicker="onOpenEmojiPicker"
          />
        </slot>
      </div>
    </section>

    <v-text-field style="margin-top:100px" v-model="temp"></v-text-field>
    
  </div>
</template>

<script>
import fb from "@/firebase/init";
import moment from 'moment'
import { scrollToBottom } from '../../helpers/scroll.js'
import MessagesList from './messages/MessagesList.vue'
import InputContainer from './input/InputContainer.vue'
export default {
  name: 'BasicVueChat',
  components: {
    MessagesList,
    InputContainer
  },
  props: {
    initialAuthorId: {
      type: Number,
      default: 0,
      required: false
    },
    attachMock: {
      type: Boolean,
      default: false,
      required: false
    },
    initialFeed: {
      type: Array,
      default: function () {
        return []
      },
      required: false
    },
    newMessage: {
      type: Object,
      default: function () {
        return {}
      },
      required: false
    }
  },
  data: function () {
    return {
      feed: [],
      authorId: 0,
      toggleEmojiPicker: false,
      chat_info:[],
      temp : ""
      
    }
  },
  watch: {
    newMessage: function (newValue) {
      this.pushToFeed(newValue)
      scrollToBottom()
    }
  },
  created () {
    let ref = fb.collection("chat").doc("w72rSxL30U1fcADuOe88").collection("messages").orderBy("timestamp");
    ref.onSnapshot(snapshot => {
      snapshot.docChanges().forEach(change => {
        if ((change.type == "added")) {
          let doc = change.doc;
          this.feed.push({
            nickname: doc.data().nickname,
            message: doc.data().message,
            timestamp: moment(doc.data().timestamp).format("LTS"),
            img: doc.data().img
          });
          scrollToBottom()
        }
      });
    });

    fb.collection('room').doc('w72rSxL30U1fcADuOe88').get()
        .then( info => {
          this.chat_info = info.data()
          console.log(this.chat_info)
        })

    // if (this.attachMock) {
    //   import('./mocks/mock-messages-list.js')
    //     .then(mockData => {
    //       this.feed = mockData.default.feed
    //       this.setAuthorId(mockData.default.authorId)
    //     })
    //     .catch(error => {
    //       console.error('Failed to load mock data from file. ', error)
    //     })
    // } else {
    //   this.feed = this.initialFeed
    //   this.authorId = this.initialAuthorId
    // }
  },
  methods: {
    setEmojiPickerToggle (toggle) {
      this.toggleEmojiPicker = toggle
    },
    pushToFeed (element) {
      this.feed.push(element)
    },
    onNewOwnMessage (message, image, imageUrl) {
      // 메세지 전송
      
      const newOwnMessage = {
        message: message,
        nickname: this.temp,
        timestamp: Date.now(),
        img: ''
      }
      console.log(image +" "+imageUrl)
      fb.collection("chat").doc("w72rSxL30U1fcADuOe88")
        .collection("messages").add(newOwnMessage);
      // this.pushToFeed(newOwnMessage)
      scrollToBottom()
      this.$emit('newOwnMessage', message)
    },
    onOpenEmojiPicker (toggle) {
      this.setEmojiPickerToggle(toggle)
    }
  }
}
</script>

<style lang="scss">
@import "../../assets/scss/main.scss";
</style>