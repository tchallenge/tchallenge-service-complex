package ru.tchallenge.service.complex.domain.workbook

import groovy.transform.CompileStatic

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page

import ru.tchallenge.service.complex.common.GenericService
import ru.tchallenge.service.complex.common.enumerated.EnumeratedInfo
import ru.tchallenge.service.complex.common.enumerated.EnumeratedInvoice
import ru.tchallenge.service.complex.common.search.SearchInfo
import ru.tchallenge.service.complex.convention.component.ServiceComponent
import ru.tchallenge.service.complex.domain.workbook.status.WorkbookStatusRepository
import static ru.tchallenge.service.complex.common.enumerated.EnumeratedTransformations.all
import static ru.tchallenge.service.complex.common.enumerated.EnumeratedTransformations.invoice
import static ru.tchallenge.service.complex.common.search.SearchTransformations.normalizeOrdinalIds
import static ru.tchallenge.service.complex.common.search.SearchTransformations.info
import static ru.tchallenge.service.complex.common.search.SearchTransformations.pageable

@CompileStatic
@ServiceComponent
class WorkbookService extends GenericService {

    @Autowired
    protected WorkbookMapper workbookMapper

    @Autowired
    protected WorkbookPersister workbookPersister

    @Autowired
    protected WorkbookRepository workbookRepository

    @Autowired
    protected WorkbookStatusRepository workbookStatusRepository

    WorkbookInfo create(WorkbookInvoice invoice) {
        def workbook = workbookMapper.asEntity(invoice.with {
            id = null
            status = initialStatus()
            it
        })
        return saveAndInfo(workbook)
    }

    WorkbookInfo get(String id) {
        return info(workbookById(id))
    }

    SearchInfo<WorkbookInfo> search(WorkbookSearchInvoice invoice) {
        Page<Workbook> page = workbookRepository.findPage(
                normalizeOrdinalIds(invoice.filterEventIds),
                normalizeOrdinalIds(invoice.filterOwnerIds),
                invoice.filterStatusTextcodes,
                pageable(invoice)
        )
        return info(invoice, page) {
            Workbook it -> info(it)
        }
    }

    WorkbookInfo update(WorkbookInvoice invoice) {
        def workbook = workbookById(invoice.id)
        def trimmedInvoice = invoice.with {
            id = null
            it
        }
        def updatedWorkbook = workbookMapper.asEntity(workbook, trimmedInvoice)
        return saveAndInfo(updatedWorkbook)
    }

    Collection<EnumeratedInfo> getStatuses() {
        return all(workbookStatusRepository)
    }

    private Workbook workbookById(String id) {
        def workbook = workbookRepository.findById(id as Long)
        if (!workbook) {
            throw unknownEvent()
        }
        return workbook
    }

    private WorkbookInfo info(Workbook entity) {
        return workbookMapper.asInfo(entity)
    }

    private Workbook save(Workbook workbook) {
        return workbookPersister.save(workbook)
    }

    private WorkbookInfo saveAndInfo(Workbook workbook) {
        return info(save(workbook))
    }

    private static EnumeratedInvoice initialStatus() {
        return invoice("APPROVED")
    }

    private static RuntimeException unknownEvent() {
        return new RuntimeException("referenced workbook does not exist")
    }
}
