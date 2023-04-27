package fon.bg.ac.rs.retailApp.services;

import fon.bg.ac.rs.retailApp.dtos.TextileMakeDto;
import fon.bg.ac.rs.retailApp.models.TextileMake;

import java.util.List;
import java.util.Optional;

public interface TextileMakeService {
    List<TextileMakeDto> getTextileMakes();

    TextileMakeDto saveTextileMake(TextileMakeDto textileMake);

    TextileMakeDto findById(int id);

    void deleteById(int id);
}
