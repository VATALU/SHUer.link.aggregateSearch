package org.shuerlink.Spider;

import org.shuerlink.model.Result;

import java.util.LinkedList;
import java.util.concurrent.Executor;

public class Spider {

    private Executor executor;

    private LinkedList<Crawler> crawlers = new LinkedList<>();

    private Task task = new Task();

    public static Spider newInstance(LinkedList<Crawler> crawlers,Executor executor) {
        return new Spider(crawlers,executor);
    }

    public Spider(LinkedList<Crawler> crawlers,Executor executor) {
        setCrawlers(crawlers);
        setExecutor(executor);
    }

    public LinkedList<Crawler> getCrawlers() {
        return crawlers;
    }

    public Spider setCrawlers(LinkedList<Crawler> crawlers) {
        this.crawlers = crawlers;
        return this;
    }

    public Spider addCrawler(Crawler<Result> crawler) {
        crawlers.add(crawler);
        return this;
    }

    public Executor getExecutor() {
        return executor;
    }

    public void setExecutor(Executor executor) {
        this.executor = executor;
    }

    public Task getTask() {
        return task;
    }

    public Spider setTask(Task task) {
        this.task = task;
        return this;
    }

    public LinkedList<Result> run() {
        LinkedList<Result> results = null;
        results = task.execute(this);
        return results;
    }

}
