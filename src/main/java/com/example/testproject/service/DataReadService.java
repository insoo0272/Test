package com.example.testproject.service;

import com.example.testproject.support.exception.DataReadWriteFailException;

public interface DataReadService {
    String readAndStoreData(String path, String charset) throws DataReadWriteFailException;
}