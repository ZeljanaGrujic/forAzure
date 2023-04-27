package fon.bg.ac.rs.retailApp.services;

import fon.bg.ac.rs.retailApp.dtos.JobTitleDto;
import fon.bg.ac.rs.retailApp.models.JobTitle;

import java.util.List;
import java.util.Optional;

public interface JobTitleService {
    List<JobTitleDto> getJobTitles();

    JobTitleDto saveJobTitle(JobTitleDto jobTitle);

    JobTitleDto findById(int id);

    void deleteById(int id);
}
