/**
 * @author steve
 */
var webSocket = new WebSocket("ws://localhost:8411/ws/chat/group-0001")
webSocket.onopen = function (event) {
    console.warn(event.data);
}

webSocket.onclose = function (event) {
    console.warn(event.data);
}

webSocket.onmessage = function (messageevent) {
    alert(JSON.stringify(messageevent.data));

    webSocket.send("hello");
}
webSocket.send("hello");