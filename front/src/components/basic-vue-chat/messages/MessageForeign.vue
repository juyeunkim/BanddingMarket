<template>
  <div
    class="message--foreign">
    <div class="message__author">
      {{ nickname }}
    </div>
    <div class="message__contents">
      <img
        v-if="img"
        :src="img"
        alt=""
        style="width: 100%" >
      {{ message }}
    </div>
    <div class="message__date">
      {{ timestamp }}
    </div>
  </div>
</template>

<script>
export default {
  name: 'MessageForeign',
  props: {
    nickname: {
      type: String,
      default: 'Person',
      required: false
    },
    timestamp: {
      type: String,
      default: '16:30:00',
      required: false
    },
    img: {
      type: String,
      default: '',
      required: false
    },
    message: {
      default: '',
      required: false
    }
  }
}
</script>

<style>

</style>
