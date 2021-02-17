package com.epam.orderingsystem.processor;

public interface WishProcessor {
    /**
     * Process file with wishes
     * @param path is path to file
     * @throws Exception when exception is thrown
     */
    void process(String path) throws Exception;
}
