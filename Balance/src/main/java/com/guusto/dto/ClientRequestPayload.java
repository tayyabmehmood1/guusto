package com.guusto.dto;

import lombok.Data;

@Data
public class ClientRequestPayload {
	private Long clientId;
	private RequestPayload[] requestPayLoad;

}
