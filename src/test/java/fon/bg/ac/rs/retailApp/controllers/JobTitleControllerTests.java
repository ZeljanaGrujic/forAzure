package fon.bg.ac.rs.retailApp.controllers;

import fon.bg.ac.rs.retailApp.dtos.JobTitleDto;
import fon.bg.ac.rs.retailApp.servicesImpl.JobTitleServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class JobTitleControllerTests {

    @Mock
    private JobTitleServiceImpl jobTitleService;

    @Mock
    private Model model;

    @InjectMocks
    private JobTitleController jobTitleController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testGetJobTitles() {
        List<JobTitleDto> jobTitles = new ArrayList<>();
        JobTitleDto jobTitleDto= new JobTitleDto();
        jobTitleDto.setId(1);
        jobTitleDto.setDetails("Detalji 1");
        jobTitleDto.setDescription("Opis 1");
        jobTitles.add(jobTitleDto);
        when(jobTitleService.getJobTitles()).thenReturn(jobTitles);

        String viewName = jobTitleController.getJobTitles(model);
        assertEquals("JobTitle", viewName);
        verify(model, times(1)).addAttribute(eq("jobTitles"), eq(jobTitles));
    }

    @Test
    void testAddNew() {
        JobTitleDto jobTitle = new JobTitleDto();
        when(jobTitleService.saveJobTitle(jobTitle)).thenReturn(jobTitle);

        String viewName = jobTitleController.addBew(jobTitle);
        assertEquals("redirect:/jobTitles", viewName);
        verify(jobTitleService, times(1)).saveJobTitle(jobTitle);
    }

    @Test
    void testFindById() {
        int id = 1;
        JobTitleDto jobTitle = new JobTitleDto();
        when(jobTitleService.findById(id)).thenReturn(jobTitle);

        JobTitleDto result = jobTitleController.findById(id);
        assertEquals(jobTitle, result);
        verify(jobTitleService, times(2)).findById(id);
    }

    @Test
    void testUpdate() {
        JobTitleDto jobTitle = new JobTitleDto();
        when(jobTitleService.saveJobTitle(jobTitle)).thenReturn(jobTitle);

        String viewName = jobTitleController.update(jobTitle);
        assertEquals("redirect:/jobTitles", viewName);
        verify(jobTitleService, times(1)).saveJobTitle(jobTitle);
    }

    @Test
    void testDeleteById() {
        int id = 1;

        String viewName = jobTitleController.deleteById(id);
        assertEquals("redirect:/jobTitles", viewName);
        verify(jobTitleService, times(1)).deleteById(id);
    }
}