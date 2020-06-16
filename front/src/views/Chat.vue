<template>
  <div>
    <basic-vue-chat :new-message="message" />
  </div>
</template>

<script>
import BasicVueChat from '../components/basic-vue-chat/BasicVueChat.vue'

export default {
  name: 'HOME',
  components: {
    BasicVueChat
  },
  data: function () {
    return {
      message: {}
    }
  }
}
</script>

<style lang="scss">
// @import "../../assets/scss/main.scss";
</style>