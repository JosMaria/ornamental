package org.fdryt.ornamental.service.impl;

import lombok.RequiredArgsConstructor;
import org.fdryt.ornamental.repository.ClassificationRepository;
import org.fdryt.ornamental.service.ClassificationService;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ClassificationServiceImpl implements ClassificationService {

    private final ClassificationRepository classificationRepository;

    @Override
    public Set<String> findAllClassificationByUtility() {
        return classificationRepository.findAllClassificationByUtility()
                .stream()
                .map(Enum::name)
                .collect(Collectors.toSet());
    }
}
