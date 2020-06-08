<template>
  <v-btn text @click.stop="loginDialog = true" rounded
    >로그인하러가기
    <v-dialog v-model="loginDialog" max-width="290">
      <v-card>
        <v-card-title>
          <span class="headline">Login</span>
        </v-card-title>
        <v-card-text>
          <v-container>
            <v-row>
              <v-col cols="12" class="px-0">
                <v-text-field
                  placeholder="ID"
                  solo
                  hide-details
                  v-model="id"
                ></v-text-field>
              </v-col>
            </v-row>
            <v-row>
              <v-col cols="12" class="px-0">
                <v-text-field
                  type="password"
                  placeholder="Password"
                  solo
                  hide-details
                  v-model="password"
                  v-on:keyup.enter="login"
                ></v-text-field>
              </v-col>
            </v-row>
            <v-row>
              <v-col cols="12" class="px-0">
                <v-btn color="primary" large block @click="login"
                  ><b>Login</b></v-btn
                >
              </v-col>
            </v-row>
          </v-container>
        </v-card-text>
      </v-card>
    </v-dialog>
  </v-btn>
</template>

<script>
import http from '../vuex/http-common'

export default {
  data: () => ({
    loginDialog: false,
    id: '',
    password: '',
  }),
  computed: {},
  mounted() {},
  beforeDestroy() {},

  methods: {
    login() {
      // let form = new FormData()
      // form.append('user_id', this.id)
      // form.append('password', this.password)

      http
        .post('/user/signIn', {
          password: '1234',
          email: 'taeminkim23@gmail.com',
        })
        .then((response) => {
          console.log('333')
          console.log(response.headers)
          // console.log(response.headers.get('jwt-auth-token'))
        })
        .catch((err) => {
          console.log('err')
          console.log(err)
        })
      //   http
      //     .post('/user/signIn', {})
      //     .then((response) => {
      //       console.log(response)
      //     })
      //     .catch((err) => {
      //       if (err.response.data.non_field_errors != undefined) {
      //         alert('아이디와 비밀번호를 확인해주세요.')
      //         this.password = ''
      //       } else if (err.response.data.password != undefined) {
      //         alert('비밀번호를 확인해주세요.')
      //         this.password = ''
      //       } else {
      //         alert('아이디와 비밀번호를 확인해주세요.')
      //         this.password = ''
      //       }
      //     })
    },
  },
}
</script>
<style scoped>
@import url('https://fonts.googleapis.com/css2?family=Cabin&display=swap');
.cabin {
  font-family: 'Cabin', sans-serif;
}
.fs40 {
  font-size: 40px;
}
</style>
