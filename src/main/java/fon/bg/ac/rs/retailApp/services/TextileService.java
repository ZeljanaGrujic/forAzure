package fon.bg.ac.rs.retailApp.services;

import fon.bg.ac.rs.retailApp.dtos.TextileDto;
import fon.bg.ac.rs.retailApp.dtos.TextileStatusDto;
import fon.bg.ac.rs.retailApp.models.Textile;

import java.util.List;
import java.util.Optional;

public interface TextileService {
    List<TextileDto> getTextiles();

    TextileDto saveTextile(TextileDto textile);

    TextileDto findById(int id);

    void deleteById(int id);
    List<TextileDto> findByPurpose(String purpose);
}
