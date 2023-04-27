package fon.bg.ac.rs.retailApp.services;

import fon.bg.ac.rs.retailApp.dtos.TextileTypeDto;
import fon.bg.ac.rs.retailApp.models.TextileType;

import java.util.List;
import java.util.Optional;

public interface TextileTypeService {
    List<TextileTypeDto> getTextileTypes();

    TextileTypeDto saveTextileType(TextileTypeDto textileType);

    TextileTypeDto findById(int id);

    void deleteById(int id);
}
