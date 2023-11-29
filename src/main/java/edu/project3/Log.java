package edu.project3;

import java.time.OffsetDateTime;

public record Log(String remoteAddr, String remoteUser, OffsetDateTime timeLocal, String request, int status,
                  int bodyBytesSent, String httpReferer, String httpUserAgent) {
}
