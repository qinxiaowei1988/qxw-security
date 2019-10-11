package com.qxw.security.wiremock;

import com.github.tomakehurst.wiremock.client.WireMock;

public class MockServer {
    public static void main(String[] args) {
        WireMock.configureFor(8083);
        WireMock.removeAllMappings();
        WireMock.stubFor(WireMock.get(WireMock.urlEqualTo("/order/1")).willReturn(WireMock.aResponse().withBody("{\"id\":1}").withStatus(200)));
    }
}
