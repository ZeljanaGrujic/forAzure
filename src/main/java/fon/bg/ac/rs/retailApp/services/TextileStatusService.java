package fon.bg.ac.rs.retailApp.services;

import fon.bg.ac.rs.retailApp.dtos.TextileStatusDto;
import fon.bg.ac.rs.retailApp.models.TextileStatus;

import java.util.List;
import java.util.Optional;

public interface TextileStatusService {
    List<TextileStatusDto> getTextileStatuses();

    TextileStatusDto saveTextileStatus(TextileStatusDto textileStatus);

    TextileStatusDto findById(int id);
}
