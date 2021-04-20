package com.wush.kafka.consumer.entity;


import com.wush.kafka.consumer.constants.ExecutorVersion;

import java.time.Instant;

import static com.wush.kafka.consumer.constants.ConsumerConfigEntityDefaults.*;


/**
 * Represents a queue configuration for the hoolihan consumers
 */
public class ConsumerConfigEntity {
    private String topicName;

    private Boolean consumerEnabled = DEFAULT_CONSUMER_ENABLED;

    private String qualifierName;

    private Integer numPollersPerTopic = DEFAULT_POLLERS_TOPIC;

    private Integer pollingFrequency = DEFAULT_POLLING_FREQUENCY;

    private Integer countOfMessagesPolled = DEFAULT_COUNT_OF_MESSAGES_POLLED;

    private Integer visibilityTimeout = DEFAULT_VISIBILITY_TIMEOUT;

    private String executorBeanName;

    private ExecutorVersion executorVersion = DEFAULT_EXECUTOR_VERSION;

    private Integer replayLimit = DEFAULT_REPLAY_LIMIT;

    private String deadLetterTopicName;

    private boolean backOffEnabled = true;

    private Integer backOffMultiplier = DEFAULT_BACKOFF_MULTIPLIER;

    private Integer backOffLimit;

    private Integer backOffInitialDelayCount = DEFAULT_BACKOFF_INITIAL_DELAY_COUNT;

    private transient volatile Integer currentPollingFrequency;

    private transient volatile Instant lastExecutionTime;

    private String groupId;

    public ConsumerConfigEntity(){
        resetCurrentPollingFrequency();
        this.lastExecutionTime = Instant.now();
        this.backOffLimit = this.pollingFrequency * 16;
    }

    public String getTopicName() {
        return topicName;
    }

    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }

    public Boolean getConsumerEnabled() { return consumerEnabled; }

    public void setConsumerEnabled(Boolean consumerEnabled) { this.consumerEnabled = consumerEnabled; }

    public String getQualifierName() {
        return qualifierName;
    }

    public void setQualifierName(String qualifierName) {
        this.qualifierName = qualifierName;
    }

    public Integer getNumPollersPerTopic() { return numPollersPerTopic; }

    public void setNumPollersPerTopic(Integer numPollersPerTopic) {
        this.numPollersPerTopic = numPollersPerTopic;
    }

    public Integer getPollingFrequency() { return pollingFrequency; }

    public void setPollingFrequency(Integer pollingFrequency) {
        this.pollingFrequency = pollingFrequency;
    }

    public Integer getCountOfMessagesPolled() { return countOfMessagesPolled; }

    public void setCountOfMessagesPolled(Integer countOfMessagesPolled) { this.countOfMessagesPolled = countOfMessagesPolled; }

    public Integer getVisibilityTimeout() { return visibilityTimeout; }

    public void setVisibilityTimeout(Integer visibilityTimeout) {
        this.visibilityTimeout = visibilityTimeout;
    }

    public String getExecutorBeanName() {
        return executorBeanName;
    }

    public void setExecutorBeanName(String executorBeanName) {
        this.executorBeanName = executorBeanName;
    }

    public ExecutorVersion getExecutorVersion() { return executorVersion; }

    public void setExecutorVersion(ExecutorVersion executorVersion) { this.executorVersion = executorVersion; }

    public Integer getReplayLimit() { return replayLimit; }

    public void setReplayLimit(Integer replayLimit) { this.replayLimit = replayLimit; }

    public String getDeadLetterTopicName() {
        return deadLetterTopicName;
    }

    public void setDeadLetterTopicName(String deadLetterTopicName) {
        this.deadLetterTopicName = deadLetterTopicName;
    }

    public Integer getBackOffMultiplier() {
        return backOffMultiplier;
    }

    public void setBackOffMultiplier(Integer backOffMultiplier) {
        this.backOffMultiplier = backOffMultiplier;
    }

    public Integer getBackOffLimit() {
        return backOffLimit;
    }

    public void setBackOffLimit(Integer backOffLimit) {
        this.backOffLimit = backOffLimit;
    }

    public Integer getBackOffInitialDelayCount() {
        return backOffInitialDelayCount;
    }

    public void setBackOffInitialDelayCount(Integer backOffInitialDelayCount) {
        this.backOffInitialDelayCount = backOffInitialDelayCount;
    }

    public Integer getCurrentPollingFrequency() {
        return currentPollingFrequency;
    }

    public void setCurrentPollingFrequency(Integer currentPollingFrequency) {
        this.currentPollingFrequency = currentPollingFrequency;
    }

    public Instant getLastExecutionTime() {
        return lastExecutionTime;
    }

    public void setLastExecutionTime(Instant lastExecutionTime) {
        this.lastExecutionTime = lastExecutionTime;
    }

    public void resetCurrentPollingFrequency(){
        this.currentPollingFrequency = this.pollingFrequency * this.backOffInitialDelayCount * this.backOffMultiplier;
    }

    public boolean isBackOffEnabled() {
        return backOffEnabled;
    }

    public void setBackOffEnabled(boolean backOffEnabled) {
        this.backOffEnabled = backOffEnabled;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String toString(){
        return String.format("Topic : "+ topicName
                + ", ROQ : " + qualifierName
                + ", Executor Bean Name : " +executorBeanName);
    }
}
