package com.oauth.server.exception;

public class GitHubException extends RuntimeException {
    public GitHubException(String message) {
        super(message);
    }
}
