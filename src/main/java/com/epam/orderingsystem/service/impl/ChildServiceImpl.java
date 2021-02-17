package com.epam.orderingsystem.service.impl;

import com.epam.orderingsystem.model.Child;
import com.epam.orderingsystem.repository.ChildRepository;
import com.epam.orderingsystem.service.ChildService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChildServiceImpl implements ChildService {
    @Autowired
    private ChildRepository childRepository;

    @Override
    public void saveChild(Child child)
    {
        childRepository.save(child);
    }

    @Override
    public void saveAllChildren(List<Child> children)
    {
        childRepository.saveAll(children);
    }

    @Override
    public Child findChildById(Long id)
    {
        return childRepository.findById(id).get();
    }
}
