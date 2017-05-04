package project.services.interfaces;

import project.entities.Type;
import project.models.TypeModel;
import java.util.List;

public interface TypeService {
    List<TypeModel> getTypes();

    Type getType(String type);
}
