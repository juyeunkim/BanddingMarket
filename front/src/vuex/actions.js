import Constant from "./Constant";
import http from "./http-common";

export default {
    [Constant.SET_TEST]: (store, payload) => {
        //사용방법
        // this.$store.dispatch(Constant.SET_TEST, {
        //     tmp: "tmp",
        //   });
        payload
        http
            .get("url")
            .then(response => {

                store.commit(Constant.SET_TEST, {
                    test: response.data.data
                });
            })
            .catch(exp => {
                alert("GET_YOUTUBERS_PER_CATEGORY에 실패하였습니다!\n" + exp);
            });
    },
    [Constant.SET_SEARCHALLLIST]: (store) => {
        // console.log(payload.searchList)
        http.get('/board').then((response) => {
            store.commit(Constant.SET_SEARCHLIST, {
                searchList: response.data.object
            });
        })

    },
    [Constant.SEARCH_BYKEYWORD]: (store, payload) => {
        console.log(escape(payload.keyword))

        http.get('/board/keyword/{keyword}?keyword=' + escape('안녕')).then((response) => {
            store.commit(Constant.SET_SEARCHLIST, {
                searchList: response.data.object
            });
        })

        // http.get('/board/keyword/{keyword}?keyword=' + escape(payload.keyword)).then((response) => {
        //     store.commit(Constant.SET_SEARCHLIST, {
        //         searchList: response.data.object
        //     });
        // })

    },

    [Constant.SEARCH_BOARD]: (store, payload) => {
        console.log(escape(payload.id))

        http.get('/board/{id}?id=' + payload.id).then((response) => {
            store.commit(Constant.SET_BOARD, {
                board: response.data.object
            });
        })

    },

};