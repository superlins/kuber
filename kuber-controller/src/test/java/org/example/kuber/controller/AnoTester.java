package org.example.kuber.controller;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;

public class AnoTester {

    /** @see ch.qos.logback.core.FileAppender#safeWrite */
    public static void main(String[] args) throws IOException {
        try (FileOutputStream fileOutputStream = new FileOutputStream("/Users/lienider/IdeaProjects/kubers/lock.log")) {
            FileChannel fileChannel = fileOutputStream.getChannel();
            FileLock fileLock = fileChannel.tryLock();
            if (fileLock == null) {
                System.out.println("failed lock");
            } else {
                System.out.println(fileLock);
                System.in.read();
            }
        }
    }
}
