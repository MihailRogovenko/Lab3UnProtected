package com.tpp.tpplab3.controllers;

import com.tpp.tpplab3.models.Groups;
import com.tpp.tpplab3.service.GroupService;
import com.tpp.tpplab3.service.CuratorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/groups")
public class GroupController {

    @Autowired
    private GroupService groupService;
    @Autowired
    private CuratorService curatorService;

    @GetMapping
    public String listGroups(Model model) {
        model.addAttribute("groups", groupService.getAllGroups());
        return "groups";
    }

    @GetMapping("/add")
    public String addGroupForm(Model model) {
        model.addAttribute("group", new Groups());
        model.addAttribute("curators", curatorService.getAllCurators());
        return "add-group";
    }

    @PostMapping("/add")
    public String addGroup(@Valid @ModelAttribute("group") Groups group, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("curators", curatorService.getAllCurators());
            return "add-group";
        }
        groupService.saveGroup(group);
        return "redirect:/groups";
    }

    @GetMapping("/edit/{id}")
    public String editGroupForm(@PathVariable("id") Integer id, Model model) {
        Optional<Groups> groupOpt = groupService.findGroupById(id);
        if (groupOpt.isPresent()) {
            model.addAttribute("group", groupOpt.get());
            model.addAttribute("curators", curatorService.getAllCurators());
            return "edit-group";
        }
        return "redirect:/groups";
    }

    @PostMapping("/update/{id}")
    public String updateGroup(@PathVariable("id") Integer id, @Valid @ModelAttribute("group") Groups group,
                              BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("curators", curatorService.getAllCurators());
            return "edit-group";
        }
        group.setGroupId(id);
        groupService.updateGroup(group);
        return "redirect:/groups";
    }

    @GetMapping("/delete/{id}")
    public String deleteGroup(@PathVariable("id") Integer id) {
        groupService.deleteGroupById(id);
        return "redirect:/groups";
    }
}
