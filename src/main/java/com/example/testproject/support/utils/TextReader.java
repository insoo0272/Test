package com.example.testproject.support.utils;

import java.io.IOException;
import java.util.List;
import java.util.stream.Stream;

public interface TextReader {
        Stream<List<String>> read(String path, String charset) throws IOException;

}
