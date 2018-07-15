package org.dayup.fun.aide.service.impl;

import org.dayup.fun.aide.jpa.repository.DocumentRepo;
import org.dayup.fun.aide.service.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DocumentServiceImpl implements DocumentService {

    @Autowired
    private DocumentRepo documentRepo;

    @Override
    public void test() {
        System.out.println(documentRepo.count());
    }
}
