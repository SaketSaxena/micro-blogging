var stompClient = null;
var notificationCount = 0;

$(document).ready(function() {
    connect();
    fetchOldMessages();
});

function fetchOldMessages() {
$.ajax({
  url: 'http://localhost:8001/messages',
  method: 'GET',
  success: function (data) {
    data?.messages.map(function (message) {
       showMessage(message);
    });
  },
  error: function (xhr, status, error) {
    console.error(error);
  }
});
}
function connect() {
    var socket = new SockJS('/websocket');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        stompClient.subscribe('/receive/message', function (message) {
            showMessage(JSON.parse(message.body));
        });
    });
}

function showMessage(message) {
    $("#messages").prepend("<tr><td>" + message.author + "</td><td>" + message.message + "</td><td>"+  message.source   +"</td></tr>");
}