import firebase from 'firebase/app';
import 'firebase/firestore';

var firebaseConfig = {
    apiKey: "AIzaSyAzJeSpwuQMUtrh-IXBhy1QzoiEfvgN0fc",
    authDomain: "bandding-chat.firebaseapp.com",
    databaseURL: "https://bandding-chat.firebaseio.com",
    projectId: "bandding-chat",
    storageBucket: "bandding-chat.appspot.com",
    messagingSenderId: "295439997275",
    appId: "1:295439997275:web:1e6b226ff20e9e86f9e27e",
    measurementId: "G-Z2XST6ZCZR"
};
const firebaseApp = firebase.initializeApp(firebaseConfig);
// firebaseApp.firestore().settings({ timestampsInSnapshots: true });

export default firebaseApp.firestore();