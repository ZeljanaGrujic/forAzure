package fon.bg.ac.rs.retailApp.servicesImpl;

import fon.bg.ac.rs.retailApp.dtos.TextileStatusDto;
import fon.bg.ac.rs.retailApp.models.TextileStatus;
import fon.bg.ac.rs.retailApp.repositories.TextileStatusRepository;
import fon.bg.ac.rs.retailApp.services.TextileStatusService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TextileStatusServiceImpl implements TextileStatusService {

    @Autowired
    private TextileStatusRepository textileStatusRepository;

    @Override
    public List<TextileStatusDto> getTextileStatuses() {

        List<TextileStatus> all = textileStatusRepository.findAll();
        List<TextileStatusDto> dtos = all.stream()
                .map(d -> new TextileStatusDto(d.getId(),
                        d.getDescription(),
                        d.getDetails())).collect(Collectors.toList());

        return dtos;
    }

    @Override
    public TextileStatusDto saveTextileStatus(TextileStatusDto textileStatus) {
        TextileStatus d = new TextileStatus();
        BeanUtils.copyProperties(textileStatus, d);

        TextileStatus saved = textileStatusRepository.save(d);
        textileStatus.setId(saved.getId());
        return textileStatus;
    }

    @Override
    public TextileStatusDto findById(int id) {

        TextileStatus find=textileStatusRepository.findById(id).get();
        TextileStatusDto d= new TextileStatusDto();
        BeanUtils.copyProperties(find, d);

        return d;
    }
}
