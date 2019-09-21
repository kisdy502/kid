package com.sdt.kid.service;

import org.springframework.core.io.Resource;

public interface StorageService {
    Resource loadAsResource(String filename);
}
