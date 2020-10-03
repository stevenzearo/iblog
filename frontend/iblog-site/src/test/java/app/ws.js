/**
 * @author steve
 */
var webSocket = new WebSocket("ws://localhost:8411/ws/chat/group-0001/user-auth/3e1420c1-3834-4d68-81cf-4610c1f70806")
webSocket.onopen = function (event) {
    console.warn(event.data);
}

webSocket.onclose = function (event) {
    console.warn(event.data);
}

webSocket.onmessage = function (messageevent) {
    let data = messageevent.data;
    alert(JSON.stringify(data));
    var content = data.content;
    if (content) {
        alert(JSON.stringify(content));
    }
    webSocket.send("hello, steve, fffffffffffffffffffffff");
}