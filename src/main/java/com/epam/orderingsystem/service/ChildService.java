package com.epam.orderingsystem.service;

import com.epam.orderingsystem.model.Child;

import java.util.List;

public interface ChildService {
    void saveChild(Child child);
    void saveAllChildren(List<Child> childred);
    Child findChildById(Long id);
}
