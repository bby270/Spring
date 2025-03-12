package com.dw.driverapp.service;

import com.dw.driverapp.exception.ResourceNotFoundException;
import com.dw.driverapp.model.Type;
import com.dw.driverapp.repository.TypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TypeService {
    @Autowired
    TypeRepository typeRepository;

    public Type typeAdd(Type newType) {
        if (newType == null || newType.getName() == null || newType.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("타입 이름은 필수입니다.");
        }
        Type savedType = typeRepository.save(newType);
        return savedType;
    }

    public Type typeUpdate(Long id, Type type) {
        Type type1 = typeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("해당 ID를 가진 타입을 찾을 수 없습니다."));
        type1.setName(type.getName());
        return typeRepository.save(type1);
    }

    public void typeDelete(Long id) {
        Type type = typeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("존재하지 않는 타입입니다."));

        typeRepository.delete(type);
    }
}