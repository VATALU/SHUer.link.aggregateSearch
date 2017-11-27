package org.shuerlink.Spider;

import org.shuerlink.model.Result;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.LinkedList;
import java.util.concurrent.Executor;

public class Spider<T> {

    private ThreadPoolTaskExecutor executor;

    private LinkedList<Crawler<T>> crawlers;

    private Task<T> task = new Task<>();

    public Spider(LinkedList<Crawler<T>> crawlers,ThreadPoolTaskExecutor executor) {
        setCrawlers(crawlers);
        setExecutor(executor);
    }

    public LinkedList<Crawler<T>> getCrawlers() {
        return crawlers;
    }

    public Spider setCrawlers(LinkedList<Crawler<T>> crawlers) {
        this.crawlers = crawlers;
        return this;
    }

    public Spider addCrawler(Crawler<T> crawler) {
        crawlers.add(crawler);
        return this;
    }

    public ThreadPoolTaskExecutor getExecutor() {
        return executor;
    }

    public void setExecutor(ThreadPoolTaskExecutor executor) {
        this.executor = executor;
    }

    public Task getTask() {
        return task;
    }

    public Spider setTask(Task<T> task) {
        this.task = task;
        return this;
    }

    public LinkedList<T> run() {
        LinkedList<T> results = null;
        results = task.execute(this);
        return results;
    }

}
