package com.company;

import java.nio.file.Path;

public interface FilePredicate {

    boolean test(Path path, String param) throws Exception;
}
