package ru.tchallenge.service.complex.domain.workbook

import java.util.stream.Collectors

import groovy.transform.CompileStatic

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.PageRequest

import ru.tchallenge.service.complex.behavior.component.Service
import ru.tchallenge.service.complex.behavior.value.search.SearchInfo
import ru.tchallenge.service.complex.behavior.value.search.SearchInvoice
import ru.tchallenge.service.complex.convention.component.ServiceComponent

@CompileStatic
@ServiceComponent
class WorkbookService extends Service {

    @Autowired
    protected WorkbookMapper workbookMapper

    @Autowired
    protected WorkbookRepository workbookRepository

    WorkbookInfo create(WorkbookInvoice invoice) {
        def entity = workbookMapper.asEntity(invoice)
        workbookRepository.save(entity)
        return workbookMapper.asInfoComplete(entity)
    }

    WorkbookInfo get(String id) {
        def entity = workbookById(id)
        return workbookMapper.asInfoComplete(entity)
    }

    SearchInfo<WorkbookInfo> search(SearchInvoice<WorkbookInvoice> invoice) {
        def pageable = new PageRequest(0, 10)
        def page = workbookRepository.findPage(pageable)
        return new SearchInfo(
                items: page.content.stream().map({return workbookMapper.asInfoComplete(it)}).collect(Collectors.toList()),
                offset: 0 as Long
        )
    }

    WorkbookInfo update(WorkbookInvoice invoice) {
        def entity = workbookById(invoice.id)
        workbookRepository.save(entity)
        return workbookMapper.asInfoComplete(entity)
    }

    private WorkbookEntity workbookById(String id) {
        def result = workbookRepository.findById(id as Long)
        if (!result) {
            throw new RuntimeException("no such workbook")
        }
        return result
    }
}
