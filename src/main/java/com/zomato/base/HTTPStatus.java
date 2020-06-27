package com.zomato.base;

import java.util.HashMap;
import java.util.Map;

public enum HTTPStatus {
	
	STATUS_OK(200),
    STATUS_FORBIDDEN(403),
    STATUS_UNAUTHORIZED(400),
    STATUS_INTERNAL_SERVER_ERROR(500),
    STATUS_NOT_FOUND(404);

    private int value;
    private static Map<Object, Object> map = new HashMap<Object, Object>();

    private HTTPStatus(int value) {
        this.value = value;
    }

    static {
        for (HTTPStatus httpStatus : HTTPStatus.values()) {
            map.put(httpStatus.value, httpStatus);
        }
    }

    public static HTTPStatus valueOf(int httpStatus) {
        return (HTTPStatus) map.get(httpStatus);
    }

    public int getValue() {
        return value;
    }

}
