package com.moas.backend.service.FileWatcher;

import com.moas.backend.toolkit.SseClient;
import com.moas.backend.toolkit.FileWatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.Future;

@Service
public class FileWatcherService {
    private final SseClient sseClient;
    private final ScheduledExecutorService executorService;
    private String currentFilePath;
    private Future<?> fileWatcherFuture;
    private FileWatcher fileWatcher;

    @Autowired
    public FileWatcherService(SseClient sseClient) {
        this.sseClient = sseClient;
        this.executorService = Executors.newScheduledThreadPool(1);
        this.currentFilePath = null;
    }

    public synchronized void startFileWatcher(String filePath, String uid) throws IOException {
        if (currentFilePath != null) {
            return;
        }
        fileWatcher = new FileWatcher(filePath, sseClient, uid);
        fileWatcherFuture = executorService.submit(fileWatcher);
        currentFilePath = filePath;
    }

    public synchronized void stopFileWatcher() {
        if (fileWatcher != null) {
            fileWatcher.stop();
            fileWatcherFuture.cancel(true);
            currentFilePath = null;
        }
    }
}