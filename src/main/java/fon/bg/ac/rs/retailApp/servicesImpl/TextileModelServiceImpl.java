package fon.bg.ac.rs.retailApp.servicesImpl;

import fon.bg.ac.rs.retailApp.dtos.TextileModelDto;
import fon.bg.ac.rs.retailApp.models.TextileModel;
import fon.bg.ac.rs.retailApp.repositories.TextileModelRepository;
import fon.bg.ac.rs.retailApp.services.TextileModelService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TextileModelServiceImpl implements TextileModelService {

    @Autowired
    private TextileModelRepository textileModelRepository;

    @Override
    public List<TextileModelDto> getTextileModels() {


        List<TextileModel> all = textileModelRepository.findAll();
        List<TextileModelDto> dtos = all.stream()
                .map(d -> new TextileModelDto(d.getId(),
                        d.getDescription(),
                        d.getDetails())).collect(Collectors.toList());

        return dtos;
    }

    @Override
    public TextileModelDto saveTextileModel(TextileModelDto textileModel) {
        TextileModel d = new TextileModel();
        BeanUtils.copyProperties(textileModel, d);

        TextileModel saved = textileModelRepository.save(d);
        textileModel.setId(saved.getId());
        return textileModel;
    }

    @Override
    public TextileModelDto findById(int id) {

        TextileModel find=textileModelRepository.findById(id).get();
        TextileModelDto d= new TextileModelDto();
        BeanUtils.copyProperties(find, d);

        return d;
    }

    @Override
    public void deleteById(int id) {
        textileModelRepository.deleteById(id);
    }
}
