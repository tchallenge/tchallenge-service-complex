package ru.tchallenge.service.complex.utility.batch

import groovy.transform.CompileStatic

@CompileStatic
interface BatchService {

    void executeAsync(Runnable task)
}
