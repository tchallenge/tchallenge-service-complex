package ru.tchallenge.service.complex.domain.workbook

import groovy.transform.CompileStatic
import groovy.transform.PackageScope

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page

import ru.tchallenge.service.complex.common.GenericServiceBean
import ru.tchallenge.service.complex.common.enumerated.EnumeratedInfo
import ru.tchallenge.service.complex.common.enumerated.EnumeratedInvoice
import ru.tchallenge.service.complex.common.search.SearchInfo
import ru.tchallenge.service.complex.convention.component.ServiceComponent
import ru.tchallenge.service.complex.domain.workbook.status.WorkbookStatusRepository

@CompileStatic
@PackageScope
@ServiceComponent
class WorkbookServiceBean extends GenericServiceBean implements WorkbookService {

    @Autowired
    WorkbookMapper workbookMapper

    @Autowired
    WorkbookPersister workbookPersister

    @Autowired
    WorkbookRepository workbookRepository

    @Autowired
    WorkbookStatusRepository workbookStatusRepository

    @Override
    WorkbookInfo create(WorkbookInvoice invoice) {
        def $workbook = workbookMapper.asEntity(invoice.with {
            id = null
            status = initialStatus()
            it
        })
        saveAndInfo($workbook)
    }

    @Override
    Collection<EnumeratedInfo> getAllStatuses() {
        enumerateds.all(workbookStatusRepository)
    }

    @Override
    WorkbookInfo getById(String id) {
        info(workbookById(id))
    }

    @Override
    SearchInfo<WorkbookInfo> search(WorkbookSearchInvoice invoice) {
        Page<Workbook> $page = workbookRepository.findPage(
                searches.normalizeOrdinalIds(invoice.filterEventIds),
                searches.normalizeOrdinalIds(invoice.filterOwnerIds),
                invoice.filterStatusTextcodes,
                searches.pageable(invoice)
        )
        searches.info(invoice, $page) { Workbook it -> info(it) }
    }

    @Override
    WorkbookInfo update(WorkbookInvoice invoice) {
        def $workbook = workbookById(invoice.id)
        def $trimmedInvoice = invoice.with {
            id = null
            it
        }
        def $updatedWorkbook = workbookMapper.asEntity($workbook, $trimmedInvoice)
        saveAndInfo($updatedWorkbook)
    }

    private Workbook workbookById(String id) {
        def $result = workbookRepository.findById(id as Long)
        if (!$result) {
            throw unknownWorkbook()
        }
        $result
    }

    private WorkbookInfo info(Workbook entity) {
        workbookMapper.asInfo(entity)
    }

    private Workbook save(Workbook workbook) {
        workbookPersister.save(workbook)
    }

    private WorkbookInfo saveAndInfo(Workbook workbook) {
        info(save(workbook))
    }

    private static EnumeratedInvoice initialStatus() {
        enumerateds.invoice('APPROVED')
    }

    private static RuntimeException unknownWorkbook() {
        new RuntimeException('referenced workbook does not exist')
    }
}
