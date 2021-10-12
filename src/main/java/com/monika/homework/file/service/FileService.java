package com.monika.homework.file.service;

import java.util.Optional;

public interface FileService {

    void save(Optional<String> path);

    void process(Optional<String> delimiter);
}
