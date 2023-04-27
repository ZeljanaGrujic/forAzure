package fon.bg.ac.rs.retailApp.servicesImpl;

import fon.bg.ac.rs.retailApp.dtos.JobTitleDto;
import fon.bg.ac.rs.retailApp.models.JobTitle;
import fon.bg.ac.rs.retailApp.repositories.JobTitleRepository;
import fon.bg.ac.rs.retailApp.services.JobTitleService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class JobTitleServiceImpl implements JobTitleService {

    @Autowired
    private JobTitleRepository jobTitleRepository;

    @Override
    public List<JobTitleDto> getJobTitles() {

        List<JobTitle> all = jobTitleRepository.findAll();
        List<JobTitleDto> dtos = all.stream()
                .map(d -> new JobTitleDto(d.getId(),
                        d.getDescription(),
                        d.getDetails())).collect(Collectors.toList());

        return dtos;
    }

    @Override
    public JobTitleDto saveJobTitle(JobTitleDto jobTitle) {


        JobTitle d = new JobTitle();
        BeanUtils.copyProperties(jobTitle, d);

        JobTitle saved = jobTitleRepository.save(d);
        jobTitle.setId(saved.getId());
        return jobTitle;
    }

    @Override
    public JobTitleDto findById(int id) {


        JobTitle find=jobTitleRepository.findById(id).get();
        JobTitleDto d= new JobTitleDto();
        BeanUtils.copyProperties(find, d);

        return d;
    }

    @Override
    public void deleteById(int id) {
        jobTitleRepository.deleteById(id);
    }
}
