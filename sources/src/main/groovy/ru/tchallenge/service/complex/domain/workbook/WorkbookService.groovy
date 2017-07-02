package ru.tchallenge.service.complex.domain.workbook

import java.util.stream.Collectors

import groovy.transform.CompileStatic

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.PageRequest

import ru.tchallenge.service.complex.common.GenericService
import static ru.tchallenge.service.complex.common.enumerated.EnumeratedTransformations.*
import ru.tchallenge.service.complex.common.enumerated.EnumeratedInfo
import ru.tchallenge.service.complex.common.search.SearchInfo
import ru.tchallenge.service.complex.convention.component.ServiceComponent
import ru.tchallenge.service.complex.domain.workbook.status.WorkbookStatusRepository

@CompileStatic
@ServiceComponent
class WorkbookService extends GenericService {

    @Autowired
    protected WorkbookMapper workbookMapper

    @Autowired
    protected WorkbookRepository workbookRepository

    @Autowired
    protected WorkbookStatusRepository workbookStatusRepository

    WorkbookInfo create(WorkbookInvoice invoice) {
        def entity = workbookMapper.asEntity(invoice)
        workbookRepository.save(entity)
        return workbookMapper.asInfoComplete(entity)
    }

    WorkbookInfo get(String id) {
        def entity = workbookById(id)
        return workbookMapper.asInfoComplete(entity)
    }

    SearchInfo<WorkbookInfo> search(WorkbookInvoice invoice) {
        def pageable = new PageRequest(0, 10)
        def page = workbookRepository.findPage(null, null, null, pageable)
        return new SearchInfo(
                content: page.content.stream().map({return workbookMapper.asInfoComplete(it)}).collect(Collectors.toList()),
                pageCount: 0
        )
    }

    WorkbookInfo update(WorkbookInvoice invoice) {
        def entity = workbookById(invoice.id)
        workbookRepository.save(entity)
        return workbookMapper.asInfoComplete(entity)
    }

    Collection<EnumeratedInfo> getStatuses() {
        return all(workbookStatusRepository)
    }

    private Workbook workbookById(String id) {
        def result = workbookRepository.findById(id as Long)
        if (!result) {
            throw new RuntimeException("no such workbook")
        }
        return result
    }
}
