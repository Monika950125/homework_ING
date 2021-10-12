package com.monika.homework.file.scheduler;

import com.monika.homework.file.service.FileService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class FileProcessorScheduler {

    private final FileService fileService;

    @Scheduled(cron = "0 0 20 * * *")
    public void processFile() {
        log.info("Starting FileProcessorScheduler");
        fileService.save(Optional.empty());
        fileService.process(Optional.empty());
        log.info("FileProcessorScheduler end of work");
    }
}
