package com.moas.backend.toolkit;

import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.nio.file.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FileWatcher implements Runnable {
    private final WatchService watchService;
    private final Path pathToWatch;
    private final Map<Path, Long> lastModifiedTimes;
    private final SseClient sseClient;
    private final String uid;
    private volatile boolean running;

    public FileWatcher(String path, SseClient sseClient, String uid) throws IOException {
        this.pathToWatch = Paths.get(path);
        this.watchService = FileSystems.getDefault().newWatchService();
        this.pathToWatch.register(watchService, StandardWatchEventKinds.ENTRY_MODIFY);
        this.lastModifiedTimes = new HashMap<>();
        this.sseClient = sseClient;
        this.uid = uid;
        this.running = true;
    }

    public void stop() {
        this.running = false;
    }

    @Override
    public void run() {
        try {
            sseClient.getSse(uid);
            while (running) {
                WatchKey key = watchService.take();
                for (WatchEvent<?> event : key.pollEvents()) {
                    WatchEvent.Kind<?> kind = event.kind();
                    if (kind == StandardWatchEventKinds.ENTRY_MODIFY) {
                        Path changedFilePath = pathToWatch.resolve((Path) event.context());
                        long lastModifiedTime = Files.getLastModifiedTime(changedFilePath).toMillis();
                        if (!lastModifiedTimes.containsKey(changedFilePath) || lastModifiedTimes.get(changedFilePath) != lastModifiedTime) {
                            List<String> lines = Files.readAllLines(changedFilePath);
                            sseClient.sendMessage(uid, "fileChanged", String.join("\n", lines));
                            lastModifiedTimes.put(changedFilePath, lastModifiedTime);
                        }
                    }
                }
                key.reset();
            }
        } catch (IOException | InterruptedException e) {
            System.err.println(e.getMessage());
        }
    }
}