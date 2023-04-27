package fon.bg.ac.rs.retailApp.servicesImpl;

import fon.bg.ac.rs.retailApp.dtos.TextileTypeDto;
import fon.bg.ac.rs.retailApp.models.TextileType;
import fon.bg.ac.rs.retailApp.repositories.TextileTypeRepository;
import fon.bg.ac.rs.retailApp.services.TextileTypeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TextileTypeServiceImpl implements TextileTypeService {
    @Autowired
    private TextileTypeRepository textileTypeRepository;

    @Override
    public List<TextileTypeDto> getTextileTypes() {

        List<TextileType> all = textileTypeRepository.findAll();
        List<TextileTypeDto> dtos = all.stream()
                .map(d -> new TextileTypeDto(d.getId(),
                        d.getDescription(),
                        d.getDetails())).collect(Collectors.toList());

        return dtos;
    }

    @Override
    public TextileTypeDto saveTextileType(TextileTypeDto textileType) {
        TextileType d = new TextileType();
        BeanUtils.copyProperties(textileType, d);

        TextileType saved = textileTypeRepository.save(d);
        textileType.setId(saved.getId());
        return textileType;
    }

    @Override
    public TextileTypeDto findById(int id) {

        TextileType find=textileTypeRepository.findById(id).get();
        TextileTypeDto d= new TextileTypeDto();
        BeanUtils.copyProperties(find, d);

        return d;
    }

    @Override
    public void deleteById(int id) {

        textileTypeRepository.deleteById(id);
    }
}
