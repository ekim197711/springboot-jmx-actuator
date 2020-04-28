package com.example.demo.jmx;

import java.util.Queue;

public interface QueueSamplerMXBean {
    public QueueSample getQueueSample();
    public void clearQueue();
}
