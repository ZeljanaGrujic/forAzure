package fon.bg.ac.rs.retailApp.servicesImpl;

import fon.bg.ac.rs.retailApp.dtos.TextileMakeDto;
import fon.bg.ac.rs.retailApp.models.TextileMake;
import fon.bg.ac.rs.retailApp.repositories.TextileMakeRepository;
import fon.bg.ac.rs.retailApp.services.TextileMakeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TextileMakeServiceImpl implements TextileMakeService {

    @Autowired
    private TextileMakeRepository textileMakeRepository;

    @Override
    public List<TextileMakeDto> getTextileMakes() {

        List<TextileMake> all = textileMakeRepository.findAll();
        List<TextileMakeDto> dtos = all.stream()
                .map(d -> new TextileMakeDto(d.getId(),
                        d.getDescription(),
                        d.getDetails())).collect(Collectors.toList());

        return dtos;
    }

    @Override
    public TextileMakeDto saveTextileMake(TextileMakeDto textileMake) {
        TextileMake d = new TextileMake();
        BeanUtils.copyProperties(textileMake, d);

        TextileMake saved = textileMakeRepository.save(d);
        textileMake.setId(saved.getId());
        return textileMake;
    }

    @Override
    public TextileMakeDto findById(int id) {

        TextileMake find=textileMakeRepository.findById(id).get();
        TextileMakeDto d= new TextileMakeDto();
        BeanUtils.copyProperties(find, d);

        return d;
    }

    @Override
    public void deleteById(int id) {
        textileMakeRepository.deleteById(id);
    }
}
