package com.thdz.csc.http;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Converter;

/**
 * desc:
 * author:  Administrator
 * date:    2018/10/16  17:46
 */
public class StringResponseBodyConverter implements Converter<ResponseBody, String> {
    @Override
    public String convert(ResponseBody value) throws IOException {
        try {
            return value.string();
        } finally {
            value.close();
        }
    }
}