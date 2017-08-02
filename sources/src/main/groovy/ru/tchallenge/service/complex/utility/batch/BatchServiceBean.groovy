package ru.tchallenge.service.complex.utility.batch

import groovy.transform.CompileStatic
import groovy.transform.PackageScope

import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

import ru.tchallenge.service.complex.common.GenericServiceBean
import ru.tchallenge.service.complex.convention.component.ServiceComponent

@CompileStatic
@PackageScope
@ServiceComponent
class BatchServiceBean extends GenericServiceBean implements BatchService {

    private ExecutorService executor

    @Override
    void executeAsync(Runnable task) {
        executor.submit(task)
    }

    protected void init() {
        super.init()
        executor = Executors.newSingleThreadExecutor()
    }
}
