package ru.tchallenge.service.complex.domain.shared.enumerated

import groovy.transform.CompileStatic
import groovy.transform.PackageScope

import java.lang.annotation.Annotation

import org.springframework.beans.factory.annotation.Autowired

import ru.tchallenge.service.complex.common.GenericServiceBean
import ru.tchallenge.service.complex.common.enumerated.GenericEnumeratedRepository
import ru.tchallenge.service.complex.convention.component.ServiceComponent

@CompileStatic
@PackageScope
@ServiceComponent
class EnumeratedServiceBean extends GenericServiceBean implements EnumeratedService {

    // TODO: refactor the code to make it more clear (especially reflection calls)

    @Autowired
    EnumeratedContextConfigurer enumeratedContextConfigurer

    @Autowired
    Collection<GenericEnumeratedRepository> enumeratedRepositories

    @Override
    Collection<EnumeratedAggregationInfo> getAll() {
        aggregations.values()
    }

    @Override
    EnumeratedAggregationInfo getAggregationByType(String type) {
        def $result = aggregations.get(type)
        if (!$result) {
            throw new RuntimeException('unknown aggregation type')
        }
        $result
    }

    private Map<String, EnumeratedAggregationInfo> getAggregations() {
        def $result
        def $optionalResult = enumeratedContextConfigurer.value
        if ($optionalResult.present) {
            $result = $optionalResult.get()
        } else {
            $result = createMappedAggregations()
            enumeratedContextConfigurer.setValue($result)
        }
        $result
    }

    private Map<String, EnumeratedAggregationInfo> createMappedAggregations() {
        Map<String, EnumeratedAggregationInfo> $result = [:]
        for (EnumeratedAggregationInfo $aggregation : createAggregations()) {
            def $type = $aggregation.type
            if ($type) {
                $result.put($type, $aggregation)
            } else {
                logAsWarn('Enumerated aggregation discovered without any type', $aggregation)
            }
        }
        $result
    }

    private Collection<EnumeratedAggregationInfo> createAggregations() {
        foundamentals.mapCollection(enumeratedRepositories, this.&createAggregation)
    }

    private static EnumeratedAggregationInfo createAggregation(GenericEnumeratedRepository repository) {
        new EnumeratedAggregationInfo(
                items: enumerateds.all(repository),
                type: getEnumeratedType(repository)
        )
    }

    private static String getEnumeratedType(GenericEnumeratedRepository repository) {
        def $annotation = null
        def $interfaces = repository.class.interfaces
        for (def $interface : $interfaces) {
            $annotation = $interface.annotations.find { Annotation it ->
                it.annotationType() == EnumeratedAggregationSource
            }
            if ($annotation) {
                break
            }
        }
        $annotation?.invokeMethod('value', null) as String
    }
}
