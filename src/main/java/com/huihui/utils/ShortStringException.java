package com.huihui.utils;

/**
 * @author Jstarry
 * @date 2022/9/18 20:49
 **/
public class ShortStringException extends Exception {

    public ShortStringException() {
        super();
    }

    public ShortStringException(String message) {
        super(message);
    }

    public ShortStringException(String message, Throwable cause) {
        super(message, cause);
    }

    public ShortStringException(Throwable cause) {
        super(cause);
    }

}
