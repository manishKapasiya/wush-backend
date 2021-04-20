package com.wush.kafka.consumer.utils;

public class ReplayCountFinder {

    public static Integer findCurrentReplayCount(String replayCountHeader){
        Integer currentReplayCount = 0;
        try {
            currentReplayCount = Integer.valueOf(replayCountHeader);
        }
        catch (NumberFormatException e){
            currentReplayCount = 0;
        }
        currentReplayCount = currentReplayCount>0?currentReplayCount:0;
        return currentReplayCount;
    }
}
