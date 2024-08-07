package com.moas.backend.controller.FileWatcher;

import com.moas.backend.toolkit.SseClient;
import com.moas.backend.service.FileWatcher.FileWatcherService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;

@RestController
@Slf4j
public class FileWatcherController {
    private final FileWatcherService fileWatcherService;
    private final SseClient sseClient;

    @Autowired
    public FileWatcherController(FileWatcherService fileWatcherService, SseClient sseClient) {
        this.fileWatcherService = fileWatcherService;
        this.sseClient = sseClient;
    }

    //使用GetMapping是由于前端的获取sse连接的方式是get请求
    @PostMapping("/startFileWatcher")
    public void startFileWatcher(@RequestParam String filePath, @RequestParam String uid) {
        try {
            fileWatcherService.startFileWatcher(filePath, uid);
        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }

    @GetMapping("/getSse")
    public SseEmitter getSse(@RequestParam String uid) {
        return sseClient.getSse(uid);
    }

    @PostMapping("/stopFileWatcher")
    public String stopFileWatcher() {
        fileWatcherService.stopFileWatcher();
        return "File watcher stopped";
    }
}