package com.upao.pe.fhahback.services;

import com.upao.pe.fhahback.models.Color;
import com.upao.pe.fhahback.repositories.ColorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ColorService {
    @Autowired
    ColorRepository colorRepository;

    public Color createColor(String colorName){
        Color color = new Color(null, colorName, new ArrayList<>());
        return colorRepository.save(color);
    }

    public Color getColor(String colorName){
        Optional<Color> color = colorRepository.findByColorName(colorName);
        if(color.isEmpty()){
            throw new RuntimeException("Color no encontrado");
        }
        return color.get();
    }

    public List<Color> getColors(List<String> colorNames){
        return colorNames.isEmpty() ? null : colorRepository.findByColorNameIn(colorNames);
    }

    public boolean existsColor(String colorName){
        return colorRepository.existsByColorName(colorName);
    }
}
