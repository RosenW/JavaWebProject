package project.services.implementations;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.entities.Meme;
import project.entities.Type;
import project.models.MemeModel;
import project.models.TypeModel;
import project.repository.MemeRepository;
import project.repository.TypeRepository;
import project.services.interfaces.MemeService;
import project.services.interfaces.TypeService;

import java.util.ArrayList;
import java.util.List;

@Service
public class TypeServiceImpl implements TypeService{

    private final TypeRepository typeRepository;

    private final ModelMapper modelMapper;

    @Autowired
    public TypeServiceImpl(TypeRepository typeRepository, ModelMapper modelMapper) {
        this.typeRepository = typeRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<TypeModel> getTypes() {
        List<Type> types = typeRepository.findAll();
        List<TypeModel> typeModels = new ArrayList<>();

        for (Type type : types) {
            TypeModel curTypeModel = new TypeModel();
            modelMapper.map(type, curTypeModel);
            typeModels.add(curTypeModel);
        }
        return typeModels;
    }

    @Override
    public Type getType(String type) {
        return typeRepository.findOneByName(type);
    }
}
