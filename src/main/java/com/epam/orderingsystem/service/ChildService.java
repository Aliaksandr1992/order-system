package com.epam.orderingsystem.service;

import com.epam.orderingsystem.model.Child;

import java.util.List;

public interface ChildService {
    /**
     * Save child
     * @param child is child which need to save
     */
    void saveChild(Child child);

    /**
     * Save many children
     * @param childred is list of children which we neen to save
     */
    void saveAllChildren(List<Child> childred);

    /**
     * Find child by id
     * @param id is id of necessary child
     * @return child object
     */
    Child findChildById(Long id);
}
