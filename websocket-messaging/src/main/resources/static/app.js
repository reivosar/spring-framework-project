class WebSocketController {
    stompClient = null;

    setConnected(connected) {
        $("#connect").prop("disabled", connected);
        $("#disconnect").prop("disabled", !connected);
        if (connected) {
            $("#conversation").show();
        } else {
            $("#conversation").hide();
        }
        $("#greetings").html("");
    }

    connect() {
        const socket = new SockJS('/websocket-connector');
        this.stompClient = Stomp.over(socket);
        this.stompClient.connect({}, function (frame) {
            webSocket.setConnected(true);
            console.log('Connected: ' + frame);
            webSocket.stompClient.subscribe('/topic/greetings', function (greeting) {
                webSocket.showGreeting(JSON.parse(greeting.body).content);
            });
        });
    }

    disconnect() {
        if (this.stompClient !== null) {
            this.stompClient.disconnect();
        }
        webSocket.setConnected(false);
        console.log("Disconnected");
    }

    sendName() {
        this.stompClient.send("/app/hello", {}, JSON.stringify({'name': $("#name").val()}));
    }

    showGreeting(message) {
        $("#greetings").append("<tr><td>" + message + "</td></tr>");
    }
}

const webSocket = new WebSocketController();

$(function () {
    $("form").on('submit', function (e) {
        e.preventDefault();
    });
    $("#connect").click(function () {
        webSocket.connect();
    });
    $("#disconnect").click(function () {
        webSocket.disconnect();
    });
    $("#send").click(function () {
        webSocket.sendName();
    });
});