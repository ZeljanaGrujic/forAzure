package fon.bg.ac.rs.retailApp.services;

import fon.bg.ac.rs.retailApp.dtos.TextileModelDto;
import fon.bg.ac.rs.retailApp.models.TextileModel;

import java.util.List;
import java.util.Optional;

public interface TextileModelService {
    List<TextileModelDto> getTextileModels();

    TextileModelDto saveTextileModel(TextileModelDto textileModel);

    TextileModelDto findById(int id);

    void deleteById(int id);
}
