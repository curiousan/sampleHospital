package com.mycompany.samplehospital.WebSocket;

import javax.json.Json;
import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;
//encodes all the message of chat room and send returns it in json format

public class ChatMessageEncoder implements Encoder.Text<ChatMessage> {
	@Override
	public void init(final EndpointConfig config) {
	}

	@Override
	public void destroy() {
	}

	@Override
	public String encode(final ChatMessage chatMessage) throws EncodeException {
		return Json.createObjectBuilder()
				.add("message", chatMessage.getMessage())
                                .add("image", chatMessage.getImage())         
				.add("sender", chatMessage.getSender())
				.add("received", chatMessage.getReceived().toString()).build()
				.toString();
	}
}
