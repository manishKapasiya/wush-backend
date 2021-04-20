package com.wush.kafka.consumer.constants;

public final class ConsumerConfigEntityDefaults {

    private ConsumerConfigEntityDefaults(){ }
    public static final ExecutorVersion DEFAULT_EXECUTOR_VERSION = ExecutorVersion.SIMPLE;
    public static final Integer DEFAULT_REPLAY_LIMIT = 3;
    public static final Integer DEFAULT_POLLERS_TOPIC = 1;
    public static final Integer DEFAULT_POLLING_FREQUENCY = 30;
    public static final Integer DEFAULT_COUNT_OF_MESSAGES_POLLED = 10;
    public static final Integer DEFAULT_VISIBILITY_TIMEOUT = 10;
    public static final Boolean DEFAULT_CONSUMER_ENABLED = false;
    public static final Integer DEFAULT_BACKOFF_INITIAL_DELAY_COUNT = 2;
    public static final Integer DEFAULT_BACKOFF_MULTIPLIER = 2 ;
}
