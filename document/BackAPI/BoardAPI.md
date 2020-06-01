# Board

## INSERT

- URL : `/board/insert`

- POST

- **INPUT** : 

  ※ user 넣을때는 id 만 넣어주기 ※

~~~json
{
  "category": 0, 
  "context": "내용",
  "limit_num": 3,
  "title": "제목",
  "user": {
  	"id" : 4
  }
}
~~~

- **OUTPUT**

~~~
{
    "status": true,
    "data": "게시판 등록",
    "object": {
        "id": 13,
        "user": {
            "id": 8,
            "email": "ww",
            "password": "1004",
            "role": "유저",
            "nickname": "닉네임",
            "name": "이름",
            "age": 0,
            "gender": null,
            "phone": null,
            "locationX": null,
            "locationY": null,
            "reputation": 0.0
        },
        "title": "제목",
        "context": "내용",
        "locationX": null,
        "locationY": null,
        "writeDate": "2020-05-26T09:26:31.979",
        "deadlineDate": null,
        "limit_num": 3,
        "participants": 0,
        "category": 0,
        "deleted": false
    }
}
~~~

## UPDATE

- URL :  `/board/update`
- POST
- **INPUT** : 
  ※ Board의 id 넣어주기
   user 넣을때는 id 만 넣어주기 ※

~~~json
{
  "id": 2,
  "category": 0, 
  "context": "내용",
  "limit_num": 3,
  "title": "제목",
  "user": {
  	"id" : 3
  }
}
~~~

- **OUTPUT**

~~~json
{
    "status": true,
    "data": "게시판 수정",
    "object": {
        "id": 3,
        "user": {
            "id": 4,
            "email": null,
            "password": null,
            "role": null,
            "nickname": null,
            "name": null,
            "age": 0,
            "gender": null,
            "phone": null,
            "locationX": null,
            "locationY": null,
            "reputation": 0.0
        },
        "title": "string",
        "context": "dsffdasddas",
        "locationX": null,
        "locationY": null,
        "writeDate": null,
        "deadlineDate": null,
        "limit_num": 3,
        "participants": 0,
        "category": 0,
        "deleted": false
    }
}
~~~

## DELETE

- URL :  `/board/delete/{id}`
- INPUT : Board ID



## APPLY

- URL : `/board/apply/{bid}/{uid}`
- INPUT : BoardID와 UserID

## CANCEL

- URL : `/board/cancel/{bid}/{uid}`
- INPUT : BoardID와 UserID