<template>
  <div @click="()=>{alert('ddd')}">
    <span style="font-size:1.5rem">
      board.title
    </span>
    <span style="color:yellow">
      ★
    </span>
    <span>
      board.user.reputation
    </span>
    <hr>
    <div style="text-align: right;">
      <span style="color:red">
      board.dday
    </span>
    </div>
  </div>
</template>

<script>
// import axios from 'axios'

export default {
  name: 'Test',
  components: {},
  data: () => ({
    board: {
      board_id: 13,
      user: {
        user_id: 2,
        email: 'jason@naver.com',
        password: '1234',
        role: '유저',
        nickname: '어그로',
        name: '이재혁',
        age: 29,
        gender: 'M',
        phone: '010-0000-0000',
        user_locationX: null,
        user_locationY: null,
        reputation: 2,
      },
      title: '역삼떡볶이같이 드실분',
      context: '역삼에서 떡볶이 드실분 엽떡먹을꺼임',
      board_locationX: '127.05136639999998',
      board_locationY: '37.4508',
      writeDate: '2020-06-06T13:01:27.924123',
      deadlineDate: '2020-06-06T12:55:39.511',
      limit_num: 5,
      participants: 0,
      category: 1,
      keyword: '#떡볶이#역삼',
      deleted: false,
    },
  }),
  created() {
    console.log(this.board)
  },
  methods: {},
  computed: {},
}
</script>

<style scoped>
/* .image-container {
  overflow: hidden;
  display: flex;
  align-items: center;
  justify-content: center;
}
.page {
  display: block;
  text-align: center;
  color: #eee;
} */
.fullpage-container {
  /* width: 100%; */
  /* height: 100%; */
  position: absolute;
  top: 0;
  bottom: 0;
  left: 0;
  right: 0;
}
.grey-bg {
  background: #eee;
}
.selectButtonArea {
  text-align: center;
}
</style>
