var stompClient = null;

function connect() {
	var socket = new SockJS(window.location.origin+'/stomp');
	stompClient = Stomp.over(socket);
	stompClient.debug = null;
	stompClient.connect({}, function(frame) {
		console.log('Connected: ' + frame);
		stompClient.subscribe('/topic/messages',
				function(message) {
					messageReceived(message.body);
				});
	});
}

function sendMessage(message) {
	console.log("sending:"+message);
	stompClient.send("/app/message", {}, message);

}

function messageReceived(message) {
	console.log("essage received:"+message);
	$('#chat').val($('#chat').val()+"\n"+message); 
}

function disconnect() {
	stompClient.disconnect();
	console.log("Disconnected");
}
