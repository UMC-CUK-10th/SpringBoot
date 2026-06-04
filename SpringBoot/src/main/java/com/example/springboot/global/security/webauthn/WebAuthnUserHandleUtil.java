package com.example.springboot.global.security.webauthn;

import org.springframework.security.web.webauthn.api.Bytes;

import java.nio.ByteBuffer;

final class WebAuthnUserHandleUtil {

    private WebAuthnUserHandleUtil() {
    }

    static Bytes fromUserId(Long userId) {
        return new Bytes(ByteBuffer.allocate(Long.BYTES).putLong(userId).array());
    }

    static Long toUserId(Bytes handle) {
        byte[] bytes = handle.getBytes();
        if (bytes.length != Long.BYTES) {
            return null;
        }
        return ByteBuffer.wrap(bytes).getLong();
    }
}
