package com.monika.homework.file.controller;

import com.monika.homework.file.service.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "api/file")
public class FileController {

    private final FileService fileService;

    @PostMapping("/read")
    public void readFile(@RequestParam(value = "path", required = false) Optional<String> path) {
        fileService.save(path);
    }

    @PostMapping("/process")
    public void processNewEntries(@RequestParam(value = "delimiter", required = false) Optional<String> delimiter) {
        fileService.process(delimiter);
    }
}
