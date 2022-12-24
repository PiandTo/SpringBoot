package edu.school21.cinema.listeners;

import edu.school21.cinema.models.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectedEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;
import java.util.Objects;

import static java.util.Objects.nonNull;

/*
    We’ll use event listeners to listen for socket connect
    and disconnect events so that we can log these
    events and also broadcast them when a user joins or leaves the chat room.
    We’re already broadcasting user join event in the addUser() method
    defined inside ChatController. So, we don’t need to do anything in the SessionConnected event.
    In the SessionDisconnect event, we’ve written code to extract
    the user’s name from the websocket session and broadcast a user leave event to all the connected clients.
*/

@Component
public class WebSocketEventListener {
	private static final Logger logger = LoggerFactory.getLogger(WebSocketEventListener.class);
	private String id_room = null;
	private String user = null;

	@Autowired
	private SimpMessagingTemplate template;

	@EventListener
	public void handleWebSocketConnectListener(SessionConnectedEvent event) {
		SimpMessageHeaderAccessor headerAccessor = SimpMessageHeaderAccessor.wrap(event.getMessage());
		GenericMessage<?> generic = (GenericMessage<?>) headerAccessor.getHeader(SimpMessageHeaderAccessor.CONNECT_MESSAGE_HEADER);
		System.out.println(headerAccessor.toMap());
		if (nonNull(generic)) {
			SimpMessageHeaderAccessor nativeAccessor = SimpMessageHeaderAccessor.wrap(generic);
			id_room = Objects.requireNonNull(nativeAccessor.getNativeHeader("id")).get(0);
			user = Objects.requireNonNull(nativeAccessor.getNativeHeader("user")).get(0);
		}
	}

	@EventListener
	public void handleWebSocketDisconnectListener(SessionDisconnectEvent event) {
		SimpMessageHeaderAccessor headerAccessor = SimpMessageHeaderAccessor.wrap(event.getMessage());
		String username = (String) Objects.requireNonNull(headerAccessor.getSessionAttributes()).get("username");
		Long id = (Long) Objects.requireNonNull(headerAccessor.getSessionAttributes()).get("id");
		if (id == null) {
			id = Long.parseLong(id_room);
		}
		Message chatMessage = new Message();
		chatMessage.setType(Message.MessageType.LEAVE);
		chatMessage.setUsername(user);
		if(username != null) {
			chatMessage.setUsername(username);
		}
		logger.info("User Disconnected : " + username);
		template.convertAndSend("/topic/messages/" + id, chatMessage);
	}
}
