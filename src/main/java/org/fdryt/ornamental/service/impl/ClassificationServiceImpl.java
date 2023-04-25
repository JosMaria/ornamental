package org.fdryt.ornamental.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.fdryt.ornamental.domain.ClassificationByUtility;
import org.fdryt.ornamental.repository.ClassificationRepository;
import org.fdryt.ornamental.service.ClassificationService;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class ClassificationServiceImpl implements ClassificationService {

    private final ClassificationRepository classificationRepository;

    @Override
    public Set<String> findAllByUtility() {
        log.info("return set of classifications by utility");
        return classificationRepository.findAllByUtility().stream()
                .map(classification -> classification.name())
                .collect(Collectors.toSet());
    }
}
