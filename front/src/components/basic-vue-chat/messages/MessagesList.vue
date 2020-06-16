<template>
  <transition-group
    name="messages-list"
    tag="div">
    <div
      v-for="(message, index) in feed"
      :key="index"
      class="messages-list-item">
      <message-own
        v-if="message.nickname === temp"
        :date="message.timestamp"
        :image-url="message.img"
        :contents="message.message" />
      <message-foreign
        v-else
        :nickname="message.nickname"
        :timestamp="message.timestamp"
        :img="message.img"
        :message="message.message" />
    </div>

    <!-- <v-text-field style="margin-top:100px" v-model="temp"></v-text-field> -->
  </transition-group>
</template>

<script>
import MessageOwn from './MessageOwn.vue'
import MessageForeign from './MessageForeign.vue'

export default {
  name: 'MessagesList',
  components: {
    MessageOwn,
    MessageForeign
  },
  props: {
    feed: {
      type: Array,
      default: function () {
        return []
      },
      required: false
    },
    authorId: {
      type: Number,
      default: 0,
      required: false
    },
    temp: {
      type: String,
      required: false
    },
  },
  data(){
    return {
      current_user:null
    }
  }
}
</script>

<style lang="scss" scoped>

</style>
