package com.victot_exe.liter_alura.service;

import java.util.List;

public interface IConvertData{
    <T> T getData(String json, Class<T> clazz);
}
