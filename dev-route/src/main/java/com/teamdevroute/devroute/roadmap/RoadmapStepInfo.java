package com.teamdevroute.devroute.roadmap;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.teamdevroute.devroute.company.Company;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Converter;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import java.io.IOException;
import java.util.Map;
import lombok.Getter;

@Entity

@Getter
public class Roadmap_step_info {
    @Id
    @GeneratedValue
    @Column(name = "roadmap_step_info_id")
    private Long id;

    @OneToOne
    @JoinColumn(name="roadmap_step_id")
    private RoadmapStep roadmap_step;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="company_id")
    private Company company;
    private String description;
    @Convert(converter = JsonConverter.class)
    private Map<String, Object> technology_stack;
    @Convert(converter = JsonConverter.class)
    private Map<String, Object> companies;
    private int used_ratio;



    public Roadmap_step_info() {
    }
}
@Converter
class JsonConverter implements AttributeConverter<Map<String, Object>, String> {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public String convertToDatabaseColumn(Map<String, Object> attribute) {
        try {
            return objectMapper.writeValueAsString(attribute);
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException("Error converting map to JSON string.", e);
        }
    }

    @Override
    public Map<String, Object> convertToEntityAttribute(String dbData) {
        try {
            return objectMapper.readValue(dbData, Map.class);
        } catch (IOException e) {
            throw new IllegalArgumentException("Error converting JSON string to map.", e);
        }
    }
}