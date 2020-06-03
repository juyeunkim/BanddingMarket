<template>
  <v-dialog v-model="tmpFlag" max-width="290" class="noShadow" persistent>
    <v-card>
      <v-card-title>
        <span> 회원신고하기 </span>
      </v-card-title>
      <v-container>
        <v-row>
          <v-col cols="5">
            <v-icon v-if="user.gender == '여자'" style="font-size:100px">
              mdi-face-woman-outline
            </v-icon>
            <v-icon v-else style="font-size:100px">
              mdi-face-outline
            </v-icon>
          </v-col>
          <v-col cols="7">
            <div>
              <span>{{ user.nickname }} ({{user.age}}살)</span>
            </div>
            <v-rating
              v-model="user.reputation"
              length="5"
              empty-icon="mdi-heart-outline"
              full-icon="mdi-heart"
              half-icon="mdi-heart-half-full"
              half-increments
              readonly
              size="25"
              dense
              color="red lighten-3"
              background-color="grey lighten-1"
            ></v-rating>
          </v-col>
        </v-row>

        <v-row>
          <v-col>
            1
          </v-col>
          <v-col>
            2
          </v-col>
        </v-row>

        <v-row>
          <v-col>
            1
          </v-col>
          <v-col>
            2
          </v-col>
        </v-row>
      </v-container>

      <v-divider></v-divider>
      <v-card-actions>
        <v-spacer></v-spacer>
        <v-btn color="blue darken-1" text @click="cancelDialog" small>
          취소</v-btn
        >
      </v-card-actions>
    </v-card>
  </v-dialog>
</template>

<script>
// writer	작성자 아이디
// reported	신고 당한 유저 아이디
// context	신고내용
// category	신고 분류
// status	신고 데이터 상태
export default {
  name: 'UserInfoDialog',
  props: ['reported', 'flag'],
  data: () => ({
    user: {
      user_id: '1',
      age: 25,
      email: 'taeminkim23@gmail.com',
      gender: '여자',
      name: '김태민',
      nickname: '탬탬',
      phone: '010-1234-1234',
      reputation: 4.8,
      role: '유저',
      user_locationx: '??',
      user_locationy: '??',
    },
    tmpFlag: true,
  }),
  created() {},
  methods: {
    cancelDialog() {
      this.$emit('updateUserDialogFlag', false)
    },
  },
}
</script>
