package org.fdryt.ornamental.service;

import org.fdryt.ornamental.dto.repertory.ItemResponseDTO;

import java.util.List;

public interface RepertoryService {

    List<ItemResponseDTO> obtainAllItems();
}
